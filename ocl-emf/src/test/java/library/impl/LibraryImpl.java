/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package library.impl;

import java.util.Collection;

import library.Author;
import library.Book;
import library.Library;
import library.LibraryPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link library.impl.LibraryImpl#getName <em>Name</em>}</li>
 *   <li>{@link library.impl.LibraryImpl#getBooks <em>Books</em>}</li>
 *   <li>{@link library.impl.LibraryImpl#getWriters <em>Writers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LibraryImpl extends EObjectImpl implements Library {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBooks() <em>Books</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooks()
	 * @generated
	 * @ordered
	 */
	protected EList books = null;

	/**
	 * The cached value of the '{@link #getWriters() <em>Writers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWriters()
	 * @generated
	 * @ordered
	 */
	protected EList writers = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LibraryPackage.eINSTANCE.getLibrary();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getBooks() {
		if (books == null) {
			books = new EObjectContainmentEList(Book.class, this, LibraryPackage.LIBRARY__BOOKS);
		}
		return books;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getWriters() {
		if (writers == null) {
			writers = new EObjectContainmentEList(Author.class, this, LibraryPackage.LIBRARY__WRITERS);
		}
		return writers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (baseClass == null ? featureID : eDerivedStructuralFeatureID(featureID, baseClass)) {
				case LibraryPackage.LIBRARY__BOOKS:
					return ((InternalEList)getBooks()).basicRemove(otherEnd, msgs);
				case LibraryPackage.LIBRARY__WRITERS:
					return ((InternalEList)getWriters()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case LibraryPackage.LIBRARY__NAME:
				return getName();
			case LibraryPackage.LIBRARY__BOOKS:
				return getBooks();
			case LibraryPackage.LIBRARY__WRITERS:
				return getWriters();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case LibraryPackage.LIBRARY__NAME:
				setName((String)newValue);
				return;
			case LibraryPackage.LIBRARY__BOOKS:
				getBooks().clear();
				getBooks().addAll((Collection)newValue);
				return;
			case LibraryPackage.LIBRARY__WRITERS:
				getWriters().clear();
				getWriters().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case LibraryPackage.LIBRARY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case LibraryPackage.LIBRARY__BOOKS:
				getBooks().clear();
				return;
			case LibraryPackage.LIBRARY__WRITERS:
				getWriters().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case LibraryPackage.LIBRARY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case LibraryPackage.LIBRARY__BOOKS:
				return books != null && !books.isEmpty();
			case LibraryPackage.LIBRARY__WRITERS:
				return writers != null && !writers.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //LibraryImpl
