package org.example.pay.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.example.pay.domain.table.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class User(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<User>(Users)
    var name by Users.name
}
