package app.mypc.banksquare.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Correntista(
    val id: Int,

): Parcelable{
    //val nome: String,
    //val cpf : String,
    //val conta : Conta
}
