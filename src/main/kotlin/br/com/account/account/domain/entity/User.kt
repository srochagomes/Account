package br.com.account.account.domain.entity

import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.UserStatus
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "TB_USER")
class User(
    @Id
    @Column(name = "ID_KEY")
    private val key: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT")
    private val account: Account,
    @Column(name = "DT_HR_CREATED")
    private val dateCreated : LocalDateTime = LocalDateTime.now(),
    @Column(name = "DS_EMAIL")
    private val email: String,

    @Column(name = "FL_TERM_ACCEPT")
    val termAccept: Boolean,

    @Column(name = "FL_EMAIL_VERIFIED")
    private val emailVerified: Boolean,

    @Column(name = "EN_STATUS")
    @Enumerated(EnumType.STRING)
    private val status: UserStatus
)