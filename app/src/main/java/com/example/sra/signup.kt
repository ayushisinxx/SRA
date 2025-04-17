package com.example.sra.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sra.R
import com.example.sra.databinding.FragmentSignupBinding
import com.example.sra.databinding.SignupBinding
import com.example.sra.login.login


import com.google.firebase.auth.FirebaseAuth

class signup : Fragment() {

    private var _binding: SignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignupBinding.inflate(inflater, container, false)
//        _binding = FragmentSignupBinding.infl
        auth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            val email = binding.btnEmail.text.toString().trim()
            val password = binding.btnPsw.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                createUser(email, password)
            }
        }

        return binding.root
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Signup successful!", Toast.LENGTH_SHORT).show()

                    // Navigate back to login fragment
                    val loginFragment = login()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, login())
                        .addToBackStack(null)
                        .commit()

                } else {
                    Log.e("Signup", "Error: ${task.exception?.message}")
                    Toast.makeText(requireContext(), "Signup failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
