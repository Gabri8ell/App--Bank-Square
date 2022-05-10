package app.mypc.banksquare.ui.statement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.mypc.banksquare.R
import app.mypc.banksquare.databinding.BankStatementItemBinding
import app.mypc.banksquare.domain.Correntista
import app.mypc.banksquare.domain.Movimentacao
import app.mypc.banksquare.domain.TipoMovimentacao
import java.text.NumberFormat

class BankStatementAdapter (private val dataSet: List<Movimentacao>) : RecyclerView.Adapter<BankStatementAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BankStatementItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Movimentacao) = with(binding){
            txvDescricao.text = item.descricao
            txvValor.text = NumberFormat.getCurrencyInstance().format(item.valor.toString().toFloat())
            txvDataHora.text = item.dataHora.replace("-", "/")
            val typeIcon = if (TipoMovimentacao.RECEITA == item.tipo) R.drawable.ic_money_in else (R.drawable.ic_money_out)
            binding.imgView.setImageResource(typeIcon)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankStatementAdapter.ViewHolder {
        val binding = BankStatementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }


    override fun getItemCount() = dataSet.size

}