package app.mypc.banksquare.ui.statement

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.mypc.banksquare.R
import app.mypc.banksquare.databinding.BankStatementItemBinding
import app.mypc.banksquare.domain.Movimentacao
import app.mypc.banksquare.domain.TipoMovimentacao
import java.text.NumberFormat

class BankStatementAdapter(private val dataSet: List<Movimentacao>, val context: Context) : RecyclerView.Adapter<BankStatementAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BankStatementItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(context: Context, item: Movimentacao) = with(binding){
            txvDescricao.text = item.descricao.uppercase()
            txvDataHora.text = item.dataHora.replace("-", "/")

            var typeIcon: Int
            var colorValor: String

            if (TipoMovimentacao.RECEITA == item.tipo){
                typeIcon = R.drawable.ic_money_in
                binding.txvValor.setTextColor(ContextCompat.getColor(context, R.color.receita))

            }else{
                typeIcon  = R.drawable.ic_money_out
                binding.txvValor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
            }

            txvValor.text = NumberFormat.getCurrencyInstance().format(item.valor.toString().toFloat())
            binding.imgView.setImageResource(typeIcon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankStatementAdapter.ViewHolder {
        val binding = BankStatementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)


    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(context, item)


    }


    override fun getItemCount() = dataSet.size

}