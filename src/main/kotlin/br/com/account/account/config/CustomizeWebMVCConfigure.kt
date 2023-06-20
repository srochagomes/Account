package br.com.account.account.config

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor

@Component
class CustomizeWebMVCConfigure (val localeChangeInterceptor: LocaleChangeInterceptor): WebMvcConfigurer {

    @Override
    override fun addInterceptors(registry : InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor);
    }
}