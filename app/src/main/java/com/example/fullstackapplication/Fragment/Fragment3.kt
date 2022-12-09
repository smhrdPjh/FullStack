package com.example.fullstackapplication.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.board.BoardAdapter
import com.example.fullstackapplication.board.BoardInsideActivity
import com.example.fullstackapplication.board.BoardVO
import com.example.fullstackapplication.board.BoardWriteActivity
import com.example.fullstackapplication.utils.FBdatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Fragment3 : Fragment() {

    //getBoardData를 통해 받아온 item(BoardVO)를 관리하는 배열 생성
    var boardList = ArrayList<BoardVO>()
    lateinit var adapter : BoardAdapter
    var keyData = ArrayList<String>()






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_3, container, false)
        // Inflate the layout for this fragment
        // btnWrite를 클릭하면 BoardWriteActivity로 이동


        val boardRv = view.findViewById<RecyclerView>(R.id.boardRv)
        val btnWrite = view.findViewById<Button>(R.id.btnWrite)

        btnWrite.setOnClickListener {
            // Fragment에서 Activity로 이동시키는 방법
            val intent = Intent(requireActivity(),BoardWriteActivity::class.java)

            startActivity(intent)
        }

        // 1. 한칸에 들어갈 디자인 만들기(board_template.xml) v
        // 2. Adapter에 보낼 데이터 가져오기
        //    Firebase에 있는 board 경로에있는 데이터를 가져오기
        getBoardData()
        adapter= BoardAdapter(requireContext(), boardList)

        // 클릭 이벤트를 호출
        adapter.setOnItemClickListener(object :BoardAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {

                // BoardInsideActivity로 넘어가자

                val intent = Intent(requireActivity(),BoardInsideActivity::class.java)

                intent.putExtra("title",boardList[position].title)
                intent.putExtra("time",boardList[position].time)
                intent.putExtra("content",boardList[position].content)
                intent.putExtra("key",keyData[position])
                startActivity(intent)

            }

        })


        // 4. boardRv에 adapter적용
        boardRv.adapter=adapter
        boardRv.layoutManager=LinearLayoutManager(requireContext())



        return view
    }

    // board에 있는 데이터 다~ 가져오는 함수
    fun getBoardData(){
        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               // Firebase에서 snapshot으로 데이터를 받아온 경우
               // 게시물의 uid
               //        -BoardVO
                boardList.clear()
                for (model in snapshot.children){
                    val item = model.getValue(BoardVO::class.java)

                    if (item != null) {
                        boardList.add(item)
                    }
                    keyData.add(model.key.toString())

                }

                // adapter 새로고침 받아오는 속도가 다르니까
                boardList.reverse()
                keyData.reverse()
                adapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                // 오류가 발생했을 경우 실행되는 함수
            }

        }

        // snapshot으로 board에 있는 모든 ~~ 데이터가 들어간다~~
        FBdatabase.getBoardRef().addValueEventListener(postListener)
    }



}