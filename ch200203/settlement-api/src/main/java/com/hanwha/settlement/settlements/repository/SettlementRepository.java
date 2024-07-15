package com.hanwha.settlement.settlements.repository;

import com.hanwha.settlement.settlements.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, Long> {
}
