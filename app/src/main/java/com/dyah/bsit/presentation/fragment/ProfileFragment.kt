package com.dyah.bsit.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat.IntentReader
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dyah.bsit.R
import com.dyah.bsit.databinding.FragmentProfileBinding
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.presentation.fragment.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    //bikin binding
    //binding tidak akan null
    //set
    private var _binding : FragmentProfileBinding? = null

    //manggil value
    //get
    private val binding get() = _binding


    private val viewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.getProfile()

    }

    private fun observeViewModel(){
        //properti object getter dari class viewmodel
        viewModel.profile.observe(viewLifecycleOwner){
            initViewProfile(it)
        }
    }


    private fun initViewProfile(data : ProfileResponse){
        binding?.ivProfile?.let{
            Glide
                .with(context?: return)
                .load(data.imageUrl)
                .centerCrop()
                .into(it)
        }
        binding?.tvName?.text = data.name
        binding?.tvJoinDate?.text = data.joinedDate
        binding?.tvStatus?.text = (if(data.status ==1)
            getString(R.string.available_title) else getString(R.string.not_available_title))
        binding?.tvNoTelp?.text = data.noTelp

        binding?.cardLocation?.setOnClickListener {
            seeLocation(data.lat.toString(),data.lng.toString())
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun seeLocation(lat : String, lng : String)  {
        val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,%20$lng")
        val intent = Intent(Intent.ACTION_VIEW,uri)
        context?.startActivity(intent)
    }

    companion object {
        const val PROFILE_RESPONSE="contact_response"

        fun createInstance(data: ProfileResponse) = Bundle().apply {
            putParcelable(PROFILE_RESPONSE,data)
        }
    }
}

//fragment udah semua bikin instance, dilayout dibikin recycler