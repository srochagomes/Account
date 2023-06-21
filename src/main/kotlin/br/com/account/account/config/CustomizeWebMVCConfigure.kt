package br.com.account.account.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor

@Configuration
class CustomizeWebMVCConfigure (val localeChangeInterceptor: LocaleChangeInterceptor): WebMvcConfigurer {

    @Override
    override fun addInterceptors(registry : InterceptorRegistry) {

        registry.addInterceptor(localeChangeInterceptor);
    }

}