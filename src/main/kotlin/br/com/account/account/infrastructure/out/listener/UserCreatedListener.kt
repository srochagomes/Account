package br.com.account.account.infrastructure.out.listener

import br.com.account.account.infrastructure.event.UserCreatedEvent
import org.apache.camel.Produce
import org.apache.camel.ProducerTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserCreatedListener(
    @Produce("{{events.origin.user-created}}")
    val template: ProducerTemplate
){

    @TransactionalEventListener
    fun onApplicationEvent(event: UserCreatedEvent) {

        template.sendBodyAndHeaders(template.defaultEndpoint,event.payload, emptyMap())

    }
}