package uk.ac.kent.cs.kmf.xmi;

public class AdapterFactoryRegister
{
    private AdapterFactoryRegister() {
    	factory = new AdapterFactory();
    }

    public static AdapterFactory getAdapterFactory() {
        return factory;
    }

    public static void registerAdapterFactory(AdapterFactory adapterFactory) {
        factory = adapterFactory;
    }

    private static AdapterFactory factory = null;
}