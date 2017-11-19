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
 * Halstead Complexity
 * 
 * Compute the Halstead metric
 *  
 */
public class OclHALCEvaluator
	extends Evaluator 
{
	public OclHALCEvaluator(QualityModel qModel) {
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
		operators = 0;
		operands = 0;
		scan(oclDecl);
		double value = operators + operands; 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	
	/** Scan a declaration for variables */
	protected void scan(PackageDeclarationAS oclDecl) {
		if (oclDecl == null) return;
		List decls = oclDecl.getContextDecls();
		for(int i=0; i<decls.size(); i++) {
			ContextDeclarationAS decl = (ContextDeclarationAS)decls.get(i);
			scanContext(decl);
		}
	}
	protected void scanContext(ContextDeclarationAS decl) {
		if (decl == null) return;
		List cons = decl.getConstraints();
		for(int i=0; i<cons.size(); i++) {
			ConstraintAS con = (ConstraintAS)cons.get(i);
			scanConstraint(con);
		}
	}
	protected void scanConstraint(ConstraintAS con) {
		if (con == null) return;
		ConstraintKindAS kind = con.getKind();
		if (kind == ConstraintKind$Class.BODY) {
			OclExpressionAS exp = con.getBodyExpression();
			scanExp(exp);
		} else if (kind == ConstraintKind$Class.DEF) {
			OclExpressionAS exp = con.getBodyExpression();
			scanExp(exp);
			scanVar(con.getDefVariable());
		} else if (kind == ConstraintKind$Class.DERIVE) {
			OclExpressionAS exp = con.getBodyExpression();
			scanExp(exp);
		} else if (kind == ConstraintKind$Class.INIT) {
			OclExpressionAS exp = con.getBodyExpression();
			scanExp(exp);
		} else if (kind == ConstraintKind$Class.INV) {
			OclExpressionAS exp = con.getBodyExpression();
			scanExp(exp);
		} else if (kind == ConstraintKind$Class.POST) {
			OclExpressionAS exp = con.getBodyExpression();
			scanExp(exp);
		} else if (kind == ConstraintKind$Class.PRE) {
			OclExpressionAS exp = con.getBodyExpression();
			scanExp(exp);
		}
	}
	/** 
	 * Nodes source of navigation
	 * Edges <=> navigation operations
	 * Iterations <=> while loops
	 *  
	 * */
	protected void scanExp(OclExpressionAS exp) {
		double res = 0;
		if (exp == null) return;
		if (exp instanceof PrimaryExpAS) {
			if (exp instanceof TupleLiteralExp) {
				TupleLiteralExp tupleExp = (TupleLiteralExp)exp;
				scanVars(tupleExp.getTuplePart());
				// One operand
				operands++;
			} else {
				// One operand
				operands++;
			}
		} else if (exp instanceof SelectionExpAS) {
			SelectionExpAS selExp = (SelectionExpAS)exp;
			scanExp(selExp.getSource());
			// One operand (selected name) and one operator
			operands++;
			operators++;
		} else if (exp instanceof CallExpAS) {
			CallExpAS callExp = (CallExpAS)exp;
			scanExp(callExp.getSource());
			List args = callExp.getArguments();
			scanExps(args);
			// One operator and N operands 
			operators++;
			operands += args.size();
		} else if (exp instanceof LoopExpAS) {
			LoopExpAS loopExp = (LoopExpAS)exp;
			scanExp(loopExp.getSource());
			scanExp(loopExp.getBody());
			scanVar(loopExp.getIterator());			
			scanVar(loopExp.getResult());
			// One operator
			operators++;
		} else if (exp instanceof LogicalExpAS) {
			LogicalExpAS logExp = (LogicalExpAS)exp;
			scanExp(logExp.getLeftOperand());
			scanExp(logExp.getRightOperand());
			// One operator
			operators++;
		} else if (exp instanceof IfExpAS) {
			IfExpAS ifExp = (IfExpAS)exp;
			scanExp(ifExp.getCondition());
			scanExp(ifExp.getThenExpression());
			scanExp(ifExp.getElseExpression());
			// One operator
			operators++;
		} else if (exp instanceof LetExpAS) {
			LetExpAS letExp = (LetExpAS)exp;
			scanVars(letExp.getVariables());
			scanExp(letExp.getIn());
		}
	}
	protected void scanExps(List exps) {
		if (exps == null) return;
		for(int i=0; i<exps.size(); i++) {
			OclExpressionAS exp = (OclExpressionAS)exps.get(i);
			scanExp(exp);
		}
	}
	protected void scanVars(List vars) {
		if (vars == null) return;
		for(int i=0; i<vars.size(); i++) {
			VariableDeclarationAS var = (VariableDeclarationAS)vars.get(i);
			scanVar(var);
		}
	}
	protected void scanVar(VariableDeclarationAS var) {
		if (var == null) ;
		scanExp(var.getInitExp());
	}
	
	//
	// Local properties
	//
	protected double operators;
	protected double operands;	
}
