//class for storing cacheable value.
//values are recomputed based on the versioning of the parent object. 
//This provides a cheap, conservative method of caching values, especially if writes to parent are infrequent compared to reads.

public final class VersionCacheable <T>{
	private IVersioned parent;
	private long versionValid = -1; //TODO replace -1 with some reserved version value?
	private Cacheable<T> cacheable;
	
	public VersionCacheable (Cacheable<T> cacheable, IVersioned parent){
		this.parent = parent;
		this.versionValid = -1;
		this.cacheable = cacheable;
	}
	
	//get the value
	public synchronized T get (){
		synchronized (this.parent){
			if (this.shouldRecompute()){
				this.versionValid = this.parent.getVersion();
				this.cacheable.invalidate();
			}
			
			return this.cacheable.get();
		}
	}
	
	//check if value needs to be recomputed
	public synchronized boolean shouldRecompute (){
		return (this.versionValid != this.parent.getVersion());
	}
}
