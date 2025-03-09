package com.news.liora.fragments.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.news.liora.R
import com.news.liora.data.User
import com.news.liora.databinding.FragmentRegisterBinding
import com.news.liora.utils.RegisterFieldState
import com.news.liora.utils.RegistrationValidation
import com.news.liora.utils.Resource
import com.news.liora.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val TAG = "RegisterFragment"

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!

    private lateinit var progressBar:ProgressBar

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_register, container, false)
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            signupButton.setOnClickListener {
                val password = mPassword.text.toString().trim()
                val confirmPassword = mConfirmPassword.text.toString().trim()
                val user:User
                if (password.equals(confirmPassword)){
                   user = User(
                       mName.text.toString().trim(),
                       mEmailId.text.toString().trim()
                    )
                    viewModel.accountWithEmailAndPassword(user,password)
                }else{
                    binding.mPassword.apply {
                        requestFocus()
                        error = ""
                    }
                    binding.mConfirmPassword.apply {
                        requestFocus()
                        error = "Password doesn't match"
                    }
                }

            }

            doLogin.setOnClickListener{
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect{
                when(it){
                    is Resource.Unspecified->{

                    }
                    is Resource.Loading ->{
                        binding.progressLoading.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        binding.progressLoading.visibility = View.GONE
                        binding.mName.text = null
                        binding.mEmailId.text = null
                        binding.mPassword.text = null
                        binding.mConfirmPassword.text = null
                        Toast.makeText(activity,"Registration completed",Toast.LENGTH_SHORT).show()
                        Log.d(TAG, it.data.toString())
                    }
                    is Resource.Error->{
                        Log.d(TAG,it.message.toString())
                        Toast.makeText(activity,"Somthing went wrong",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect{ validation->
                if (validation.email is RegistrationValidation.Faild){
                    withContext(Dispatchers.Main){
                        binding.mEmailId.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if (validation.password is RegistrationValidation.Faild){
                    withContext(Dispatchers.Main){
                        binding.mPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }

    }
}