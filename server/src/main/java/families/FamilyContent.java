package families;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the whole content of a particular family
 * It contains the Family class, Father class and a list of children for this family
 */ 
public class FamilyContent {
	private Family family;
	private Father father;
	private List<Child> children;
	
	public FamilyContent(Family family, Father father) {
		this.family = family;
		this.father = father;
		this.children = new ArrayList<Child>();
	}
	
	public Family getFamily() {
		return family;
	}
	
	public void setFamily(Family family) {
		this.family = family;
	}
	
	public Father getFather() {
		return father;
	}
	
	public void setFather(Father father) {
		this.father = father;
	}
	
	public List<Child> getChildren() {
		return children;
	}
	
	public void setChildren(List<Child> children) {
		this.children = children;
	}

/**
 *  Adds a child to the family's children list inside the FamilyContent class
 */
	public void addChild(Child child) {
		if (this.children.contains(child)) { return;} 
		this.children.add(child);
	}
}
