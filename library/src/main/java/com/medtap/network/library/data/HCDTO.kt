package com.medtap.network.library.data

import com.medtap.network.library.commen.Transform

class HCDTO : Transform<HCVO> {
    var name: String? = null
    var age: Int? = 0
    var sex: String? = null
    var girl: String? = null
    override fun transform(): HCVO {
        return HCVO(name!!, age!!)
    }
}