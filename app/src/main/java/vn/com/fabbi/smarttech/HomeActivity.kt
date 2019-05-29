package vn.com.fabbi.smarttech

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_home.*

import vn.com.fabbi.smarttech.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {

    private var mNavigation: BottomNavigationView? = null
    private var mCurrentFragmentTag: String? = null
    private var mCurrentFragment: Fragment? = null


    private val mBottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        Log.d(TAG, "onNavigationItemSelected() $item")

        var temTag: String? = null
        when (item.itemId) {
            R.id.nav1 -> temTag = HomeFragment::class.java.simpleName
            R.id.nav2 -> {
            }
            R.id.nav3 -> return@OnNavigationItemSelectedListener true
            R.id.nav4 -> {
            }
            else -> {
            }
        }

        if (temTag == null || TextUtils.equals(mCurrentFragmentTag, temTag)) return@OnNavigationItemSelectedListener true
        mCurrentFragmentTag = temTag

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        mCurrentFragment = fm.findFragmentByTag(mCurrentFragmentTag)
         Log.d(TAG, "onNavigationItemSelected() $mCurrentFragment")
        if (mCurrentFragment == null) {
            mCurrentFragment = createFragment(mCurrentFragmentTag)
        }
        ft.replace(R.id.home_fragment, mCurrentFragment!!, mCurrentFragmentTag).commit()
        fm.executePendingTransactions()
        true
    }

    private fun createFragment(tag: String?): Fragment? {
        var fragment: Fragment? = null
        if (HomeFragment::class.java.simpleName == tag) {
            fragment = HomeFragment()
        } else {

        }
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mNavigation = findViewById(R.id.navigation)
        mNavigation!!.setOnNavigationItemSelectedListener(mBottomNavigationListener)
        mNavigation!!.selectedItemId = R.id.nav1
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.extras != null) {

        }
    }

    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }

}
