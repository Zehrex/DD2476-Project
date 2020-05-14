97
https://raw.githubusercontent.com/Sipkab/jvm-tail-recursion/master/test/src/testing/sipka/jvm/tailrec/NumberConcatTest.java
package testing.sipka.jvm.tailrec;

import java.util.Map;

import testing.saker.SakerTest;

@SakerTest
public class NumberConcatTest extends TailRecOptimizerTestCase {

	@Override
	public void runTest(Map<String, String> parameters) throws Throwable {
		assertSuccessfulOptimization(TestMethods.class.getMethod("numbers", int.class, String.class), 10000, "");
	}

	public static class TestMethods {
		public static String numbers(int n, String s) {
			if (n == 0) {
				return s + "0";
			}
			return numbers(n - 1, s + n + ",");
		}
	}
}
