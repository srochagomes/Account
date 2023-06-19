package br.com.account.account.application

import br.com.account.account.domain.aggregate.Account

import br.com.account.account.domain.datavalue.AccountStatus
import br.com.account.account.domain.datavalue.UserStatus
import br.com.account.account.domain.entity.User
import br.com.account.account.infrastructure.dto.entry.AccountNewEntry
import br.com.account.account.infrastructure.dto.view.AccountCreatedView
import br.com.account.account.infrastructure.out.database.repository.AccountRepository
import br.com.account.account.infrastructure.out.database.repository.UserRepository
import br.com.account.account.type.exception.BusinessException
import br.com.account.account.type.mapper.AccountCreatedViewMapper

class CreateAccountService(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository
) {

    fun applyTo(accountNewDTO: AccountNewEntry): AccountCreatedView {

        val accountFount = accountRepository.findAccountByApplicationAndUserNameOwner(accountNewDTO.application, accountNewDTO.username)

        accountFount.ifPresent{
            throw BusinessException("Account already created")
        }

        val accountNew = Account(
            application=accountNewDTO.application,
            userNameOwner=accountNewDTO.username,
            email = accountNewDTO.email,
            termAccept = accountNewDTO.termAccept,
            status = AccountStatus.NOT_VALIDATED
        );

        val accountCreated = accountRepository.save(accountNew);
        val userNew = User(
            status = UserStatus.BLOCKED,
            email = accountCreated.email,
            emailVerified = false,
            account = accountCreated
        )

        val userCreated = userRepository.save(userNew)

        return AccountCreatedViewMapper().from(accountCreated)


    }
}