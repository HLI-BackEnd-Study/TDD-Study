package com.example.pay.request

import org.junit.jupiter.api.Test

class PayRequestTest {
    @Test fun `금액 확인`() {
        // Given

        // When

        // Then

    }

    /**
     * 1. 사용자는 다수의 사람들에게 금액을 지정하여 정산 요청을 할 수 있습니다.
     * 2. 요청받은 사용자는 정산하기 버튼을 통해서 요청한 요청자에게 금액을
     * 송금할 수 있습니다.
     * 3. 요청한 모든 인원이 정산을 완료한 경우에는 해당 요청은 정산완료
     * 처리됩니다.
     */
    @Test
    @Description("N명 정산 요청 상태 확인")
    void test1() {
        Request request = new Request("요청", 100000, 100000);
        assertEquals("요청", request.status());
        assertNotEquals("진행", request.status());
        assertNotEquals("완료", request.status());
    }

    @Test
    @Description("N명 정산 요청 금액 확인")
    void test2() {
        Request request = new Request("요청", 100000, 70000);
        Detail detail1 = new Detail("요청", 10000);
        Detail detail2 = new Detail("요청", 20000);
        Detail detail3 = new Detail("완료", 30000);
        Detail detail4 = new Detail("요청", 40000);
        assertEquals(request.amount(), detail1.amount() + detail2.amount() + detail3.amount() + detail4.amount());
        assertEquals(request.amount() - request.remain(), detail3.amount());
    }

}