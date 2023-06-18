package br.com.account.account.application

import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.AccountKey
import br.com.account.account.infrastructure.dto.AccountNewDTO
import br.com.account.account.infrastructure.out.database.repository.AccountRepository
import br.com.account.account.types.exceptions.BusinessException

class CreateAccountService(val accountRepository: AccountRepository) {

    fun applyTo(accountNewDTO: AccountNewDTO) {

        val key = AccountKey(accountNewDTO.application, accountNewDTO.username)
        val accountFount = accountRepository.findById(key);
        accountFount.ifPresent{
            throw BusinessException("Account already created")
        }

        val accountNew = Account(key);

        accountRepository.save(accountNew);



    }
}