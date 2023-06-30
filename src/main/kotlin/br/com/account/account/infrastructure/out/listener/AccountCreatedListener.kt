package br.com.account.account.infrastructure.out.listener

import br.com.account.account.infrastructure.event.AccountCreatedEvent
import org.apache.camel.Produce
import org.apache.camel.ProducerTemplate
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class AccountCreatedListener(
    @Produce("{{events.origin.account-created}}")
    val template: ProducerTemplate
){

    @TransactionalEventListener
    fun onApplicationEvent(event: AccountCreatedEvent) {

        template.sendBodyAndHeaders(template.defaultEndpoint,event.payload, emptyMap())

    }
}