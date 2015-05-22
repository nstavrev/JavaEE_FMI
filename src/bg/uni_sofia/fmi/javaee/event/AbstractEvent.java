package bg.uni_sofia.fmi.javaee.event;

public abstract class AbstractEvent<T> {
	
	private T t;
	
	public AbstractEvent(T t) {
		this.t = t;
	}
	
	public T get() {
		return t;
	}
	
}
