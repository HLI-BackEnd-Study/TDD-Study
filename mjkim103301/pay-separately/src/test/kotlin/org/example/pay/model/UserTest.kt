package org.example.pay.model

import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class UserTest{

    @Test
    fun `사용자 조회 테스트`(){
        val user = listOf(
            User(1, "Alice"),
            User(2, "Bob"),
            User(3, "Charlie"),
            User(4, "Dave"),
            User(5, "Minji")
        )
        assertSoftly {
            for (user in user) {
                println(user.toString())
            }
        }
    }
}
