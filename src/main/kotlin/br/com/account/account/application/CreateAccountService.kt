package br.com.account.account.application

import br.com.account.account.config.EventPublisher
import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.AccountStatus
import br.com.account.account.domain.datavalue.UserStatus
import br.com.account.account.domain.entity.User
import br.com.account.account.infrastructure.dto.entry.AccountNewEntry
import br.com.account.account.infrastructure.dto.view.AccountCreatedView
import br.com.account.account.infrastructure.event.AccountCreatedEvent
import br.com.account.account.infrastructure.event.UserCreatedEvent
import br.com.account.account.infrastructure.out.database.AccountRepository
import br.com.account.account.infrastructure.out.database.UserRepository
import br.com.account.account.type.exception.BusinessException
import br.com.account.account.type.mapper.AccountCreatedViewMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CreateAccountService(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository,
    private val eventPublisher: EventPublisher
) {

    @Transactional(rollbackFor = [Throwable::class])
    fun applyTo(accountNewDTO: AccountNewEntry): AccountCreatedView {

        val accountFound = accountRepository.findAccountByApplicationAndEmail(accountNewDTO.application, accountNewDTO.email)

        accountFound.ifPresent{
            throw BusinessException("Account already created")
        }
        val keyUser = UUID.randomUUID();
        val accountNew = Account(
            application=accountNewDTO.application,
            userOwnerKey=keyUser,
            email = accountNewDTO.email,
            termAccept = accountNewDTO.termAccept,
            status = AccountStatus.NOT_VALIDATED
        );

        val accountCreated = accountRepository.save(accountNew);
        val userNew = accountCreated.let{ account ->
            User(
                key = keyUser,
                status = UserStatus.BLOCKED,
                email = account.email,
                emailVerified = false,
                account = account,
                name = accountNewDTO.name,
                termAccept = account.termAccept
            )
        }




        val userCreated = userRepository.save(userNew)

        eventPublisher.with(AccountCreatedEvent(this,accountCreated))
        eventPublisher.with(UserCreatedEvent(this,userCreated))

        return AccountCreatedViewMapper().from(accountCreated)


    }
}