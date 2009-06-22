/**
 * LineItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bean.stateless;

public class LineItem  implements java.io.Serializable {
    private bean.stateless.Book book;

    private long quantity;

    public LineItem() {
    }

    public LineItem(
           bean.stateless.Book book,
           long quantity) {
           this.book = book;
           this.quantity = quantity;
    }


    /**
     * Gets the book value for this LineItem.
     * 
     * @return book
     */
    public bean.stateless.Book getBook() {
        return book;
    }


    /**
     * Sets the book value for this LineItem.
     * 
     * @param book
     */
    public void setBook(bean.stateless.Book book) {
        this.book = book;
    }


    /**
     * Gets the quantity value for this LineItem.
     * 
     * @return quantity
     */
    public long getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this LineItem.
     * 
     * @param quantity
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LineItem)) return false;
        LineItem other = (LineItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.book==null && other.getBook()==null) || 
             (this.book!=null &&
              this.book.equals(other.getBook()))) &&
            this.quantity == other.getQuantity();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBook() != null) {
            _hashCode += getBook().hashCode();
        }
        _hashCode += new Long(getQuantity()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LineItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stateless.bean/", "lineItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("book");
        elemField.setXmlName(new javax.xml.namespace.QName("", "book"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stateless.bean/", "book"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
