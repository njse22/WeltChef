package appmoviles.com.weltchef.entity;


import java.util.ArrayList;

/**
 * Class Order
 */
public class Order {
  public final static  int NONE = 0;
  public final static int WAITING = 1;
  public final static int ACCEPTED = 2;
  private final static int COMPLETED = 3;
  public final static int CANCELED = 4;
  /**
   * HashMap de los platos que se han pedido 
   * llave : nombre del plato 
   * valor : cantidad de platos
   * */
  private ArrayList<Menu> plates;

  /**
   * Valor o costo total del pedido 
   */
  private int totalPrice;
  private int status;

  public Order( int totalPrice, int status) {
    this.totalPrice = totalPrice;
    this.status = status;
    plates = new ArrayList<Menu>();

  }

  /**
   * Set the value of plates
   * HashMap de los platos que se han pedido
   * llave : nombre del plato
   * valor : cantidad de platos
   * @param newVar the new value of plates
   */
  public void setPlates (ArrayList<Menu> newVar) {
    plates = newVar;
  }

  /**
   * Get the value of plates
   * HashMap de los platos que se han pedido
   * llave : nombre del plato
   * valor : cantidad de platos
   * @return the value of plates
   */
  public ArrayList<Menu> getPlates () {
    return plates;
  }

  /**
   * Set the value of totalPrice
   * Valor o costo total del pedido
   * @param newVar the new value of totalPrice
   */
  public void setTotalPrice (int newVar) {
    totalPrice = newVar;
  }

  /**
   * Get the value of totalPrice
   * Valor o costo total del pedido
   * @return the value of totalPrice
   */
  public int getTotalPrice () {
    return totalPrice;
  }

  /**
   * Set the value of status
   * @param newVar the new value of status
   */
  public void setStatus (int newVar) {
    status = newVar;
  }

  /**
   * Get the value of status
   * @return the value of status
   */
  public int getStatus () {
    return status;
  }



}
