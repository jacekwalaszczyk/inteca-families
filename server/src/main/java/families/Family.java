package families;

/**
 * The Family class contains only the id of a family.
 * This id serves as a family's identifier which contain other classes
 * connected with a family
 */
public class Family {
    private int id;

    public Family() {
    	
    }
    
    public Family(int id) {
    	this.id = id;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
