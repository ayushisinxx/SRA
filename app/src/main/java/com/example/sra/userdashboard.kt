package com.example.sra

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.example.sra.login.login
import com.example.sra.R

class userdashboard : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userdashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoutButton = view.findViewById<Button>(R.id.logoutButton)

        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Logout")
            setMessage("Are you sure you want to log out?")
            setPositiveButton("Yes") { _, _ ->
                logoutUser()
            }
            setNegativeButton("Cancel", null)
            show()
        }
    }

    private fun logoutUser() {
        // Sign out from Firebase
        FirebaseAuth.getInstance().signOut()

        // Clear back stack and navigate to login fragment
        parentFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // Replace with login fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, login())
            .commit()
    }

}
