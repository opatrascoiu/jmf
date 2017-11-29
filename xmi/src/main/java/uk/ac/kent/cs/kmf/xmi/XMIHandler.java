 package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

import uk.ac.kent.cs.kmf.util.*;

import org.xml.sax.*;

public class XMIHandler
	implements ContentHandler, 
				ErrorHandler	
{
	/** Constructor */
    public XMIHandler(XMIFile xmiFile, ILog log) {
    	this.xmiFile = xmiFile;
    	this.log = log;
        readerAdapter = AdapterFactoryRegister.getAdapterFactory().createReaderAdapter();
        text = new Stack();
        objects = new Stack();
        tags = new Stack();
        types = new Stack();
    	idToObj = new HashMap();
        forwardFeaturesToSet = new Vector();
    	uriToPrefix = new HashMap();
        objectInfo = new ObjectInfo(xmiFile.getXMIVersion());
        featureInfo = new FeatureInfo();
    }

	/** SAX Callback function */
	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}
	public void startDocument() throws SAXException {
		inContent = false;
	}
	public void endDocument() throws SAXException {
		// Set values for forward references
        for(int i=0; i<forwardFeaturesToSet.size()-2; i+=3) {
        	Object obj = forwardFeaturesToSet.get(i);
        	Object propertyName = forwardFeaturesToSet.get(i+1);
        	String idref = (String)forwardFeaturesToSet.get(i+2);
        	Object propertyValue = idToObj.get(idref);
        	readerAdapter.resolveValue(obj, propertyName, propertyValue);
        }
    }
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// Add (uri, prefix) mapping
		uriToPrefix.put(uri, prefix);
	}
	public void endPrefixMapping(String prefix) throws SAXException {
		// Delete the prefix
		for(Iterator i=uriToPrefix.keySet().iterator(); i.hasNext();) {
			String uri = (String)i.next();
			String thisPrefix = (String)uriToPrefix.get(uri);
			if (prefix.equals(thisPrefix)) {
				uriToPrefix.remove(uri);
				break;
			}
		}
	}
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		// Add text
		text.push(new Vector());
		// Header
		if (!inContent) {
			startHeaderElement(namespaceURI, localName, qName, atts);
		// Content
		} else {
			startContentElement(namespaceURI, localName, qName, atts);		
        }
        // Add tag
        tags.push(qName);
	}
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		// Header
		if (!inContent) {
			endHeaderElement(namespaceURI, localName, qName);
		// Content
		} else {
			endContentElement(namespaceURI, localName, qName);
		}
        // Remove tag
        tags.pop();
        // Remove text
        text.pop();
    }
	public void characters(char ch[], int start, int length) throws SAXException {
		String s = new String(ch, start, length);
		if (s.replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "").length() != 0) {
			// Add text
        	((Vector)text.peek()).add(s);
		}    	
    }
	public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {
    }
	public void processingInstruction(String target, String data) throws SAXException {
    }
	public void skippedEntity(String name) throws SAXException {
    }
    public void warning(SAXParseException exception) throws SAXException {
    	String message = "Warning: "+exception.getSystemId()+" ("+exception.getLineNumber()+"): "+exception.getMessage();
    	if (log!=null) log.reportMessage(message);
    	else System.out.println(message);
    }
    public void error(SAXParseException exception) throws SAXException {
    	String message = "Error: "+exception.getSystemId()+" ("+exception.getLineNumber()+"): "+exception.getMessage();
    	if (log!=null) log.reportMessage(message);
    	else System.out.println(message);
    }
    public void fatalError(SAXParseException exception) throws SAXException {
    	String message = "Error: "+exception.getSystemId()+" ("+exception.getLineNumber()+"): "+exception.getMessage();
    	if (log!=null) log.reportMessage(message);
    	else System.out.println(message);
    }

	/** Process header */
	public void startHeaderElement(String namespaceURI, String localName, String qName, Attributes atts) {
		String name = localName;
    	if(name.equals("XMI")) {
    		xmiFile.setTimestamp(atts.getValue("timestamp"));
    		xmiFile.setXMIVersion(atts.getValue("xmi.version"));
    		xmiFile.setVerified(Boolean.valueOf(atts.getValue("xmi.verified")).booleanValue());
		// XMI.extensions
    	} else if(name.equals("XMI.extensions")) {
    		xmiFile.setExtensions(new XMIFile.Extensions(atts.getValue("XMI.extender")));
		// XMI.extension
    	} else if(name.equals("XMI.extension")) {
    		xmiFile.setExtension(new XMIFile.Extension(
    			atts.getValue("xmi.id"), atts.getValue("xmi.label"), atts.getValue("xmi.uuid"), 
    			atts.getValue("href"), atts.getValue("xmi.idref"), 
    			atts.getValue("xmi.extender"), atts.getValue("xmi.extenderID") 
    		));
		// XMI.documentation
    	} else if(name.equals("XMI.owner")) {
    	} else if(name.equals("XMI.contact")) {
    	} else if(name.equals("XMI.longDescription")) {
    	} else if(name.equals("XMI.shortDescription")) {
    	} else if(name.equals("XMI.exporter")) {
    	} else if(name.equals("XMI.exporterVersion")) {
    	} else if(name.equals("XMI.ExporterID")) {
    	} else if(name.equals("XMI.notice")) {
		// XMI.model
    	} else if(name.equals("XMI.model")) {
    		xmiFile.addModel(new XMIFile.Model(
    			atts.getValue("xmi.name"), atts.getValue("xmi.version"), atts.getValue("xmi.idref"), 
    			atts.getValue("href")
    		));
		// XMI.metamodel
    	} else if(name.equals("XMI.metamodel")) {
    		xmiFile.addMetamodel(new XMIFile.Metamodel(
    			atts.getValue("xmi.name"), atts.getValue("xmi.version"), atts.getValue("xmi.idref"), 
    			atts.getValue("href")
    		));
		// XMI.metametamodel
    	} else if(name.equals("XMI.metametamodel")) {
    		xmiFile.addMetametamodel(new XMIFile.Metametamodel(
    			atts.getValue("xmi.name"), atts.getValue("xmi.version"), atts.getValue("xmi.idref"), 
    			atts.getValue("href")
    		));
		// XMI.metametamodel
    	} else if(name.equals("XMI.metametamodel")) {
    		xmiFile.addMetametamodel(new XMIFile.Metametamodel(
    			atts.getValue("xmi.name"), atts.getValue("xmi.version"), atts.getValue("xmi.idref"), 
    			atts.getValue("href")
    		));
		// XMI.import
    	} else if(name.equals("XMI.import")) {
    		xmiFile.addImport(new XMIFile.Import(
    			atts.getValue("xmi.name"), atts.getValue("xmi.version"), atts.getValue("xmi.idref"), 
    			atts.getValue("href")
    		));
		// XMI.difference
    	} else if(name.equals("XMI.difference")) {
    		xmiFile.setDifference(new XMIFile.Difference(
    			atts.getValue("xmi.id"), atts.getValue("xmi.label"), atts.getValue("xmi.uuid"), 
    			atts.getValue("href"), atts.getValue("xmi.idref")
    		));
		// XMI.delete
    	} else if(name.equals("XMI.delete")) {
    		xmiFile.setDelete(new XMIFile.Delete(
    			atts.getValue("xmi.id"), atts.getValue("xmi.label"), atts.getValue("xmi.uuid"), 
    			atts.getValue("href"), atts.getValue("xmi.idref")
    		));
		// XMI.add
    	} else if(name.equals("XMI.add")) {
    		xmiFile.setAdd(new XMIFile.Add(
    			atts.getValue("xmi.id"), atts.getValue("xmi.label"), atts.getValue("xmi.uuid"), 
    			atts.getValue("href"), atts.getValue("xmi.idref"), atts.getValue("xmi.position")
    		));
		// XMI.replace
    	} else if(name.equals("XMI.add")) {
    		xmiFile.setReplace(new XMIFile.Replace(
    			atts.getValue("xmi.id"), atts.getValue("xmi.label"), atts.getValue("xmi.uuid"), 
    			atts.getValue("href"), atts.getValue("xmi.idref"), atts.getValue("xmi.position")
    		));
		// XMI.reference
    	} else if(name.equals("XMI.reference")) {
    		xmiFile.setReference(new XMIFile.Reference(
    			atts.getValue("xmi.id"), atts.getValue("xmi.label"), atts.getValue("xmi.uuid") 
    		));
		// XMI.reference
    	} else if(name.equals("XMI.add")) {
    		xmiFile.setAdd(new XMIFile.Add(
    			atts.getValue("xmi.id"), atts.getValue("xmi.label"), atts.getValue("xmi.uuid"), 
    			atts.getValue("href"), atts.getValue("xmi.idref"), atts.getValue("xmi.position")
    		));
		// XMI.content
    	} else if(name.equals("XMI.content")) {
        	inContent = true;
        	readerAdapter.setXMIFile(xmiFile);
    	}
	}
	public void endHeaderElement(String namespaceURI, String localName, String qName) {
		String name = localName;
		// XMI.documentation
    	if(name.equals("XMI.owner")) {
    		xmiFile.setOwner(text.toString());
    	} else if(name.equals("XMI.contact")) {
    		xmiFile.setOwner(text.toString());
    	} else if(name.equals("XMI.longDescription")) {
    		xmiFile.setLongDescription(text.toString());
    	} else if(name.equals("XMI.shortDescription")) {
    		xmiFile.setShortDescription(text.toString());
    	} else if(name.equals("XMI.exporter")) {
    		xmiFile.setExporter(text.toString());
    	} else if(name.equals("XMI.exporterVersion")) {
    		xmiFile.setExporterVersion(text.toString());
    	} else if(name.equals("XMI.ExporterID")) {
    		xmiFile.setExporterID(text.toString());
    	} else if(name.equals("XMI.notice")) {
    		xmiFile.setNotice(text.toString());
    	}
	}
		
	/** Process content */
	protected boolean isObject(Attributes atts, Stack tags) {
		if (((String)tags.peek()).equals("XMI.content")) return true;
		return atts.getValue("xmi.id") != null || atts.getValue("xmi.label") != null || atts.getValue("xmi.uuid") != null;
	}
	protected boolean isObjectReference(Attributes atts) {
		return atts.getValue("xmi.idref") != null;
	}
	protected boolean isPropertyName(Attributes atts) {
		return atts.getValue("xmi.value") != null;
	}
	protected boolean isPropertyValue(Attributes atts, Stack tags) {
		return !isObject(atts, tags) && !isObjectReference(atts) && !isPropertyName(atts);
	}
	protected boolean isProperty(Attributes atts, Stack tags) {
		return isPropertyName(atts) || isPropertyValue(atts, tags);
	}
	protected boolean topStackIsObject(Stack types) {
		return !types.isEmpty() && ((String)types.peek()).equals("Object");
	}
	protected boolean topStackIsObjectReference(Stack types) {
		return !types.isEmpty() && ((String)types.peek()).equals("ObjectReference");
	}
	protected boolean topStackIsPropertyName(Stack types) {
		return !types.isEmpty() && ((String)types.peek()).equals("PropertyName");
	}
	protected boolean topStackIsPropertyValue(Stack types) {
		return !types.isEmpty() && ((String)types.peek()).equals("PropertyValue");
	}
	protected boolean topStackIsProperty(Stack types) {
		return topStackIsPropertyName(types) || topStackIsPropertyValue(types);
	}
    private Object createFeature(FeatureInfo featureInfo, String idref) {
        Object obj = readerAdapter.createProperty(featureInfo);
        return obj;
    }
    protected Object createObject(ObjectInfo objectInfo) {
    	objectInfo.setXMIVersion(xmiFile.getXMIVersion());
        Object obj = readerAdapter.createObject(objectInfo);
        if(obj != null) {
        	// Add only the top level object into XMI file
            if (tags.isEmpty() || !tags.isEmpty() && tags.peek().equals("XMI.content")) 
            	xmiFile.addTopObject(obj);
            // Add 'id' to 'obj' mapping
            String key = objectInfo.getId();
            if(key != null) {
                idToObj.put(key, obj);
            }
        }
        return obj;
    }
    protected String[] getIdrefs(String idrefs) {
        List list = new Vector();
        for(StringTokenizer stringtokenizer = new StringTokenizer(idrefs); stringtokenizer.hasMoreTokens(); list.add(stringtokenizer.nextToken()));
        return (String[])list.toArray(new String[0]);
    }
    public void handleStartObject(String namespaceURI, String localName, String qName, Attributes atts) {
    	// Create objectInfo
        objectInfo.clear();
        objectInfo.setId(atts.getValue("xmi.id"));
        objectInfo.setUUID(atts.getValue("xmi.uuid"));
        objectInfo.setLabel(atts.getValue("xmi.label"));
        objectInfo.setIdref(atts.getValue("xmi.idref"));
        objectInfo.setHref(atts.getValue("xmi.href"));
        objectInfo.setXMIName(localName);
        objectInfo.setNamespace(new Namespace((String)uriToPrefix.get(namespaceURI), namespaceURI));
        objectInfo.setModels(xmiFile.getModels());
        // Create object
        Object obj = createObject(objectInfo);
        if(obj != null) {
            // Push object
            objects.push(obj);
            // Push type
    		types.push("Object");
            // Create properties stored in attributes 
	        for(int i= 0; i<atts.getLength(); i++) {
	            if (atts.getLocalName(i).equals("xmi.id"));
	            else if (atts.getLocalName(i).equals("xmi.uuid"));
	            else if (atts.getLocalName(i).equals("xmi.label"));
	            else if (atts.getLocalName(i).equals("xmi.uuid"));
	            else if (atts.getLocalName(i).equals("xmi.idref"));
	            else if (atts.getLocalName(i).equals("xmi.href"));
	            else {
	            	// Set FeatureInfo
		            featureInfo.clear();
		            featureInfo.setObject(obj);
		            featureInfo.setClassName(localName);
		            featureInfo.setXMIName(atts.getLocalName(i));
		            featureInfo.setNamespace(new Namespace((String)uriToPrefix.get(namespaceURI), namespaceURI));
        			featureInfo.setModels(xmiFile.getModels());
	            	// Set value and create
		            String attribType = atts.getType(i);
	            	if(attribType.equals("CDATA")) {
	                	featureInfo.setValue(atts.getValue(i));
	                	createFeature(featureInfo, null);
	            	} else if(attribType.equals("IDREF")) {
	                    createFeature(featureInfo, atts.getValue(i));
	            	} else if(attribType.equals("IDREFS")) {
	                    ObjectInfo objectinfo = new ObjectInfo(xmiFile.getXMIVersion());
	                    objectInfo.setIdref(atts.getValue(i));
	                    Object obj1 = createObject(objectInfo);
	                	String idrefs[] = getIdrefs(atts.getValue(i));
	                	for(int j=0; j<idrefs.length; j++) {
	                    	ObjectInfo objectInfo = new ObjectInfo(xmiFile.getXMIVersion());
	                    	objectInfo.setIdref(idrefs[j]);
	                    	Object obj2 = createObject(objectInfo);
	                    	featureInfo.setValue(obj2);
	                    	createFeature(featureInfo, idrefs[j]);
	                	}
	            	}
	        	}
	        }
    	} else {
            // Push object
            objects.push(obj);
            // Push type
    		types.push("Object");
        }
    }
    public void handleStartObjectReference(String namespaceURI, String localName, String qName, Attributes atts) {
		// Search reference or store to resolve latter
        featureInfo.clear();
        if (objects.size()!=0) 
        	featureInfo.setObject(objects.peek());
        else
        	featureInfo.setObject(null);        
        featureInfo.setXMIName((String)tags.peek());
        featureInfo.setNamespace(new Namespace((String)uriToPrefix.get(namespaceURI), namespaceURI));
		featureInfo.setModels(xmiFile.getModels());
    	String idref = atts.getValue("xmi.idref");
    	Object obj = idToObj.get(idref);
    	// Backward reference
    	if (obj != null) {
	        featureInfo.setValue(obj);
	        createFeature(featureInfo, idref);
    	// Forward reference
	    } else {
            forwardFeaturesToSet.add(featureInfo.getObject());
            forwardFeaturesToSet.add((String)tags.peek());
            forwardFeaturesToSet.add(idref);
    	}
    	// Push type
        types.push("ObjectReference");
    }
    public void handleStartProperty(String namespaceURI, String localName, String qName, Attributes atts) {
    	// xmi.value stuff
    	if (isPropertyName(atts)) {
    		// Push property
        	types.push("PropertyName");
        	// Set text
	    	String val = (String)atts.getValue("xmi.value");
	    	if (val != null) ((Vector)text.peek()).add(val);
    	// Property name
    	} else if (topStackIsObject(types) || topStackIsPropertyValue(types)) {
	    	// Push property
	        types.push("PropertyName");
	    // Property type
    	} else if (topStackIsPropertyName(types)) {
    		// Create objectInfo
	        objectInfo.clear();
	        objectInfo.setXMIName(localName);
	        objectInfo.setNamespace(new Namespace((String)uriToPrefix.get(namespaceURI), namespaceURI));
	        objectInfo.setModels(xmiFile.getModels());
	        // Create object
	        Object obj = createObject(objectInfo);
            // Push object
            objects.push(obj);
    		// Push property
        	types.push("PropertyValue");
    	} else {
    		// Push property
        	types.push("PropertyXValue");
    	}
    }
    public void startContentElement(String namespaceURI, String localName, String qName, Attributes atts) {
    	if (qName.equals("UML:Class")) {
    		String id = atts.getValue(0);
    		if (id.equals("'a703'")) {
    			int i = 4;
    		}
    	}
    	// Object
    	if (isObject(atts, tags)) {
    		handleStartObject(namespaceURI, localName, qName, atts);
    	// Reference
    	} else if (isObjectReference(atts)) {
    		handleStartObjectReference(namespaceURI, localName, qName, atts);
    	// Property
    	} else {
    		handleStartProperty(namespaceURI, localName, qName, atts);
    	}
    }
    public void endContentElement(String namespaceURI, String localName, String qName) {
    	// Object
    	if (topStackIsObject(types)) {
			// Set OBJECT values
            featureInfo.clear();
            String top = (String)tags.peek();
            tags.pop(); 
            featureInfo.setXMIName((String)tags.peek());
            tags.push(top);
            featureInfo.setNamespace(new Namespace((String)uriToPrefix.get(namespaceURI), namespaceURI));
			featureInfo.setModels(xmiFile.getModels());
			// Set value of property
			Object value = null;
            if (!objects.empty()) {
            	value = objects.peek();
            	featureInfo.setValue(value);
            	objects.pop();
            }
			// Set object
			Object obj = null;
            if (!objects.empty()) {
            	obj = objects.peek();
            	featureInfo.setObject(obj);
            }
            String className = "";
            if (obj != null) {
	            className = obj.getClass().getName();
    	        int index = className.indexOf("$");
        	    if (index != -1) className = className.substring(0, index);
            }
           	featureInfo.setClassName(className);
            if (obj != null)
            	createFeature(featureInfo, null);
    	// Reference
    	} else if (topStackIsObjectReference(types)) {    		
    	// Property
    	} else if (topStackIsProperty(types)) {
    		// End of Property Name
    		if(topStackIsPropertyName(types)) {
			    // Set all CDATA value: xmi.value='value' or <A>value</A>
	        	if (((Vector)text.peek()).size() != 0) {
		        	// Set feature attributes
		            featureInfo.clear();
		            featureInfo.setXMIName((String)tags.peek());
		            featureInfo.setNamespace(new Namespace((String)uriToPrefix.get(namespaceURI), namespaceURI));
					featureInfo.setModels(xmiFile.getModels());
					// Set object
					Object obj = null;
		            if (!objects.empty()) {
		            	obj = objects.peek();
		            	featureInfo.setObject(obj);
		            }
		            String className = "";
        		    if (obj != null) {
	            		className = obj.getClass().getName();
    	        		int index = className.indexOf("$");
        	    		if (index != -1) className = className.substring(0, index);
            		}
           			featureInfo.setClassName(className);
		            Vector values = (Vector)text.peek();
		            String strVal = new String();
					for(int i=0;i<values.size();i++) {
						strVal += values.elementAt(i).toString();
					}
	            	featureInfo.setValue(strVal);
					createFeature(featureInfo, null);
	        	}
    		// End of Property Value
	        } else {
				// Set OBJECT values
	            featureInfo.clear();
	            String top = (String)tags.peek();
	            tags.pop(); 
	            featureInfo.setXMIName((String)tags.peek());
	            tags.push(top);
	            featureInfo.setNamespace(new Namespace((String)uriToPrefix.get(namespaceURI), namespaceURI));
				featureInfo.setModels(xmiFile.getModels());
				// Set value of property
				Object value = null;
	            if (!objects.empty()) {
	            	value = objects.peek();
	            	featureInfo.setValue(value);
	            	objects.pop();
	            }
				// Set object
				Object obj = null;
	            if (!objects.empty()) {
	            	obj = objects.peek();
	            	featureInfo.setObject(obj);
	            }
	            String className = "";
    	        if (obj != null) {
	    	        className = obj.getClass().getName();
    	    	    int index = className.indexOf("$");
        	    	if (index != -1) className = className.substring(0, index);
            	}
           		featureInfo.setClassName(className);
	            if (obj != null)
	            	createFeature(featureInfo, null);
	        }
    	}
        if (!types.empty()) types.pop();
	}

	/** XMI content */ 
    protected XMIFile xmiFile;
	/** Log file */
    protected ILog log;
	/** Location information */
	protected Locator locator;
	/** Event appears in xmi header or content */
    protected boolean inContent;
	/** Current DCHAR area */
    protected Stack text;
   	/** Reader Adapter - builds the objects and sets the properties */
    protected ReaderAdapter readerAdapter;
    /** Stack of tags */
    protected Stack tags;
    /** Stack of types */
    protected Stack types;
   	/** XMI's objects */
    protected Stack objects;
    /** Pairs (id, obj) */
    protected Map idToObj;
    /** Forward features to set */
    protected List forwardFeaturesToSet;
	/** (uri, prefix) mappings */
	protected Map uriToPrefix;
    /** Object info */
    protected ObjectInfo objectInfo;
    /** Feature info */
    protected FeatureInfo featureInfo;
}