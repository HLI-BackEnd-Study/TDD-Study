package org.example.pay.repository

import com.sun.tools.javac.jvm.PoolConstant.LoadableConstant.Long
import jdk.dynalink.StandardNamespace.findFirst
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.model.RequestedSettlement
import org.example.pay.model.RequestedSettlementDetail
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 정산금 송금 레포지토리 테스트
 */
@ExtendWith(SpringExtension::class)
@DataJpaTest
class RemitSettlementRepositoryTest() {
    @Autowired
    private lateinit var remitSettlementRepository: RemitSettlementRepository

    @Autowired
    private lateinit var requestSettlementRepository: RequestSettlementRepository

    @DisplayName("정산금 요청 entity 저장")
    @BeforeEach
    fun setUp() {
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

        val result = requestSettlementRepository.save(entity)
        assertSoftly {
            assertThat(result.requestDetails.size).isEqualTo(entity.requestDetails.size)
            assertThat(result.requestName).isEqualTo(entity.requestName)
        }
    }

    @Test
    fun `요청받은 정산금 송금 테스트`() {
        val requesterId: Long = 1
        val requestedPersonId: Long = 2
        val requestedSettlement = requestSettlementRepository.findByRequesterId(requesterId)
        val requestedSettlementDetails:List<RequestedSettlementDetail> = requestedSettlement.get().requestDetails
        val myRequestedSettlementDetail:RequestedSettlementDetail = requestedSettlementDetails
            .filter { it.requestedPersonId == requestedPersonId }[0]

        val remit = RequestedSettlementDetail(
            id = myRequestedSettlementDetail.id,
            amount = myRequestedSettlementDetail.amount,
            requestedPersonId = myRequestedSettlementDetail.requestedPersonId,
            isCompleted = true,
            completionDateTime = LocalDateTime.now(),
        )

        val result = remitSettlementRepository.save(remit)


        
        assertTrue(result.isCompleted)
    }

}
