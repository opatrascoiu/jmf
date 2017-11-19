start kmf::sd2ocl::main;

namespace kmf(sd, ocl) {
	transformation sd2ocl {
		-- 1-1 Mappings
		-- Map a SD unitary diagram to an OCL expression
		rule ud2let match sd::as::UnitaryDiagram () {
			-- Create let expression
			let letExp: syntax::ast::expressions::LetExpAS;
			letExp := new syntax::ast::expressions::LetExpAS;
			-- Store mapping
			track(self, ud2let, letExp);
		}
		-- Map a SD zone to a variable: init exppression computes the set
		rule z2var match sd::as::Zone () {
			--
			-- Create name(zone): Set{OclAny} = OclAny.allInstances()->select(x:OclAny | x.isKindOf() and ... and not x.isKindOf() and ... and not )
			--
			-- Create OclAny type
			let oclAnyType: syntax::ast::types::ClassifierAS;
			oclAnyType := new syntax::ast::types::ClassifierAS;
			oclAnyType.pathName := Sequence{'OclAny'};
			-- Create type Set{OclAny}
			let setType: syntax::ast::types::SetTypeAS;
			setType := new syntax::ast::types::SetTypeAS;
			setType.elementType := oclAnyType;
			-- Create pathName expression 'OclAny'
			let oclAnyPathNameExp: syntax::ast::expressions::PathNameExpAS;
			oclAnyPathNameExp := new syntax::ast::expressions::PathNameExpAS;
			oclAnyPathNameExp.pathName := Sequence{'OclAny'};
			-- Create OclAny.allInstances selection
			let allInstancesSelection: syntax::ast::expressions::DotSelectionExpAS;
			allInstancesSelection := new syntax::ast::expressions::DotSelectionExpAS;
			allInstancesSelection.source := oclAnyPathNameExp;
			allInstancesSelection.name := 'allInstances';
			-- Create OclAny.allInstances() operation call
			let allInstancesCall: syntax::ast::expressions::OperationCallExpAS;
			allInstancesCall := new syntax::ast::expressions::OperationCallExpAS;
			allInstancesCall.source := allInstancesSelection;
			allInstancesCall.arguments := Sequence{};
			-- Create OclAny.allInstances()->select selection
			let selectExp: syntax::ast::expressions::ArrowSelectionExpAS;
			selectExp := new syntax::ast::expressions::ArrowSelectionExpAS;
			selectExp.source := allInstancesCall;
			selectExp.name := 'select';
			-- Create x: OclAny variable declaration
			let xVar: syntax::ast::contexts::VariableDeclarationAS;
			xVar := new syntax::ast::contexts::VariableDeclarationAS;
			xVar.name := 'x';
			xVar.type := oclAnyType;
			-- Create filters: isKindOf and notIsKindOf
			let filters: Sequence(syntax::ast::expressions::OclExpressionAS);
			filters := Sequence{};
			let isKindOfSelection: syntax::ast::expressions::DotSelectionExpAS;
			let isKindOfCall: syntax::ast::expressions::OperationCallExpAS;
			let contourPathNameExp: syntax::ast::expressions::PathNameExpAS;
			foreach c: sd::as::Contour in self.containingContours do {
				-- Create name(c) path name
				contourPathNameExp := new syntax::ast::expressions::PathNameExpAS;
				contourPathNameExp.pathName := Sequence{c.name}; 
				-- Create x.isKindOf
				isKindOfSelection := new syntax::ast::expressions::DotSelectionExpAS;
				isKindOfSelection.source := xVar;
				isKindOfSelection.name := 'isKindOf';
				-- Create x.isKindOf(c.name)
				isKindOfCall := new syntax::ast::expressions::OperationCallExpAS;
				isKindOfCall.source := isKindOfSelection;
				isKindOfCall.arguments := Sequence{contourPathNameExp};
				-- Add it to filters
				filters := filters->including(isKindOfCall);				
			}
			foreach c: sd::as::Contour in self.excludingContours do {
				-- Create name(c) path name
				contourPathNameExp := new syntax::ast::expressions::PathNameExpAS;
				contourPathNameExp.pathName := Sequence{c.name}; 
				-- Create x.isKindOf
				isKindOfSelection := new syntax::ast::expressions::DotSelectionExpAS;
				isKindOfSelection.source := xVar;
				isKindOfSelection.name := 'isKindOf';
				-- Create x.isKindOf(c.name)
				isKindOfCall := new syntax::ast::expressions::OperationCallExpAS;
				isKindOfCall.source := isKindOfSelection;
				isKindOfCall.arguments := Sequence{contourPathNameExp};
				-- Create not x.isKindOf(c.name)
				let notSelection: syntax::ast::expressions::DotSelectionExpAS;
				notSelection := new syntax::ast::expressions::DotSelectionExpAS;
				notSelection.source := isKindOfCall;
				notSelection.name := 'not';
				let notCall: syntax::ast::expressions::OperationCallExpAS;
				notCall := new syntax::ast::expressions::OperationCallExpAS;
				notCall.source := notSelection;
				notCall.arguments := Sequence{};
				-- Add it to filters
				filters := filters->including(notCall);				
			}
			-- Compute iterator's body
			let itBody: syntax::ast::expressions::OclExpressionAS;
			itBody := filters->at(1);
			let i:Integer = 2;
			while i <= filters->size() do {
				-- Create itBody.and 
				let andSelection: syntax::ast::expressions::DotSelectionExpAS;
				andSelection := new syntax::ast::expressions::DotSelectionExpAS;
				andSelection.name := 'and';
				andSelection.source := itBody;
				-- Create itBody.and(args)
				let andCall: syntax::ast::expressions::OperationCallExpAS;
				andCall := new syntax::ast::expressions::OperationCallExpAS;
				andCall.source := andSelection;
				andCall.arguments := Sequence{filters->at(i)};
				-- Set new value for itBody
				itBody := andCall;				
				-- Next filter
				i := i + 1;
			}
			-- Create iterator expression OclAny.allInstances()->select( ... )
			let iteratorExp: syntax::ast::expressions::IteratorExpAS;
			iteratorExp := new syntax::ast::expressions::IteratorExpAS;
			iteratorExp.source := selectExp;
			iteratorExp.iterator := xVar;
			iteratorExp.loopBody := itBody;
			-- Compute zone's name
			let zName: String = '';
			foreach c: sd::as::Contour in self.containingContours do {
				zName := zName.concat(c.name);
				zName := zName.concat('_');
			}
			zName := zName.concat('|');
			foreach c: sd::as::Contour in self.excludingContours do {
				zName := zName.concat('_');
				zName := zName.concat(c.name);
			}
			-- Create name(zone): Set{OclAny} := OclAny.allInstances()->select( ... )
			let var: syntax::ast::contexts::VariableDeclarationAS;
			var := new syntax::ast::contexts::VariableDeclarationAS;
			var.name := zName;
			var.type := setType;
			var.initExp := iteratorExp;
			-- Store mapping
			track(self, z2var, var);			
		}

		-- Map a SD to let's body (in expression) 
		rule ud2in match sd::as::UnitaryDiagram () {
			-- Make a list of conditions for each zone
			let ands: Sequence(syntax::ast::expressions::OclExpressionAS) = Sequence{};
			-- For each zone
			foreach z: sd::as::Zone in self.zones do {
				-- Compute the number of spiders touching the zone
				-- All spiders are single footed
				let feetNo: Integer = 0;
				foreach s: sd::as::Spider in self.spiders do {
					iff s.habitat->includes(z) then
						feetNo := feetNo + 1;
					endif
				}
				-- Compute is shaded flag
				let isShaded: Boolean = self.shadedZones->includes(z);
				-- Make the expression that checks the size
				-- name(z)->size() operator feetNo
				-- Make name(z) expression
				let varExp: syntax::ast::expressions::VariableExpAS;
				varExp := new syntax::ast::expressions::VariableExpAS;
				varExp.variableDeclarationAS := track(z, z2var, null);
				-- Make name(z)->size
				let selectExp: syntax::ast::expressions::ArrowSelectionExpAS;
				selectExp := new syntax::ast::expressions::ArrowSelectionExpAS;
				selectExp.source = varExp;
				selectExp.name := 'size';
				-- Make name(z)->size()
				let callExp: syntax::ast::expressions::OperationCallExpAS;
				callExp := new syntax::ast::expressions::OperationCallExpAS;
				callExp.source := selectExp;
				-- Make operator
				let opName: String = '>=';
				iff isShaded then
					opName := '=';
				endif
				-- Make name(z)->size() <=
				let selExp: syntax::ast::expressions::DotSelectionExpAS;
				selExp := new syntax::ast::expressions::DotSelectionExpAS;
				selExp.source := callExp;
				selExp.name := opName;
				-- Make feetName exp
				let argExp: syntax::ast::expressions::IntegerLiteralExpAS;
				argExp := new syntax::ast::expressions::IntegerLiteralExpAS;
				argExp.value := feetNo;
				-- Make name(z)->size() <= feetNo
				let relCall: syntax::ast::expressions::OperationCallExpAS;
				relCall :=  new syntax::ast::expressions::OperationCallExpAS;
				relCall.source := selExp;
				relCall.arguments := relCall.arguments->including(argExp);
				--
				-- Add exp to ands
				--
				ands := ands->including(relCall);
			}
			-- Make a logical expression from ands
			iff ands->size() >= 1 then {
				let inExp: syntax::ast::expressions::OclExpressionAS;
				inExp := ands->at(1);
				let i:Integer = 2;
				while i<=ands->size() do {
					-- Make an and
					let andSel: syntax::ast::expressions::DotSelectionExpAS;
					andSel := new syntax::ast::expressions::DotSelectionExpAS;
					andSel.source := inExp;
					andSel.name := 'and';
					let andCall: syntax::ast::expressions::OperationCallExpAS;
					andCall := new syntax::ast::expressions::OperationCallExpAS;
					andCall.arguments := andCall.arguments->including(ands->at(i));
					-- Update inExp for next iteration
					inExp := andCall;
					-- Next 
					i := i+1;
				}
				-- Store mapping
				track(self, ud2in, inExp);
			}	
			endif
		}

		-- Link let expressions to variables
		rule linkLet2Variables match sd::as::UnitaryDiagram () {
			-- Get let expression
			let letExp: syntax::ast::expressions::LetExpAS;
			letExp := track(self, ud2let, null);
			-- For each zone
			foreach z: sd::as::Zone in self.zones do {
				let var:syntax::ast::contexts::VariableDeclarationAS;
				var := track(z, z2var, null);
				letExp.variables := letExp.variables->including(var);
			}
		}
				
		-- Link let expressions to variables
		rule linkLet2In match sd::as::UnitaryDiagram () {
			-- Get let expression
			let letExp: syntax::ast::expressions::LetExpAS;
			letExp := track(self, ud2let, null);
			-- Get in expression
			let inExp: syntax::ast::expressions::OclExpressionAS;
			inExp := track(self, ud2in, null);
			-- Link them
			letExp.inExp := inExp;
		}

		-- main rule
		rule main () {
			-- Create a let expression for each unitary diagram
			apply ud2let();
			-- Create a variable declaration for each zone
			apply z2var();
			-- Create the in expression
			apply ud2in();
			-- Link diagrams to variables
			apply linkLet2Variables();
			-- Link diagrams to in
			apply linkLet2In();
		}
	}
}
