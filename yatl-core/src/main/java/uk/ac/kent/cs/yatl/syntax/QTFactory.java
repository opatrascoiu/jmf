package uk.ac.kent.cs.yatl.syntax;

import java.util.*;

import uk.ac.kent.cs.yatl.syntax.statements.*;
import uk.ac.kent.cs.yatl.syntax.transformations.*;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.VariableDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclExpressionAS;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class QTFactory {
	/** Build a Unit */
	public Unit buildUnit(List imports, List pathName, List namespaces) {
		Unit result = new Unit$Class();
		result.setImports(imports);
		result.setNamespaces(namespaces);
		result.setStartPathName(pathName);
		return result;
	}
	
	/** Build a Namespace */
	public Namespace buildNamespace(String name, List models, List qts, Object location) {
		Namespace result = new Namespace$Class();
		result.setName(name);
		List qs = new Vector();
		List ts = new Vector();
		Iterator i = qts.iterator();
		while (i.hasNext()) {
			Object qt = i.next();
			if (qt instanceof Query) qs.add(qt);
			else if (qt instanceof Transformation) ts.add(qt);
		}
		result.setQueries(qs);
		result.setTransformations(ts);
		result.setLocation(location);
		return result;
	}
	
	/** Build an Import */
	public Import buildImport(String name) {
		Import result = new Import$Class();
		result.setName(name);
		return result;
	}
	
	/** Build a Transformation */
	public Transformation buildTransformation(String name, List rules, Object location) {
		Transformation result = new Transformation$Class();
		result.setName(name);
		result.setRules(rules);
		result.setLocation(location);
		return result;
	}

	/** Build a Rule */
	public Rule buildRule(String name, Filter filter, List params, Statement body, Object location) {
		Rule result = new Rule$Class();
		result.setName(name);
		result.setFilter(filter);
		result.setParameters(params);
		result.setBody((CompoundStm)body);
		result.setLocation(location);
		return result;
	}

	/** Build a Filter */
	public Filter buildFilter(List pathName, Object exp) {
		Filter result = new Filter$Class();
		result.setPathName(pathName);
		result.setExpression(exp);
		return result;
	}

	/** Build a DeclarationStm */
	public DeclarationStm buildDeclarationStm(List decls, Object location) {
		DeclarationStm result = new DeclarationStm$Class();
		result.setDecls(decls);
		result.setLocation(location);
		return result;
	}

	/** Build an ExpressionStatement  */
	public ExpressionStm buildExpressionStm(Expression exp, Object location) {
		ExpressionStm result = new ExpressionStm$Class();
		result.setExpression(exp);
		result.setLocation(location);
		return result;
	}

	/** Build an CompoundStatement  */
	public CompoundStm buildCompoundStm(List stms, Object location) {
		CompoundStm result = new CompoundStm$Class();
		result.setBody(stms);
		result.setLocation(location);
		return result;
	}

	/** Build an IfStatement */
	public IfStm buildIfStm(OclExpressionAS cond, Statement thenStm, Statement elseStm, Object location) {
		IfStm result = new IfStm$Class();
		result.setExpression(cond);
		result.setThenStm(thenStm);
		result.setElseStm(elseStm);
		result.setLocation(location);
		return result;
	}

	/** Build a WhileStatement */
	public WhileStm buildWhileStm(OclExpressionAS cond, Statement body, Object location) {
		WhileStm result = new WhileStm$Class();
		result.setExpression(cond);
		result.setBody(body);
		result.setLocation(location);
		return result;
	}

	/** Build a DoStatement */
	public DoStm buildDoStm(OclExpressionAS cond, Statement body, Object location) {
		DoStm result = new DoStm$Class();
		result.setExpression(cond);
		result.setBody(body);
		result.setLocation(location);
		return result;
	}

	/** Build a */
	public ForeachStm buildForeachStm(VariableDeclarationAS varDecl, OclExpressionAS col, Statement body, Object location) {
		ForeachStm result = new ForeachStm$Class();
		result.setVarDecl(varDecl);
		result.setExpression(col);
		result.setBody(body);
		result.setLocation(location);
		return result;
	}

	/** Build a BreakStm */
	public BreakStm buildBreakStm(Object location) {
		BreakStm result = new BreakStm$Class();
		result.setLocation(location);
		return result;
	}

	/** Build a ContinueStm */
	public ContinueStm buildContinueStm(Object location) {
		ContinueStm result = new ContinueStm$Class();
		result.setLocation(location);
		return result;
	}

	/** Build a ApplyStm */
	public ApplyStm buildApplyStm(String name, List args, Object location) {
		ApplyStm result = new ApplyStm$Class();
		result.setRuleName(name);
		result.setArgs(args);
		result.setLocation(location);
		return result;
	}

	/** Build a DeleteStm */
	public DeleteStm buildDeleteStm(OclExpressionAS exp, Object location) {
		DeleteStm result = new DeleteStm$Class();
		result.setExp(exp);
		result.setLocation(location);
		return result;
	}

	/** Build an Expression */
	public Expression buildExpression(Object left, Object right) {
		Expression result = new Expression$Class();
		result.getOperands().add(left);
		result.getOperands().add(right);
		return result;
	}

	/** Build a ReturnStm */
	public TrackExp buildTrackExp(String propName, OclExpressionAS left, OclExpressionAS right) {
		TrackExp result = new TrackExp$Class();
		result.getOperands().add(propName);
		result.getOperands().add(left);
		result.getOperands().add(right);
		return result;
	}

	/** Build a Query */
	public Query buildQuery(String name, List contextDecls, Object location) {
		Query result = new Query$Class();
		result.setName(name);
		result.setContextDeclList(contextDecls);
		result.setLocation(location);
		return result;
	}

	/** Build a NewExp */
	public NewExp buildNewExp(List pathName) {
		NewExp result = new NewExp$Class();
		result.setPathName(pathName);
		return result;
	}
}
