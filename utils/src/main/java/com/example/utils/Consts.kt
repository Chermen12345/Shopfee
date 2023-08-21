package com.example.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object Consts {
    val AUTH = FirebaseAuth.getInstance()
    val FIRESTORE = FirebaseFirestore.getInstance()

    const val BASE_URL = "http://10.0.2.2:8080"
}