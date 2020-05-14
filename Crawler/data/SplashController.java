18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controller/SplashController.java
package com.openjfx.database.app.controller;

import com.jfoenix.controls.JFXSlider;
import com.openjfx.database.app.BaseController;
import com.openjfx.database.app.config.DbPreference;
import com.openjfx.database.app.stage.DatabaseFxStage;
import com.openjfx.database.app.utils.AssetUtils;
import com.openjfx.database.model.ConnectionParam;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.openjfx.database.app.config.Constants.*;
import static com.openjfx.database.app.config.FileConfig.loadConfig;
import static com.openjfx.database.app.utils.DialogUtils.showErrorDialog;

/**
 * Splash 启动页控制器
 *
 * @author yangkui
 * @since 1.0
 */
public class SplashController extends BaseController {
    @FXML
    private Label title;

    @FXML
    private JFXSlider progress;

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void init() {
        var future = CompletableFuture.runAsync(() -> {
            try {
                init0();
                init1();
                init2();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
        future.whenComplete((r, t) -> {
            if (Objects.nonNull(t)) {
                LOGGER.error("application startup failed cause:{}", t.getMessage());
                showErrorDialog(t, "启动失败");
                return;
            }
            updateProgress("启动成功", 100);
            Platform.runLater(() -> {
                new DatabaseFxStage();
                stage.close();
            });
        });
    }


    /**
     * 更新进度
     *
     * @param title 进度描述
     * @param value 进度值 0-100
     */
    private void updateProgress(String title, double value) {
        Platform.runLater(() -> {
            this.title.setText(title);
            progress.setValue(value);
        });
    }

    private void init0() throws InterruptedException {
        AssetUtils.loadAllFont();
        updateProgress("初始化中....", 0);
        Thread.sleep(250);
    }

    private void init1() throws InterruptedException {
        updateProgress("加载配置信息...", 25);
        var db = loadConfig(DB_CONFIG_FILE);
        var params = db.getJsonArray(DATABASE).stream()
                .map(it -> ((JsonObject) it).mapTo(ConnectionParam.class))
                .collect(Collectors.toList());
        DbPreference.setParams(params);
        Thread.sleep(250);
    }

    private void init2() throws InterruptedException {
        updateProgress("加载UI配置....", 75);
        loadConfig(UI_CONFIG_FILE);
        Thread.sleep(250);
    }
}
