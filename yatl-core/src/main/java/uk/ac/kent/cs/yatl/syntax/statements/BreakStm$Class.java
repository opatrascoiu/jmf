/**
 *
 *  Class BreakStm$Class.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl.syntax.statements;

import uk.ac.kent.cs.yatl.YatlVisitable;
import uk.ac.kent.cs.yatl.YatlVisitor;

public class BreakStm$Class
extends
	Statement$Class
implements
	BreakStm,
    YatlVisitable
{
	/** Default constructor */
	public BreakStm$Class() {
		//--- Set property 'location' from 'Statement' ---
		this.location = null;
		//--- Set property 'loopStm' from 'Statement' ---
		this.loopStm = null;
		//--- Set property 'owner' from 'Statement' ---
		this.owner = null;
		//--- Set property 'statement' from 'Statement' ---
		this.statement = null;
		//--- Set property 'ifStm' from 'Statement' ---
		this.ifStm = null;
		//--- Set property 'compoundStm' from 'Statement' ---
		this.compoundStm = null;
	}
	/** Specialized constructor */
	public BreakStm$Class(Object location) {
		//--- Set property 'location' from 'Statement' ---
		this.location = location;
		//--- Set property 'loopStm' from 'Statement' ---
		this.loopStm = null;
		//--- Set property 'owner' from 'Statement' ---
		this.owner = null;
		//--- Set property 'statement' from 'Statement' ---
		this.statement = null;
		//--- Set property 'ifStm' from 'Statement' ---
		this.ifStm = null;
		//--- Set property 'compoundStm' from 'Statement' ---
		this.compoundStm = null;
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
		String strId = "syntax.statements.BreakStm";
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
			if (loopStm != null)
			this.loopStm.setBody(null);
			if (owner != null)
			this.owner.setStatement(null);
			if (ifStm != null)
			this.ifStm.setElseStm(null);
		if (compoundStm != null)
			this.compoundStm.getBody().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		BreakStm$Class obj = new BreakStm$Class();
		obj.location = location==null ? null : this.location;
		obj.loopStm = loopStm==null ? null : this.loopStm;
		obj.owner = owner==null ? null : this.owner;
		obj.statement = statement==null ? null : this.statement;
		obj.ifStm = ifStm==null ? null : this.ifStm;
		obj.compoundStm = compoundStm==null ? null : this.compoundStm;
		return obj;
	}

	/** Accept 'uk.ac.kent.cs.ktl.syntax.statements.BreakStm$Visitor' */
	public Object accept(YatlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
