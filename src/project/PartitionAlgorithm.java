package project;

public class PartitionAlgorithm {
	/*
	 * A = Array
	 * l = 
	 * m = 
	 * h = 
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
		
		for (int j = 0; j < h; j++) { //Check of h needs -1 or not
			if (A[j] < pivotval) {
				pivotloc++;
				
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

}
