package com.eritlab.jexmon.presentation.screens.product_detail_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.domain.item.BookDetailItem
import com.eritlab.jexmon.domain.use_case.book_detail.BookDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BookDetailViewModel @Inject constructor(private val bookDetailUseCase: BookDetailUseCase,stateHandle: SavedStateHandle):ViewModel() {
    private val _bookDetail = MutableStateFlow<BookDetailItem?>(null)
    val bookDetail:StateFlow<BookDetailItem?> get() = _bookDetail
    init {
        val bookId = stateHandle.get<String>(Constrains.PRODUCT_ID_PARAM)
        Log.e("ID book", bookId.toString())
        getBookDetail(bookId!!.toInt())
    }

    private fun getBookDetail(bookId: Int) {
        viewModelScope.launch {
            try {
                val detailBook = bookDetailUseCase(bookId)
                _bookDetail.value = detailBook
            }catch (_:Exception){}
        }
    }
}