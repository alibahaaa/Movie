package ir.baha.moviedetail.vm

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.baha.designsystem.base.BaseViewModel
import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val getMovieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel<MovieDetailIntent, MovieDetailAction, MovieDetailState>() {

    override fun createInitialState(): MovieDetailState = MovieDetailState.Idle

    override fun handleIntent(intent: MovieDetailIntent): MovieDetailAction = when (intent) {
        is MovieDetailIntent.GetMovieDetail -> MovieDetailAction.Movie(intent.movieId)
    }

    override fun handleAction(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.Movie -> viewModelScope.launch {
                setState { MovieDetailState.Loading }
                when (val response = getMovieDetailUseCase(action.movieId)) {
                    is Resource.Error -> setState { MovieDetailState.Error(response.exception?.message) }
                    is Resource.Success -> setState { MovieDetailState.ShowMovieList(response.data) }
                }
            }
        }
    }

}


sealed class MovieDetailIntent {
    class GetMovieDetail(val movieId: Int) : MovieDetailIntent()
}

sealed class MovieDetailAction {
    class Movie(val movieId: Int) : MovieDetailAction()
}

sealed class MovieDetailState {
    object Idle : MovieDetailState()
    object Loading : MovieDetailState()
    data class ShowMovieList(val movie: MovieDetailEntity?) : MovieDetailState()
    data class Error(val error: String?) : MovieDetailState()
}