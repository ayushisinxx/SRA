package com.example.sra

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sra.login.LoginFragment  // Import your LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getStartedButton: Button = findViewById(R.id.btn_get_started)
        getStartedButton.setOnClickListener {
            replaceFragment(LoginFragment()) // Replace with the Login Fragment
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment) // Make sure this container exists
        fragmentTransaction.addToBackStack(null) // Allows back navigation
        fragmentTransaction.commit()
    }
}
