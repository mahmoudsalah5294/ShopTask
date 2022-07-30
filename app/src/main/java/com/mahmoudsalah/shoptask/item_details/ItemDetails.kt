package com.mahmoudsalah.shoptask.item_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudsalah.shoptask.databinding.FragmentItemDetailsBinding
import com.mahmoudsalah.shoptask.model.Result


class ItemDetails : Fragment() {

private lateinit var binding: FragmentItemDetailsBinding
private lateinit var item:ItemDetailsArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetailsBinding.inflate(layoutInflater)
        val bundle = arguments
    bundle?.let {
        item = ItemDetailsArgs.fromBundle(bundle)
    }
        item.item?.let {
            (activity as AppCompatActivity)?.supportActionBar?.title = item.item.name
            updateUI(item.item)
        }
        return binding.root
    }

    private fun updateUI(item: Result) {

        val name = item.name.split(",")[0]
        binding.titleTxt.text = name
        binding.dateTxt.text = item.created_at
        binding.itemPriceTxt.text = item.price

        loadImage(item.image_urls)
    }

    private fun loadImage(imageUrls: List<String>) {
        val viewPagerAdapter = ViewPagerAdapter(imageUrls,requireContext())
        binding.viewpager.adapter = viewPagerAdapter
    }


}