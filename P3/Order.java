/*
CSE 17
Amber Wallace
alw218
Program #3 DEADLINE: March 26, 2015
Program Description: Online Store
*/ 

/**Order This class represents an order by a customer for some number of units of a product. The product is only specified by its
serial number. Note, if a customer wishes to order different products, then they must create different Orders.*/
public class Order{
  private String customer; //The customer who placed the order.
  private int serialNum; //The serial number of the product ordered.
  private int qty; //The number of units of the product ordered.
  
  /**constructor that specifies name of the customer, serial #, and # of units of the product that the customer desires (qty).*/
  public Order(String customer, int serialNum, int qty){
    this.customer = customer;
    this.serialNum = serialNum;
    this.qty = qty;
  }
  
  /**Returns the name of the customer who placed the order.*/
  public String getCustomer(){
    return this.customer;
  }
  
  /**Returns the number of units of the product that are desired by the customer.*/
  public int getQty(){
    return this.qty;
  }
  
  /**Returns the serial number of the ordered product.*/
  public int getSerialNum(){
    return this.serialNum;
  }
}