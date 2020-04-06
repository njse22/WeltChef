package appmoviles.com.weltchef.entity;


/**
 * Class Client
 */
public class Client extends User {

  //
  // Fields
  //

  /**
   * el cliente tiene una lista de pedidos
   */
  private Order orders;
  
  //
  // Constructors
  //
  public Client () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of orders
   * el cliente tiene una lista de pedidos
   * @param newVar the new value of orders
   */
  public void setOrders (Order newVar) {
    orders = newVar;
  }

  /**
   * Get the value of orders
   * el cliente tiene una lista de pedidos
   * @return the value of orders
   */
  public Order getOrders () {
    return orders;
  }

  //
  // Other methods
  //

}
