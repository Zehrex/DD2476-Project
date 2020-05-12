3
https://raw.githubusercontent.com/BanqiJane/Bilibili_Danmuji/master/Bilibilidanmuji/src/main/java/xyz/acproject/danmuji/conf/WebSocketProxy.java
package xyz.acproject.danmuji.conf;

import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebSocketProxy extends Websocket {

	private static Logger LOGGER = LogManager.getLogger(WebSocketProxy.class);
	public WebSocketProxy(String url) throws URISyntaxException, InterruptedException {
		super(url);
		LOGGER.debug("Connecting...........................................");
		// TODO 自动生成的构造函数存根
		super.connectBlocking();
		LOGGER.debug("Connecting Success");
	}

}
