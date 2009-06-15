/**
 * BookSBService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public interface BookSBService extends javax.xml.rpc.Service {
    public java.lang.String getBookSBPortAddress();

    public bean.stateless.BookSB getBookSBPort() throws javax.xml.rpc.ServiceException;

    public bean.stateless.BookSB getBookSBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
