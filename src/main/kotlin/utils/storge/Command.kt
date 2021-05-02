package org.elk.mirai.utils.storge

import net.mamoe.mirai.console.data.ReadOnlyPluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 *
 * Created by elk on 2021/5/3
 */
object Command : ReadOnlyPluginConfig("command"){
    @ValueDescription("修改触发的指令")
    val command_get: MutableList<String> by value(mutableListOf("日记", "舔狗日记", "舔狗", "舔"))
    val command_off: MutableList<String> by value(mutableListOf("关闭插件", "封印"))
    val command_on: MutableList<String> by value(mutableListOf("打开插件", "开启封印"))
}