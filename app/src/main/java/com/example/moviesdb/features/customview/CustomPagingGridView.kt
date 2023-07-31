package com.example.moviesdb.features.customview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.moviesdb.ui.theme.dimensions

@ExperimentalFoundationApi
@Composable
fun <T : Any> CustomPagingGridView(
    lazyPagingItems: LazyPagingItems<T>,
    count: Int,
    initItemView: (@Composable (itemIndex: Int) -> Unit),
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensions()._16Dp,
            end = dimensions()._16Dp,
            top = dimensions()._20Dp,
        ),
        columns = GridCells.Fixed(count),
        content = {
            items(lazyPagingItems.itemCount) { itemIndex ->
                initItemView(itemIndex)
            }

            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            val error = (loadState.append as LoadState.Error).error
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Error loading more data: ${error.localizedMessage}")
                                Button(onClick = { lazyPagingItems.retry() }) {
                                    Text("Retry")
                                }
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {
                                val error = (loadState.refresh as LoadState.Error).error
                                Text("Initial Data Error: ${error.localizedMessage}")
                                Button(onClick = { lazyPagingItems.retry() }) {
                                    Text("Retry")
                                }
                            }
                        }
                    }
                }
            }

        },
    )
}
