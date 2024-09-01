package org.example.pay.service

import org.example.pay.repository.UserRepository
import org.example.pay.repository.UserRepositoryImpl
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository = UserRepositoryImpl()) {
    fun findUser(userId: Long) = userRepository.findById(userId)
}
