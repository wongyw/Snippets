//interface for objects with version history.
//valid versions should be >= 0 
public interface IVersioned {
	public void updateVersion (); //update the version
	public long getVersion(); //get the current version
}
