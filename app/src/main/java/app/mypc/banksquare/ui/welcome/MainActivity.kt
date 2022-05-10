package app.mypc.banksquare.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.mypc.banksquare.R
import app.mypc.banksquare.databinding.ActivityMainBinding
import app.mypc.banksquare.domain.Correntista
import app.mypc.banksquare.ui.statement.Bankstatement_Activity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val accountHolderId = binding.txtEdtIdCorrentista.text.toString().toInt()
            val accontHolder = Correntista(accountHolderId)

            val intent = Intent(this, Bankstatement_Activity::class.java).apply{
                putExtra(Bankstatement_Activity.EXTRA_ACCOUNT_HOLDER, accontHolder)
            }
            startActivity(intent)
        }

    }
}