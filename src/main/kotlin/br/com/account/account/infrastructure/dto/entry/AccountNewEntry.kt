package br.com.account.account.infrastructure.dto.entry

data class AccountNewEntry(
        val application: String,
        val username:String,
        val email:String,
        val termAccept: Boolean) {

}
