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
 * Height of Nesting Tree
 * 
 * Counts the height of the nesting tree that describes 
 * the nesting relation. 
 * 
 * Nesting relations that appear in OCL iterations and 
 * let expressions are considered. 
 * 
 */
public class OclHNTEvaluator
	extends Evaluator 
{
	public OclHNTEvaluator(QualityModel qModel) {
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
				res = scanVars(tupleExp.getTuplePart());
			}
		} else if (exp instanceof SelectionExpAS) {
			SelectionExpAS selExp = (SelectionExpAS)exp;
			res = scanExp(selExp.getSource());
		} else if (exp instanceof CallExpAS) {
			CallExpAS callExp = (CallExpAS)exp;
			res = scanExp(callExp.getSource());
			List args = callExp.getArguments();
			res = scanExps(args);
		} else if (exp instanceof LoopExpAS) {
			LoopExpAS loopExp = (LoopExpAS)exp;
			double h1 = scanExp(loopExp.getSource());
			double h2 = scanExp(loopExp.getBody());
			double h3 = scanVar(loopExp.getIterator());
			double h4 = scanVar(loopExp.getResult());
			double max = h2;
			if (h3 > max) {
				max = h3;
			}
			if (h4 > max) {
				max = h4;
			}
			return h1+1+max;
		} else if (exp instanceof LogicalExpAS) {
			LogicalExpAS logExp = (LogicalExpAS)exp;
			double h1 = scanExp(logExp.getLeftOperand());
			double h2 = scanExp(logExp.getRightOperand());
			if (h1 > h2)
				res = h1;
			else
				res = h2;
		} else if (exp instanceof IfExpAS) {
			IfExpAS ifExp = (IfExpAS)exp;
			double h1 = scanExp(ifExp.getCondition());
			double h2 = scanExp(ifExp.getThenExpression());
			double h3 = scanExp(ifExp.getElseExpression());
			if (h2 > h3)
				res = h1 + 1 + h2;
			else 
				res = h1 + 1 + h3;
		} else if (exp instanceof LetExpAS) {
			LetExpAS letExp = (LetExpAS)exp;
			double h1 = scanVars(letExp.getVariables());
			double h2 = scanExp(letExp.getIn());
			res = h1 + 1 + h2;
		}
		return res;
	}
	protected double scanExps(List exps) {
		double res = 0;
		if (exps == null) return res;
		double max = 0; 
		for(int i=0; i<exps.size(); i++) {
			OclExpressionAS exp = (OclExpressionAS)exps.get(i);
			double h = scanExp(exp);
			if (h > max)
				max = h;
		}
		res = max;
		return res;
	}
	protected double scanVars(List vars) {
		double res = 0;
		if (vars == null) return res;
		double max = 0;
		for(int i=0; i<vars.size(); i++) {
			VariableDeclarationAS var = (VariableDeclarationAS)vars.get(i);
			double h = scanVar(var);
			if (h > max)
				max = h;
		}
		res = max;
		return res;
	}
	protected double scanVar(VariableDeclarationAS var) {
		double res = 0;
		if (var == null) return 0;
		return scanExp(var.getInitExp());
	}
}
