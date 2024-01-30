package br.com.account.account.infrastructure.dto.entry

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Schema(description = "Model for a dealer's view of a NewAccount.")
data class AccountNewEntry(

        @field:NotEmpty(message = "{field.nao_deve_ser_nulo}")
        var application: String = "",

        @field:Size(min = 5, max = 100, message = "{field.nao_deve_ser_nulo}")
        var name:String = "",

        @field:Size(min = 5, max = 100 , message = "{field.nao_deve_ser_nulo}")
        var email:String = "",

        @field:Size(min = 0, max = 14 , message = "{field.nao_deve_ser_nulo}")
        var phone:String = "",

        @field:NotNull(message = "{field.nao_deve_ser_nulo}")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        var dateBirth:LocalDate?,

        @field:NotNull(message = "{field.nao_deve_ser_nulo}")
        var password:String = "",

        @field:NotNull(message = "{field.nao_deve_ser_nulo}")
        var termAccept: Boolean) {

}
