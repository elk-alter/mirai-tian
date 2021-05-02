package org.elk.mirai.tianapi


import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.util.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.message.MessageReceipt
import net.mamoe.mirai.utils.error
import org.elk.mirai.PluginMain
import org.elk.mirai.tianapi.jsondata.TianJson
import org.elk.mirai.utils.storge.Message
import org.elk.mirai.utils.storge.MySetting

/**
 *
 * Created by elk on 2021/5/3
 */
class TianGou(val subject: Group) {

    private var content: String = ""

    private lateinit var tianinfomsg: MessageReceipt<Group>

    companion object {
        @KtorExperimentalAPI
        private val client = HttpClient(OkHttp) {
            engine {
                proxy = when (MySetting.proxyconfig) {
                    0 -> null
                    1 -> ProxyBuilder.http(MySetting.httpproxy.proxy)
                    2 -> ProxyBuilder.socks(host = MySetting.socksproxy.host, port = MySetting.socksproxy.port)
                    else -> null
                }
            }
        }

        @KtorExperimentalAPI
        fun closeClient() {
            client.close()
        }
    }

    @KtorExperimentalAPI
    suspend fun getTian() {
        try {
            val tianJson: String =
                client.get("http://api.tianapi.com/txapi/tiangou/index?key=${MySetting.TianAPIKEY}")
            parseTian(tianJson)
        } catch (e: Exception) {
            subject.sendMessage("出现错误\n" + e.message?.replace(MySetting.TianAPIKEY, "/$/{APIKEY/}"))
            PluginMain.logger.error(e)
            throw e
        }
    }

    private fun parseTian(tianjson: String) {
        val result: TianJson = Json.decodeFromString(tianjson)
        fun parseCode(message: String): String {
            return message
                .replace("%code%", result.code.toString())
                .replace("%msg%", result.msg)
        }

        when (result.code) {
            200 -> {
                result.newslist?.get(0)?.let {
                    content = it.content
                }
            }
            // 调用到达上限
            150 -> {
                PluginMain.logger.error { "tian-api错误代码：${result.code} 错误信息：${result.msg}" }
                throw Exception(parseCode(Message.tiancode150))
            }
            // -1和403 错误等一系列未知错误
            else -> {
                PluginMain.logger.error { "发生此错误请到github反馈错误 tian-api错误代码：${result.code} 错误信息：${result.msg}" }
                throw Exception(parseCode(Message.tiancodeelse))
            }
        }
    }

    @KtorExperimentalAPI
    suspend fun sendMessage() {
        // 发送信息
        try {
            tianinfomsg = subject.sendMessage(content)
        } catch (e: Exception) {
        }
    }
}