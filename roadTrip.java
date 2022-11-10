import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Name: Kaitlyn Rice
Class: CSCI 405
Assignment: Optimial Route

Runtime: O(n^2)
Space - O(n)
*/
public class roadTrip {
    /*
    Function: optimalRoute
    Parameters: m - ideal miles traveled/day
                hotels - distance of the hotels from stating point  

    Output: (1) optimized route between each of the hotels based on m
			(2) total minimum penalty 
    */
    public static void optimalRoute(int m, ArrayList<Integer> hotels) {
        int[] path = new int[hotels.size()];
		int[] penalty = new int[hotels.size()];

		for (int i = 0; i < hotels.size(); i++) {
			// set penalty - (x - m)^2
			penalty[i] = (int)Math.pow(hotels.get(i) - m, 2);
			for (int j = 0; j < i; j++) {
                // compare & update penalty & path
				if (penalty[j] + (int)Math.pow((hotels.get(i) - hotels.get(j)) - m, 2) < penalty[i]) {
                    path[i] = j + 1;
					penalty[i] = penalty[j] + (int)Math.pow((hotels.get(i) - hotels.get(j)) - m, 2);
				}
			}
		}

		// print output
		int i = path.length - 1;
        int stops = 0;
		String route = "";

		while(i >= 0){
			route = "Hotel" + (i + 1) + " " + route;
			i = path[i] - 1;
            stops++;
		}
		
		// requested output
        System.out.println(stops);
        System.out.println(penalty[hotels.size() - 1]);
		// double check 
		System.out.println(route);
	}

	public static void main(String[] args) {
		File input = new File(args[0]);
		try {
			Scanner sc = new Scanner(input);
			int m = sc.nextInt();
			ArrayList<Integer> hotels = new ArrayList<Integer>();

			while (sc.hasNextInt())
				hotels.add(sc.nextInt());

			optimalRoute(m, hotels);
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		}
	}
}