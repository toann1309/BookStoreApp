package com.eritlab.jexmon.presentation.screens.favourite_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Resource
import com.eritlab.jexmon.domain.item.GameItem
import com.eritlab.jexmon.domain.use_case.games.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavouriteViewModel @Inject constructor(private val getGameUseCase: GetGameUseCase) :ViewModel() {
    private val _games = MutableStateFlow(emptyList<GameItem>())
    val games:StateFlow<List<GameItem>> get() = _games
    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch {
            try{
                val games=getGameUseCase()
                _games.value=games
            }catch (_:Exception){}
        }
    }
}