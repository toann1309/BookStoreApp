package com.eritlab.jexmon.domain.use_case.books

import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.repo.BookRepository
import javax.inject.Inject

class GetFilterBooksUseCase @Inject constructor(private val bookRepository: BookRepository){
    suspend operator fun invoke(price:String,publisher:String):List<BookItem>{
        return bookRepository.getFilterBook(price,publisher).shuffled()
    }
}