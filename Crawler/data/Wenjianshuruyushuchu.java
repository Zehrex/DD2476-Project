2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Wenjianshuruyushuchu.java
package ray;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class Wenjianshuruyushuchu {
	public static void main(String[] args) throws IOException{//抛出异常
		File file=new File("scores.txt");//创建文件
		if(file.exists()) System.out.println("file already exists");//检测文件是否存在
		try(PrintWriter output=new PrintWriter(file);){//写入文件
			output.print("John T Smith ");
			output.println(90);
			output.print("Eric K Jones ");
			output.println(85);
		}
		Scanner input=new Scanner(file);
		while(input.hasNext()){//读取文件
			String firstName=input.next();
			String mi=input.next();
			String lastName=input.next();
			
			String score =input.next();
			
			System.out.println(firstName+" "+mi+" "+lastName+" "+score);
		}
		input.close();//关闭文件
		}
}
