/*
 * Created on 17-Jul-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package uk.ac.kent.cs.yatl.semantics;

import java.util.*;

import uk.ac.kent.cs.yatl.*;
import uk.ac.kent.cs.yatl.syntax.parser.ErrorManager;
import uk.ac.kent.cs.yatl.syntax.statements.*;
import uk.ac.kent.cs.yatl.syntax.transformations.*;
import uk.ac.kent.cs.yatl.syntax.transformations.Namespace;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.analyser.OclSemanticAnalyserVisitorImpl;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.*;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.*;
import uk.ac.kent.cs.ocl20.syntax.ast.expressions.*;
import uk.ac.kent.cs.ocl20.syntax.ast.types.*;
import uk.ac.kent.cs.kmf.util.ILog;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class YatlSemanticVisitorImpl
	extends	OclSemanticAnalyserVisitorImpl
	implements YatlVisitor
{
	/** Constructor */ 
	public YatlSemanticVisitorImpl(OclProcessor proc) {
		super(proc);
	}
	
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Unit' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Unit host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Visit namespaces ---
		Iterator i = host.getNamespaces().iterator();
		List newList = new Vector();
		while (i.hasNext()) {
			// Visit namespaces
			Namespace n = (Namespace)i.next();
			newList.add(n.accept(this, data));
		}
		host.setNamespaces(newList);
		// Set the starting rule
		Rule startRule = null;
		List pathName = host.getStartPathName();
		if (pathName.size()== 3) {
			int level = 0;
			Iterator i1 = host.getNamespaces().iterator();
			while (i1.hasNext()) {
				Namespace n = (Namespace)i1.next();
				if (n.getName().equals(pathName.get(level))) {
					level++;		
					Iterator i2 = n.getTransformations().iterator();
					while (i2.hasNext()) {
						Transformation t = (Transformation)i2.next();
						if (t.getName().equals(pathName.get(level))) {
							level++;		
							Iterator i3 = t.getRules().iterator();
							while (i3.hasNext()) {
								Rule r = (Rule)i3.next();
								if (r.getName().equals(pathName.get(level))) {
									startRule = r;
								}
							}
						}
					}		
				}
			}
		}
		if (startRule == null) {
			String ruleStr = "";
			for(int k=0; k<pathName.size(); k++) {
				if (k != 0) ruleStr += "::";
				ruleStr += (String)pathName.get(k); 
			}
			ErrorManager.reportError(log, location, "Missing main rule '" + ruleStr + "'");		
		}
		host.setStartRule(startRule);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Namespace' */
	protected Set tqNames = new LinkedHashSet();
	protected void checkTranQue(String name, ILog log) { 
		if (tqNames.contains(name)) {
			ErrorManager.reportError(log, location, "Transformation or query '" + name + "' was already declared");
		} else {
			tqNames.add(name);
		}
	}
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Namespace host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Clear local names
		tqNames.clear();
		//--- Visit transformations ---
		Iterator i = host.getTransformations().iterator();
		List newList = new Vector();
		while (i.hasNext()) {
			Transformation n = (Transformation)i.next();
			newList.add(n.accept(this, data));
			// Check if not previosly declared
			checkTranQue(n.getName(), log);
		}
		host.setTransformations(newList);
		//--- Visit queries ---
		i = host.getQueries().iterator();
		newList = new Vector();
		while (i.hasNext()) {
			Query n = (Query)i.next();
			newList.add(n.accept(this, data));
			// Check if not previosly declared
			checkTranQue(n.getName(), log);
		}
		host.setQueries(newList);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Import' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Import host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Transformation' */
	protected Set rules = new LinkedHashSet();
	protected Rule searchRule(String name, List types) {
		Iterator i = rules.iterator();
		while (i.hasNext()) {
			Rule r = (Rule)i.next();
			// Check name
			if (r.getName().equals(name)) {
				// Check arguments
				List ts = new Vector();
				Iterator j = r.getParameters().iterator();
				while (j.hasNext()) {
					VariableDeclaration varDecl = (VariableDeclaration)j.next();
					ts.add(varDecl.getType());
				}
				if (ts.size() == types.size()) {
					boolean ok = true;
					for(int k=0; k<ts.size(); k++)
						if (!ts.get(k).equals(types.get(k))) {
							ok = false; 
						}
					if (ok) return r;
				}
			}
		}
		return null;
	}
	protected void checkRule(Rule rule, ILog log) {
		List ts = new Vector();
		Iterator j = rule.getParameters().iterator();
		while (j.hasNext()) {
			VariableDeclaration varDecl = (VariableDeclaration)j.next();
			ts.add(varDecl.getType());
		}
		Rule r = searchRule(rule.getName(), ts);
		if (r != null) {
			ErrorManager.reportError(log, location, "Rule '" + rule.getName() + "' was already declared");
		} else {
			rules.add(rule);
		}
	}
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Transformation host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Clear local names
		rules.clear();
		//--- Visit rules ---
		Iterator i = host.getRules().iterator();
		List newList = new Vector();
		while (i.hasNext()) {
			Rule n = (Rule)i.next();
			// Make a copy of the env in order to hide local variables
			Map newData = new HashMap();
			newData.putAll((Map)data);
			newData.put("env", env.clone());
			newList.add(n.accept(this, newData));
			// Check if not previosly declared
			checkRule(n, log);
		}
		host.setRules(newList);		
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Query' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Query host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Rule' */
	protected Set vNames = new LinkedHashSet();
	protected void checkVariable(String name, ILog log) {
		if (vNames.contains(name)) {
			ErrorManager.reportError(log, location, "Identifier '" + name + "' was already declared");
		} else {
			vNames.add(name);
		}
	}
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Rule host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Clear local names
		vNames.clear();
		//--- Visit filter ---
		if (host.getFilter() != null) {
			//--- Add 'self' variable ---
			VariableDeclaration selfVar = new VariableDeclaration$Class();
			selfVar.setName("self");
			//--- Search for pathName classifier ---
			List pathName = host.getFilter().getPathName();
			ModelElement element = env.lookupPathName(pathName);
			if (element instanceof Classifier && element != null) {
				selfVar.setType((Classifier) element);
			} else {
				String typeStr = "";
				for(int i=0; i<pathName.size(); i++) {
					if (i != 0) typeStr += "::";
					typeStr += pathName.get(i);
				}
				ErrorManager.reportError(log, location, "Unknown classifier '"+typeStr+"'.");
				selfVar.setType(processor.getTypeFactory().buildVoidType());
			}
			env = env.addElement("self", selfVar, Boolean.TRUE);
		}
		((Map) data).put("env", env);
		//--- Visit parameters ---
		if (host.getParameters() != null) {
			Iterator i = host.getParameters().iterator();
			List newList = new Vector();
			while (i.hasNext()) {
				VariableDeclarationAS varAS = (VariableDeclarationAS)i.next();
				VariableDeclaration varSem = (VariableDeclaration)varAS.accept(this, data);
				newList.add(varSem);
				// Check if not previosly declared
				checkVariable(varAS.getName(), log);
				// Add parameters to env
				env = env.addElement(varAS.getName(), varSem, Boolean.TRUE);
			}
			host.setParameters(newList);
		}
		((Map) data).put("env", env);
		//--- Visit filter and check expression ---
		if (host.getFilter() != null) host.setFilter((Filter)host.getFilter().accept(this, data));
		//--- Visit body ---
		host.setBody((CompoundStm)host.getBody().accept(this, data));
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Filter' */
	protected void checkCondition(Object exp, ILog log) {
		if (exp instanceof OclExpression) {
			checkCondition((OclExpression)exp, log);
		} else {
			ErrorManager.reportError(log, location, "Condition type should be boolean");
		}
	}
	protected void checkCondition(OclExpression exp, ILog log) {
		Object type = exp.getType();
		if (type instanceof BooleanType && !(type instanceof VoidType)) {
			return;
		}
		ErrorManager.reportError(log, location, "A condition or a filter should have boolean type.");
	}
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Filter host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Visit expression ---
		OclExpressionAS expAS = (OclExpressionAS)host.getExpression();
		if (expAS != null) {
			host.setExpression(expAS.accept(this, data));
			OclExpression expSem = (OclExpression)host.getExpression();
			checkCondition(expSem, log);
		}
		return host;
	}

	//
	// Statements
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeclarationStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeclarationStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit variable declarations ---
		Iterator i = host.getDecls().iterator();
		List newList = new Vector();
		while (i.hasNext()) {
			// Compute abstract and semantic variable
			VariableDeclarationAS varDeclAS = (VariableDeclarationAS) i.next();
			VariableDeclaration semVarDecl = (VariableDeclaration) varDeclAS.accept(this, data);
			// Add abstract variable inside the environment		
			env = env.addElement(varDeclAS.getName(), semVarDecl, new Boolean(false));
			((Map) data).put("env", env);
			newList.add(semVarDecl);
			// Check if not previosly declared
			checkVariable(varDeclAS.getName(), log);
		}
		host.setDecls(newList);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ExpressionStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ExpressionStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit expression ---
		if (host.getExpression() != null)
			host.setExpression((Expression)host.getExpression().accept(this, data));		
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.CompoundStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.CompoundStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit statements ---
		Iterator i = host.getBody().iterator();
		List newList = new Vector();
		while (i.hasNext()) {
			Statement n = (Statement)i.next();
			n.setOwner(host);
			newList.add(n.accept(this, data));
		}
		host.setBody(newList);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeleteStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeleteStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit expression ---
		OclExpression exp = (OclExpression)((OclExpressionAS)host.getExp()).accept(this, data);
		host.setExp(exp);
		//--- Check type ---
		Classifier type = exp.getType();
		if (!(type instanceof OclModelElementType)) {
			ErrorManager.reportError(log, location, "Cannot delete basic types.");
		}
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.BreakStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.BreakStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Check if is contained into a loop ---
		if (loopLevel == 0) {
			ErrorManager.reportError(log, location, "'break' and 'continue' statements must be within a loop statement.");			 
		}
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ContinueStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ContinueStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Check if is contained into a loop ---
		if (loopLevel == 0) {
			ErrorManager.reportError(log, location, "'break' and 'continue' statements must be within a loop statement.");			 
		}
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.IfStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.IfStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit condition ---
		Object exp = host.getExpression();
		host.setExpression(((OclExpressionAS)exp).accept(this, data));
		//--- Visit then ---
		Statement thenStm = (Statement)host.getThenStm().accept(this, data);  
		host.setThenStm(thenStm);
		thenStm.setOwner(host);
		//--- Visit else ---
		if (host.getElseStm() != null) {
			Statement elseStm = (Statement)host.getElseStm().accept(this, data);
			elseStm.setOwner(host); 
			host.setElseStm(elseStm);	
		}
		//--- Check condition
		checkCondition(host.getExpression(), log);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.WhileStm' */
	protected int loopLevel = 0;
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.WhileStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit condition ---
		host.setExpression(((OclExpressionAS)host.getExpression()).accept(this, data));	
		//--- Visit body ---
		loopLevel++;
		Statement body = (Statement)host.getBody().accept(this, data);
		body.setOwner(host); 
		host.setBody(body);
		loopLevel--;	
		//--- Check condition
		checkCondition(host.getExpression(), log);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DoStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DoStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit condition ---
		host.setExpression(((OclExpressionAS)host.getExpression()).accept(this, data));	
		//--- Visit body ---
		loopLevel++;
		Statement body = (Statement)host.getBody().accept(this, data);
		body.setOwner(host); 
		host.setBody(body);
		loopLevel--;	
		//--- Check condition
		checkCondition(host.getExpression(), log);
		return host;
	}
	/** Check if an expression is collection */
	protected void checkCollection(Object exp, ILog log) {
		if (exp instanceof OclExpression) {
			Object type = ((OclExpression)exp).getType();
			if (type instanceof CollectionType) {
				return;
			}
		}
		ErrorManager.reportError(log, location, "Condition type should be boolean");
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ForeachStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ForeachStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit declaration and add variable into context ---
		VariableDeclaration varDecl = (VariableDeclaration)((VariableDeclarationAS)host.getVarDecl()).accept(this, data);
		host.setVarDecl(varDecl);
		// Add abstract variable inside the environment		
		env = env.addElement(varDecl.getName(), varDecl, new Boolean(false));
		((Map) data).put("env", env);	
		//--- Visit collection ---
		host.setExpression(((OclExpressionAS)host.getExpression()).accept(this, data));	
		//--- Visit body ---
		loopLevel++;
		Statement body = (Statement)host.getBody().accept(this, data);
		body.setOwner(host); 
		host.setBody(body);
		loopLevel--;	
		//--- Check collection
		checkCollection(host.getExpression(), log);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ApplyStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ApplyStm host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Setup location ---
		location = host.getLocation();
		//--- Visit arguments ---
		Iterator i = host.getArgs().iterator();
		List argList = new Vector();
		List typeList = new Vector();
		while (i.hasNext()) {
			Object obj = i.next();
			OclExpression exp = (OclExpression)((OclExpressionAS)obj).accept(this, data);
			argList.add(exp);
			typeList.add(exp.getType());
		}
		host.setArgs(argList);
		//--- Set rule ---
		Rule rule = searchRule(host.getRuleName(), typeList);
		if (rule == null)
			ErrorManager.reportError(log, location, "Unknown rule '"+host.getRuleName()+"'.");
		host.setRule(rule);
		return host;
	}
	
	//
	// Expressions
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.Expression' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.Expression host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Visit operands ---
		Iterator i = host.getOperands().iterator();
		List newList = new Vector();
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj instanceof OclExpressionAS) {
				OclExpression exp = (OclExpression)((OclExpressionAS)obj).accept(this, data);
				newList.add(exp);
			} else if (obj instanceof TrackExp) {
				newList.add(((TrackExp)obj).accept(this, data));				
			} else if (obj instanceof NewExp) {
				newList.add(((NewExp)obj).accept(this, data));				
			}
		}
		host.setOperands(newList);
		//--- Check and set type ---
		boolean isAssignment = host.getOperands().size() == 2; 
		//--- oclExpression := (oclExpression | trackExp | newExp) ---
		if (isAssignment) {
			Object left = host.getOperands().get(0);
			Object right = host.getOperands().get(1);
			Classifier leftType = ((OclExpression)left).getType();
			Classifier rightType = null;
			if (right instanceof OclExpression) rightType = ((OclExpression)right).getType();
			else if (right instanceof TrackExp) rightType = (Classifier)((TrackExp)right).getType();
			else if (right instanceof NewExp)  rightType = (Classifier)((NewExp)right).getType();
			host.setType(leftType);
			if (rightType == null || !rightType.conformsTo(leftType).booleanValue()) {
				ErrorManager.reportError(log, location, "Non-conforming types '" + leftType + "' and '" + rightType + "' in assignment expression.");
			}
		//--- oclExpression | trackExp ---
		} else {
			Object left = host.getOperands().get(0);
			Classifier leftType = null;
			if (left instanceof OclExpression) leftType = ((OclExpression)left).getType();
			else if (left instanceof TrackExp) leftType = (Classifier)((TrackExp)left).getType();
			host.setType(leftType);
		}
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.TrackExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.TrackExp host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Set type ---
		host.setType(processor.getTypeFactory().buildVoidType());
		//--- Visit expressions ---
		OclExpressionAS expAS1 = (OclExpressionAS)host.getOperands().get(1);
		OclExpression exp1 = null;
		if (expAS1 != null) exp1 = (OclExpression)expAS1.accept(this, data);
		OclExpressionAS expAS2 = (OclExpressionAS)host.getOperands().get(2);
		OclExpression exp2 = null;
		if (expAS2 != null) exp2 = (OclExpression)expAS2.accept(this, data);
		host.getOperands().set(1, exp1);
		host.getOperands().set(2, exp2);		
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.NewExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.NewExp host, Object data) {
		//--- Unpack arguments ---
		Environment env = (Environment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Set type ---
		ClassifierAS typeAS = new ClassifierAS$Class();
		typeAS.setPathName(host.getPathName());
		TypeExp typeExp = (TypeExp)typeAS.accept(this, data);
		Classifier type = (Classifier)typeExp.getType();
		host.setType(type);
		return host;
	}
	
	//
	// Abstract elements
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.NamedElement' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.NamedElement host, Object data) {
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Model' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Model host, Object data) {
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.Statement' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.Statement host, Object data) {
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.LifecycleStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.LifecycleStm host, Object data) {
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.LoopStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.LoopStm host, Object data) {
		return host;
	}
}
