8
https://raw.githubusercontent.com/nataraz123/Spring/master/IOCProj62-LMI-FinalSolution/src/main/java/com/nt/beans/RequestHandler.java
package com.nt.beans;

public class RequestHandler {
	private static int count;
	
	public RequestHandler() {
		count++;
		System.out.println("RequestHandler::0-param  constructor::" +count);
	}
	
	
	public   void  handle(String data) {
		System.out.println("handling request using object ::"+count + "having data :::"+data +"-->"+this.hashCode());
	}

}
