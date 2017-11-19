package uk.ac.kent.cs.ocl20.standard.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.DataTypeImpl;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableDeclaration;
import uk.ac.kent.cs.ocl20.semantics.model.types.TupleType;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.semantics.model.types.VoidType;
import uk.ac.kent.cs.ocl20.standard.lib.OclTuple;

/**
 * @author dha
 *
 */
public class TupleTypeImpl extends DataTypeImpl implements TupleType {
	/** Construct a TupleType */
	public TupleTypeImpl(OclProcessor proc) {
		super(proc);
	}

	public void createOperations(TypeFactory tf) {
	}

	/** PartType field */
	protected List partType = new ArrayList();
	public List getPartType() {
		return partType;
	}
	public void setPartType(List partType) {
		this.partType = partType;
	}

	/** Check if this (a Tuple) conforms with c */
	public Boolean conformsTo(Classifier t2) {
		//--- T2 is undefined ---
		if (t2 instanceof VoidType)
			return Boolean.TRUE;
		//--- T2 is tuple ---
		if (t2 instanceof TupleType) {
			//--- Get the internal lists ---
			List l1 = ((TupleType)this).getPartType();
			List l2 = ((TupleType)t2).getPartType();
			//--- Check size ---
			if (l1.size() != l2.size()) return Boolean.FALSE;
			//--- Check each element from list
			Iterator i1 = l1.iterator();
			Iterator i2 = l2.iterator();
			while (i1.hasNext()) {
				//--- Get current element ---
				VariableDeclaration var1 = (VariableDeclaration)i1.next();
				VariableDeclaration var2 = (VariableDeclaration)i2.next();
				//--- Check name ---
				String name1 = var1.getName();
				String name2 = var2.getName();
				if (!name1.equals(name2)) return Boolean.FALSE;
				//--- Check type
				Classifier t11 = var1.getType();
				Classifier t21 = var2.getType();
				Boolean conforms = t11.conformsTo(t21);
				if (!conforms.booleanValue()) return Boolean.TRUE;
			}
			return Boolean.TRUE;
		//--- Check for parents --- 
		} else {
			return TypeConformance.isAssignableTo(this, t2) ? Boolean.TRUE : Boolean.FALSE;
		}
	}

	/** Get the delegate Class */
	public Object getDelegate() {
		return java.util.Map.class;
	}

	/** Get the implementation Class */
	public Class getImplClass() {
		return OclTuple.class;
	}

	/** Clone */
	public Object clone() {
		return super.clone();
	}

	/** Accept a Semantic Visitor */ 
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this, obj);
	}
	
	/** ToString */
	public String toString(){
		String str = "Tuple(";
		Iterator i = this.getPartType().iterator();
		while (i.hasNext()) {
			VariableDeclaration pt = (VariableDeclaration) i.next();
			str+=pt.getName()+":"+pt.getType();
			if (i.hasNext()) str+=", ";
		}
		return str+")";
	}
}
