18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/common/src/main/java/com/openjfx/database/common/VertexUtils.java
package com.openjfx.database.common;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;

/**
 * vertx toolkit 工具类
 *
 * @author yangkui
 * @since 1.0
 */
public class VertexUtils {
    /*
     * 在国内这个大环境下,禁用vertx默认dns解析器是有必要的
     */
    static {
        System.setProperty("vertx.disableDnsResolver", "true");
    }

    /**
     * vertx实例对象
     */
    private static final Vertx VERTX = Vertx.vertx();

    /**
     * vertx 文件系统
     */
    private static final FileSystem FILE_SYSTEM = VERTX.fileSystem();


    public static Vertx getVertex() {
        return VERTX;
    }

    public static void close() {
        VERTX.close();
    }

    public static FileSystem getFileSystem() {
        return FILE_SYSTEM;
    }

    public static EventBus eventBus() {
        return VERTX.eventBus();
    }

    /**
     * by event send message
     *
     * @param address Eventbus address;
     * @param message message content
     */
    public static void send(final String address, final JsonObject message) {
        VERTX.eventBus().send(address, message);
    }
}
