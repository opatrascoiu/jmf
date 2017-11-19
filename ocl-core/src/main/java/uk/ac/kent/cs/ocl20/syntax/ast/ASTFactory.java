/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast;

import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.ocl20.syntax.ast.contexts.*;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.*;
import uk.ac.kent.cs.ocl20.syntax.ast.types.*;

public class ASTFactory {
	//
	// Build context
	//
	/** Build PackageDeclaration */
	public PackageDeclarationAS buildPackageDeclaration(List path, List contextDecls) {
		PackageDeclarationAS result = new PackageDeclarationAS$Class();
		result.setPathName(path);
		result.setContextDecls(contextDecls);
		return result;
	}
	/** Build PropertyContextDeclaration */
	public PropertyContextDeclAS buildPropertyContextDeclaration(List path, String name, TypeAS type, List constraints) {
		PropertyContextDeclAS res = new PropertyContextDeclAS$Class();
		res.setPathName(path);
		res.setName(name);
		res.setType(type);
		res.setConstraints(constraints);
		return res;
	}
	/** Build ClassifierContextDeclaration */
	public ClassifierContextDeclAS buildClassifierContextDeclaration(List path, List constraints, Object location) {
		ClassifierContextDeclAS res = new ClassifierContextDeclAS$Class();
		res.setPathName(path);
		res.setConstraints(constraints);
		res.setLocation(location);
		return res;
	}
	/** Build OperationContextDeclaration */
	public OperationContextDeclAS buildOperationContextDeclaration(OperationAS oper, List constraints) {
		OperationContextDeclAS result = new OperationContextDeclAS$Class();
		result.setOperation(oper);
		result.setConstraints(constraints);
		return result;
	}
	/** Build Operation */
	public OperationAS buildOperation(List path, String name, List params, TypeAS type) {
		OperationAS oper = new OperationAS$Class();
		oper.setPathName(path);
		oper.setName(name);
		oper.setParameters(params);
		oper.setType(type);
		return oper;
	}	
	/** Build Constraint */
	public ConstraintAS buildConstraint(ConstraintKindAS kind, String name, OclExpressionAS body, Object varOrOper) {
		ConstraintAS cons = new ConstraintAS$Class();
		cons.setKind(kind);
		cons.setName(name);
		cons.setBodyExpression(body);
		if (varOrOper instanceof VariableDeclarationAS) {
			cons.setDefVariable((VariableDeclarationAS)varOrOper);
		} else if (varOrOper instanceof OperationAS) {
			cons.setDefOperation((OperationAS)varOrOper);
		}
		return cons;
	}

	//
	// Build variables and types
	//
	/** Build VariableDeclaration */
	public VariableDeclarationAS buildVariableDeclaration(String name, TypeAS type, OclExpressionAS init) {
		VariableDeclarationAS var = new VariableDeclarationAS$Class();
		var.setName(name);
		var.setType(type);
		var.setInitExp(init);
		return var;
	}
	/** Build PathNameType */
	public ClassifierAS buildPathNameType(List path) {
		ClassifierAS type = new ClassifierAS$Class();
		type.setPathName(path);
		return type;
	}
	/** Build CollectionType */
	public CollectionTypeAS buildCollectionType(CollectionKindAS kind, TypeAS elementType) {
		CollectionTypeAS type = null;
		if (kind == CollectionKindAS$Class.BAG) {
			type = new BagTypeAS$Class();
			type.setElementType(elementType);
		} else if (kind == CollectionKindAS$Class.COLLECTION) {
			type = new CollectionTypeAS$Class();
			type.setElementType(elementType);
		} else if (kind == CollectionKindAS$Class.SEQUENCE) {
			type = new SequenceTypeAS$Class();
			type.setElementType(elementType);
		} else if (kind == CollectionKindAS$Class.SET) {
			type = new SetTypeAS$Class();
			type.setElementType(elementType);
		} else if (kind == CollectionKindAS$Class.ORDERED_SET) {
			type = new OrderedSetTypeAS$Class();
			type.setElementType(elementType);
		} else {
			type = null;
		}
		return type;
	}
	/** Build TypeType */
	public TupleTypeAS buildTupleType(List varList) {
		TupleTypeAS type = new TupleTypeAS$Class();
		type.setVariableDeclarationList(varList);
		return type;
	}
	
