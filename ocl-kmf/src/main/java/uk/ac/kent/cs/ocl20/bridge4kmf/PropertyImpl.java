package uk.ac.kent.cs.ocl20.bridge4kmf;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class PropertyImpl implements Property {
	/** Construct a Property */
	public PropertyImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement sf, OclProcessor proc) {
		property = sf;
		processor = proc;
		type = getType();
		name = getName();
	}
	protected OclProcessor processor;

	/** Wrapped UML correspondent */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement property;
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement getImpl() {
		return property;
	}
	public void setImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement property) {
		this.property = property;
	}

	/** Get Property Type */
	Classifier type = null;
	protected boolean isMany(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier umlType, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity mul) {
		if (mul!=null) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.MultiplicityRange range = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.MultiplicityRange)mul.getRange().iterator().next();
			int lower = range.getLower().intValue();
			int upper = range.getUpper().intValue();
			// Upper = *
			if (upper == -1) return true;
			// Optional element
			else if (lower == 0 && upper == 1) ;
			else if (lower < upper) return true;
			else if (lower == upper && lower > 1) return true;
		}
//		if (umlType != null) {
//			String colls = "Set Sequence Bag MutableSet MutableSequence MutableBag Collection List Set";
//			String typeName = umlType.getName().getBody_();
//			return colls.indexOf(typeName) != -1; 
//		}
		return false;
	}
	public Classifier getType() {
		if (property != null) {
			// Attribute
			if (property instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature) {
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature attrib = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature)property;
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier umlType = attrib.getType();
				type = (uk.ac.kent.cs.ocl20.semantics.bridge.Classifier)processor.getBridgeFactory().buildClassifier(umlType);
				boolean isOrdered = attrib.getOrdering() == uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind$Class.ORDERED;
				boolean isMany = isMany(umlType, attrib.getMultiplicity());
				boolean isUnique = false;
				if (isMany) {
					if (isOrdered)
						if (isUnique) type = processor.getTypeFactory().buildOrderedSetType(type);
						else type = processor.getTypeFactory().buildSequenceType(type);
					else 
						if (isUnique) type = processor.getTypeFactory().buildSetType(type);
						else type = processor.getTypeFactory().buildBagType(type);
				} else {		
					type = (uk.ac.kent.cs.ocl20.semantics.bridge.Classifier)processor.getBridgeFactory().buildClassifier(umlType);
				}
			// Association end
			} else {
				// Compute type
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd assocEnd  = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd)property;
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier umlType = assocEnd.getType();
				type = (uk.ac.kent.cs.ocl20.semantics.bridge.Classifier)processor.getBridgeFactory().buildOclModelElementType((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)umlType);
				boolean isOrdered = assocEnd.getOrdering() == uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind$Class.ORDERED;
				boolean isMany = isMany(umlType, assocEnd.getMultiplicity());
				boolean isUnique = false;
				if (isMany) {
					if (isOrdered)
						if (isUnique) type = processor.getTypeFactory().buildOrderedSetType(type);
						else type = processor.getTypeFactory().buildSequenceType(type);
					else 
						if (isUnique) type = processor.getTypeFactory().buildSetType(type);
						else type = processor.getTypeFactory().buildBagType(type);
				} else {		
					type = (uk.ac.kent.cs.ocl20.semantics.bridge.Classifier)processor.getBridgeFactory().buildClassifier(umlType);
				}
			}
		}
		return type; 
	}

	/** Set Property Type */
	public void setType(Classifier type) {
		if (property == null) this.type = type;
	}

	/** Get Name from implementation */
	protected String name = null;
	/** Get property name for an attribute */
	public static String getPropertyName(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature attrib) {
		String name = attrib.getName().getBody_();
		return name;
	}
	/** Get property name for an association end */
	public static String getPropertyName(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd aend) {
		String name = null;
		if (aend.getName() == null) {
			StringBuffer nameB = new StringBuffer(aend.getType().getName().getBody_());
			nameB.setCharAt(0, Character.toLowerCase(nameB.charAt(0)));
			name = new String(nameB);
		} else {
			name = aend.getName().getBody_();
			if (name.equals("")) {
				StringBuffer nameB = new StringBuffer(aend.getType().getName().getBody_());
				nameB.setCharAt(0, Character.toLowerCase(nameB.charAt(0)));
				name = new String(nameB);
			}
		}
		return name;
	}
	public String getName() {
		if (property != null) {
			if (property instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature) {
				name = getPropertyName((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature)property);
			} else {
				name = getPropertyName((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd)property);
			}
		}
		return name;
	}

	/** Set Name */
	public void setName(String name) {
		if (property == null) this.name = name;	
	}

	/** Get delegate */
	public Object getDelegate() {
		return this.getClass();
	}
	
	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** ToString */
	public String toString() {
		return "Property("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new PropertyImpl(property, processor);
	}
}
