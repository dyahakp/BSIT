package com.dyah.bsit.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dyah.bsit.presentation.adapter.TransactionAdapter
import com.dyah.bsit.databinding.ActivityMainBinding
import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import com.dyah.bsit.presentation.adapter.BsitFragmentPagerAdapter
import com.dyah.bsit.presentation.adapter.ContactAdapter
import com.dyah.bsit.presentation.adapter.ProfileAdapter
import com.dyah.bsit.presentation.fragment.ContactFragment
import com.dyah.bsit.presentation.fragment.ProfileFragment
import com.dyah.bsit.presentation.fragment.TransactionFragment
import com.dyah.bsit.presentation.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager(listOf())
        setUpTabLayout()
        observeViewModel()
        //di komen karena, listnya kosong bbuat array
        mainViewModel.getTransaction()
        mainViewModel.getContact()
        mainViewModel.getProfile()
    }



    private fun setUpViewPager(bundle: List<Bundle?>) {
        val viewPager = binding.vpContainer
        viewPager.adapter = BsitFragmentPagerAdapter(this, bundle)
    }

    private fun setUpTabLayout() {
        val tabLayout = binding.tabsFragment
        val viewPager = binding.vpContainer
        //overriding
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Transaction"
                1 -> tab.text = "Contact"
                2 -> tab.text = "Profile"
            }
        }.attach()
    }
//    private fun setData( dataTransaction : List<TransactionResponse>, dataContact: List<ContactResponse>, dataProfile : ProfileResponse?){
//        val bundle = listOf(
//            dataContact.let {
//                ContactFragment.createInstance(it)},
//            dataProfile.let {
//                it?.let { it1 -> ProfileFragment.createInstance(it1) }
//            },
//            dataTransaction.let {
//                TransactionFragment.createInstance(it)}
//
//        )
//        setUpViewPager(bundle)
//    }

    private fun observeViewModel() {
        mainViewModel.transaction.observe(this) {
            setDataForTransaction(it)
            //   setDataForTransaction(it)
            //    Toast.makeText(applicationContext,it[0].name,Toast.LENGTH_LONG).show()
        }

//        mainViewModel.contact.observe(this) {
//            setDataForTransaction(it)
//
//        }
//        mainViewModel.profile.observe(this) {
//            setData( listOf(), listOf(), it)
//
//        }
        mainViewModel.errorMessage.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
        mainViewModel.showLoading.observe(this) { isShow ->
            if (isShow) showLoading() else hideLoading()

        }

    }

    private fun setDataForTransaction(data: List<TransactionResponse>) {
        val adapter = TransactionAdapter(data)
        // binding.rvListTransaction.adapter=adapter
    }
//    private fun setDataForContact(data : List<ContactResponse>) {
//        val adapter = ContactAdapter(data)
//        // binding.rvListTransaction.adapter=adapter
//    }
//
//    private fun setDataForProfile(data: ProfileResponse) {
//        val adapter = ProfileAdapter(data)
//        // binding.rvListTransaction.adapter=adapter
//    }

    private fun showLoading() {
        binding.cmpLoading.root.visibility = View.VISIBLE
        //binding.rvListTransaction.visibility = View.GONE

    }

    private fun hideLoading() {
        binding.cmpLoading.root.visibility = View.GONE
        // binding.rvListTransaction.visibility = View.VISIBLE

    }
}