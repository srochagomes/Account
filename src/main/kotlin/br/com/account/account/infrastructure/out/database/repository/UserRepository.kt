package br.com.account.account.infrastructure.out.database.repository

import br.com.account.account.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {

}
