package app.mypc.banksquare.ui.statement

import androidx.lifecycle.ViewModel
import app.mypc.banksquare.data.BanklineRepository

class BankStatementViewModel: ViewModel() {

    fun findBankStatement(accountHolderId: Int) =
        BanklineRepository.findBankStatement(accountHolderId)
}