/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.yatl.evaluation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

import uk.ac.kent.cs.yatl.YatlVisitor;
import uk.ac.kent.cs.yatl.syntax.statements.*;
import uk.ac.kent.cs.yatl.syntax.transformations.*;
import uk.ac.kent.cs.ocl20.OclProcessor;
//import uk.ac.kent.cs.ocl20.bridge4kmf.KmfImplementationAdapterImpl;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.*;
import uk.ac.kent.cs.ocl20.standard.lib.*;
import uk.ac.kent.cs.ocl20.synthesis.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name;
import uk.ac.kent.cs.kmf.util.*;

public class YatlInterpreterVisitorImpl
	extends OclEvaluatorVisitorImpl
	implements YatlVisitor
{
	/** Constructor */
	public YatlInterpreterVisitorImpl(OclProcessor proc) {
		super(proc);
	}

	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Unit' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Unit host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Visit the main rule ---
		Rule startRule = host.getStartRule();
		if (startRule != null) startRule.accept(this, data);
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Namespace' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Namespace host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Import' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Import host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Transformation' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Transformation host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Query' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Query host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Rule' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Rule host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Get body ---
		CompoundStm body = host.getBody();
		//--- Filtered rule ---
		Filter filter = host.getFilter();
		if (filter != null) {
			//--- Get filter expression ---
			OclExpression filterExp = (OclExpression)filter.getExpression();
			//--- Compute filter pathname ---
			String filterStr = "";
			for(int i=0; i<filter.getPathName().size(); i++) {
				if (i != 0) filterStr += ".";
				String name = (String)filter.getPathName().get(i);
				filterStr += Naming.getCleanName(name);
			}
			//--- Get population ---
			Collection population = processor.getModelImplAdapter().OclModelElement_allInstances(filterStr);
			Iterator j = population.iterator();
			while (j.hasNext()) {
				Object self = j.next();
				//--- Add self variable into environment ---
				env.setValue("self", self);
				//--- Evaluate filter ---
				if (filterExp != null) {
					OclBoolean result = (OclBoolean)filterExp.accept(this, data);
					if (processor.getStdLibAdapter().impl(result).booleanValue()) {
						//---- Evaluate body --- 
						body.accept(this, data);
					}
				} else {
					//---- Evaluate body --- 
					body.accept(this, data);
				}
			}	
		//--- Unfiltered rule ---
		} else {
			//--- Add self variable into environment ---
			env.setValue("self", null);
			//--- Visit body ---
			body.accept(this, data);
		}
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Filter' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Filter host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return host;
	}

	//
	// Statements
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeclarationStm' */
	protected void addVarsToEnv(List vars, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		Iterator i = vars.iterator();
		while (i.hasNext()) {
			VariableDeclaration var = (VariableDeclaration)i.next();
			// Add parameters to env
			OclExpression init = var.getInitExpression();
			Object value = null;
			if (init != null) value = init.accept(this, data); 
			env.setValue(var.getName(), value);
		}
	}
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeclarationStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Add variables to environment ---
		if (host.getDecls() != null) {
			addVarsToEnv(host.getDecls(), data);
		}
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ExpressionStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ExpressionStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Visit expression ---
		if (host.getExpression() != null)
			host.setExpression((Expression)host.getExpression().accept(this, data));		
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.CompoundStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.CompoundStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Visit statements ---
		Iterator i = host.getBody().iterator();
		List newList = new Vector();
		while (i.hasNext()) {
			Statement n = (Statement)i.next();
			StmResult stmResult = (StmResult)n.accept(this, data);
			if (stmResult.endsBreak()) 
				return new StmResult(1);
 			if (stmResult.endsContinue()) 
 				return new StmResult(2);
		}
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeleteStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeleteStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Compute expression value ---
		OclExpression exp = (OclExpression)host.getExp();
		Object expValue = exp.accept(this, data);
		//--- Use the adapter to delete value ---
