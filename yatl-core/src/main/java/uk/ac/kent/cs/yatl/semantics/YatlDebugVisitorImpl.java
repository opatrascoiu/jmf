 /**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.yatl.semantics;

import java.util.*;

import uk.ac.kent.cs.yatl.YatlVisitor;
import uk.ac.kent.cs.yatl.syntax.transformations.*;
import uk.ac.kent.cs.yatl.syntax.statements.*;
import uk.ac.kent.cs.ocl20.semantics.analyser.OclDebugVisitorImpl;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OclExpression;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.VariableDeclaration;

public class YatlDebugVisitorImpl 
	extends	OclDebugVisitorImpl
	implements YatlVisitor
{
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Unit' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Unit host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = "";
		//--- Visit namespaces ---
		Iterator i = host.getNamespaces().iterator();
		while (i.hasNext()) {
			Namespace n = (Namespace)i.next();
			result += (String)n.accept(this, data);
		}
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Namespace' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Namespace host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String sourceModel = (String)(host.getSourceModel() == null ? "null" : host.getSourceModel().accept(this, data));
		String targetModel = (String)(host.getTargetModel() == null ? "null" : host.getTargetModel().accept(this, data));
		String result = indent + "Namespace "+host.getName()+"("+sourceModel+", "+targetModel+") {\n";
		//--- Visit transformations ---
		Iterator i = host.getTransformations().iterator();
		while (i.hasNext()) {
			Transformation n = (Transformation)i.next();
			result += (String)n.accept(this, ((String)data)+tab);
		}
		//--- Visit queries ---
		i = host.getQueries().iterator();
		while (i.hasNext()) {
			Query n = (Query)i.next();
			result += (String)n.accept(this, ((String)data)+tab);
		}
		result += indent + "}\n";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Model' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Model host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent + host.getName(); 
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Import' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Import host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent + "import "+host.getName()+".*\n";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Transformation' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Transformation host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"Transformation "+host.getName()+" {\n";
		//--- Visit rules ---
		Iterator i = host.getRules().iterator();
		while (i.hasNext()) {
			Rule n = (Rule)i.next();
			result += (String)n.accept(this, ((String)data)+tab);
		}
		result += indent+"}\n";		
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Query' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Query host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent + "Query()";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Rule' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Rule host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		//--- Visit filter ---
		String filter = "";
		if (host.getFilter() != null)
			filter = (String)host.getFilter().accept(this, "");
		//--- Visit parameters ---
		String params = "";
		if (host.getParameters() != null) {
			Iterator i = host.getParameters().iterator();
			while (i.hasNext()) {
				VariableDeclaration n = (VariableDeclaration)i.next();
				params += n.accept(this, "");
				if (i.hasNext()) params += ", ";
			}
		}
		//--- Visit body ---
		String body = (String)host.getBody().accept(this, data);
		String result = indent+"rule "+host.getName()+" "+filter+" ("+params+")"+"\n";
		result += body;
		result += indent+"\n";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Filter' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Filter host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"match "+host.getPathName()+"[";
		//--- Visit expression ---
		OclExpression exp = (OclExpression)host.getExpression();
		if (exp != null)
			result += exp.accept(this, "");
		result += "]";
		return result;
	}

	//
	// Statements
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeclarationStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeclarationStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = "";
		//--- Visit variable declarations ---
		Iterator i = host.getDecls().iterator();
		while (i.hasNext()) {
			VariableDeclaration n = (VariableDeclaration)i.next();
			result += n.accept(this, data);
			if (i.hasNext()) result += "\n";
		}
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ExpressionStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ExpressionStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent;
		//--- Visit expression ---
		if (host.getExpression() != null) {
			Object exp = host.getExpression();
			if (exp instanceof Expression) {
				result = (String)((Expression)exp).accept(this, data);
			} else if (exp instanceof OclExpression) {
				result = (String)((OclExpression)exp).accept(this, data);
			} else if (exp instanceof TrackExp) {
				result = (String)((TrackExp)exp).accept(this, data);
			} else if (exp instanceof NewExp) {
				result = (String)((NewExp)exp).accept(this, data);
			}
		}
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.CompoundStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.CompoundStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"{\n";
		//--- Visit statements ---
		Iterator i = host.getBody().iterator();
		while (i.hasNext()) {
			Statement n = (Statement)i.next();
			result += n.accept(this, ((String)data)+tab)+";\n";
		}
		result += indent+"}";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeleteStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeleteStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"deleteStm (\n";
		result += ((OclExpression)host.getExp()).accept(this, tab+indent)+"\n";
		result += indent+")";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.BreakStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.BreakStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"breakStm";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ContinueStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ContinueStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"continueStm";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.IfStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.IfStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = "";
		//--- Visit condition ---
		String condStr = (String)((OclExpression)host.getExpression()).accept(this, tab+indent);	
		//--- Visit then ---
		String thenStr = (String)host.getThenStm().accept(this, tab+indent);	
		//--- Visit else ---
		String elseStr = "";
		if (host.getElseStm() != null)
			elseStr += host.getElseStm().accept(this, tab+indent);
		result += indent+"ifStm (\n";
		result += condStr+"\n";
		result += thenStr+"\n";
		result += elseStr+"\n";
		result += indent+")";	
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.WhileStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.WhileStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = "";
		//--- Visit condition ---
		String condStr = (String)((OclExpression)host.getExpression()).accept(this, tab+indent);	
		//--- Visit body ---
		String bodyStr = (String)host.getBody().accept(this, tab+indent);
		result += indent+"whileStm (\n";
		result += condStr+"\n";
		result += bodyStr+"\n";
		result += indent+")";	
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DoStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DoStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = "";
		//--- Visit condition ---
		String condStr = (String)((OclExpression)host.getExpression()).accept(this, tab+indent);	
		//--- Visit body ---
		String bodyStr = (String)host.getBody().accept(this, tab+indent);
		result += indent+"doStm (\n";
		result += bodyStr+"\n";	
		result += condStr+"\n";
		result += indent+")";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ForeachStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ForeachStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = "";
		//--- Visit declaration ---
		String declStr = (String)((VariableDeclaration)host.getVarDecl()).accept(this, tab+indent);	
		//--- Visit collection ---
		OclExpression exp = (OclExpression)host.getExpression();
		String expStr = "null";
		if (exp != null)
			expStr = (String)exp.accept(this, tab+indent);	
		//--- Visit body ---
		String bodyStr = (String)host.getBody().accept(this, tab+indent);
		result += indent+"foreachStm (\n";
		result += declStr+"\n";
		result += expStr+"\n";
		result += bodyStr+"\n";	
		result += indent+")";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ApplyStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ApplyStm host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = "";
		//--- Visit arguments ---
		Iterator i = host.getArgs().iterator();
		String args = "";
		while (i.hasNext()) {
			Expression n = (Expression)i.next();
			args += n.accept(this, tab+indent);
			args += "\n";
		}
		result += indent+"applyStm "+host.getRuleName()+"(\n";
		result += args;
		result += indent+")";
		return result;
	}
	
	//
	// Expressions
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.Expression' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.Expression host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String arg = indent;
		boolean isAssignment = host.getOperands().size() == 2; 
		String result = "";
		if (isAssignment) {
			arg = tab + indent;
			result = indent+":= (\n";
		} 
		//--- Visit operands ---
		Iterator i = host.getOperands().iterator();
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj instanceof OclExpression) {
				result += ((OclExpression)obj).accept(this, arg);
			} else if (obj instanceof TrackExp) {
				result += ((TrackExp)obj).accept(this, arg);
			} else if (obj instanceof NewExp) {
				result += ((NewExp)obj).accept(this, arg);
			}
			if (isAssignment) result += "\n";
		}
		if (isAssignment) result += indent+")";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.TrackExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.TrackExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"trackExp (";
		//--- Visit operands ---
		Iterator i = host.getOperands().iterator();
		while (i.hasNext()) {
			Object obj = i.next();
			result += obj;
			if (i.hasNext()) result += ", ";
		}
		result += ")";
		return result;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.NewExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.NewExp host, Object data) {
		//--- Unpack arguments ---
		String indent = (String)data;
		String result = indent+"newExp "+host.getPathName().toString();
		return result;
	}
	
	//
	// Abstract elements
	//
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.NamedElement' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.NamedElement host, Object data) {
		return null;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.Statement' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.Statement host, Object data) {
		return null;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.LifecycleStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.LifecycleStm host, Object data) {
		return null;
	}
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.LoopStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.LoopStm host, Object data) {
		return null;
	}
}
