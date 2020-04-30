package appmoviles.com.weltchef.entity;


import java.util.ArrayList;

/**
 * Class Client
 */
public class Client extends User {


  /**
   * el cliente tiene una lista de pedidos
   */
  private ArrayList<Order> orders;

  public Client() {
  }

  public Client(String name, String email, String phone, String password, String id) {
    super(name, email, phone, password, id, false);
    this.orders = new ArrayList<>();
  }

  /**
   * Set the value of orders
   * el cliente tiene una lista de pedidos
   * @param orders
   */
  public void setOrders(ArrayList<Order> orders) {
    this.orders = orders;
  }
  /**
   * Get the value of orders
   * el cliente tiene una lista de pedidos
   * @return the value of orders
   */
  public ArrayList<Order> getOrders() {
    return orders;
  }
}
