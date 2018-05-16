package project;

public class BruteForceAlgorithm {
	
	public static void main(String[] args) {
		BruteForceAlgorithm test = new BruteForceAlgorithm();
		test.Check();
	}
	
	public void Check() {
		int[] A = {30,10,50,40,20};
	//	int[] A = {10,520,230,640,7750};
		
		System.out.println("Foo " + BruteForceMedian(A));
		System.out.print("Fuu " + Median(A));
	}
	
	//Unefficient Algorithm
	public int BruteForceMedian(int[] A) {
		int n = A.length;
		int k = n/2;
		
		for (int i = 0; i < n-1; i++) {
			
			int numsmaller = 0;
			int numequal = 0;
			
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
	
	//More Efficient Algorithm
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
