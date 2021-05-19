package com.example.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.dogs.R
import com.example.dogs.databinding.FragmentDetailBinding
import com.example.dogs.util.getProgressDrawable
import com.example.dogs.util.loadImage
import com.example.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private var dogUuid = 0
    private lateinit var viewModel: DetailViewModel

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //retrieving the data from fragment arguments
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid

        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)



        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog.let {
                dataBinding.dog = dog

            }

        })
    }
}