18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/config/DbPreference.java
package com.openjfx.database.app.config;

import com.openjfx.database.model.ConnectionParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * db配置
 *
 * @author yangkui
 * @since 1.0
 */
public class DbPreference {
    /**
     * 数据库连接信息
     */
    private static List<ConnectionParam> params = new ArrayList<>();

    /**
     * 删除连接
     *
     * @param uuid uuid
     */
    public static void deleteConnect(String uuid) {

        Optional<ConnectionParam> optional = params.stream()
                .filter(c -> c.getUuid().equals(uuid))
                .findAny();

        optional.ifPresent(param -> {
            params.remove(param);
            FileConfig.deleteCon(param.getUuid());
        });
    }

    /**
     * 更新连接信息
     *
     * @param param 更新后的连接信息
     */
    public static void updateConnection(ConnectionParam param) {
        int index = -1;

        for (int i = 0; i < params.size(); i++) {
            ConnectionParam par = params.get(i);
            if (par.getUuid().equals(par.getUuid())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            params.add(index, param);
        }
        FileConfig.updateConnection(param);
    }

    /**
     * 根据uuid获取连接信息
     *
     * @param uuid uuid
     * @return 返回连接信息
     */
    public static Optional<ConnectionParam> getConnectionParam(String uuid) {
        Objects.requireNonNull(uuid);
        return params.stream().filter(c -> c.getUuid().equals(uuid)).findAny();
    }

    /**
     * 新增连接信息
     *
     * @param param 连接参数
     */
    public static void addConnection(ConnectionParam param) {
        params.add(param);
    }

    public static List<ConnectionParam> getParams() {
        return params;
    }

    public static void setParams(List<ConnectionParam> params) {
        DbPreference.params = params;
    }
}
