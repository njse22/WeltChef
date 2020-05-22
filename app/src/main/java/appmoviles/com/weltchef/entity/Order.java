package appmoviles.com.weltchef.entity;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Order
 */
public class Order implements Serializable {
  public final static  int NONE = 0;
  public final static int WAITING = 1;
  public final static int ACCEPTED = 2;
  public final static int COMPLETED = 3;
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
  private int numPersonas;

  private String clientId;

  private String id;

  public Order() {
    this.plates = new ArrayList<>();
  }

  public Order(ArrayList<Menu> plates, String id, String clientId) {
    this.plates = plates;
    this.id = id;
    this.clientId = clientId;
    this.totalPrice = calculatePrice();
    this.numPersonas = 0;

  }

  public Order(int totalPrice, String clientId, String id) {
    this.totalPrice = totalPrice;
    this.clientId = clientId;
    this.id = id;
  }

  public Order(int totalPrice, int status, String id) {
    this.totalPrice = totalPrice;
    this.status = status;
    this.clientId = "";
    this.id = id;
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

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getId() {
    return id;
  }

  public int getNumPersonas() {
    return numPersonas;
  }

  public void setNumPersonas(int numPersonas) {
    this.numPersonas = numPersonas;
  }

  public int calculatePrice(){
    int price = 0;
    for (int i =0; i < plates.size(); i++) {
      price += plates.get(i).getPrice();
    }
    return price;
  }



}
