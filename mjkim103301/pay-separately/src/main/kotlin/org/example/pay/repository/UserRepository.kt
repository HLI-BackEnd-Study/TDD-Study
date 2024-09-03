package org.example.pay.repository

import org.example.pay.domain.model.User
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {
    fun findById(userId: Long): User
}

class UserRepositoryImpl : UserRepository {
    override fun findById(userId: Long): User {
        return transaction {
            User.findById(userId) ?: throw NoSuchElementException("조회할 사용자가 없습니다. => UserId: ${userId}")
        }
    }

}
