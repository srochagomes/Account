package br.com.account.account.infrastructure.entry.http.controller.post

import br.com.account.account.application.CreateAccountService
import br.com.account.account.infrastructure.dto.entry.AccountNewEntry
import br.com.account.account.infrastructure.dto.view.AccountCreatedView
import br.com.account.account.infrastructure.entry.http.controller.RootController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@SecurityRequirement(name = "bearerAuth")
class CreateAccountController(private val createAccountService: CreateAccountService)
    : RootController() {

    @Operation(description = "Create a course")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(
        @RequestBody @Valid accountNewEntry: AccountNewEntry,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AccountCreatedView> {
        val accountCreated = createAccountService.applyTo(accountNewEntry)
        val uri = uriBuilder.path("/accounts/${accountCreated.key}").build().toUri()
        return ResponseEntity.created(uri).body(accountCreated)
    }
}