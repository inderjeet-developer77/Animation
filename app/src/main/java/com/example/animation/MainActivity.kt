package com.example.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var ball: ImageView
    private var screenHeight: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ball = findViewById(R.id.ball)
        screenHeight = resources.displayMetrics.heightPixels.toFloat()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y

            startBallDropAnimation(x, y)
            return true
        }
        return super.onTouchEvent(event)
    }

    private fun startBallDropAnimation(x: Float, y: Float) {
        val distanceToBottom = screenHeight - y

        ball.apply {
            visibility = View.VISIBLE
            translationX = x - ball.width / 2
            translationY = y - ball.height / 2
            animate()
                .translationYBy(distanceToBottom)
                .setDuration((distanceToBottom / screenHeight * 1000).toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        visibility = View.INVISIBLE
                    }
                })
        }
    }
}

