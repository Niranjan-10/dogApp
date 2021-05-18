package com.example.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogApiService
import com.example.dogs.model.DogBreed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val dogService = DogApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
         loading.value = true
        disposable.add(
            dogService.getDogs().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(dogList: List<DogBreed>) {
                        dogs.value = dogList
                        loading.value = false
                        dogsLoadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}