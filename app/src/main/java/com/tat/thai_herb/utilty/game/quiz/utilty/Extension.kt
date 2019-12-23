package com.tat.thai_herb.utilty.game.quiz.utilty

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.fragment.app.Fragment
import com.tat.thai_herb.R

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideStatusBar(){
    if (Build.VERSION.SDK_INT >= 16) {
        window.setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT)
        window.decorView.systemUiVisibility = 3328
    }else{
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}

fun Activity.darkenStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun Activity.darkenStatusBarColor(bg: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            statusBarColor = Color.TRANSPARENT
            setBackgroundDrawableResource(bg)
        }
    }
}


fun Activity.setSoundButton(type: SoundType) {
    var sound: MediaPlayer? = null

    when(type){
        SoundType.BUTTON -> {
            sound = MediaPlayer.create(this, R.raw.sound_play_btn)
            sound!!.start()
            Handler().postDelayed({
                sound!!.stop()
                sound!!.release()
                sound = null
            }, 500)
        }
        SoundType.MAIN -> {
            sound = MediaPlayer.create(this, R.raw.sound_main)
            sound!!.isLooping = true
            sound!!.start()
//            Handler().postDelayed({
//                sound.stop()
//            }, 3400)
        }
        SoundType.WIN -> {
            sound = MediaPlayer.create(this, R.raw.sound_win)
            sound!!.start()
            Handler().postDelayed({
                sound!!.stop()
                sound!!.release()
                sound = null
            }, 500)
        }
        SoundType.WRONG -> {
            sound = MediaPlayer.create(this, R.raw.sound_wrong)
            sound!!.start()
            Handler().postDelayed({
                sound!!.stop()
                sound!!.release()
                sound = null
            }, 1000)
        }
        SoundType.TIME -> {
            sound = MediaPlayer.create(this, R.raw.sound_time)
            sound!!.start()
            Handler().postDelayed({
                sound!!.stop()
                sound!!.release()
                sound = null
            }, 2000)
        }
        else -> {
            sound = MediaPlayer.create(this, R.raw.sound_correct)
            sound!!.start()
            Handler().postDelayed({
                sound!!.stop()
                sound!!.release()
                sound = null
            }, 1000)
        }
    }
}
