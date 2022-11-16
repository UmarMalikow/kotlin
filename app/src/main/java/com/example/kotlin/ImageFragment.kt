package com.example.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ImageFragment : Fragment() {

    var etImage: EditText? = null
    var etName: EditText? = null
    var btnAdd: FloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etImage = view.findViewById(R.id.et_image)
        etName = view.findViewById(R.id.et_name)
        btnAdd = view.findViewById(R.id.btn_add_third_fragment)
        clickAdd()
    }

    private fun clickAdd() {
        btnAdd!!.setOnClickListener {
            val url = etImage!!.text.toString().trim { it <= ' ' }
            val name = etName!!.text.toString()
            val bundle = Bundle()
            if (url.isEmpty() && name.isEmpty()) {
                etImage!!.error = "Add URL"
                etName!!.error = "Add name"
            } else if (url.isEmpty()) {
                etImage!!.error = "Add URL"
            } else if (name.isEmpty()) {
                etName!!.error = "Add name"
            } else {
                val model = Model(url, name)
                bundle.putSerializable("component", model)
                parentFragmentManager.setFragmentResult("backStack", bundle)
                parentFragmentManager.popBackStack()

            }
        }
    }
}