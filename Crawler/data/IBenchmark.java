2
https://raw.githubusercontent.com/miricel/CO_Project/master/UPTBench/src/bench/IBenchmark.java
package bench;

import java.io.IOException;

public interface IBenchmark {
	
	void run();
	void run(Object... params) throws IOException;
	void initialize(Object... params);
	void clean() ;
	void cancel() ;
	public void warmUp() throws IOException;
	String getResult();
}
