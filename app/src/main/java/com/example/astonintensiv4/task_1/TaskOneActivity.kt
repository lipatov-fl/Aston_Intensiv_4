package com.example.astonintensiv4.task_1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.ActivityTaskOneBinding

class TaskOneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.first_activity_fragment, AFragment())
                .commit()
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, TaskOneActivity::class.java)
    }
}