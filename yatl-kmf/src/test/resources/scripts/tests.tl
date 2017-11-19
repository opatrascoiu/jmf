start kmf::uml2java::main;

namespace kmf (uml, java) {
	transformation uml2java {
		-- test1
		rule test1 match uml::Foundation::Core::Class () {
			-- Create a Java Class
			let jClass: java_::Class;
			jClass := new java_::Class;
			-- Set name
			jClass.name := self.name.body_;
			-- Delete 
			delete jClass;
		}

		-- test2
		rule test2 match uml::Foundation::Core::Class [name.body_ = 'Element'] () {
			-- Create a Java Class
			let jClass: java_::Class_;
			jClass := new java_::Class_;
			-- Set name
			jClass.name := self.name.body_;
		}

		-- test3
		rule test3 match uml::Foundation::Core::Class [name.body_ = n] (n: String) {
			-- Create a Java Class
			let jClass: java_::Class_;
			jClass := new java_::Class_;
			-- Set name
			jClass.name := self.name.body_;
		}

		-- test4
		rule test4 match uml::Foundation::Core::Class [name.body_ = n] (n: String) {
			let i: Integer = 1;
			while i <= 3 do {
				-- Create a Java Class
				let jClass: java_::Class_;
				jClass := new java_::Class_;
				-- Set name
				jClass.name := self.name.body_;
				i:= i+1;
			}
		}

		-- test5
		rule test5 match uml::Foundation::Core::Class [name.body_ = n] (n: String) {
			let i: Integer = 1;
			do {
				-- Create a Java Class
				let jClass: java_::Class_;
				jClass := new java_::Class_;
				-- Set name
				jClass.name := self.name.body_;
				i:= i+1;
			} while (i <= 3);
		}

		-- test6
		rule test6 match uml::Foundation::Core::Class [name.body_ = n] (n: String) {
			let col: Set(String) = Set{'A', 'B', 'C'};
			foreach x:String in col do { 
				-- Create a Java Class
				let jClass: java_::Class_;
				jClass := new java_::Class_;
				-- Set name
				jClass.name := x;
			}
		}

		-- test7
		rule test7 match uml::Foundation::Core::Class [name.body_ = 'Element'] () {
			let col: Set(String) = Set{'A', 'B', 'C'};
			let i: Integer = 0;
			foreach x:String in col do { 
				-- Create a Java Class
				let jClass: java_::Class_;
				jClass := new java_::Class_;
				-- Set name
				jClass.name := x;
				-- Break
				i := i+1;
				iff i=2 then
					break;
				endif
			}
		}

		-- test8
		rule test8 match uml::Foundation::Core::Class [name.body_ = 'Element'] () {
			let col: Set(String) = Set{'A', 'B', 'C'};
			let i: Integer = 0;
			foreach x:String in col do { 
				-- Create a Java Class
				let jClass: java_::Class_;
				jClass := new java_::Class_;
				-- Set name
				jClass.name := x;
				-- Continue
				i := i+1;
				iff i=2 then
					continue; 
				endif
			}
		}

		-- main rule
		rule main () {
			-- Map individual elements
			apply test8();
		}
		
		rule syntax match uml::Foundation::Core::Classifier [true] () {
			let x:Integer = 4 in x;
			let x:Integer = 4;
			let y:Set(Integer) = Set{5};
			let z:Real = 5.0;
			z := x;
			if x=3 then 3 else 4 endif; 
			iff x=3 then x:=4; else x:=5; endif
			iff x=3 then x:=4; else ; endif
			iff x=3 then x:=4; endif
			do x:=4; while (x=4);
			foreach x:Real in Set{4} do x:=4;
			while x=4 do x:=4;
			track(4, b, 5);
			break;
			continue;
			delete x;
			delete x+3;
		}
	}
}