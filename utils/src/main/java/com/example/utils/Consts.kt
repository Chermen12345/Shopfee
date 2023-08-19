package com.example.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object Consts {
    val AUTH = FirebaseAuth.getInstance()
    val FIRESTORE = FirebaseFirestore.getInstance()
}