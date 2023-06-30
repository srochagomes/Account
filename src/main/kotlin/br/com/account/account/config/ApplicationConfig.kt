package br.com.account.account.config


import org.apache.camel.CamelContext
import org.apache.camel.ConsumerTemplate
import org.apache.camel.ProducerTemplate
import org.apache.camel.spring.boot.CamelContextConfiguration
import org.apache.camel.spring.boot.SpringBootCamelContext
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@Configuration
class ApplicationConfig {

    @Bean
    fun localeResolver() = SessionLocaleResolver().apply {
        setDefaultLocale(Locale("pt", "BR"))
    }

    @Bean("messageSource")
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasenames("classpath:language/messages")
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setDefaultLocale(Locale("pt", "BR"))
        return messageSource
    }


    @Bean
    fun localeChangeInterceptor() : LocaleChangeInterceptor {
        val lci : LocaleChangeInterceptor = LocaleChangeInterceptor()
        lci.paramName = "lang";
        return lci;
    }



}