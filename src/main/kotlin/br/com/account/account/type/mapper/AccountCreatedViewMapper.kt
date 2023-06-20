package br.com.account.account.type.mapper

import br.com.account.account.domain.aggregate.Account
import br.com.account.account.infrastructure.dto.view.AccountCreatedView

class AccountCreatedViewMapper: Mapper<Account, AccountCreatedView> {
    override fun from(entry: Account): AccountCreatedView {
        return entry.let{ account ->
            AccountCreatedView(account.key.toString(), account.application, account.userNameOwner);
        }
    }

}