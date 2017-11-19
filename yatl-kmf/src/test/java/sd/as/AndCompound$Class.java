/**
 *
 *  Class AndCompound$Class.java
 *
 *  Generated by KMFStudio at 18 February 2004 20:38:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class AndCompound$Class
extends
	CompoundDiagram$Class
implements
	AndCompound,
    sd.SdVisitable
{
	/** Default constructor */
	public AndCompound$Class() {
		//--- Set property 'name' from 'Diagram' ---
		this.name = null;
		//--- Set property 'compoundDiagram' from 'Diagram' ---
		this.compoundDiagram = null;
		//--- Set property 'children' from 'CompoundDiagram' ---
		this.children = new java.util.Vector();
		//--- Set property 'unitaryParts' from 'CompoundDiagram' ---
		this.unitaryParts = new java.util.Vector();
	}
	/** Specialized constructor */
	public AndCompound$Class(String name) {
		//--- Set property 'name' from 'Diagram' ---
		this.name = name;
		//--- Set property 'compoundDiagram' from 'Diagram' ---
		this.compoundDiagram = null;
		//--- Set property 'children' from 'CompoundDiagram' ---
		this.children = new java.util.Vector();
		//--- Set property 'unitaryParts' from 'CompoundDiagram' ---
		this.unitaryParts = new java.util.Vector();
	}


	/** The id */
	protected String id;
	/** Get the id */
	public String getId() {
		return id;
	}
	/** Set the id */
	public void setId(String id) {
		this.id = id;
	}

	/** Override toString */
	public String toString() {
		String strId = "sd.as.AndCompound";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId+" 'id-"+getId()+"'";
		else
			return strId+" '"+name+"-"+getId()+"'";
	}

	/** Delete the object */
	public void delete() {
		if (compoundDiagram != null)
			this.compoundDiagram.getChildren().remove(this);
		java.util.Iterator childrenIt = this.children.iterator();
		while (childrenIt.hasNext()) {
			sd.as.Diagram childrenObj = (sd.as.Diagram)childrenIt.next();
			if (childrenObj != null)
				childrenObj.setCompoundDiagram(null);
		}
		java.util.Iterator unitaryPartsIt = this.unitaryParts.iterator();
		while (unitaryPartsIt.hasNext()) {
			sd.as.UnitaryDiagram unitaryPartsObj = (sd.as.UnitaryDiagram)unitaryPartsIt.next();
			if (unitaryPartsObj != null)
				unitaryPartsObj.setCompoundDiagram(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		AndCompound$Class obj = new AndCompound$Class();
		obj.name = name==null ? null : this.name;
		obj.compoundDiagram = compoundDiagram==null ? null : this.compoundDiagram;
		obj.children = children==null ? null : (java.util.List)((java.util.Vector)this.children).clone();
		obj.unitaryParts = unitaryParts==null ? null : (java.util.List)((java.util.Vector)this.unitaryParts).clone();
		return obj;
	}

	/** Accept 'sd.as.AndCompoundVisitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}
