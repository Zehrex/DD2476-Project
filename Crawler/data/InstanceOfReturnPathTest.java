97
https://raw.githubusercontent.com/Sipkab/jvm-tail-recursion/master/test/src/testing/sipka/jvm/tailrec/InstanceOfReturnPathTest.java
package testing.sipka.jvm.tailrec;

import java.util.Map;

import testing.saker.SakerTest;

@SakerTest
public class InstanceOfReturnPathTest extends TailRecOptimizerTestCase {
	@Override
	public void runTest(Map<String, String> parameters) throws Throwable {
		assertSuccessfulOptimization(TestMethods.class.getMethod("count", int.class), 10000000);
		assertSuccessfulOptimization(TestMethods.class.getMethod("count", long.class), 10000000);
		assertOptimizationResultEquals(TestMethods.class.getMethod("count", int.class), 0);
		assertOptimizationResultEquals(TestMethods.class.getMethod("count", int.class), 1);
		assertOptimizationResultEquals(TestMethods.class.getMethod("count", int.class), 10);
	}

	public static class TestMethods {
		public static Object count(int n) {
			if (n == 0) {
				return "123";
			}
			Object v = count(n - 1);
			if (v instanceof CharSequence) {
				String k = "";
			}
			return v;
		}

		public static Object count(long n) {
			if (n == 0) {
				return "123";
			}
			Object v = count(n - 1);
			if (v instanceof CharSequence) {
				String k = "";
			}
			return v;
		}
	}
}
