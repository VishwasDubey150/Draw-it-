package com.example.drawit

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.icu.text.CaseMap.Title
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val cameraResultLauncher:ActivityResultLauncher<String> =
    registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
            isGranted ->
            if (isGranted)
            {
                Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"permission Denied",Toast.LENGTH_SHORT).show()
            }

        }


    private var drawingView: DrawingView? = null

    private var mImageButtonCurrentPaint: ImageButton? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        drawingView = findViewById(R.id.drawingview)
        drawingView?.setSizeForBrush(7.toFloat())


        val undo:ImageButton=findViewById(R.id.undo)
        undo.setOnClickListener{
            drawingView?.onclickUndo()
        }
    }
    fun brushsizing(view: View) {
        showBrushSizeChooserDialog()
    }
    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.brushsize)
        brushDialog.setTitle("Brush size :")
        val smallBtn: ImageButton = brushDialog.findViewById(R.id.ib_small_brush)
        smallBtn.setOnClickListener(View.OnClickListener {
            drawingView?.setSizeForBrush(5.toFloat())
            brushDialog.dismiss()
        })
        val mediumBtn: ImageButton = brushDialog.findViewById(R.id.ib_medium_brush)
        mediumBtn.setOnClickListener(View.OnClickListener {
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        })

        val largeBtn: ImageButton = brushDialog.findViewById(R.id.ib_large_brush)
        largeBtn.setOnClickListener(View.OnClickListener {
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        })
        brushDialog.show()
    }

    fun galary(view: View) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M &&
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
        {
            showRationaleDialog("Permission Demo requires camera accesss","access denied")
        }
        else
        {
            cameraResultLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun showRationaleDialog(title: String,message: String,) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("cancel") { dialog,
                                           _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}


