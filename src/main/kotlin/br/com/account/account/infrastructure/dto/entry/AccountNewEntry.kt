package br.com.account.account.infrastructure.dto.entry

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class AccountNewEntry(

        @field:NotEmpty(message = "{field.nao_deve_ser_nulo}")
        var application: String = "",

        @field:Size(min = 5, max = 100, message = "{field.nao_deve_ser_nulo}")
        var username:String = "",

        @field:Size(min = 5, max = 100 , message = "{field.nao_deve_ser_nulo}")
        var email:String = "",
        @field:NotNull(message = "{field.nao_deve_ser_nulo}")
        var termAccept: Boolean) {

}
