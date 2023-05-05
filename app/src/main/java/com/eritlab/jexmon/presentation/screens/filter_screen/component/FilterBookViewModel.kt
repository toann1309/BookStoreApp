package com.eritlab.jexmon.presentation.screens.filter_screen.component

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.use_case.books.GetFilterBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FilterBookViewModel @Inject constructor(private val getFilterBooksUseCase: GetFilterBooksUseCase, stateHandle: SavedStateHandle):ViewModel(){
    private val _bookFilter = MutableStateFlow(emptyList<BookItem>())
    val bookFilter:StateFlow<List<BookItem>> get() = _bookFilter
    init {
        val priceParam = stateHandle.get<String>(Constrains.PRICE)
        val publisherParam = stateHandle.get<String>(Constrains.PUBLISHER)
        Log.e("priceParam", priceParam.toString())
        Log.e("publisherParam", publisherParam.toString())
        getFilterBooks(priceParam!!,publisherParam!!)
    }

    private fun getFilterBooks(priceParam: String, publisherParam: String) {
        viewModelScope.launch {
            try {
                val booksFilter = getFilterBooksUseCase(priceParam,publisherParam)
                _bookFilter.value = booksFilter
            }catch (_:Exception){}
        }
    }
}