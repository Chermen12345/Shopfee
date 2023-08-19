package com.example.shopfee.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentLoginBinding
import com.example.shopfee.presentation.activities.HomeActivity
import com.example.utils.Consts.AUTH
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isAtLogin()


        login()





    }



    private fun isAtLogin(){
        val pref = activity?.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = pref?.edit()

        editor?.putBoolean("IsGoToLogin",true)?.commit()
    }





    //login

    private fun login(){
        binding.btLogin.setOnClickListener {
            val number = binding.edPhoneLogin.text.toString()
            if (number.isNotEmpty()){

                sendVerificationCode(number)
            }else{

                message("please, enter your phone number")
            }
        }
    }

    private fun sendVerificationCode(number: String) {
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber(number)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(verificationCallBack).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    val verificationCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {

            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)

        }

        override fun onVerificationFailed(p0: FirebaseException) {

            message(p0.message.toString())

        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, token)

            val verificationId = verificationId
            val bundle = Bundle()
            bundle.putString("verificationId",verificationId)
            bundle.putString("phonenum",binding.edPhoneLogin.text.toString())
            findNavController().navigate(R.id.action_loginFragment_to_otpFragment,bundle)

        }

    }

    private fun message(message: String){

        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }





}