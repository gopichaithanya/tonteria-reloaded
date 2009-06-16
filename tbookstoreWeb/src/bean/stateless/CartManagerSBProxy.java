package bean.stateless;

public class CartManagerSBProxy implements bean.stateless.CartManagerSB {
  private String _endpoint = null;
  private bean.stateless.CartManagerSB cartManagerSB = null;
  
  public CartManagerSBProxy() {
    _initCartManagerSBProxy();
  }
  
  public CartManagerSBProxy(String endpoint) {
    _endpoint = endpoint;
    _initCartManagerSBProxy();
  }
  
  private void _initCartManagerSBProxy() {
    try {
      cartManagerSB = (new bean.stateless.CartManagerSBServiceLocator()).getCartManagerSBPort();
      if (cartManagerSB != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cartManagerSB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cartManagerSB)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cartManagerSB != null)
      ((javax.xml.rpc.Stub)cartManagerSB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public bean.stateless.CartManagerSB getCartManagerSB() {
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    return cartManagerSB;
  }
  
  public void addBook(byte[] arg0, bean.stateless.Book arg1) throws java.rmi.RemoteException{
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    cartManagerSB.addBook(arg0, arg1);
  }
  
  public void closeSession(byte[] arg0) throws java.rmi.RemoteException{
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    cartManagerSB.closeSession(arg0);
  }
  
  public bean.stateless.LineItem[] getLineItemList(byte[] arg0) throws java.rmi.RemoteException{
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    return cartManagerSB.getLineItemList(arg0);
  }
  
  public java.lang.String getPaymentType(byte[] arg0) throws java.rmi.RemoteException{
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    return cartManagerSB.getPaymentType(arg0);
  }
  
  public byte[] openSession(bean.stateless.Customer arg0) throws java.rmi.RemoteException{
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    return cartManagerSB.openSession(arg0);
  }
  
  public void removeBookFromCart(byte[] arg0, bean.stateless.Book arg1) throws java.rmi.RemoteException{
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    cartManagerSB.removeBookFromCart(arg0, arg1);
  }
  
  public void shipOrder(byte[] arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (cartManagerSB == null)
      _initCartManagerSBProxy();
    cartManagerSB.shipOrder(arg0, arg1);
  }
  
  
}