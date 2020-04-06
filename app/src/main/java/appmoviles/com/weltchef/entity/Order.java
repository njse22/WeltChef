package appmoviles.com.weltchef.entity;


/**
 * Class Order
 */
public class Order {

  //
  // Fields
  //

  public int NONE = 0;
  public int WATING = 1;
  public int ACEPTED = 2;
  public int CANCELED = 4;  /**

   * HashMap de los platos que se han pedido 
   * llave : nombre del plato 
   * valor : cantidad de platos   */

  private Menu plates;
  /**
   * Valor o costo total del pedido 
   */
  private int totalPrice;
  private int status;
  private int COMPLETED = 3;
  
  //
  // Constructors
  //
  public Order () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of NONE
   * @param newVar the new value of NONE
   */
  public void setNONE (int newVar) {
    NONE = newVar;
  }

  /**
   * Get the value of NONE
   * @return the value of NONE
   */
  public int getNONE () {
    return NONE;
  }

  /**
   * Set the value of WATING
   * @param newVar the new value of WATING
   */
  public void setWATING (int newVar) {
    WATING = newVar;
  }

  /**
   * Get the value of WATING
   * @return the value of WATING
   */
  public int getWATING () {
    return WATING;
  }

  /**
   * Set the value of ACEPTED
   * @param newVar the new value of ACEPTED
   */
  public void setACEPTED (int newVar) {
    ACEPTED = newVar;
  }

  /**
   * Get the value of ACEPTED
   * @return the value of ACEPTED
   */
  public int getACEPTED () {
    return ACEPTED;
  }

  /**
   * Set the value of CANCELED
   * @param newVar the new value of CANCELED
   */
  public void setCANCELED (int newVar) {
    CANCELED = newVar;
  }

  /**
   * Get the value of CANCELED
   * @return the value of CANCELED
   */
  public int getCANCELED () {
    return CANCELED;
  }

  /**
   * Set the value of plates
   * HashMap de los platos que se han pedido
   * llave : nombre del plato
   * valor : cantidad de platos
   * @param newVar the new value of plates
   */
  public void setPlates (Menu newVar) {
    plates = newVar;
  }

  /**
   * Get the value of plates
   * HashMap de los platos que se han pedido
   * llave : nombre del plato
   * valor : cantidad de platos
   * @return the value of plates
   */
  public Menu getPlates () {
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

  /**
   * Set the value of COMPLETED
   * @param newVar the new value of COMPLETED
   */
  public void setCOMPLETED (int newVar) {
    COMPLETED = newVar;
  }

  /**
   * Get the value of COMPLETED
   * @return the value of COMPLETED
   */
  public int getCOMPLETED () {
    return COMPLETED;
  }

  //
  // Other methods
  //

}
