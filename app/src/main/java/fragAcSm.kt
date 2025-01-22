package com.example.firstprojwct

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FragmentSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_sample)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.insetsController?.let { insetsController ->
                insetsController.hide(android.view.WindowInsets.Type.statusBars())
                insetsController.hide(android.view.WindowInsets.Type.navigationBars())
                insetsController.systemBarsBehavior = android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<View>(R.id.changeButton).setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

            if (currentFragment is FragmentA) {
                fragmentTransaction.replace(R.id.fragment_container_view, FragmentB())
                fragmentTransaction.addToBackStack(null)
            } else {
                supportFragmentManager.popBackStack()
            }

            fragmentTransaction.commit()
        }
    }
}
