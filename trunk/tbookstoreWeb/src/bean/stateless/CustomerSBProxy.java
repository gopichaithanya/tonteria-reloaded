package bean.stateless;

public class CustomerSBProxy implements bean.stateless.CustomerSB {
  private String _endpoint = null;
  private bean.stateless.CustomerSB customerSB = null;
  
  public CustomerSBProxy() {
    _initCustomerSBProxy();
  }
  
  public CustomerSBProxy(String endpoint) {
    _endpoint = endpoint;
    _initCustomerSBProxy();
  }
  
  private void _initCustomerSBProxy() {
    try {
      customerSB = (new bean.stateless.CustomerSBServiceLocator()).getCustomerSBPort();
      if (customerSB != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)customerSB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)customerSB)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (customerSB != null)
      ((javax.xml.rpc.Stub)customerSB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public bean.stateless.CustomerSB getCustomerSB() {
    if (customerSB == null)
      _initCustomerSBProxy();
    return customerSB;
  }
  
  public boolean createCustomer(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (customerSB == null)
      _initCustomerSBProxy();
    return customerSB.createCustomer(arg0, arg1, arg2, arg3);
  }
  
  public bean.stateless.Customer[] findAllCustomers() throws java.rmi.RemoteException{
    if (customerSB == null)
      _initCustomerSBProxy();
    return customerSB.findAllCustomers();
  }
  
  public bean.stateless.Customer login(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (customerSB == null)
      _initCustomerSBProxy();
    return customerSB.login(arg0, arg1);
  }
  
  public boolean removeCustomer(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (customerSB == null)
      _initCustomerSBProxy();
    return customerSB.removeCustomer(arg0, arg1);
  }
  
  public bean.stateless.Customer searchCustomer(java.lang.String arg0) throws java.rmi.RemoteException{
    if (customerSB == null)
      _initCustomerSBProxy();
    return customerSB.searchCustomer(arg0);
  }
  
  
}