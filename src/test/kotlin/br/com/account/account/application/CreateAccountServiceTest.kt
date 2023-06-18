package br.com.account.account.application

import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.AccountKey

import br.com.account.account.infrastructure.dto.AccountNewDTO
import br.com.account.account.infrastructure.out.database.repository.AccountRepository
import br.com.account.account.types.exceptions.BusinessException
import io.mockk.every

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import java.util.*


internal class CreateAccountServiceTest{
    val accountRepository:AccountRepository = mockk()
    val createAccountService = CreateAccountService(accountRepository)

    @Test
    fun `deve lançar um erro quando o usuário já possui uma conta cadastrada com a mesma aplicação e username`() {
        val accountNewDTO = AccountNewDTO( "","",true)
        every { accountRepository.findById(any())} returns Optional.of(Account(AccountKey("","")))

        val actual = assertThrows<BusinessException> {
            createAccountService.applyTo(accountNewDTO)
        }
        verify(exactly = 1) { accountRepository.findById(any()) }
        verify(exactly = 0) { accountRepository.save(any()) }

        assertThat(actual.message).isEqualTo("Account already created")
    }

    @Test
    fun `deve criar uma conta quando a aplicação e o usuário não está registrado`() {
        val accountNewDTO = AccountNewDTO( "","",true)
        every { accountRepository.findById(any())} returns Optional.empty()
        every { accountRepository.save(any())} returns Account(AccountKey("",""))


        createAccountService.applyTo(accountNewDTO)

        verify(exactly = 1) { accountRepository.findById(any()) }
        verify(exactly = 1) { accountRepository.save(any()) }
    }

}