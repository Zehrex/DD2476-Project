18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/BaseStage.java
package com.openjfx.database.app;

import com.openjfx.database.app.annotation.Layout;
import com.openjfx.database.app.utils.AssetUtils;
import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.common.utils.StringUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static com.openjfx.database.app.utils.AssetUtils.getLocalImage;


/**
 * base stage
 *
 * @param <D> 传递参数类型
 * @author yangkui
 * @since 1.0
 */
public class BaseStage<D> extends Stage {

    protected Scene scene = null;

    protected BaseController<D> controller;

    /**
     * 不需要传递参数
     */
    public BaseStage() {
        initController();
        controller.init();
        initStage();
    }

    /**
     * 需要传递参数
     *
     * @param data 参数
     */

    public BaseStage(D data) {
        initController();
        controller.setData(data);
        controller.init();
        initStage();
    }

    /**
     * 获取Layout注解
     *
     * @return 返回注解信息
     */
    private Layout getLayout() {
        Layout layout = this.getClass().getAnnotation(Layout.class);
        if (Objects.isNull(layout)) {
            throw new RuntimeException("layout 不能为空");
        }
        return layout;
    }

    /**
     * 初始化controller
     */
    private void initController() {
        Layout layout = this.getClass().getAnnotation(Layout.class);
        if (Objects.isNull(layout)) {
            throw new RuntimeException("layout 不能为空");
        }
        String path = "fxml/" + layout.layout();
        URL url = ClassLoader.getSystemResource(path);
        FXMLLoader loader = new FXMLLoader(url);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e, "加载页面失败");
            throw new RuntimeException(e);
        }
        scene = new Scene(root);
        //添加全局样式
        scene.getStylesheets().add("css/base.css");
        controller = loader.getController();
        controller.setStage(this);
    }

    /**
     * 初始化stage
     */
    private void initStage() {
        Layout layout = getLayout();

        setScene(scene);
        setWidth(layout.width());
        setHeight(layout.height());
        setMaximized(layout.maximized());
        setResizable(layout.resizable());
        if (StringUtils.isEmpty(getTitle())) {
            setTitle(StringUtils.isEmpty(layout.title()) ? "DatabaseFX" : layout.title());
        }
        setAlwaysOnTop(layout.alwaysOnTop());

        Image icon = getLocalImage(200, 200, layout.icon());

        initStyle(layout.stageStyle());
        setIconified(layout.iconified());

        getIcons().add(icon);
        initModality(layout.modality());
        if (!layout.show()) {
            return;
        }
        if (layout.await()) {
            showAndWait();
        } else {
            show();
        }
    }
}