	//
	// Build expressions
	//
	/** Build PathNameExp */
	public PathNameExpAS buildPathNameExp(List path, Boolean isMarkedPre) {
		PathNameExpAS res = new PathNameExpAS$Class();
		res.setPathName(path);
		res.setIsMarkedPre(isMarkedPre);
		return res;
	}
	/** Build DotSelectionExp */
	public DotSelectionExpAS buildDotSelectionExp(OclExpressionAS exp, String name, Boolean isMarkedPre) {
		DotSelectionExpAS res = new DotSelectionExpAS$Class();
		res.setSource(exp);
		res.setName(name);
		res.setIsMarkedPre(isMarkedPre);
		exp.setParent(res);
		return res;
	}
	/** Build ArrowSelectionExp */
	public ArrowSelectionExpAS buildArrowSelectionExp(OclExpressionAS exp, String name) {
		ArrowSelectionExpAS res = new ArrowSelectionExpAS$Class();
		res.setSource(exp);
		res.setName(name);
		exp.setParent(res);
		return res;
	}
	/** Build OperationCallExp for +, -, *, / aso */
	public OclExpressionAS buildOperationCallExp(String name, OclExpressionAS left, OclExpressionAS right) {
		// Create source
		DotSelectionExpAS source = new DotSelectionExpAS$Class();
		source.setSource(left);
		source.setName(name);
		// Create acll
		OperationCallExpAS res = new OperationCallExpAS$Class();
		res.setSource(source);
		res.setArguments(new Vector());
		if (right != null)
			res.getArguments().add(right);
		left.setParent(res);
		return res;
	}
	/** Build OperationCallExp */
	public CallExpAS buildOperationCallExp(OclExpressionAS exp, List arguments) {
		CallExpAS res = new OperationCallExpAS$Class();
		res.setSource(exp);
		res.setArguments(arguments);
		exp.setParent(res);
		return res;
	}
	/** Build IteratorCallExp */
	public LoopExpAS buildIteratorCallExp(OclExpressionAS exp1, VariableDeclarationAS var1, VariableDeclarationAS var2, OclExpressionAS exp2) {
		LoopExpAS res = new IteratorExpAS$Class();
		res.setSource(exp1);
		res.setIterator(var1);
		res.setResult(var2);
		res.setBody(exp2);
		exp1.setParent(res);
		return res;
	}
	/** Build AssociationCallExp */
	public CallExpAS buildAssociationCallExp(OclExpressionAS exp, List arguments, Boolean isMarkedPre) {
		CallExpAS res = new AssociationCallExpAS$Class();
		res.setSource(exp);
		res.setArguments(arguments);
		res.setIsMarkedPre(isMarkedPre);
		exp.setParent(res);
		return res;
	}
	/** Create an IterateExp */
	public OclExpressionAS buildIterateExp(OclExpressionAS source, VariableDeclarationAS iterator, VariableDeclarationAS result, OclExpressionAS body) {
		IterateExpAS res = new IterateExpAS$Class();
		res.setSource(source);
		res.setName("iterate");
		res.setIterator(iterator);
		res.setResult(result);
		res.setBody(body);
		source.setParent(res);
		return res;
	}
	/** Build LogicalExp */
	public OclExpressionAS buildLogicalExp(int kind, OclExpressionAS left, OclExpressionAS right) {
		switch (kind) {
			case 0 : {
				NotExpAS res = new NotExpAS$Class();
				res.setLeftOperand(left);
				left.setParent(res);
				return res;
			} 
			case 1 : {
				AndExpAS res = new AndExpAS$Class();
				res.setLeftOperand(left);
				res.setRightOperand(right);
				left.setParent(res);
				return res;
			} 
			case 2 : {
				OrExpAS res = new OrExpAS$Class();
				res.setLeftOperand(left);
				res.setRightOperand(right);
				left.setParent(res);
				return res;
			} 
			case 3 : {
				XorExpAS res = new XorExpAS$Class();
				res.setLeftOperand(left);
				res.setRightOperand(right);
				left.setParent(res);
				return res;
			} 
			case 4 : {
				ImpliesExpAS res = new ImpliesExpAS$Class();
				res.setLeftOperand(left);
				res.setRightOperand(right);
				left.setParent(res);
				return res;
			}
			default : return null;
		}
	}
	/** Create an OclMessageExp */
	public OclExpressionAS buildOclMessageExp(OclMessageKindAS kind, OclExpressionAS target, String name, List arguments) {
		OclMessageExpAS res = new OclMessageExpAS$Class();
		res.setKind(kind);
		res.setName(name);
		res.setTarget(target);
		res.setArguments(arguments);
		target.setParent(res);
		return res;
	}
	/** Build OclMessageArg */
	public OclMessageArgAS buildOclMessageArg(OclExpressionAS exp) {
		OclMessageArgAS arg = new OclMessageArgAS$Class();
		arg.setExpression(exp);
		return arg;
	}
	/** Build IfExp */
	public IfExpAS buildIfExp(OclExpressionAS condition, OclExpressionAS thenExp, OclExpressionAS elseExp) {
		IfExpAS res = new IfExpAS$Class();
		res.setCondition(condition);
		res.setThenExpression(thenExp);
		res.setElseExpression(elseExp);
		return res;
	}
	/** Build LetExp */
	public LetExpAS buildLetExp(List variables, OclExpressionAS exp) {
		LetExpAS res = new LetExpAS$Class();
		res.setVariables(variables);
		res.setIn(exp);
		return res;
	}
	
