package com.eritlab.jexmon.domain.use_case.books

import com.eritlab.jexmon.common.Resource
import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.model.bookModel.BookModel
import com.eritlab.jexmon.domain.repo.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBookUseCase @Inject constructor(private val bookRepository: BookRepository) {
    suspend operator fun invoke():List<BookItem>{
        return bookRepository.getBooks().shuffled()
    }

}