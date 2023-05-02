package com.eritlab.jexmon.presentation.screens.search_screen.component

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.domain.item.GameItem
import com.eritlab.jexmon.domain.use_case.games.GetCategoryGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavouriteCategoryViewModel @Inject constructor(private val getCategoryGameUseCase: GetCategoryGameUseCase, stateHandle: SavedStateHandle):ViewModel(){
    private val _gamesCategory = MutableStateFlow(emptyList<GameItem>())
    val gamesCategory:StateFlow<List<GameItem>> get() = _gamesCategory
    init {
        val categoryGame = stateHandle.get<String>(Constrains.SEARCH_CATEGORY)
        Log.e("Category", categoryGame.toString())
        getCategoryGames(categoryGame!!)
    }

    private fun getCategoryGames(categoryGame:String) {
        viewModelScope.launch {
            try {
                val gamesCategory = getCategoryGameUseCase(categoryGame)
                _gamesCategory.value = gamesCategory
            }catch (_:Exception){}
        }
    }

}