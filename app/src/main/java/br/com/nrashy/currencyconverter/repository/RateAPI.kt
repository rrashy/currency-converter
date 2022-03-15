package br.com.nrashy.currencyconverter.repository


import android.content.Context
import android.util.Log
import br.com.nrashy.currencyconverter.model.IObserver
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RateAPI {
    fun getCurrency(
        context: Context,
        observer: IObserver
    ){
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.hgbrasil.com/finance?key=fbc54c2f"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                    response ->
                if (response.isNullOrEmpty()){
                    val jsonObj = JSONObject(response)
                    val results = jsonObj.getJSONObject("results")
                    val currencies = results.getJSONObject("currencies")
                    val dollar = currencies.getJSONObject("USD").getDouble("buy")
                    val euro = currencies.getJSONObject("EUR").getDouble("buy")

                    val map = mutableMapOf<String,Any>()
                    map["dollar"] = dollar
                    map["euro"] = euro
                    observer.updateUI(map)
                }
            },{
                    erro -> Log.e("APPDEBUG","!!Erro!!")
            }
        )
        queue.add(stringRequest)
    }
}