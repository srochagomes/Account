package br.com.account.account.application

import br.com.account.account.config.EventPublisher
import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.AccountStatus
import br.com.account.account.domain.datavalue.UserStatus
import br.com.account.account.domain.entity.User
import br.com.account.account.infrastructure.dto.entry.AccountNewEntry
import br.com.account.account.infrastructure.out.database.AccountRepository
import br.com.account.account.infrastructure.out.database.UserRepository
import br.com.account.account.type.exception.BusinessException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.util.*


internal class CreateAccountServiceTest{
    val accountRepository: AccountRepository = mockk()
    val userRepository: UserRepository = mockk()
    val eventPublisher: EventPublisher = mockk()
    val createAccountService = CreateAccountService(accountRepository, userRepository, eventPublisher)

    @Test
    fun `deve lançar um erro quando o usuário já possui uma conta cadastrada com a mesma aplicação e username`() {
        val accountNewDTO = AccountNewEntry( "","","","", LocalDate.now(),"",true)
        val email = "antronio@gmail.com"
        every { accountRepository.findAccountByApplicationAndEmail(any(),any())} returns Optional.of(
            Account(
                application =  "1234",
                userOwnerKey = UUID.randomUUID(),
                email =  email,
                termAccept = true,
                status = AccountStatus.ACTIVATED))

        val actual = assertThrows<BusinessException> {
            createAccountService.applyTo(accountNewDTO)
        }
        verify(exactly = 1) { accountRepository.findAccountByApplicationAndEmail(any(),any()) }
        verify(exactly = 0) { accountRepository.save(any()) }
        verify(exactly = 0) { userRepository.save(any()) }

        assertThat(actual.message).isEqualTo("Account already created")
    }

    @Test
    fun `deve criar uma conta quando a aplicação e o usuário não está registrado`() {
        val accountNewDTO = AccountNewEntry( "","","","", LocalDate.now(),"",true)
        val email = "antronio@gmail.com"
        val accountCreated = Account(
            application =  "1234",
            userOwnerKey = UUID.randomUUID(),
            email =  email,
            termAccept = true,
            status = AccountStatus.ACTIVATED)

        every { accountRepository.findAccountByApplicationAndEmail(any(),any())} returns Optional.empty()
        every { accountRepository.save(any())} returns accountCreated
        every { userRepository.save(any())} returns User(name="",email="", account = accountCreated, emailVerified = false, status=UserStatus.BLOCKED, phone = "", termAccept = true, dateBirth = LocalDate.now())
        every { eventPublisher.with(any())} returns Unit


        val accountCreatedView = createAccountService.applyTo(accountNewDTO)

        verify(exactly = 1) { accountRepository.findAccountByApplicationAndEmail(any(),any())}
        verify(exactly = 1) { accountRepository.save(any()) }
        verify(exactly = 2) { eventPublisher.with(any()) }
        assertThat(accountCreatedView).isNotNull
        assertThat(accountCreatedView.application).isEqualTo("1234")

    }

}