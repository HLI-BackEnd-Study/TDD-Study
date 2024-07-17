package com.hanwha.settlement.settlements.repository;

import com.hanwha.settlement.settlements.model.SettlementReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementReceiveRepository extends JpaRepository<SettlementReceive, Long> {
}
