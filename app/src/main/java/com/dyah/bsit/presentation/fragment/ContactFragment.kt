package com.dyah.bsit.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dyah.bsit.databinding.FragmentContactBinding
import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.presentation.adapter.ContactAdapter
import com.dyah.bsit.presentation.fragment.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : Fragment() {

    // 1 bikin binding
    private var _binding : FragmentContactBinding?= null

    //2 ambil binding
    val binding get() = _binding!!
    private var contactAdapter = ContactAdapter()

    private val viewModel : ContactViewModel by viewModels()

    //3
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
        viewModel.getContact()

        binding.etSearch.addTextChangedListener( object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val editTextValue = binding.etSearch.text
                if(editTextValue.isEmpty()){
                    binding.ivCancel.visibility = View.INVISIBLE
                }else{
                    binding.ivCancel.visibility = View.VISIBLE
                }
                viewModel.filterSearchContact(p0.toString())

            }
        } )
        binding.ivCancel.setOnClickListener {
            binding.etSearch.text.clear()
        }

    }


    private fun observerViewModel(){
        viewModel.contact.observe(viewLifecycleOwner){ contactData ->
            if(contactData!= null){
                setData(contactData)
            }
        }
    }

    private fun setData(data : List<ContactResponse>){
        contactAdapter.setData(data.toMutableList())
        contactAdapter.onClickIconCall {
            callAFriend((it.noTelp))
        }
        binding.rvContact.adapter = contactAdapter
    }

    private fun callAFriend(phoneNumber:String?){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
       _binding= null
    }


//    companion object {
//        const val CONTACT_RESPONSE="contact_response"
//
//        fun createInstance(data: List<ContactResponse>) = Bundle().apply {
//            putParcelableArray(CONTACT_RESPONSE,data.toTypedArray())
//        }
//    }
}