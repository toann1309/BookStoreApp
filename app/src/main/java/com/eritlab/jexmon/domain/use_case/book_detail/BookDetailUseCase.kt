package com.eritlab.jexmon.domain.use_case.book_detail

import com.eritlab.jexmon.domain.item.BookDetailItem
import com.eritlab.jexmon.domain.repo.BookDetailRepository
import javax.inject.Inject

class BookDetailUseCase @Inject constructor(private val bookDetailRepository: BookDetailRepository){
    suspend operator fun invoke(id:Int):BookDetailItem{
        return bookDetailRepository.getBookDetail(id)
    }
}