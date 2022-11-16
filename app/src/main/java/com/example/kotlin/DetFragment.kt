package com.example.kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetFragment : Fragment() {

    private var Image: ImageView? = null
    private var tvNamePerson: TextView? = null
    private var model: Model? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Image = view.findViewById(R.id.Image)
        tvNamePerson = view.findViewById(R.id.tv_name_person)
        data
    }

    private val data: Unit
        private get() {
            val argument = arguments
            if (argument != null) {
                model = argument.getSerializable("celebrities") as Model?
                Glide.with(Image!!.context).load(model!!.imageUrl).into(Image!!)
                tvNamePerson!!.text = model!!.name
            }
        }
}