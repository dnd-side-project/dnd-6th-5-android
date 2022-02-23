package com.fork.spoonfeed.domain.model

enum class MedianIncome(val value: String) {
    UNDER_30("30% 이하"), UNDER_40("40% 이하"), UNDER_45("45% 이하"),
    UNDER_50("50% 이하"), UNDER_72("72% 이하"), UNDER_100("100% 이하"),
    NOTHING("해당없음"), PRIVATE("미공개")
}
