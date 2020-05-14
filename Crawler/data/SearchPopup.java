18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/component/SearchPopup.java
package com.openjfx.database.app.component;

import com.jfoenix.controls.JFXButton;
import com.openjfx.database.common.Handler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import static com.openjfx.database.app.utils.AssetUtils.getLocalImage;

/**
 * <p>This component provides basic search function, search completion keyword callback and up and down coefficient callback</p>
 *
 * @author yangkui
 * @since 1.0
 */
public class SearchPopup extends HBox {
    /**
     * style sheet
     */
    private final static String STYLE_SHEET = "css/search_popup.css";
    /**
     * icon size
     */
    private static final double ICON_WIDTH = 0x14;
    private static final double ICON_HEIGHT = 0x14;

    private final static Image UP_ICON = getLocalImage(ICON_WIDTH, ICON_HEIGHT, "up_icon.png");
    private final static Image DOWN_ICON = getLocalImage(ICON_WIDTH, ICON_HEIGHT, "down_icon.png");
    private final static Image CLOSE_ICON = getLocalImage(ICON_WIDTH, ICON_HEIGHT, "close.png");

    private final TextField textField = new TextField();
    private final Label label = new Label("0 result");

    private final SimpleIntegerProperty indexProperty = new SimpleIntegerProperty(-1);

    private int searchMax = 0;

    private EventHandler<ActionEvent> closeHandler;

    /**
     * <p>The display forms of search components are divided into simple and complex</p>
     *
     * @author yangkui
     * @since 1.0
     */
    private enum SearchPopupModel {
        /**
         * <p>Simple mode only includes simple search box and control composition</p>
         */
        SIMPLE,
        /**
         * <p></>In addition, it also has regular and other advanced syntax search display</p>
         */
        COMPLEX
    }

    /**
     * Create a simple search box
     *
     * @return SearchPopup instance
     */
    public static SearchPopup simplePopup() {
        return new SearchPopup(SearchPopupModel.SIMPLE);
    }

    /**
     * Create a  complex search box
     *
     * @return SearchPopup instance
     */
    public static SearchPopup complexPopup() {
        return new SearchPopup(SearchPopupModel.COMPLEX);
    }

    /**
     * Private methods do not allow external calls
     *
     * @param model Search box presentation
     */
    private SearchPopup(SearchPopupModel model) {
        var up = new JFXButton();
        var down = new JFXButton();
        var close = new JFXButton();
        var lBox = new HBox();
        var rBox = new HBox();

        close.setGraphic(new ImageView(CLOSE_ICON));
        up.setGraphic(new ImageView(UP_ICON));
        down.setGraphic(new ImageView(DOWN_ICON));

        setAlignment(Pos.CENTER);
        lBox.getChildren().add(textField);
        if (model == SearchPopupModel.COMPLEX) {
            lBox.getChildren().addAll(label, up, down);
        }
        rBox.getChildren().addAll(close);

        HBox.setHgrow(textField, Priority.ALWAYS);

        HBox.setHgrow(lBox, Priority.ALWAYS);

        lBox.getStyleClass().add("left-box");
        rBox.getStyleClass().add("right-box");

        getChildren().addAll(lBox, rBox);

        getStyleClass().add("search-popup");

        getStylesheets().add(STYLE_SHEET);

        close.setOnAction(e -> {
            var root = getParent();
            if (root instanceof BorderPane) {
                ((BorderPane) root).setTop(null);
            }
            if (root instanceof VBox) {
                ((VBox) root).getChildren().remove(this);
            }
            if (closeHandler != null) {
                closeHandler.handle(e);
            }
        });

        up.setOnAction(e -> {
            var index = getIndexProperty();
            if (index >= 1) {
                setIndexProperty(--index);
            }
        });
        down.setOnAction(e -> {
            var index = getIndexProperty();
            if (index < searchMax - 1) {
                setIndexProperty(++index);
            }
        });
        textField.setOnKeyPressed(e -> {
            //hidden search box
            if (e.getCode() == KeyCode.ESCAPE) {
                close.fire();
            }
        });
    }

    /**
     * register input text change callback.
     *
     * @param handler callback handler
     */
    public void textChange(final Handler<Integer, String> handler) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (getIndexProperty() != -1) {
                setIndexProperty(-1);
            }
            if (newValue != null && newValue.length() > 0) {
                var number = handler.handler(newValue);
                searchMax = number;
                label.setText(number + "条结果");
            } else {
                label.setText("0条结果");
            }
        });
    }

    /**
     * Register events that start when certain special keys are pressed
     *
     * @param value event handler
     */
    public void setSearchOnKeyPressed(EventHandler<? super KeyEvent> value) {
        textField.setOnKeyPressed(value);
    }


    public int getIndexProperty() {
        return indexProperty.get();
    }

    public SimpleIntegerProperty indexPropertyProperty() {
        return indexProperty;
    }

    public void setIndexProperty(int indexProperty) {
        this.indexProperty.set(indexProperty);
    }

    /**
     * register close event
     *
     * @param closeHandler handler body
     */
    public void setCloseHandler(EventHandler<ActionEvent> closeHandler) {
        this.closeHandler = closeHandler;
    }
}
