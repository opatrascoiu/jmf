/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.semantics.analyser;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ContextDeclarationAS;

public interface OclSemanticAnalyser {
	/** Semantic analysis */
	public ContextDeclaration analyse(ContextDeclarationAS decl, Environment env);

	/** Semantic analysis */
	public ContextDeclaration analyse(ContextDeclarationAS decl, Environment env, ILog log);

	/** Semantic analysis */
	public ContextDeclaration analyse(ContextDeclarationAS decl, Environment env, ILog log, boolean debugFlag);

	/** Check if the analyser encountered errors */
	public boolean hasErrors();
}
