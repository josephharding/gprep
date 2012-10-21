public class PrimeCentral {
	
	/**
	*	lists out the prime numbers that are less than the specified value
	*/
	public static int listPrimesLessThan(int num) {
		int lastPrime = 0;
        for(int i = 3; i < num; i=i+2) {
        	Boolean prime = true;
            for(int j = 3; j < i; j=j+2) {
            	if(i%j == 0 && i!=j) {
                	prime = false;
                    break;
                }
            }
            if(prime) {
				lastPrime = i;
                System.out.println(i + " is a prime number");
            }
            prime = true;
        }
		return lastPrime;
	}

	/**
	* get the next prime number after the specified value
	*/
	public static int getNextPrime(int afterNum) {
		int prime = 0;
		//int numLoops = 0;
		Boolean foundPrime = false;
		while(!foundPrime) {
 			foundPrime = true;
			for(int i = 3; i < afterNum; i++) {
                if(afterNum%i == 0) {
					//System.out.println("with afterNum: " + afterNum + ", and i: " + i + " =: " + afterNum%i);
                    foundPrime = false;
                    break;
                }
            }
			if(foundPrime) {
				prime = afterNum;
			} else {
				afterNum++;
			}
			//if(afterNum > 10000) {
			//	System.out.println("infinite tripped...");
			//	foundPrime = true;
			//}
			//numLoops++;
			//System.out.println("loop number " + numLoops);
		}
		return prime; 
	}
}
