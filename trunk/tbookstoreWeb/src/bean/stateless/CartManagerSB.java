/**
 * CartManagerSB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public interface CartManagerSB extends java.rmi.Remote {
    public void addBook(byte[] arg0, bean.stateless.Book arg1) throws java.rmi.RemoteException;
    public void closeSession(byte[] arg0) throws java.rmi.RemoteException;
    public bean.stateless.LineItem[] getLineItemList(byte[] arg0) throws java.rmi.RemoteException;
    public java.lang.String getPaymentType(byte[] arg0) throws java.rmi.RemoteException;
    public byte[] openSession(bean.stateless.Customer arg0) throws java.rmi.RemoteException;
    public void removeBookFromCart(byte[] arg0, bean.stateless.Book arg1) throws java.rmi.RemoteException;
    public void shipOrder(byte[] arg0, java.lang.String arg1) throws java.rmi.RemoteException;
}
