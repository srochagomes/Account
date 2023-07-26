package br.com.account.account.application

import br.com.account.account.config.EventPublisher
import br.com.account.account.domain.datavalue.UserStatus
import br.com.account.account.infrastructure.dto.entry.UserConfirmEntry
import br.com.account.account.infrastructure.out.database.AccountRepository
import br.com.account.account.infrastructure.out.database.UserRepository
import br.com.account.account.type.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConfirmAccountService(
    private val userRepository: UserRepository,

) {

    @Transactional(rollbackFor = [Throwable::class])
    fun applyTo(userConfirmDTO: UserConfirmEntry): UserConfirmEntry {

        val userFound = userRepository.findById(userConfirmDTO.key)
            .orElseThrow{
                throw BusinessException("Account already created")
            }

        if (UserStatus.ACTIVED.equals(userConfirmDTO.status)){
            userFound.activeAccount()
        }
        return userConfirmDTO
    }
}