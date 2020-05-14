18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/utils/TableDataUtils.java
package com.openjfx.database.app.utils;

import com.openjfx.database.app.model.TableSearchResultModel;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import static com.openjfx.database.common.config.StringConstants.NULL;

/**
 * Table data deal tool class
 *
 * @author yangkui
 * @since 1.0
 */
public class TableDataUtils {

    public static List<TableSearchResultModel> findWithStr(final ObservableList<ObservableList<StringProperty>> items, final String keyword) {
        var list = new ArrayList<TableSearchResultModel>();
        var rowIndex = 0;

        for (var item : items) {
            var columnIndex = 0;
            for (var strProperty : item) {
                var str = strProperty.get();
                //if value is null skip
                if (!str.equals(NULL) && str.contains(keyword)) {
                    var model = new TableSearchResultModel();
                    model.setColumnIndex(columnIndex);
                    model.setRowIndex(rowIndex);
                    list.add(model);
                }
                columnIndex++;
            }
            rowIndex++;
        }
        return list;
    }
}
