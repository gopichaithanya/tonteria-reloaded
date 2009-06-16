package bean.stateless;

public class BookSBProxy implements bean.stateless.BookSB {
  private String _endpoint = null;
  private bean.stateless.BookSB bookSB = null;
  
  public BookSBProxy() {
    _initBookSBProxy();
  }
  
  public BookSBProxy(String endpoint) {
    _endpoint = endpoint;
    _initBookSBProxy();
  }
  
  private void _initBookSBProxy() {
    try {
      bookSB = (new bean.stateless.BookSBServiceLocator()).getBookSBPort();
      if (bookSB != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bookSB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bookSB)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bookSB != null)
      ((javax.xml.rpc.Stub)bookSB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public bean.stateless.BookSB getBookSB() {
    if (bookSB == null)
      _initBookSBProxy();
    return bookSB;
  }
  
  public bean.stateless.Book[] findAllBooks() throws java.rmi.RemoteException{
    if (bookSB == null)
      _initBookSBProxy();
    return bookSB.findAllBooks();
  }
  
  public bean.stateless.Book insertBook(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, int arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6) throws java.rmi.RemoteException{
    if (bookSB == null)
      _initBookSBProxy();
    return bookSB.insertBook(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
  }
  
  public boolean removeBook(java.lang.String arg0) throws java.rmi.RemoteException{
    if (bookSB == null)
      _initBookSBProxy();
    return bookSB.removeBook(arg0);
  }
  
  
}