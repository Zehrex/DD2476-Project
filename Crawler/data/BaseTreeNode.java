18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controls/BaseTreeNode.java
package com.openjfx.database.app.controls;


import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.model.ConnectionParam;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Database node base class
 *
 * @param <T> value type
 * @author yangkui
 * @since 1.0
 */
public abstract class BaseTreeNode<T> extends TreeItem<T> {

    /**
     * Loading state, prevent repeated loading true means false is not in loading
     */
    private final BooleanProperty loading = new SimpleBooleanProperty();
    /**
     * Connection parameters property
     */
    protected ObjectProperty<ConnectionParam> param = new SimpleObjectProperty<>();

    /**
     * Called when a child node is initialized
     */
    public abstract void init();

    /**
     * Menu list
     */
    protected List<MenuItem> menus = new ArrayList<>();

    /**
     * Load progress information
     */
    private final ProgressIndicator indicator = new ProgressIndicator();

    /**
     * Node constructor
     *
     * @param param Link parameters
     */
    public BaseTreeNode(ConnectionParam param, Image image) {
        this.param.set(param);
        var icon = new ImageView(image);
        var stackPane = new StackPane();

        indicator.setPrefWidth(20);
        indicator.setPrefHeight(20);
        indicator.setVisible(false);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(indicator, icon);
        setGraphic(stackPane);
        //Detect status changes
        loading.addListener((observable, oldValue, newValue) ->
                Platform.runLater(() ->
                        {
                            if (newValue) {
                                icon.setVisible(false);
                                indicator.setVisible(true);
                            } else {
                                indicator.setVisible(false);
                                icon.setVisible(true);
                            }
                        }
                ));
    }

    /**
     * initialization failed
     *
     * @param throwable Exception information
     * @param message   Error message
     */
    protected void initFailed(Throwable throwable, String message) {
        DialogUtils.showErrorDialog(throwable, message);
        setLoading(false);
    }

    public String getUuid() {
        return param.get().getUuid();
    }

    public String getServerName() {
        return param.getName();
    }

    /**
     * flush
     */
    public void flush() {
        Platform.runLater(() -> {
            setExpanded(false);
            getChildren().clear();
            init();
        });
    }

    public synchronized List<MenuItem> getMenus() {
        return menus;
    }

    /**
     * Batch add menu
     *
     * @param menuItems Add on behalf list
     */
    protected void addMenuItem(final MenuItem... menuItems) {
        this.menus.addAll(Arrays.asList(menuItems));
    }

    /**
     * Add menu to specified location
     *
     * @param index position index
     * @param item  Menu to be added
     */
    protected void addMenuItem(final int index, final MenuItem item) {
        if (!menus.contains(item)) {
            menus.add(index, item);
        }
    }

    /**
     * Add a single menu to the list
     *
     * @param item Menu to be added
     */
    protected void addMenuItem(final MenuItem item) {
        if (!menus.contains(item)) {
            menus.add(item);
        }
    }

    protected void removeMenu(MenuItem item) {
        this.menus.remove(item);
    }

    public boolean isLoading() {
        return loading.get();
    }

    public BooleanProperty loadingProperty() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading.set(loading);
    }

    public ConnectionParam getParam() {
        return param.get();
    }

    public ObjectProperty<ConnectionParam> paramProperty() {
        return param;
    }

    public void setParam(ConnectionParam param) {
        this.param.set(param);
    }
}
