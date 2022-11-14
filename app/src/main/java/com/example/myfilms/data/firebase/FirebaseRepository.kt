package com.example.myfilms.data.firebase

import com.example.myfilms.model.MoviesUser

interface FirebaseRepository {

    fun updateUserData(firebaseUser: MoviesUser, userId: String)

}