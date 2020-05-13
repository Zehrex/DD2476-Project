2
https://raw.githubusercontent.com/fadhilahramadhan/Android_Covid19Stats/master/app/src/main/java/fadhilah/ramadhan/covid19stats/util/service/AsyncTaskCompleteListener.java
/**
 * 
 */
package fadhilah.ramadhan.covid19stats.util.service;

/**
 * @author abiandono
 *
 */
public interface AsyncTaskCompleteListener<T> {
	public void onTaskComplete(T... params);
}
