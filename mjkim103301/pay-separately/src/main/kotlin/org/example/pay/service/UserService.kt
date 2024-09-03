package org.example.pay.service

import org.example.pay.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(private val userRepository: UserRepository) {
    fun findUser(userId: Long) = userRepository.findById(userId)
}
