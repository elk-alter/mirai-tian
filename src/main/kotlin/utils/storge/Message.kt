package org.elk.mirai.utils.storge

import net.mamoe.mirai.console.data.ReadOnlyPluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 *
 * Created by elk on 2021/5/3
 */
object Message : ReadOnlyPluginConfig("Message") {

    @ValueDescription("舔狗插件未启用自动回复")
    val tianmodeoff by value("本群尚未开启插件,请联系管理员")

    @ValueDescription("关闭舔狗插件的自动回复")
    val tianmode_1 by value("已经关闭了本群的舔狗插件")
    val tianmode_1to_1 by value("本群尚未启用舔狗插件,无需再次禁用")

    @ValueDescription("打开舔狗插件的自动回复")
    val tianmode_0 by value("已经打开了本群的舔狗插件")
    val tianmode_0to_0 by value("本群尚未启用舔狗插件,无需再次禁用")

    @ValueDescription("群友没有权限的时候的自动回复")
    val tiannopermission by value("您没有权限操作")

    @ValueDescription("舔狗日记报错")
    val tianerror by value("日记获取失败")

    @ValueDescription("来自tian-api的报错，code(错误代码)，msg(错误信息)")
    val tiancode150 by value("tian-api错误代码：%code% 错误信息：%message%")
    val tiancodeelse by value("tian-api错误代码：%code%,发生此错误请截取详细信息到github提交issue 错误信息：%message%")
}