/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.synthesis;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uk.ac.kent.cs.kmf.util.Pair;
import uk.ac.kent.cs.kmf.util.PairImpl;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.Constraint;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;
import uk.ac.kent.cs.ocl20.semantics.model.expressions.OclExpression;

public class OclCodeGeneratorImpl 
	implements OclCodeGenerator
{
	/** 
	 *  Generate code for an Ocl Expression and returns a Pair containing: 
	 *   1. the name of the variable that holds the result or a constant expression
	 *   2. the generated code
	 *  
	 **/
	public Pair generate(OclExpression exp, String indent, OclProcessor processor) {
		//--- Create buffer and output
		StringWriter buffer = new StringWriter(); 
		PrintWriter output = new PrintWriter(buffer);
		
		//--- Generate the code using a visitor
		OclCodeGeneratorVisitorImpl codeGenerator = new OclCodeGeneratorVisitorImpl(output, processor);
		Map data = new HashMap();
		data.put("output", output);
		data.put("indent", indent);
		String result = (String)exp.accept(codeGenerator, data);
		
		//--- Return result
		return new PairImpl(result, buffer.toString()); 
	}

	/** 
	 *  Generate code for an Ocl Constraint and returns a Pair containing: 
	 *   1. the name of the variable that holds the result or a constant expression
	 *   2. the generated code
	 *  
	 **/
	public Pair generate(Constraint con, String indent, OclProcessor processor) {
		return generate(con.getBodyExpression(), indent, processor);
	}

	/** 
	 *  Generate code for a Context Declaration and returns a List of Pairs containing: 
	 *   1. the name of the variable that holds the result or a constant expression
	 *   2. the generated code
	 *  
	 **/
	public List generate(ContextDeclaration con, String indent, OclProcessor processor) {
		//--- Create result
		List result = new Vector();
		//--- For each constraint: genarate code and add resulting Pair to result
		Iterator i = con.getConstraint().iterator();
		while (i.hasNext()) {
			result.add(generate((Constraint)i.next(), indent, processor));
		}
		return result;
	}
}
