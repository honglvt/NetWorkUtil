package com.medtap.network.library.commen

import com.medtap.network.library.basemodel.BaseModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 * 统一处理error，剥离data层
 */
class ResponseTransformer {


    class ResponseFunction<T> : Function<BaseModel<T>, ObservableSource<out T>> {
        override fun apply(t: BaseModel<T>): ObservableSource<out T> {
            val code = t.code
            val msg = t.msg
            val content = t.data
            return if (code == 200) {
                Observable.just(content!!)
            } else {
                Observable.error(ApiException(code!!, msg!!))
            }
        }
    }

    class ErrorResumeFunction<T> : Function<Throwable, ObservableSource<BaseModel<out T>>>  {

        override fun apply(e: Throwable): ObservableSource<BaseModel<out T>> {

            return Observable.error(CustomException.handleException(e))
        }
    }

}