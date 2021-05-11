package com.example.BurningUp

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.BurningUp.databinding.ActivityChangeProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception
import java.util.*


class ChangeProfileActivity : AppCompatActivity()
{
    private var mBinding: ActivityChangeProfileBinding? = null
    private val binding get() = mBinding!!

    private lateinit var auth : FirebaseAuth
    private var uid : String? = null
    private lateinit var firebase : FirebaseDatabase
    private lateinit var users_test_ref : DatabaseReference //연습용
    private lateinit var user_ref: DatabaseReference
    private lateinit var storage : FirebaseStorage
    private lateinit var storage_ref : StorageReference

    private val PICK_FROM_ALBUM = 1
    /*
        TODO : 나중에 다른 액티비티에서 사용
        users_test_ref = firebase.getReference("usersTest").child(uid.toString()).child("nickname")
        GetUserDataPractice()
    */

    //exp : App 실행동안 값을 메모리에 유지
    companion object
    {
        var bitmap : Bitmap ?= null
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth?.uid
        firebase = FirebaseDatabase.getInstance()
        user_ref = firebase.getReference("usersTest").child(uid.toString())

        storage = FirebaseStorage.getInstance()
        storage_ref = storage.reference //storage's root

        var uid = auth?.uid
        var email = auth?.currentUser?.email
        //GetProfileFromStorage()

        //call Function
        MoveGallery()
        ChangeUserData()
        ReturnMainActivity()

        //test at 04/07
        Users.readInfo() //exp : Users의 method를 통해 DB의 데이터를 읽어옵니다.
    }

    //exp : activity가 front에 올 때 마다 실행
    // 자주 호출 되므로 Unity의 Update()라고 생각하고 복잡도 낮춰야 합니다.
    override fun onResume()
    {
        super.onResume()
        if(bitmap != null)
        {
            binding.circleview.setImageBitmap(bitmap)
        }
    }

    fun MoveGallery()
    {
        //갤러리에 접근
        binding.circleview.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent , PICK_FROM_ALBUM)
        }
    }

    //exp :
    // 1. 갤러리의 사진을 누르면 호출되어 Dialog(builder 객체 이용)로 사용자의 클릭에 대한 확인 작업을 합니다.
    // 2. get의 return값은 uri타입 , 그걸 getbitmap으로 return값을 bitmap 타입으로
    //ref : https://taekwang.tistory.com/6
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_FROM_ALBUM && resultCode == Activity.RESULT_OK)
        {
            var builder = AlertDialog.Builder(this) //dialog 제작
            builder.setTitle("프로필 사진을 변경하시겠습니까?")
            builder.setIcon(R.drawable.icon)

            var listener = object : DialogInterface.OnClickListener
            {
                override fun onClick(p0: DialogInterface?, btn_num: Int)
                {
                    when (btn_num)
                    {
                        DialogInterface.BUTTON_POSITIVE -> {
                            var current_image_url = data?.data
                            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, current_image_url)
                            binding.circleview.setImageBitmap(bitmap)
                            //hard : !!문법이 ?type을 ?아닌 type으로 하는 듯
                            //아래의 코드 한 줄로 유저의 프로필 사진을 올립니다. -> 이미 데이터가 있다면 어떻게 될까?
                            val user_storage = storage_ref.child("users/${uid}/profileImage.png").putFile(current_image_url!!)
                            Log.d("jiwon" , "변경 O")
                        }
                        DialogInterface.BUTTON_NEGATIVE ->{
                            Log.d("jiwon" , "변경 X")
                        }
                    }
                }
            }
            builder.setPositiveButton("네", listener)
            builder.setNegativeButton("아니오", listener)
            builder.show()

            //exp : no dialog code
            /*
            var current_image_url = data?.data
            try {
                Log.d("jiwon" , "click gallery")
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,current_image_url)
                binding.circleview.setImageBitmap(bitmap)
            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }*/
        }
    }

    fun ChangeUserData()
    {
        binding.btnConfirm.setOnClickListener{
            user_ref.child("nickname").setValue(binding.editTextTextPersonName.text.toString())
            user_ref.child("determination").setValue(binding.etDetermination.text.toString())
            Toast.makeText(this.getApplicationContext() , "프로필 변경이 완료되었습니다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }

    }

    fun ReturnMainActivity()
    {
        binding.btnReturnMain.setOnClickListener {
            Toast.makeText(this.getApplicationContext() , "프로필 변경을 취소하셨습니다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }
    }

    fun GetProfileFromStorage()
    {
        Log.d("jiwon" , "come")
        val imageFileName = "users/IAtbYnVcBZdxKuZLzBJ8izWM9mr1/profileImage.png"
        val uploadTask = storage_ref.child(imageFileName)
        Log.d("jiwon" , uploadTask.name.toString())
    }

    //exp : 연습
    fun GetUserDataPractice()
    {
        val map = HashMap<Any, Any>()
        users_test_ref.get().addOnSuccessListener{
            Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 형식
            Log.d("jiwon" , it.getValue().toString()) //exp : getValue()를 이용하는 형식 -> 이게 익숙
            map.put("key" , it.getValue().toString()) //exp : it가 DataSnapshot이므로 , getValue의 return type도 Datasnapshot이므로 string으로
        }
    }

    /*
    private fun AddPostEventListener(postReference: DatabaseReference)
    {
        // [START post_value_event_listener]
        val postListener = object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                val post = dataSnapshot.getValue()
                Log.d("jiwon" , post.toString());

            }

            override fun onCancelled(databaseError: DatabaseError)
            {
                // Getting Post failed, log a message
                Log.w("jiwon", "loadPost:onCancelled", databaseError.toException())
            }
        }
        postReference.addValueEventListener(postListener)
        // [END post_value_event_listener]
    }*/


}