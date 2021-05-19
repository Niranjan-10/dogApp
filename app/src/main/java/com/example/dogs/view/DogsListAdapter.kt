package com.example.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.databinding.ItemDogBinding
import com.example.dogs.model.DogBreed
import com.example.dogs.util.getProgressDrawable
import com.example.dogs.util.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)


    fun updateDogList(newDogsList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_dog, parent, false)
        val view = DataBindingUtil.inflate<ItemDogBinding>(inflater,R.layout.item_dog,parent,false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
            holder.view.dog = dogList[position]

//        holder.view.name.text = dogList[position].dogBreed
//        holder.view.lifespan.text = dogList[position].lifeSpan
//        holder.view.setOnClickListener {
//            val action = ListFragmentDirections.actionDetailFragment()
//            action.dogUuid = dogList[position].uuid
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        holder.view.imageView.loadImage(
//            dogList[position].imageUrl,
//            getProgressDrawable(holder.view.imageView.context)
//        )

    }

    override fun getItemCount() = dogList.size
}