package com.example.myfilms.screens.signIn

import androidx.lifecycle.ViewModel
import com.example.myfilms.data.firebase.FirebaseRepository
import com.example.myfilms.data.firebase.FirebaseRepositoryRealization
import com.example.myfilms.model.MoviesUser

class SigInFragmentViewModel: ViewModel() {

    private val firebaseRepository: FirebaseRepository = FirebaseRepositoryRealization()

    fun updateUserData (firebaseUser: MoviesUser, uid: String){
        firebaseRepository.updateUserData(firebaseUser, uid)
    }

}