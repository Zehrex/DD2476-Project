18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/component/BaseTab.java
package com.openjfx.database.app.component;

import com.openjfx.database.app.model.BaseTabMode;
import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 *
 *
 * base tab
 *
 * @since 1.0
 * @author yangkui
 *
 */
public class BaseTab<T extends BaseTabMode> extends Tab {

    protected T model;

    public BaseTab(T model) {
        this.model = model;
    }

    @Override
    public Node getStyleableNode() {
        return null;
    }

    public T getModel() {
        return model;
    }
}
