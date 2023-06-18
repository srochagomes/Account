package br.com.account.account.infrastructure.out.database.repository

import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.AccountKey
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, AccountKey> {



}
