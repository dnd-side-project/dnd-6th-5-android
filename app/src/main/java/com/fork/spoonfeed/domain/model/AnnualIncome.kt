package com.fork.spoonfeed.domain.model

enum class AnnualIncome(val value: String) {
    COUPLE_UNDER_2("부부합산 2천만원 이하"), COUPLE_UNDER_5("부부합산 5천만원 이하"),
    SINGLE_UNDER_3("외벌이 3천만원 이하"), SINGLE_UNDER_3_5("외벌이 3.5천만원 이하"),
    NOTHING("해당없음"), PRIVATE("미공개")
}
