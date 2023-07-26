package br.com.account.account.domain.entity

import br.com.account.account.domain.aggregate.Account
import br.com.account.account.domain.datavalue.AccountStatus
import br.com.account.account.domain.datavalue.UserStatus
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "TB_USER")
data class User(
    @Id
    @Column(name = "ID_KEY")
    val key: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT")
    val account: Account,
    @Column(name = "DT_HR_CREATED")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    val dateCreated : LocalDateTime = LocalDateTime.now(),
    @Column(name = "DS_EMAIL")
    val email: String,
    @Column(name = "NM_USER")
    val name: String,
    @Column(name = "FL_TERM_ACCEPT")
    val termAccept: Boolean,

    @Column(name = "FL_EMAIL_VERIFIED")
    val emailVerified: Boolean,

    @Column(name = "EN_STATUS")
    @Enumerated(EnumType.STRING)
    var status: UserStatus
){
    fun activeAccount(){
        this.status = UserStatus.ACTIVED
        this.account.status = AccountStatus.ACTIVATED
    }
}

