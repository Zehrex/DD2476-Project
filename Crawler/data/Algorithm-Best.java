2
https://raw.githubusercontent.com/jordantangy/Algorithm-Best/master/Algorithm-Best.java
//////////////*********Created by Jordan Tangy*****************\\\\\\\\\\\\\\\\

public class Best {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		int arr[] = {1,7,-13,2};
		//		int ans = Best(arr);
		//		System.out.println(ans);

//		int Best= Best(arr2);
//		int BestRound = Best_Round(arr2);
//		BestOrBestRound(Best, BestRound);
	
		
		int gas_stations[] = {3,6,2,8};
		int consume[] = {5,4,3,4};
		int result = Best_Round(GasStations(gas_stations, consume));
		System.out.println(result);
	}

/*
 * For a given array, this algorithm determines the 
 * maximum sum we can get from a straight subsequence of the array.
 * For example for the given array : [2,5,7,-9,6]
 * the subsequence [2,5,7] is the greatest subsequence because the sum of its number is 14.
 * We can't get better than 14 in terms of normal subsequence of the array.
 */
	public static int Best(int arr[]) {
		int max = 0;
		int h[] = new int[arr.length];
		h[0] = arr[0];
		if(h[0]<0) {
			h[0] = 0;
		}
		for (int i = 1; i < h.length; i++) {

			if(h[i-1] + arr[i] < 0 ) {

				continue;
			}
			else {
				h[i] = h[i-1] + arr[i];
			}
		}
		int max_index=0;
		int temp = 0;
		for (int i = 0; i < h.length-1; i = i+2) {
			temp = Math.max(h[i], h[i+1]);
			if(temp == h[i] && temp>max) {
				max_index = i;
				max = temp;
			}
			else if(temp == h[i+1] && temp>max) {
				max_index = i+1;
				max = temp;
			}


		}
		if(h.length%2 != 0) {
			temp = h[h.length-1];
			if(temp>max) {
				max = temp;
				max_index = h.length-1;
			}


		}
		find_Index_Best(arr, max_index, max);
		System.out.println(Arrays.toString(h));

		return max;

	}
/*
 * Calculates the start index and end index for 'Best Algorithm'
 */
	public static int[] find_Index_Best(int[] arr,int index,int max) {

		int ans[] = new int[2];
		int max_sum = 0;
		int start_index = 0;
		for (int i = index; i > 0; i--) {
			max_sum += arr[i];
			if(max_sum == max) {
				start_index = i;
				break;
			}
		}
		ans[0] = start_index;
		ans[1]=index;
		System.out.println("For 'Best Algo':starting index:"+ans[0]+" and the end index is:"+ans[1]);
		return ans;
	}

/*
 * For a given array, this algorithm calculates the maximum sum of an array as if the array was a "cycle".
 * In other words, if the array is [2,7,-9,-4,2], then the algorithm would output the number 11.
 * which is the sum of 2+7+2 ([2,7,x,x,2]). So only the first two numbers and the last one were added together as a "cycle" array. 
 */
	public static int Best_Round(int arr[]) {
		
		int sum_arr = 0;
		for (int i = 0; i < arr.length; i++) {
			sum_arr += arr[i];
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] *= -1;
		}
		System.out.println(Arrays.toString(arr));
		int max = 0;
		int h[] = new int[arr.length];
		h[0] = arr[0];
		if(h[0]<0) {
			h[0] = 0;
		}
		for (int i = 1; i < h.length; i++) {

			if(h[i-1] + arr[i] < 0 ) {

				continue;
			}
			else {
				h[i] = h[i-1] + arr[i];
			}
		}
		int max_index=0;
		
		int temp = 0;
		for (int i = 0; i < h.length-1; i = i+2) {
			temp = Math.max(h[i], h[i+1]);
			if(temp == h[i] && temp>max) {
				max_index = i;
				max = temp;
			}
			else if(temp == h[i+1] && temp>max) {
				max_index = i+1;
				max = temp;
			}

		}
		if(h.length%2 != 0) {
			temp = h[h.length-1];
			if(temp>max) {
				max = temp;
				max_index = h.length-1;
			}
		}
		find_index_Round(arr,max,max_index);
		int potential1 = sum_arr-(-max);
		max = potential1;

		return max;


	}
/*
 * Find the starting index and the ending index for 'Best Round Algorithm'
 */
	public static void find_index_Round(int arr[],int max,int index) {
		
		int sum = 0;
		int start_index = 0;
		int end_index = 0;
		for (int i = index; i>=0 ; i--) {
			sum += arr[i];
			if(sum == max) {
				
				if(i == 0) {
					end_index = arr.length-1;
				}
				else {
					end_index = i - 1 ;
				}
				if(start_index == arr.length-1) {
					start_index = 0;
				}
				else {
					start_index = index+1;
				}
			}
		}
		System.out.println("For Best-round Algorithm, the start index is: " +start_index+ "and the end index is"+end_index);
		
	}
/*
 * Function that tells you which of 'Best' or 'Best Round Algorithm' gives the best answer.
 */
	public static void BestOrBestRound(int Best,int BestRound) {

		int ans = Math.max(Best, BestRound);
		if (ans == Best) {
			System.out.println("Best Algorithm gives a better result than BestRound,");
			System.out.println("The result for Best is"+Best+"The result for BestRound is"+BestRound);
		}
		else if(ans == BestRound) {
			System.out.println("'BestRound Algorithm' gives a better result than 'Best',");
			System.out.println("The result for 'BestRound' is "+ BestRound +",the result for 'Best' is "+ Best);
		}
		else {
			System.out.println("Both Algorithms give the same result");
			System.out.println("The result for both of them is:"+BestRound);
		}
	}
/*
 * Algorithm that solves the problem of the Gas Station Problem, based ton the algorithms above.
 */
	public static int[] GasStations(int a[],int b[]) {
		
		int sum_a = 0;
		int sum_b = 0;
		int Nosolution[] = {-1};
		for (int i = 0; i < b.length; i++) {
			sum_a += a[i];
			sum_b += b[i];
		}
		
		if(sum_a < sum_b) {
			System.out.println("impossible to solve the problem");
			return Nosolution;
		}
		int h[] = new int[a.length];
		for (int i = 0; i < h.length; i++) {
			h[i] = a[i]-b[i];
		}
		
		
		return h;
		
	}

}
