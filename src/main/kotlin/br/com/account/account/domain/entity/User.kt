package br.com.account.account.domain.entity

import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "TB_USER")
class User(
    @Id
    @Column(name = "ID_KEY")
    private val key: UUID = UUID.randomUUID(),
    @ManyToOne
    private val account: Account,
    @Column(name = "DT_HR_CREATED")
    private val dateCreated : LocalDateTime = LocalDateTime.now(),
    @Column(name = "DS_EMAIL")
    private val email: String,
    private val emailVerified: Boolean,
    private val status: UserStatus
)