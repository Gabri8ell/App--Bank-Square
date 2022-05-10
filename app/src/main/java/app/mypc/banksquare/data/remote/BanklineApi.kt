package app.mypc.banksquare.data.remote

import app.mypc.banksquare.domain.Correntista
import app.mypc.banksquare.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BanklineApi {

    @GET("/movimentacoes/{id}")
    suspend fun findBankStatment(@Path("id") accountHolder: Int): List<Movimentacao>

    @GET("/correntistas/{id}")
    suspend fun findAccountHolder(@Path("id") accountHolder: Int): List<Correntista>
}