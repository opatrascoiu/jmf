package uk.ac.kent.cs.kmf.util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uk.ac.kent.cs.kmf.xmi.AdapterFactoryRegister;
import uk.ac.kent.cs.kmf.xmi.IXMIFile;
import uk.ac.kent.cs.kmf.xmi.XMIReader;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.*;

public class XMIToUMLLoader implements Loader {
	protected static UmlRepository rep;
	public Pair loadXMI(String inputFile, ILog log) {
		//--- Initialize the XMI container  ---
    	IXMIFile xmiFile = null;
		rep = new UmlRepository$Class(log);
    	try {
    		//--- Register the UML factory into XMI framework ---
    		UMLAdapterFactory adapterFactory = new UMLAdapterFactory(rep, log);
        	AdapterFactoryRegister.registerAdapterFactory(adapterFactory);
        	//--- Read in the XMI file and create UML objects ---
        	log.reportMessage("Reading XMI file '"+inputFile+"' ...");
        	xmiFile = (new XMIReader()).read(inputFile, log);
    	} catch (Exception e) {
    		e.printStackTrace(System.out);
    	}
    	Pair result = new PairImpl(xmiFile, rep);
    	return result;
	}
	public Pair loadXMI(File inputFile, ILog log) {
		//--- Initialize the XMI container  ---
		IXMIFile xmiFile = null;
		rep = new UmlRepository$Class(log);
		try {
			//--- Register the UML factory into XMI framework ---
			UMLAdapterFactory adapterFactory = new UMLAdapterFactory(rep, log);
			AdapterFactoryRegister.registerAdapterFactory(adapterFactory);
			//--- Read in the XMI file and create UML objects ---
			log.reportMessage("Reading XMI file '"+inputFile+"' ...");
			xmiFile = (new XMIReader()).read(inputFile, log);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		Pair result = new PairImpl(xmiFile, rep);
		return result;
	}
	public Pair loadXMI(InputStream inputFile, ILog log) {
		//--- Initialize the XMI container  ---
		IXMIFile xmiFile = null;
		rep = new UmlRepository$Class(log);
		try {
			//--- Register the UML factory into XMI framework ---
			UMLAdapterFactory adapterFactory = new UMLAdapterFactory(rep, log);
			AdapterFactoryRegister.registerAdapterFactory(adapterFactory);
			//--- Read in the XMI file and create UML objects ---
			log.reportMessage("Reading XMI file ...");
			xmiFile = (new XMIReader()).read(inputFile, log);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		Pair result = new PairImpl(xmiFile, rep);
		return result;
	}
	public Pair loadModel(String inputFile, ILog log) {
		//--- Initialize the XMI container  ---
		Pair pair = loadXMI(inputFile, log);
		IXMIFile xmiFile = (IXMIFile)pair.getFirst();
		if (xmiFile ==null) return null;
		Iterator i = xmiFile.getTopObjects().iterator();
		while (i.hasNext()) {
			Object elem = i.next();
			if (elem instanceof Model) {
				setMissingLinks((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace)elem);
				setLinksToEnums((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace)elem);
				pair.setFirst(elem);
				return pair;
			} 
		}
		pair.setFirst(null);
		return pair;
	}
	public Pair loadModel(File inputFile, ILog log) {
		//--- Initialize the XMI container  ---
		Pair pair = loadXMI(inputFile, log);
		IXMIFile xmiFile = (IXMIFile)pair.getFirst();
		if (xmiFile ==null) return null;
		Iterator i = xmiFile.getTopObjects().iterator();
		while (i.hasNext()) {
			Object elem = i.next();
			if (elem instanceof Model) {
				setMissingLinks((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace)elem);
				setLinksToEnums((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace)elem);
				pair.setFirst(elem);
				return pair;
			} 
		}
		pair.setFirst(null);
		return pair;
	}
	public Pair loadModel(InputStream inputFile, ILog log) {
		//--- Initialize the XMI container  ---
		Pair pair = loadXMI(inputFile, log);
		IXMIFile xmiFile = (IXMIFile)pair.getFirst();
		if (xmiFile ==null) return null;
		Iterator i = xmiFile.getTopObjects().iterator();
		while (i.hasNext()) {
			Object elem = i.next();
			if (elem instanceof Model) {
				setMissingLinks((Namespace)elem);
				setLinksToEnums((Namespace)elem);
				pair.setFirst(elem);
				return pair;
			} 
		}
		pair.setFirst(null);
		return pair;
	}

	/** Set some missing links and compute enumerations */
	protected static Map globalClsToEnum = new HashMap();
	protected static void setLinksToEnums(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace pkg) {
		// For each element within a package
		Iterator i = pkg.getOwnedElement().iterator();
		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof Class_) {
				Class_ cls = (Class_)o;
				// Link to father for XMI 1.2
				cls.setNamespace_(pkg); 
				// Scan all Attributes and link them
				Iterator j = cls.getFeature().iterator();
				while (j.hasNext()) {
					Object obj = j.next();
					if (obj instanceof Attribute) {
						Classifier type = ((Attribute)obj).getType();
						Enumeration_ enum_ = (Enumeration_)XMIToUMLLoader.globalClsToEnum.get(type);
						if (enum_ != null) {
							((Attribute)obj).setType(enum_);
						}
					}
				}
			}
			else if (o instanceof Package) {
				// Link to father for XMI 1.2
				((Package)o).setNamespace_(pkg);
				// Process the nested package	        	
				setLinksToEnums((Package)o);
			}
		}
	}

