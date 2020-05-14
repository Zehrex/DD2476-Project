18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/BaseController.java
package com.openjfx.database.app;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * base controller
 *
 * @param <D> 传递数据类型
 * @author yangkui
 * @since 1.0
 */
public abstract class BaseController<D> implements Initializable {
    /**
     * 外部数据
     */
    protected D data;
    /**
     * stage引用
     */
    protected Stage stage;
    /**
     * ResourceBundle
     */
    protected ResourceBundle resourceBundle;
    /**
     * URL
     */
    protected URL location;

    /**
     * 初始化fxml视图时调用
     *
     * @param location  location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        this.location = location;
    }

    /**
     * 初始化controller
     */
    public void init() {
        //todo override
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
