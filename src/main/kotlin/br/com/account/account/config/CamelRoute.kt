package br.com.account.account.config

import br.com.account.account.application.ConfirmAccountService
import br.com.account.account.infrastructure.dto.entry.UserConfirmEntry
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.jackson.JacksonDataFormat
import org.springframework.context.annotation.Configuration


@Configuration
class CamelRoute( val confirmAccountService: ConfirmAccountService
): RouteBuilder() {
    override fun configure() {

        val jacksonDataFormat = JacksonDataFormat(Any::class.java)
        jacksonDataFormat.addModule(JavaTimeModule())

        val userConfirmDataFormat = JacksonDataFormat(UserConfirmEntry::class.java)
        userConfirmDataFormat.addModule(JavaTimeModule())

        from("{{events.origin.account-created}}")
            .marshal(jacksonDataFormat)
            .log("Enviando mensagem para o RabbitMQ: \${body}")
            .to("{{events.destiny.rabbit-mq.account-created}}").end()
        from("{{events.origin.user-created}}")
            .marshal(jacksonDataFormat)
            .log("Enviando mensagem para o RabbitMQ: \${body}")
            .to("{{events.destiny.rabbit-mq.user-created}}").end()

        from("{{events.origin.user-access-confirm-email}}")
            .log("Recebendo mensagem Confirmação de email: \${body}")
            .unmarshal(userConfirmDataFormat)
            .bean(confirmAccountService,"applyTo")
            .end()

    }

}