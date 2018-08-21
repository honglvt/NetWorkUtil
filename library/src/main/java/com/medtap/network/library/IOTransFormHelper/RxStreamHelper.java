package com.medtap.network.library.IOTransFormHelper;

import com.medtap.network.library.basemodel.BaseModel;
import com.medtap.network.library.commen.ApiException;
import com.medtap.network.library.commen.CustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxStreamHelper {
    public <T> ObservableTransformer<BaseModel<T>, T> io_Main() {
        return new ObservableTransformer<BaseModel<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseModel<T>> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        //出错统一处理
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends BaseModel<T>>>() {
                            @Override
                            public ObservableSource<? extends BaseModel<T>> apply(Throwable throwable) throws Exception {
                                return Observable.error(CustomException.Companion.handleException(throwable));
                            }
                        })
                        //解析data层，剔除 code /msg
                        .flatMap(new Function<BaseModel<T>, ObservableSource<? extends T>>() {

                            @Override
                            public ObservableSource<? extends T> apply(BaseModel<T> tBaseModel) throws Exception {
                                if (tBaseModel.getCode() == 200) {
                                    return Observable.just(tBaseModel.getData());
                                }
                                return Observable.error(new ApiException(tBaseModel.getCode(), tBaseModel.getMsg()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        ;
            }
        };

    }
}
