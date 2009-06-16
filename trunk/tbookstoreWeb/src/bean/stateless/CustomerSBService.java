/**
 * CustomerSBService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public interface CustomerSBService extends javax.xml.rpc.Service {
    public java.lang.String getCustomerSBPortAddress();

    public bean.stateless.CustomerSB getCustomerSBPort() throws javax.xml.rpc.ServiceException;

    public bean.stateless.CustomerSB getCustomerSBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
