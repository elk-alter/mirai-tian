package org.elk.mirai.tianapi

import io.ktor.util.*
import net.mamoe.mirai.console.command.ConsoleCommandSender.sendMessage
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import org.elk.mirai.utils.checkpower
import org.elk.mirai.utils.storge.Command
import org.elk.mirai.utils.storge.Message
import org.elk.mirai.utils.storge.Mydata

/**
 *
 * Created by elk on 2021/5/3
 */

@KtorExperimentalAPI
fun TianEntrance() {
    GlobalEventChannel.subscribeGroupMessages {
        case("好耶") {
            group.sendMessage("好耶")

        }

        //舔狗日记
        always {
            if (Command.command_get.contains(message.contentToString())) {
                if (Mydata.Grouppower[group.id] != null) {
                    val tian = TianGou(group)
                    tian.getTian()
                    tian.sendMessage()
                } else {
                    group.sendMessage(Message.tianmodeoff)
                }
            }
        }

        // 舔狗插件开关控制
        always {
            // 关闭舔狗插件
            if (Command.command_off.contains(message.contentToString())) {
                if (checkpower(sender)) {
                    if (Mydata.Grouppower[group.id] == null) {
                        group.sendMessage(Message.tianmode_1to_1)
                    } else {
                        group.sendMessage(Message.tianmode_1)
                        Mydata.Grouppower.set(group.id, value = 0)
                    }
                } else {
                    group.sendMessage(Message.tiannopermission)
                }
            }
        }

        always {
            if (Command.command_on.contains(message.contentToString())) {
                group.sendMessage("开启中...")
                if (checkpower(sender)) {
                    if (Mydata.Grouppower[group.id] != null) {
                        if (Mydata.Grouppower[group.id] == 0) {
                            Mydata.Grouppower.set(group.id, value = 1)
                            group.sendMessage(Message.tianmode_0)
                        } else {
                            group.sendMessage(Message.tianmode_0to_0)
                        }
                    } else {
                        Mydata.Grouppower[group.id] = 1
                        group.sendMessage(Message.tianmode_0)
                    }
                } else {
                    group.sendMessage(Message.tiannopermission)
                }
            }
        }
    }
}

private fun MutableList<String>.startWith(contentToString: String): String? {
    this.forEach {
        if (contentToString.startsWith(it)) {
            return contentToString.replace(it, "").replace(" ", "")
        }
    }
    return null
}