package com.example.kotlin

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RecyclerFragment : Fragment(), OnItemClickListener {

    private val repository = Repository()
    private val adapter = Adapter(this)
    private var rvListOfCelebrities: RecyclerView? = null
    private var btn: FloatingActionButton? = null
    private var model: Model? = null
    private var list: ArrayList<Model>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvListOfCelebrities = view.findViewById(R.id.rv_list_of_name)
        btn = view.findViewById(R.id.btn)
        list = repository.getListOfCelebrities()
        adapter.setData(list)
        initialize()
        setUpOnClickListener()
        data()
    }

    private fun initialize() {
        rvListOfCelebrities!!.adapter = adapter
    }

    private fun setUpOnClickListener() {
        btn?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ImageFragment())
                .addToBackStack("RecyclerFragment")
                .commit()
        }
    }

    override fun onClick(model: Model?) {
        val bundle = Bundle()
        bundle.putSerializable("celebrities", model)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetFragment::class.java, bundle)
            .addToBackStack("RecyclerFragment")
            .commit()
    }

    private fun data() {
        parentFragmentManager.setFragmentResultListener("backStack", viewLifecycleOwner)
        { requestKey, result ->
            if (requestKey == "backStack") {
                model = result.getSerializable("component") as Model?
                model?.let { list?.add(it) }
                adapter.setData(list)

            }
        }
    }
}