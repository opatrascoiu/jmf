package test;

import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.kmf.common.*;
import uk.ac.kent.cs.kmf.util.*;

import uk.ac.kent.cs.yatl.bridge4kmf.gui.*;

import sd.as.*;
import sd.repository.*;

import edoc.ECA.CCA.*;
import edoc.ECA.DocumentModel.*;
import edoc.repository.*;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class TestInterpreterFromFile {
	public static void main(String[] args) {
		Repository umlRep = initSDPopulation();
		Repository sdRep = initUMLPopulation();
		Repository edocRep = initEDOCPopulation();
		(new YatlStudio()).setVisible(true);
	}
		
	//
	// Init population
	//
	protected static Repository initUMLPopulation() {
		Pair pair = null;
		try {
			//--- Load the model ---
			pair = (new XMIToUMLLoader()).loadModel("src/test/scripts/java.xmi", new OutputStreamLog(System.out)); 
		} catch (Exception e) {
		}
		Repository rep = (Repository)pair.getSecond(); 
		rep.saveXMI("src/test/scripts/umlRep.xml");
		return rep;
	}
	protected static Repository initSDPopulation() {
		SdRepository rep = new SdRepository$Class();
		// Create contours
		Contour a = (Contour)rep.buildElement("sd.as.Contour");
		a.setName("a");	
		Contour b = (Contour)rep.buildElement("sd.as.Contour");
		b.setName("b");	
		Contour c = (Contour)rep.buildElement("sd.as.Contour");
		c.setName("c");	
		// Create zone (a | b)
		Zone z1 = (Zone)rep.buildElement("sd.as.Zone");
		z1.getContainingContours().add(a);
		z1.getExcludingContours().add(b);
		// Create zone (b | a)
		Zone z2 = (Zone)rep.buildElement("sd.as.Zone");
		z2.getContainingContours().add(b);
		z2.getExcludingContours().add(a);
		// Create zone (a, b |)
		Zone z3 = (Zone)rep.buildElement("sd.as.Zone");
		z3.getContainingContours().add(a);
		z3.getContainingContours().add(b);
		
		// Create diagram containing all the zones
		UnitaryDiagram ud1 = (UnitaryDiagram)rep.buildElement("sd.as.UnitaryDiagram");
		ud1.getZones().add(z1);
		ud1.getZones().add(z2);
		ud1.getZones().add(z3);
//		// Create diagram containing all the zones and shade one
//		UnitaryDiagram ud2 = (UnitaryDiagram)rep.buildElement("sd.as.UnitaryDiagram");
//		ud2.getZones().add(z1);
//		ud2.getZones().add(z2);
//		ud2.getZones().add(z3);
//		ud2.getShadedZones().add(z3);
		rep.saveXMI("src/test/scripts/sdRep.xml");
		return rep;
	}
	//
	// Create EDOC population
	//
	protected static DataType makeDataType(Repository rep, String type) {
		DataType dt = (DataType)rep.buildElement("edoc.ECA.DocumentModel.DataType");
		dt.setName(type);
		return dt;
	}
	protected static Attribute makeAttribute(Repository rep, String name, DataElement type) {
		Attribute at = (Attribute)rep.buildElement("edoc.ECA.DocumentModel.Attribute");
		at.setName(name);
		at.setType(type);
		return at;
	}
	protected static CompositeData makeCompositeType(Repository rep, String name, List dataElements) {
		CompositeData dt = (CompositeData)rep.buildElement("edoc.ECA.DocumentModel.CompositeData");
		dt.setName(name);
		dt.setFeatures(dataElements);
		return dt;
	}
	protected static Protocol makeProtocol(Repository rep, String name) {
		Protocol p = (Protocol)rep.buildElement("edoc.ECA.CCA.Protocol");
		p.setName(name);
		return p;
	}
	protected static FlowPort makeFlowPort(Repository rep, String name, DataElement type) {
		FlowPort fp = (FlowPort)rep.buildElement("edoc.ECA.CCA.FlowPort");
		fp.setName(name);
		fp.setType(type);
		return fp;
	}
	protected static ProtocolPort makeProtocolPort(Repository rep, String name) {
		ProtocolPort pp = (ProtocolPort)rep.buildElement("edoc.ECA.CCA.ProtocolPort");
		pp.setName(name);
		return pp;
	}
	protected static OperationPort makeOperationPort(Repository rep, String name, FlowPort call, FlowPort ret) {
		OperationPort op = (OperationPort)rep.buildElement("edoc.ECA.CCA.OperationPort");
		op.setName(name);
		op.getPorts().add(call);
		op.getPorts().add(ret);
		return op;
	}
	protected static Repository initEDOCPopulation() {
		EdocRepository rep = new EdocRepository$Class();
		// Create simple types
		DataType stringType = makeDataType(rep, "String");
		DataType integerType = makeDataType(rep, "Integer");
		DataType realType = makeDataType(rep, "Real");
		// Create attributes
		Attribute airlineName = makeAttribute(rep, "AirlineName", stringType);
		Attribute flightNo = makeAttribute(rep, "FlightNo", integerType);
		Attribute location = makeAttribute(rep, "Location", stringType);
		Attribute date = makeAttribute(rep, "Date", stringType);
		Attribute hotelName = makeAttribute(rep, "HotelName", stringType);
		Attribute address = makeAttribute(rep, "Address", stringType);
		Attribute companyName = makeAttribute(rep, "CompanyName", stringType);
		Attribute period = makeAttribute(rep, "Period", integerType);
		// Create composite types
		List locationInfo = new Vector();
		locationInfo.add(location); 
		locationInfo.add(date); 
		CompositeData locationType = makeCompositeType(rep, "Location", locationInfo);
		List flightInfo = new Vector();
		flightInfo.add(airlineName); 
		flightInfo.add(flightNo); 
		flightInfo.add(date);
		CompositeData flightType = makeCompositeType(rep, "Flight", flightInfo);
		List hotelInfo = new Vector();
		hotelInfo.add(hotelName); 
		hotelInfo.add(address); 
		hotelInfo.add(date);
		hotelInfo.add(period);
		CompositeData hotelType = makeCompositeType(rep, "Hotel", hotelInfo);
		List carInfo = new Vector();
		carInfo.add(companyName); 
		carInfo.add(address); 
		carInfo.add(date); 
		carInfo.add(period); 
		CompositeData carType = makeCompositeType(rep, "Car", carInfo);
		// Create BuySell protocol
		Protocol buySellProt = makeProtocol(rep, "BuySell");
		ProtocolPort buyPort = makeProtocolPort(rep, "Buy");
		buyPort.setDirection(DirectionType$Class.Initiates);
		buyPort.setOwner(buySellProt);
		buyPort.setUses(buySellProt);
		ProtocolPort sellPort = makeProtocolPort(rep, "Sell");
		sellPort.setDirection(DirectionType$Class.Responds);
		sellPort.setOwner(buySellProt);
		sellPort.setUses(buySellProt);
		buySellProt.getPorts().add(buyPort);
		buySellProt.getPorts().add(sellPort);
		// Create BuyFlight protocol
		Protocol buyFlightProt = makeProtocol(rep, "BuyFlight");
		ProtocolPort buyFlightPort = makeProtocolPort(rep, "BuyFlight");
		buyFlightPort.setDirection(DirectionType$Class.Initiates);
		buyFlightPort.setOwner(buyFlightProt);
		buyFlightPort.setUses(buyFlightProt);
		ProtocolPort flightPort = makeProtocolPort(rep, "Flight");
		flightPort.setDirection(DirectionType$Class.Responds);
		flightPort.setOwner(buyFlightProt);
		flightPort.setUses(buyFlightProt);
		buyFlightProt.getPorts().add(buyFlightPort);
		buyFlightProt.getPorts().add(flightPort);
		// Add operation protocols
		FlowPort locationPort = makeFlowPort(rep, "Location", locationType);
		locationPort.setDirection(DirectionType$Class.Initiates);
		FlowPort flightFlowPort = makeFlowPort(rep, "FlightInfo", flightType); 
		flightFlowPort.setDirection(DirectionType$Class.Responds);
		OperationPort findFlightPort = makeOperationPort(rep, "FindFlight", locationPort, flightFlowPort);
		buyFlightProt.getPorts().add(findFlightPort);
		// Create reserveRoom protocol
		Protocol reserveRoomProt = makeProtocol(rep, "ReserveRoom");
		ProtocolPort reserveRoomPort = makeProtocolPort(rep, "ReserveRoom");
		reserveRoomPort.setDirection(DirectionType$Class.Initiates);
		reserveRoomPort.setOwner(reserveRoomProt);
		reserveRoomPort.setUses(reserveRoomProt);
		ProtocolPort roomPort = makeProtocolPort(rep, "Room");
		roomPort.setDirection(DirectionType$Class.Responds);
		roomPort.setOwner(reserveRoomProt);
		roomPort.setUses(reserveRoomProt);
		reserveRoomProt.getPorts().add(reserveRoomPort);
		reserveRoomProt.getPorts().add(roomPort);
		// Create rentCar protocol
		Protocol rentCarProt = makeProtocol(rep, "RentCar");
		ProtocolPort rentCarPort = makeProtocolPort(rep, "RentCar");
		rentCarPort.setDirection(DirectionType$Class.Initiates);
		rentCarPort.setOwner(rentCarProt);
		rentCarPort.setUses(rentCarProt);
		ProtocolPort carPort = makeProtocolPort(rep, "Car");
		carPort.setDirection(DirectionType$Class.Responds);
		carPort.setOwner(rentCarProt);
		carPort.setUses(rentCarProt);
		rentCarProt.getPorts().add(rentCarPort);
		rentCarProt.getPorts().add(carPort);
		// Create payment protocol
		Protocol paymentProt = makeProtocol(rep, "Payment");
		ProtocolPort taPaymentPort = makeProtocolPort(rep, "TAPayment");
		taPaymentPort.setDirection(DirectionType$Class.Initiates);
		taPaymentPort.setOwner(paymentProt);
		taPaymentPort.setUses(paymentProt);
		ProtocolPort bPaymentPort = makeProtocolPort(rep, "BPayment");
		bPaymentPort.setDirection(DirectionType$Class.Responds);
		bPaymentPort.setOwner(paymentProt);
		bPaymentPort.setUses(paymentProt);
		paymentProt.getPorts().add(taPaymentPort);
		paymentProt.getPorts().add(bPaymentPort);
		// Create ShipDelivery protocol
		Protocol shipDeliveryProt = makeProtocol(rep, "ShipDelivery");
		ProtocolPort shipPort = makeProtocolPort(rep, "Ship");
		shipPort.setDirection(DirectionType$Class.Initiates);
		shipPort.setOwner(shipDeliveryProt);
		shipPort.setUses(shipDeliveryProt);
		ProtocolPort deliveryPort = makeProtocolPort(rep, "Delivery");
		deliveryPort.setDirection(DirectionType$Class.Responds);
		deliveryPort.setOwner(shipDeliveryProt);
		deliveryPort.setUses(shipDeliveryProt);
		shipDeliveryProt.getPorts().add(shipPort);
		shipDeliveryProt.getPorts().add(deliveryPort);
		// Create Client
		ProcessComponent client = (ProcessComponent)rep.buildElement("edoc.ECA.CCA.ProcessComponent");
		client.setName("Client");
		client.getPorts().add(buyPort);
		client.getPorts().add(deliveryPort);
		buyPort.setOwner(client);
		deliveryPort.setOwner(client);
		// Create Travel Agency
		ProcessComponent travelAgency = (ProcessComponent)rep.buildElement("edoc.ECA.CCA.ProcessComponent");
		travelAgency.setName("Expedia");
		travelAgency.getPorts().add(sellPort);
		travelAgency.getPorts().add(buyFlightPort);		
		travelAgency.getPorts().add(findFlightPort);		
		travelAgency.getPorts().add(reserveRoomPort);		
		travelAgency.getPorts().add(rentCarPort);
		travelAgency.getPorts().add(taPaymentPort);
		sellPort.setOwner(travelAgency);
		buyFlightPort.setOwner(travelAgency);
		reserveRoomPort.setOwner(travelAgency);
		rentCarPort.setOwner(travelAgency);
		taPaymentPort.setOwner(travelAgency);		
		// Create Airline
		ProcessComponent airline = (ProcessComponent)rep.buildElement("edoc.ECA.CCA.ProcessComponent");
		airline.setName("BA");
		airline.getPorts().add(flightPort);
		// Create Hotel
		ProcessComponent hotel = (ProcessComponent)rep.buildElement("edoc.ECA.CCA.ProcessComponent");
		hotel.setName("Marriot");
		hotel.getPorts().add(roomPort);
		// Create CarCompany
		ProcessComponent carCompany = (ProcessComponent)rep.buildElement("edoc.ECA.CCA.ProcessComponent");
		carCompany.setName("CarCompany");
		carCompany.getPorts().add(carPort);
		// Save repository into an xml	
		rep.saveXMI("src/test/scripts/edocRep.xml");
		return rep;
	}
	protected static Repository initEDOCPopulation1() {
		EdocRepository rep = new EdocRepository$Class();
		// Create simple types
		DataType stringType = makeDataType(rep, "String");
		DataType integerType = makeDataType(rep, "Integer");
		DataType realType = makeDataType(rep, "Real");
		// Create attributes
		Attribute productName = makeAttribute(rep, "ProductName", stringType);
		Attribute productRef = makeAttribute(rep, "ProductReference", stringType);
		Attribute supplierName =  makeAttribute(rep, "Stock", realType);
		Attribute customerName = makeAttribute(rep, "CustomerName", stringType);
		Attribute customerRef = makeAttribute(rep, "CustomerReference", stringType);
		// Create composite types
		List productInfo = new Vector();
		productInfo.add(productName); 
		productInfo.add(productRef); 
		CompositeData productType = makeCompositeType(rep, "Product", productInfo);
		List customerInfo = new Vector();
		customerInfo.add(customerName); 
		customerInfo.add(customerRef); 
		CompositeData customerType = makeCompositeType(rep, "Customer", customerInfo);
		// Create ports
		FlowPort sPort = makeFlowPort(rep, "StringPort", stringType);
		FlowPort iPort = makeFlowPort(rep, "IntegerPort", stringType);
		FlowPort callPort = makeFlowPort(rep, "ProductPort", productType);
		callPort.setDirection(DirectionType$Class.Initiates);
		FlowPort retPort = makeFlowPort(rep, "StockValuePort", integerType);
		OperationPort oPort = makeOperationPort(rep, "StockQueryPort", callPort, retPort);		
		// Create process components
		ProcessComponent pc1 = (ProcessComponent)rep.buildElement("edoc.ECA.CCA.ProcessComponent");
		pc1.setIsPersistent(new Boolean(true));
		pc1.getPorts().add(oPort);
		// Save repository into an xml	
		rep.saveXMI("src/test/scripts/edocRep.xml");
		return rep;
	}
}

