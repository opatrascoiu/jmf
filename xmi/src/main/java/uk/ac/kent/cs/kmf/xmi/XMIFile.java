package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

public class XMIFile
	implements IXMIFile
{
    public XMIFile(String fileName) {
        encoding = "UTF-8";
        xmiVersion = "1.0";
        exporter = "Kent Modeling Framework [XMI]";
        exporterVersion = "1.0";
        printTimestamp = true;
        models = new Vector();
        metamodels = new Vector();
        metametamodels = new Vector();
        imports = new Vector();
        topObjects = new Vector();
        this.fileName = fileName;
    }

	//--- XMI ---
	public boolean hasHeader() {
		return hasDocumentation() || hasModels() || hasMetamodels() || hasMetametamodels() || hasImports();
	}
	public String getXMIVersion() {
    	return xmiVersion;
    }
    public void setXMIVersion(String xmiVersion) {
    	this.xmiVersion = xmiVersion;
    }
    public String getEncoding() {
    	return encoding;
    }
    public void setEncoding(String encoding) {
    	this.encoding = encoding;
    }
    public boolean getPrintTimestamp() {
    	return printTimestamp;
    }
    public void setPrintTimestamp(boolean printTimestamp) {
    	this.printTimestamp = printTimestamp;
    }
    public String getTimestamp() {
    	return timestamp;
    }
    public void setTimestamp(String timestamp) {
    	this.timestamp = timestamp;
    }
    public boolean getVerified() {
    	return verified;
    }
    public void setVerified(boolean verified) {
    	this.verified = verified;
    }

	//--- XMI.extensions ---
    public static class Extensions {
        public Extensions(String extender) {
            this.extender = extender;
        }
        public String getExtender() {
            return extender;
        }
        protected String extender;
    }
    public Extensions getExtensions() {
    	return extensions;
    }
    public void setExtensions(Extensions extensions) {
    	this.extensions = extensions;
    }

	//--- XMI.extension ---
    public static class Extension {
        public Extension(String id, String label, String uuid, String href, String idref, String extender, String extenderID) {
            this.id = id;
            this.label = label;
            this.uuid = uuid;
            this.href = href;
            this.idref = idref;
            this.extender = extender;
            this.extenderID = extenderID;
        }
        public String getID() {
            return id;
        }
        public String getLabel() {
            return label;
        }
        public String getHref() {
            return href;
        }
        public String getIDref() {
            return idref;
        }
        public String getExtender() {
            return extender;
        }
        public String getExtenderID() {
            return extenderID;
        }
        protected String id;
        protected String label;
        protected String uuid;
        protected String href;
        protected String idref;
        protected String extender;
        protected String extenderID;
    }
    public Extension getExtension() {
    	return extension;
    }
    public void setExtension(Extension extension) {
    	this.extension = extension;
    }

	//--- XMI.documentation ---
	public boolean hasDocumentation() {
		return  owner != null || contact != null || longDescription != null ||
				 shortDescription != null || exporter != null || exporterVersion != null ||
				 notice != null;
	}
    public String getOwner() {
    	return owner;
    }
    public void setOwner(String owner) {
    	this.owner = owner;
    }
    public String getContact() {
    	return contact;
    }
    public void setContact(String contact) {
    	this.contact = contact;
    }
    public String getLongDescription() {
    	return longDescription;
    }
    public void setLongDescription(String longDescription) {
    	this.longDescription = longDescription;
    }
    public String getShortDescription() {
    	return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
    	this.shortDescription = shortDescription;
    }
    public String getExporter() {
    	return exporter;
    }
    public void setExporter(String exporter) {
    	this.exporter = exporter;
    }
    public String getExporterVersion() {
    	return exporterVersion;
    }
    public void setExporterVersion(String exporterVersion) {
    	this.exporterVersion = exporterVersion;
    }
    public String getExporterID() {
    	return exporterID;
    }
    public void setExporterID(String exporterID) {
    	this.exporterID = exporterID;
    }
    public String getNotice() {
    	return notice;
    }
    public void setNotice(String notice) {
    	this.notice = notice;
    }

	//--- XMI.model ---
    public static class Model {
        public Model(String name, String version, String href, String idref) {
            this.name = name;
            this.version = version;
            this.href = href;
            this.idref = idref;
        }
        public String getName() {
            return name;
        }
        public String getVersion() {
            return version;
        }
        public String getHref() {
            return href;
        }
        public String getIDref() {
            return idref;
        }
        protected String name;
        protected String version;
        protected String href;
        protected String idref;
    }
    public boolean hasModels() {
    	return models.size() != 0;
    }
    public List getModels() {
    	return models;
    }
    public void addModel(Model model) {
    	this.models.add(model);
    }

	//--- XMI.metamodel ---
    public static class Metamodel extends Model {
        public Metamodel(String name, String version, String href, String idref) {
            super(name, version, href, idref);
        }
    }
    public boolean hasMetamodels() {
    	return metamodels.size() != 0;
    }
    public List getMetamodels() {
    	return metamodels;
    }
    public void addMetamodel(Metamodel metamodel) {
    	this.metamodels.add(metamodel);
    }

	//--- XMI.metametamodel ---
    public static class Metametamodel extends Model {
        public Metametamodel(String name, String version, String href, String idref) {
            super(name, version, href, idref);
        }
    }
    public boolean hasMetametamodels() {
    	return metametamodels.size() != 0;
    }
    public List getMetametamodels() {
    	return metametamodels;
    }
    public void addMetametamodel(Metametamodel metametamodel) {
    	this.metametamodels.add(metametamodel);
    }
   
	//--- XMI.import ---
    public static class Import {
        public Import(String name, String version, String href, String idref) {
            this.name = name;
            this.version = version;
            this.href = href;
            this.idref = idref;
        }
        public String getName() {
            return name;
        }
        public String getVersion() {
            return version;
        }
        public String getHref() {
            return href;
        }
        public String getIDref() {
            return idref;
        }
        protected String name;
        protected String version;
        protected String href;
        protected String idref;
    }
    public boolean hasImports() {
    	return imports.size() != 0;
    }
    public List getImports() {
    	return imports;
    }
    public void addImport(Import imp) {
    	this.imports.add(imp);
    }
    
	//--- XMI.difference ---
    public static class Difference {
        public Difference(String id, String label, String uuid, String href, String idref) {
            this.id = id;
            this.label = label;
            this.uuid = uuid;
            this.href = href;
            this.idref = idref;
        }
        public String getID() {
            return id;
        }
        public String getLabel() {
            return label;
        }
        public String getHref() {
            return href;
        }
        public String getIDref() {
            return idref;
        }
        protected String id;
        protected String label;
        protected String uuid;
        protected String href;
        protected String idref;
    }
    public Difference getDifference() {
    	return difference;
    }
    public void setDifference(Difference difference) {
    	this.difference = difference;
    }

	//--- XMI.delete ---
    public static class Delete {
        public Delete(String id, String label, String uuid, String href, String idref) {
            this.id = id;
            this.label = label;
            this.uuid = uuid;
            this.href = href;
            this.idref = idref;
        }
        public String getID() {
            return id;
        }
        public String getLabel() {
            return label;
        }
        public String getHref() {
            return href;
        }
        public String getIDref() {
            return idref;
        }
        protected String id;
        protected String label;
        protected String uuid;
        protected String href;
        protected String idref;
    }
    public Delete getDelete() {
    	return delete;
    }
    public void setDelete(Delete delete) {
    	this.delete = delete;
    }

	//--- XMI.add ---
    public static class Add {
        public Add(String id, String label, String uuid, String href, String idref, String position) {
            this.id = id;
            this.label = label;
            this.uuid = uuid;
            this.href = href;
            this.idref = idref;
            this.position = position;
        }
        public String getID() {
            return id;
        }
        public String getLabel() {
            return label;
        }
        public String getHref() {
            return href;
        }
        public String getIDref() {
            return idref;
        }
        public String getPosition() {
            return position;
        }
        protected String id;
        protected String label;
        protected String uuid;
        protected String href;
        protected String idref;
        protected String position;
    }
    public Add getAdd() {
    	return add;
    }
    public void setAdd(Add add) {
    	this.add = add;
    }

	//--- XMI.replace ---
    public static class Replace {
        public Replace(String id, String label, String uuid, String href, String idref, String position) {
            this.id = id;
            this.label = label;
            this.uuid = uuid;
            this.href = href;
            this.idref = idref;
            this.position = position;
        }
        public String getID() {
            return id;
        }
        public String getLabel() {
            return label;
        }
        public String getHref() {
            return href;
        }
        public String getIDref() {
            return idref;
        }
        public String getPosition() {
            return position;
        }
        protected String id;
        protected String label;
        protected String uuid;
        protected String href;
        protected String idref;
        protected String position;
    }
    public Replace getReplace() {
    	return replace;
    }
    public void setReplace(Replace replace) {
    	this.replace = replace;
    }

	//--- XMI.reference ---
    public static class Reference {
        public Reference(String id, String label, String uuid) {
            this.id = id;
            this.label = label;
            this.uuid = uuid;
        }
        public String getID() {
            return id;
        }
        public String getLabel() {
            return label;
        }
        public String getUUID() {
            return uuid;
        }
        protected String id;
        protected String label;
        protected String uuid;
    }
    public Reference getReference() {
    	return reference;
    }
    public void setReference(Reference reference) {
    	this.reference = reference;
    }

	/** Top objects */
    public Collection getTopObjects() {
    	return topObjects;
    }
    public void setTopObjects(Iterator iterator) {
        topObjects = new Vector();
        for(; iterator.hasNext(); topObjects.addElement(iterator.next()));
    }

    public void addTopObject(Object obj) {
        if(topObjects == null) topObjects = new Vector();
        topObjects.addElement(obj);
    }

	// Members
	//--- XMI ---
    protected String xmiVersion;
    protected String encoding;
	protected boolean printTimestamp;
	protected String timestamp;
	protected boolean verified;
	//--- XMI.extensions ---
	protected Extensions extensions;
	//--- XMI.extension ---
	protected Extension extension;
	//--- XMI.documentation ---
    protected String owner;
    protected String contact;
    protected String longDescription;
    protected String shortDescription;
	protected String exporter;
	protected String exporterVersion;
	protected String exporterID;
    protected String notice;
	//--- XMI.model ---
	protected List models;
	//--- XMI.metamodel ---
	protected List metamodels;
	//--- XMI.metametamodel ---
	protected List metametamodels;
	//--- XMI.import ---
	protected List imports;
	//--- XMI.difference ---
	protected Difference difference;
	//--- XMI.add ---
	protected Add add;
	//--- XMI.delete ---
	protected Delete delete;
	//--- XMI.replace] ---
	protected Replace replace;
	//--- XMI.reference ---
	protected Reference reference;
	
	//--- File name --- 
    protected String fileName;

	//--- Top objects --- 
    protected Vector topObjects;
}