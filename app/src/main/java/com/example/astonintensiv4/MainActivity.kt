package com.example.astonintensiv4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.astonintensiv4.databinding.ActivityMainBinding
import com.example.astonintensiv4.task_1.TaskOneActivity
import com.example.astonintensiv4.task_2.TaskTwoActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            task1Button.setOnClickListener {
                startActivity(TaskOneActivity.newIntent(this@MainActivity))
            }

            task2Button.setOnClickListener {
                startActivity(TaskTwoActivity.newIntent(this@MainActivity))
            }
        }
    }
}