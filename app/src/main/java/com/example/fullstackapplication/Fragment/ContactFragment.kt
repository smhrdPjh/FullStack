package com.example.fullstackapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R



class ContactFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val view = inflater.inflate(R.layout.fragment_contact, container,false)


        val rvChat = view.findViewById<RecyclerView>(R.id.rvChat)



        return view
    }


}