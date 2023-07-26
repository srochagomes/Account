package br.com.account.account.infrastructure.dto.entry

import br.com.account.account.domain.datavalue.UserStatus
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Model for a dealer's view of a User Confirm")
data class UserConfirmEntry (
        var confirmedAt:LocalDateTime?,
        var key:UUID,
        var applicationId:String,
        var userLogin: String,
        var accountId: UUID?,
        var createdAt:LocalDateTime?,
        var email:String?,
        var status:UserStatus,
        var userProviderId:UUID?
        ) {

        constructor() : this(
                null,
                UUID.randomUUID(),
                "Não Informada",
                "Não Informada",null,
                null,null,
                UserStatus.BLOCKED,null

        )
}