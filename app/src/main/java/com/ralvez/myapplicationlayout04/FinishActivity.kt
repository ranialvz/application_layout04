package com.ralvez.myapplicationlayout04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ralvez.myapplicationlayout04.databinding.ActivityFinishBinding
import java.util.zip.Inflater

class FinishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName=intent.getStringExtra(myData.name)
        val score=intent.getStringExtra(myData.score)
        val totalQuestion=intent.getStringExtra("total size")

        binding.tvName.text="$userName"
        binding.tvScore.text="${score} / ${totalQuestion}"

        binding.btnMain.setOnClickListener{


            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)



        }
    }
}