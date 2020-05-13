1
https://raw.githubusercontent.com/LeonRain/wangwang/master/wangwang/src/main/java/com/leon/wangwang/Bark.java
package com.leon.wangwang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.baidu.translate.demo.TransApi;

/**
 * 简述：该项目用来对源代码中的注释内容进行翻译，在不改变代码结构的前提下。
 * 项目功能说明：
 *     1.不改变源代码结构
 *     2.只翻译注解部分
 *     3.支持c、c++、c#、.NET、go、java、scala、kotlin、js、php等，暂不支持python
 *     4.当前只支持将其他语言翻译为中文
 * 版本：
 *     当前版本：1.0 
 *     时间：2020-5-7 11:29:41
 *     jdk版本：1.8
 * 后续待升级：
 *     1.使用优化：增加图形用户界面
 *     2.功能加强：增加对python及一些脚本语言的支持
 *     3.修改发现的其他问题
 *     4.支持翻译为其他语言
 * 使用方法：
 *     1.去百度翻译官网申请一个“通用翻译API”普通版（不限流量、限制每秒一次请求的免费版），得到APP_ID、SECURITY_KEY，填入类对应变量中。
 *     2.在main方法中填入输入输出路径，接的在路径结尾加个“\\”
 *     3.运行，等待运行完成
 * @author LeonRain
 *
 */
public class Bark {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "your_APP_ID";//申请后可以得到，填入你获取的该内容
    private static final String SECURITY_KEY = "your_SECURITY_KEY";//申请后可以得到，填入你获取的该内容

    public static void main(String[] args) {
    	//指定待翻译的源代码目录
    	String inputPath = "C:\\Users\\Leon\\Documents\\一些作品\\源码即教程\\源码\\功能性测试源码\\spark-part\\";//例子
    	//指定处理完的（翻译出的）结果文件存放路径
		String outputPath = "C:\\Users\\Leon\\Documents\\一些作品\\源码即教程\\输出\\功能性测试源码\\";//例子
		beachTranslateSourceCode(inputPath,outputPath);
    }
    
    //批量 代码文档翻译
	static void beachTranslateSourceCode(String inputPath,String outputPath) {
		File file = new File(inputPath);
		File [] files = file.listFiles();
		for(File f:files) {
			String name = f.getName();
			if(f.isDirectory()) {
				String newOutputPath = outputPath + "\\" + name +"\\";
				String newInputPath = inputPath + name + "\\";
				System.out.println("创建目录：" + newInputPath);
				new File(newOutputPath).mkdir();
				//递归调用当前方法
				beachTranslateSourceCode(newInputPath,newOutputPath);
				//定义支持的源码类型
			}else if(name.endsWith(".java") 
					|| name.endsWith(".scala")
					|| name.endsWith(".go")
					|| name.endsWith(".c")
					|| name.endsWith(".c++")
					||name.endsWith(".cs")
					|| name.endsWith(".kt")
					|| name.endsWith(".php")
					|| name.endsWith(".js")) {
				//扫描到这些类型的文件则翻译
				System.out.println("开始翻译 " + inputPath + name);
				translateSourceCode(inputPath + name,outputPath + "" + name);
			}else{
				//否则进行复制
				System.out.println("开始复制 " + inputPath + name);
				justCopyOtherSourceFile(inputPath + name,outputPath + "" + name);
			}
		}
	}

