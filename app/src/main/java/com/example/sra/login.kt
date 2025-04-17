package com.example.sra.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sra.R
import com.example.sra.databinding.FragmentLoginBinding
import com.example.sra.signup.signup
import com.example.sra.userdashboard
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get()=_binding!!
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using ViewBinding
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Get an instance of FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Set up click listener
        binding.btnLogin.setOnClickListener {
            checkUser()
        }
        // Sign Up Button Click
        binding.btnSignup.setOnClickListener {
            // Navigate to signup fragment
            val signupFragment = signup()  // Make sure your signup fragment class is named correctly
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, signupFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
    private fun checkUser() {

        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@login.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }


    // This function will update the UI as parameter passed form the checkUser

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(requireActivity(), "Login Successful", Toast.LENGTH_SHORT).show()

            // Navigate to user_dashboard fragment
            val dashboardFragment = userdashboard() // your target fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, dashboardFragment)
                .addToBackStack(null)
                .commit()
        } else {
            Toast.makeText(requireActivity(), "Please enter valid credentials", Toast.LENGTH_SHORT).show()
        }
    }


    // this will set the value of current user
    override fun onStart() {

        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }


    private fun reload(){
    }

}
