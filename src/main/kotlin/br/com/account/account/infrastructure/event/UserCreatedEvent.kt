package br.com.account.account.infrastructure.event

import br.com.account.account.domain.entity.User
import org.springframework.context.ApplicationEvent

data class UserCreatedEvent(private val source: Any, val payload: User ) : ApplicationEvent(source) {
}

