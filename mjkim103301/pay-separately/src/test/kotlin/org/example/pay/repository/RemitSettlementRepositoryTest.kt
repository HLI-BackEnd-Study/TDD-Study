//package org.example.pay.repository
//
//import org.assertj.core.api.Assertions.assertThat
//import org.assertj.core.api.SoftAssertions.assertSoftly
//import org.example.pay.domain.model.Settlement
//import org.example.pay.domain.model.SettlementDetail
//import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import java.math.BigDecimal
//import java.time.LocalDateTime
//
///**
// * 정산금 송금 레포지토리 테스트
// */
//@ExtendWith(SpringExtension::class)
//@DataJpaTest
//class RemitSettlementRepositoryTest() {
//    @Autowired
//    private lateinit var remitSettlementRepository: RemitSettlementRepository
//
//    @Autowired
//    private lateinit var requestSettlementRepository: RequestSettlementRepository
//
//    @DisplayName("정산금 요청 entity 저장")
//    @BeforeEach
//    fun setUp() {
//        val entity = Settlement(
//            requestName = "한화생명 보험료 정산",
//            requesterId = 1,
//            amount = BigDecimal(30_000),
//            discountAmount = BigDecimal.ZERO,
//            requestDateTime = LocalDateTime.now()
//        )
//        val result = requestSettlementRepository.save(entity)
//
//        val settlementDetails = listOf(
//            SettlementDetail(
//                amount = BigDecimal(10_000),
//                requestedPersonId = 1,
//                settlement = result
//            ),
//            SettlementDetail(
//                amount = BigDecimal(10_000),
//                requestedPersonId = 2,
//                settlement = result
//            ),
//            SettlementDetail(
//                amount = BigDecimal(10_000),
//                requestedPersonId = 3,
//                settlement = result
//            )
//        )
//
//        assertSoftly {
//            assertThat(result.requestDetails.size).isEqualTo(entity.requestDetails.size)
//            assertThat(result.requestName).isEqualTo(entity.requestName)
//        }
//    }
//
//    @Test
//    fun `요청받은 정산금 송금 테스트`() {
//        val requesterId: Long = 1
//        val requestedPersonId: Long = 2
//        val requestedSettlement = requestSettlementRepository.findByRequesterId(requesterId)
//        val settlementDetails:List<SettlementDetail> = requestedSettlement.get().requestDetails
//        val mySettlementDetail: SettlementDetail = settlementDetails
//            .filter { it.requestedPersonId == requestedPersonId }[0]
//
//        val remit = SettlementDetail(
//            id = mySettlementDetail.id,
//            amount = mySettlementDetail.amount,
//            requestedPersonId = mySettlementDetail.requestedPersonId,
//            isCompleted = true,
//            completionDateTime = LocalDateTime.now(),
//            settlement = TODO(),
//        )
//
//        val result = remitSettlementRepository.save(remit)
//
//
//
//        assertTrue(result.isCompleted)
//    }
//
//}
