package trainproblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

enum ROUTE1 {
	CHN(0), SLM(350), BLR(550), KRN(900), HYB(1200), NGP(1600), ITJ(1900), BPL(2000), AGA(2500), NDL(2700), TVC(0),
	SRR(300), MAQ(600), MAO(1000), PNE(1400), PTA(3800), NJP(4200), GHY(4700);

	ROUTE1(Integer i) {
		this.distance = i;
	}

	final Integer distance;
}

enum ROUTE2 {
	TVC(0), SRR(300), MAQ(600), MAO(1000), PNE(1400), HYB(2000), NGP(2400), ITJ(2700), BPL(2800), PTA(3800), NJP(4200),
	GHY(4700), CHN(0), SLM(350), BLR(550), KRN(900), AGA(2500), NDL(2700);

	ROUTE2(Integer i) {
		this.distance = i;
	}

	final Integer distance;
}

public class Geektrust {

	public static void main(String[] args) throws FileNotFoundException {

		// Preparing distance map after Bhopal
		HashMap<String, Integer> trainDistanceMap = new HashMap<>();
		trainDistanceMap.put("NGP", 1600);
		trainDistanceMap.put("ITJ", 1900);
		trainDistanceMap.put("BPL", 2000);
		trainDistanceMap.put("AGA", 2500);
		trainDistanceMap.put("NDL", 2700);
		trainDistanceMap.put("PTA", 3800);
		trainDistanceMap.put("NJP", 4200);
		trainDistanceMap.put("GHY", 4700);

		// Scanning File
		File file = new File(args[0]);
		Scanner s = new Scanner(file);
		String train1 = s.nextLine();
		String train2 = s.nextLine();
		s.close();

		String[] stations1 = train1.split(" ");
		String[] stations2 = train2.split(" ");

		// preparing Train A for Arrival at Hyderabad
		ArrayList<ROUTE1> trainA = new ArrayList<>();
		for (int i = 2; i < stations1.length; i++) {
			ROUTE1 st = ROUTE1.valueOf(stations1[i]);
			if (st.distance >= ROUTE1.HYB.distance) {
				trainA.add(st);
			}
		}

		// Preparing Train B for arrival at Hyderabad
		ArrayList<ROUTE2> trainB = new ArrayList<>();
		for (int i = 2; i < stations2.length; i++) {
			ROUTE2 st = ROUTE2.valueOf(stations2[i]);
			if (st.distance >= ROUTE2.HYB.distance) {
				trainB.add(st);
			}
		}

		System.out.print("ARRIVAL TRAIN_A ENGINE ");
		for (ROUTE1 route : trainA) {
			System.out.print(route.name() + " ");
		}
		System.out.println();

		System.out.print("ARRIVAL TRAIN_B ENGINE ");
		for (ROUTE2 route : trainB) {
			System.out.print(route.name() + " ");
		}
		System.out.println();

		if (trainA.isEmpty() && trainB.isEmpty()) {
			System.out.println("JOURNEY_ENDED");
			return;
		}

		// Merging Both the trains
		ArrayList<String> trainAB = new ArrayList<String>();
		trainA.forEach(t -> trainAB.add(t.name()));

		trainB.forEach(t -> trainAB.add(t.name()));

		for (int k = 0; k < trainAB.size(); k++) {
			if (trainAB.get(k).equals("HYB")) {
				trainAB.remove(k);
			}
		}

		Comparator<String> comparator = (h1, h2) -> {
			if (trainDistanceMap.get(h1) == trainDistanceMap.get(h2)) {
				return 0;
			} else if (trainDistanceMap.get(h1) < trainDistanceMap.get(h2)) {
				return -1;
			} else {
				return 1;
			}
		};

		trainAB.sort(comparator.reversed());

		System.out.print("DEPARTURE TRAIN_AB ENGINE ENGINE ");

		trainAB.forEach(t -> System.out.print(t + " "));
	}
}
