/**
 *
 *  Class KtlVisitor.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl;

public interface YatlVisitor {
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Import' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Import host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Transformation' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Transformation host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Unit' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Unit host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Filter' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Filter host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Query' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Query host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Model' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Model host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.NamedElement' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.NamedElement host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Rule' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Rule host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.transformations.Namespace' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.transformations.Namespace host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.TrackExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.TrackExp host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.LifecycleStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.LifecycleStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ContinueStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ContinueStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.Statement' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.Statement host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.IfStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.IfStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeclarationStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeclarationStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.Expression' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.Expression host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ExpressionStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ExpressionStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DeleteStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DeleteStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.BreakStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.BreakStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.DoStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.DoStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.CompoundStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.CompoundStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ForeachStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ForeachStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.ApplyStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.ApplyStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.WhileStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.WhileStm host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.NewExp' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.NewExp host, Object data);
	/** Visit class 'uk.ac.kent.cs.ktl.syntax.statements.LoopStm' */
	public Object visit(uk.ac.kent.cs.yatl.syntax.statements.LoopStm host, Object data);
}
