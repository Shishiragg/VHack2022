package infytq;

import java.util.*;

class VHack {

	private static final long val = Integer.MAX_VALUE * (long) 10000;

	public static void main(String args[]) {
		
		//Dynamic Programming :- Storing already computed distances(cost) between characters
		long[][] distance = new long[26][26];
		
		//Taking User Input
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		int m = scan.nextInt();
		
		//Initializing the 2-d array
		for (int i = 0; i < distance.length; i++) {
			for (int j = 0; j < distance[i].length; j++) {
				if(i==j) {
					distance[i][j]=0;
				}
				else {
				distance[i][j] = val;
				}
			}
		}
		
		//Taking more user Inputs (costs for swapping characters)
		for (int i = 0; i < m; i++) {
			char x = scan.next().charAt(0);
			char y = scan.next().charAt(0);
			int cost = scan.nextInt();
			distance[y - 'a'][x - 'a'] = cost;
			distance[x - 'a'][y - 'a'] = cost;
			
		}
		
		
		//Applying Floyd Warshall Algorithm for shortest distance
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 26; k++) {
					if (distance[j][i] < val && distance[i][k] < val) {
						distance[j][k] = Math.min( distance[j][i] + distance[i][k] , distance[j][k]);
					}
				}
			}

		}
		
		//Now checking for Palindromes and the cost consumed 
		long result = 0;
		for (int i = 0; i < str.length() / 2; i++) {
			long min = val;
			for (char ch = 'a'; ch <= 'z'; ch++) {
				min = Math.min(min,distance[ch - 'a'][str.charAt(i) - 'a'] + distance[ch - 'a'][str.charAt(str.length() - i - 1) - 'a']);
			}
			result += min;
		}

		//Final result
		System.out.println(result);

	}
}
