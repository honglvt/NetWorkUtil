# NetWorkUtil</br>
## 历时2天，完成了用kotlin写网络框架</br>
### 1.错误统一处理</br>
### 2.和服务端约定response格式，剥离出data，</br>
### 3.MVVM</br>
### 4.DTO-VO转换</br>
### 5.Activity层几乎没有代码，极度简洁</br>

# 使用</br>
## 1）首先新建一个VM类，用于网络请求</br>
## 2) 利用Map操作符把服务器的DTO结构转为VO结构
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
  
## 3) 在业务层直接调用MainVM().getData(callback)
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
## 4）用上databinding 一般APP几乎可以毫不费力地写出来了 