	protected static void setMissingLinks(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace pkg) {
		// For each element within a package
		Iterator i = pkg.getOwnedElement().iterator();
		Map clsToEnum = new HashMap();
		while (i.hasNext()) {
			Object o = i.next();
			// Set 'specialization'
			if (o instanceof GeneralizableElement) {
				GeneralizableElement g = (GeneralizableElement)o;
				Iterator j = g.getGeneralization().iterator();
				while (j.hasNext()) {
					Object o1 = j.next();
					if (o1 instanceof Generalization) {
						Generalization g1 = (Generalization)o1;
						GeneralizableElement parent = g1.getParent();
						parent.getSpecialization().add(g1);	
					}
				}
			}
			// Class_
			if (o instanceof Class_) {
				Class_ cls = (Class_)o;
				// Set 'namespace'
				cls.setNamespace_(pkg); 
				// Scan all features and set 'owner'
				Iterator j = cls.getFeature().iterator();
				while (j.hasNext()) {
					Object obj = j.next();
					((Feature)obj).setOwner((Class_)o);
					// Link method to operation
					if (obj instanceof Method) {
						Method method = (Method)obj;
						Operation oper = method.getSpecification();
						if (oper != null) {
							oper.getMethod().add(method);
						}
					}
				}
				// Turn <<enumeration>> into Enumeration
				if (Naming.isStereotype(cls, "enumeration")) {
					// Create an emueration and set main attributes
					Enumeration_ enum_ = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_)rep.buildElement("uml.Foundation.Core.Enumeration_");
					enum_.setName(cls.getName());
					enum_.setNamespace_(cls.getNamespace_());
					// Create all literals
					List fs = cls.getFeature();
					List clone = new Vector();
					clone.addAll(fs);
					for(int k=0; k<clone.size();k++) {
						Object obj = clone.get(k);
						if (obj instanceof Attribute) {
							// Create a literal and set main attributes
							Attribute att = (Attribute)obj;
							EnumLiteral lit = (EnumLiteral)rep.buildElement("uml.Foundation.Core.EnumLiteral");
							lit.setName(att.getName());
							lit.setNamespace_(att.getNamespace_());
							lit.setVisibility(att.getVisibility());
							// Link EnumLiteral and Enumeration_
							lit.setEnumeration(enum_);
							enum_.getLiterals().add(lit);
						}
					}
					// Remove Attributes from links and repository 
					for(int l=0; l<clone.size();l++) {
						Object obj = clone.get(l);
						if (obj instanceof Attribute) {
							// Remove attribute
							Attribute att = (Attribute)obj;
							att.delete();
							rep.removeElement("uml.Foundation.Core.Attribute", att);
						}
					}
					// Store class and enumeration to remove latter
					clsToEnum.put(cls, enum_);
				}
			}
			else if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype) {
				// Link elements to stereotypes
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype s = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype)o;
				// For each link from stereotype
				Iterator ie = s.getExtendedElement().iterator();
				while (ie.hasNext()) {
					Object obj = ie.next();
					if (obj instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement) {
						uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement e = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement)obj;
						// Link back
						e.getStereotype().add(s);
					}
				}       
			} 
			else if (o instanceof Association) {
				// Get ends
				List ends = ((Association)o).getConnection();
				AssociationEnd aend1 = (AssociationEnd)ends.get(0);
				AssociationEnd aend2 = (AssociationEnd)ends.get(1);
				// Link ends to association
				aend1.setAssociation((Association)o);
				aend2.setAssociation((Association)o);
				// Link types from AssociationEnds to types
				if (aend1.getType() == null && aend1.getParticipant() != null) {
					aend1.setType(aend1.getParticipant());
				}
				if (aend2.getType() == null && aend2.getParticipant() != null) {
					aend2.setType(aend2.getParticipant());
				}
			}
			else if (o instanceof Package) {
				// Link to father for XMI 1.2
				((Package)o).setNamespace_(pkg);
				// Process the nested package	        	
				setMissingLinks((Package)o);
			}
		}
		// Remove cls and add enum
		i = clsToEnum.keySet().iterator();
		while (i.hasNext()) {
			Object cls = i.next();
			pkg.getOwnedElement().remove(cls); 
			pkg.getOwnedElement().add(clsToEnum.get(cls)); 
		}
		globalClsToEnum.putAll(clsToEnum);
	}
}
