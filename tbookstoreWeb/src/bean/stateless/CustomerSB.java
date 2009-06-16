/**
 * CustomerSB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public interface CustomerSB extends java.rmi.Remote {
    public boolean createCustomer(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public bean.stateless.Customer[] findAllCustomers() throws java.rmi.RemoteException;
    public bean.stateless.Customer login(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public boolean removeCustomer(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public bean.stateless.Customer searchCustomer(java.lang.String arg0) throws java.rmi.RemoteException;
}
