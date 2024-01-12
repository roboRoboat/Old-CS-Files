package a8;

public class Debugs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*for (int i = 0; i < 10; i++) {
			System.out.println(fib(i));
		}*/
		System.out.println(fib(7));
		
		
		
	}
	
	public static int fib(int n) {
	    int f = 0;
	    int g = 1;
	    int h = f; 

	    for (int i = 1; i <= n; i++) {
	    	h = f; 
	        f += g;
	        g = h;
	    } 
	    return f;
    
    }  

}
