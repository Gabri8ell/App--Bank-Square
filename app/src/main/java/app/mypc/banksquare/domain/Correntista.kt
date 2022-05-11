package app.mypc.banksquare.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Correntista(
    val id: Int,
    val nome: String,
    val cpf : String,
    val conta : Conta
)
