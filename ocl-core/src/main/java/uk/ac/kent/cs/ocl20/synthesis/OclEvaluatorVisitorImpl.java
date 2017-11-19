/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.synthesis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor$Class;
import uk.ac.kent.cs.ocl20.semantics.bridge.CallAction;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.DataType;
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
import uk.ac.kent.cs.ocl20.semantics.bridge.SendAction;
import uk.ac.kent.cs.ocl20.semantics.bridge.Signal;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ClassifierContextDecl;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.Constraint;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ConstraintKind$Class;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.OperationContextDecl;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.PropertyContextDecl;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.BooleanLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionItem;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionKind;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionKind$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionLiteralPart;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionRange;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.EnumLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IfExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IntegerLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IterateExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.IteratorExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.LetExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OclExpression;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OclMessageArg;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OclMessageExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OperationCallExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.PropertyCallExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.RealLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.StringLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TupleLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TypeExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.UnspecifiedValueExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableDeclaration;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableExp;
import uk.ac.kent.cs.ocl20.semantics.model.types.BagType;
import uk.ac.kent.cs.ocl20.semantics.model.types.BooleanType;
import uk.ac.kent.cs.ocl20.semantics.model.types.CollectionType;
import uk.ac.kent.cs.ocl20.semantics.model.types.IntegerType;
import uk.ac.kent.cs.ocl20.semantics.model.types.OclAnyType;
import uk.ac.kent.cs.ocl20.semantics.model.types.OclMessageType;
import uk.ac.kent.cs.ocl20.semantics.model.types.OrderedSetType;
import uk.ac.kent.cs.ocl20.semantics.model.types.RealType;
import uk.ac.kent.cs.ocl20.semantics.model.types.SequenceType;
import uk.ac.kent.cs.ocl20.semantics.model.types.SetType;
import uk.ac.kent.cs.ocl20.semantics.model.types.StringType;
import uk.ac.kent.cs.ocl20.semantics.model.types.TupleType;
import uk.ac.kent.cs.ocl20.semantics.model.types.VoidType;
import uk.ac.kent.cs.ocl20.standard.lib.OclAny;
import uk.ac.kent.cs.ocl20.standard.lib.OclBag;
import uk.ac.kent.cs.ocl20.standard.lib.OclBoolean;
import uk.ac.kent.cs.ocl20.standard.lib.OclCollection;
import uk.ac.kent.cs.ocl20.standard.lib.OclInteger;
import uk.ac.kent.cs.ocl20.standard.lib.OclOrderedSet;
import uk.ac.kent.cs.ocl20.standard.lib.OclReal;
import uk.ac.kent.cs.ocl20.standard.lib.OclSequence;
import uk.ac.kent.cs.ocl20.standard.lib.OclSet;
import uk.ac.kent.cs.ocl20.standard.lib.OclString;
import uk.ac.kent.cs.ocl20.standard.lib.OclTuple;
import uk.ac.kent.cs.ocl20.standard.lib.OclUndefined;
import uk.ac.kent.cs.ocl20.standard.types.BagTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.BooleanTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.CollectionTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.IntegerTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.OrderedSetTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.RealTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.SequenceTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.SetTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.StringTypeImpl;
import uk.ac.kent.cs.ocl20.standard.types.VoidTypeImpl;

public class OclEvaluatorVisitorImpl extends SemanticsVisitor$Class implements SemanticsVisitor {
	public OclEvaluatorVisitorImpl(OclProcessor proc) {
		this.processor = proc;
	}

	protected OclProcessor processor = null;

	/** Invoke a method from the standard library adapater */
	protected Object getOclObject(String type_name, Object value) throws Exception {
		Class[] typeArr;
		Object[] valArr;
		if (value == null) {
			typeArr = new Class[] {};
			valArr = new Object[] {};
		} else {
			typeArr = new Class[] {value.getClass()};
			valArr = new Object[] {value};
		}
		Method m = getMethod(this.processor.getStdLibAdapter(), type_name, typeArr);
		try {
			return m.invoke(this.processor.getStdLibAdapter(), valArr);
		} catch (Exception e) {
			return null;
		}
	}
	 	
