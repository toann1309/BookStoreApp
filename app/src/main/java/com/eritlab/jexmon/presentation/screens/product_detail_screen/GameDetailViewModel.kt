package com.eritlab.jexmon.presentation.screens.product_detail_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.domain.item.GameDetailItem
import com.eritlab.jexmon.domain.use_case.get_detail_game.GameProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class GameDetailViewModel @Inject constructor(private val gameProductDetailUseCase: GameProductDetailUseCase,stateHandle: SavedStateHandle):ViewModel() {
    private val _gameDetail = MutableStateFlow<GameDetailItem?>(null)
    val gameDetail:StateFlow<GameDetailItem?> get() = _gameDetail
    init {
        val gameId = stateHandle.get<String>(Constrains.PRODUCT_ID_PARAM)
        Log.e("ID game", gameId.toString())
        getGameDetail(gameId!!.toInt())
    }
    private fun getGameDetail(gameId:Int) {
        viewModelScope.launch {
            try{
                val gameDetail=gameProductDetailUseCase(gameId)
                _gameDetail.value = gameDetail
            }catch (_:Exception){}
        }
    }
}