	//
	// Build primitive expressions
	//
	/** Build EnumLiteralExp */
	public EnumLiteralExpAS buildEnumLiteralExp(List path) {
		EnumLiteralExpAS exp = new EnumLiteralExpAS$Class();
		exp.setPathName(path);
		return exp;
	}
	/** Build CollectionLiteralExp */
	public CollectionLiteralExpAS buildCollectionLiteralExp(CollectionKindAS kind, List parts) {
		CollectionLiteralExpAS exp = new CollectionLiteralExpAS$Class();
		exp.setKind(kind);
		exp.setCollectionParts(parts);
		return exp;
	}
	/** Build CollectionItem */
	public CollectionItemAS buildCollectionItem(OclExpressionAS exp) {
		CollectionItemAS item = new CollectionItemAS$Class();
		item.setItem(exp);
		return item;
	}
	/** Build CollectionRange */
	public CollectionRangeAS buildCollectionRange(OclExpressionAS first, OclExpressionAS last) {
		CollectionRangeAS exp = new CollectionRangeAS$Class();
		exp.setFirst(first);
		exp.setLast(last);
		return exp;
	}
	/** Build BooleanLiteralExp */
	public BooleanLiteralExpAS buildBooleanLiteralExp(String value) {
		BooleanLiteralExpAS exp = new BooleanLiteralExpAS$Class();
		if (value != null)
			exp.setValue(Boolean.valueOf(value));
		else
			exp.setValue(null);
		return exp;
	}
	/** Build IntegerLiteralExp */
	public IntegerLiteralExpAS buildIntegerLiteralExp(String value) {
		IntegerLiteralExpAS exp = new IntegerLiteralExpAS$Class();
		exp.setValue(Integer.valueOf(value));
		return exp;
	}
	/** Build RealLiteralExp */
	public RealLiteralExpAS buildRealLiteralExp(String value) {
		RealLiteralExpAS exp = new RealLiteralExpAS$Class();
		exp.setValue(Double.valueOf(value));
		return exp;
	}
	/** Build StringLiteralExp */
	public StringLiteralExpAS buildStringLiteralExp(String value) {
		StringLiteralExpAS exp = new StringLiteralExpAS$Class();
		exp.setValue(value.substring(1,value.length()-1));
		return exp;
	}
	/** Build TupleLiteralExp */
	public TupleLiteralExpAS buildTupleLiteralExp(List seq) {
		TupleLiteralExpAS exp = new TupleLiteralExpAS$Class();
		exp.setTupleParts(seq);
		return exp;
	}
}
