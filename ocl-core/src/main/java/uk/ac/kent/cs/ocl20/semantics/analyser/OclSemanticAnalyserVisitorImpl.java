/**
 * 
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.ocl20.semantics.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.EnumLiteral;
import uk.ac.kent.cs.ocl20.semantics.bridge.Enumeration_;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.ocl20.semantics.bridge.ModelElement;
import uk.ac.kent.cs.ocl20.semantics.bridge.NamedElement;
import uk.ac.kent.cs.ocl20.semantics.bridge.Namespace;
import uk.ac.kent.cs.ocl20.semantics.bridge.OclModelElementType;
import uk.ac.kent.cs.ocl20.semantics.bridge.Operation;
import uk.ac.kent.cs.ocl20.semantics.bridge.Primitive;
import uk.ac.kent.cs.ocl20.semantics.bridge.Property;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ClassifierContextDecl;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ClassifierContextDecl$Class;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.Constraint;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.Constraint$Class;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ConstraintKind;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ConstraintKind$Class;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.OperationContextDecl;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.OperationContextDecl$Class;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.PropertyContextDecl;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.PropertyContextDecl$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.BooleanLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.BooleanLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionItem;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionItem$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionKind;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionKind$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionLiteralPart;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionRange;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionRange$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.EnumLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.EnumLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IfExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IfExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IntegerLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IntegerLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IterateExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IterateExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IteratorExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IteratorExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.LetExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.LetExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OclExpression;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OperationCallExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OperationCallExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.PropertyCallExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.PropertyCallExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.RealLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.RealLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.StringLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.StringLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TupleLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TupleLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TypeExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TypeExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.UndefinedLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.UndefinedLiteralExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableDeclaration;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableDeclaration$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableExp$Class;
import uk.ac.kent.cs.ocl20.semantics.model.types.BagType;
import uk.ac.kent.cs.ocl20.semantics.model.types.BooleanType;
import uk.ac.kent.cs.ocl20.semantics.model.types.CollectionType;
import uk.ac.kent.cs.ocl20.semantics.model.types.OrderedSetType;
import uk.ac.kent.cs.ocl20.semantics.model.types.SequenceType;
import uk.ac.kent.cs.ocl20.semantics.model.types.SetType;
import uk.ac.kent.cs.ocl20.semantics.model.types.TupleType;
//import uk.ac.kent.cs.ocl20.semantics.model.types.TypeType;
import uk.ac.kent.cs.ocl20.semantics.model.types.VoidType;
import uk.ac.kent.cs.ocl20.standard.types.TypeConformance;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor$Class;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ClassifierContextDeclAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ConstraintAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ConstraintKindAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ConstraintKindAS$Class;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ContextDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.OperationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.OperationContextDeclAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.PackageDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.PropertyContextDeclAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.VariableDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.AndExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.ArrowSelectionExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.AssociationCallExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.BooleanLiteralExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CallExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionItemAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionKindAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionKindAS$Class;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionLiteralExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionLiteralPartAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionRangeAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.DotSelectionExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.EnumLiteralExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.IfExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.ImpliesExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.IntegerLiteralExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.IterateExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.IteratorExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.LetExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.NotExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclExpressionAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclMessageArgAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclMessageExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclMessageKindAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OperationCallExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OrExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.PathNameExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.RealLiteralExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.SelectionExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.StringLiteralExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.TupleLiteralExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.XorExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.types.BagTypeAS;
import uk.ac.kent.cs.ocl20.syntax.ast.types.ClassifierAS;
import uk.ac.kent.cs.ocl20.syntax.ast.types.CollectionTypeAS;
import uk.ac.kent.cs.ocl20.syntax.ast.types.OrderedSetTypeAS;
import uk.ac.kent.cs.ocl20.syntax.ast.types.SequenceTypeAS;
import uk.ac.kent.cs.ocl20.syntax.ast.types.SetTypeAS;
import uk.ac.kent.cs.ocl20.syntax.ast.types.TupleTypeAS;
import uk.ac.kent.cs.ocl20.syntax.parser.ErrorManager;

public class OclSemanticAnalyserVisitorImpl
	extends SyntaxVisitor$Class
	implements SyntaxVisitor 
{
	protected boolean DEBUG = false;
	
	public OclSemanticAnalyserVisitorImpl(OclProcessor proc) {
		this.processor = proc;
/*
		basicDataTypes.put("OclAny", processor.getTypeFactory().buildOclAnyType());
		basicDataTypes.put("OclMessage", processor.getTypeFactory().buildOclMessageType());
		//dataTypes.put("OclType", processor.getTypeFactory().buildOclType());
		basicDataTypes.put("OclVoid", processor.getTypeFactory().buildVoidType());
		//dataTypes.put("OclState", processor.getTypeFactory().buildOclStateType());
		basicDataTypes.put("Boolean", processor.getTypeFactory().buildBooleanType());
		basicDataTypes.put("Integer", processor.getTypeFactory().buildIntegerType());
		basicDataTypes.put("Real", processor.getTypeFactory().buildRealType());
		basicDataTypes.put("String", processor.getTypeFactory().buildStringType());
*/
	}
	//protected Map basicDataTypes = new HashMap();
	protected OclProcessor processor=null;

	static long counter = 0;
	protected String newName() {
		return "_tempIt" + counter++;
	}

	//
	// Contexts
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ContextsAS.PackageDeclarationAS' */
	public Object visit(PackageDeclarationAS host, Object data) {
		//--- Compute semantic contexts ---
		List semContexts = new Vector();
		Iterator i = host.getContextDecls().iterator();
		while (i.hasNext()) {
			ContextDeclarationAS asContext = (ContextDeclarationAS) i.next();
			semContexts.add(asContext.accept(this, data));
		}
		//--- Create semantic package ---
		//		PackageDeclaration semPkg = new PackageDeclaration$Class();
		//		semPkg.setPathName(host.getPathName());
		//		semPkg.setContextDecls(semContexts);
		return semContexts;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ContextsAS.ClassifierContextDeclAS' */
	protected Object location = null;
	public Object visit(ClassifierContextDeclAS host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		Environment newEnv = null;
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Create semantic context
		ClassifierContextDecl semContext = new ClassifierContextDecl$Class();
		semContext.setReferredNamespace((Namespace) env.lookupPathName(host.getPathName()));
		//--- Search for pathName classifier ---
		ModelElement element = env.lookupPathName(host.getPathName());
		if (element instanceof Classifier && element != null) {
			semContext.setReferredClassifier((Classifier) element);
			newEnv = env.addVariableDeclaration("self", (Classifier) element, Boolean.TRUE);
		} else {
			String pathName = new String();
			Iterator i = host.getPathName().iterator();
			while (i.hasNext()) {
				pathName += (String) i.next();
				if (i.hasNext())
					pathName += ".";
			}
			if (!pathName.equals("OclVoid")) 
				ErrorManager.reportError(log, location, "Unknown classifier '" + pathName + "'");
			newEnv = env.addVariableDeclaration("self", processor.getTypeFactory().buildVoidType(), Boolean.TRUE);
		}
		//--- Compute semantic constraints ---
		List semConstraints = new Vector();
		Iterator i = host.getConstraints().iterator();
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("env", newEnv);
		if (element instanceof Classifier)
			newData.put("context", semContext);
		while (i.hasNext()) {
			ConstraintAS asConstraint = (ConstraintAS) i.next();
			semConstraints.add(asConstraint.accept(this, newData));
		}
		semContext.setConstraint(semConstraints);
		return semContext;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.Contexts.PropertyContextDeclAS' */
	public Object visit(PropertyContextDeclAS host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Create semantic context
		PropertyContextDecl semContext = new PropertyContextDecl$Class();
		semContext.setReferredNamespace((Namespace) env.lookupPathName(host.getPathName()));
		//--- Search for pathName::name property ---
		List pathName = host.getPathName();
		String name = host.getName();
		pathName.add(name);
		ModelElement element = env.lookupPathName(pathName);
		if (element instanceof Property) {
			Property property = (Property) element;
			semContext.setReferredProperty((Property) element);
			if (host.getType() != null) {
				TypeExp tle = (TypeExp)host.getType().accept(this, data);
				property.setType( tle.getType() );
			}
		} else {
			String pathNameStr = new String();
			Iterator i = host.getPathName().iterator();
			while (i.hasNext()) {
				pathNameStr += (String) i.next();
				if (i.hasNext())
					pathNameStr += ".";
			}
			ErrorManager.reportError(log, location, "Unknown property '" + pathName + "'");
		}
		//--- Compute semantic constraints ---
		List semConstraints = new Vector();
		Iterator i = host.getConstraints().iterator();
		Map newData = new HashMap();
		newData.putAll((Map) data);
		if (element instanceof Property)
			newData.put("context", semContext);
		while (i.hasNext()) {
			ConstraintAS asConstraint = (ConstraintAS) i.next();
			semConstraints.add(asConstraint.accept(this, newData));
		}
		semContext.setConstraint(semConstraints);
		return semContext;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.Contexts.OperationContextDeclAS' */
	public Object visit(OperationContextDeclAS host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Create semantic context
		OperationContextDecl semContext = new OperationContextDecl$Class();
		semContext.setReferredNamespace((Namespace) env.lookupPathName(host.getOperation().getPathName()));
		if (host.getOperation() != null)
			semContext.setReferredOperation((Operation) host.getOperation().accept(this, data));
		//--- Compute semantic constraints ---
		List semConstraints = new Vector();
		Iterator i = host.getConstraints().iterator();
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("context", semContext);
		while (i.hasNext()) {
			ConstraintAS asConstraint = (ConstraintAS) i.next();
			semConstraints.add(asConstraint.accept(this, newData));
		}
		semContext.setConstraint(semConstraints);
		return semContext;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.Contexts.VariableDeclarationAS' */
	public Object visit(VariableDeclarationAS host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Create the variable ---
		VariableDeclaration semVar = new VariableDeclaration$Class();
		semVar.setName(host.getName());
		if (host.getType() != null) {
			TypeExp tle = (TypeExp)host.getType().accept(this, data);
			semVar.setType( tle.getType() );
		} else
			semVar.setType(null);
		if (host.getInitExp() != null) {
			OclExpression initExp = (OclExpression) host.getInitExp().accept(this, data);
			if (initExp == null) {
				log.reportError("Semantic error in expression - "+host.getInitExp());
				return null;
			}
			//check conformance between var type and expr type
			if (semVar.getType() == null)
				semVar.setType(initExp.getType());
			else {
				Classifier varType = semVar.getType();
				Classifier initType = initExp.getType();
				if (!initType.conformsTo(varType).booleanValue()) {
					ErrorManager.reportError(log, location, "Non-conforming types '" + varType + "' and '" + initType + "' in variable declaration.");
				}
			}
			semVar.setInitExpression(initExp);
		}
		return semVar;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.Contexts.OperationAS' */
	public Object visit(OperationAS host, Object data) {
		//--- Compute semantic parameters ---
		List semParameters = new Vector();
		Iterator i = host.getParameters().iterator();
		while (i.hasNext()) {
			VariableDeclarationAS asVar = (VariableDeclarationAS) i.next();
			semParameters.add(asVar.accept(this, data));
		}
		//--- Create the semantic variable ---
		Operation semOper = processor.getBridgeFactory().buildOperation(null, host.getName(), null);
		return semOper;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.Contexts.ConstraintAS' */
	public Object visit(ConstraintAS host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		ContextDeclaration context = (ContextDeclaration) ((Map) data).get("context");
		//--- Create the semantic constraint ---
		Constraint semCons = new Constraint$Class();
		semCons.setName(host.getName());
		if (host.getDefOperation() != null)
			semCons.setDefOperation((Operation) host.getDefOperation().accept(this, data));
		VariableDeclarationAS defVar = host.getDefVariable();
		if (host.getDefVariable() != null) {
			Classifier type = processor.getTypeFactory().buildOclAnyType();
			if (defVar.getType() != null)
				type = (Classifier) defVar.getType().accept(this, data);
			Property prop = processor.getBridgeFactory().buildProperty(type, defVar.getName());
			semCons.setDefProperty(prop);
		}
		semCons.setContext(context);
		semCons.setKind((ConstraintKind) host.getKind().accept(this, data));
		if (host.getBodyExpression() != null) {
			Object val = host.getBodyExpression().accept(this, data);
			semCons.setBodyExpression((OclExpression) val);
		}
		return semCons;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.Contexts.ConstraintKindAS' */
	public Object visit(ConstraintKindAS host, Object data) {
		ConstraintKind kind = null;
		if (host == ConstraintKindAS$Class.BODY)
			kind = ConstraintKind$Class.BODY;
		else if (host == ConstraintKindAS$Class.DEF)
			kind = ConstraintKind$Class.DEF;
		else if (host == ConstraintKindAS$Class.DERIVE)
			kind = ConstraintKind$Class.DERIVE;
		else if (host == ConstraintKindAS$Class.INIT)
			kind = ConstraintKind$Class.INIT;
		else if (host == ConstraintKindAS$Class.INV)
			kind = ConstraintKind$Class.INV;
		else if (host == ConstraintKindAS$Class.PRE)
			kind = ConstraintKind$Class.PRE;
		else if (host == ConstraintKindAS$Class.POST)
			kind = ConstraintKind$Class.POST;
		return kind;
	}

	//
	// Ocl expression
	//
	//
	// Primary expressions
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.BooleanLiteralExpAS' */
	public Object visit(BooleanLiteralExpAS host, Object data) {
		//--- Create semantic expression ---
		if (host.getValue() == null) {
			UndefinedLiteralExp result = new UndefinedLiteralExp$Class();
			result.setType(processor.getTypeFactory().buildVoidType());
			result.setIsMarkedPre(host.getIsMarkedPre());
			return result;
		} else {
			BooleanLiteralExp result = new BooleanLiteralExp$Class();
			result.setBooleanSymbol(host.getValue());
			result.setType(processor.getTypeFactory().buildBooleanType());
			result.setIsMarkedPre(host.getIsMarkedPre());
			return result;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.IntegerLiteralExpAS' */
	public Object visit(IntegerLiteralExpAS host, Object data) {
		//--- Create semantic expression ---
		IntegerLiteralExp result = new IntegerLiteralExp$Class();
		result.setIntegerSymbol(host.getValue());
		result.setType(processor.getTypeFactory().buildIntegerType());
		result.setIsMarkedPre(host.getIsMarkedPre());
		return result;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.RealLiteralExpAS' */
	public Object visit(RealLiteralExpAS host, Object data) {
		//--- Create semantic expression ---
		RealLiteralExp result = new RealLiteralExp$Class();
		result.setRealSymbol(host.getValue());
		result.setType(processor.getTypeFactory().buildRealType());
		result.setIsMarkedPre(host.getIsMarkedPre());
		return result;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.StringLiteralExpAS' */
	public Object visit(StringLiteralExpAS host, Object data) {
		//--- Create semantic expression ---
		StringLiteralExp result = new StringLiteralExp$Class();
		result.setStringSymbol(host.getValue());
		result.setType(processor.getTypeFactory().buildStringType());
		result.setIsMarkedPre(host.getIsMarkedPre());
		return result;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.EnumLiteralExpAS' */
	public Object visit(EnumLiteralExpAS host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Compute the type ---
		List pathName = host.getPathName().subList(0, host.getPathName().size() - 1);
		String enumName = (String) host.getPathName().get(host.getPathName().size() - 1);
		Classifier type = (Classifier) env.lookupPathName(pathName);
		//--- Create semantic expression ---
		EnumLiteralExp result = new EnumLiteralExp$Class();
		//result.setPathName(host.getPathName());
		result.setType(type);
		result.setName(enumName);
		result.setReferredEnumLiteral(((Enumeration_) type).lookupEnumLiteral(enumName));
		result.setIsMarkedPre(host.getIsMarkedPre());
		return result;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.CollectionLiteralExpAS' */
	public Object visit(CollectionLiteralExpAS host, Object data) {
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Compute elementType ---
			Iterator i = host.getCollectionParts().iterator();
			Classifier elementType = processor.getTypeFactory().buildVoidType();
			while (i.hasNext()) {
				CollectionLiteralPart crtElement = (CollectionLiteralPart) ((CollectionLiteralPartAS) i.next()).accept(this, data);
				Classifier crtElementType = crtElement.getType();
				Classifier tempType = checkTypes(elementType, crtElementType);
				if (tempType == null) {
					ErrorManager.reportWarning(log, location, "Non-conforming types '" + elementType + "' and '" + crtElementType + "' in collection literal.");
					elementType = processor.getTypeFactory().buildOclAnyType();
					break;
				} else {
					elementType = tempType;
				}
			}
			//--- Compute the type ---
			CollectionType type = null;
			//--- Compute kind ---
			CollectionKindAS kind = host.getKind();
			if (kind == CollectionKindAS$Class.BAG)
				type = processor.getTypeFactory().buildBagType((Classifier) elementType);
			else if (kind == CollectionKindAS$Class.SET)
				type = processor.getTypeFactory().buildSetType((Classifier) elementType);
			else if (kind == CollectionKindAS$Class.ORDERED_SET)
				type = processor.getTypeFactory().buildOrderedSetType((Classifier) elementType);
			else if (kind == CollectionKindAS$Class.SEQUENCE)
				type = processor.getTypeFactory().buildSequenceType((Classifier) elementType);
			else
				type = processor.getTypeFactory().buildCollectionType((Classifier) elementType);
			type.setElementType(elementType);
			//--- Compute semantic contexts ---
			List colParts = new Vector();
			i = host.getCollectionParts().iterator();
			while (i.hasNext()) {
				colParts.add(((CollectionLiteralPartAS) i.next()).accept(this, data));
			}
			//--- Create semantic expression ---
			CollectionLiteralExp result = new CollectionLiteralExp$Class();
			result.setKind((CollectionKind) host.getKind().accept(this, data));
			result.setParts(colParts);
			result.setType(type);
			result.setIsMarkedPre(host.getIsMarkedPre());
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.CollectionKindAS' */
	public Object visit(CollectionKindAS host, Object data) {
		CollectionKind kind = null;
		if (host == CollectionKindAS$Class.COLLECTION)
			kind = CollectionKind$Class.COLLECTION;
		else if (host == CollectionKindAS$Class.BAG)
			kind = CollectionKind$Class.BAG;
		else if (host == CollectionKindAS$Class.SET)
			kind = CollectionKind$Class.SET;
		else if (host == CollectionKindAS$Class.SEQUENCE)
			kind = CollectionKind$Class.SEQUENCE;
		else if (host == CollectionKindAS$Class.ORDERED_SET)
			kind = CollectionKind$Class.ORDERED_SET;
		return kind;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.CollectionItemAS' */
	public Object visit(CollectionItemAS host, Object data) {
		//--- Create semantic item ---
		CollectionItem result = new CollectionItem$Class();
		result.setItem((OclExpression) host.getItem().accept(this, data));
		result.setType(result.getItem().getType());
		return result;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.CollectionRangeAS' */
	public Object visit(CollectionRangeAS host, Object data) {
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Compute the type ---
			Classifier type = null;
			// Check first and last types
			OclExpression firstExp = (OclExpression) host.getFirst().accept(this, data);
			Classifier firstType = firstExp.getType();
			OclExpression lastExp = (OclExpression) host.getLast().accept(this, data);
			Classifier lastType = lastExp.getType();
			type = checkTypes(firstType, lastType);
			if (type == null) {
				ErrorManager.reportError(log, location, "Non-conforming types '" + firstType + "' and '" + lastType + "' in collection range.");
				type = processor.getTypeFactory().buildVoidType();
			}
			//--- Create semantic range ---
			CollectionRange result = new CollectionRange$Class();
			result.setType(type);
			result.setFirst(firstExp);
			result.setLast(lastExp);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.TupleLiteralExpAS' */
	public Object visit(TupleLiteralExpAS host, Object data) {
		try {
			// Create the abstract type
			TupleType type = processor.getTypeFactory().buildTupleType(new String[]{}, new Classifier[]{});
			// Compute the semantic tuple parts
			List semTupleParts = new Vector();
			Iterator i = host.getTupleParts().iterator();
			while (i.hasNext()) {
				// Create a abstact variable declaration
				VariableDeclarationAS varDeclAS = (VariableDeclarationAS) i.next();
				VariableDeclaration varDecl = new VariableDeclaration$Class();
				varDecl.setName(varDeclAS.getName());
				Classifier varType = null;
				if (varDeclAS.getType() != null) {
					TypeExp tle = (TypeExp)varDeclAS.getType().accept(this, data);
					varType = tle.getType();
				}
				//varDecl.setType(varType);
				OclExpression init = null;
				if (varDeclAS.getInitExp() != null) {
					init = (OclExpression) varDeclAS.getInitExp().accept(this, data);
					if (varType == null) varType = init.getType();
				}
				varDecl.setInitExpression(init);
				if (varType == null) {
					varDecl.setType( processor.getTypeFactory().buildOclAnyType() );
				} else {
					varDecl.setType(varType);
				}
				// Add it
				semTupleParts.add(varDecl);
			}
			type.setPartType(semTupleParts);
			//--- Create semantic tuple ---
			TupleLiteralExp result = new TupleLiteralExp$Class();
			result.setTuplePart(semTupleParts);
			result.setType(type);
			result.setIsMarkedPre(host.getIsMarkedPre());
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	//	Ambiguous syntactic rules 
	//	
	// attributeCallExpression ::= simpleName ['@'] 
	// attributeCallExpression ::= pathName
	// attributeCallExpression ::= oclExpression '.' simpleName ['@']
	//
	// associationCallExpression ::= simpleName ['[' argumentList ']'] ['@']
	// associationCallExpression ::= oclExpression '.' simpleName ['[' argumentList ']'] ['@'] 
	//
	// operationCallExp ::= simpleName ['@'] '(' [arguments] ')'
	// operationCallExp ::= pathName '(' [arguments] ')'
	// operationCallExp ::= oclExpression '.' simpleName ['@'] '(' [arguments] ')'
	// operationCallExp ::= oclExpression '->' simpleName '(' [arguments] ')'
	//
	// iteratorExp ::= oclExpression '.' simpleName 
	// iteratorExp ::= oclExpression '.' simpleName '(' arguments ')'
	// iteratorExp ::= oclExpression '.' simpleName '[' argumentList ']'
	// iteratorExp ::= oclExpression '->' simpleName '(' varDecl [',' varDecl] '|' oclExpression ')'
	//
	// iterateExp ::= oclExpression '->' 'iterate' '(' [varDecl ';'] varDecl '|' oclExpression ')'
	//

	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.PathNameExpAS' */
	protected String getString(List pathName) {
		String res = new String();
		Iterator i = pathName.iterator();
		while (i.hasNext()) {
			res += (String) i.next();
			if (i.hasNext())
				res += "::";
		}
		return res;
	}
	protected boolean isPartOfCall(OclExpressionAS host) {
		//--- Is this a part of a call ? - () or [] are present in parent
		OclExpressionAS parent = host.getParent();
		if (parent != null && parent instanceof CallExpAS) {
			return true;
		}
		return false;
	}
	public Object visit(PathNameExpAS host, Object data) {
		//
		//	Ambiguous syntactic rules 
		//	
		// attributeCallExpression ::= simpleName ['@'] 
		// associationCallExpression ::= simpleName ['[' argumentList ']'] ['@']
		// operationCallExp ::= simpleName ['@'] '(' [arguments] ')'
		// attributeCallExpression ::= pathName
		// operationCallExp ::= pathName '(' [arguments] ')'
		//
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Is part of a call ? ---
			//			if (isPartOfCall(host)) {
			//				return null;
			//			}
			//--- Initialize the result and type ---
			OclExpression result = null;
			Classifier type = null;
			//--- Get pathName ---
			List pathName = host.getPathName();
			String pathNameStr = getString(pathName);
			// Simple name
			if (pathName.size() == 1) {
				// search for variable
				ModelElement element = env.lookup(pathNameStr);
				// Variable found
				if (element != null && element instanceof VariableDeclaration) {
					// Compute type
					type = ((VariableDeclaration) element).getType();
					// Compute semantic referred variable
					VariableDeclaration semVarDecl = (VariableDeclaration) element;
					// Compute variable expression
					result = new VariableExp$Class();
					((VariableExp) result).setReferredVariable(semVarDecl);
					result.setType(type);
			    //--- Maybe a Classifier --
				} else if (element != null && element instanceof Classifier) {
//					type = processor.getTypeFactory().buildTypeType((Classifier)element);
					result = new TypeExp$Class(pathNameStr, Boolean.FALSE, (Classifier)element);
//					result.setType(type);
					result.setType((Classifier)element);
				} else {
					//--- Lookup for an implicit source and property 
					NamedElement entry = env.lookupImplicitSourceForProperty(pathNameStr);
					Property prop = env.lookupImplicitProperty(pathNameStr);
					//--- Create PropertyCall ---
					PropertyCallExp propCall = new PropertyCallExp$Class();
					result = propCall;
					propCall.setReferredProperty(prop);
					if (entry != null) {
						VariableExp sourceExp = new VariableExp$Class();
						sourceExp.setReferredVariable((VariableDeclaration) entry.getReferredElement());
						sourceExp.setType(((VariableDeclaration) entry.getReferredElement()).getType());
						propCall.setSource(sourceExp);
						type = prop.getType();
					} else {
						ErrorManager.reportError(log, location, "Unknown property or variable '" + pathNameStr + "'");
					}
					propCall.setType(type);
				}
			// Pathname
			} else {
				// Search for a property (attribute) or an enumerator
				Object element = env.lookupPathName(pathName);
				// Unfound element
				if (element == null) {
					ErrorManager.reportError(log, location, "Unknown pathname '" + pathNameStr + "'");
				// Enumeration
				} else if (element instanceof EnumLiteral) {
					result = new EnumLiteralExp$Class();
					((EnumLiteralExp)result).setReferredEnumLiteral((EnumLiteral)element);
					type = ((EnumLiteral) element).getEnumeration();
					result.setType(type);
					((EnumLiteralExp)result).setName(((EnumLiteral) element).getName());
				// Property
				} else if (element instanceof Property) {
					// Compute type
					type = ((Property) element).getType();
					// Compute result
					result = new PropertyCallExp$Class();
					((PropertyCallExp) result).setReferredProperty((Property) element);
					result.setType(type);
				//--- Maybe a Classifier --
				} else if (element != null && element instanceof Classifier) {
//					type = processor.getTypeFactory().buildTypeType((Classifier)element);
					result = new TypeExp$Class(pathNameStr, Boolean.FALSE, (Classifier)element);
//					result.setType(type);
					result.setType((Classifier)element);
				} else {
					type = processor.getTypeFactory().buildVoidType();
				}
			}
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	// Selection ExpressionsAS
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.DotSelectionExpAS' */
	public Object visit(DotSelectionExpAS host, Object data) {
		//
		//	Ambiguous syntactic rules 
		//	
		// attributeCallExpression ::= oclExpression '.' simpleName ['@']
		// associationCallExpression ::= oclExpression '.' simpleName ['[' argumentList ']'] ['@'] 
		// iteratorExp ::= oclExpression '.' simpleName 
		// operationCallExp ::= oclExpression '.' simpleName ['@'] '(' [arguments] ')'
		// iteratorExp ::= oclExpression '.' simpleName '(' arguments ')'
		//	
		//--- Unpack arguments ---
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Is part of a call ? ---
			//			if (isPartOfCall(host)) {
			//				return null;
			//			}
			//--- Initialize the result and the type ---
			OclExpression result = null;
			Classifier type = null;
			//--- Property or iterator
			//--- Visit left and compute type ---
			OclExpressionAS source = host.getSource();
			OclExpression semSource = (OclExpression) source.accept(this, data);
			// Property
			if (semSource != null) {
				Classifier sourceType = semSource.getType();
				//--- Default 'collect' Iterator ---
				if (sourceType instanceof CollectionType) {
					Classifier elementType = ((CollectionType) sourceType).getElementType();
					Property property = elementType.lookupProperty(host.getName());
					if (property != null) {
						//--- Compute result: a 'collect' iterator ---
						IteratorExp itExp = new IteratorExp$Class();
						result = itExp;
						itExp.setIsMarkedPre(host.getIsMarkedPre());
						itExp.setName("collect");
						itExp.setSource(semSource);
						//--- Compute first iterator ---
						VariableDeclaration it = new VariableDeclaration$Class();
						it.setName(newName());
						it.setType(elementType);
						itExp.setIterators(new LinkedHashSet());
						itExp.getIterators().add(it);
						//--- Compute body: a property call ---
						PropertyCallExp propCall = new PropertyCallExp$Class();
						VariableExp propSource = new VariableExp$Class();
						propSource.setReferredVariable(it);
						propSource.setType(it.getType());
						propCall.setSource(propSource);
						propCall.setReferredProperty(property);
						itExp.setBody(propCall);
						Classifier propertyType = property.getType();
						propCall.setType(propertyType);
						//--- Compute IteratorExp type ---
						//--- Property is a collection type ---
						if (propertyType instanceof CollectionType) {
							type = ((CollectionType) propertyType).getElementType();
							//--- Property is primitive or a model type instance
						} else if (propertyType instanceof Classifier) {
							type = propertyType;
						}
						// Source is a Set
						if (sourceType instanceof BagType) {
							type = processor.getTypeFactory().buildBagType(type);
						} else if (sourceType instanceof OrderedSetType) {
							type = processor.getTypeFactory().buildOrderedSetType(type);
						} else if (sourceType instanceof SetType) {
							type = processor.getTypeFactory().buildBagType(type);
						} else {
							type = processor.getTypeFactory().buildSequenceType(type);
						}
						itExp.setType(type);
					} else {
						ErrorManager.reportError(log, location, "Unknown property '" + host.getName() + "' in '" + elementType + "'");
					}
				//--- Property call ---
				} else if (sourceType instanceof Classifier) {
					Property property = sourceType.lookupProperty(host.getName());
					if (property != null) {
						// Create a PropertyCallExp
						PropertyCallExp propCall = new PropertyCallExp$Class();
						result = propCall;
						propCall.setIsMarkedPre(host.getIsMarkedPre());
						propCall.setSource(semSource);
						propCall.setReferredProperty(property);
						//--- Compute type ---
						Classifier propertyType = property.getType();
						type = propertyType;
						propCall.setType(type);
						//--- Report error ---
					} else {
						ErrorManager.reportError(log, location, "Unknown property '" + host.getName() + "'");
					}
				}
			} else {
				ErrorManager.reportError(log, location, "Left hand side of '.' operator has a wrong type");
			}
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.ArrowSelectionExpAS' */
	public Object visit(ArrowSelectionExpAS host, Object data) {
		//
		//	Ambiguous syntactic rules 
		//	
		// operationCallExp ::= oclExpression '->' simpleName '(' [arguments] ')'
		// iteratorExp ::= oclExpression '->' simpleName '(' [varDecl ','] varDecl '|' oclExpression ')'
		//		
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- It is always a part a call ---
			//			if (isPartOfCall(host)) {
			//				return null;
			//			}
			return null;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	// Call ExpressionsAS
	//
	/** Check if 'name' is a default operation for a CollectionType */
	protected static boolean isIteratorOperation(CollectionType type, String name) {
		String collection = "*exists*forAll*isUnique*any*one*collect*collectNested*";
		String bag = "*select*reject*collectNested*sortedBy*";
		String set = "*select*reject*collectNested*sortedBy*";
		String sequence = "*select*reject*collectNested*sortedBy*";
		String orderedSet = "*select*reject*collectNested*sortedBy*";
		String x = "*" + name + "*";
		if (type instanceof BagType) {
			return (collection.indexOf(x) != -1) || (bag.indexOf(x) != -1);
		} else if (type instanceof OrderedSetType) {
			return (collection.indexOf(x) != -1) || (orderedSet.indexOf(x) != -1);
		} else if (type instanceof SequenceType) {
			return (collection.indexOf(x) != -1) || (sequence.indexOf(x) != -1);
		} else if (type instanceof SetType) {
			return (collection.indexOf(x) != -1) || (set.indexOf(x) != -1);
		} else if (type instanceof CollectionType) {
			return (collection.indexOf(x) != -1);
		} else {
			return false;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.OperationCallExpAS' */
	public Object visit(OperationCallExpAS host, Object data) {
		//
		//	Ambiguous syntactic rules 
		//	
		// operationCallExp ::= simpleName ['@'] '(' [arguments] ')'
		// operationCallExp ::= pathName '(' [arguments] ')'
		// operationCallExp ::= oclExpression '.' simpleName ['@'] '(' [arguments] ')'
		// operationCallExp ::= oclExpression '->' simpleName '(' [arguments] ')'
		// iteratorExp ::= oclExpression '.' simpleName '(' arguments ')'
		//
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and type ---
			OclExpression result = null;
			Classifier type = null;
			//--- Compute the arguments and argument types ---
			List types = new Vector();
			List args = new Vector();
			Iterator i = host.getArguments().iterator();
			while (i.hasNext()) {
				OclExpressionAS pExp = (OclExpressionAS) i.next();
				OclExpression paramExp = (OclExpression) pExp.accept(this, data);
				if (paramExp == null)  {
					log.reportError("Semantic error in expression : "+pExp);
					return null;
				} 
				Classifier paramType = paramExp.getType();
				args.add(paramExp);
				types.add(paramType);
			}
			//--- Initialize the referred operation ---
			Operation oper = null;
			//--- Source is a simple name or a pathname: OperationCall ---
			OclExpressionAS source = host.getSource();
			if (source instanceof PathNameExpAS) {
				// Create result
				OperationCallExp callExp = new OperationCallExp$Class();
				result = callExp;
				// Prepare the name to search 
				List pathName = ((PathNameExpAS) source).getPathName();
				String pathNameStr = getString(pathName);
				//--- Simple name ---
				if (pathName.size() == 1) {
					oper = env.lookupImplicitOperation(pathNameStr, types);
					NamedElement entry = env.lookupImplicitSourceForOperation(pathNameStr, types);
					if (entry != null) {
						VariableExp sourceExp = new VariableExp$Class();
						sourceExp.setReferredVariable((VariableDeclaration) entry.getReferredElement());
						sourceExp.setType(((VariableDeclaration) entry.getReferredElement()).getType());
						callExp.setSource(sourceExp);
					}
					callExp.setReferredOperation(oper);
					callExp.setArguments(args);
					//--- Path name ---
				} else {
					oper = env.lookupPathName(pathName, types);
					callExp.setSource(null);
					callExp.setReferredOperation(oper);
					callExp.setArguments(args);
				}
				//--- Get the type --
				if (oper != null) {
					type = oper.getReturnType();
					// If 'collect' than set result's elementType to body.type
					if ("collect collectNested".indexOf(pathNameStr) != -1 && type instanceof CollectionType && types.size() != 0)
						 ((CollectionType) type).setElementType((Classifier) types.get(0));
					//--- Unknown operation ---
				} else {
					ErrorManager.reportError(log, location, "Unknown operation '" + pathNameStr + "' or type mismatch between parameters and arguments");
				}
			//--- Source is a -> selection: OperationCall ---
			} else if (source instanceof ArrowSelectionExpAS) {
				//--- Compute the real source and real source type ---
				OclExpressionAS realSource = ((ArrowSelectionExpAS) source).getSource();
				OclExpression semRealSource = (OclExpression) realSource.accept(this, data);
				if (semRealSource == null) {
					log.reportError("Semantic error in expression - "+realSource);
				}
				String name = ((ArrowSelectionExpAS) source).getName();
				Classifier realSourceType = semRealSource.getType();
				//--- Make a set from a singleton ---
				if (!(realSourceType instanceof CollectionType)) {
					//--- Create a collection item ---
					CollectionItem colItem = new CollectionItem$Class();
					colItem.setItem(semRealSource);
					//--- Create a set collection ---
					semRealSource = new CollectionLiteralExp$Class();
					realSourceType = processor.getTypeFactory().buildSetType((Classifier) realSourceType);
					semRealSource.setType(realSourceType);
					((CollectionLiteralExp) semRealSource).setKind(CollectionKind$Class.SET);
					((CollectionLiteralExp) semRealSource).getParts().add(colItem);
				}
				//--- IteratorExp ---
				if (realSourceType instanceof CollectionType && isIteratorOperation((CollectionType) realSourceType, name)) {
					IteratorExp itExp = new IteratorExp$Class();
					result = itExp;
					itExp.setName(name);
					//--- Set iterator ---
					itExp.setIterators(new LinkedHashSet());
					VariableDeclaration it = new VariableDeclaration$Class();
					Classifier elementType = ((CollectionType) realSourceType).getElementType();
					it.setType(elementType);
					itExp.getIterators().add(it);
					//--- Set source ---
					itExp.setSource(semRealSource);
					//--- Set name ---
					itExp.setName(name);
					//--- Set body ---
					if (args != null && args.size() > 0)
						itExp.setBody((OclExpression) args.get(0));

					//--- Search iterator ---
					oper = realSourceType.lookupOperation(name, types);
				//--- Operation call
				} else {
					OperationCallExp callExp = new OperationCallExp$Class();
					result = callExp;
					//--- Set source ---
					callExp.setSource(semRealSource);
					//--- Set arguments ---
					callExp.setArguments(args);
					//--- Set referred operation ---
					oper = realSourceType.lookupOperation(name, types);
					callExp.setReferredOperation(oper);
				}
				// Set type
				if (oper != null) {
					type = oper.getReturnType();
//					if (type instanceof TypeType) type = ((TypeType)type).getClassifier();
					// If 'collect' than set result's elementType to body.type
					if ("collect collectNested".indexOf(name) != -1 && type instanceof CollectionType && types.size() != 0)
						 ((CollectionType) type).setElementType((Classifier) types.get(0));
				//--- Unknown operation ---
				} else {
					ErrorManager.reportError(log, location, "Unknown operation '" + name + "' in type '" + realSourceType + "' or type mismatch between parameters and arguments");
					return null;
				}
			//--- Source is a . selection: OperationCall or IteratorExp ---
			} else if (source instanceof DotSelectionExpAS) {
				String name = ((SelectionExpAS) source).getName();
				//--- Compute the real source and real source type ---
				OclExpressionAS realSource = ((SelectionExpAS) source).getSource();
				OclExpression semRealSource = (OclExpression) realSource.accept(this, data);
				if (semRealSource == null ) {
					log.reportError("Semantic error in expression : "+realSource);
					return null;
				} 
				Classifier realSourceType = semRealSource.getType();
				//--- IteratorExp ---
				if (realSourceType instanceof CollectionType && isIteratorOperation((CollectionType) realSourceType, name)) {
					IteratorExp itExp = new IteratorExp$Class();
					result = itExp;
					//--- Set iterator ---
					itExp.setIterators(new LinkedHashSet());
					VariableDeclaration it = new VariableDeclaration$Class();
					Classifier elementType = ((CollectionType) realSourceType).getElementType();
					it.setType(elementType);
					itExp.getIterators().add(it);
					//--- Set source ---
					itExp.setSource(semRealSource);
					//--- Set name ---
					itExp.setName(name);
					//--- Set body ---
					OperationCallExp body = new OperationCallExp$Class();
					VariableExp bodySource = new VariableExp$Class();
					bodySource.setReferredVariable(it);
					bodySource.setType(it.getType());
					body.setSource(bodySource);
					body.setArguments(args);
					//--- Set referred operation ---
					oper = elementType.lookupOperation(name, types);
					body.setReferredOperation(oper);
				//--- Operation call
				} else {
					OperationCallExp callExp = new OperationCallExp$Class();
					result = callExp;
					//--- Set source ---
					callExp.setSource(semRealSource);
					//--- Set arguments ---
					callExp.setArguments(args);
					//--- Set referred operation ---
					oper = realSourceType.lookupOperation(name, types);
					callExp.setReferredOperation(oper);
				}
				//--- Get the type --
				if (oper != null) {
					type = oper.getReturnType();
//					if (type instanceof TypeType) type = ((TypeType)type).getClassifier();
					// If 'collect' than set result's elementType to body.type
					if ("collect collectNested".indexOf(name) != -1 && type instanceof CollectionType && types.size() != 0)
						 ((CollectionType) type).setElementType((Classifier) types.get(0));
					//--- Unknown operation ---
				} else {
					ErrorManager.reportError(log, location, "Unknown operation '" + name + "' in '" + realSourceType + "' or type mismatch between parameters and arguments");
					return null;
				}
				//--- Other source */
			}
			result.setType(type);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.AssociationCallExpAS' */
	public Object visit(AssociationCallExpAS host, Object data) {
		//
		//	Ambiguous syntactic rules 
		//	
		// associationCallExpression ::= simpleName ['[' argumentList ']'] ['@']
		// associationCallExpression ::= oclExpression '.' simpleName ['[' argumentList ']'] ['@'] 
		// iteratorExp ::= oclExpression '.' simpleName '[' argumentList ']'
		//
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and type ---
			OclExpression result = null;
			Classifier type = null;
			//--- Compute qualifiers ---
			List semQuals = new Vector();
			Iterator i = host.getArguments().iterator();
			while (i.hasNext()) {
				OclExpressionAS arg = (OclExpressionAS) i.next();
				semQuals.add(arg.accept(this, data));
			}
			//--- Compute source ---
			OclExpressionAS source = host.getSource();
			//--- Source is a simple name ---
			if (source instanceof PathNameExpAS) {
				//--- Create PropertyCall ---
				PropertyCallExp propCall = new PropertyCallExp$Class();
				result = propCall;
				propCall.setQualifiers(semQuals);
				String name = getString(((PathNameExpAS) host).getPathName());
				Property prop = env.lookupImplicitProperty(name);
				propCall.setReferredProperty(prop);
				NamedElement entry = env.lookupImplicitSourceForProperty(name);
				if (entry != null) {
					VariableExp sourceExp = new VariableExp$Class();
					sourceExp.setReferredVariable((VariableDeclaration) entry.getReferredElement());
					sourceExp.setType(((VariableDeclaration) entry.getReferredElement()).getType());
					propCall.setSource(sourceExp);
				}
				type = prop.getType();
				//--- Source is a . selection ---
			} else if (source instanceof DotSelectionExpAS) {
				//--- Compute real source ---
				OclExpressionAS realSource = ((DotSelectionExpAS) source).getSource();
				OclExpression realSemSource = (OclExpression) realSource.accept(this, data);
				Classifier realSourceType = (Classifier) realSemSource.getType();
				//--- IteratorExp ---
				if (realSourceType instanceof CollectionType) {
					Classifier elementType = ((CollectionType) realSourceType).getElementType();
					String name = ((DotSelectionExpAS) source).getName();
					Property property = elementType.lookupProperty(name);
					if (property != null) {
						//--- Compute result: a 'collect' iterator ---
						IteratorExp itExp = new IteratorExp$Class();
						result = itExp;
						itExp.setIsMarkedPre(host.getIsMarkedPre());
						itExp.setSource(realSemSource);
						itExp.setName("collect");
						//--- Compute iterator ---
						VariableDeclaration it = new VariableDeclaration$Class();
						it.setName(newName());
						it.setType(elementType);
						itExp.setIterators(new LinkedHashSet());
						itExp.getIterators().add(it);
						//--- Compute iterator's body: a property call ---
						PropertyCallExp propCall = new PropertyCallExp$Class();
						VariableExp propSource = new VariableExp$Class();
						propSource.setReferredVariable(it);
						propSource.setType(it.getType());
						propCall.setSource(propSource);
						propCall.setReferredProperty(property);
						itExp.setBody(propCall);
						//--- Compute property type ---
						Classifier propertyType = property.getType();
						propCall.setType(propertyType);
						//--- Compute IteratorExp type ---
						//--- Property is a collection type ---
						if (propertyType instanceof CollectionType) {
							type = ((CollectionType) propertyType).getElementType();
							//--- Property is primitive or a model type instance
						} else if (propertyType instanceof Classifier) {
							type = propertyType;
						}
						// Source is a Set
						if (realSourceType instanceof BagType) {
							type = processor.getTypeFactory().buildBagType(type);
						} else if (realSourceType instanceof OrderedSetType) {
							type = processor.getTypeFactory().buildOrderedSetType(type);
						} else if (realSourceType instanceof SetType) {
							type = processor.getTypeFactory().buildBagType(type);
						} else {
							type = processor.getTypeFactory().buildSequenceType(type);
						}
						itExp.setType(type);
					} else {
						ErrorManager.reportError(log, location, "Unknown property '" + name + "' in type '" + elementType + "'");
						return null;
					}
					//--- PropertyCall: association end --- 
				} else {
					//--- Create PropertyCall ---
					PropertyCallExp propCall = new PropertyCallExp$Class();
					result = propCall;
					propCall.setQualifiers(semQuals);
					String name = getString(((PathNameExpAS) host).getPathName());
					Property prop = realSourceType.lookupProperty(name);
					propCall.setReferredProperty(prop);
					propCall.setSource(realSemSource);
					type = prop.getType();
				}
			}
			result.setType(type);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	// Loop ExpressionsAS
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.IteratorExpAS' */
	public Object visit(IteratorExpAS host, Object data) {
		//
		//	Ambiguous syntactic rules 
		//	
		// iteratorExp ::= oclExpression '->' simpleName '(' varDecl [',' varDecl] '|' oclExpression ')'
		//
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and type ---
			IteratorExp result = new IteratorExp$Class();
			Classifier type = null;
			//--- Compute source type ---
			OclExpressionAS source = host.getSource();
			//--- Source is a selection ---
			if (source instanceof SelectionExpAS) {
				//--- Compute the real source and reals source type ---
				OclExpressionAS realSource = ((SelectionExpAS) source).getSource();
				OclExpression semRealSource = (OclExpression) realSource.accept(this, data);
				if (semRealSource == null){
					log.reportError("Semantic error in expression - "+realSource);
					return null;
				}
				Classifier sourceType = semRealSource.getType();
				//--- Valid source type ---
				if (sourceType != null) {
					//--- Source is a primitive type or a model type ---
					if (sourceType instanceof OclModelElementType || sourceType instanceof Primitive) {
						//--- Make a Set ---
						sourceType = processor.getTypeFactory().buildSetType((Classifier) sourceType);
					}
					if (sourceType instanceof CollectionType) {
						//--- Compute the name of the iterator ---
						String name = ((ArrowSelectionExpAS) host.getSource()).getName();
						//--- Compute the iterators and iterator types ---
						Classifier elementType = ((CollectionType) sourceType).getElementType();
						VariableDeclaration var1 = (VariableDeclaration) host.getIterator().accept(this, data);
						Classifier var1Type = var1.getType();
						if (var1Type == null || var1Type instanceof VoidType) {
							var1Type = elementType;
							var1.setType(elementType);
						}
						Classifier var2Type = null;
						VariableDeclaration var2 = null;
						if (host.getResult() != null) {
							var2 = (VariableDeclaration) host.getResult().accept(this, data);
							var2Type = var2.getType();
							if (var2Type == null || var2Type instanceof VoidType) {
								var2Type = elementType;
								var2.setType(elementType);
							}
						}
						//--- Create a new environment ---
						Environment env1 = env.addVariableDeclaration(var1.getName(), var1.getType(), Boolean.TRUE);
						if (var2 != null) {
							env1 = env1.addVariableDeclaration(var2.getName(), var2.getType(), Boolean.TRUE);
						}
						//--- Compute body's type ---
						Map data1 = new HashMap();
						data1.putAll((Map) data);
						data1.put("env", env1);
						OclExpressionAS bodyAS = host.getBody();
						OclExpression body = (OclExpression) bodyAS.accept(this, data1);
						Classifier bodyType = body.getType();
						//--- Compute iterator's signature ---
						List types = new Vector();
						types.add(var1Type);
						if (var2Type != null)
							types.add(var2Type);
						types.add(bodyType);
						//--- Check conformance between iterators and source types ---
						if (!elementType.conformsTo(var1Type).booleanValue()) {
							ErrorManager.reportError(log, location, "Element type '" + elementType + "' does not conform to iterator type '" + var1Type + "'");
							return null;
						}
						if (var2Type != null) {
							if (!elementType.conformsTo(var2Type).booleanValue()) {
								ErrorManager.reportError(log, location, "Element type '" + elementType + "' does not conform to iterator type '" + var2Type + "'");
								return null;
							}
						}
						//--- Search the iterator ---
						Operation oper = ((CollectionType) sourceType).lookupOperation(name, types);
						//--- Get the return type ---
						if (oper != null) {
							type = oper.getReturnType();
							// If 'collect' than set result's elementType to body.type
							if ("collect collectNested".indexOf(name) != -1 && type instanceof CollectionType)
								 ((CollectionType) type).setElementType(bodyType);
							//--- Complete result ---
							result.setSource(semRealSource);
							result.setName(name);
							result.setIterators(new LinkedHashSet());
							result.getIterators().add(var1);
							if (var2 != null)
								result.getIterators().add(var2);
							result.setBody(body);
							//--- Unknown operation ---
						} else {
							ErrorManager.reportError(log, location, "Unknown iterator '" + name + "' or type mismatch between parameters and arguments");
							return null;
						}
					} else {
						ErrorManager.reportError(log, location, "Left hand side of '->' operator should be a collection type");
						return null;
					}
				} else {
					ErrorManager.reportError(log, location, "Left hand side of '->' operator has a wrong type");
					return null;
				}
			}
			result.setType(type);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.IterateExpAS' */
	// iterateExp ::= oclExpression '->' 'iterate' '(' [varDecl ';' varDecl] '|' oclExpression ')'
	//
	public Object visit(IterateExpAS host, Object data) {
		//
		//	Ambiguous syntactic rules 
		//	
		// iterateExp ::= oclExpression '->' 'iterate' '(' [varDecl ';'] varDecl '|' oclExpression ')'
		//
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");

			//--- Compute the result ---
			IterateExp result = new IterateExp$Class();
			result.setIsMarkedPre(host.getIsMarkedPre());
			//--- Compute source ---
			OclExpression source = (OclExpression) host.getSource().accept(this, data);
			if (!(source.getType() instanceof CollectionType)) {
				ErrorManager.reportError(log, location, "Source of 'iterate' must be a collection");
				return null;
			}
			Classifier elementType = ((CollectionType) source.getType()).getElementType();
			result.setSource(source);
			//--- Compute iterators ---
			result.setIterators(new LinkedHashSet());
			VariableDeclaration var1 = null;
			if (host.getIterator() != null) {
				var1 = (VariableDeclaration) host.getIterator().accept(this, data);
				if (var1.getType() == null)
					var1.setType(elementType);
				if (!TypeConformance.isAssignableTo(elementType, var1.getType())) {
					ErrorManager.reportError(log, location, "Type of iterator '" + var1.getName() + "' does not conform to collection element type");
					return null;
				}
				result.getIterators().add(var1);
			}
			//--- Compute result ---
			VariableDeclaration var2 = null;
			if (host.getResult() != null) {
				var2 = (VariableDeclaration) host.getResult().accept(this, data);
				if (var2.getType() == null) {
					ErrorManager.reportWarning(log, location, "Missing type for accumulator '"+var2.getName()+"' in 'iterate' call");					
					var2.setType(elementType);
				}
				result.setResult(var2);
			}
			//--- Create a new environment ---
			Environment env1 = env;
			if (var1 != null)
				env1 = env1.addVariableDeclaration(var1.getName(), var1.getType(), new Boolean(true));
			if (var2 != null) {
				env1 = env1.addVariableDeclaration(var2.getName(), var2.getType(), new Boolean(true));
			}
			if (var1 != null || var2 != null) {
				Map data1 = new HashMap();
				data1.putAll((Map) data);
				data1.put("env", env1);
				data = data1;
			}
			result.setBody((OclExpression) host.getBody().accept(this, data));
			Classifier varType = result.getBody().getType();
			if (varType == null)
				varType = processor.getTypeFactory().buildOclAnyType();
			result.setType(varType);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	// Logical ExpressionsAS
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.NotExpAS' */
	public Object visit(NotExpAS host, Object data) {
		try {
			String opName = "not";
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and the type ---
			OperationCallExp opExp = new OperationCallExp$Class();
			Classifier type = null;
			// Check argument types
			OclExpression leftExp = (OclExpression) host.getLeftOperand().accept(this, data);
			type = leftExp.getType();
			if (type == null || !(type instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'not' method is not defined for the type '" + type + "'");
				type = processor.getTypeFactory().buildBooleanType();
			}
			opExp.setSource(leftExp);
			opExp.setName(opName);
			opExp.setReferredOperation(type.lookupOperation(opName, new ArrayList()));
			opExp.setType(type);
			leftExp.setAppliedProperty(opExp);
			return opExp;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.AndExpAS' */
	public Object visit(AndExpAS host, Object data) {
		try {
			String opName = "and";
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and the type ---
			OperationCallExp opExp = new OperationCallExp$Class();
			// Check argument types
			OclExpression leftExp = (OclExpression) host.getLeftOperand().accept(this, data);
			Classifier leftType = leftExp.getType();
			OclExpression rightExp = (OclExpression) host.getRightOperand().accept(this, data);
			Classifier rightType = rightExp.getType();
			if (!(leftType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the type '" + leftType + "'");
			} else if (!(rightType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the argument type '" + rightType + "'");
			}
			opExp.setSource(leftExp);
			opExp.setName(opName);
			List argumentTypes = new ArrayList();
			List arguments = new ArrayList();
			arguments.add(rightExp);
			argumentTypes.add(rightType);
			opExp.setReferredOperation(leftType.lookupOperation(opName, argumentTypes));
			opExp.setType(rightType);
			opExp.setArguments(arguments);
			leftExp.setAppliedProperty(opExp);
			return opExp;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.OrExpAS' */
	public Object visit(OrExpAS host, Object data) {
		try {
			String opName = "or";
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and the type ---
			OperationCallExp opExp = new OperationCallExp$Class();
			// Check argument types
			OclExpression leftExp = (OclExpression) host.getLeftOperand().accept(this, data);
			Classifier leftType = leftExp.getType();
			OclExpression rightExp = (OclExpression) host.getRightOperand().accept(this, data);
			Classifier rightType = rightExp.getType();
			if (!(leftType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the type '" + leftType + "'");
			} else if (!(rightType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the argument type '" + rightType + "'");
			}
			opExp.setSource(leftExp);
			opExp.setName(opName);
			List argumentTypes = new ArrayList();
			List arguments = new ArrayList();
			arguments.add(rightExp);
			argumentTypes.add(rightType);
			opExp.setReferredOperation(leftType.lookupOperation(opName, argumentTypes));
			opExp.setType(rightType);
			opExp.setArguments(arguments);
			leftExp.setAppliedProperty(opExp);
			return opExp;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.XorExpAS' */
	public Object visit(XorExpAS host, Object data) {
		try {
			String opName = "xor";
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and the type ---
			OperationCallExp opExp = new OperationCallExp$Class();
			// Check argument types
			OclExpression leftExp = (OclExpression) host.getLeftOperand().accept(this, data);
			Classifier leftType = leftExp.getType();
			OclExpression rightExp = (OclExpression) host.getRightOperand().accept(this, data);
			Classifier rightType = rightExp.getType();
			if (!(leftType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the type '" + leftType + "'");
			} else if (!(rightType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the argument type '" + rightType + "'");
			}
			opExp.setSource(leftExp);
			opExp.setName(opName);
			List argumentTypes = new ArrayList();
			List arguments = new ArrayList();
			arguments.add(rightExp);
			argumentTypes.add(rightType);
			opExp.setReferredOperation(leftType.lookupOperation(opName, argumentTypes));
			opExp.setType(rightType);
			opExp.setArguments(arguments);
			leftExp.setAppliedProperty(opExp);
			return opExp;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.ImpliesExpAS' */
	public Object visit(ImpliesExpAS host, Object data) {
		try {
			String opName = "implies";
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and the type ---
			OperationCallExp opExp = new OperationCallExp$Class();
			// Check argument types
			OclExpression leftExp = (OclExpression) host.getLeftOperand().accept(this, data);
			Classifier leftType = leftExp.getType();
			OclExpression rightExp = (OclExpression) host.getRightOperand().accept(this, data);
			Classifier rightType = rightExp.getType();
			if (!(leftType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the type '" + leftType + "'");
			} else if (!(rightType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The 'and' method is not defined for the argument type '" + rightType + "'");
			}
			opExp.setSource(leftExp);
			opExp.setName(opName);
			List argumentTypes = new ArrayList();
			List arguments = new ArrayList();
			arguments.add(rightExp);
			argumentTypes.add(rightType);
			opExp.setReferredOperation(leftType.lookupOperation(opName, argumentTypes));
			opExp.setType(rightType);
			opExp.setArguments(arguments);
			leftExp.setAppliedProperty(opExp);
			return opExp;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	// If expression
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.IfExpAS' */
	public Object visit(IfExpAS host, Object data) {
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result and the type ---
			IfExp result = new IfExp$Class();
			Classifier type = null;
			// Check condition type
			OclExpression condExp = (OclExpression) host.getCondition().accept(this, data);
			Classifier condType = condExp.getType();
			if (!(condType instanceof BooleanType)) {
				ErrorManager.reportError(log, location, "The condition of 'if' must be boolean");
				type = processor.getTypeFactory().buildBooleanType();
			}
			// Check then and else types
			OclExpression leftExp = (OclExpression) host.getThenExpression().accept(this, data);
			Classifier leftType = leftExp.getType();
			OclExpression rightExp = (OclExpression) host.getElseExpression().accept(this, data);
			Classifier rightType = rightExp.getType();
			type = checkTypes(leftType, rightType);
			if (type == null) {
				ErrorManager.reportError(log, location, "Different types for branches of 'if'");
			}
			result.setCondition(condExp);
			result.setThenExpression(leftExp);
			result.setElseExpression(rightExp);
			result.setType(type);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	// Let expression
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.LetExpAS' */
	public Object visit(LetExpAS host, Object data) {
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			//--- Initialize the result ---
			LetExp result = new LetExp$Class();
			// Create new argument
			LetExp lastLetExp = result;
			Iterator i = host.getVariables().iterator();
			//--- Store LET Expressions ---
			List letExps = new Vector();
			letExps.add(lastLetExp);
			while (i.hasNext()) {
				// Compute abstract and semantic variable
				VariableDeclarationAS varDeclAS = (VariableDeclarationAS) i.next();
				((Map) data).put("env", env);
				VariableDeclaration semVarDecl = (VariableDeclaration) varDeclAS.accept(this, data);
				if (semVarDecl == null) {
					log.reportError("Semantic error in expression - "+varDeclAS);
					return null;
				}
				// Add abstract variable inside the environment		
				env = env.addVariableDeclaration(varDeclAS.getName(), semVarDecl.getType(), new Boolean(false));
				// Add semantic variable to let expression
				lastLetExp.setVariable(semVarDecl);
				lastLetExp.setIsMarkedPre(host.getIsMarkedPre());
				if (i.hasNext()) {
					LetExp nextLetExp = new LetExp$Class();
					lastLetExp.setIn(nextLetExp);
					lastLetExp = nextLetExp;
					letExps.add(lastLetExp);
				}
			}
			// Create new data argument
			Map newData = new HashMap();
			newData.put("log", log);
			newData.put("env", env);
			lastLetExp.setIn((OclExpression) host.getIn().accept(this, newData));
			// Set type
			Classifier inType = lastLetExp.getIn().getType();
			i = letExps.iterator();
			while (i.hasNext()) {
				LetExp letExp = (LetExp)i.next();
				letExp.setType(inType); 
			}
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	//
	// OclMessage expression
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.OclMessageExpAS' */
	public Object visit(OclMessageExpAS host, Object data) {
		return null;
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.ExpressionsAS.OclMessageArgAS' */
	public Object visit(OclMessageArgAS host, Object data) {
		return null;
	}

	//
	// Types
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.TypesAS.CollectionAS' */
	public Object visit(CollectionTypeAS host, Object data) {
		try {
			OclExpression result;
			// Compute the type of element
			TypeExp tle = (TypeExp)host.getElementType().accept(this, data);
			Classifier subType = tle.getType();
			Classifier litType = processor.getTypeFactory().buildCollectionType(subType);
//			Classifier type = processor.getTypeFactory().buildTypeType(litType);
			result = new TypeExp$Class("Collection", Boolean.FALSE, litType);
//			result.setType(type);
			result.setType(litType);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.TypesAS.BagTypeAS' */
	public Object visit(BagTypeAS host, Object data) {
		try {
			OclExpression result;
			// Compute the type of element
			TypeExp tle = (TypeExp)host.getElementType().accept(this, data);
			Classifier subType = tle.getType();
			Classifier litType = processor.getTypeFactory().buildBagType(subType);
//			Classifier type = processor.getTypeFactory().buildTypeType(litType);
			result = new TypeExp$Class("Bag", Boolean.FALSE, litType);
//			result.setType(type);
			result.setType(litType);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.TypesAS.SetTypeAS' */
	public Object visit(SetTypeAS host, Object data) {
		try {
			OclExpression result;
			// Compute the type of element
			TypeExp tle = (TypeExp)host.getElementType().accept(this, data);
			Classifier subType = tle.getType();
			Classifier litType = processor.getTypeFactory().buildSetType(subType);
//			Classifier type = processor.getTypeFactory().buildTypeType(litType);
			result = new TypeExp$Class("Set", Boolean.FALSE, litType);
//			result.setType(type);
			result.setType(litType);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.TypesAS.OrderedSetTypeAS' */
	public Object visit(OrderedSetTypeAS host, Object data) {
		try {
			OclExpression result;
			// Compute the type of element
			TypeExp tle = (TypeExp)host.getElementType().accept(this, data);
			Classifier subType = tle.getType();
			Classifier litType = processor.getTypeFactory().buildOrderedSetType(subType);
//			Classifier type = processor.getTypeFactory().buildTypeType(litType);
			result = new TypeExp$Class("OrderedSet", Boolean.FALSE, litType);
//			result.setType(type);
			result.setType(litType);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.TypesAS.SequenceTypeAS' */
	public Object visit(SequenceTypeAS host, Object data) {
		try {
			OclExpression result;
			// Compute the type of element
			TypeExp tle = (TypeExp)host.getElementType().accept(this, data);
			Classifier subType = tle.getType();
			Classifier litType = processor.getTypeFactory().buildSequenceType(subType);
//			Classifier type = processor.getTypeFactory().buildTypeType(litType);
			result = new TypeExp$Class("Sequence", Boolean.FALSE, litType);
//			result.setType(type);
			result.setType(litType);
			return result;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.TypesAS.TupleTypeAS' */
	public Object visit(TupleTypeAS host, Object data) {
		try {
			// Create the abstract type
			TupleType type = processor.getTypeFactory().buildTupleType(new String[]{}, new Classifier[]{});
			// Add elements
			Iterator i = host.getVariableDeclarationList().iterator();
			while (i.hasNext()) {
				// Create a abstact variable declaration
				VariableDeclarationAS varDeclAS = (VariableDeclarationAS) i.next();
				VariableDeclaration varDecl = new VariableDeclaration$Class();
				varDecl.setName(varDeclAS.getName());
				TypeExp tle = (TypeExp)varDeclAS.getType().accept(this, data);
				Classifier varType = tle.getType();
				if (varType == null)
					varType = processor.getTypeFactory().buildOclAnyType();
				varDecl.setType(varType);
				// Add it
				type.getPartType().add(varDecl);
			}
			return type;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.AS.TypesAS.ClassifierAS' */
	public Object visit(ClassifierAS host, Object data) {
		try {
			//--- Unpack arguments ---
			Environment env = (Environment) ((Map) data).get("env");
			ILog log = (ILog) ((Map) data).get("log");
			// Check if it's a basic type

			List pathName = host.getPathName();
			/*	
			if (pathName.size() == 1) {
				String name = (String) pathName.get(0);
				Classifier type = (Classifier) basicDataTypes.get(name);
				if (type != null)
					return type;
			}
*/
			// Compute type
			ModelElement element = env.lookupPathName(pathName);
			if (element instanceof Classifier && element != null) {
				OclExpression result;
				Classifier litType = (Classifier)element;
//				Classifier type = processor.getTypeFactory().buildTypeType(litType);
				result = new TypeExp$Class(litType.toString(), Boolean.FALSE, litType);
//				result.setType(type);
				result.setType(litType);
				return result;
			} else {
				String typeStr = "";
				for(int i=0; i<pathName.size(); i++) {
					if (i != 0) typeStr += "::";
					typeStr += pathName.get(i);
				}
				ErrorManager.reportError(log, location, "Unknown classifier '"+typeStr+"'.");
				return this.processor.getTypeFactory().buildVoidType();
			}
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}

	public Object visit(OclMessageKindAS host, Object data) {
		return null;
	}

	/** Check if two abstract types are compatible and compute the proper type */
	public Classifier checkTypes(Classifier t1, Classifier t2) {
		try {
			//--- Compute the type ---
			Classifier type = null;
			if (t1.conformsTo(t2).booleanValue()) {
				type = t2;
			} else if (t2.conformsTo(t1).booleanValue()) {
				type = t1;
			}
			return type;
		} catch (Exception e) {
			if (DEBUG) e.printStackTrace(System.out);
			return null;
		}
	}
}