class Temp {
//	public static void main(String[] args) {
//		Repository umlRep = initSDPopulation();
//		Repository sdRep = initUMLPopulation();
//		Repository edocRep = initEDOCPopulation();
//		(new YatlStudio()).setVisible(true);
//		
////		UML2Java();
////		SD2OCL();
////		SD2OCL1();
////		SD2OCL2();
////		SD2OCL3();
//	}
//	protected static void SD2OCL3() {
//		// Source model
//		String sourceModel = "C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/sd.xmi";
//		String targetModel = "C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/ocl.xmi";
//		String transFileName = "C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/sd2ocl.tl";
//		
//		// Initialize the log
//		ILog log = new OutputStreamLog(System.out);
//		
//		// Initialize the warehouse
//		Warehouse warehouse = new WarehouseImpl();
//		warehouse.registerRepository(sourceModel, new SdRepository$Class());
//		warehouse.registerRepository(targetModel, new SyntaxRepository$Class());
//
//		// Create an OclProcessor, add models, and set storage area in adapter
//		KmfOclProcessorImpl oclProcessor = new KmfOclProcessorImpl(log);
//		oclProcessor.setBridgeFactory(new KmfBridgeFactoryImpl(oclProcessor));		
//		oclProcessor.loadModel(sourceModel, log);
//		oclProcessor.loadModel(targetModel, log);
//		((KmfImplementationAdapterImpl)oclProcessor.getModelImplAdapter()).setWarehouse(warehouse);
//
//		// Create a KtlProcessor
//		YatlProcessor yatlProcessor = new Yatl4KmfProcessorImpl(oclProcessor, log);
//
//		(new YatlStudio()).setVisible(true);
//	}
//
//	protected static void SD2OCL2() {
//		// Source model
//		String sourceModel = "C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/sd.xmi";
//		String targetModel = "C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/ocl.xmi";
//		String transFileName = "C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/sd2ocl.tl";
//		
//		// Initialize the warehouse
//		Warehouse warehouse = new WarehouseImpl();
//		warehouse.registerRepository(sourceModel, new SdRepository$Class());
//		warehouse.registerRepository(targetModel, new SyntaxRepository$Class());
//
//		// Initialize the log
//		ILog log = new OutputStreamLog(System.out);
//
//		// Create and load project
//		Project project = new Project("C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/sd2ocl.ysp", warehouse, log);
//		project.setSourceModelFileName(sourceModel);
//		project.setTargetModelFileName(targetModel);
//		project.setTransfFileName(transFileName);
//		project.setLog(log);
//		// Load project
//		project.loadProject();		
//
//		// Create an OclProcessor, add models, and set storage area in adapter
//		KmfOclProcessorImpl oclProcessor = new KmfOclProcessorImpl(log);
//		oclProcessor.setBridgeFactory(new KmfBridgeFactoryImpl(oclProcessor));		
//		oclProcessor.addModel(project.getSourceModel());
//		oclProcessor.addModel(project.getTargetModel());
//		((KmfImplementationAdapterImpl)oclProcessor.getModelImplAdapter()).setWarehouse(warehouse);
//
//		// Create a KtlProcessor
//		YatlProcessor yatlProcessor = new Yatl4KmfProcessorImpl(oclProcessor, log);
//
//		// Init the population
//		Repository sourceRep = initSDPopulation();
//		Repository targetRep = new SyntaxRepository$Class();
//		project.setSourceRepository(sourceRep);
//		project.setTargetRepository(targetRep);
//
////		(new YatlStudio(project, yatlProcessor)).setVisible(true);
//		(new YatlStudio(null)).setVisible(true);
//	}
//
//	protected static void SD2OCL1() {
//		// Source model
//		String sourceModel = "src/test/scripts/sd.xmi";
//		String sourceModelName = "sd";
//		// Target model
//		String targetModel = "src/test/scripts/ocl.xmi";
//		String targetModelName = "syntax";
//		
//		// Initialize the log
//		ILog log = new OutputStreamLog(System.out);
//		// Create an OclProcessor
//		KmfOclProcessorImpl oclProcessor = new KmfOclProcessorImpl(log);
//		oclProcessor.setBridgeFactory(new KmfBridgeFactoryImpl(oclProcessor));		
//		// Add models
//		Pair sourcePair = oclProcessor.loadModel(sourceModel, log);
//		Pair targetPair = oclProcessor.loadModel(targetModel, log);
//		// Create a KtlProcessor
//		YatlProcessor yatlProcessor = new Yatl4KmfProcessorImpl(oclProcessor, log);
//
//		// Init the population
//		Repository sourceRep = initSDPopulation();
//
//		// Set the common storage
//		Repository targetRep = new SyntaxRepository$Class();		
//		Warehouse storage = new WarehouseImpl(log);
//		storage.registerRepository(sourceModelName, sourceRep);
//		storage.registerRepository(targetModelName, targetRep);
//
//		// Set storage area
//		((KmfImplementationAdapterImpl)oclProcessor.getModelImplAdapter()).setWarehouse(storage);
//
////		(new YatlStudio(storage, sourceModelName, targetModelName, "src/test/scripts/sd2ocl.tl", yatlProcessor)).setVisible(true);
//	}
//
//	protected void setLookAndFeel() {
//		try {
////			UIManager.setLookAndFeel(new com.incors.plaf.alloy.AlloyLookAndFeel());
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
////			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
////			UIManager.setLookAndFeel(new com.incors.plaf.alloy.AlloyLookAndFeel());
//		} catch (Exception e) {
//		}
//	}
//	protected static void UML2Java() {
//		// Initialize the log
//		ILog log = new OutputStreamLog(System.out);
//		// Create an OclProcessor
//		KmfOclProcessorImpl oclProcessor = new KmfOclProcessorImpl(log);
//		oclProcessor.setBridgeFactory(new KmfBridgeFactoryImpl(oclProcessor));		
//		// Add models
//		Pair umlPair = oclProcessor.loadModel("src/test/scripts/UMLModel.xmi", log);
//		Pair javaPair = oclProcessor.loadModel("src/test/scripts/java.xmi", log);
//		// Create a KtlProcessor
//		YatlProcessor ktlProcessor = new Yatl4KmfProcessorImpl(oclProcessor, log);
//
//		// Set the common storage
//		Repository umlRep = (UmlRepository)umlPair.getSecond();
//		umlRep.saveXMI("src/test/scripts/UMLRep");
//		Repository javaRep = new JavaModelRepository$Class();		
//		Warehouse storage = new WarehouseImpl(log);
//		storage.registerRepository("uml", umlRep);
//		storage.registerRepository("java_", javaRep);
//
//		// Set storage area
//		((KmfImplementationAdapterImpl)oclProcessor.getModelImplAdapter()).setWarehouse(storage);
//		
//		try {
//			// Evaluate the input
//			FileReader input = new FileReader("src/test/scripts/uml2java.tl");
//			ktlProcessor.interpret(input, log);
//
//			//--- View the results ---
//			UmlBrowser$Class umlBrow = new UmlBrowser$Class("src/test/scripts/UMLModel.xmi");
//			umlBrow.setRep((UmlRepository)storage.getRepository("uml"));
//			JavaModelBrowser$Class javaBrow = new JavaModelBrowser$Class("src/test/scripts/java.xmi");
//			javaBrow.setRep((JavaModelRepository)storage.getRepository("java_"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	protected static void SD2OCL() {
//		// Source model
//		String sdModel = "src/test/scripts/sd.xmi";
//		String sdModelName = "sd";
//		// Target model
//		String oclModel = "src/test/scripts/ocl.xmi";
//		String oclModelName = "syntax";
//		
//		// Initialize the log
//		ILog log = new OutputStreamLog(System.out);
//		// Create an OclProcessor
//		KmfOclProcessorImpl oclProcessor = new KmfOclProcessorImpl(log);
//		oclProcessor.setBridgeFactory(new KmfBridgeFactoryImpl(oclProcessor));		
//		// Add models
//		Pair sdPair = oclProcessor.loadModel(sdModel, log);
//		Pair oclPair = oclProcessor.loadModel(oclModel, log);
//		// Create a KtlProcessor
//		YatlProcessor ktlProcessor = new Yatl4KmfProcessorImpl(oclProcessor, log);
//
//		// Init the population
//		Repository sdRep = initSDPopulation();
//
//		// Set the common storage
//		Repository oclRep = new SyntaxRepository$Class();		
//		Warehouse storage = new WarehouseImpl(log);
//		storage.registerRepository(sdModelName, sdRep);
//		storage.registerRepository(oclModelName, oclRep);
//
//		// Set storage area
//		((KmfImplementationAdapterImpl)oclProcessor.getModelImplAdapter()).setWarehouse(storage);
//		
//		try {
//			// Evaluate the input
//			FileReader input = new FileReader("src/test/scripts/sd2ocl.tl");
//			ktlProcessor.interpret(input, log);
//
//			//--- View the results ---
//			SdBrowser$Class sdBrow = new SdBrowser$Class(sdModel);
//			sdBrow.setRep((SdRepository)storage.getRepository(sdModelName));
//			SyntaxBrowser$Class oclBrow = new SyntaxBrowser$Class(oclModel);
//			oclBrow.setRep((SyntaxRepository)storage.getRepository(oclModelName));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}	
}
