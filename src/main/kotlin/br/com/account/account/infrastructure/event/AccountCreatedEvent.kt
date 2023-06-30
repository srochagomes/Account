package br.com.account.account.infrastructure.event

import br.com.account.account.domain.aggregate.Account
import org.springframework.context.ApplicationEvent

data class AccountCreatedEvent(private val source: Any, val payload: Account ) : ApplicationEvent(source) {
}

