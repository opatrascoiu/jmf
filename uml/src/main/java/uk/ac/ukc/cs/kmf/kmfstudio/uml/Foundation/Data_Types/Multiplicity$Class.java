/**
 *
 *  Class Multiplicity$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

public class Multiplicity$Class
implements
	Multiplicity,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable
{
	/** Default constructor */
	public Multiplicity$Class() {
		//--- Set property 'range' from 'Multiplicity' ---
		this.range = new java.util.LinkedHashSet();
	}


	/** Property 'range' from 'Multiplicity' */
	protected java.util.Set range;
	/** Get property 'range' from 'Multiplicity' */
	public java.util.Set getRange() {
		return range;
	}
	/** Set property 'range' from 'Multiplicity' */
	public void setRange(java.util.Set range) { 
		this.range = range;
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
		String strId = "uml.Foundation.Data_Types.Multiplicity";
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
		java.util.Iterator rangeIt = this.range.iterator();
		while (rangeIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.MultiplicityRange rangeObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.MultiplicityRange)rangeIt.next();
			if (rangeObj != null)
				rangeObj.setMultiplicity(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		Multiplicity$Class obj = new Multiplicity$Class();
		obj.range = range==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.range).clone();
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.MultiplicityVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}