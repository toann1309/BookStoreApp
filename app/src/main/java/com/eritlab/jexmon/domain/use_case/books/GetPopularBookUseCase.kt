package com.eritlab.jexmon.domain.use_case.books

import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.repo.BookRepository
import javax.inject.Inject

class GetPopularBookUseCase @Inject constructor(private val bookRepository: BookRepository){
    suspend operator fun invoke():List<BookItem>{
        return bookRepository.getPopularBook().shuffled();
    }
}