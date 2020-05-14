18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/skin/DataLabelSkin.java
package com.openjfx.database.app.skin;

import com.jfoenix.controls.JFXButton;
import com.openjfx.database.app.controls.DataLabel;
import com.sun.javafx.scene.control.LabeledText;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * DataLabel skin
 *
 * <p>{@link com.openjfx.database.app.controls.DataLabel}</p>
 *
 * @author yangkui
 * @since 1.0
 */
public class DataLabelSkin extends SkinBase<DataLabel> {
    /**
     * 用于显示文字信息
     */
    private final Text text;
    /**
     * 文字超过单元格范围显示扩展按钮，弹出window显示全部内容
     */
    private final JFXButton extendData;

    private HBox hBox = new HBox();

    public DataLabelSkin(DataLabel control) {
        super(control);
        extendData = new JFXButton("扩展");
        text = new Text(control.getText());
        hBox.getChildren().addAll(text, extendData);
        getChildren().add(hBox);
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);

    }

    @Override
    protected void layoutInArea(Node child, double areaX, double areaY,
                                double areaWidth, double areaHeight,
                                double areaBaselineOffset,
                                HPos halignment, VPos valignment) {
        layoutInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset,
                Insets.EMPTY, true, true, halignment, valignment);
    }
}
