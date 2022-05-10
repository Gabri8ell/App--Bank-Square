package app.mypc.banksquare.ui.statement

import androidx.lifecycle.ViewModel
import app.mypc.banksquare.data.BanklineRepository

class BankAccountHolderViewModel: ViewModel() {

    fun findAccountHolder(accountHolderId: Int) =
        BanklineRepository.findAccountHolder(accountHolderId)
}