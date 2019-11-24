package ua.nure.sidorov.practice1;

public class Part5{

	public static void main(String[] args) {
		if (args.length != 1) {
			return;
		}
		int x = Integer.parseInt(args[0]);
		int count = Integer.bitCount(x);
		System.out.println(count);
	}
}