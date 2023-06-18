package br.com.account.account.infrastructure.dto

data class AccountNewDTO(
        val application: String,
        val username:String,
        val termAccept: Boolean) {

}
