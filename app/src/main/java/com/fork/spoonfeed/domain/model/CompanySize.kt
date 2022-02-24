package com.fork.spoonfeed.domain.model

enum class CompanySize(val value: String) {
    SMALL("중소기업"), MID("중견기업"),
    SELF("자영업자"), FOUNDER("(예비)창업자"), NOTHING("해당없음")
}
