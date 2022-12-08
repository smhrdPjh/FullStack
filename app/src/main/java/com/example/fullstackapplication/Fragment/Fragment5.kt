package com.example.fullstackapplication.Fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.ContactAdapter
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.io.path.fileVisitor

class Fragment5 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_5, container, false)

        // AdapterView 6단계
        // 1. Container 결정
        // 2. Template 작성

        val contactList =ArrayList<ContactVO>()
        val db = Firebase.database // json파일을 바꾸면 Firebase도 바뀐다
        val contact2 = db.getReference("contact2")
        val rvContact = view.findViewById<RecyclerView>(R.id.rvContact)




        //4. Adapter 결정

        //  requireContext() : Fragment의 페이지 정보 호출!
        val adapter = ContactAdapter(requireContext(), contactList)

        //5. Container에 Adapter 부착

        rvContact.adapter=adapter
        rvContact.layoutManager = LinearLayoutManager(requireContext())

        // 3. Item 결정
        //          인터페이스 override 필요!!
        contact2.addChildEventListener(object  : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

              //
                val contact = snapshot.getValue<ContactVO>() as ContactVO
                contactList.add(contact)
                // 추가가 완료된 이후
                // 어댑터 새로고침!!
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return view
    }

}