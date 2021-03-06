/**
 *
 *  Class JavaModelHUTNVisitor$Class.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel.repository;

public class JavaModelHUTNVisitor$Class
	implements JavaModelHUTNVisitor
{
	/** Visit factory for 'JavaModelFactory' */
	public Object visit(javaModel.JavaModelFactory host, Object data) {
		String str = "javaModel.JavaModelFactory";
		return str;
	}
	/** Visit factory for 'javaModel.JavaClass' */
	public Object visit(javaModel.JavaClassFactory host, Object data) {
		String str = "javaModel.JavaClassFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaClass' */
	public Object visit(javaModel.JavaClass host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaClass ---
		//--- Association fields ---
		str += "    fields"+": ";
		if(host.getFields() == null) str += "null"+"\n";
		else str += host.getFields().toString()+"\n";
		//--- Association methods ---
		str += "    methods"+": ";
		if(host.getMethods() == null) str += "null"+"\n";
		else str += host.getMethods().toString()+"\n";
		//--- Association sources ---
		str += "    sources"+": ";
		if(host.getSources() == null) str += "null"+"\n";
		else str += host.getSources().toString()+"\n";
		//--- Association implements_ ---
		str += "    implements_"+": ";
		if(host.getImplements_() == null) str += "null"+"\n";
		else str += host.getImplements_().toString()+"\n";
		//--- Properties for JavaClassifier ---
		//--- Association sub ---
		str += "    sub"+": ";
		if(host.getSub() == null) str += "null"+"\n";
		else str += host.getSub().toString()+"\n";
		//--- Association super_ ---
		str += "    super_"+": ";
		if(host.getSuper_() == null) str += "null"+"\n";
		else str += host.getSuper_().toString()+"\n";
		//--- Properties for JavaPackageElement ---
		//--- Association javaPackage ---
		str += "    javaPackage"+": ";
		if(host.getJavaPackage() == null) str += "null"+"\n";
		else str += host.getJavaPackage().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.JavaField' */
	public Object visit(javaModel.JavaFieldFactory host, Object data) {
		String str = "javaModel.JavaFieldFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaField' */
	public Object visit(javaModel.JavaField host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaField ---
		//--- Property isFinal ---
		str += "    isFinal"+": ";
		if(host.getIsFinal() == null) str += "null"+"\n";
		else str += host.getIsFinal().toString()+"\n";
		//--- Property isStatic ---
		str += "    isStatic"+": ";
		if(host.getIsStatic() == null) str += "null"+"\n";
		else str += host.getIsStatic().toString()+"\n";
		//--- Property isVolatile ---
		str += "    isVolatile"+": ";
		if(host.getIsVolatile() == null) str += "null"+"\n";
		else str += host.getIsVolatile().toString()+"\n";
		//--- Property isTransient ---
		str += "    isTransient"+": ";
		if(host.getIsTransient() == null) str += "null"+"\n";
		else str += host.getIsTransient().toString()+"\n";
		//--- Association javaClass ---
		str += "    javaClass"+": ";
		if(host.getJavaClass() == null) str += "null"+"\n";
		else str += host.getJavaClass().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.JavaMethod' */
	public Object visit(javaModel.JavaMethodFactory host, Object data) {
		String str = "javaModel.JavaMethodFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaMethod' */
	public Object visit(javaModel.JavaMethod host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaMethod ---
		//--- Property isAbstract ---
		str += "    isAbstract"+": ";
		if(host.getIsAbstract() == null) str += "null"+"\n";
		else str += host.getIsAbstract().toString()+"\n";
		//--- Property isNative ---
		str += "    isNative"+": ";
		if(host.getIsNative() == null) str += "null"+"\n";
		else str += host.getIsNative().toString()+"\n";
		//--- Property isSynchronized ---
		str += "    isSynchronized"+": ";
		if(host.getIsSynchronized() == null) str += "null"+"\n";
		else str += host.getIsSynchronized().toString()+"\n";
		//--- Property isFinal ---
		str += "    isFinal"+": ";
		if(host.getIsFinal() == null) str += "null"+"\n";
		else str += host.getIsFinal().toString()+"\n";
		//--- Property isConstructor ---
		str += "    isConstructor"+": ";
		if(host.getIsConstructor() == null) str += "null"+"\n";
		else str += host.getIsConstructor().toString()+"\n";
		//--- Property isStatic ---
		str += "    isStatic"+": ";
		if(host.getIsStatic() == null) str += "null"+"\n";
		else str += host.getIsStatic().toString()+"\n";
		//--- Property body ---
		str += "    body"+": ";
		if(host.getBody() == null) str += "null"+"\n";
		else str += host.getBody().toString()+"\n";
		//--- Association javaClasses ---
		str += "    javaClasses"+": ";
		if(host.getJavaClasses() == null) str += "null"+"\n";
		else str += host.getJavaClasses().toString()+"\n";
		//--- Association javaException ---
		str += "    javaException"+": ";
		if(host.getJavaException() == null) str += "null"+"\n";
		else str += host.getJavaException().toString()+"\n";
		//--- Association parameters ---
		str += "    parameters"+": ";
		if(host.getParameters() == null) str += "null"+"\n";
		else str += host.getParameters().toString()+"\n";
		//--- Association result ---
		str += "    result"+": ";
		if(host.getResult() == null) str += "null"+"\n";
		else str += host.getResult().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit class for 'javaModel.JavaElement' */
	public Object visit(javaModel.JavaElement host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.JavaPackage' */
	public Object visit(javaModel.JavaPackageFactory host, Object data) {
		String str = "javaModel.JavaPackageFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaPackage' */
	public Object visit(javaModel.JavaPackage host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaPackage ---
		//--- Association elements ---
		str += "    elements"+": ";
		if(host.getElements() == null) str += "null"+"\n";
		else str += host.getElements().toString()+"\n";
		//--- Properties for JavaPackageElement ---
		//--- Association javaPackage ---
		str += "    javaPackage"+": ";
		if(host.getJavaPackage() == null) str += "null"+"\n";
		else str += host.getJavaPackage().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.JavaParameter' */
	public Object visit(javaModel.JavaParameterFactory host, Object data) {
		String str = "javaModel.JavaParameterFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaParameter' */
	public Object visit(javaModel.JavaParameter host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaParameter ---
		//--- Association owner ---
		str += "    owner"+": ";
		if(host.getOwner() == null) str += "null"+"\n";
		else str += host.getOwner().toString()+"\n";
		//--- Association function ---
		str += "    function"+": ";
		if(host.getFunction() == null) str += "null"+"\n";
		else str += host.getFunction().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.JavaPackageElement' */
	public Object visit(javaModel.JavaPackageElementFactory host, Object data) {
		String str = "javaModel.JavaPackageElementFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaPackageElement' */
	public Object visit(javaModel.JavaPackageElement host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaPackageElement ---
		//--- Association javaPackage ---
		str += "    javaPackage"+": ";
		if(host.getJavaPackage() == null) str += "null"+"\n";
		else str += host.getJavaPackage().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.JavaInterface' */
	public Object visit(javaModel.JavaInterfaceFactory host, Object data) {
		String str = "javaModel.JavaInterfaceFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaInterface' */
	public Object visit(javaModel.JavaInterface host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaInterface ---
		//--- Association implementedBy ---
		str += "    implementedBy"+": ";
		if(host.getImplementedBy() == null) str += "null"+"\n";
		else str += host.getImplementedBy().toString()+"\n";
		//--- Properties for JavaClassifier ---
		//--- Association sub ---
		str += "    sub"+": ";
		if(host.getSub() == null) str += "null"+"\n";
		else str += host.getSub().toString()+"\n";
		//--- Association super_ ---
		str += "    super_"+": ";
		if(host.getSuper_() == null) str += "null"+"\n";
		else str += host.getSuper_().toString()+"\n";
		//--- Properties for JavaPackageElement ---
		//--- Association javaPackage ---
		str += "    javaPackage"+": ";
		if(host.getJavaPackage() == null) str += "null"+"\n";
		else str += host.getJavaPackage().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.JavaClassifier' */
	public Object visit(javaModel.JavaClassifierFactory host, Object data) {
		String str = "javaModel.JavaClassifierFactory";
		return str;
	}
	/** Visit class for 'javaModel.JavaClassifier' */
	public Object visit(javaModel.JavaClassifier host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for JavaClassifier ---
		//--- Association sub ---
		str += "    sub"+": ";
		if(host.getSub() == null) str += "null"+"\n";
		else str += host.getSub().toString()+"\n";
		//--- Association super_ ---
		str += "    super_"+": ";
		if(host.getSuper_() == null) str += "null"+"\n";
		else str += host.getSuper_().toString()+"\n";
		//--- Properties for JavaPackageElement ---
		//--- Association javaPackage ---
		str += "    javaPackage"+": ";
		if(host.getJavaPackage() == null) str += "null"+"\n";
		else str += host.getJavaPackage().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.DataType' */
	public Object visit(javaModel.DataTypeFactory host, Object data) {
		String str = "javaModel.DataTypeFactory";
		return str;
	}
	/** Visit class for 'javaModel.DataType' */
	public Object visit(javaModel.DataType host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for DataType ---
		//--- Property kind ---
		str += "    kind"+": ";
		if(host.getKind() == null) str += "null"+"\n";
		else str += host.getKind().toString()+"\n";
		//--- Properties for JavaClassifier ---
		//--- Association sub ---
		str += "    sub"+": ";
		if(host.getSub() == null) str += "null"+"\n";
		else str += host.getSub().toString()+"\n";
		//--- Association super_ ---
		str += "    super_"+": ";
		if(host.getSuper_() == null) str += "null"+"\n";
		else str += host.getSuper_().toString()+"\n";
		//--- Properties for JavaPackageElement ---
		//--- Association javaPackage ---
		str += "    javaPackage"+": ";
		if(host.getJavaPackage() == null) str += "null"+"\n";
		else str += host.getJavaPackage().toString()+"\n";
		//--- Properties for JavaElement ---
		//--- Property name ---
		str += "    name"+": ";
		if(host.getName() == null) str += "null"+"\n";
		else str += host.getName().toString()+"\n";
		str += "  }\n";
		return str;
	}
	/** Visit factory for 'javaModel.DataKind' */
	public Object visit(javaModel.DataKindFactory host, Object data) {
		String str = "javaModel.DataKindFactory";
		return str;
	}
	/** Visit class for 'javaModel.DataKind' */
	public Object visit(javaModel.DataKind host, Object data) {
		String str = "  "+host.toString()+" {\n";
		//--- Properties for DataKind ---
		str += "  }\n";
		return str;
	}
}
