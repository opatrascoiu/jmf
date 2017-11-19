start kmf::uml2java::main;

namespace kmf(uml, java) {
	transformation uml2java {
		-- 1-1 Mappings
		-- Map a UML package to a Java package
		rule umlPkg2JavaPkg match uml::Model_Management::Package () {
			-- Create Java package
			let jPkg: javaModel::JavaPackage;
			jPkg := new javaModel::JavaPackage;
			-- Set name
			jPkg.name := self.name.body_;
			-- Store mapping
			track(self, pkg2pkg, jPkg);
		}
		-- Map a UML class to a Java class
		rule umlClass2JavaClass match uml::Foundation::Core::Class () {
			-- Create Java class
			let jClass: javaModel::JavaClass;
			jClass := new javaModel::JavaClass;
			-- Set name
			jClass.name := self.name.body_;
			-- Store mapping
			track(self, class2class, jClass);
		}
		-- Map a UML attribute to a Java field
		rule umlAttribute2JavaField match uml::Foundation::Core::Attribute () {
			-- Create a Java Field
			let jField: javaModel::JavaField;
			jField := new javaModel::JavaField;
			-- Set name
			jField.name := self.name.body_;
			-- Store mapping
			track(self, attribute2field, jField);
		}
		-- Map a UML association end to a Java field
		rule umlAssociationEnd2JavaField match uml::Foundation::Core::AssociationEnd () {
			-- Create the Java field
			let jField: javaModel::JavaField;
			jField := new javaModel::JavaField;
			-- Set name
			iff self.name.oclIsUndefined() then 
				jField.name := self.type.name.body_; 
			else 
				jField.name := self.name.body_; 
			endif
--			jField.name := if self.name.oclIsUndefined() then self.type.name.body_ else self.name.body_ endif;
--			jField.name := if self.name->isEmpty() then self.type.name.body_ else self.name.body_ endif;
			-- Store mapping
			track(self, associationEnd2field, jField);
		}
		-- Map a UML method to a Java operation
		rule umlOperation2JavaMethod match uml::Foundation::Core::Operation () {
			-- Create a Java Method
			let jMethod: javaModel::JavaMethod;
			jMethod := new javaModel::JavaMethod;
			-- Set name
			jMethod.name := self.name.body_;
			-- Store mapping
			track(self, operation2method, jMethod);
		}

		-- Link all the elements to the corresponding package
		rule linkElement2Pkg match uml::Model_Management::Package () {
			-- Get the corresponding JavaPackage
			let jPkg: javaModel::JavaPackage;
			jPkg := track(self, pkg2pkg, null);
			-- For each owned element
			foreach e:uml::Foundation::Core::Classifier in self.ownedElement do {
				-- Get the Java classifier
				let jCls: javaModel::JavaClassifier;
				jCls := track(e, class2class, null);
				jPkg.elements := jPkg.elements->including(jCls);
			}
		}
		
		-- Link all the fields to the corresponding class
		rule linkAttribute2Class match uml::Foundation::Core::Attribute () {
			-- Get the Java Class that owns the corresponding field
			let umlOwner: uml::Foundation::Core::Classifier, jClass : javaModel::JavaClass;
			umlOwner := self.owner;
			jClass := track(umlOwner, class2class, null);
			-- Get the Java Field
			let jField: javaModel::JavaField;
			jField := track(self, attribute2field, null);
			-- Link field and class
			jClass.fields := jClass.fields->including(jField);
			jField.javaClass := jClass;
		}
		rule linkAssociationEnd2Class match uml::Foundation::Core::AssociationEnd () {
			-- Get the AssociationEnds
			let ends: Set(uml::Foundation::Core::AssociationEnd) = self.association.connection->asSet();
			let otherEnd: uml::Foundation::Core::AssociationEnd = (ends->asSet()-Set{self})->asSequence()->at(1);
			-- Get the Java Class that owns the corresponding field
			let umlOwner: uml::Foundation::Core::Classifier, jClass: javaModel::JavaClass;
			umlOwner := otherEnd.type;
			jClass := track(umlOwner, class2class, null);
			-- Get the Java Field
			let jField: javaModel::JavaField;
			jField := track(self, associationEnd2field, null);
			-- Link field and class
			jClass.fields := jClass.fields->including(jField);
			jField.javaClass := jClass;
		}
		-- Link all the operations to the corresponding class
		rule linkOperation2Class match uml::Foundation::Core::Operation () {
			-- Get the UML Class that owns the attribute
			let umlOwner: uml::Foundation::Core::Classifier, jClass: javaModel::JavaClass;
			umlOwner := self.owner;
			jClass := track(umlOwner, class2class, null);
			-- Get the Java Method
			let jMethod: javaModel::JavaMethod;
			jMethod := track(self, operation2field, null);
			-- Link method and class
			jClass.methods := jClass.methods->including(jMethod);
			jMethod.javaClasses := jMethod.javaClasses->including(jClass);
		}

		-- main rule
		rule main () {
			-- Map individual elements
			apply umlPkg2JavaPkg();
			apply umlClass2JavaClass();
			apply umlAttribute2JavaField();
			apply umlAssociationEnd2JavaField();
			apply umlOperation2JavaMethod();
			-- Add element to Java packages
			apply linkElement2Pkg();
			-- Add fields to Java classes
			apply linkAttribute2Class();
			apply linkAssociationEnd2Class();
			-- Add operations to Java classes
			apply linkOperation2Class();
		}
	}
}
