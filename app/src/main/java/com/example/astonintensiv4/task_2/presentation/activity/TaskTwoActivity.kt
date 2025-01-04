package com.example.astonintensiv4.task_2.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.ActivityTaskTwoBinding
import com.example.astonintensiv4.task_2.presentation.fragments.ListOfUsersFragment

class TaskTwoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListOfUsersFragment())
            .commit()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, TaskTwoActivity::class.java)
    }
}