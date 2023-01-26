package ir.baha.movielist.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.baha.designsystem.base.BaseViewModel
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.usecase.GetMovieListUseCase
import ir.baha.movielist.paging.MovieListPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : BaseViewModel<MovieListIntent, MovieListAction, MovieListState>() {

    override fun createInitialState(): MovieListState = MovieListState.Idle

    override fun handleIntent(intent: MovieListIntent): MovieListAction = when (intent) {
        MovieListIntent.GetMovieList -> MovieListAction.MovieList
    }

    override fun handleAction(action: MovieListAction) {
        when (action) {
            MovieListAction.MovieList -> {
                setState { MovieListState.Loading }
                val movieListPage = Pager(PagingConfig(pageSize = 10)) {
                    MovieListPagingSource(getMovieListUseCase)
                }.flow.cachedIn(viewModelScope)
                setState { MovieListState.Pager(movieListPage) }
            }
        }
    }

}


sealed class MovieListIntent {
    object GetMovieList : MovieListIntent()
}

sealed class MovieListAction {
    object MovieList : MovieListAction()
}

sealed class MovieListState {
    object Idle : MovieListState()
    object Loading : MovieListState()
    data class Pager(val movies: Flow<PagingData<MovieEntity>>) : MovieListState()
}