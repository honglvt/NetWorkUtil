package com.medtap.network.commen_network_util

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.medtap.network.library.ObserverCallBack.DesCallBack
import com.medtap.network.library.data.HCVO
import com.medtap.network.vm.MainVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_btn.setOnClickListener { request() }
    }

    fun request() {

        val mainVM = MainVM()
        mainVM.getData(object : DesCallBack<HCVO> {
            override fun success(any: HCVO) {
                Log.i("success", any.name)
            }

            override fun failed(e: Throwable) {
            }

        })
    }
}
