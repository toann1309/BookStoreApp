package com.eritlab.jexmon.domain.use_case.games

import com.eritlab.jexmon.domain.item.GameItem
import com.eritlab.jexmon.domain.repo.GameRepository
import javax.inject.Inject

class GetFilterGamesUseCase @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(platform:String, category:String, sortBy:String):List<GameItem>{
        return gameRepository.getFilterGame(platform, category, sortBy).shuffled()
    }
}