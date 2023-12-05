package com.bignerdranch.android.movieapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.movieapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding

    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val loginButton : Button = binding.loginButton
        val registerButton: Button = binding.registerButton
        
        //setup firebase connection
        val mAuth = FirebaseAuth.getInstance()


        loginButton.setOnClickListener{

            //grab text from edit text boxes
            val email: String = binding.EmailAddress.editableText.toString()
            val password: String = binding.Password.text.toString()
            Log.d("DebugMe", "Email: $email, Password: $password")

            //check if blank
            if(email.isBlank()) {
                Toast.makeText(this.context, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.isBlank()) {
                Toast.makeText(this.context, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // authenticate using firebase
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this.context, "Login Successful", Toast.LENGTH_SHORT).show()
                        val action = LoginFragmentDirections.actionLoginFragmentToMovieFragment()
                        findNavController().navigate(action)
                    }
                    else {
                        Toast.makeText(this.context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        registerButton.setOnClickListener{

            //grab text from edit text boxes
            val email: String = binding.EmailAddress.editableText.toString()
            val password: String = binding.Password.text.toString()
            Log.d("DebugMe", "Email: $email, Password: $password")

            //check if blank
            if(email.isBlank()) {
                Toast.makeText(this.context, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.isBlank()) {
                Toast.makeText(this.context, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // authenticate using firebase
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this.context, "Account created.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this.context, "Problem creating account.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        return binding.root
    }



}
