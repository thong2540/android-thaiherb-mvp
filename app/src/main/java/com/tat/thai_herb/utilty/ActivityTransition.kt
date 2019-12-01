package com.tat.thai_herb.utilty

import android.app.Activity
import com.tat.thai_herb.R

object ActivityTransition {

    fun GoRight(activity: Activity) {
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun GoLeft(activity: Activity) {
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }
}