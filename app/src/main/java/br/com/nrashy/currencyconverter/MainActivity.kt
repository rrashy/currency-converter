package br.com.nrashy.currencyconverter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import br.com.nrashy.currencyconverter.databinding.ActivityMainBinding
import br.com.nrashy.currencyconverter.model.IObserver
import br.com.nrashy.currencyconverter.model.Price
import br.com.nrashy.currencyconverter.repository.RateAPI

class MainActivity : AppCompatActivity(), IObserver {
    private lateinit var binding: ActivityMainBinding
    private lateinit var alert:AlertDialog
    private val euroPrice = Price()
    private val dollarPrice = Price()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.euroPrice = euroPrice
        binding.dollarPrice = dollarPrice

        binding.btnConvert.setOnClickListener{
            alert = AlertDialog.Builder(this).create()
            alert.setTitle("Aguarde...")
            alert.setMessage("Estamos processando a conversão!!!")
            alert.show()

            val rateAPI = RateAPI()
            rateAPI.getCurrency(applicationContext, this)
        }
    }

    override fun updateUI(data: MutableMap<String, Any>) {
        if(data.isNotEmpty()){
            val dollarValue = data["dollar"] as Double
            val euroValue = data["euro"] as Double

            val realValue = binding.txtReal.text.toString().toDouble()

            dollarPrice.setValue(realValue / dollarValue)
            euroPrice.setValue(realValue / euroValue)

            alert.dismiss()

        }
    }
}