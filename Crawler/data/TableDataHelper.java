18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/TableDataHelper.java
package com.openjfx.database.app;

import com.openjfx.database.app.model.TableDataChangeMode;
import com.openjfx.database.model.TableColumnMeta;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.openjfx.database.common.config.StringConstants.*;

public class TableDataHelper {
    /**
     * 将FX属性值装换为常规值
     *
     * @param list 属性值列表
     * @return 返回常规值列表
     */
    public static List<Object[]> fxPropertyToObject(List<ObservableList<StringProperty>> list) {
        return list.stream()
                .map(TableDataHelper::fxPropertyToObject)
                .collect(Collectors.toList());
    }

    /**
     * 将更改后的字段转换为map
     *
     * @param modes 更改值
     * @param metas table column meta
     * @param data  原始数据
     * @return 返回转换后的数据
     */
    public static List<Map<String, Object[]>> getChangeValue(List<TableDataChangeMode> modes, List<TableColumnMeta> metas, int keyIndex, ObservableList<ObservableList<StringProperty>> data) {
        List<Integer> rows = modes.stream().map(TableDataChangeMode::getRowIndex)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<Map<String, Object[]>> list = new ArrayList<>();

        int index = 0;
        for (Integer row : rows) {
            //获取行数据
            ObservableList<StringProperty> item = data.get(row);

            Object[] objects = fxPropertyToObject(item);

            Map<String, Object[]> map = new HashMap<>();

            List<TableDataChangeMode> models = modes.stream()
                    .filter(m -> m.getRowIndex() == row)
                    .collect(Collectors.toList());
            //默认key值没有发生改变
            map.put(KEY, new Object[]{objects[keyIndex]});
            //检测key值是否发生改变
            for (TableDataChangeMode model : models) {
                int colIndex = model.getColumnIndex();
                if (keyIndex == colIndex) {
                    map.put(KEY, new Object[]{model.getOriginalData()});
                    break;
                }
            }
            map.put(ROW, objects);
            list.add(map);
        }
        return list;
    }

    public static Object[] fxPropertyToObject(ObservableList<StringProperty> items) {
        Object[] obj = new Object[items.size()];
        for (int i = 0; i < items.size(); i++) {
            StringProperty object = items.get(i);
            obj[i] = singleFxPropertyToObject(object);
        }
        return obj;
    }

    public static Object singleFxPropertyToObject(StringProperty item) {
        Object object = item.getValue();
        Object target = null;
        if (!object.equals(NULL)) {
            target = object;
        }
        return target;
    }
}
