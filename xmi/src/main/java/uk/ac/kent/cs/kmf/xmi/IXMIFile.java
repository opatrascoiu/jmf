package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

public interface IXMIFile {
	//--- XMI header ---
	public boolean hasHeader();
    public String getXMIVersion();
    public void setXMIVersion(String xmiVersion);
    public String getEncoding();
    public void setEncoding(String encoding);
    public boolean getPrintTimestamp();
    public void setPrintTimestamp(boolean printTimestamp);
    public String getTimestamp();
    public void setTimestamp(String timestamp);
    public boolean getVerified();
    public void setVerified(boolean verified);
    public XMIFile.Extensions getExtensions();
    public void setExtensions(XMIFile.Extensions extensions);

	//--- XMI.extension ---
    public XMIFile.Extension getExtension();
    public void setExtension(XMIFile.Extension extension);

	//--- XMI.documentation ---
	public boolean hasDocumentation();
    public String getOwner();
    public void setOwner(String owner);
    public String getContact();
    public void setContact(String contact);
    public String getLongDescription();
    public void setLongDescription(String longDescription);
    public String getShortDescription();
    public void setShortDescription(String shortDescription);
    public String getExporter();
    public void setExporter(String exporter);
    public String getExporterVersion();
    public void setExporterVersion(String exporterVersion);
    public String getExporterID();
    public void setExporterID(String exporterID);
    public String getNotice();
    public void setNotice(String notice);

	//--- XMI.model ---
    public boolean hasModels();
    public List getModels();
    public void addModel(XMIFile.Model model);

	//--- XMI.metamodel ---
    public boolean hasMetamodels();
    public List getMetamodels();
    public void addMetamodel(XMIFile.Metamodel metamodel);

	//--- XMI.metametamodel ---
    public boolean hasMetametamodels();
    public List getMetametamodels();
    public void addMetametamodel(XMIFile.Metametamodel metametamodel);
   
	//--- XMI.import ---
    public boolean hasImports();
    public List getImports();
    public void addImport(XMIFile.Import imp);
    
	//--- XMI.difference ---
    public XMIFile.Difference getDifference();
    public void setDifference(XMIFile.Difference difference);

	//--- XMI.delete ---
    public XMIFile.Delete getDelete();
    public void setDelete(XMIFile.Delete delete);

	//--- XMI.add ---
    public XMIFile.Add getAdd();
    public void setAdd(XMIFile.Add add);

	//--- XMI.replace ---
    public XMIFile.Replace getReplace();
    public void setReplace(XMIFile.Replace replace);

	//--- XMI.reference ---
    public XMIFile.Reference getReference();
    public void setReference(XMIFile.Reference reference);

	//--- Top objects ---
    public Collection getTopObjects();
    public void setTopObjects(Iterator iterator);
    public void addTopObject(Object obj);
}
