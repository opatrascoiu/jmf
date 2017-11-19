start kmf::edoc2ws::main;

namespace kmf(sd, ocl) {
	transformation edoc2ws {
		--
		-- EDOC.ECA.DocumentModel to  WS.XSD  
		--
		-- Map an EDOC DataType to an XSD SimpleType
		rule dt2st match edoc::ECA::DocumentModel::DataType () {
			-- Create SimpleType
			let st: ws::xsd::SimpleType;
			st := new ws::xsd::SimpleType;
			st.name := self.name;
			-- Store mapping
			track(self, type2type, st);
		}
		-- Map an EDOC CompositeData to an XSD ComplexType
		rule cd2ct match edoc::ECA::DocumentModel::CompositeData () {
			-- Create ComplexType
			let ct: ws::xsd::ComplexType;
			ct := new ws::xsd::ComplexType;
			ct.name := self.name;
			-- Store mapping
			track(self, type2type, ct);
		}
		-- Map an EDOC Attribute to an XSD attribute
		rule at2at match edoc::ECA::DocumentModel::Attribute () {
			-- Create Attribute
			let at: ws::xsd::Attribute;
			at := new ws::xsd::Attribute;
			at.name := self.name;
			-- Store mapping
			track(self, at2at, at);
		}
		-- Link XSD attributes to XSD types
		rule linkAttribute2Type match edoc::ECA::DocumentModel::Attribute () {
			-- Get the XSD Attribute
			let xsdAttribute: ws::xsd::Attribute;
			xsdAttribute := track(self, at2at, null);
			-- Get the type
			let edocType : edoc::ECA::DocumentModel::DataElement;
			edocType := self.type;
			let xsdType: ws::xsd::Type;
			xsdType := track(edocType, type2type, null);
			xsdAttribute.type := xsdType;
		}
		-- Link XSD ComplexTypes to XSD Attributes
		rule linkComplexType2Attribute match edoc::ECA::DocumentModel::CompositeData () {
			-- Get the XSD ComplexType
			let xsdComplexType: ws::xsd::ComplexType;
			xsdComplexType := track(self, type2type, null);
			-- Add every attribute
			foreach edocAttribute : edoc::ECA::DocumentModel::Attribute in self.features do {
				let xsdAttribute : ws::xsd::Attribute;
				xsdAttribute := track(edocAttribute, at2at, null);
				xsdComplexType.sequence := xsdComplexType.sequence->including(xsdAttribute);
			}
		}
		-- Map concepts from EDOC.ECA.DocumentModel to WS.XSD concepts
		rule documentModel2xsd() {
			-- Create a SimpleType for each DataType
			apply dt2st();
			-- Create a ComplexType for each CompositeData
			apply cd2ct();
			-- Create an XSD Attribute for each EDOC Attribute
			apply at2at();
			-- Link XSD Attributes to XSD Types
			apply linkAttribute2Type();
			-- Link XSD ComplexTypes to XSD Attributes
			apply linkComplexType2Attribute();
		}

		--
		-- Map concepts from EDOC.ECA.CCA to WS:WSDL
		--
		-- Create a WSDL Message for each EDOC FlowPort
		rule flowPort2message match edoc::ECA::CCA::FlowPort () {
			-- Create Message
			let m: ws::wsdl::Message;
			m := new ws::wsdl::Message;
			m.name := self.name;
			-- Create part and add it
			let part: ws::wsdl::Part;
			part := new ws::wsdl::Part;
			part.name := self.name;
			part.type := track(self.type, type2type, null);
			m.parts := m.parts->including(part);
			-- Store mapping
			track(self, fp2m, m);
		}
		-- Create a WSDL Operation for each EDOC OperationPort
		rule operationPort2operation match edoc::ECA::CCA::OperationPort () {
			-- Get input and output port
			let iPort : edoc::ECA::CCA::OperationPort;
			iPort := self.ports->asSequence()->at(1);
			let oPort : edoc::ECA::CCA::OperationPort;
			oPort := self.ports->asSequence()->at(2);
			-- Create input
			let input: ws::wsdl::Input;
			input := new ws::wsdl::Input;
			input.name := iPort.name;
			input.message := track(iPort, fp2m, null);
			-- Create outpout
			let output: ws::wsdl::Output;
			output := new ws::wsdl::Output;
			output.name := oPort.name;
			output.message := track(oPort, fp2m, null);
			-- Create Operation
			let o: ws::wsdl::Operation;
			o := new ws::wsdl::Operation;
			o.name := self.name;
			o.input := input;
			o.output := output;
			input.operation := o;
			output.operation := o;
			-- Store mapping
			track(self, op2o, o);
		}
		-- Create a WSDL PortType for each EDOC ProtocolPort
		rule protocolPort2portType match edoc::ECA::CCA::ProtocolPort () {
			-- Create a portType
			let pt: ws::wsdl::PortType;
			pt := new ws::wsdl::PortType;
			pt.name := self.name;
			-- Add operations
			let ps: Set(edoc::ECA::CCA::Port) = self.owner.ports->asSet();
			let fps: Set(edoc::ECA::CCA::Port) = ps->select(e | e.oclIsKindOf(edoc::ECA::CCA::FlowPort));
			let ops: Set(edoc::ECA::CCA::Port) = ps->select(e | e.oclIsKindOf(edoc::ECA::CCA::OperationPort));
			foreach op: edoc::ECA::CCA::OperationPort in ops do {
				-- Find operation
				let o: ws::wsdl::Operation;
				o := track(op, op2o, null);
				pt.operations := pt.operations->including(o);
			} 
			-- Store mapping
			track(self, pp2pt, pt);
		}
		-- Create a WSDL Definition for each EDOC ProcessComponent
		rule processComponent2service match edoc::ECA::CCA::ProcessComponent () {
			-- Create Definition
			let d: ws::wsdl::Definition;
			d := new ws::wsdl::Definition;
			-- Create service
			let s: ws::wsdl::Service;
			s := new ws::wsdl::Service;
			s.definition := d;
			s.name := self.name;
			-- Store mapping
			track(self, pc2s, s);
		}
		-- Link Definition to Types
		rule linkDefinition2X match edoc::ECA::CCA::ProcessComponent () {
			-- Get the WSDL Service
			let s: ws::wsdl::Service;
			s := track(self, pc2s, null);
			let d : ws::wsdl::Definition;
			d := s.definition;
			-- Add every portType
			let ps : Set(edoc::ECA::CCA::Port) = self.ports->asSet();
			let fps: Set(edoc::ECA::CCA::Port) = ps->select(e | e.oclIsKindOf(edoc::ECA::CCA::FlowPort));
			let ops: Set(edoc::ECA::CCA::Port) = ps->select(e | e.oclIsKindOf(edoc::ECA::CCA::OperationPort));
			let pps: Set(edoc::ECA::CCA::Port) = ps->select(e | e.oclIsKindOf(edoc::ECA::CCA::ProtocolPort));
			let m: ws::wsdl::Message;
			let ms: Set(ws::wsdl::Message);
			let ts: Set(ws::xsd::Type);
			foreach fp : edoc::ECA::CCA::FlowPort in fps do {
				m := track(fp, fp2m, null);
				ms := ms->including(m);
				foreach p:ws::wsdl::Part in m.parts do {
					ts := ts->including(p.type);
				}
			}
			foreach op : edoc::ECA::CCA::OperationPort in ops do {
				-- Get input and output port
				let iPort : edoc::ECA::CCA::OperationPort;
				iPort := op.ports->asSequence()->at(1);
				let oPort : edoc::ECA::CCA::OperationPort;
				oPort := op.ports->asSequence()->at(2);
				m := track(iPort, fp2m, null);
				ms := ms->including(m);
				foreach p:ws::wsdl::Part in m.parts do {
					ts := ts->including(p.type);
				}
				m := track(oPort, fp2m, null);
				ms := ms->including(m);
				foreach p:ws::wsdl::Part in m.parts do {
					ts := ts->including(p.type);
				}
			}
			let pts : Set(ws::wsdl::PortType);
			foreach pp : edoc::ECA::CCA::ProtocolPort in pps do {
				let pt : ws::wsdl::PortType;
				pt := track(pp, pp2pt, null);
				pts := pts->including(pt);
			}
			d.messages := ms->asSequence();
			d.types := ts->asSequence();
			d.portTypes := pts->asSequence();
		}
		--- Map CCA to WSDL
		rule cca2wsdl() {
			-- Create a WSDL Message for each EDOC FlowPort
			apply flowPort2message();
			-- Map Operation Ports
			apply operationPort2operation();
			-- Map Protocol Ports
			apply protocolPort2portType();
			-- Map ProcessComponent
			apply processComponent2service();
			-- Link Definition to types, messages, and portTypes
			apply linkDefinition2X();
		}

		-- main rule
		rule main () {
			-- Map DocumentModel to XSD
			apply documentModel2xsd();
			-- ECA to WSLD
			apply cca2wsdl();
		}
	}
}
