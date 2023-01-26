package ir.baha.moviedetail.vm

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.baha.designsystem.base.BaseViewModel
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.usecase.GetMovieListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val getMovieListUseCase: GetMovieListUseCase
) : BaseViewModel<MovieListIntent, MovieListAction, MovieListState>() {

    override fun createInitialState(): MovieListState = MovieListState.Loading

    override fun handleIntent(intent: MovieListIntent): MovieListAction = when (intent) {
        MovieListIntent.GetMovieList -> MovieListAction.MovieList
    }

    override fun handleAction(action: MovieListAction) {
        when (action) {
            MovieListAction.MovieList -> viewModelScope.launch {
                when (val response = getMovieListUseCase()) {
                    is Resource.Error -> setState { MovieListState.Error(response.exception?.message) }
                    is Resource.Loading -> setState { MovieListState.Loading }
                    is Resource.Success -> setState { MovieListState.ShowMovieList(response.data) }
                }
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
    object Loading : MovieListState()
    data class ShowMovieList(val movies: List<MovieEntity>?) : MovieListState()
    data class Error(val error: String?) : MovieListState()
}