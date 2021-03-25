package com.example.BurningUp

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.BurningUp.databinding.ActivityRegister2Binding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class Register2Activity : AppCompatActivity() {

    private var mBinding: ActivityRegister2Binding? = null
    private val binding get() = mBinding!!

    private var profileImg:String ?=null

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseDatabase

    companion object{
        const val REQUEST_FROM_CAMERA = 1001
        const val REQUEST_FROM_GALLERY = 1002
    }

    data class UserInfo(var uid : String? = null,
                        var email : String?=null,
                        var Nick : String?=null,
                        var profileImage : String?=null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.profileImageView.setOnClickListener {

            setPermission()//최초에 권한을 설정

            var builder = AlertDialog.Builder(this)
            builder.setTitle("프로필 사진 등록")
            builder.setIcon(R.drawable.edit_profile_img)

            // 버튼 클릭시에 무슨 작업을 할 것인가!
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        DialogInterface.BUTTON_POSITIVE ->
                            captureImageUsingCamera()//기본 카메라 앱을 실행하여 사진 촬영

                        DialogInterface.BUTTON_NEUTRAL -> { //기본 이미지로 프로필을 설정
                            binding.profileImageView.setImageResource(R.drawable.default_profile_img)
                        }
                        DialogInterface.BUTTON_NEGATIVE ->
                            pickImageFromGallery()//앨범에서 이미지 설정
                    }
                }
            }

            //왜 긍정적 부정적 중립인지 모르겠음...ㅋㅋ
            builder.setNeutralButton("기본 이미지 설정", listener)
            builder.setNegativeButton("앨범에서 가져오기", listener)
            builder.setPositiveButton("사진 촬영", listener)

            builder.show()
        }

        binding.registerBtn.setOnClickListener{
            val userInfo = UserInfo(
                uid = auth?.uid,
                email = auth?.currentUser?.email,
                Nick = binding.nicknameEdit.text.toString(),
                profileImage = profileImg
            )

            database.reference.child("users").child("${userInfo.uid}")
                .setValue(userInfo)

            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)

        }
    }

    /*테드 퍼미션 설정*/
    private fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() {//설정 해놓은 위험 권한들이 허용 되었을 경우 이곳을 수행
                Toast.makeText(this@Register2Activity, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {//거부 됐을 경우
                Toast.makeText(this@Register2Activity, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정]->[권한] 항목에서 허용해주세요.")
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .check()
    }

    private fun captureImageUsingCamera(){
        ImagePicker.with(this).cameraOnly()
            .cropSquare()//정사각형으로 crop
            .start(REQUEST_FROM_CAMERA)
    }

    private fun pickImageFromGallery(){
        ImagePicker.with(this)
            .cropSquare()//정사각형으로 crop
            .galleryOnly().start(REQUEST_FROM_GALLERY)
    }


    //startActivityForResult를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과 값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK){
            when(requestCode){
                REQUEST_FROM_CAMERA-> {
                    binding.profileImageView.setImageURI(data!!.data)
                    profileImg=FirebaseStorageManager().uploadeImage(this,data.data!!)
                }
                REQUEST_FROM_GALLERY-> {
                    binding.profileImageView.setImageURI(data!!.data)
                    profileImg=FirebaseStorageManager().uploadeImage(this,data.data!!)
                }
            }
        }
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}