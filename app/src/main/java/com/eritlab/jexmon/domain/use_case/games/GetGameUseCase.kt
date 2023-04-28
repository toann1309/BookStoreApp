package com.eritlab.jexmon.domain.use_case.games

import com.eritlab.jexmon.domain.item.GameItem
import com.eritlab.jexmon.domain.repo.GameRepository
import javax.inject.Inject

class GetGameUseCase @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke():List<GameItem>{
        return gameRepository.getGames().shuffled()
    }
//    operator fun invoke(): kotlinx.coroutines.flow.Flow<Resource<List<GameItem>>> = flow {
//        try {
//            emit(Resource.Loading())
//            val products = gameRepository.getGames()?.map { it }
//            emit(Resource.Success(data = products))
//        } catch (e: Exception) {
//            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
//        }
//    }
}