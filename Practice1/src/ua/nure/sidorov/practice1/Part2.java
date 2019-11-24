package ua.nure.sidorov.practice1;

public class Part2{

	public static double subtraction(double x, double y) {
		return x-y;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			return;
		}
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		double substr = subtraction(x, y);
		System.out.println("x = x - y : " + substr);
	}
}