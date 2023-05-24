package io.github.will7200.plugins.casbin.view.ui

import com.intellij.notification.Notification
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project


class CasbinExecutorErrorsNotifier {
    companion object {
        fun getNotificationGroup(): NotificationGroup {
            return NotificationGroupManager.getInstance().getNotificationGroup("Casbin Executor Error")
        }

        fun notify(content: String?): Notification {
            return notify(null, content)
        }

        fun notify(project: Project?, content: String?): Notification {
            val notification: Notification =
                this.getNotificationGroup().createNotification(content!!, NotificationType.ERROR)
            notification.notify(project)
            return notification
        }

        fun notify(project: Project?, title: String?, details: String?): Notification {
            val notification: Notification =
                this.getNotificationGroup().createNotification(title!!, details!!, NotificationType.ERROR)
            notification.notify(project)
            return notification
        }
    }
}