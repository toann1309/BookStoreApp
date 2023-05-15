package com.eritlab.jexmon.presentation.screens.favourite_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.use_case.books.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val getBookUseCase: GetBookUseCase):ViewModel(){
    private val _books = MutableStateFlow(emptyList<BookItem>())
    val books:StateFlow<List<BookItem>> get() = _books
    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            try {
                val books = getBookUseCase()
                _books.value = books
            }catch (_:Exception){}
        }
    }
}