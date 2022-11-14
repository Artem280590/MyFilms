package com.example.myfilms.data.firebase

import com.example.myfilms.model.MoviesUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryRealization: FirebaseRepository {

    private var database: DatabaseReference = Firebase.database.reference

    override fun updateUserData(firebaseUser: MoviesUser, userId: String) {
        database.child("users").child(userId).setValue(firebaseUser)
    }

}