

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Multipleknapsack {


	// Prints the items which are put
	// in a knapsack of capacity W
	static void printknapSack(int W, int wt[], int val[], int n, int index[][], int a) {
		int i, j;
		int k =0;
		int K[][] = new int[n + 1][W + 1];
		int z = 0;
		int p = 0;
		// Build table K[][] in bottom up manner
		for (i = 0; i <= n; i++) {
			for (j = 0; j <= W; j++) {
				if (i == 0 || j == 0)
					K[i][j] = 0;
				else if (wt[i - 1] <= j)
					K[i][j] = Math.max(val[i - 1] + K[i - 1][j - wt[i - 1]], K[i - 1][j]);
				else
					K[i][j] = K[i - 1][j];
			}
		}

		// stores the result of Knapsack
		int res = K[n][W];
		System.out.println(res);
		
		// checking whether items are available
		j = W;
		for (i = n; i > 0 && res > 0; i--) {

			// either the result comes from the top
			// (K[i-1][w]) or from (val[i-1] + K[i-1] [w-wt[i-1]]) as in Knapsack table. If
			// it comes from the latter one it means
			// the item is included.
			if (res == K[i - 1][j]) {
				index[k][a] = 0;
				z++;k++;
				continue;
			}

			else {
				// This item is included.
				index[k][a] = 1;
				z++;p++;k++;
				// Since this weight is included its
				// value is deducted
				res = res - val[i - 1];
				j = j - wt[i - 1];
			}
		}
		System.out.println("\n p:" + p);// count of numbers
	}
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public static void main(String[] args) throws FileNotFoundException {
		int val[] = new int[10];// { 3, 5, 2,4 };
		int wt[] = new int[10];// { 2, 3, 1,4 };
		int W = 0;
		int n = 0;// = val.length;

		// int[] dortluk = new int[4];

		File file = new File("seconds\\test2a.txt");

		// int [] sayilar = new int[2];
		String[] sayilar2 = new String[2];
		Scanner input = new Scanner(file);
		int i = 0;

		String line = input.nextLine();
		sayilar2 = line.split(" ");
		n = Integer.parseInt(sayilar2[0]);
		W = Integer.parseInt(sayilar2[1]);
		int kapasiteler[]  = new int[W];
		i++;
		while (input.hasNext()) {
			line = input.nextLine();
			// System.out.println(line);
			sayilar2 = line.split(" ");

			if(i == 1){
				int j;
				for(j = 1; j<=W; j++) {
					kapasiteler[j-1] = Integer.parseInt(sayilar2[j-1]);
					if(j == W) i++;
				}
				continue;
			}




			val[i - 2] = Integer.parseInt(sayilar2[0]);
			wt[i - 2] = Integer.parseInt(sayilar2[1]);

			i++;
		}
		int o = 0;

	//	printknapSack(int W, int wt[], int val[], int n, int index[]) {

		int index[][] = new int[n][W];
		for(i = 0; i < W; i++){
			printknapSack(kapasiteler[i], wt, val, n, index, i);
			System.out.println("--------------------------------");
		}
		//printknapSack(W, wt, val, n,index);
		int j;
		for(i = n-1; i >= 0; i--) {
			for(j = 0; j < W; j++){
				if(index[i][j] == 1)o++;

				System.out.print(index[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("\n o: " + o);
		input.close();

	}

}
