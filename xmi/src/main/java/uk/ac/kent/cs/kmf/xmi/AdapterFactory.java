package uk.ac.kent.cs.kmf.xmi;

public class AdapterFactory
	implements IAdapterFactory
{
    public AdapterFactory(){
    }

    public ReaderAdapter createReaderAdapter() {
        return new ReaderAdapter();
    }

    public WriterAdapter createWriterAdapter() {
        return new WriterAdapter();
    }
}