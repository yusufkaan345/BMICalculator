package com.example.bmicalculator

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var weightValue=findViewById<EditText>(R.id.weightValue)
        var heightValue=findViewById<EditText>(R.id.heightValue)
        val btnCalculate=findViewById<Button>(R.id.btnCalculate)
        var card3=findViewById<CardView>(R.id.cardView3)

                  btnCalculate.setOnClickListener {

                      var weight=weightValue.text.toString()
                      var height=heightValue.text.toString()
                       if(validateInput(weight,height)){
                           card3.visibility=View.VISIBLE
                           val bmi=weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                           display(bmi)
                           weightValue.text.clear()
                           heightValue.text.clear()
                       }
                  }

    }

     private fun validateInput(weight:String?,height:String?):Boolean{
         return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
                false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                false
            }
            else->{
                true
            }
        }
     }

     private fun display(bmi:Float){
        var tvIndex=findViewById<TextView>(R.id.tvIndex)
        var tvResult=findViewById<TextView>(R.id.tvResult)
        var tvInfo=findViewById<TextView>(R.id.tvInfo)
        var bmi2format=String.format("%.2f", bmi)
        tvIndex.text=bmi2format
        tvInfo.text="Normal range is 18,5-24,9"

        var resultText=""
        var color=0

        when{
            bmi<18.5 ->{
                resultText="Under Weight"
                color=R.color.under
            }
            bmi in 18.50..24.99->{
                resultText="Healthy Weight"
                color=R.color.normal
            }
            bmi in 25.0..29.99->{
                resultText="Over Weight"
                color=R.color.over
            }
            bmi>30->{
                resultText="Obese"
                color=R.color.obese
            }

        }
        tvResult.text=resultText
        tvResult.setTextColor(ContextCompat.getColor(this,color))
    }


}

