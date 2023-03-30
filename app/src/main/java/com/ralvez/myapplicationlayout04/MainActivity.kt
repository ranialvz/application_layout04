package com.ralvez.myapplicationlayout04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ralvez.myapplicationlayout04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {

            val name= binding.etName.text.toString()
            if(name.isEmpty())
            {
                val toast = Toast.makeText(this, "Please Input Name", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                var intent = Intent(this,QuizActivity::class.java)
                intent.putExtra("${myData.name}",name)
                startActivity(intent)
                finish()

            }
        }

    }
}