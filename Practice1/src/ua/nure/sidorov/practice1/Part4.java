package ua.nure.sidorov.practice1;

public class Part4{

	public static int nod(int fi, int si) {
 	
        	int min;
        	int nod = 0;
        	if (fi > si) {
            		min = si;
        	} else {
            		min = fi;
        	}
        	for (int count = 1; count <= min; count++) {
            		if (fi % count == 0 && si % count == 0) {
                		if (count > nod) {
                    			nod = count;
                		}
            		}
        	}
        	return (fi*si)/nod;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			return;
		}
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int nod = nod(x, y);
		System.out.println("nod x y " + nod);
	}
}