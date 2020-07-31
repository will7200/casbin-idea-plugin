package io.github.will7200.plugins.casbin.view.ui

import com.intellij.notification.Notification
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project


class CasbinExecutorErrorsNotifier {
    private val NOTIFICATION_GROUP =
        NotificationGroup("Casbin Executor Error", NotificationDisplayType.BALLOON, true)

    fun notify(content: String?): Notification? {
        return notify(null, content)
    }

    fun notify(project: Project?, content: String?): Notification? {
        val notification: Notification = NOTIFICATION_GROUP.createNotification(content!!, NotificationType.ERROR)
        notification.notify(project)
        return notification
    }

    fun notify(project: Project?, title: String?, details: String?): Notification? {
        val notification: Notification =
            NOTIFICATION_GROUP.createNotification(title!!, details!!, NotificationType.ERROR)
        notification.notify(project)
        return notification
    }
}