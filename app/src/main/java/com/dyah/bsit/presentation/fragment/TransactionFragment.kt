package com.dyah.bsit.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dyah.bsit.databinding.FragmentTransactionBinding
import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.TransactionResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {
    //1 bikin binding
    private var _binding: FragmentTransactionBinding? = null

    //2 ambil nilai binding
    private val binding get() = _binding

    //3
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    //5
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    //4
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TRANSACTION_RESPONSE = "transaction_response"

        fun createInstance(data: List<TransactionResponse>) = Bundle().apply {
            putParcelableArray(TRANSACTION_RESPONSE, data.toTypedArray())
        }
    }

}