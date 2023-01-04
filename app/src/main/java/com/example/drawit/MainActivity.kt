package com.example.drawit

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null

    private var mImageButtonCurrentPaint: ImageButton? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        drawingView = findViewById(R.id.drawingview)
        drawingView?.setSizeForBrush(7.toFloat())

        val undo:Button=findViewById(R.id.undo)
        undo.setOnClickListener{
            drawingView?.onclickUndo()
        }



    }
}