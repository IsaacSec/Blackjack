package session;

import org.springframework.stereotype.Component;

@Component
public class SessionBean {
	
	
	public static int SESSSION_COUNTER = 0;
	
	private int id;

	
	public SessionBean() {
		
		id = SessionBean.SESSSION_COUNTER++;
		
	}
	
	@Override
	public String toString() {
		
		return "I'm bean with id " + id;
	}
}
