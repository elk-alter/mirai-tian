package org.elk.mirai.tianapi.jsondata

import kotlinx.serialization.Serializable

/**
 *
 * Created by elk on 2021/5/3
 */
@Serializable
data class TianJson(
    val code: Int,
    val msg: String,
    val newslist: List<TianStrJson>? = null
) {
    @Serializable
    data class TianStrJson (
        val content: String
        )
}