    //代码文档翻译
    static void translateSourceCode(String inputFile,String outputFile) {
		File file = new File(inputFile);
		String line = null;
		//整篇文档存入一个StringBuffer中，原来的每行内容现在以"\n"分隔
		StringBuffer originalAllLinesToOneSB = new StringBuffer();
		try {
			BufferedReader originalAllLines = new BufferedReader(new FileReader(file));
			while((line = originalAllLines.readLine()) != null) {
				originalAllLinesToOneSB.append("\n"+line);
			}
			originalAllLines.close();
			//将上面originalAllLinesToOneSB 存入一个字符串数组中
			String[] linesStrArray = originalAllLinesToOneSB.toString().split("\n");
			//在全文中将需要翻译的多行注释"/*   **内容**   */"整理至一行中
			StringBuffer linesSBN2One = new StringBuffer("");
			StringBuffer toTranslate = new StringBuffer("");
			for(String oneLine : linesStrArray) {
				if(oneLine.contains("*") && (!oneLine.contains("*/"))){
					toTranslate.append(oneLine);
				}else if(oneLine.contains("*/")) {
					toTranslate.append(oneLine);//添加最后一行内容
					String annotateN2One = toTranslate.toString().replace("/*", "xfuckx").replace("*/", "xduangx").replace("*", "").replace("xfuckx", "/*").replace("xduangx","*/");//切除许多*
					toTranslate = new StringBuffer("");
					linesSBN2One.append("\n"+annotateN2One);
				}else {
					linesSBN2One.append("\n"+oneLine);
				}
			}
			
			//将整理（多行注解整理到同一行）后的全文转为字符串数组
			String[] linesArr = linesSBN2One.toString().split("\n");
			//将所有需要翻译的内容放入一个StringBuffer ：all2TransSB
			StringBuffer all2TransSB = new StringBuffer("");
			for(String tmpStr:linesArr) {
				if(tmpStr.contains("//") || tmpStr.contains("/*")) {
					all2TransSB.append("\n"+tmpStr);
				}
			}
			
			//分段翻译，一段内容的长度在6K字节以内（我申请的百度翻译是免费版本，限制为每秒只能请求一次，每次请求的翻译文本量需在6000字节以内）
			//使用前请自行申请baidu翻译的“通用翻译API”，切记：选择免费版，不要选择有流量上限超限收费的那种。（钱多的请选择最高规格的版本，性能更屌，不过当前版本发挥不出来）
			//将所有待翻译的内容存入字符串数组
			String[] linesArr2 = all2TransSB.toString().split("\n");
			//用来控制一次的实际翻译量
			StringBuffer toTranslateOnce = new StringBuffer("");
			//用来存放翻译过的内容
			StringBuffer translatedSB = new StringBuffer("");
			for(String tmpStr:linesArr2) {
				if(toTranslateOnce.length() + tmpStr.length() > 6000) {
					//翻译、清空、再添加
					translatedSB.append("\n"+useBDTBatch(toTranslateOnce.toString()));//翻译
					toTranslateOnce = new StringBuffer("");//清空
					toTranslateOnce.append("\n"+tmpStr);//添加
				}else {
					toTranslateOnce.append("\n"+tmpStr);//添加
				}
			}
			//结束前翻译剩下的内容
			translatedSB.append("\n"+useBDTBatch(toTranslateOnce.toString()));//翻译
			//翻译之后的内容转为字符串数组
			String[] translatedArr = translatedSB.toString().split("\n");
			//将内容放回整理后的全文字符串数组linesArr中
			int indexOfTranslated = 2;//翻译后的结果集，初始值为需要跳过的翻译结果中的空结果
			for(int x = 0; x <linesArr.length; x ++) {
				if(linesArr[x].contains("//") || linesArr[x].contains("/*")) {
					linesArr[x] = translatedArr[indexOfTranslated];
					indexOfTranslated ++;
				}
			}
			//将写入了已翻译内容的全文字符串数组linesArr转为StringBuffer进而转为String，输出到文件
			StringBuffer toOutput = new StringBuffer("");
			for(String str:linesArr) {
				toOutput.append("\n"+str);
			}
			//输出到文件
			writeFile(toOutput.toString(),outputFile);
			System.out.println("finished !");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    //翻译多行内容
    static String useBDTBatch(String input) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        try {
			Thread.sleep(1000);//受限于当前的百度翻译账号版本，每秒只能请求1次，故在此进程睡眠1秒
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //一次翻译请求，服务器返回的结果
        String res = api.getTransResult(input, "auto", "zh");
        //用来存放翻译后的多行结果集
        StringBuffer out = new StringBuffer("");
        //结果解析成自定义结构的列表，这里定义了一个Resoult类
        List<Resoult> resultList = JSON.parseArray("["+res+"]", Resoult.class);
        //从resultList中提取出翻译结果字符串，作为输出
        for (Resoult ares : resultList) {
        	for(TransResult tr:ares.transResult) {
        		out.append("\n"+tr.dst);
        	}
        }
        return out.toString();
    }
    
    //文件写入
	static void writeFile(String content,String fileName) {
	    File file =new File(fileName);
		try {
			if(!file.exists()){
	        	file.createNewFile();
	        }
	        Writer fileWritter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fileWritter);
			bw.write(content);
		    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    //不需要翻译的内容直接复制写入
	static void justCopyOtherSourceFile(String inputFile,String outputFile) {
		File file = new File(inputFile);
		String line = null;
		StringBuffer thisCode = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				thisCode.append("\n"+line);
			}
			br.close();
			writeFile(thisCode.toString(),outputFile);
			System.out.println("copyed !");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	//翻译结果数据类
	static class Resoult{
		String from;
		String to;
		TransResult[] transResult;//多行翻译得到的结果集
		
		public String getFrom() {
			return from;
		}
		
		public void setFrom(String from) {
			this.from = from;
		}
		
		public String getTo() {
			return to;
		}
		
		public void setTo(String to) {
			this.to = to;
		}
		
		public TransResult[] getTrans_result() {
			return transResult;
		}
		
		public void setTrans_result(TransResult[] transResult) {
			this.transResult = transResult;
		}
		
		public Resoult(String from, String to, TransResult[] transResult) {
			super();
			this.from = from;
			this.to = to;
			this.transResult = transResult;
		}
		
		public Resoult() {
			super();
		}
		
	}
    
	//多行翻译中的一行翻译结果
	static class TransResult{
		//待翻译内容
		String src;
		//翻译结果
		String dst;
		
		public String getSrc() {
			return src;
		}
		
		public void setSrc(String src) {
			this.src = src;
		}
		
		public String getDst() {
			return dst;
		}
		
		public void setDst(String dst) {
			this.dst = dst;
		}

		public TransResult() {}
		
		public TransResult(String src,String dst) {
			this.src = src;
			this.dst = dst;
		}
	}
	
}