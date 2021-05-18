package com.example.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogBreed

class ListViewModel : ViewModel() {
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreed("1", "Corgi", "15 years", "breed Group", "brefFor", "aad", "")
        val dog2 = DogBreed("2", "Corgi", "15 years", "breed Group", "brefFor", "aad", "")
        val dog3 = DogBreed("3", "Corgi", "15 years", "breed Group", "brefFor", "aad", "")

        var dogList = arrayListOf<DogBreed>(dog1, dog2, dog3)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}