package br.com.account.account.domain.aggregate


import br.com.account.account.domain.datavalue.AccountStatus
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_ACCOUNT")
data class Account (
    @Id
    @Column(name = "ID_KEY")
    val key: UUID = UUID.randomUUID(),
    @Column(name = "ID_APPLICATION")
    val application:String,
    @Column(name = "ID_KEY_USER_OWNER")
    var userOwnerKey: UUID,
    @Column(name = "DS_EMAIL")
    val email: String,
    @Column(name = "FL_TERM_ACCEPT")
    val termAccept: Boolean,
    @Column(name = "DT_HR_CREATED")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    val dateCreated: LocalDateTime = LocalDateTime.now(),
    @Column(name = "EN_STATUS")
    @Enumerated(EnumType.STRING)
    var status: AccountStatus
)
