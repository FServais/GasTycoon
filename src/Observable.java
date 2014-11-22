/**
 * 
 * 
 */

public abstract class Observable {
	private Observer observer; 
	
	public void notify_obs(){
		observer.update();
	}
}
