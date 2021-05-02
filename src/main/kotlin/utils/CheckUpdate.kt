package org.elk.mirai.utils

import io.ktor.client.request.*
import org.elk.mirai.PluginMain

/**
 *
 * Created by elk on 2021/5/3
 */
suspend fun checkupdate(version: String) {
    val newversion: String =
        KtorUtils.normalclient.get("https://cdn.jsdelivr.net/gh/elk-alter/mirai-tian@main/doc/Version.txt")

    // git给文件加了一个回车我能怎么办呢
    if (newversion.equals(version + "\n"))
        PluginMain.logger.info("舔狗插件当前版本:$version")
    else
        PluginMain.logger.warning("舔狗插件当前版本：$version，检查到新版本：$newversion 请到 https://github.com/elk-alter/mirai-tian/releases 更新")
}
