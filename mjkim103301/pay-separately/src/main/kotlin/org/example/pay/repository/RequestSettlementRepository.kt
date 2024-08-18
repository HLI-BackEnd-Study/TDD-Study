package org.example.pay.repository

import org.example.pay.domain.model.Settlement
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RequestSettlementRepository : JpaRepository<Settlement, Long> {
    fun findByRequesterId(requesterId: Long): Optional<Settlement>
}
