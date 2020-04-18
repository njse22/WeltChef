package appmoviles.com.weltchef.entity;


/**
 * Class Client
 */
public class Client extends User {


  /**
   * el cliente tiene una lista de pedidos
   */
  private Order orders;

  public Client() {
  }

  public Client(String name, String email, String phone, String password, String id,Order orders) {
    super(name, email, phone, password, id);
    this.orders = orders;
  }

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


}
