package br.com.account.account.infrastructure.out.database

import br.com.account.account.domain.aggregate.Account

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface AccountRepository : JpaRepository<Account, UUID> {

    fun findAccountByApplicationAndUserNameOwner(application:String, userOwner:String):Optional<Account>


}
