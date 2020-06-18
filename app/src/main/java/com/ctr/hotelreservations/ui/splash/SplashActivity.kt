package com.ctr.hotelreservations.ui.splash

import android.content.Intent
import android.os.Bundle
import com.ctr.hotelreservations.R
import com.ctr.hotelreservations.base.BaseActivity
import com.ctr.hotelreservations.extension.getStatusBarHeight
import com.ctr.hotelreservations.extension.replaceFragment
import com.ctr.hotelreservations.ui.home.MainActivity
import com.ctr.hotelreservations.ui.onboarding.OnBoardingActivity
import com.ctr.hotelreservations.util.SharedReferencesUtil

/**
 * Created by at-trinhnguyen2 on 2020/05/31
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        SharedReferencesUtil.saveInt(
            this,
            SharedReferencesUtil.KEY_STATUS_BAR_HEIGHT,
            this@SplashActivity.getStatusBarHeight()
        )
        processOnCreate()
    }

    override fun getContainerId() = R.id.container

    private fun processOnCreate() {
        fullScreenActivity()
        openSplashFragment()
    }

    private fun openSplashFragment() {
        replaceFragment(getContainerId(), SplashFragment.newInstance())
    }

    internal fun startHomeActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    internal fun startOnBoardingActivity() {
        startActivity(Intent(this, OnBoardingActivity::class.java))
        finishAffinity()
    }
}