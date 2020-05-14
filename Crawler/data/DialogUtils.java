18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/utils/DialogUtils.java
package com.openjfx.database.app.utils;

import com.openjfx.database.app.enums.NotificationType;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.Notifications;
import org.controlsfx.dialog.ExceptionDialog;

/**
 * application dialog utils
 *
 * @author yangkui
 * @since 1.0
 */
public class DialogUtils {

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * show error dialog
     *
     * @param title     header title of dialog
     * @param throwable error info
     */
    public static void showErrorDialog(Throwable throwable, String title) {
        LOGGER.error(throwable.getMessage(), throwable);
        throwable.printStackTrace();
        Platform.runLater(() -> {
            var exceptionDialog = new ExceptionDialog(throwable);
            var pane = exceptionDialog.getDialogPane();
            pane.getStylesheets().add("css/base.css");
            exceptionDialog.setHeaderText(title);
            exceptionDialog.setTitle("Exception detail");
            exceptionDialog.show();
        });
    }

    /**
     * show notification
     *
     * @param text notification content
     * @param pos  {@link Pos} notification show position
     * @param type {@link NotificationType} notification type
     */
    public static void showNotification(String text, Pos pos, NotificationType type) {
        Platform.runLater(() -> {
            var notifications = Notifications.create();
            notifications.position(pos);
            notifications.text(text);
            switch (type) {
                case ERROR:
                    notifications.showError();
                    break;
                case WARNING:
                    notifications.showWarning();
                    break;
                case INFORMATION:
                    notifications.showInformation();
                    break;
                case CONFIRMATION:
                    notifications.showConfirm();
                    break;
                default:
                    notifications.show();
            }
        });
    }

    /**
     * show confirm message
     *
     * @param message message content
     * @return <p>return the confirmation result.If you click OK,
     * it will return true. Otherwise, it will return false<p/>
     */
    public static boolean showAlertConfirm(String message) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add("css/base.css");
        var optional = alert.showAndWait();
        return optional.isPresent() && optional.get() == ButtonType.OK;
    }

    /**
     * show dialog message
     *
     * @param message message content
     */
    public static void showAlertInfo(String message) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("消息");
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add("css/base.css");
        alert.showAndWait();
    }
}
