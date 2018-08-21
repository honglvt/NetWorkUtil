package com.medtap.network.vm

import com.medtap.network.library.IoHelper.RxStreamHelper
import com.medtap.network.library.ObserverCallBack.DesCallBack
import com.medtap.network.library.api.ApiClient
import com.medtap.network.library.commen.Destiny
import com.medtap.network.library.data.HCVO

/**
 * VM层 数据callback给View层
 */
class MainVM {
    fun getData(callback: DesCallBack<HCVO>) {
        return ApiClient
                .instance
                .getApiService()
                .test()
                .compose(RxStreamHelper().io_Main())
                .map {
                    it.transform()
                }
                .subscribe(Destiny(callback))
    }
}