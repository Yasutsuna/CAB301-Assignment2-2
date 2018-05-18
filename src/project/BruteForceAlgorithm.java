package project;

import java.util.Random;

public class BruteForceAlgorithm {
	
	public static void main(String[] args) {
		BruteForceAlgorithm test = new BruteForceAlgorithm();
		test.AutoCheck();
		//test.TestCaseCheck();
	}
	
	/*
	 * Used in manual test cases only.
	 */
	public void TestCaseCheck() {
		//int[] A = {200,400,499,550,600}; //Test Case 1 No rearrangement
		//int[] A = {200,300,100,500,400}; //Test Case 2 Arrangement needed
		//int[] A = {100,200,300,300,400}; //Test Case 3 Duplicates
		//int[] A = {100}; //Test Case 4 One number only
		//int[] A = {500,400,300,200,100}; //Test Case 5 Decreament
		//int[] A = {100,200,300,400,500,600}; //Test Case 6 No rearrangement Even
		//int[] A = {600,500,400,300,200,100}; //Test Case 7 Rearrangement Even
		int[] A = {550,500,450,400,350,300,250,200,150,100}; //Test Case 8 10 numbers
		
		System.out.println("Brute Foce Median result: " + BruteForceMedian(A));
		System.out.println("Partition Median result: " + Median(A));
	}
	
	/*
	 * Used to run massive test cases.
	 */
	public void AutoCheck() {
		int arraySize = 100000; //This size and up
		int [] A = new int[arraySize];
		
		int max = 10000;
		int min = 0;
		Random rand = new Random();
		
		for (int z = 0; z < A.length; z++) {
			A[z] = rand.nextInt(max + 1 - min) - min;
		}
		
		System.out.println(BruteForceMedian(A));
		System.out.println(Median(A));
	}
	
	/*
	 * Not efficient Algorithm
	 */
	public int BruteForceMedian(int[] A) {
		int numsmaller;;
		int numequal;;
		
		int n = A.length; //Need to plus 1 as count from 1 not 0
		int k = n/2;
		
		for (int i = 0; i < n-1; i++) {
			
			numsmaller = 0;
			numequal = 0;
			
			for (int j = 0; j < n-1; j++) {
				if (A[j] < A[i]) {
					numsmaller++;
				}
				
				else {
					if (A[j] == A[i]) {
						numequal++; 
					}
				}
			
			}
			if (numsmaller < k && k <= (numsmaller + numequal)) {
				return A[i];
			}
		}
		return 0; //We'll never get here...
	}
	
	/*
	 * Efficient Algorithm
	 */
	public int Median(int[] A) {
		int n = A.length;
		if (n == 1) {
			return A[0];
		}
		else {
			return Select(A, 0, n/2, n-1); //Needs to round down, check again later
		}
	}
	
	public int Select(int[]A, int l, int m, int h) {
		int pos = Partition(A, l, h);
		
		if (pos == m) {
			return A[pos];
		}
		
		if (pos > m) {
			return Select(A, l, m, pos - 1);
		}
		
		if (pos < m) {
			return Select(A, pos + 1, m, h);
		}
		return 0; //We'll never get here...
	}
	
	public int Partition(int[]A, int l, int h) {
		
		int pivotval = A[l];
		int pivotloc = l;
		
		for (int j = l + 1; j < h; j++) { //Check of h needs -1 or not
			if (A[j] < pivotval) {
				pivotloc = pivotloc + 1;
				
				int temp;
				temp = A[pivotloc];
				A[pivotloc] = A[j];
				A[j] = temp;
			}
			
		}
		int temp2;
		temp2 = A[l];
		A[l] = A[pivotloc];
		A[pivotloc] = temp2;
		
		return pivotloc;
	}
	
	/*
	 * Appending for results
	 */
	
	
}
