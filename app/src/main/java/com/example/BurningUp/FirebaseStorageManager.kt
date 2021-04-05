package com.example.BurningUp

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class FirebaseStorageManager {

    private val TAG="FirebaseStorageManager"

    private lateinit var mProgressDialog: ProgressDialog

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mStorage : FirebaseStorage
    private lateinit var mDatabase : FirebaseDatabase

    fun uploadeImage(mContext: Context, imageURI: Uri) : String {

        mProgressDialog = ProgressDialog(mContext)
        mProgressDialog.setMessage("Please wait, image being uploading...")
        mProgressDialog.show()

        mAuth = FirebaseAuth.getInstance()
        mStorage= FirebaseStorage.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        val mReference = FirebaseStorage.getInstance().reference

        var uid = mAuth?.uid

        val imageFileName = "users/${uid}/profileImage.png"
        val uploadTask = mReference.child(imageFileName).putFile(imageURI)

        uploadTask.addOnSuccessListener {
            Log.e(TAG, "Image upload successfully")
            val downloadURLTask = mReference.child(imageFileName).downloadUrl
            downloadURLTask.addOnSuccessListener {
                Log.e(TAG, "IMAGE PATH:$it")
                mProgressDialog.dismiss()
            }.addOnFailureListener{
                mProgressDialog.dismiss()
            }
        }.addOnFailureListener{
            Log.e(TAG, "Image upload failed ${it.printStackTrace()}")
            mProgressDialog.dismiss()
        }
        return imageFileName
    }

}