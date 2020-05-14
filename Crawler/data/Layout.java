18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/annotation/Layout.java
package com.openjfx.database.app.annotation;

import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Stage 配置注解
 *
 * @author yangkui
 * @since 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Layout {
    /**
     * 布局文件名称
     *
     * @return 布局文件名
     */
    String layout();

    /**
     * 窗口标题
     *
     * @return 窗口标题
     */
    String title() default "";

    /**
     * 窗口图标
     *
     * @return 返回图标
     */
    String icon() default "icon.png";

    /**
     * 窗口宽度
     *
     * @return 返回宽度
     */
    double width() default 800;

    /**
     * 窗口高度
     *
     * @return 返回高度
     */
    double height() default 600;

    /**
     * 窗口是否最大化
     *
     * @return 返回是否最大化
     */
    boolean maximized() default false;

    /**
     * 是否显示
     *
     * @return 返回是否显示
     */
    boolean show() default true;

    /**
     * 是否调用showAndWait
     *
     * @return 返回结果
     */
    boolean await() default false;

    /**
     * 是否允许窗口调整
     *
     * @return 返回结果
     */
    boolean resizable() default true;

    /**
     * stage style
     */
    StageStyle stageStyle() default StageStyle.DECORATED;

    /**
     * 是否允许最小化
     */
    boolean iconified() default false;

    /**
     * 是否显示在最顶层
     */
    boolean alwaysOnTop() default false;

    /**
     * 窗口模式
     */
    Modality modality() default Modality.NONE;
}
