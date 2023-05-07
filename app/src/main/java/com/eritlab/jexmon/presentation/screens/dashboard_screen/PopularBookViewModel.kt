package com.eritlab.jexmon.presentation.screens.dashboard_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.use_case.books.GetPopularBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularBookViewModel @Inject constructor(private val getPopularBookUseCase: GetPopularBookUseCase):ViewModel(){
    private val _popularBooks = MutableStateFlow(emptyList<BookItem>())
    val popular:StateFlow<List<BookItem>> get() = _popularBooks
    init {
        getPopularBook()
    }

    private fun getPopularBook() {
        viewModelScope.launch {
            try{
                val popularBook = getPopularBookUseCase()
                _popularBooks.value = popularBook
            }
            catch (_:Exception){}
        }
    }
}