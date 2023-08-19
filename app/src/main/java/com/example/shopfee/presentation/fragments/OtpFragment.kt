package com.example.shopfee.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.domain.model.User
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentOtpBinding
import com.example.shopfee.presentation.activities.HomeActivity
import com.example.utils.Consts.AUTH
import com.example.utils.Consts.FIRESTORE
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider


class OtpFragment : Fragment() {

    private lateinit var binding: FragmentOtpBinding

    lateinit var verificationId: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOtpBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle()
        verify()
    }

    private fun getBundle(){
        val args = arguments
        args?.let {bundle ->

            val phoneNum = bundle.getString("phonenum")
            verificationId = bundle.getString("verificationId").toString()
            binding.tvPhonenum.setText(phoneNum)

        }
    }



    private fun verify(){
        binding.btVerify.setOnClickListener {

            val otp = binding.edCode.text.toString()

            if (otp.isNotEmpty()){

                val credential = PhoneAuthProvider.getCredential(verificationId,otp)

                signInWithPhoneAuthCredential(credential)


            }else{


                message("please enter the code")


            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        AUTH.signInWithCredential(credential).addOnCompleteListener {loginWithPhone->



            if (loginWithPhone.isSuccessful){
                val user = User(binding.tvPhonenum.text.toString(), AUTH.currentUser!!.uid)
                FIRESTORE.collection("users").document(AUTH.currentUser!!.uid).set(user)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(context,HomeActivity::class.java)
                            startActivity(intent)
                        }else{

                            message(it.exception!!.message.toString())
                        }
                    }





            }else{

                message(loginWithPhone.exception!!.message.toString())


            }
        }
    }



    private fun message(message: String){

        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }


}