/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.synthesis;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor$Class;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.DataType;
import uk.ac.kent.cs.ocl20.semantics.bridge.EnumLiteral;
import uk.ac.kent.cs.ocl20.semantics.bridge.Enumeration_;
import uk.ac.kent.cs.ocl20.semantics.bridge.OclModelElementType;
import uk.ac.kent.cs.ocl20.semantics.bridge.Operation;
import uk.ac.kent.cs.ocl20.semantics.bridge.Primitive;
import uk.ac.kent.cs.ocl20.semantics.bridge.Property;
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
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OperationCallExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.PropertyCallExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.RealLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.StringLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TupleLiteralExp;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TypeExp;
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
import uk.ac.kent.cs.ocl20.standard.lib.OclBag;
import uk.ac.kent.cs.ocl20.standard.lib.OclBoolean;
import uk.ac.kent.cs.ocl20.standard.lib.OclCollection;
import uk.ac.kent.cs.ocl20.standard.lib.OclInteger;
import uk.ac.kent.cs.ocl20.standard.lib.OclOrderedSet;
import uk.ac.kent.cs.ocl20.standard.lib.OclReal;
import uk.ac.kent.cs.ocl20.standard.lib.OclSequence;
import uk.ac.kent.cs.ocl20.standard.lib.OclSet;
import uk.ac.kent.cs.ocl20.standard.lib.OclString;
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
import uk.ac.kent.cs.kmf.util.ILog;

