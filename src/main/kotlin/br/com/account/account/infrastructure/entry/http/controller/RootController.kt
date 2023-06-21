package br.com.account.account.infrastructure.entry.http.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Account")
@RequestMapping("/accounts")
class RootController() {
}