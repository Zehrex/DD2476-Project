18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controller/CreateConnectionController.java
package com.openjfx.database.app.controller;

import com.openjfx.database.app.config.DbPreference;
import com.openjfx.database.app.enums.NotificationType;
import com.openjfx.database.app.BaseController;
import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.common.VertexUtils;
import com.openjfx.database.model.ConnectionParam;
import com.openjfx.database.mysql.MysqlHelper;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;
import java.util.Optional;

import static com.openjfx.database.app.config.Constants.ACTION;
import static com.openjfx.database.app.config.Constants.UUID;
import static com.openjfx.database.app.config.DbPreference.updateConnection;
import static com.openjfx.database.app.config.FileConfig.saveConnection;
import static com.openjfx.database.app.utils.DialogUtils.showErrorDialog;
import static com.openjfx.database.app.utils.DialogUtils.showNotification;
import static com.openjfx.database.common.utils.StringUtils.isEmpty;

/**
 * 创建连接控制器
 *
 * @author yangkui
 * @since 1.0
 */
public class CreateConnectionController extends BaseController<String> {
    @FXML
    private TextField server;
    @FXML
    private TextField name;
    @FXML
    private TextField port;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;

    @Override
    public void init() {
        if (data != null) {
            //加载配置信息
            DbPreference.getConnectionParam(data).ifPresent(cc -> {
                server.setText(cc.getHost());
                name.setText(cc.getName());
                port.setText(String.valueOf(cc.getPort()));
                user.setText(cc.getUser());
                password.setText(cc.getPassword());
            });
        }
    }

    @FXML
    private void test() {
        var param = validatorParam();
        if (Objects.isNull(param)) {
            return;
        }
        var future = MysqlHelper.testConnection(param);
        future.onSuccess(r -> showNotification("连接成功", Pos.TOP_CENTER, NotificationType.INFORMATION));
        future.onFailure(t -> showErrorDialog(t, "连接失败"));
    }

    @FXML
    private void close() {
        stage.close();
    }

    @FXML
    private void save() {
        var param = validatorParam();
        if (param == null) {
            return;
        }
        var flag = true;
        //update connection
        if (Objects.nonNull(data)) {
            updateConnection(param);
            flag = DialogUtils.showAlertConfirm("连接已更改是否重连?");
        } else {
            //new connection
            saveConnection(param);
            DbPreference.addConnection(param);
        }
        var message = new JsonObject();
        DatabaseFxController.EventBusAction action = Objects.nonNull(data)
                ? DatabaseFxController.EventBusAction.UPDATE_CONNECTION
                : DatabaseFxController.EventBusAction.ADD_CONNECTION;

        message.put(ACTION, action);
        message.put(UUID, param.getUuid());

        if (flag) {
            VertexUtils.eventBus().send(DatabaseFxController.EVENT_ADDRESS, message);
        }

        stage.close();
    }

    private ConnectionParam validatorParam() {
        var a = server.getText();
        var b = port.getText();
        var c = user.getText();
        var d = password.getText();
        var e = name.getText();

        if (isEmpty(a) || isEmpty(b) || isEmpty(c) || isEmpty(d)) {
            showNotification("参数不全", Pos.TOP_CENTER, NotificationType.WARNING);
            return null;
        }

        var param = new ConnectionParam();

        if (Objects.nonNull(data)) {
            param.setUuid(data);
        }

        param.setHost(a);
        param.setUser(c);
        param.setPassword(d);
        param.setPort(Integer.parseInt(b));
        param.setName(e);

        return param;
    }
}
