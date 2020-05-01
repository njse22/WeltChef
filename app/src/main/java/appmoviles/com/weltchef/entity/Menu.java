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
  private int type;
  /**
   * price if the plate or menu 
   */
  private int price;
  /**
   * descripción del plato
   */
  private String description;

  private String chefId;

  private String name;

  private String id;

  public Menu(int type, int price, String description, String name, String id) {
    this.type = type;
    this.price = price;
    this.description = description;
    this.name = name;
    this.id = id;
    this.chefId = "";
  }

  /**
   * Set the value of tipe
   * tipe of the plate or menu
   * 
   * @param newVar the new value of tipe
   */
  public void setType(int newVar) {
    type = newVar;
  }

  /**
   * Get the value of tipe
   * tipe of the plate or menu
   * 
   * @return the value of tipe
   */
  public int getType() {
    return type;
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

  public void setChefId(String chefId) {
    this.chefId = chefId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getChefId() {
    return chefId;
  }

  public String getId() {
    return id;
  }
}
