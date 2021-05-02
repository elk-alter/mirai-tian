package org.elk.mirai.utils

import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.isOperator
import org.elk.mirai.PluginMain
import org.elk.mirai.utils.storge.MySetting

/**
 *
 * Created by elk on 2021/5/3
 */
fun checkpower(sender: Member): Boolean {
    when (MySetting.mastermode) {
        0 -> {
            return true
        }
        1 -> {
            if (MySetting.masterid.contains(sender.id))
                return true
        }
        2 -> {
            if (MySetting.masterid.contains(sender.id))
                return true
            return sender.isOperator()
        }
        else -> {
            PluginMain.logger.warning("权限设置信息错误，请检查权限模式配置")
            return false
        }
    }
    return false
}