	/** Get a method using reflection */
	protected Method getMethod(Object source, String operName, Class[] types) throws Exception {
		Method method = null;
		try {
			//--- Search foe exact match ---
			try {
				method = source.getClass().getMethod(operName, types);
			} catch(Exception e) {
			}
			//--- Search compatible methods ---
			if (method == null) {
				Method methods[] = source.getClass().getMethods();
				for(int i=0; i<methods.length; i++) {
					String methodName = methods[i].getName();
					Class argTypes[] = methods[i].getParameterTypes();
					if (methodName.equals(operName) && types.length == argTypes.length) {
						boolean found = true;
						for(int j=0; j<types.length; j++) {
							if (!argTypes[j].isAssignableFrom(types[j])) {
								found = false;
								break;
							}
						}
						if (found)
							return methods[i];
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return method;
	}
	protected Method getMethod(String className, String operName, Class[] types) throws Exception {
		return Class.forName(className).getMethod(operName, types);
	}

	/** Temporary variables */
	protected static long tempVarCounter = 1;
	public static String newTempVar() {
		return "t"+tempVarCounter++;
	}
	public void resetCounter() {
		tempVarCounter = 1;
 	}
 	
	/** Check is value needs wrapping */
	protected static Set basicOclTypes = new LinkedHashSet();
	static {
		basicOclTypes.add(BooleanType.class);
		basicOclTypes.add(IntegerType.class);
		basicOclTypes.add(RealType.class);
		basicOclTypes.add(StringType.class);
		basicOclTypes.add(CollectionType.class);
		basicOclTypes.add(BagType.class);
		basicOclTypes.add(SetType.class);
		basicOclTypes.add(SequenceType.class);
		basicOclTypes.add(OrderedSetType.class);
		basicOclTypes.add(OclAnyType.class);
		basicOclTypes.add(VoidType.class);
		
		basicOclTypes.add(BooleanTypeImpl.class);
		basicOclTypes.add(IntegerTypeImpl.class);
		basicOclTypes.add(RealTypeImpl.class);
		basicOclTypes.add(StringTypeImpl.class);
		basicOclTypes.add(CollectionTypeImpl.class);
		basicOclTypes.add(BagTypeImpl.class);
		basicOclTypes.add(SetTypeImpl.class);
		basicOclTypes.add(SequenceTypeImpl.class);
		basicOclTypes.add(OrderedSetTypeImpl.class);
		basicOclTypes.add(OclAnyTypeImpl.class);
		basicOclTypes.add(VoidTypeImpl.class);
	}
	protected static boolean isBasicOclType(Classifier sourceType) {
		return basicOclTypes.contains(sourceType.getClass());
	}
	protected static Map needsWrapMap = new HashMap();
	static {
		needsWrapMap.put(OclCollection.class+"-sum", Boolean.TRUE);
		needsWrapMap.put(OclBag.class+"-sum", Boolean.TRUE);
		needsWrapMap.put(OclSet.class+"-sum", Boolean.TRUE);
		needsWrapMap.put(OclSequence.class+"-sum", Boolean.TRUE);
		needsWrapMap.put(OclOrderedSet.class+"-sum", Boolean.TRUE);

		needsWrapMap.put(OclSequence.class+"-at", Boolean.TRUE);
		needsWrapMap.put(OclSequence.class+"-first", Boolean.TRUE);
		needsWrapMap.put(OclSequence.class+"-last", Boolean.TRUE);

		needsWrapMap.put(OclOrderedSet.class+"-at", Boolean.TRUE);
		needsWrapMap.put(OclOrderedSet.class+"-first", Boolean.TRUE);
		needsWrapMap.put(OclOrderedSet.class+"-last", Boolean.TRUE);
	}
	protected static boolean needsOclWrapping(Classifier sourceType, String operName) {
		// Usually after a call over an OclType the result needs no wrapping
		if (isBasicOclType(sourceType)) {
			// Solve the exceptions
			return (needsWrapMap.get(sourceType.getClass()+"-"+operName) != null);
		}
		return true;
	}
	 
	protected Object wrapJavaObjectAsOclType(Classifier type, Object original) {
		Object result=original;
		if (original instanceof OclAny)
			return original;
		if (original instanceof OclCollection)
			return original;
		String name = getAdapterMethodName(type);
		if (name != null) {
			Class ocl = this.processor.getStdLibAdapter().getClass();
			Class argType = getJavaType((String)type.accept(this, null/*data*/), result);
			Method oclConv = null;
			try {
				oclConv = ocl.getMethod(name, new Class[] { argType });
			} catch (SecurityException e) {
				if (this.processor.getDebug().booleanValue()) e.printStackTrace();
			} catch (NoSuchMethodException e) {
				if (this.processor.getDebug().booleanValue()) e.printStackTrace();
			}
			try {
				result = oclConv.invoke(this.processor.getStdLibAdapter(), new Object[]{result});
			} catch (IllegalArgumentException e1) {
				if (this.processor.getDebug().booleanValue()) e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				if (this.processor.getDebug().booleanValue()) e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				if (this.processor.getDebug().booleanValue()) e1.printStackTrace();
			}
		} else {
			result = original;					
		}
		return result;
	}
	 	
 	/** Get the name for this.processor.getStdLibAdapter().XXX() */
 	protected static Map methodNames = new HashMap();
 	static {
		methodNames.put(BooleanType.class, "Boolean");
		methodNames.put(IntegerType.class, "Integer");
		methodNames.put(RealType.class, "Real");
		methodNames.put(StringType.class, "String");
		methodNames.put(CollectionType.class, "Collection");
		methodNames.put(BagType.class, "Bag");
		methodNames.put(SetType.class, "Set");
		methodNames.put(SequenceType.class, "Sequence");
		methodNames.put(OrderedSetType.class, "OrderedSet");
		methodNames.put(VoidType.class, "Undefined");

		methodNames.put(BooleanTypeImpl.class, "Boolean");
		methodNames.put(IntegerTypeImpl.class, "Integer");
		methodNames.put(RealTypeImpl.class, "Real");
		methodNames.put(StringTypeImpl.class, "String");
		methodNames.put(CollectionTypeImpl.class, "Collection");
		methodNames.put(BagTypeImpl.class, "Bag");
		methodNames.put(SetTypeImpl.class, "Set");
		methodNames.put(SequenceTypeImpl.class, "Sequence");
		methodNames.put(OrderedSetTypeImpl.class, "OrderedSet");
		methodNames.put(VoidTypeImpl.class, "Undefined");
 	}
 	protected static String getAdapterMethodName(Classifier type) {
 		String methodName = (String)methodNames.get(type.getClass());
 		return methodName;
 	}

	/** Get the Java class associated with a type name */
	protected static Map getJavaTypeMap = new HashMap();
	static {
		getJavaTypeMap.put("Classifier", java.lang.Class.class);
		getJavaTypeMap.put("OclBoolean", Boolean.class);
		getJavaTypeMap.put("OclInteger", Integer.class);
		getJavaTypeMap.put("OclReal", Double.class);
		getJavaTypeMap.put("OclString", String.class);
		getJavaTypeMap.put("OclBag", Collection.class);
		getJavaTypeMap.put("OclSet", Collection.class);
		getJavaTypeMap.put("OclOrderedSet", Collection.class);
		getJavaTypeMap.put("OclSequence", Collection.class);

		getJavaTypeMap.put("OclBooleanImpl", Boolean.class);
		getJavaTypeMap.put("OclIntegerImpl", Integer.class);
		getJavaTypeMap.put("OclRealImpl", Double.class);
		getJavaTypeMap.put("OclStringImpl", String.class);
		getJavaTypeMap.put("OclBagImpl", Collection.class);
		getJavaTypeMap.put("OclSetImpl", Collection.class);
		getJavaTypeMap.put("OclOrderedSetImpl", Collection.class);
		getJavaTypeMap.put("OclSequenceImpl", Collection.class);
	}
	protected static Class getJavaType(String name, Object value) {
		Class type = (Class)getJavaTypeMap.get(name);
		if (type == null)
			return value.getClass();
		else
			return type;
	}

	/** Get the OCL class associated with a type name */
	protected static Map getOclTypeMap = new HashMap();
	static {
		getOclTypeMap.put("OclBoolean", OclBoolean.class);
		getOclTypeMap.put("OclInteger", OclInteger.class);
		getOclTypeMap.put("OclReal", OclReal.class);
		getOclTypeMap.put("OclString", OclString.class);
		getOclTypeMap.put("OclBag", OclBag.class);
		getOclTypeMap.put("OclSet", OclSet.class);
		getOclTypeMap.put("OclOrderedSet", OclOrderedSet.class);
		getOclTypeMap.put("OclSequence", OclSequence.class);
	}
	protected static Class getOclType(String name, Object value) {
		Class type = (Class)getOclTypeMap.get(name);
		if (type == null)
			return value.getClass();
		else
			return type;
	}

	/** Convert = <> aso into function names */
	static protected Map nameMap = new HashMap();
	static {
		nameMap.put("=", "equalTo");
		nameMap.put("<>", "notEqualTo");
		nameMap.put("+", "add");
		nameMap.put("-", "subtract");
		nameMap.put("*", "multiply");
		nameMap.put("/", "divide");
		nameMap.put("<", "lessThan");
		nameMap.put(">", "greaterThan");
		nameMap.put("<=", "lessThanOrEqualTo");
		nameMap.put(">=", "greaterThanOrEqualTo");
	} 
	static protected String getFunctionName(Operation op) {
		String name = op.getName();
		Classifier type = op.getReturnType();
		int numParams = op.getParameterTypes().size();
		if (name.equals("abs") && numParams == 0 && type instanceof IntegerType)
			return "iabs";
		if (name.equals("-") && numParams == 0) {
			return ((type instanceof IntegerType) ? "i" : "") +"negate";
		}
		String result = (String)nameMap.get(name);
		if (result == null)
			return name;
		else
			return result;
	}
	
	//
	// Contexts
	//
	/** Visit class 'VariableDeclaration' */
	public Object visit(VariableDeclaration host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- Add (name, value) to env and return value ---
		String name = host.getName();
		Object initValue = null;
		if (host.getInitExpression() != null) {
			initValue = host.getInitExpression().accept(this, data);
		}
		env.setValue(name, initValue);	
		return initValue;
	}

	//
	// Expressions
	//
	//
	// Literal expressions
	//
	/** Visit class 'BooleanLiteralExp' */
	public Object visit(BooleanLiteralExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- result = literal's value ---
		return this.processor.getStdLibAdapter().Boolean(host.getBooleanSymbol());
	}

	/** Visit class 'TypeExp' */
	public Object visit(TypeExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");

		//--- result = literal's value ---
		return host.getLiteralType();
//		return this.processor.getTypeFactory().buildTypeType(host.getLiteralType());
//		return this.processor.getStdLibAdapter().Type((java.lang.Class) host.getType().getDelegate());
	}
	/** Visit class 'IntegerLiteralExp' */
	public Object visit(IntegerLiteralExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- result = literal's value ---
		return this.processor.getStdLibAdapter().Integer(host.getIntegerSymbol());
	}
	/** Visit class 'RealLiteralExp' */
	public Object visit(RealLiteralExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- result = literal's value ---
		return this.processor.getStdLibAdapter().Real(host.getRealSymbol());
	}
	/** Visit class 'StringLiteralExp' */
	public Object visit(StringLiteralExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- result = literal's value ---
		return this.processor.getStdLibAdapter().String(host.getStringSymbol());
	}
	/** Visit class 'EnumLiteralExp' */
	public Object visit(EnumLiteralExp host, Object data) {
//		return this.processor.getModelImplAdapter().getEnumLiteralValue(host.getReferredEnumLiteral());
		return host.getReferredEnumLiteral();
	}
	/** Visit class 'CollectionLiteralExp' */
	public Object visit(CollectionLiteralExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		String indent = (String)((Map)data).get("indent");

		//--- result = appropiate container ---
		Object result = this.processor.getStdLibAdapter().Bag();
		CollectionKind kind = host.getKind();
		if (kind == CollectionKind$Class.BAG) {
			result = this.processor.getStdLibAdapter().Bag();
		} else if (kind == CollectionKind$Class.ORDERED_SET) {
			result = this.processor.getStdLibAdapter().OrderedSet();
		} else if (kind == CollectionKind$Class.SEQUENCE) {
			result = this.processor.getStdLibAdapter().Sequence();
		} else if (kind == CollectionKind$Class.SET) {
			result = this.processor.getStdLibAdapter().Set();
		}
		//--- Add all parts ---
		Iterator i = host.getParts().iterator();
		while (i.hasNext()) {
			CollectionLiteralPart part = ((CollectionLiteralPart)i.next());
			// Item
			if (part instanceof CollectionItem) {
				if (part != null) {
					OclExpression expPart = ((CollectionItem)part).getItem();
					if (expPart != null) {
						Object value = expPart.accept(this, data);
						if (kind == CollectionKind$Class.BAG) {
							result = ((OclBag)result).including(value);
						} else if (kind == CollectionKind$Class.ORDERED_SET) {
							result = ((OclOrderedSet)result).including(value);
						} else if (kind == CollectionKind$Class.SEQUENCE) {
							result = ((OclSequence)result).including(value);
						} else if (kind == CollectionKind$Class.SET) {
							result = ((OclSet)result).including(value);
						}
					}
				}
			// Range
			} else {
				OclExpression first = ((CollectionRange)part).getFirst();
				OclExpression last = ((CollectionRange)part).getLast();
				Classifier firstType = first.getType();
				Classifier lastType = last.getType();
				if (firstType instanceof IntegerType && lastType instanceof IntegerType) {
					OclInteger firstValue = (OclInteger)first.accept(this, data);
					OclInteger lastValue = (OclInteger)last.accept(this, data);
					for(int j=this.processor.getStdLibAdapter().impl(firstValue).intValue(); j<=this.processor.getStdLibAdapter().impl(lastValue).intValue(); j++) {
						OclInteger value = this.processor.getStdLibAdapter().Integer(j);
						if (kind == CollectionKind$Class.BAG) {
							result = ((OclBag)result).including(value);
						} else if (kind == CollectionKind$Class.ORDERED_SET) {
							result = ((OclOrderedSet)result).including(value);
						} else if (kind == CollectionKind$Class.SEQUENCE) {
							result = ((OclSequence)result).including(value);
						} else if (kind == CollectionKind$Class.SET) {
							result = ((OclSet)result).including(value);
						}
					}
				}
			}
		}
		return result;
	}
	/** Visit class 'TupleLiteralExp' */
	public Object visit(TupleLiteralExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- result = OclTuple ---
		OclTuple result = this.processor.getStdLibAdapter().Tuple( new HashMap() );
		//--- add all properties ---
		Iterator i = host.getTuplePart().iterator();
		while (i.hasNext()) {
			VariableDeclaration var = (VariableDeclaration)i.next();
			if (var != null) {
				String varName = var.getName();
				Object value = null;
				OclExpression initValue = var.getInitExpression();
				if (initValue != null) 
					value = initValue.accept(this, data);
				result.setProperty(this.processor.getStdLibAdapter().String(varName), value);
			}
		}
		return result;
	}

	//
	// Call expressions
	//
	/** Visit class 'OperationCallExp' */
	public Object visit(OperationCallExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		Object source = host.getSource().accept(this, data);
		Classifier sourceType = host.getSource().getType();
		//--- Compute arguments and argument types ---
		List staticTypes = new Vector();
		List dynamicTypes = new Vector();
		List oclArgs = new Vector();
		List javaArgs = new Vector();
		Iterator argIt = host.getArguments().iterator();
		while (argIt.hasNext()) {
			//--- Get current argument
			OclExpression arg = (OclExpression) argIt.next();

			//--- Compute the value of the argument and unwrapped it if necessary
			Object value = null;
			if (arg != null) {
				value = arg.accept(this, data);
			}
			//--- Objects from standard library: String, Integer, Real
			Object dynamicType = null;
			if (value instanceof OclAny) {
				oclArgs.add(value);
				javaArgs.add(((OclAny) value).getImpl());
				dynamicType = ((OclAny) value).getImpl().getClass();
			//--- Objects from standard library
			} else if (value instanceof OclCollection) {
				oclArgs.add(value);
				javaArgs.add(((OclCollection) value).getImpl());
				dynamicType = ((OclCollection) value).getImpl().getClass();
			//--- Objects from model
			} else {
//				oclArgs.add(processor.getStdLibAdapter().OclAny(value));
				oclArgs.add(value);
				javaArgs.add(value);
				dynamicType = value.getClass();
			}
			
			//--- Compute the type and Class delegate
			Classifier type = arg.getType();
			staticTypes.add(type);
//			dynamicTypes.add(type.getDelegate());
			dynamicTypes.add(dynamicType);
		}
		//--- Compute function name
		String operName = getFunctionName(host.getReferredOperation());
		if (operName.equals("_default") && sourceType instanceof VoidType) {
			dynamicTypes = new Vector();
			staticTypes = new Vector();
		}
		//--- Compute the result
		Object result = null;
		// Is an OclAny operation: =, <>, oclIsNew ...
		Operation declaredOp = processor.getTypeFactory().buildOclAnyType().lookupOperation(host.getReferredOperation().getName(), staticTypes);
		boolean isOclAnyOperation = declaredOp != null;
		// Source is a OCL collection
		if (sourceType instanceof CollectionType) {
			//Assumed to be an operation on ocl lib type
			OclCollection oclColl = null;
			if (source instanceof OclCollection)
				oclColl = (OclCollection) source;
			else
				oclColl = processor.getStdLibAdapter().OclCollection((java.util.Collection) source);
			result = invokeOclLibOperation(sourceType, oclColl, operName, staticTypes, oclArgs, dynamicTypes, javaArgs, log);
		// Source is an enumeration
		} else if (sourceType instanceof Enumeration_) {
			// OclAny operation
			if (isOclAnyOperation) {
				// Invoke the operation
				result = invokeOclAnyOperation(sourceType, source, operName, staticTypes, oclArgs, dynamicTypes, javaArgs, log);
			// Error
			} else {
				log.reportError("Unknown operation on enumeration");
			}
		// Operations on OclAny - including:
		//     Boolean, Integer, Real, String, OclTuple, OclState, OclUndefined  and 
		//     Model Elements 
		} else 	if (sourceType instanceof OclAnyType) {
			// Ocl objects
			if (source instanceof OclAny) { 
				// OclAny operation
				if (isOclAnyOperation) {
					// Invoke the operation
					result = invokeOclAnyOperation(sourceType, source, operName, staticTypes, oclArgs, dynamicTypes, javaArgs, log);
				} else {
					OclAny oclObj = processor.getStdLibAdapter().OclAny(source);
					result = invokeOclLibOperation(sourceType, oclObj, operName, staticTypes, oclArgs, dynamicTypes, javaArgs, log);
				}
			// Model elements
			} else {
				// OclAny operation
				if (isOclAnyOperation) {
					// Invoke the operation
					result = invokeOclAnyOperation(sourceType, source, operName, staticTypes, oclArgs, dynamicTypes, javaArgs, log);
				//Assumed to be a user model operation
				} else {
					result = invokeModelOperation(sourceType, source, operName, dynamicTypes, javaArgs, dynamicTypes, javaArgs, log);
				}				
			}
		// Else
		} else {
			log.reportError("Cannot match operation");
		}
		if (result == null)
			return this.processor.getStdLibAdapter().Undefined();
		return result;
	}

	protected Object invokeModelOperation(
		Classifier sourceType, Object source, String operName, 
		List staticTypes, List oclArgs, List dynamicTypes, List javaArgs, ILog log) 
	{
		Object result = null;
		try {
			Method oper = getMethod(source, operName, (Class[]) dynamicTypes.toArray(new Class[] {}));
			if (source != null) {
				if (oper != null) {
					result = oper.invoke(source, javaArgs.toArray());
				} else {
					//try converting source into an OclAnyModelElement ?
					log.reportError("Operation " + operName + dynamicTypes + " not found on object " + source);
				}
			}
		} catch (Exception e) {
			if (this.processor.getDebug().booleanValue()) e.printStackTrace();
		}
		return this.processor.getStdLibAdapter().OclAny(result);
	}

	protected Object invokeOclLibOperation(
		Classifier sourceType, Object source, String operName, 
		List staticTypes, List oclArgs, List dynamicTypes, List javaArgs, ILog log) 
	{
		Object result = null;
		List types = new Vector();
		Iterator i = staticTypes.iterator();
		while (i.hasNext()) {
			Classifier c = (Classifier) i.next();
			types.add(c.getImplClass());
		}
		try {
			Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
			if (source != null) {
				if (oper != null) {
					result = oper.invoke(source, oclArgs.toArray());
				} else {
					//try converting source into an OclAnyModelElement
					log.reportError("Operation " + operName + types + " not found on object " + source);
				}
			}
		} catch (Exception e) {
			if (this.processor.getDebug().booleanValue()) e.printStackTrace();
		}
		return result;
	}
	protected Object invokeEnumerationOperation(
		Classifier sourceType, Object source, String operName, 
		List staticTypes, List oclArgs, List dynamicTypes, List javaArgs, ILog log) 
	{
		Object result = null;
		List types = new Vector();
		Iterator i = staticTypes.iterator();
		while (i.hasNext()) {
			DataType c = (DataType) i.next();
			types.add(c.getImplClass());
		}
		try {
			//--- Invoke the right operation for Model Elements and Enumerations 
			if (operName.equals("equalTo")) {
				if (sourceType instanceof OclModelElementType) {
					boolean bool = this.processor.getModelImplAdapter().OclModelElement_equalTo(source, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				} else if (sourceType instanceof Enumeration_) {
					boolean bool = this.processor.getModelImplAdapter().EnumLiteral_equalTo(source, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				}
			} else if (operName.equals("notEqualTo")) {
				if (sourceType instanceof OclModelElementType) {
					boolean bool = !this.processor.getModelImplAdapter().OclModelElement_equalTo(source, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (sourceType instanceof Enumeration_) {
					boolean bool = !this.processor.getModelImplAdapter().EnumLiteral_equalTo(source, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				}
			}
		} catch (Exception e) {
			if (this.processor.getDebug().booleanValue()) e.printStackTrace();
		}
		return result;
	}

	protected Object invokeOclAnyOperation(
		Classifier sourceType, Object source, String operName, 
		List staticTypes, List oclArgs, List dynamicTypes, List javaArgs, ILog log)
	{ 
		Object result = null;
		List types = new Vector();
		Iterator i = staticTypes.iterator();
		while (i.hasNext()) {
			DataType c = (DataType) i.next();
			types.add(c.getImplClass());
		}
		try {
			//--- Invoke the right operation for Model Elements and Enumerations 
			if (operName.equals("equalTo")) {
				if (sourceType instanceof Enumeration_) {
					boolean bool = this.processor.getModelImplAdapter().EnumLiteral_equalTo(source, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (sourceType instanceof OclAnyType) {
					// Standard library object
					if (source instanceof OclAny) {
						Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
						result = oper.invoke(source, oclArgs.toArray());
					// Model element types
					} else {
						boolean bool = this.processor.getModelImplAdapter().OclModelElement_equalTo(source, javaArgs.get(0));
						result = this.processor.getStdLibAdapter().Boolean(bool);
					} 
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				} 
			} else if (operName.equals("notEqualTo")) {
				if (sourceType instanceof Enumeration_) {
					boolean bool = !this.processor.getModelImplAdapter().EnumLiteral_equalTo(source, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (sourceType instanceof OclAnyType) { 
					// Standard library object
					if (source instanceof OclAny) {
						Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
						result = oper.invoke(source, oclArgs.toArray());
					// Model element types
					} else {
						boolean bool = !this.processor.getModelImplAdapter().OclModelElement_equalTo(source, javaArgs.get(0));
						result = this.processor.getStdLibAdapter().Boolean(bool);
					}
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				} 
			} else if (operName.equals("oclIsNew")) {
				if (sourceType instanceof Enumeration_) {
					boolean bool = this.processor.getModelImplAdapter().EnumLiteral_oclIsNew(source);
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (sourceType instanceof OclAnyType) { 
					// Standard library object
					if (source instanceof OclAny) {
						Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
						result = oper.invoke(source, javaArgs.toArray());
					// Model element types
					} else {
						boolean bool = this.processor.getModelImplAdapter().OclModelElement_oclIsNew(source);
						result = this.processor.getStdLibAdapter().Boolean(bool);
					}
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				} 
			} else if (operName.equals("oclIsUndefined")) {
				if (sourceType instanceof Enumeration_) {
					boolean bool = this.processor.getModelImplAdapter().EnumLiteral_oclIsUndefined(source);
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (sourceType instanceof OclAnyType) { 
					// Standard library object
					if (source instanceof OclAny) {
						Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
						result = oper.invoke(source, javaArgs.toArray());
					// Model element types
					} else {
						boolean bool = this.processor.getModelImplAdapter().OclModelElement_oclIsUndefined(source);
						result = this.processor.getStdLibAdapter().Boolean(bool);
					}
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				} 
			} else if (operName.equals("oclIsKindOf")) {
				if (sourceType instanceof Enumeration_) {
					boolean bool = this.processor.getModelImplAdapter().EnumLiteral_oclIsKindOf(source, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (sourceType instanceof OclAnyType) { 
					// Standard library object
					if (source instanceof OclAny) {
						Method oper = getMethod(source, operName, new Class[] {Classifier.class});
						result = oper.invoke(source, javaArgs.toArray());
					// Model element types
					} else {
						boolean bool = this.processor.getModelImplAdapter().OclModelElement_oclIsKindOf(source, javaArgs.get(0));
						result = this.processor.getStdLibAdapter().Boolean(bool);
					}
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				} 
			} else if (operName.equals("oclIsTypeOf")) {
				if (sourceType instanceof Enumeration_) {
					boolean bool = this.processor.getModelImplAdapter().EnumLiteral_oclIsTypeOf(sourceType, javaArgs.get(0));
					result = this.processor.getStdLibAdapter().Boolean(bool);
				}
				else if (sourceType instanceof OclAnyType) { 
					// Standard library object
					if (source instanceof OclAny) {
						Method oper = getMethod(source, operName, new Class[] {Classifier.class});
						result = oper.invoke(source, javaArgs.toArray());
					// Model element types
					} else {
						boolean bool = this.processor.getModelImplAdapter().OclModelElement_oclIsTypeOf(sourceType, javaArgs.get(0));
						result = this.processor.getStdLibAdapter().Boolean(bool);
					}
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				} 
			} else if (operName.equals("oclAsType")) {
				if (sourceType instanceof Enumeration_) {
					result = this.processor.getModelImplAdapter().EnumLiteral_oclAsType(source, javaArgs.get(0));
				}
				else if (sourceType instanceof OclAnyType) { 
					// Standard library object
					if (source instanceof OclAny) {
						Method oper = getMethod(source, operName, new Class[] {Classifier.class});
						result = oper.invoke(source, javaArgs.toArray());
					// Model element types
					} else {
						result = this.processor.getModelImplAdapter().OclModelElement_oclAsType(source, javaArgs.get(0));
					}
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				} 
			} else if (operName.equals("allInstances")) {
				if (sourceType instanceof Enumeration_) {
					Collection col = this.processor.getModelImplAdapter().EnumLiteral_allInstances(source);
					result = this.processor.getStdLibAdapter().Set(col);
				}
				else if (sourceType instanceof OclAnyType) { 
					// Standard library object
					if (source instanceof OclAny) {
						Collection col = this.processor.getModelImplAdapter().OclModelElement_allInstances(source);
						result = this.processor.getStdLibAdapter().Set(col);
					// Model element types
					} else {
						Collection col = this.processor.getModelImplAdapter().OclModelElement_allInstances(source);
						result = this.processor.getStdLibAdapter().Set(col);
					}
				}
				else if (source != null) {
					Method oper = getMethod(source, operName, (Class[]) types.toArray(new Class[] {}));
					result = oper.invoke(source, javaArgs.toArray());
				}
			}
		} catch (Exception e) {
			if (processor.getDebug().booleanValue()) e.printStackTrace();
		}
		return result;
	}
	/** Visit class 'PropertyCallExp' */
	public Object visit(PropertyCallExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- Compute result
		Object result = null;
		// Enumeration
		Property prop = host.getReferredProperty();
		if (prop instanceof EnumLiteral) {
			result = this.processor.getModelImplAdapter().getEnumLiteralValue((EnumLiteral)prop);
		// Usual property
		} else { 
			//--- Compute source and getter name ---
			Object source = host.getSource().accept(this, data);
			if (source == null || source instanceof OclUndefined)
				return this.processor.getStdLibAdapter().Undefined();
			Classifier sourceType = host.getSource().getType();
			if (source instanceof OclAny)
				source = ((OclAny) source).getImpl();
			String operName = this.processor.getModelImplAdapter().getGetterName(host.getReferredProperty().getName());
			try {
				// Compute getter
				Method getter = getMethod(source, operName, new Class[]{});
				if (getter == null)
					return this.processor.getStdLibAdapter().Undefined();
				// Invoke getter
				if (source != null)
					result = getter.invoke(source, new Object[] {
				});
				//if result of propertyCall is null convert it to Undefined
				//  (the convertion done at end is only activated if source was null)
				if (result == null)
					return this.processor.getStdLibAdapter().Undefined();
				// Add required conversions to OCL types
				// Operations from model do not return OCL types; most of Stdlib operation do
				if (needsOclWrapping(sourceType, operName)) {
					Classifier propertyType = host.getReferredProperty().getType();
					String name = getAdapterMethodName(propertyType);
					if (name != null) {
						Class ocl = this.processor.getStdLibAdapter().getClass();
						Class argType = getJavaType((String)propertyType.accept(this, data), result);
						Method oclConv = ocl.getMethod(name, new Class[] {argType});
						result = oclConv.invoke(this.processor.getStdLibAdapter(), new Object[]{result});
					} else {
						// result = (propertyType)result: implicit					
					}
				}
			} catch (Exception e) {
				if (processor.getDebug().booleanValue()) e.printStackTrace();
			}
		}
		return (result==null) ? this.processor.getStdLibAdapter().Undefined() : result;
	}
	
	//
	// Loop expressions
	//
	/** Compute attributes for an iterator */
	protected Map computeIteratorAttributes(OclExpression source, VariableDeclaration var, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");

		/** Compute name, type, and init value */
		String name = null;
		Classifier type = null;
		Object value = null;
		if (var != null) {
			name = var.getName();
			Classifier type1 = var.getType();
			Classifier elementType = ((CollectionType) source.getType()).getElementType(); 
			if (type == null)
				type = elementType;
			if (var.getInitExpression() != null)
				value = var.getInitExpression().accept(this, data);
		} else {
			name = newTempVar();
			type = (Classifier) ((CollectionType) source.getType()).getElementType().accept(this, data);
		}

		//--- Return result ---
		Map result = new HashMap();
		result.put("name", name);
		result.put("type", type);
		result.put("value", value);
		return result;
	}
	/** Initialize an iterator according to source type and value */
	protected java.util.Iterator initIterator(Classifier sourceType, Object sourceValue, ILog log) {
		if (sourceValue instanceof OclUndefined) {
			log.reportError("Constructing an iterator over - "+sourceValue+"\n  expected type - "+sourceType);
			return null;
		}
		java.util.Iterator i = null;
		if (sourceValue instanceof OclCollection) {
			i = ((java.util.Collection)((OclCollection)sourceValue).getImpl()).iterator();			
		} else if (sourceValue instanceof java.util.Collection) {
			i = ((java.util.Collection)sourceValue).iterator();
		}
		return i;
	}
	/** Generate code for 'exists' */
	protected OclBoolean exists(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init flag
		OclBoolean result = this.processor.getStdLibAdapter().Boolean(false);
		//--- For each element from source
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			//--- Break the loop
			result = (OclBoolean)bodyValue;		
			if (this.processor.getStdLibAdapter().impl(result).booleanValue())
				break;
		}
		return result;
	}
	/** Generate code for 'forAll' */
	protected Object forAll(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init flag
		OclBoolean result = this.processor.getStdLibAdapter().Boolean(true);
		//--- For each element from source
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			//--- Break loop
			result = (OclBoolean)bodyValue;		
			if (!this.processor.getStdLibAdapter().impl(result).booleanValue())
				break;
		}
		return result;
	}
	/** Generate code for 'isUnique' */
	protected Object isUnique(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init flag
		OclBoolean result = this.processor.getStdLibAdapter().Boolean(true);
		//--- For each element from source
		OclSet tempSet = this.processor.getStdLibAdapter().Set();
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			//--- Compute result and break loop
			OclBoolean found = tempSet.includes(bodyValue);
			tempSet = tempSet.including(bodyValue);
			if(this.processor.getStdLibAdapter().impl(found).booleanValue()) {
				result = this.processor.getStdLibAdapter().Boolean(false);
				break;
			} 
		}
		return result;
	}
	/** Generate code for 'any' */
	protected Object any(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init result ---
		Object result = this.processor.getStdLibAdapter().Undefined();
		//--- For each element from source ---
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		if (i == null)
			return this.processor.getStdLibAdapter().Undefined();
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			if (((Boolean) ((OclBoolean) bodyValue).getImpl()).booleanValue()) {
				result = it1Value;
				break;
			}
		}
		return result;
	}
	/** Generate code for 'one' */
	protected Object one(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init flag ---
		OclBoolean result = this.processor.getStdLibAdapter().Boolean(false);
		OclSet tempSet = this.processor.getStdLibAdapter().Set();
		//--- For each element from source --- 
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		int counter = 0;
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			//--- Compute result and break loop
			if (this.processor.getStdLibAdapter().impl((OclBoolean)bodyValue).booleanValue()) {
				counter++;
				if (counter == 1)
					result = this.processor.getStdLibAdapter().Boolean(true);
				if (counter > 1) {
					result = this.processor.getStdLibAdapter().Boolean(false);
					break;
				}
			}
		}
		return result;
	}
	/** Generate code for 'collect' */
	protected Object collect(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		return collectNested(host, var1, var2, body, data);
	}
	/** Initialize a collection according to type */
	protected OclCollection initCollection(Classifier hostType) {
		OclCollection result = null;
		if (hostType instanceof BagType) {
			result = this.processor.getStdLibAdapter().Bag();
		} else if (hostType instanceof OrderedSetType) {
			result = this.processor.getStdLibAdapter().OrderedSet();
		} else if (hostType instanceof SetType) {
			result = this.processor.getStdLibAdapter().Set();
		} else if (hostType instanceof SequenceType) {
			result = this.processor.getStdLibAdapter().Sequence();
		}
		return result;
	}
	/** Generate code for 'select' */
	protected Object select(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init result ---
		Classifier hostType = host.getType();
		OclCollection result = initCollection(hostType);
		//--- Iterate all the elements from source ---
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object obj = body.accept(this, newData);
			OclBoolean bodyValue = (OclBoolean)obj; 
			//--- Compute result 
			if ( ((Boolean)bodyValue.getImpl()).booleanValue() ) {
				if (hostType instanceof BagType) {
					result = ((OclBag)result).including(it1Value);					
				} else if (hostType instanceof OrderedSetType) {
					// why test for contains ?
					if (! (this.processor.getStdLibAdapter().impl(result).contains(it1Value)) ) {
						result = ((OclOrderedSet)result).including(it1Value);
					}
				} else if (hostType instanceof SetType) {
					result = ((OclSet)result).including(it1Value);					
				} else if (hostType instanceof SequenceType) {
					result = ((OclSequence)result).including(it1Value);					
				}
			}
		}
		return result;
	}
	/** Generate code for 'reject' */
	protected Object reject(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init result ---
		Classifier hostType = host.getType();
		OclCollection result = initCollection(hostType);
		//--- For each element from source ---
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			//--- Compute result ---
			if (!this.processor.getStdLibAdapter().impl((OclBoolean)bodyValue).booleanValue()) {
				if (hostType instanceof BagType) {
					result = ((OclBag)result).including(it1Value);					
				} else if (hostType instanceof OrderedSetType) {
					if (!this.processor.getStdLibAdapter().impl(result).contains(it1Value)) {
						result = ((OclOrderedSet)result).including(it1Value);
					}
				} else if (hostType instanceof SetType) {
					result = ((OclSet)result).including(it1Value);					
				} else if (hostType instanceof SequenceType) {
					result = ((OclSequence)result).including(it1Value);					
				}
			}
		}
		return result;
	}
	/** Generate code for 'collectedNested' */
	protected Object collectNested(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Compute result ---
		//--- Init result ---
		Classifier hostType = host.getType();
		OclCollection result = initCollection(hostType);
		//--- For each element from source ---
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			//--- Compute result ---
			if (hostType instanceof BagType) {
				result = ((OclBag)result).including(bodyValue);					
			} else if (hostType instanceof OrderedSetType) {
				if (!this.processor.getStdLibAdapter().impl(result).contains(it1Value)) {
					result = ((OclOrderedSet)result).including(bodyValue);
				}
			} else if (hostType instanceof SetType) {
				result = ((OclSet)result).including(bodyValue);					
			} else if (sourceType instanceof SequenceType) {
				result = ((OclSequence)result).including(bodyValue);					
			}
		}
		//--- Flatten ---
		if (hostType instanceof BagType) {
			result = ((OclBag)result).flatten();
		} else if (hostType instanceof OrderedSetType) {
			result = ((OclOrderedSet)result).flatten();
		} else if (hostType instanceof SetType) {
			result = ((OclSet)result).flatten();
		} else if (hostType instanceof SequenceType) {
			result = ((OclSequence)result).flatten();
		}
		return result;
	}
	/** Generate code for 'sortedBy' */
	protected Object sortedBy(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog)((Map)data).get("log");

		//--- Compute source ---
		OclExpression source = host.getSource();
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();

		//--- Check if the collection can be sorted ---
		Classifier bodyType = body.getType();
		if (!(bodyType instanceof RealType )) {
			log.reportError("'sortedBy' is only suppoted for types which conformTo Collection(Real), tried on "+sourceType);
			return null;
		}

		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		Classifier it1Type = (Classifier)it1.get("type");
		Object it1Value = (String)it1.get("value");
		//--- Second optional iterator ---
		String it2Name = null;
		Classifier it2Type = null;
		Object it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (Classifier)it1.get("type");
			it2Value = it1.get("value");
		}
		//--- Iterate all the elements from collection ---
		List list = new Vector();
		java.util.Iterator i = initIterator(sourceType, sourceValue, log);
		// Create a new environment to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		//--- Sort the result ---
		//--- Compute bodyList and itemList ---
		List bodyList = new Vector();
		List itemList = new Vector();
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			Object bodyValue = body.accept(this, newData);
			bodyList.add(bodyValue);
			itemList.add(it1Value);
		}
		//--- Sort item list ---
		boolean ordered = true;
		do {
			ordered = true;
			for(int k=0; k<itemList.size()-1; k++) {
				OclReal k1 = (OclReal)bodyList.get(k);
				OclReal k2 = (OclReal)bodyList.get(k+1);
				if (this.processor.getStdLibAdapter().impl(k1.greaterThan(k2)).booleanValue()) {
					Object temp = k1;
					bodyList.set(k, k2);					
					bodyList.set(k+1, temp);					
					temp = itemList.get(k);
					itemList.set(k, itemList.get(k+1));					
					itemList.set(k+1, temp);
					ordered = false;					
				}
			}
		} while (!ordered);
		//--- Compute result ---
		OclCollection result = null;
		Classifier hostType = host.getType();
		if (hostType instanceof BagType) {
			result = this.processor.getStdLibAdapter().Sequence(itemList);
		} else if (hostType instanceof OrderedSetType) {
			result = this.processor.getStdLibAdapter().OrderedSet(itemList);
		} else if (hostType instanceof SetType) {
			result = this.processor.getStdLibAdapter().OrderedSet(itemList);
		} else if (hostType instanceof SequenceType) {
			result = this.processor.getStdLibAdapter().Sequence(itemList);
		}
		return result;
	}
	/** Visit class 'IteratorExp' */
	public Object visit(IteratorExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		String indent = (String)((Map)data).get("indent");

		//--- Generate Java code and compute result ---
		String name = host.getName();
		VariableDeclaration var1 = null;
		if (host.getIterators().size() >= 1)
			var1 = (VariableDeclaration) host.getIterators().toArray()[0];
		VariableDeclaration var2 = null;
		if (host.getIterators().size() >= 2)
			var2 = (VariableDeclaration) host.getIterators().toArray()[1];
		OclExpression body = host.getBody();
		Object result = null;
		if (name.equals("exists"))
			result = exists(host, var1, var2, body, (Map) data);
		else if (name.equals("forAll"))
			result = forAll(host, var1, var2, body, (Map) data);
		else if (name.equals("isUnique"))
			result = isUnique(host, var1, var2, body, (Map) data);
		else if (name.equals("any"))
			result = any(host, var1, var2, body, (Map) data);
		else if (name.equals("one"))
			result = one(host, var1, var2, body, (Map) data);
		else if (name.equals("collect"))
			result = collect(host, var1, var2, body, (Map) data);
		else if (name.equals("select"))
			result = select(host, var1, var2, body, (Map) data);
		else if (name.equals("reject"))
			result = reject(host, var1, var2, body, (Map) data);
		else if (name.equals("collectNested"))
			result = collectNested(host, var1, var2, body, (Map) data);
		else if (name.equals("sortedBy"))
			result = sortedBy(host, var1, var2, body, (Map) data);
		return result;
	}
	protected Object iterate(OclExpression source, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		String indent = (String)((Map)data).get("indent");

		//--- Compute source ---
		Object sourceValue = source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- First iterator ---
		String it1Name = null;
		Classifier it1Type = null;
		Object it1Value = null;
		if (var1 != null) {
			it1Name = var1.getName();
			it1Type = var1.getType();
			if (it1Type == null)
				it1Type = ((CollectionType) source.getType()).getElementType();
			if (var1.getInitExpression() != null)
				it1Value = var1.getInitExpression().accept(this, data);
		} else {
			it1Name = "i1";
			it1Type = ((CollectionType)source.getType()).getElementType();
		}
		//--- Accumulator ---
		String accName = null;
		Classifier accType = null;
		Object accValue = null;
		if (var2 != null) {
			accName = var2.getName();
			accType = var2.getType();
			accValue = var2.getInitExpression().accept(this, data);
		}
		//--- Compute result ---
		java.util.Iterator i = null;
		if (sourceType instanceof BagType) {
			i = this.processor.getStdLibAdapter().impl((OclBag)sourceValue).iterator();
		} else if (sourceType instanceof OrderedSetType) {
			i = this.processor.getStdLibAdapter().impl((OclOrderedSet)sourceValue).iterator();
		} else if (sourceType instanceof SetType) {
			i = this.processor.getStdLibAdapter().impl((OclSet)sourceValue).iterator();
		} else if (sourceType instanceof SequenceType) {
			i = this.processor.getStdLibAdapter().impl((OclSequence)sourceValue).iterator();
		}
		// Create a new environemnet to add iterator
		RuntimeEnvironment newEnv = env.newEnvironment();
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		while (i.hasNext()) {
			it1Value = i.next();
			newEnv.setValue(it1Name, it1Value);
			newEnv.setValue(accName, accValue);
			//--- Generate body ---
			Object bodyValue = body.accept(this, newData);
			accValue = bodyValue;		
		}
		return accValue;
	}
	
	/** Visit class 'IterateExp' */
	public Object visit(IterateExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		String indent = (String)((Map)data).get("indent");

		//--- Generate Java code and compute result ---
		OclExpression source = host.getSource();
		VariableDeclaration var1 = null;
		if (host.getIterators() != null && host.getIterators().size() > 0) {
			var1 = (VariableDeclaration)host.getIterators().toArray()[0];
		}
		VariableDeclaration var2 = (VariableDeclaration)host.getResult();
		OclExpression body = host.getBody();
		return iterate(source, var1, var2, body, (Map)data);
	}

	//
	// Others
	//
	/** Visit class 'VariableExp' */
	public Object visit(VariableExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- Compute result ---		
		Object result = null;
		if (host.getReferredVariable() != null) {
			result = env.getValue(host.getReferredVariable().getName());
			Classifier type = host.getType();
			result = wrapJavaObjectAsOclType(type,result);
		}
		return result;
	}
	/** Visit class 'IfExp' */
	public Object visit(IfExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");

		//--- Compute result ---
		Object condition = host.getCondition().accept(this, data);
		Object result = null;
		if (condition instanceof OclUndefined)
			return condition;
		if (condition instanceof OclBoolean) {
			if ( ((Boolean)((OclBoolean)condition).getImpl()).booleanValue()) {
				result = host.getThenExpression().accept(this, data);
			} else {
				result = host.getElseExpression().accept(this, data);
			}
		}
		return result;
	}
	/** Visit class 'LetExp' */
	public Object visit(LetExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment)((Map)data).get("env");
		ILog log = (ILog) ((Map) data).get("log");

		//--- Compute result ---
		Map newMap = new HashMap();
		env = env.newEnvironment();
		newMap.put("env", env);
		newMap.put("log", log);
			VariableDeclaration var = host.getVariable();
			if (var != null) {
				var.accept(this, newMap);
			}
		Object result = null;
		if (host.getIn() != null) 
			result = host.getIn().accept(this, newMap);
		return result;
	}
	/** Visit class 'OclMessageExp' */
	public Object visit(OclMessageExp host, Object data) {
		return null;
	}
	/** Visit class 'OclMessageArg' */
	public Object visit(OclMessageArg host, Object data) {
		return null;
	}
	/** Visit class 'UnspecifiedValueExp' */
	public Object visit(UnspecifiedValueExp host, Object data) {
		return null;
	}

	//
	// Types
	//
	//
	// Smallest common supertype 
	//
	/** Visit class 'OclAnyType' */
	public Object visit(OclAnyType host, Object data) {
		return "OclAny";
	}

	//
	// Data types
	//
	/** Visit class 'DataType' */
	public Object visit(DataType host, Object data) {
		return "OclAny";
	}
	
	//
	// Primitive types
	//
	/** Visit class 'PrimitiveType' */
	public Object visit(Primitive host, Object data) {
		return "OclAny";
	}
	/** Visit class 'BooleanType' */
	public Object visit(BooleanType host, Object data) {
		return "OclBoolean";
	}
	/** Visit class 'IntegerType' */
	public Object visit(IntegerType host, Object data) {
		return "OclInteger";
	}
	/** Visit class 'RealType' */
	public Object visit(RealType host, Object data) {
		return "OclReal";
	}
	/** Visit class 'StringType' */
	public Object visit(StringType host, Object data) {
		return "OclString";
	}

	//
	// Tuple type
	//
	/** Visit class 'TupleType' */
	public Object visit(TupleType host, Object data) {
		return "OclTuple";
	}
	
	//
	// Collection types
	//
	/** Visit class 'CollectionType' */
	public Object visit(CollectionType host, Object data) {
		return "OclCollection";
	}
	/** Visit class 'SequenceType' */
	public Object visit(SequenceType host, Object data) {
		return "OclSequence";
	}
	/** Visit class 'OrderedSetType' */
	public Object visit(OrderedSetType host, Object data) {
		return "OclOrderedSet";
	}
	/** Visit class 'SetType' */
	public Object visit(SetType host, Object data) {
		return "OclSet";
	}
	/** Visit class 'BagType' */
	public Object visit(BagType host, Object data) {
		return "OclBag";
	}

	//
	// Other types
	//
	/** Visit class 'OclModelElementType' */
	public Object visit(OclModelElementType host, Object data) {
		return host.getName();
	}
	/*
	/** Visit class 'OclStateType' 
	public Object visit(OclStateType host, Object data) {
		return "OclState";
	}
	*/
	/** Visit class 'OclMessageType' */
	public Object visit(OclMessageType host, Object data) {
		return "OclMessageType";
	}

	//
	// Greatest common subtype
	//
	/** Visit class 'VoidType' */
	public Object visit(VoidType host, Object data) {
		return host.toString();
	}
	
	public Object visit(Property host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionKind, java.lang.Object)
	 */
	public Object visit(CollectionKind host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionRange, java.lang.Object)
	 */
	public Object visit(CollectionRange host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionLiteralPart, java.lang.Object)
	 */
	public Object visit(CollectionLiteralPart host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionItem, java.lang.Object)
	 */
	public Object visit(CollectionItem host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration, java.lang.Object)
	 */
	public Object visit(ContextDeclaration host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.contexts.OperationContextDecl, java.lang.Object)
	 */
	public Object visit(OperationContextDecl host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.contexts.PropertyContextDecl, java.lang.Object)
	 */
	public Object visit(PropertyContextDecl host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.contexts.ClassifierContextDecl, java.lang.Object)
	 */
	public Object visit(ClassifierContextDecl host, Object data) {
		List result = new Vector();
		Iterator j = host.getConstraint().iterator();
		while (j.hasNext()) {
			Constraint con = (Constraint) j.next();
			if (con.getKind() == ConstraintKind$Class.INV) {
				//--- Compute the type ---
				OclExpression exp = con.getBodyExpression();
				if (exp != null) {
					result.add(exp.accept(this, data));
				} else {
					result.add(processor.getStdLibAdapter().Undefined());
				}
			}
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.SendAction, java.lang.Object)
	 */
	public Object visit(SendAction host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.ModelElement, java.lang.Object)
	 */
	public Object visit(ModelElement host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.EnumLiteral, java.lang.Object)
	 */
	public Object visit(EnumLiteral host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.CallAction, java.lang.Object)
	 */
	public Object visit(CallAction host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.Signal, java.lang.Object)
	 */
	public Object visit(Signal host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.Namespace, java.lang.Object)
	 */
	public Object visit(Namespace host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.Environment, java.lang.Object)
	 */
	public Object visit(Environment host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.Classifier, java.lang.Object)
	 */
	public Object visit(Classifier host, Object data) {
		return "Classifier";
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.Enumeration_, java.lang.Object)
	 */
	public Object visit(Enumeration_ host, Object data) {
		return host.getName();
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.NamedElement, java.lang.Object)
	 */
	public Object visit(NamedElement host, Object data) {
		return null;
	}
	/* (non-Javadoc)
	 * @see uk.ac.kent.cs.ocl20.semantics.model.SemanticsVisitor#visit(uk.ac.kent.cs.ocl20.semantics.model.bridge.Operation, java.lang.Object)
	 */
	public Object visit(Operation host, Object data) {
		return null;
	}
}
