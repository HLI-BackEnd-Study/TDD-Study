package org.example.pay.domain.model


import org.example.pay.domain.table.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class User(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<User>(Users)
    var name by Users.name
}
