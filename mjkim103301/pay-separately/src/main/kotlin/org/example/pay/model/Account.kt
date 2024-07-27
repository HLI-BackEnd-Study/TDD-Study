package org.example.pay.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne(fetch = FetchType.EAGER)
    val user: User,
    val balance: BigDecimal
)
