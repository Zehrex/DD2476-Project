2
https://raw.githubusercontent.com/likith22/DataStructures/master/TestSelectionSort.java
import DataStructures.SelectionSort;
public class TestSelectionSort {

	public static void main(String[] args) {
		SelectionSort ss = new SelectionSort();
		int arr[] = {3,10,1,4,2};
		
		ss.sort(arr);
		for(int  i= 0;i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		

	}

}
