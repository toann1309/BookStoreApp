package com.eritlab.jexmon.presentation.screens.filter_screen.component

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.domain.item.GameItem
import com.eritlab.jexmon.domain.use_case.games.GetFilterGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterGameViewModel @Inject constructor(private val getFilterGamesUseCase: GetFilterGamesUseCase,stateHandle: SavedStateHandle):ViewModel() {
    private val _gameFilter = MutableStateFlow(emptyList<GameItem>())
    val gameFilter:StateFlow<List<GameItem>> get() = _gameFilter
    init {
        val platformParam = stateHandle.get<String>(Constrains.PLATFORM)
        val categoryParam = stateHandle.get<String>(Constrains.CATEGORY)
        val sortByParam = stateHandle.get<String>(Constrains.SORTBY)
        Log.e("platformParam",platformParam.toString())
        Log.e("categoryParam",categoryParam.toString())
        Log.e("sortByParam",sortByParam.toString())
        getFilterGamesAPI(platformParam!!,categoryParam!!,sortByParam!!)
    }

    private fun getFilterGamesAPI(platformParam: String, categoryParam: String, sortByParam: String) {
        viewModelScope.launch {
            try {
                val gameFilter = getFilterGamesUseCase(platformParam,categoryParam,sortByParam)
                _gameFilter.value = gameFilter
            }catch (_:Exception){}
        }

    }
}