public class OclCodeGeneratorVisitorImpl
	extends SemanticsVisitor$Class
	implements SemanticsVisitor 
{
	public OclCodeGeneratorVisitorImpl(Object output, OclProcessor proc) {
		this.output = output;
		this.processor = proc;
	}

	/** Adapters for the model implementation and standard library */	
	protected OclProcessor processor = null;

	/** Tab */
	protected static String tab = "\t";

	/** Output */
	protected Object output;
	/** Returns the output */
	public Object getOutput() {
		return output;
	}
	/** Sets the output */
	public void setOutput(Object output) {
		this.output = output;
	}

	/** Print a string */
	public void println(String indent, String text) {
		if (output instanceof PrintStream)
			 ((PrintStream) output).println(indent + text);
		else if (output instanceof PrintWriter)
			 ((PrintWriter) output).println(indent + text);
		else if (output instanceof ILog)
			 ((ILog) output).reportMessage(indent + text);
		else if (output instanceof StringBuffer)
			 ((StringBuffer)output).append(indent+text+"\n");
	}

	/** Temporary variables */
	protected static long tempVarCounter = 1;
	public static String newTempVar() {
		return "t" + tempVarCounter++;
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
	 	
 	/** Get the name for stdlibAdapter.XXX() */
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
		getJavaTypeMap.put("IOclBoolean", Boolean.class);
		getJavaTypeMap.put("IOclInteger", Integer.class);
		getJavaTypeMap.put("IOclReal", Double.class);
		getJavaTypeMap.put("IOclString", String.class);
		getJavaTypeMap.put("IOclBag", Collection.class);
		getJavaTypeMap.put("IOclSet", Collection.class);
		getJavaTypeMap.put("IOclOrderedSet", Collection.class);
		getJavaTypeMap.put("IOclSequence", Collection.class);

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
		if (type == null) return value.getClass();
		else return type;
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
		if (type == null) return value.getClass();
		else return type;
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
		String result = (String) nameMap.get(name);
		if (result == null) return name;
		else return result;
	}

	//
	// Contexts
	//
	/** Visit class 'VariableDeclaration' */
	public Object visit(VariableDeclaration host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Output is: type name [= exp]; --- 
		String result = host.getName();
		String text = (String) host.getType().accept(this, data) + " " + result;
		if (host.getInitExpression() != null) {
			String initStr = (String) host.getInitExpression().accept(this, data);
			text += " = " + initStr;
		}
		text += ";";
		println(indent, text);
		return result;
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
		String indent = (String) ((Map) data).get("indent");

		return this.processor.getStdLibAdapterName()+".Boolean(" + host.getBooleanSymbol() + ")";
	}
	/** Visit class 'IntegerLiteralExp' */
	public Object visit(IntegerLiteralExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return this.processor.getStdLibAdapterName()+".Integer(" + host.getIntegerSymbol() + ")";
	}
	/** Visit class 'RealLiteralExp' */
	public Object visit(RealLiteralExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return this.processor.getStdLibAdapterName()+".Real(" + host.getRealSymbol() + ")";
	}
	/** Visit class 'StringLiteralExp' */
	public Object visit(StringLiteralExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return this.processor.getStdLibAdapterName()+".String(\"" + host.getStringSymbol() + "\")";
	}
	/** Visit class 'uk.ac.kent.cs.ocl20.semantics.model.bridge.Enumeration_' */
	public Object visit(uk.ac.kent.cs.ocl20.semantics.bridge.Enumeration_ host, Object data) {
		return host.getName();
	}
	/** Visit class 'EnumLiteralExp' */
	public Object visit(EnumLiteralExp host, Object data) {
		return this.processor.getModelImplAdapter().getEnumLiteralValue(host.getType().getName(), host.getName());
	}
	/** Visit class 'CollectionLiteralExp' */
	public Object visit(CollectionLiteralExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code and compute result */
		String result = newTempVar();
		CollectionKind kind = host.getKind();
		if (kind == CollectionKind$Class.BAG) {
			println(indent, "OclBag " + result + " = OCL.Bag();");
		} else if (kind == CollectionKind$Class.COLLECTION) {
			println(indent, "OclCollection " + result + " = " + this.processor.getStdLibAdapterName() + ".Collection();");
		} else if (kind == CollectionKind$Class.ORDERED_SET) {
			println(indent, "OclOrderedSet " + result + " = " + this.processor.getStdLibAdapterName() + ".OrderedSet();");
		} else if (kind == CollectionKind$Class.SEQUENCE) {
			println(indent, "OclSequence " + result + " = " + this.processor.getStdLibAdapterName() + ".Sequence();");
		} else if (kind == CollectionKind$Class.SET) {
			println(indent, "OclSet " + result + " = " + this.processor.getStdLibAdapterName() + ".Set();");
		} else {
			println(indent, "OclCollection " + result + " = " + this.processor.getStdLibAdapterName() + ".Collection();");
		}
		Iterator i = host.getParts().iterator();
		while (i.hasNext()) {
			CollectionLiteralPart part = ((CollectionLiteralPart) i.next());
			// Item
			if (part instanceof CollectionItem) {
				if (part != null) {
					OclExpression expPart = ((CollectionItem) part).getItem();
					if (expPart != null)
						println(indent, result + " = " + result + ".including(" + (String) expPart.accept(this, data) + ");");
				}
			// Range
			} else {
				OclExpression first = ((CollectionRange) part).getFirst();
				OclExpression last = ((CollectionRange) part).getLast();
				Classifier firstType = first.getType();
				Classifier lastType = last.getType();
				if (firstType instanceof IntegerType && lastType instanceof IntegerType) {
					String firstValue = (String) first.accept(this, data);
					String lastValue = (String) last.accept(this, data);
					String firstTemp = newTempVar();
					String lastTemp = newTempVar();
					String iTemp = newTempVar();
					println(indent, "OclInteger " + firstTemp + "= " + firstValue + ";");
					println(indent, "OclInteger " + lastTemp + "= " + lastValue + ";");
					println(indent, "for(int " + iTemp + "=" + this.processor.getStdLibAdapterName() + ".impl(" + firstTemp + ").intValue(); " + iTemp + "<=" + this.processor.getStdLibAdapterName() + ".impl(" + lastTemp + ").intValue(); " + iTemp + "++) {");
					println(indent + tab, result + " = " + result + ".including(" + this.processor.getStdLibAdapterName() + ".Integer(" + iTemp + "));");
					println(indent, "}");
				}
			}
		}
		return result;
	}
	/** Visit class 'TupleLiteralExp' */
	public Object visit(TupleLiteralExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		/** Generate Java code and compute result */
		String result = newTempVar();
		println(indent, "OclTuple " + result + " = " + this.processor.getStdLibAdapterName() + ".Tuple(new HashMap());");
		Iterator i = host.getTuplePart().iterator();
		while (i.hasNext()) {
			VariableDeclaration var = (VariableDeclaration) i.next();
			if (var != null) {
				String varName = var.getName();
				String valueStr = "null";
				OclExpression value = var.getInitExpression();
				if (value != null)
					valueStr = (String) value.accept(this, data);
				println(indent, result + ".setProperty("+ this.processor.getStdLibAdapterName() + ".String(\"" + varName + "\"), " + valueStr + ");");
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
		String indent = (String) ((Map) data).get("indent");

		/** Generate code for result */
		Classifier sourceType = host.getSource().getType();
		String result = newTempVar();
		String operName = getFunctionName(host.getReferredOperation());

		//--- Generate the correct code for logical expressions ---
	    if (sourceType instanceof BooleanType && 
	       (operName.equals("and") || operName.equals("or") ||	operName.equals("xor") || operName.equals("implies"))) {
	       	//--- Increase indent in data
	       	((Map)data).put("indent", indent+tab); 
			//--- Generate code for left operand
			String temp1 = newTempVar();
			println(indent, "OclBoolean "+temp1+";");
			println(indent, "try {");
			String source = (String) host.getSource().accept(this, data);
			println(indent+tab, temp1+" = "+source+";");
			println(indent, "} catch (Exception e) {");
			println(indent+tab, temp1+" = " + this.processor.getStdLibAdapterName() + ".Undefined();");
			println(indent, "}");
			//--- Generate code for left operand
			String temp2 = newTempVar();
			println(indent, "OclBoolean "+temp2+";");
			println(indent, "try {");
			//--- Compute arguments ---
			Iterator i = host.getArguments().iterator();
			String argStr = new String();
			while (i.hasNext()) {
				OclExpression arg = (OclExpression) i.next();
				if (arg != null)
					argStr += arg.accept(this, data);
				else
					argStr += "null";
				if (i.hasNext())
					argStr += ", ";
			}
			println(indent+tab, temp2+" = "+argStr+";");
			println(indent, "} catch (Exception e) {");
			println(indent+tab, temp2+" = " + this.processor.getStdLibAdapterName() + ".Undefined();");
			println(indent, "}");
			//--- Generate code to compute the result
			println(indent, "// Call operation '"+operName+"'");
			println(indent, "OclBoolean " + result + " = " + temp1 + "." + operName + "("+temp2+");");
			return result;
		}
		
		//--- Generate code for all the others
		//--- Compute source ---
		String source = (String) host.getSource().accept(this, data);
		//--- Compute arguments ---
		Iterator i = host.getArguments().iterator();
		String argStr = new String();
		while (i.hasNext()) {
			OclExpression arg = (OclExpression) i.next();
			if (arg != null)
				argStr += arg.accept(this, data);
			else
				argStr += "null";
			if (i.hasNext())
				argStr += ", ";
		}
		String type = (String) host.getType().accept(this, data);
		//--- Invoke the right operations = and <> for Model Elements and Enumerations 
		println(indent, "// Call operation '"+operName+"'");
		if (operName.equals("equalTo")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".OclModelElement_equalTo("+source+", "+argStr+")"+");");
			else if (sourceType instanceof Enumeration_) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".EnumLiteral_equalTo("+source+", "+argStr+")"+");");
			else
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean(" + source + ".equals(" + argStr + "));");
		} else if (operName.equals("notEqualTo")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean(!"+this.processor.getModelImplAdapterName()+".OclModelElement_equalTo("+source+", "+argStr+")"+");");
			else if (sourceType instanceof Enumeration_) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean(!"+this.processor.getModelImplAdapterName()+".EnumLiteral_equalTo("+source+", "+argStr+")"+");");
			else
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean(!" + source + ".equals(" + argStr + "));");
		} else if (operName.equals("oclIsNew")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".OclModelElement_oclIsNew("+source+")"+");");
			else if (sourceType instanceof Enumeration_) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".EnumLiteral_oclIsNew("+source+")"+");");
			else
				println(indent, type + " " + result + " = " + source + ".oclIsNew();");
		} else if (operName.equals("oclIsUndefined")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".OclModelElement_oclIsUndefined("+source+")"+");");
			else if (sourceType instanceof Enumeration_) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".EnumLiteral_oclIsUndefined("+source+")"+");");
			else
				println(indent, type + " " + result + " = " + source + ".oclIsUndefined();");
		} else if (operName.equals("oclAsType")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, "Object " + result + " = " + this.processor.getModelImplAdapterName()+".OclModelElement_oclAsType("+source+", "+argStr+")"+";");
			else if (sourceType instanceof Enumeration_) 
				println(indent, "Object " + result + " = " + this.processor.getModelImplAdapterName()+".EnumLiteral_oclAsType("+source+", "+argStr+")"+";");
			else
				println(indent, "Object " + result + " = " + source + ".oclAsType(" + argStr + ");");
		} else if (operName.equals("oclIsTypeOf")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".OclModelElement_oclIsTypeOf("+source+", "+argStr+")"+");");
			else if (sourceType instanceof Enumeration_) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".EnumLiteral_oclIsTypeOf("+source+", "+argStr+")"+");");
			else
				println(indent, type + " " + result + " = " + source + ".oclIsTypeOf(" + argStr + ");");
		} else if (operName.equals("oclIsKindOf")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".OclModelElement_oclIsKindOf("+source+", "+argStr+")"+");");
			else if (sourceType instanceof Enumeration_) 
				println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName()+".Boolean("+this.processor.getModelImplAdapterName()+".EnumLiteral_oclIsKindOf("+source+", "+argStr+")"+");");
			else
				println(indent, type + " " + result + " = " + source + ".oclIsKindOf(" + argStr + ");");
		} else if (operName.equals("allInstances")) {
			if (sourceType instanceof OclModelElementType) 
				println(indent, "OclSet " + result + " = " + this.processor.getStdLibAdapterName()+".Set("+this.processor.getModelImplAdapterName()+".OclModelElement_allInstances("+source+")"+");");
			else if (sourceType instanceof Enumeration_) 
				println(indent, "OclSet " + result + " = " + this.processor.getStdLibAdapterName()+".Set("+this.processor.getModelImplAdapterName()+".EnumLiteral_allInstances("+source+")"+");");
			else
				println(indent, "OclSet " + result + " = " + source + ".allInstances();");
		//--- Generate code for the other cases ---
		} else {
			// Add required conversions to OCL types
			// Operations from model do not return OCL types; most of Stdlib operation do
			if (needsOclWrapping(sourceType, operName)) {
				Classifier semanticType = host.getType();
				String nameFromAdapter = getAdapterMethodName(semanticType);
				if (nameFromAdapter != null)
					println(indent, type + " " + result + " = " + this.processor.getStdLibAdapterName() + "." + nameFromAdapter + "(" + source + "." + operName + "(" + argStr + "));");
				else
					println(indent, type + " " + result + " = (" + type + ")" + source + "." + operName + "(" + argStr + ");");
			} else {
				println(indent, type + " " + result + " = (" + type + ")" + source + "." + operName + "(" + argStr + ");");
			}
		}
		return result;
	}
	/** Visit class 'PropertyCallExp' */
	public Object visit(PropertyCallExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Compute result and generate code ---
		String result = newTempVar();
		String propertyType = (String) host.getReferredProperty().getType().accept(this, data);
		String propertyName = host.getReferredProperty().getName();
		// Enumeration
		Property prop = host.getReferredProperty();
		if (prop instanceof EnumLiteral) {
			propertyType = (String) host.getReferredProperty().getType().accept(this, data);
			println(indent, "// Call property '"+propertyName+"'");
			String propertyValue = this.processor.getModelImplAdapter().getEnumLiteralValue(propertyType, prop.getName());
			println(indent, propertyType + " " + result + " = "+propertyValue+";");
		// Usual property
		} else { 
			String source = (String) host.getSource().accept(this, data);
			Classifier sourceType = host.getSource().getType();
			String operName = this.processor.getModelImplAdapter().getGetterName(propertyName);
			// Add required conversions to OCL types
			// Operations from model do not return OCL types; most of Stdlib operation do
			println(indent, "// Call property '"+propertyName+"'");
			if (needsOclWrapping(sourceType, operName)) {
				Classifier semanticType = host.getType();
				String nameFromAdapter = getAdapterMethodName(semanticType);
				if (nameFromAdapter != null)
					println(indent, propertyType + " " + result + " = " + this.processor.getStdLibAdapterName() + "." + nameFromAdapter + "(" + source + "." + this.processor.getModelImplAdapter().getGetterName(propertyName) + "());");
				else
					println(indent, propertyType + " " + result + " = (" + propertyType + ")" + source + "." + this.processor.getModelImplAdapter().getGetterName(propertyName) + "();");
			} else {
				println(indent, propertyType + " " + result + " = " + source + "." + operName + "();");
			}
		}
		return result;
	}

	//
	// Loop expressions
	//
	/** Compute attributes for an iterator */
	protected Map computeIteratorAttributes(OclExpression source, VariableDeclaration var, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		/** Compute name, type, and init value */
		String name = null;
		String type = null;
		String value = null;
		if (var != null) {
			name = var.getName();
			Classifier type1 = var.getType();
			Classifier elementType = ((CollectionType) source.getType()).getElementType(); 
			type = (String) elementType.accept(this, data);
			if (var.getInitExpression() != null)
				value = (String) var.getInitExpression().accept(this, data);
		} else {
			name = newTempVar();
			type = (String) ((CollectionType) source.getType()).getElementType().accept(this, data);
		}
		
		//--- Return result ---
		Map result = new HashMap();
		result.put("name", name);
		result.put("type", type);
		result.put("value", value);
		return result;
	}
	/** Generate code for 'exists' */
	protected String exists(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'exists' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		String resultName = newTempVar();
		println(indent, "//--- Init flag ---");
		println(indent, "OclBoolean" + " " + resultName + " = " + this.processor.getStdLibAdapterName() + ".Boolean(false)" + ";");
		//--- Generate loop for iterate ---
		String tempName = newTempVar();
		println(indent, "//--- For each element from collection ---");
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, resultName + " = " + bodyValue + ";");
		println(indent + tab, "//--- Break the loop ---");
		println(indent + tab, "if(" + this.processor.getStdLibAdapterName() + ".impl(" + resultName + ").booleanValue()) break;");
		println(indent, "}");
		return resultName;
	}
	/** Generate code for 'forAll' */
	protected String forAll(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'forAll' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		println(indent, "//--- Init flag ---");
		String resultName = newTempVar();
		println(indent, "OclBoolean" + " " + resultName + " = " + this.processor.getStdLibAdapterName() + ".Boolean(true)" + ";");
		//--- Generate loop for iterate ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, resultName + " = " + bodyValue + ";");
		println(indent + tab, "//--- Break the loop ---");
		println(indent + tab, "if(!" + this.processor.getStdLibAdapterName() + ".impl(" + resultName + ").booleanValue()) break;");
		println(indent, "}");
		return resultName;
	}
	/** Generate code for 'isUnique' */
	protected String isUnique(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'isUnique' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		println(indent, "//--- Init flag ---");
		String resultName = newTempVar();
		println(indent, "OclBoolean" + " " + resultName + " = " + this.processor.getStdLibAdapterName() + ".Boolean(true)" + ";");
		//--- Generate code for acumulator ---
		String setType = "OclSet";
		String setName = newTempVar();
		String setValue = "" + this.processor.getStdLibAdapterName() + ".Set()";
		//--- Generate loop for iterate ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, "//--- Break the loop ---");
		println(indent + tab, "if("+this.processor.getStdLibAdapterName()+".impl(" + setName + ".includes(" + bodyValue + ")).booleanValue()) {");
		println(indent + tab + tab, resultName+" = " + resultName + ".not()");
		println(indent + tab + tab, "break;");
		println(indent + tab, "}");
		println(indent, "}");
		return resultName;
	}
	/** Generate code for 'any' */
	protected String any(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'any' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		println(indent, "//--- Init result ---");
		String resultName = newTempVar();
		String resultType = (String)host.getType().accept(this, data);
		println(indent, resultType + " " + resultName + " = null;");
		//--- Generate code for acumulator ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, "//--- Break the loop ---");
		println(indent + tab, "if(" + this.processor.getStdLibAdapterName() + ".impl(" + bodyValue + ").booleanValue())" + " {");
		println(indent + tab + tab, resultName + " = " + it1Name + ";");
		println(indent + tab + tab, "break;");
		println(indent + tab, "}");
		println(indent, "}");
		return resultName;
	}
	/** Generate code for 'one' */
	protected String one(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		println(indent, "// 'one' iterator");
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		println(indent, "//--- Init result ---");
		String resultName = newTempVar();
		String resultValue = "" + this.processor.getStdLibAdapterName() + ".Boolean(false);";
		println(indent, "OclBoolean" + " " + resultName + " = " + this.processor.getStdLibAdapterName() + ".Boolean(false)" + ";");
		//--- Generate code for acumulator ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		String counterName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "int " + counterName + " = 0;");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, "//--- Compute result and break the loop ---");
		println(indent + tab, "if(" + this.processor.getStdLibAdapterName() + ".impl(" + bodyValue + ").booleanValue())" + " {");
		println(indent + tab + tab, counterName + "++;");
		println(indent + tab + tab, "if (" + counterName + " == 1) {");
		println(indent + tab + tab + tab, resultName += "" + this.processor.getStdLibAdapterName() + ".Boolean(true);");
		println(indent + tab + tab, "}");
		println(indent + tab + tab, "if (" + counterName + " > 1) {");
		println(indent + tab + tab + tab, resultName += "" + this.processor.getStdLibAdapterName() + ".Boolean(false);");
		println(indent + tab + tab + tab, "break;");
		println(indent + tab + tab, "}");
		println(indent + tab, "}");
		println(indent, "}");
		return resultName;
	}
	/** Generate code for 'collect' */
	protected String collect(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		println(indent, "// 'collect' iterator");
		return collectNested(host, var1, var2, body, data);
	}
	/** Generate the code that initialises an empty collection */
	protected Map computeEmptyCollectionAttributes(Classifier sourceType) {
		//--- Compute name, type, and value ---
		String resultType = null;
		String resultName = null;
		String resultValue = null;
		if (sourceType instanceof BagType) {
			resultType = "OclBag";
			resultName = newTempVar();
			resultValue = this.processor.getStdLibAdapterName() + ".Bag();";
		} else if (sourceType instanceof SetType) {
			resultType = "OclSet";
			resultName = newTempVar();
			resultValue = this.processor.getStdLibAdapterName() + ".Set();";
		} else if (sourceType instanceof OrderedSetType) {
			resultType = "OclOrderedSet";
			resultName = newTempVar();
			resultValue = this.processor.getStdLibAdapterName() + ".OrderedSet();";
		} else if (sourceType instanceof SequenceType) {
			resultType = "OclSequence";
			resultName = newTempVar();
			resultValue = this.processor.getStdLibAdapterName() + ".Sequence();";
		}
		//--- Compute result ---
		Map result = new HashMap();
		result.put("name", resultName);
		result.put("type", resultType);
		result.put("value", resultValue);
		return result;
	}
	/** Generate code for 'select' */
	protected String select(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'select' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		println(indent, "//--- Init result ---");
		Classifier hostType = host.getType();
		Map resultMap = this.computeEmptyCollectionAttributes(hostType);
		String resultType = (String)resultMap.get("type");
		String resultName = (String)resultMap.get("name");
		String resultValue = (String)resultMap.get("value");
		println(indent, resultType + " " + resultName + " = " + resultValue + ";");
		//--- Generate loop for iterate ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, "//--- Compute result and break the loop ---");
		println(indent + tab, "if(" + this.processor.getStdLibAdapterName() + ".impl(" + bodyValue + ").booleanValue())" + " {");
		if (hostType instanceof OrderedSetType) {
			println(indent + tab + tab, "if (!" + this.processor.getStdLibAdapterName() + ".impl(" + resultName + ".includes(" + it1Name + ")).booleanValue()) {");
			println(indent + tab + tab + tab, resultName + " = " + resultName + ".including(" + it1Name + ");");
			println(indent + tab + tab, "}");
		} else {
			println(indent + tab + tab, resultName + " = " + resultName + ".including(" + it1Name + ");");
		}
		println(indent + tab, "}");
		println(indent, "}");
		return resultName;
	}
	/** Generate code for 'reject' */
	protected String reject(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'reject' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		println(indent, "//--- Init result ---");
		Classifier hostType = host.getType();
		Map resultMap = this.computeEmptyCollectionAttributes(hostType);
		String resultType = (String)resultMap.get("type");
		String resultName = (String)resultMap.get("name");
		String resultValue = (String)resultMap.get("value");
		println(indent, resultType + " " + resultName + " = " + resultValue + ";");
		//--- Generate loop for iterate ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, "//--- Compute result and break the loop ---");
		println(indent + tab, "if(!" + this.processor.getStdLibAdapterName() + ".impl(" + bodyValue + ").booleanValue())" + " {");
		if (hostType instanceof OrderedSetType) {
			println(indent + tab + tab, "if (!" + this.processor.getStdLibAdapterName() + ".impl(" + resultName + ".includes(" + it1Name + ")).booleanValue()) {");
			println(indent + tab + tab + tab, resultName + " = " + resultName + ".including(" + it1Name + ");");
			println(indent + tab + tab, "}");
		} else {
			println(indent + tab + tab, resultName + " = " + resultName + ".including(" + it1Name + ");");
		}
		println(indent + tab, "}");
		println(indent, "}");
		return resultName;
	}
	/** Generate code for 'collectedNested' */
	protected String collectNested(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'collectedNested' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code for acumulator ---
		println(indent, "//--- Init result ---");
		Classifier hostType = host.getType();
		Map resultMap = this.computeEmptyCollectionAttributes(hostType);
		String resultType = (String)resultMap.get("type");
		String resultName = (String)resultMap.get("name");
		String resultValue = (String)resultMap.get("value");
		println(indent, resultType + " " + resultName + " = " + resultValue + ";");
		//--- Generate loop for iterate ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, "//--- Add body to result ---");
		if (hostType instanceof OrderedSetType) {
			println(indent + tab, "if (!" + this.processor.getStdLibAdapterName() + ".impl(" + resultName + ".includes(" + bodyValue + ")).booleanValue()) {");
			println(indent + tab + tab, resultName + " = " + resultName + ".including(" + bodyValue + ");");
			println(indent + tab, "}");
		} else {
			println(indent + tab, resultName + " = " + resultName + ".including(" + bodyValue + ");");
		}
		println(indent, "}");
		println(indent, "//--- Flatten the collection ---");
		println(indent, resultName + " = " + resultName + ".flatten();");
		return resultName;
	}
	/** Generate code for 'sortedBy' */
	protected String sortedBy(IteratorExp host, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Check if the collection can be sorted ---
		Classifier bodyType = body.getType();
		if (!(bodyType instanceof RealType )) return null;

		//--- Generate code for source ---
		println(indent, "// 'sortedBy' iterator");
		OclExpression source = host.getSource();
		String sourceStr = (String) source.accept(this, data);
		Classifier sourceType = source.getType();
		//--- Generate code for iterators ---
		//--- First iterator ---
		Map it1 = computeIteratorAttributes(source, var1, data);
		String it1Name = (String)it1.get("name");
		String it1Type = (String)it1.get("type");
		String it1Value = (String)it1.get("value");
		println(indent, it1Type + " " + it1Name + " = " + it1Value + ";");
		//--- Second optional iterator ---
		String it2Name = null;
		String it2Type = null;
		String it2Value = null;
		if (var2 != null) {
			Map it2 = computeIteratorAttributes(source, var1, data);
			it2Name = (String)it1.get("name");
			it2Type = (String)it1.get("type");
			it2Value = (String)it1.get("value");
			println(indent, it2Type + " " + it2Name + " = " + it2Value + ";");
		}
		//--- Generate code to sort ---
		println(indent, "//--- Compute 2 temporary list which contain the initial list and the list of body values ---");
		String itemListName = newTempVar();
		String bodyListName = newTempVar();
		println(indent, "List "+bodyListName+" = new Vector();");
		println(indent, "List "+itemListName+" = new Vector();");
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, it1Name + " = (" + it1Type + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, "//--- Add elements to item and body list ---");
		println(indent + tab, itemListName+".add("+it1Name+");");
		println(indent + tab, bodyListName+".add("+bodyValue+");");
		println(indent, "}");
		//--- Sort item list ---
		println(indent, "//--- Sort item list according to body list values ---");
		String ordered = newTempVar();
		println(indent, "boolean "+ordered+" = true;");
		println(indent, "do {");
		println(indent + tab, ordered + " = true;");
		String k = newTempVar();
		println(indent + tab, "for(int "+k+"=0; "+k+"<"+itemListName+".size()-1; "+k+"++) {");
		String k1 = newTempVar();
		String k2 = newTempVar();
		println(indent + tab + tab, "OclReal "+k1+" = (OclReal)"+bodyListName+".get("+k+");");
		println(indent + tab + tab, "OclReal "+k2+" = (OclReal)"+bodyListName+".get("+k+"1);");
		println(indent + tab + tab, "if ("+this.processor.getStdLibAdapterName()+".impl("+k1+".greaterThan(+"+k2+")).booleanValue()) {");
		String temp = newTempVar();
		println(indent + tab + tab + tab, "Object "+temp+" = "+k1+";");
		println(indent + tab + tab + tab, bodyListName+".set("+k+", "+k2+");");					
		println(indent + tab + tab + tab, bodyListName+".set("+k+"+1, "+temp+");");					
		println(indent + tab + tab + tab, temp+" = "+itemListName+".get("+k+");");
		println(indent + tab + tab + tab, itemListName+".set("+k+", "+itemListName+".get("+k+"+1));");					
		println(indent + tab + tab + tab, itemListName+".set("+k+"+1, "+temp+");");
		println(indent + tab + tab + tab, ordered +"= false;");					
		println(indent + tab + tab, "}");
		println(indent + tab, "}");
		println(indent, "} while (!"+ordered+");");
		//--- Compute result ---
		println(indent, "//--- Compute result ---");
		String result = newTempVar();
		if (sourceType instanceof BagType) {
			println(indent, "OclCollection "+ result + " = " + this.processor.getStdLibAdapterName()+".Sequence("+itemListName+"));");
		} else if (sourceType instanceof OrderedSetType) {
			println(indent, "OclCollection "+ result + " = " + this.processor.getStdLibAdapterName()+".OrderedSet("+itemListName+"));");
		} else if (sourceType instanceof SetType) {
			println(indent, "OclCollection "+ result + " = " + this.processor.getStdLibAdapterName()+".OrderedSet("+itemListName+"));");
		} else if (sourceType instanceof SequenceType) {
			println(indent, "OclCollection "+ result + " = " + this.processor.getStdLibAdapterName()+".Sequence("+itemListName+"));");
		} else {
			println(indent, "OclCollection "+ result + " = " + this.processor.getStdLibAdapterName()+".Sequence("+itemListName+"));");
		}
		return result;
	}
	/** Visit class 'IteratorExp' */
	public Object visit(IteratorExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate Java code and compute result ---
		String name = host.getName();
		VariableDeclaration var1 = null;
		if (host.getIterators().size() >= 1)
			var1 = (VariableDeclaration) host.getIterators().toArray()[0];
		VariableDeclaration var2 = null;
		if (host.getIterators().size() >= 2)
			var2 = (VariableDeclaration) host.getIterators().toArray()[1];
		OclExpression body = host.getBody();
		String result = "null";
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
	protected String iterate(OclExpression source, VariableDeclaration var1, VariableDeclaration var2, OclExpression body, Map data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for source ---
		println(indent, "// 'iterate '");
		String sourceStr = (String) source.accept(this, data);
		//--- Generate code for iterator ---
		Map it = computeIteratorAttributes(source, var1, data);
		String itName = (String)it.get("name");
		String itType = (String)it.get("type");
		String itValue = (String)it.get("value");
		println(indent, itType + " " + itName + " = " + itValue + ";");
		//--- Generate code for acumulator ---
		String resultType = null;
		String resultName = var2.getName();
		String resultValue = null;
		resultType = (String) ((CollectionType) source.getType()).getElementType().accept(this, data);
		if (var2.getInitExpression() != null)
			resultValue = (String) var2.getInitExpression().accept(this, data);
		println(indent, resultType + " " + resultName + " = " + resultValue);
		//--- Generate loop for iterate ---
		println(indent, "//--- For each element from collection ---");
		String tempName = newTempVar();
		println(indent, "java.util.Iterator " + tempName + " = " + this.processor.getStdLibAdapterName() + ".impl(" + sourceStr + ").iterator();");
		println(indent, "while (" + tempName + ".hasNext()) {");
		println(indent + tab, itName + " = (" + itType + ")" + tempName + ".next();");
		//--- Generate body ---
		println(indent + tab, "//--- Compute body ---");
		Map newData = new HashMap();
		newData.putAll((Map) data);
		newData.put("indent", ((Map) data).get("indent") + tab);
		String bodyValue = (String) body.accept(this, newData);
		println(indent + tab, resultName + " = " + bodyValue + ";");
		println(indent, "}");
		return resultName;
	}
	/** Visit class 'IterateExp' */
	public Object visit(IterateExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate Java code and compute result ---
		OclExpression source = host.getSource();
		VariableDeclaration var1 = null;
		if (host.getIterators() != null && host.getIterators().size() > 0) {
			var1 = (VariableDeclaration) host.getIterators().toArray()[0];
		}
		VariableDeclaration var2 = (VariableDeclaration) host.getResult();
		OclExpression body = host.getBody();
		return iterate(source, var1, var2, body, (Map) data);
	}
	//
	// Others
	//
	/** Visit class 'VariableExp' */
	public Object visit(VariableExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Generate code for variable ---		
		String result = "null";
		if (host.getReferredVariable() != null) {
			result = host.getReferredVariable().getName();
		}
		return result;
	}
	/** Visit class 'IfExp' */
	public Object visit(IfExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Compute result and generate Java code ---/		
		println(indent, "// 'if'");
		String condition = (String) host.getCondition().accept(this, data);
		String thenValue = (String) host.getThenExpression().accept(this, data);
		String elseValue = (String) host.getElseExpression().accept(this, data);
		String result = newTempVar();
		String resultType = (String) host.getType().accept(this, data);
		println(indent, resultType + " " + result + " = " + this.processor.getStdLibAdapterName() + ".impl(" + condition + ").booleanValue()?" + thenValue + ":" + elseValue + ";");
		return result;
	}
	/** Visit class 'LetExp' */
	public Object visit(LetExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		//--- Compute result and generate Java code ---/
		//--- Generate code vor the variable ---
		VariableDeclaration var = host.getVariable();
		if (var != null) {
			var.accept(this, data);
		}
		//--- Generate code for in expression ---
		String in = "null";
		if (host.getIn() != null)
			in = (String) host.getIn().accept(this, data);
		return in;
	}

	//
	// Types
	//
	//
	// Smallest common supertype 
	//
	/** Visit class 'OclAnyType' */
	public Object visit(OclAnyType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclAny";
	}

	//
	// Data types
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.Bridge.DataType' */
	public Object visit(DataType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclAny";
	}

	//
	// Primitive types
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.Bridge.PrimitiveType' */
	public Object visit(Primitive host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		String indent = (String) ((Map) data).get("indent");

		return "OclAny";
	}
	/** Visit class 'BooleanType' */
	public Object visit(BooleanType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclBoolean";
	}
	/** Visit class 'IntegerType' */
	public Object visit(IntegerType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclInteger";
	}
	/** Visit class 'RealType' */
	public Object visit(RealType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclReal";
	}
	/** Visit class 'StringType' */
	public Object visit(StringType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclString";
	}

	//
	// Tuple type
	//
	/** Visit class 'TupleType' */
	public Object visit(TupleType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclTuple";
	}

	//
	// Collection types
	//
	/** Visit class 'CollectionType' */
	public Object visit(CollectionType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclCollection";
	}
	/** Visit class 'SequenceType' */
	public Object visit(SequenceType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclSequence";
	}
	/** Visit class 'OrderedSetType' */
	public Object visit(OrderedSetType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclOrderedSet";
	}
	/** Visit class 'SetType' */
	public Object visit(SetType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclSet";
	}
	/** Visit class 'BagType' */
	public Object visit(BagType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclBag";
	}

	//
	// Other types
	//
	/** Visit class 'uk.ac.ukc.cs.kmf.ocl20.Bridge.OclModelElementType' */
	public Object visit(OclModelElementType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		String name = host.getFullName("."); 
		return name;
	}
	/** Visit class 'OclMessageType' */
	public Object visit(OclMessageType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "OclMessageType";
	}

	//
	// Greatest common subtype
	//
	/** Visit class 'VoidType' */
	public Object visit(VoidType host, Object data) {
		//--- Unpack arguments ---
		String indent = (String) ((Map) data).get("indent");

		return "VoidType";
	}
	public Object visit(TypeExp host, Object data) {
		return null;
	}
}
