package br.com.account.account.config

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.stereotype.Component

@Component
class EventPublisher(
    private var publisher:ApplicationEventPublisher
): ApplicationEventPublisherAware {
    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        this.publisher = applicationEventPublisher
    }

    fun with(event: ApplicationEvent): Unit = this.publisher.publishEvent(event)
}