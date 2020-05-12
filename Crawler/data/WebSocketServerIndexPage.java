1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/WebSocketServerIndexPage.java
package com.game.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @Author: wx
 * @Date: 上午 10:10 2019/12/24 0024
 * @Desc: 心跳页面
 * @version:
 */
public final class WebSocketServerIndexPage {

    private static final String NEWLINE = "\r\n";

    public static ByteBuf getContent(String webSocketLocation) {
        return Unpooled.copiedBuffer(
                "<html><head><title>Web Socket Test</title></head>" + NEWLINE +
                        "<body>" + NEWLINE +
                        "<script type=\"text/javascript\">" + NEWLINE +
                        "var socket;" + NEWLINE +
                        "if (!window.WebSocket) {" + NEWLINE +
                        "  window.WebSocket = window.MozWebSocket;" + NEWLINE +
                        '}' + NEWLINE +
                        "if (window.WebSocket) {" + NEWLINE +
                        "  socket = new WebSocket(\"" + webSocketLocation + "\");" + NEWLINE +
                        "  socket.onmessage = function(event) {" + NEWLINE +
                        "    var ta = document.getElementById('responseText');" + NEWLINE +
                        "    ta.value = ta.value + '\\n' + event.data" + NEWLINE +
                        "  };" + NEWLINE +
                        "  socket.onopen = function(event) {" + NEWLINE +
                        "    var ta = document.getElementById('responseText');" + NEWLINE +
                        "    ta.value = \"Web Socket opened!\";" + NEWLINE +
                        "  };" + NEWLINE +
                        "  socket.onclose = function(event) {" + NEWLINE +
                        "    var ta = document.getElementById('responseText');" + NEWLINE +
                        "    ta.value = ta.value + \"Web Socket closed\"; " + NEWLINE +
                        "  };" + NEWLINE +
                        "} else {" + NEWLINE +
                        "  alert(\"Your browser does not support Web Socket.\");" + NEWLINE +
                        '}' + NEWLINE +
                        NEWLINE +
                        "function send(message) {" + NEWLINE +
                        "  if (!window.WebSocket) { return; }" + NEWLINE +
                        "  if (socket.readyState == WebSocket.OPEN) {" + NEWLINE +
                        "    socket.send(message);" + NEWLINE +
                        "  } else {" + NEWLINE +
                        "    alert(\"The socket is not open.\");" + NEWLINE +
                        "  }" + NEWLINE +
                        '}' + NEWLINE +
                        "</script>" + NEWLINE +
                        "<form onsubmit=\"return false;\">" + NEWLINE +
                        "<input type=\"text\" name=\"message\" value=\"{cmd:1002,data: 212}\"/>" +
                        "<input type=\"button\" value=\"Send Web Socket Data\"" + NEWLINE +
                        "       onclick=\"send(this.form.message.value)\" />" + NEWLINE +
                        "<h3>Output</h3>" + NEWLINE +
                        "<textarea id=\"responseText\" style=\"width:500px;height:300px;\"></textarea>" + NEWLINE +
                        "</form>" + NEWLINE +
                        "</body>" + NEWLINE +
                        "</html>" + NEWLINE, CharsetUtil.US_ASCII);
    }

    private WebSocketServerIndexPage() {
        // Unused
    }
}
