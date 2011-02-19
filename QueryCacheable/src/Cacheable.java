//cacheable value.

public class Cacheable<T> {
	private T value = null;
	private boolean valid = false;
	private Supplier<T> valueSupplier;
	
	public Cacheable (Supplier<T> valueSupplier){
		this.valueSupplier = valueSupplier;
	}
	
	public Cacheable (T initialValue, Supplier<T> valueSupplier){
		this(valueSupplier);
		this.value = initialValue;
		this.valid = true;
	}
	
	//get the value of this cacheable
	public synchronized T get (){
		if (!this.valid){
			this.value = this.valueSupplier.get();
			this.valid = true;
		}
		
		return this.value;
	}
	
	//invalidates the value of this cacheable
	public synchronized void invalidate (){
		this.valid = false;
	}
}
