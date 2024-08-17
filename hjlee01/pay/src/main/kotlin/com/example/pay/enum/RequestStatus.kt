package com.example.pay.enum

enum class RequestStatus(
        val code: String
) {
    요청("10"),
    진행("50"),
    완료("80")
}