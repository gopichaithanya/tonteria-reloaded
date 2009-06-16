/**
 * BookSBServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public class BookSBServiceLocator extends org.apache.axis.client.Service implements bean.stateless.BookSBService {

    public BookSBServiceLocator() {
    }


    public BookSBServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BookSBServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BookSBPort
    private java.lang.String BookSBPort_address = "http://localhost:8080/tbookstore-tbookstoreEJB/BookSB";

    public java.lang.String getBookSBPortAddress() {
        return BookSBPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BookSBPortWSDDServiceName = "BookSBPort";

    public java.lang.String getBookSBPortWSDDServiceName() {
        return BookSBPortWSDDServiceName;
    }

    public void setBookSBPortWSDDServiceName(java.lang.String name) {
        BookSBPortWSDDServiceName = name;
    }

    public bean.stateless.BookSB getBookSBPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BookSBPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBookSBPort(endpoint);
    }

    public bean.stateless.BookSB getBookSBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            bean.stateless.BookSBBindingStub _stub = new bean.stateless.BookSBBindingStub(portAddress, this);
            _stub.setPortName(getBookSBPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBookSBPortEndpointAddress(java.lang.String address) {
        BookSBPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (bean.stateless.BookSB.class.isAssignableFrom(serviceEndpointInterface)) {
                bean.stateless.BookSBBindingStub _stub = new bean.stateless.BookSBBindingStub(new java.net.URL(BookSBPort_address), this);
                _stub.setPortName(getBookSBPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BookSBPort".equals(inputPortName)) {
            return getBookSBPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://stateless.bean/", "BookSBService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://stateless.bean/", "BookSBPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BookSBPort".equals(portName)) {
            setBookSBPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
