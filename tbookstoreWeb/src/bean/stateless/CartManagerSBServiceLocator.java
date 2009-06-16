/**
 * CartManagerSBServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public class CartManagerSBServiceLocator extends org.apache.axis.client.Service implements bean.stateless.CartManagerSBService {

    public CartManagerSBServiceLocator() {
    }


    public CartManagerSBServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CartManagerSBServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CartManagerSBPort
    private java.lang.String CartManagerSBPort_address = "http://localhost:8080/tbookstore-tbookstoreEJB/CartManagerSB";

    public java.lang.String getCartManagerSBPortAddress() {
        return CartManagerSBPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CartManagerSBPortWSDDServiceName = "CartManagerSBPort";

    public java.lang.String getCartManagerSBPortWSDDServiceName() {
        return CartManagerSBPortWSDDServiceName;
    }

    public void setCartManagerSBPortWSDDServiceName(java.lang.String name) {
        CartManagerSBPortWSDDServiceName = name;
    }

    public bean.stateless.CartManagerSB getCartManagerSBPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CartManagerSBPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCartManagerSBPort(endpoint);
    }

    public bean.stateless.CartManagerSB getCartManagerSBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            bean.stateless.CartManagerSBBindingStub _stub = new bean.stateless.CartManagerSBBindingStub(portAddress, this);
            _stub.setPortName(getCartManagerSBPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCartManagerSBPortEndpointAddress(java.lang.String address) {
        CartManagerSBPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (bean.stateless.CartManagerSB.class.isAssignableFrom(serviceEndpointInterface)) {
                bean.stateless.CartManagerSBBindingStub _stub = new bean.stateless.CartManagerSBBindingStub(new java.net.URL(CartManagerSBPort_address), this);
                _stub.setPortName(getCartManagerSBPortWSDDServiceName());
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
        if ("CartManagerSBPort".equals(inputPortName)) {
            return getCartManagerSBPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://stateless.bean/", "CartManagerSBService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://stateless.bean/", "CartManagerSBPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CartManagerSBPort".equals(portName)) {
            setCartManagerSBPortEndpointAddress(address);
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
