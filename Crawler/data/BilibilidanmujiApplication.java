3
https://raw.githubusercontent.com/BanqiJane/Bilibili_Danmuji/master/Bilibilidanmuji/src/main/java/xyz/acproject/danmuji/BilibilidanmujiApplication.java
package xyz.acproject.danmuji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.acproject.danmuji.service.ClientService;

@SpringBootApplication
public class BilibilidanmujiApplication implements CommandLineRunner{
	@Autowired
	private ClientService clientService;
	public static void main(String[] args) {
		SpringApplication.run(BilibilidanmujiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO 自动生成的方法存根
		clientService.startService();
	}

}
