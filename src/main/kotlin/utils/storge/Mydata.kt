package org.elk.mirai.utils.storge

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

/**
 *
 * Created by elk on 2021/5/3
 */
object Mydata : AutoSavePluginData("tian-data") {

    // 记录群的权限
    var Grouppower: MutableMap<Long, Int> by value(mutableMapOf())
}