package project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BruteForceAlgorithm {
	int testnum = 1;
	long boCount = 0;
	long boCount2 = 0;
	
	public static void main(String[] args) {
		int arraySize = 10000;
		
		BruteForceAlgorithm test = new BruteForceAlgorithm();
		//test.TestCaseCheck();
		for (int i = 0; i < 50; i++) {
			test.AutoCheck(arraySize);
			arraySize = arraySize + 10000;
		}
	}
	
	/*
	 * Used in manual test cases only.
	 */
	public void TestCaseCheck() {
		//int[] A = {200,400,499,550,600}; //Test Case 1 No rearrangement
		//int[] A = {200,300,100,500,400}; //Test Case 2 Arrangement needed
		//int[] A = {100,200,300,300,400}; //Test Case 3 Duplicates
		//int[] A = {100}; //Test Case 4 One number only
		//int[] A = {100,200,300,400,500,600}; //Test Case 5 No rearrangement Even
		//int[] A = {600,500,400,300,200,100}; //Test Case 6 Rearrangement Even
		//int[] A = {550,500,450,400,350,300,250,200,150,100}; //Test Case 7 10 numbers
		int[] A = {-300,-200,0,100,200}; //Test Case 8 Negative Numbers
		
		
		System.out.println("Brute Foce Median result: " + BruteForceMedian(A));
		System.out.println("Partition Median result: " + Median(A));
	}
	
	/*
	 * Used to run massive test cases.
	 */
	public void AutoCheck(int arraySize) {
		long startTime;
		long endTime;
		
		long startTime2 = 0;
		long endTime2 = 0;
		
		int [] A = new int[arraySize];
		
		int max = 100000;
		int min = 0;
		Random rand = new Random();
		
		for (int z = 0; z < A.length; z++) {
			A[z] = rand.nextInt(max + 1 - min) - min;
		}
		
		startTime = System.nanoTime();
		System.out.println("Brute Force Test " + testnum + ": " + BruteForceMedian(A));
		endTime = System.nanoTime();
		
		startTime2 = System.nanoTime();
		System.out.println("Parition Test " + testnum + ": " + Median(A));
		endTime2 = System.nanoTime();
		
		System.out.println("BF Basic Opteration: " + boCount);
		System.out.println("P Basic Operation: " + boCount2);
		System.out.println("BF Execution Time: " + (endTime - startTime));
		System.out.println("P Execution Time: " + (endTime2 - startTime2) + "\n");
		
		try {
			Append(arraySize, startTime, endTime, startTime2, endTime2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boCount = 0;
		boCount2 = 0;
		testnum++;
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
					boCount++;
				}
				
				else {
					if (A[j] == A[i]) {
						numequal++; 
						boCount++;
					}
				}
			
			}
			if (numsmaller < k && k <= (numsmaller + numequal)) {
				return A[i];
			}
		}
		return A[0]; //If there is only one element.
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
				boCount2++;
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
	public void Append(int arraySize, long startTime, long endTime, long startTime2, long endTime2) throws IOException {
		FileWriter pw = new FileWriter("Result.csv", true);
		StringBuilder sb = new StringBuilder();
		
		sb.append(testnum);
		sb.append(',');
		sb.append(arraySize);
    	sb.append(',');
    	sb.append(endTime - startTime);
    	sb.append(',');
    	sb.append(endTime2 - startTime2);
    	sb.append(',');
    	sb.append(boCount);
    	sb.append(',');
    	sb.append(boCount2);
    	sb.append('\n');
    	
    	pw.write(sb.toString());
    	pw.close();
	}
	
}
