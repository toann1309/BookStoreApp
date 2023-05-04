package com.eritlab.jexmon.presentation.screens.search_screen.component

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.use_case.books.GetSearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchBookViewModel @Inject constructor(private val getSearchBooksUseCase: GetSearchBooksUseCase, stateHandle: SavedStateHandle):ViewModel() {
    private val _searchBooks = MutableStateFlow(emptyList<BookItem>())
    val searchBooks:StateFlow<List<BookItem>> get() = _searchBooks
    init {
        val searchBook = stateHandle.get<String>(Constrains.SEARCH_BOOK)
        Log.e("Search-book", searchBook.toString())
        getSearchBooks(searchBook!!)
    }

    private fun getSearchBooks(searchBook: String) {
        viewModelScope.launch {
            try {
                val bookSearch = getSearchBooksUseCase(searchBook)
                _searchBooks.value = bookSearch
            }catch (_:Exception){}
        }
    }
}