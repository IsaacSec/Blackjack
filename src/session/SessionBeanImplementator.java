package session;

public class SessionBeanImplementator implements SessionBeanInterface {

	public static int COUNTER = 0;
	
	private int id;
	
	public SessionBeanImplementator() {
		
		id = SessionBeanImplementator.COUNTER++;
		
	}
	
	@Override
	public void printMe() {
		// TODO Auto-generated method stub
		
		System.out.println("I'm bean with id "  + id  + " of " + SessionBeanImplementator.COUNTER);
		
	}

	
	
}
