package appmoviles.com.weltchef.entity;


/**
 * Class Menu
 */
public class Menu {

  //
  // Fields
  //

  /**
   * Representa el tipo de comida China
   */

  /**
   * Representa el tipo de comida colombiana
   */
  public int COLOMBIANA = 1;
  public int MEXICANA = 2;
  public int MEDITERRANEA = 3;
  public int CHINA = 4;
  public int JAPONESA = 5;
  private int FRANCESA = 6;
  public int ITALIANA = 7;

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


  public Menu () { };

  /**
   * Set the value of CHINA
   * Representa el tipo de comida China
   * @param newVar the new value of CHINA
   */
  public void setCHINA (int newVar) {
    CHINA = newVar;
  }

  /**
   * Get the value of CHINA
   * Representa el tipo de comida China
   * @return the value of CHINA
   */
  public int getCHINA () {
    return CHINA;
  }

  /**
   * Set the value of COLOMBIANA
   * Representa el tipo de comida colombiana
   * @param newVar the new value of COLOMBIANA
   */
  public void setCOLOMBIANA (int newVar) {
    COLOMBIANA = newVar;
  }

  /**
   * Get the value of COLOMBIANA
   * Representa el tipo de comida colombiana
   * @return the value of COLOMBIANA
   */
  public int getCOLOMBIANA () {
    return COLOMBIANA;
  }

  /**
   * Set the value of ITALIANA
   * Representa el tipo de comida Italiana
   * @param newVar the new value of ITALIANA
   */
  public void setITALIANA (int newVar) {
    ITALIANA = newVar;
  }

  /**
   * Get the value of ITALIANA
   * Representa el tipo de comida Italiana
   * @return the value of ITALIANA
   */
  public int getITALIANA () {
    return ITALIANA;
  }

  /**
   * Set the value of MEXICANA
   * @param newVar the new value of MEXICANA
   */
  public void setMEXICANA (int newVar) {
    MEXICANA = newVar;
  }

  /**
   * Get the value of MEXICANA
   * @return the value of MEXICANA
   */
  public int getMEXICANA () {
    return MEXICANA;
  }

  /**
   * Set the value of MEDITERRANEA
   * @param newVar the new value of MEDITERRANEA
   */
  public void setMEDITERRANEA (int newVar) {
    MEDITERRANEA = newVar;
  }

  /**
   * Get the value of MEDITERRANEA
   * @return the value of MEDITERRANEA
   */
  public int getMEDITERRANEA () {
    return MEDITERRANEA;
  }

  /**
   * Set the value of JAPONESA
   * @param newVar the new value of JAPONESA
   */
  public void setJAPONESA (int newVar) {
    JAPONESA = newVar;
  }

  /**
   * Get the value of JAPONESA
   * @return the value of JAPONESA
   */
  public int getJAPONESA () {
    return JAPONESA;
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

  /**
   * Set the value of FRANCESA
   * @param newVar the new value of FRANCESA
   */
  public void setFRANCESA (int newVar) {
    FRANCESA = newVar;
  }

  /**
   * Get the value of FRANCESA
   * @return the value of FRANCESA
   */
  public int getFRANCESA () {
    return FRANCESA;
  }

  //
  // Other methods
  //

}
