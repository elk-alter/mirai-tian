package org.elk.mirai

import kotlinx.coroutines.async
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.version
import net.mamoe.mirai.utils.info
import org.elk.mirai.tianapi.TianEntrance
import org.elk.mirai.tianapi.TianGou
import org.elk.mirai.utils.checkupdate
import org.elk.mirai.utils.storge.Command
import org.elk.mirai.utils.storge.Message
import org.elk.mirai.utils.storge.MySetting
import org.elk.mirai.utils.storge.Mydata

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "org.elk.mirai-tian",
        name = "Mirai-tian",
        version = "1.0.0"
    ) {
        author("麋鹿")

        info("""
            这是一个舔狗插件, 
            主要为舔狗语录.
        """.trimIndent())

        // author 和 info 可以删除.
    }
) {
    override fun onEnable() {
        async {
            checkupdate(version.toString())
        }
        MySetting.reload()
        Mydata.reload()
        Command.reload()
        Message.reload()
        TianEntrance()
        logger.info { "舔狗插件加载完成，版本：$version" }
    }

    override fun onDisable() {
        TianGou.closeClient()
        logger.info { "舔狗插件已关闭，牛年快乐" }
    }
}