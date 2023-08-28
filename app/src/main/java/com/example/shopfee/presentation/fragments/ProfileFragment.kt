package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentProfileBinding
import com.example.utils.Consts.AUTH
import com.example.utils.Consts.FIRESTORE


class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPhone()
        logout()
    }
    private fun setPhoneNumber(phone: String){

        binding.tvphone.setText(phone)

    }
    private fun getPhone(){
        FIRESTORE.collection("users").document(AUTH.currentUser!!.uid).get().addOnSuccessListener {
            it.data?.let { val phone = it.get("phone").toString()
            setPhoneNumber(phone)}
        }
    }

    private fun logout(){
        binding.btLogout.setOnClickListener {
            AUTH.signOut()
            activity?.finish()
        }
    }
}