package br.com.account.account.infrastructure.dto.view

import java.util.UUID

class AccountCreatedView(
    val key: String,
    val application: String,
    val userKey:UUID
)
