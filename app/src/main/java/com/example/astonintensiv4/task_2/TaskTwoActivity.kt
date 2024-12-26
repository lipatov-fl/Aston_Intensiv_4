package com.example.astonintensiv4.task_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.ActivityTaskOneBinding
import com.example.astonintensiv4.databinding.ActivityTaskTwoBinding
import com.example.astonintensiv4.task_1.TaskOneActivity

class TaskTwoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, TaskTwoActivity::class.java)
    }
}