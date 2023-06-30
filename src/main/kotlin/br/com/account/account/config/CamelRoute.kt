package br.com.account.account.config

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.apache.camel.CamelContext
import org.apache.camel.ConsumerTemplate
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.jackson.JacksonDataFormat
import org.apache.camel.spring.boot.CamelContextConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component


@Configuration
class CamelRoute(
): RouteBuilder() {
    override fun configure() {

        val jacksonDataFormat = JacksonDataFormat(Any::class.java)
        jacksonDataFormat.addModule(JavaTimeModule())

        from("{{events.origin.account-created}}")
            .marshal(jacksonDataFormat)
            .log("Enviando mensagem para o RabbitMQ: \${body}")
            .to("{{events.destiny.rabbit-mq.account-created}}").end()
        from("{{events.origin.user-created}}")
            .marshal(jacksonDataFormat)
            .log("Enviando mensagem para o RabbitMQ: \${body}")
            .to("{{events.destiny.rabbit-mq.user-created}}").end()

    }

}