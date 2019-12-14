package com.tat.thai_herb

import android.app.Application
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupCalligraphy()
    }

    private fun setupCalligraphy() {
        ViewPump.init(
            ViewPump.builder().addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig
                        .Builder()
                        .setDefaultFontPath("fonts/kanit_regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                )
            ).build()
        )
    }
}