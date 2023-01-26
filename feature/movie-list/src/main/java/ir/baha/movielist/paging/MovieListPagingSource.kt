package ir.baha.movielist.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.usecase.GetMovieListUseCase

class MovieListPagingSource(
    private val getMovieListUseCase: GetMovieListUseCase
) : PagingSource<Int, MovieEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> = try {
        val page = params.key ?: 1

        when (val res = getMovieListUseCase(page)) {
            is Resource.Error -> LoadResult.Error(res.exception!!)
            is Resource.Success -> LoadResult.Page(
                data = res.data!!,
                prevKey = null,
                nextKey = page + 1
            )
        }

    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}