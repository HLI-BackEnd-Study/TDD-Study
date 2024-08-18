//package org.example.pay.repository
//
//import org.assertj.core.api.Assertions.assertThat
//import org.assertj.core.api.SoftAssertions.assertSoftly
//import org.example.pay.domain.model.Settlement
//import org.example.pay.domain.model.SettlementDetail
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import java.math.BigDecimal
//import java.time.LocalDateTime
//
//@ExtendWith(SpringExtension::class)
//@DataJpaTest
//class RequestSettlementRepositoryTest {
//    @Autowired
//    private lateinit var repository: RequestSettlementRepository
//
//    @Test
//    fun `정산금 요청정보 저장 테스트`() {
//        val entity = Settlement(
//            requestName = "한화생명 보험료 정산",
//            requesterId = 1,
//            amount = BigDecimal(30_000),
//            discountAmount = BigDecimal.ZERO,
//            requestDateTime = LocalDateTime.now()
//        )
//
//        val result = repository.save(entity)
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
//        val detailsResult = repository
//
//
//        assertSoftly {
//            assertThat(result.requestName).isEqualTo(entity.requestName)
//        }
//    }
//
//    @Test
//    fun `내가 정산 요청한 목록 조회 테스트`() {
//
//    }
//}
