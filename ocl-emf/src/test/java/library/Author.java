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
 * A representation of the model object '<em><b>Author</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link library.Author#getName <em>Name</em>}</li>
 *   <li>{@link library.Author#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @see library.LibraryPackage#getAuthor()
 * @model 
 * @generated
 */
public interface Author extends EObject {
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
	 * @see library.LibraryPackage#getAuthor_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link library.Author#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Books</b></em>' reference list.
	 * The list contents are of type {@link library.Book}.
	 * It is bidirectional and its opposite is '{@link library.Book#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Books</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Books</em>' reference list.
	 * @see library.LibraryPackage#getAuthor_Books()
	 * @see library.Book#getAuthor
	 * @model type="library.Book" opposite="author"
	 * @generated
	 */
	EList getBooks();

} // Author
