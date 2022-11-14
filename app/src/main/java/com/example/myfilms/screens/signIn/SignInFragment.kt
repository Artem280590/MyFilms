package com.example.myfilms.screens.signIn

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myfilms.MAIN
import com.example.myfilms.R
import com.example.myfilms.model.MoviesUser
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment(){

    private lateinit var authUser: FirebaseAuth
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authUser = Firebase.auth
        openRegistrationScreen()

    }

    private fun openRegistrationScreen(){

        MAIN.navController.navigate(R.id.action_signInFragment_to_rootFragment)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build())

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers).setTheme(R.style.Theme_MyFilms)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {

        val viewModel = ViewModelProvider(this)[SigInFragmentViewModel::class.java]

        when (result.resultCode) {
            RESULT_OK -> {
                authUser.currentUser.let {
                    val email = it?.email.toString()
                    val uid = it?.uid
                    val firebaseUser = MoviesUser(email, uid!!)
                    viewModel.updateUserData(firebaseUser, uid)
                    MAIN.navController.navigate(R.id.action_signInFragment_to_rootFragment)
                }
            }
            RESULT_CANCELED -> {
                Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}