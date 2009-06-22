/**
 * BookSB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public interface BookSB extends java.rmi.Remote {
    public bean.stateless.Book[] findAllBooks() throws java.rmi.RemoteException;
    public bean.stateless.Book getBook(java.lang.String arg0) throws java.rmi.RemoteException;
    public bean.stateless.Book insertBook(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, int arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6) throws java.rmi.RemoteException;
    public boolean removeBook(java.lang.String arg0) throws java.rmi.RemoteException;
    public bean.stateless.Book[] searchBook(java.lang.String[] arg0, java.lang.String[] arg1) throws java.rmi.RemoteException;
}
