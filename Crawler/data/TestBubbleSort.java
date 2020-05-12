2
https://raw.githubusercontent.com/likith22/DataStructures/master/TestBubbleSort.java
import DataStructures.BubbleSort;
public class TestBubbleSort {

	public static void main(String[] args) {
		BubbleSort bs = new BubbleSort();
		
		int[] arr = {1,1,21,1};
		
		bs.sort(arr);
		
		for(int i =0 ;i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}

	}

}
