package com.ctr.hotelreservations.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ctr.hotelreservations.R
import com.ctr.hotelreservations.base.BaseActivity
import com.ctr.hotelreservations.extension.addFragment

class AuthActivity : BaseActivity() {

    companion object {
        private const val KEY_OPEN_LOGIN = "key_open_login"
        internal fun start(from: Fragment, isOpenLogin: Boolean) {
            AuthActivity()
                .apply {
                    val intent = Intent(from.activity, AuthActivity::class.java)
                    intent.putExtras(Bundle().apply {
                        putBoolean(KEY_OPEN_LOGIN, isOpenLogin)
                    })
                    from.startActivity(intent)
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (intent.getBooleanExtra(KEY_OPEN_LOGIN, true)) {
            addFragment(getContainerId(), LoginFragment.newInstance())
        } else {
            addFragment(getContainerId(), RegisterFragment.newInstance())
        }
    }

    override fun getContainerId() = R.id.container
}