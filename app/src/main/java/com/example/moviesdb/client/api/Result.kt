/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.moviesdb.client.api

import com.example.moviesdb.client.model.ErrorMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    //    data class Error(val exception: Exception) : MovieData<Nothing>()
    data class Error<out T>(val errorMessage: ErrorMessage? = null) : Result<T>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[errorCode=${errorMessage?.errorCode}]]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Result.Success] & holds non-null [Result.Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null

/**
 * `true` if [Result] is of type [Result.Error].
 */
val Result<*>.failed
    get() = this is Result.Error

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

val <T> Result<T>.requireData: T
    get() = (this as? Result.Success)!!.data

val <T> Result<T>.error: Exception?
    get() = (this as? Result.Error)?.errorMessage?.exception

val <T> Result<T>.errorMessage: ErrorMessage?
    get() = (this as? Result.Error)?.errorMessage

fun <T> Flow<Result<T>>.onSuccess(action: suspend (T) -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Success<T>) {
            action(result.data)
        }
        return@transform emit(result)
    }

fun <T> Flow<Result<T>>.doOnSuccess(
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    action: (T) -> Unit
): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Success<T>) {
            scope.launch {
                action(result.data)
            }
        }
        return@transform emit(result)
    }

fun <T> Flow<Result<T>>.mapSuccess(): Flow<T> =
    transform { result ->
        if (result is Result.Success<T>) {
            emit(result.data)
        }
    }

fun <T> Flow<Result<T>>.onError(
    action: suspend (ErrorMessage) -> Unit = {}
): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Error) {
            result.errorMessage?.let { action(it) }
        }
        return@transform emit(result)
    }
