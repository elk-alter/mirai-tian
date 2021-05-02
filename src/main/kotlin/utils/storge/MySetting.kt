package org.elk.mirai.utils.storge

import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.ReadOnlyPluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 *
 * Created by elk on 2021/5/3
 */
object MySetting : ReadOnlyPluginConfig("tian-config") {

    @ValueDescription("插件权限控制设置\n0为所有人都可以控制,1为只有插件主人可以进行配置，2为群管理员也可以配置")
    val mastermode by value(1)

    @ValueDescription("设置此插件主人的id。")
    val masterid by value(mutableListOf<Long>(123456))

    @ValueDescription("设置tian-api的APIKEY(https://www.tianapi.com/)可以获取自己的api来获取稳定的日记请求量")
    val TianAPIKEY by value("")

    @ValueDescription("代理设置,0为不使用代理，1为使用http代理，2为使用socks代理\n 代理只对日记的获取生效")
    val proxyconfig by value(0)
    val httpproxy by value(HttpProxy())
    val socksproxy by value(SocksProxy())

    @Serializable
    data class SocksProxy(
        val host: String = "127.0.0.1",
        val port: Int = 4001
    )

    @Serializable
    data class HttpProxy(
        val proxy: String = "http://127.0.0.1:80"
    )
}