/**
 * CustomerSBServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public class CustomerSBServiceLocator extends org.apache.axis.client.Service implements bean.stateless.CustomerSBService {

    public CustomerSBServiceLocator() {
    }


    public CustomerSBServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CustomerSBServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CustomerSBPort
    private java.lang.String CustomerSBPort_address = "http://localhost:8080/tbookstore-tbookstoreEJB/CustomerSB";

    public java.lang.String getCustomerSBPortAddress() {
        return CustomerSBPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CustomerSBPortWSDDServiceName = "CustomerSBPort";

    public java.lang.String getCustomerSBPortWSDDServiceName() {
        return CustomerSBPortWSDDServiceName;
    }

    public void setCustomerSBPortWSDDServiceName(java.lang.String name) {
        CustomerSBPortWSDDServiceName = name;
    }

    public bean.stateless.CustomerSB getCustomerSBPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CustomerSBPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCustomerSBPort(endpoint);
    }

    public bean.stateless.CustomerSB getCustomerSBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            bean.stateless.CustomerSBBindingStub _stub = new bean.stateless.CustomerSBBindingStub(portAddress, this);
            _stub.setPortName(getCustomerSBPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCustomerSBPortEndpointAddress(java.lang.String address) {
        CustomerSBPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (bean.stateless.CustomerSB.class.isAssignableFrom(serviceEndpointInterface)) {
                bean.stateless.CustomerSBBindingStub _stub = new bean.stateless.CustomerSBBindingStub(new java.net.URL(CustomerSBPort_address), this);
                _stub.setPortName(getCustomerSBPortWSDDServiceName());
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
        if ("CustomerSBPort".equals(inputPortName)) {
            return getCustomerSBPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://stateless.bean/", "CustomerSBService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://stateless.bean/", "CustomerSBPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CustomerSBPort".equals(portName)) {
            setCustomerSBPortEndpointAddress(address);
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
