import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class MergeSort {
	/**
	 * Thread that declares the lambda and then initiates the work
	 */

	public static int message_id = 0;

	public static JSONObject init(int[] array) {
		JSONArray arr = new JSONArray();
		for (var i : array) {
			arr.put(i);
		}
		JSONObject req = new JSONObject();
		req.put("method", "init");
		req.put("data", arr);
		return req;
	}

	public static JSONObject peek() {
		JSONObject req = new JSONObject();
		req.put("method", "peek");
		return req;
	}

	public static JSONObject remove() {
		JSONObject req = new JSONObject();
		req.put("method", "remove");
		return req;
	}

	public static void Test(int port, String host, char array) {
		JSONObject response = null;
		if (array == 'a') {
			int[] a = { 624, 5, 1, 10, 34, 23, 653, 3, 4, 56, 7, 8, 12, 230, 23, 2, 6, 52, 6, 17 };
			System.out.println("Sort array 'a'");
			System.out.println(host + ":" + port);
			response = NetworkUtils.send(port, host, init(a));
		} else if (array == 'b') {
			int[] b = new int[100];
			for (int i = 0; i < 100; i++) {
				b[i] = (int) ((Math.random() * 100));
			}
			response = NetworkUtils.send(port, host, init(b));
		} else if (array == 'c') {
			int[] c = new int[1000];
			for (int j = 0; j < 1000; j++) {
				c[j] = (int) ((Math.random() * 1000));
			}
			response = NetworkUtils.send(port, host, init(c));
		}

		System.out.println(response);
		response = NetworkUtils.send(port, host, peek());
		System.out.println(response);

		while (true) {
			response = NetworkUtils.send(port, host, remove());

			if (response.getBoolean("hasValue")) {
				System.out.println(response);
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		// all the listening ports in the setup
		// ArrayList<Integer> ports = new ArrayList<>();

		String node = args[0];
		int port = Integer.parseInt(args[1]);
		String host = args[2];
		if (node.equalsIgnoreCase("branch") || node.equalsIgnoreCase("master")) {
			
			if (node.equalsIgnoreCase("branch")) {
				new Thread(new Branch(port, host, port + 1, port + 2)).start();
			} else {
				new Thread(new Branch(port, host, port + 1, port + 4)).start();
			}
			System.out.println("Started");

			Scanner scan = new Scanner(System.in);
			boolean waiting = true;
			while (waiting) {
				System.out.println("Type 'done' when connections are established...");
				String input = scan.nextLine();
				if (input.equalsIgnoreCase("done")) {
					waiting = false;
				} else {
					waiting = true;
				}
			}

			// One branch / Two Sorters / Array 'a'
			long startTime = System.currentTimeMillis();
			Test(port, host, 'a');
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			System.out.println("RESULTS:");
			System.out.println("TEST : 1 branch / 2 sorters 20 Entry Array\nDuration: " + duration + " ms");

			// One branch / Two Sorters / Array 'b'
			startTime = System.currentTimeMillis();
			Test(port, host, 'b');
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			System.out.println("TEST : 1 Branch / 2 Sorters / 100 Entry Array\nDuration: " + duration + " ms");

			// One branch / Two Sorters / Array 'c'
			startTime = System.currentTimeMillis();
			Test(port, host, 'c');
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			System.out.println("TEST : 1 Branch / 2 Sorters / 1000 Entry Array\nDuration: " + duration + " ms");
		} else if (node.equalsIgnoreCase("sorter")) {
			new Thread(new Sorter(port)).start();
			System.out.println("Created new sorter");
		} else if (node.equalsIgnoreCase("solo")) {
			new Thread(new Sorter(port)).start();

			// One branch / Two Sorters / Array 'a'
			long startTime = System.currentTimeMillis();
			Test(port, host, 'a');
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			System.out.println("RESULTS:");
			System.out.println("TEST : 1 sorters 20 Entry Array\nDuration: " + duration + " ms");

			// One branch / Two Sorters / Array 'b'
			startTime = System.currentTimeMillis();
			Test(port, host, 'b');
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			System.out.println("TEST : 1 Sorters / 100 Entry Array\nDuration: " + duration + " ms");

			// One branch / Two Sorters / Array 'c'
			startTime = System.currentTimeMillis();
			Test(port, host, 'c');
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			System.out.println("TEST : 1 Sorters / 1000 Entry Array\nDuration: " + duration + " ms");
		} else {
			System.out.println("WRONG");
			System.exit(0);
		}

	}

}
