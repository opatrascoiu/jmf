/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package library;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link library.Library#getName <em>Name</em>}</li>
 *   <li>{@link library.Library#getBooks <em>Books</em>}</li>
 *   <li>{@link library.Library#getWriters <em>Writers</em>}</li>
 * </ul>
 * </p>
 *
 * @see library.LibraryPackage#getLibrary()
 * @model 
 * @generated
 */
public interface Library extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see library.LibraryPackage#getLibrary_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link library.Library#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Books</b></em>' containment reference list.
	 * The list contents are of type {@link library.Book}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Books</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Books</em>' containment reference list.
	 * @see library.LibraryPackage#getLibrary_Books()
	 * @model type="library.Book" containment="true"
	 * @generated
	 */
	EList getBooks();

	/**
	 * Returns the value of the '<em><b>Writers</b></em>' containment reference list.
	 * The list contents are of type {@link library.Author}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Writers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Writers</em>' containment reference list.
	 * @see library.LibraryPackage#getLibrary_Writers()
	 * @model type="library.Author" containment="true"
	 * @generated
	 */
	EList getWriters();

} // Library
