package com.eritlab.jexmon.domain.use_case.games

import com.eritlab.jexmon.domain.item.GameItem
import com.eritlab.jexmon.domain.repo.GameRepository
import javax.inject.Inject

class GetCategoryGameUseCase @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(category:String):List<GameItem>{
        return gameRepository.getCategoryGame(category).shuffled()
    }
}