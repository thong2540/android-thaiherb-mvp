package com.tat.thai_herb.ui.game.presenter

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.firebase.database.*
import com.tat.thai_herb.R
import com.tat.thai_herb.model.respone.DataGame
import com.tat.thai_herb.model.respone.SearchList
import com.tat.thai_herb.ui.game.GameView

class GamePresenter(private val view: GameView.View) {

    private var databaseReference: DatabaseReference? = null

    internal var data: SearchList? = null
    private val list = arrayListOf<SearchList>()
    private val bit = arrayListOf<DataGame>()

//    fun getDta() {
//        list.clear()
//        databaseReference = FirebaseDatabase.getInstance().getReference("data_herb").child("search")
//        databaseReference!!.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                for (snapshot in p0.children) {
//                    data = snapshot.getValue(SearchList::class.java)
//                    list.add(data!!)
//                }
//                view.respone(list)
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//
//        })
//    }

    fun mekeData(mActivity: Activity) {
        bit.clear()
        bit.add(
            DataGame(
                "อาการเบื่ออาหาร",
                "\nส่วนที่ใช้ : เถาหรือลำต้นสด\n\nขนาดและวิธีใช้ : เป็นยาช่วยเจริญอาหาร โดยใช้เถาหรือต้นสด ครั้งละ 2 คืบครึ่ง ตำคั้นเอาน้ำดื่ม หรือต้มกับน้ำ โดยใช้น้ำ 3 ส่วน ต้มเคี่ยวให้เหลือ 1 ส่วน ดื่มก่อนอาหารวันละ 2 ครั้ง เช้าและเย็น หรือเวลามีอาการหรืออาจใช้ดองน้ำผึ้ง หรือปั้นเป็นลูกกลอน",
                R.drawable.game_bo,
                "https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FBoraphet.jpg?alt=media&token=0c10f052-6cbc-442f-8542-59257ec5f216",
                "บอระเพ็ด"
            )
        )

        bit.add(
            DataGame(
                "อาการท้องอืด",
                "\nส่วนที่ใช้ : ดอกตูม\n\nสารสำคัญและฤทธิ์ทางเภสัชวิทยา : ดอกตูมประกอบด้วยน้ำมันหอมระเหย 14-20 % ซึ่งประกอบด้วยสารเคมีหลายชนิดที่สำคัญคือ Eugenol, Acetyl eugenol, Eugenol acetate เป็นต้น สารEugenol มีฤทธิ์ลดการบีบตัวของลำไส้ ทำให้อาการปวดท้องลดลง ช่วยขับน้ำดี ลดอาการจุกเสียด และช่วยฆ่าเชื้อแบคทีเรีย\n\nขนาดและวิธีใช้ : ดอกแห้งของกานพลูรักษาอาการท้องอืดเฟ้อแน่นจุกเสียดโดยใช้ดอกแห้ง 5-8 ดอก(0.12-0.16 กรัม) ต้มน้ำดื่ม หรือบดเป็นผง ชงน้ำดื่มนอกจากนี้ยังป้องกันไม่ให้ เด็กอ่อนท้องอืดเฟ้อได้ โดยใช้ดอกแห้ง 1 ดอก แช่น้ำไว้ในกระติกน้ำร้อนที่ใช้ชงนมให้เด็กอ่อน",
                R.drawable.game_kan,
                "https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FKanphlu.jpg?alt=media&token=aaadf2c3-e0eb-4fb9-9312-1c8ab2237aa9",
                "กานพลู"
            )
        )

        bit.add(
            DataGame(
                "อาการท้องเสีย",
                "\nส่วนที่ใช้ : ลูกดิบ หรือลูกห่าม\n\nสารสำคัญและฤทธิ์ทางเภสัชวิทยา : ผลกล้วยมีสารแทนนิน และกรดอินทรีย์อีกหลายชนิด สารสกัดจากกล้วยมีฤทธิ์ฆ่าเชื้อราและแบคทีเรียที่เป็นสาเหตุให้เกิดหนอง สารแทนนินช่วยรักษาอาการท้องเสีย\n\nขนาดและวิธีใช้ : รับประทานครั้งละ ครึ่งผล-หนึ่งผล หรือใช้กล้วยน้ำว้าดิบฝานเป็นแว่นตากแดดให้แห้ง บดเป็นผง ชงน้ำดื่มครั้งละครึ่งผลถึงหนึ่งผล หรือบดเป็นผงปั้นเป็นยาลูกกลอนรับประทานครั้งละ 4 เม็ด วันละ 4 ครั้ง ก่อนอาหารและก่อนนอน",
                R.drawable.game_kuy,
                "https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FKluaiNamwa.jpg?alt=media&token=2bb5b034-2c8a-432c-8173-ddcd16643cb3",
                "กล้วยน้ำว้า"
            )
        )

        bit.add(
            DataGame(
                "อาการไอและระคายคอจากเสมหะ",
                "\nส่วนที่ใช้ : ผลแก่สด\n\nขนาดและวิธีใช้ : นำผลแก่สด 5-10 ผล โขลกพอแหลก คั้นเอาน้ำ ใส่เกลือ รับประทานบ่อยๆ",
                R.drawable.game_ma,
                "https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FMaWaengKhruea.jpg?alt=media&token=6cc1044e-cb59-4314-b9d5-33f679553f7c",
                "มะแว้งเครือ"
            )
        )

        bit.add(
            DataGame(
                "อาการท้องเสีย",
                "\nส่วนที่ใช้ : เปลือกผลแห้ง\n\nสารสำคัญและฤทธิ์ทางเภสัชวิทยา  เปลือกผลมีแทนนินประมาณ 22-25 % กรด gallotanic 28 % สารดังกล่าวนี้มีฤทธิ์รักษาอาการท้องเดินได้\n\nขนาดและวิธีใช้ : เปลือกทับทิมใช้เป็นยาแก้ท้องเดินและบิด มีวิธีใช้คือใช้เปลือกผลแห้งประมาณ 1 ใน 4 ของผล ฝนกับน้ำปูนใสให้ข้นๆ รับประทานครั้งละ 1-2 ช้อนแกง หรือต้มกับน้ำปูนใสแล้วดื่มน้ำที่ต้มก็ได้",
                R.drawable.game_thap,
                "https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FThapthim.jpg?alt=media&token=431c6662-4921-45f5-9d61-ebeb6b0c4116",
                "ทับทิม"
            )
        )

        view.respone(bit)
    }

}