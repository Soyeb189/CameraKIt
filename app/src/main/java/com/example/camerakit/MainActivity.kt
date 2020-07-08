package com.example.camerakit

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.camerakit.CameraKitView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var cameraKitView: CameraKitView? = null
    private lateinit var photoButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraKitView = findViewById(R.id.camera)
        photoButton = findViewById(R.id.photoButton)

        photoButton.setOnClickListener {
            photoOnClickListner
        }

    }

    override fun onResume() {
        super.onResume()
        cameraKitView!!.onResume()
    }

    override fun onPause() {
        cameraKitView!!.onPause()
        super.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraKitView!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private val photoOnClickListner =
        View.OnClickListener {
            cameraKitView!!.captureImage { cameraKitView, photo ->
                val savedPhoto =
                    File(Environment.getStorageDirectory(), "photo.jpg")
                try {
                    val fileOutputStream =
                        FileOutputStream(savedPhoto.path)
                    fileOutputStream.write(photo)
                    fileOutputStream.close()
                    //Toast.makeText(MainActivity.this, "CCCC", Toast.LENGTH_SHORT).show();
                } catch (e: IOException) {
                    //Toast.makeText(MainActivity.this, "EEE", Toast.LENGTH_SHORT).show();
                }
            }
        }
}