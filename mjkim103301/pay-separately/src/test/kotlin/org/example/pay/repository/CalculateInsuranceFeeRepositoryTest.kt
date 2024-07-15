package org.example.pay.repository

import jakarta.persistence.EntityManager
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.model.InsuranceFee
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal

class CalculateInsuranceFeeRepositoryTest(val repository: CalculateInsuranceFeeRepository) {
    @Test
    fun `보험료 계산 테스트`() {
        val entity = InsuranceFee(userId = 1, premium = BigDecimal.valueOf(30000))
        val result = repository.save(entity)

        assertSoftly {
            assertEquals(entity.userId, result.userId)
            assertEquals(entity.premium, result.premium)
            assertEquals(entity.paymentCompleted, result.paymentCompleted)
            assertEquals(entity.completedDateTime, result.completedDateTime)
        }
    }
}
