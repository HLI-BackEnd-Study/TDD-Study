package com.tdd.settlement.domain;

import java.util.List;
import lombok.Getter;

/**
 * Name : Settlement <br/>
 * Description : Create a description.
 */
@Getter
public class Settlement {
    private final User owner;
    private final Amount requestAmount;
    private final SettlementDetail settlementDetail;

    public Settlement(User owner, Amount requestAmount, List<User> userList) {
        this.owner = owner;
        this.requestAmount = requestAmount;
        this.settlementDetail = new SettlementDetail(requestAmount, userList);
    }

    public List<User> requestSettlement() {
        List<User> userList = settlementDetail.requestSettlement();
        this.owner.getRequestSettlements().add(this);
        return userList.stream().toList();
    }

    public List<User> requestDivSettlement() {
        return settlementDetail.requestDivSettlement();
    }

    public void sendSettlementAmount(String id) {
        settlementDetail.sendSettlementAmount(id);
    }

    public List<SettlementDetail> getSendSettlementDetail(String id) {
        return settlementDetail.getSendSettlementDetail(id);
    }
}
