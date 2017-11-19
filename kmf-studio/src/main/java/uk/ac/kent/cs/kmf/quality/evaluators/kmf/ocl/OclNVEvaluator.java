package uk.ac.kent.cs.kmf.quality.evaluators.kmf.ocl;

import java.util.List;

import uk.ac.kent.cs.kmf.kmfstudio.gui.StudioGUI;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ConstraintKind$Class;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.TupleLiteralExp;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ConstraintAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ConstraintKindAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ContextDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.PackageDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.VariableDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.CallExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.IfExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.LetExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.LogicalExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.LoopExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclExpressionAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.PrimaryExpAS;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.SelectionExpAS;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Variables
 * 
 * Counts the number of variables used in an OCL expression. 
 *   
 */
public class OclNVEvaluator
	extends Evaluator 
{
	public OclNVEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof String) {
			return this.computeMetric((String)elem, m);
		}
		return null;
	}

	/** Compute metric */
	protected Metric computeMetric(String oclString, Metric m) {
		// Get OclProcessor and parse input
		OclProcessor oclProcessor = StudioGUI.metaOclProcessor;
		PackageDeclarationAS oclDecl = oclProcessor.parse(oclString);

		// Compute 
		double value = scan(oclDecl);
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	
	/** Scan a declaration for variables */
	protected double scan(PackageDeclarationAS oclDecl) {
		double res = 0;
		if (oclDecl == null) return res;
		List decls = oclDecl.getContextDecls();
		for(int i=0; i<decls.size(); i++) {
			ContextDeclarationAS decl = (ContextDeclarationAS)decls.get(i);
			res += scanContext(decl);
		}
		return res;
	}
	protected double scanContext(ContextDeclarationAS decl) {
		double res = 0;
		if (decl == null) return res;
		List cons = decl.getConstraints();
		for(int i=0; i<cons.size(); i++) {
			ConstraintAS con = (ConstraintAS)cons.get(i);
			res += scanConstraint(con);
		}
		return res;
	}
	protected double scanConstraint(ConstraintAS con) {
		double res = 0;
		if (con == null) return res;
		ConstraintKindAS kind = con.getKind();
		if (kind == ConstraintKind$Class.BODY) {
			OclExpressionAS exp = con.getBodyExpression();
			res += scanExp(exp);
		} else if (kind == ConstraintKind$Class.DEF) {
			OclExpressionAS exp = con.getBodyExpression();
			res += scanExp(exp);
			res += scanVar(con.getDefVariable());
		} else if (kind == ConstraintKind$Class.DERIVE) {
			OclExpressionAS exp = con.getBodyExpression();
			res += scanExp(exp);
		} else if (kind == ConstraintKind$Class.INIT) {
			OclExpressionAS exp = con.getBodyExpression();
			res += scanExp(exp);
		} else if (kind == ConstraintKind$Class.INV) {
			OclExpressionAS exp = con.getBodyExpression();
			res += scanExp(exp);
		} else if (kind == ConstraintKind$Class.POST) {
			OclExpressionAS exp = con.getBodyExpression();
			res += scanExp(exp);
		} else if (kind == ConstraintKind$Class.PRE) {
			OclExpressionAS exp = con.getBodyExpression();
			res += scanExp(exp);
		}
		return res;
	}
	protected double scanExp(OclExpressionAS exp) {
		double res = 0;
		if (exp == null) return res;
		if (exp instanceof PrimaryExpAS) {
			if (exp instanceof TupleLiteralExp) {
				TupleLiteralExp tupleExp = (TupleLiteralExp)exp;
				res += scanVars(tupleExp.getTuplePart());
			}
		} else if (exp instanceof SelectionExpAS) {
			SelectionExpAS selExp = (SelectionExpAS)exp;
			res += scanExp(selExp.getSource());
		} else if (exp instanceof CallExpAS) {
			CallExpAS callExp = (CallExpAS)exp;
			res += scanExp(callExp.getSource());
			List args = callExp.getArguments();
			res += scanExps(args);
		} else if (exp instanceof LoopExpAS) {
			LoopExpAS loopExp = (LoopExpAS)exp;
			res += scanExp(loopExp.getSource());
			res += scanExp(loopExp.getBody());
			res += scanVar(loopExp.getIterator());			
			res += scanVar(loopExp.getResult());			
		} else if (exp instanceof LogicalExpAS) {
			LogicalExpAS logExp = (LogicalExpAS)exp;
			res += scanExp(logExp.getLeftOperand());
			res += scanExp(logExp.getRightOperand());
		} else if (exp instanceof IfExpAS) {
			IfExpAS ifExp = (IfExpAS)exp;
			res += scanExp(ifExp.getCondition());
			res += scanExp(ifExp.getThenExpression());
			res += scanExp(ifExp.getElseExpression());
		} else if (exp instanceof LetExpAS) {
			LetExpAS letExp = (LetExpAS)exp;
			res += scanVars(letExp.getVariables());
			res += scanExp(letExp.getIn());
		}
		return res;
	}
	protected double scanExps(List exps) {
		double res = 0;
		if (exps == null) return res;
		for(int i=0; i<exps.size(); i++) {
			OclExpressionAS exp = (OclExpressionAS)exps.get(i);
			res += scanExp(exp);
		}
		return res;
	}
	protected double scanVars(List vars) {
		double res = 0;
		if (vars == null) return res;
		for(int i=0; i<vars.size(); i++) {
			VariableDeclarationAS var = (VariableDeclarationAS)vars.get(i);
			res += scanVar(var);
		}
		return res;
	}
	protected double scanVar(VariableDeclarationAS var) {
		double res = 0;
		if (var == null) return 0;
		return 1 + scanExp(var.getInitExp());
	}
}
