package appmoviles.com.weltchef.entity;


/**
 * Class Menu
 */
public class Menu {

  public final static int COLOMBIANA = 1;
  public final static int MEXICANA = 2;
  public final static int MEDITERRANEA = 3;
  public final static int CHINA = 4;
  public final static int JAPONESA = 5;
  public final static int FRANCESA = 6;
  public final static int ITALIANA = 7;

  /**
   * tipe of the plate or menu 
   *    */
  private int tipe;
  /**
   * price if the plate or menu 
   */
  private int price;
  /**
   * descripción del plato
   */
  private String description;

  private String chefId;


  public Menu(int tipe, int price, String description) {
    this.tipe = tipe;
    this.price = price;
    this.description = description;
    this.chefId = "";
  }

  /**
   * Set the value of tipe
   * tipe of the plate or menu
   * 
   * @param newVar the new value of tipe
   */
  public void setTipe (int newVar) {
    tipe = newVar;
  }

  /**
   * Get the value of tipe
   * tipe of the plate or menu
   * 
   * @return the value of tipe
   */
  public int getTipe () {
    return tipe;
  }

  /**
   * Set the value of price
   * price if the plate or menu
   * @param newVar the new value of price
   */
  public void setPrice (int newVar) {
    price = newVar;
  }

  /**
   * Get the value of price
   * price if the plate or menu
   * @return the value of price
   */
  public int getPrice () {
    return price;
  }

  /**
   * Set the value of description
   * descripción del plato
   * @param newVar the new value of description
   */
  public void setDescription (String newVar) {
    description = newVar;
  }

  /**
   * Get the value of description
   * descripción del plato
   * @return the value of description
   */
  public String getDescription () {
    return description;
  }




}
