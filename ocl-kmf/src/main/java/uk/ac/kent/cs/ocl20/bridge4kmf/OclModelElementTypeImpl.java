package uk.ac.kent.cs.ocl20.bridge4kmf;

import java.util.*;

import uk.ac.kent.cs.kmf.util.Naming;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.*;
import uk.ac.kent.cs.ocl20.standard.types.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Generalization;

/**
 * @author Octavian Patrascoiu
 *
 */

public class OclModelElementTypeImpl 
	extends OclAnyTypeImpl 
	implements OclModelElementType 
{
	/** Construct a Model Element Type */
	public OclModelElementTypeImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ class_, OclProcessor proc) {
		super(proc);
		this.processor = proc;
		this.class_ = class_;
		List oper = super.getOperations();
		BridgeFactory bf = proc.getBridgeFactory();
		TypeFactory tf = proc.getTypeFactory();
		oper.add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { this }));
		oper.add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { this }));
		List oclAnyOper = ((TypeFactoryImpl)tf).OCLANY_TYPE.getOperations();
		oper.addAll(oclAnyOper);
	}
	protected OclProcessor processor;

	/** Wrapped UML correspondent */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ class_;
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ getImpl() {
		return class_;
	}
	public void setImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ class_) {
		this.class_ = class_;
	}

	/** Search for a given Property */
	Map _properties = new HashMap();
	/** Get property name for an association end */
	protected static String getPropertyName(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd aend) {
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
	public Property lookupProperty(String name) {
		Property prop = (Property)_properties.get(name);
		// Search for attribute
		uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement f = null;
		// Compute all superclasess and this
		List superClasses = OclModelElementTypeImpl.allSuperClasses(class_);
		for(int k = superClasses.size()-1; k>=0; k--) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ class_ = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)superClasses.get(k);
			Iterator i = class_.getFeature().iterator();
			while (i.hasNext()) {
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature f2 = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature)i.next();
				if (f2.getName() != null && f2.getName().getBody_().equals(name)) {
					f = f2;
					break;
				}
			}
			if (f != null && f instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature) {
				return processor.getBridgeFactory().buildProperty((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature)f);
			}
			// Search for associations
			i = ((KmfOclProcessorImpl)processor).associations.iterator();
			while (i.hasNext()) {
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association assoc = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association)i.next();
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd aend1 = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd)assoc.getConnection().get(0);
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd aend2 = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd)assoc.getConnection().get(1);
				if (aend1.getType() == class_ && aend2.getIsNavigable().booleanValue()) {
					String assocName = getPropertyName(aend2);
					if (name.equals(assocName)) {
						f = aend2;
						break;
					}
				}
				if (aend2.getType() == class_ && aend1.getIsNavigable().booleanValue()) {
					String assocName = getPropertyName(aend1);
					if (name.equals(assocName)) {
						f = aend1;
						break;
					}
				}
			}
			if (f != null && f instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd) {
				return processor.getBridgeFactory().buildProperty((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd)f);
			}
		}
		return null;
	}
	public ModelElement lookupOwnedElement(String name) {
		return lookupProperty(name);
	}

	/** Search for a given Operation */
	public Operation lookupOperation(String name, List types) {
		// Compute all superclasess and this
		List superClasses = OclModelElementTypeImpl.allSuperClasses(class_);
		for(int l = superClasses.size()-1; l>=0; l--) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ class_ = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)superClasses.get(l);
			// Search an operation
			Iterator i = class_.getFeature().iterator();
			while (i.hasNext()) {
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement elem = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement)i.next();
				if (elem instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation) {
					// Compute parameter types 
					uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation oper = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation)elem;
					List operTypes = new Vector();
					Classifier ret = null;
					Iterator j = oper.getParameter().iterator();
					while (j.hasNext()) {
						uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter param = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter)j.next();
						if (param.getKind() != uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ParameterDirectionKind$Class.RETURN) {
							uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier typeUml = param.getType();
							operTypes.add(processor.getBridgeFactory().buildClassifier(typeUml));
						} else {
							uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier typeUml = param.getType();
							ret = processor.getBridgeFactory().buildClassifier(typeUml);
						}
					}
					String operName = oper.getName().getBody_();			
					if (operName.equals(name) && types.size() == operTypes.size()) {
						boolean equal = true;
						Iterator i1 = types.iterator();
						Iterator i2 = operTypes.iterator();
						while (i1.hasNext()) {
							uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier t1 = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)i1.next();
							uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier t2 = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)i2.next();
							if (!t1.equals(t2)) equal = false;
						}
						if (equal) {
							Classifier[] array = null;
							if (types != null) {
								array = new Classifier[types.size()];
								for (int k = 0; k < types.size(); k++) {
									Classifier paramType = (Classifier) types.get(k);
									array[k] = paramType;
								}
							}
							return (Operation)processor.getBridgeFactory().buildOperation(ret, operName, array);
						}
					}
				}
			}
		}
		return super.lookupOperation(name, types);
	}

	/** Get Name fom implementation */
	public String getName() {
		try {
			name = Naming.getFullInterfaceName(class_);
		} catch (Exception e) {
			name = "TypeVoid";
		}
		return name;
	}
	
	/* Compute all super classes of a class */
	public static List allSuperClasses(GeneralizableElement cls) {
		// Initialize the list
		List classes = new Vector();
		if (cls == null) return classes;
		classes.add(cls);
		int first = 0;
		int last = 1;
		// While there are unvisited nodes
		while (first < last) {
			// Get current class
			GeneralizableElement crtCls = (GeneralizableElement)classes.get(first++);
			// Scan all direct superclasses   	
			if (crtCls != null) {
				Iterator it = crtCls.getGeneralization().iterator();
				while (it.hasNext()) {
					Generalization gen = (Generalization)it.next();
					Object parent = gen.getParent();
					if (parent != null) {
						if (!classes.contains(parent)) { 
							classes.add(parent);
							last++;
						} else {
						}
					}
				}
			}
		}
		return classes;
	}

	/** Check if this (a ModelType) conforms with c */
	public Boolean conformsTo(Classifier c) {
		return TypeConformance.conformsTo(this, c) ? Boolean.TRUE : Boolean.FALSE;
//		if (c instanceof OclAnyType)
//			return Boolean.TRUE;
//			if (c instanceof OclModelElementTypeImpl) {
//				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier cc = ((OclModelElementTypeImpl) c).class_;
//				return new Boolean(OclModelElementTypeImpl.allSuperClasses((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement)cc).contains(cc));
//			}
//		return null;
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this, obj);
	}
	
	/** ToString */
	public String toString() {
		return "OclModelElementType("+class_.getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new OclModelElementTypeImpl(class_, processor);
	}
}
