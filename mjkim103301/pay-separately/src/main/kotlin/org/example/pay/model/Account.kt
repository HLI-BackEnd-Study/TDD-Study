package org.example.pay.model

import java.math.BigDecimal

class Account(
    val id: Long = 0,
    val userId: Long = 0,
    val balance: BigDecimal? = null
)
