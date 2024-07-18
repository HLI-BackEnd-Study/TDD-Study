package com.hanwha.settlement.settlements.repository;

import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    List<Settlement> findSettlementByRequestUser(User requestUser);
}
