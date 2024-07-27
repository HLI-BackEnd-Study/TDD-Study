package org.example.pay.repository

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.model.RequestedSettlement
import org.example.pay.model.RequestedSettlementDetail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@DataJpaTest
class RequestSettlementRepositoryTest {
    @Autowired
    private lateinit var repository: RequestSettlementRepository

    @Test
    fun `정산금 요청정보 저장 테스트`() {
        val requestedSettlementDetails = listOf(
            RequestedSettlementDetail(
                amount = BigDecimal(10_000),
                requestedPersonId = 1
            ),
            RequestedSettlementDetail(
                amount = BigDecimal(10_000),
                requestedPersonId = 2
            ),
            RequestedSettlementDetail(
                amount = BigDecimal(10_000),
                requestedPersonId = 3
            )
        )
        val entity = RequestedSettlement(
            requestName = "한화생명 보험료 정산",
            requesterId = 1,
            amount = BigDecimal(30_000),
            requestDateTime = LocalDateTime.now(),
            requestDetails = requestedSettlementDetails
        )

        val result = repository.save(entity)

        assertSoftly {
            assertThat(result.requestDetails.size).isEqualTo(entity.requestDetails.size)
            assertThat(result.requestName).isEqualTo(entity.requestName)
        }
    }
}
