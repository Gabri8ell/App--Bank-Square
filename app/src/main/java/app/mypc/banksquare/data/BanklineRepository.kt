package app.mypc.banksquare.data

import android.util.Log
import androidx.lifecycle.liveData
import app.mypc.banksquare.data.remote.BanklineApi
import app.mypc.banksquare.data.remote.State
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BanklineRepository {

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanklineApi::class.java)
    }


    fun findBankStatement(accountHolderId: Int) = liveData {
        emit(State.Wait)
        try{
            emit(State.Success(data = restApi.findBankStatment(accountHolderId)))
        }catch ( e: Exception){
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }

    fun findAccountHolder(accountHolderId: Int) = liveData {
        emit(State.Wait)
        try{
            emit(State.Success(data = restApi.findAccountHolder(accountHolderId)))
        }catch ( e: Exception){
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }
}