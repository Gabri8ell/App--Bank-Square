package app.mypc.banksquare.ui.statement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.mypc.banksquare.data.BanklineRepository.findAccountHolder
import app.mypc.banksquare.data.remote.State
import app.mypc.banksquare.databinding.ActivityBankstatementBinding
import app.mypc.banksquare.domain.Conta
import app.mypc.banksquare.domain.Correntista
import app.mypc.banksquare.ui.welcome.MainActivity
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

class Bankstatement_Activity : AppCompatActivity() {

    companion object{
        const val EXTRA_ACCOUNT_HOLDER = "app.mypc.bankline.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy { ActivityBankstatementBinding.inflate(layoutInflater) }

    private val accountHolder by lazy {
        intent.getIntExtra(EXTRA_ACCOUNT_HOLDER, 1) ?: throw IllegalArgumentException()}

    private val viewModelStatement by viewModels<BankStatementViewModel>()
    private val viewModelAccount by viewModels<BankAccountHolderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rcvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()

        binding.slrBankstatement.setOnRefreshListener { findBankStatement() }

    }

    private fun findAccountHolder() {
        viewModelAccount.findAccountHolder(accountHolder).observe(this){ state ->
            when(state){
                is State.Success -> {
                    if(state.data != null){
                        binding.txvNomeCorrentista.text = state.data.nome
                        binding.txtSaldoConta.text = NumberFormat
                            .getCurrencyInstance()
                            .format(state.data.conta.saldo.toString().toFloat())
                    }
                }
            }
        }
    }

    private fun findBankStatement() {
        viewModelStatement.findBankStatement(accountHolder).observe(this){ state ->
            when(state){
                is State.Success -> {
                    val adapter = state.data?.let { BankStatementAdapter(it, this) }
                    //Se id digitado coresponder a algum correntista
                    if (adapter?.itemCount?.equals(0) != true) {
                        findAccountHolder()
                        binding.rcvBankStatement.adapter = adapter
                        binding.slrBankstatement.isRefreshing = false
                    }else{
                        Toast.makeText(this, "Não existe Correntista com ID $accountHolder", Toast.LENGTH_SHORT).show()
                        binding.slrBankstatement.isRefreshing = false
                        onBackPressed()
                    }
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rcvBankStatement, it, Snackbar.LENGTH_SHORT).show() }
                    binding.slrBankstatement.isRefreshing = false
                }
                State.Wait -> binding.slrBankstatement.isRefreshing = true
            }
        }
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
    }
}