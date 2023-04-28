package com.eritlab.jexmon.domain.use_case.get_detail_game

import com.eritlab.jexmon.common.Resource
import com.eritlab.jexmon.domain.item.GameDetailItem
import com.eritlab.jexmon.domain.repo.GameDetailRepository
import java.util.concurrent.Flow
import javax.inject.Inject

class GameProductDetailUseCase @Inject constructor(private val gameDetailRepository: GameDetailRepository) {
    suspend operator fun invoke(id:Int):GameDetailItem{
        return gameDetailRepository.getDetail(id)
    }
}