//		((KmfImplementationAdapterImpl)processor.getModelImplAdapter()).OclModelElement_deleteInstance(expValue);
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.BreakStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.BreakStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return new StmResult(1);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ContinueStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ContinueStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return new StmResult(2);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.IfStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.IfStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		
		//--- Evaluate condition ---
		OclExpression exp = (OclExpression)host.getExpression();
		Object cond = exp.accept(this, data);;
		if (cond instanceof OclBoolean) {
			if (processor.getStdLibAdapter().impl((OclBoolean)cond).booleanValue()) {
				return host.getThenStm().accept(this, data);	
			} else {
				Statement stm = host.getElseStm();
				if (stm != null)
					return host.getElseStm().accept(this, data);
				else
					return new StmResult(0);	
			}
		}
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.WhileStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.WhileStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Get condition and body ---
		OclExpression exp = (OclExpression)host.getExpression();
		Statement body = host.getBody();
		//--- Simulate the loop ---
		do { 
			//--- Evaluate condition ---
			OclBoolean value = (OclBoolean)exp.accept(this, data);
			//--- Check the value and branch ---
			if (processor.getStdLibAdapter().impl(value).booleanValue()) {
				//--- Visit body ---
				StmResult stmResult = (StmResult)body.accept(this, data);
				if (stmResult.endsBreak()) break;
				if (stmResult.endsContinue()) continue;
			} else {
				break;	
			}
		} while (true);
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DoStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DoStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Get condition and body ---
		OclExpression exp = (OclExpression)host.getExpression();
		Statement body = host.getBody();
		//--- Simulate the loop ---
		do { 
			//--- Visit body ---
			StmResult stmResult = (StmResult)body.accept(this, data);
			if (stmResult.endsBreak()) break;
			if (stmResult.endsContinue()) continue;
			//--- Evaluate condition ---
			OclBoolean value = (OclBoolean)exp.accept(this, data);
			//--- Check the value and branch ---
			if (processor.getStdLibAdapter().impl(value).booleanValue()) {
			} else {
				break;	
			}
		} while (true);
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ForeachStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ForeachStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Get variableDeclaration, expression and body ---
		VariableDeclaration varDecl = (VariableDeclaration)host.getVarDecl();
		OclExpression exp = (OclExpression)host.getExpression();
		Statement body = host.getBody();
		//--- Evaluate expression ---
		Collection col = (Collection)processor.getStdLibAdapter().impl((OclCollection)exp.accept(this, data));
		Iterator i = col.iterator();
		while (i.hasNext()) {
			//--- Add variable into environment ---
			Object elemValue = i.next();
			String elemName = varDecl.getName();
			env.setValue(elemName, elemValue);
			//--- Visit body ---
			StmResult stmResult = (StmResult)body.accept(this, data);
			if (stmResult.endsBreak()) break;
			if (stmResult.endsContinue()) continue;
		}
		return new StmResult(0);
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ApplyStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ApplyStm host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Create new environment and link it ---
		RuntimeEnvironment newEnv = env.newEnvironment();
		newEnv.setParent(env);
		//--- Check rule ---
		Rule rule = host.getRule();
		if (rule == null) return new StmResult(0);
		//--- Evaluate arguments and add them to the new environment ---		
		List args = host.getArgs();
		List params = rule.getParameters();
		List argValues = new Vector();
		for(int i=0; i<args.size(); i++) {
			VariableDeclaration param = (VariableDeclaration)params.get(i);
			OclExpression argExp = (OclExpression)args.get(i);
			Object value = argExp.accept(this, data);
			newEnv.setValue(param.getName(), value);
		}
		//--- Invoke the rule ---
		Map newData = new HashMap();
		newData.putAll((Map)data);
		newData.put("env", newEnv);
		rule.accept(this, newData);
		return new StmResult(0);
	}
	
	//
	// Expressions
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.Expression' */
	protected Map track = new HashMap();
	protected void setTrackValue(TrackExp exp, Object data) {
		//--- Get property name and values ---
		List opds = exp.getOperands();
		String name = (String)opds.get(0);
		OclExpression first = (OclExpression)opds.get(1);
		OclExpression second = (OclExpression)opds.get(2);
		Object firstValue = first.accept(this, data);
		Object secondValue = second.accept(this, data);
		//--- Set property ---
		if (track.get(name) == null) track.put(name, new Vector());
		((List)track.get(name)).add(new PairImpl(firstValue, secondValue)); 
		if (processor.getDebug().booleanValue()) {
			String firstName = "";
			if (firstValue instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement) {
				Name umlName = ((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement)firstValue).getName();
				if (umlName != null) {
					firstName = umlName.getBody_();
				}
			}
			System.out.println("Call SetTrack "+firstValue+" '"+firstName+"' "+name+" "+secondValue);
//			System.out.println("Set "+track.get(name));
		} 
	}
	protected Object getTrackValue(TrackExp exp, Object data) {
		//--- Get property name and values ---
		List opds = exp.getOperands();
		String name = (String)opds.get(0);
		OclExpression first = (OclExpression)opds.get(1);
		OclExpression second = (OclExpression)opds.get(2);
		Object firstValue = null;
		if (first != null) firstValue = first.accept(this, data);
		Object secondValue = null;
		if (second != null) secondValue = second.accept(this, data);
		//--- Get property ---
		List result = new Vector();
		if (processor.getDebug().booleanValue()) System.out.println("Call GetTrack "+firstValue+" "+name+" "+secondValue);
		Object relation = track.get(name);
		if (relation == null) {
		} else {
			List list = (List)relation;
			if (firstValue == null) {
				Iterator i = list.iterator();
				while (i.hasNext()) {
					Pair pair = (Pair)i.next();
					Object val1 = pair.getFirst();
					Object val2 = pair.getSecond();
					if (secondValue == val2) {
						result.add(val1);
					}
				}
			} 
			else if (secondValue == null) {
				Iterator i = list.iterator();
				while (i.hasNext()) {
					Pair pair = (Pair)i.next();
					Object val1 = pair.getFirst();
					Object val2 = pair.getSecond();
					if (firstValue == val1) {
						result.add(val2);
					}
				}
			}
		}
		Object finalRes = processor.getStdLibAdapter().Bag(result);
		if (result.size() == 1) finalRes = result.get(0);
		if (processor.getDebug().booleanValue()) System.out.println("Got "+finalRes);
		return finalRes;
	}
	protected boolean needsOclUnwrapping(OclExpression exp) {
		return exp instanceof PropertyCallExp;
	}
	protected Object unwrap(Object obj, ILog log) {
		if (obj instanceof OclUndefined) {
			return null;
		} else if (obj instanceof OclBoolean) {
			return processor.getStdLibAdapter().impl((OclBoolean)obj);
		} else if (obj instanceof OclString) {
			return processor.getStdLibAdapter().impl((OclString)obj);
		} else if (obj instanceof OclInteger) {
			return processor.getStdLibAdapter().impl((OclInteger)obj);
		} else if (obj instanceof OclReal) {
			return processor.getStdLibAdapter().impl((OclReal)obj);
		} else if (obj instanceof OclBag) {
			Collection result = processor.getStdLibAdapter().impl((OclBag)obj);
			try {
				return unwrapCollection(result, log);
			} catch (Exception e) {
				log.reportError("Cannot unwrap object '"+obj+"'");
				return null;
			}
		} else if (obj instanceof OclSet) {
			Collection result = processor.getStdLibAdapter().impl((OclSet)obj);
			try {
				return unwrapCollection(result, log);
			} catch (Exception e) {
				log.reportError("Cannot unwrap object '"+obj+"'");
				return null;
			}
		} else if (obj instanceof OclSequence) {
			Collection result = processor.getStdLibAdapter().impl((OclSequence)obj);
			try {
				return unwrapCollection(result, log);
			} catch (Exception e) {
				log.reportError("Cannot unwrap object '"+obj+"'");
				return null;
			}
		} else if (obj instanceof OclOrderedSet) {
			Collection result = processor.getStdLibAdapter().impl((OclOrderedSet)obj);
			try {
				return unwrapCollection(result, log);
			} catch (Exception e) {
				log.reportError("Cannot unwrap object '"+obj+"'");
				return null;
			}
		}
		return obj;
	}
	private Object unwrapCollection(Collection result, ILog log) throws Exception { 
		Class type = result.getClass();
		Constructor cons = type.getConstructor(new Class[] {});
		Collection result1 = (Collection)cons.newInstance(new Object[]{});
		Iterator i = result.iterator();
		while (i.hasNext()) {
			Object obj1 = i.next();
			result1.add(unwrap(obj1, log));
		}
		return result1;
	}
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.Expression host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Assignment: oclExpression := (oclExpression | trackExp | newExp) ---
		boolean isAssignment = host.getOperands().size() == 2;
		if (isAssignment) {
			//--- Get left and right hand side ---
			OclExpression left = (OclExpression)host.getOperands().get(0);
			Object right = host.getOperands().get(1);
			//--- Evaluate right ---
			Object rightValue = null;
			if (right instanceof OclExpression) {
				if (right != null) {
					rightValue = ((OclExpression)right).accept(this, data);
					if (needsOclUnwrapping(left)) {
						rightValue = unwrap(rightValue, log);
					}
				}
			} else if (right instanceof TrackExp) {
				rightValue = getTrackValue((TrackExp)right, data);
			} else if (right instanceof NewExp) {
				rightValue = ((NewExp)right).accept(this, data);
			}
			//--- Set the value ---
			if (left instanceof VariableExp) {
				String name = ((VariableExp)left).getReferredVariable().getName();
				env.setValue(name, rightValue);
			} else if (left instanceof PropertyCallExp) {
				PropertyCallExp propCall = (PropertyCallExp)left;
				Property prop = propCall.getReferredProperty();
				//--- Compute source and getter name ---
				Object source = propCall.getSource().accept(this, data);
				Classifier sourceType = propCall.getSource().getType();
				String getterName = processor.getModelImplAdapter().getGetterName(prop.getName());
				String setterName = processor.getModelImplAdapter().getSetterName(prop.getName());
				try {
					// Compute setter
					Method getter = getMethod(source, getterName, new Class[]{});
					Method setter = getMethod(source, setterName, new Class[]{getter.getReturnType()});
					// Invoke setter
					if (source != null) setter.invoke(source, new Object[]{rightValue});
				} catch (Exception e) {
					if (processor.getDebug().booleanValue()) e.printStackTrace();
				}
			}
		//--- Usual expression: oclExpression | trackExp ---
		} else {
			//--- Evaluate expression ---
			Object left = host.getOperands().get(0);
			if (left instanceof OclExpression) ((OclExpression)left).accept(this, data);
			else if (left instanceof TrackExp) {
				setTrackValue((TrackExp)left, data);
			} 
		}
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.TrackExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.TrackExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		return host;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.NewExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.NewExp host, Object data) {
		//--- Unpack arguments ---
		RuntimeEnvironment env = (RuntimeEnvironment) ((Map) data).get("env");
		ILog log = (ILog) ((Map) data).get("log");
		//--- Create a new instance ---
		String pathNameStr = "";
		List pathName = host.getPathName();
		for(int i=0; i<pathName.size(); i++) {
			if (i!=0) pathNameStr += ".";
			pathNameStr += pathName.get(i);
		}
		return null;
//		return ((KmfImplementationAdapterImpl)processor.getModelImplAdapter()).OclModelElement_createInstance(pathNameStr);
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

class StmResult {
	//--- 0 - normal ---
	//--- 1 - ends with a break ---
	//--- 2 - ends with a continue --- 
	protected int type;
	public StmResult(int type) {
		this.type = type;
	}
	public boolean endsNormal() {
		return type == 0;
	}
	public boolean endsBreak() {
		return type == 1;
	}
	public boolean endsContinue() {
		return type == 2;
	}
}