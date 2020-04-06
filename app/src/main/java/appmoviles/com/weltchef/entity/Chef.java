package appmoviles.com.weltchef.entity;


/**
 * Class Chef
 */
public class Chef extends User {

  //
  // Fields
  //
  /**

   * indica si el chef es un chef a domicilio 
   * true : si lo es 
   * false: si no lo es    */

  private boolean chefHome;
  /**
   * indica si el chef ofrece el servicio de clases de cocina 
   */
  private boolean chefKitchen;
  /**
   * Descripción o información del chef 
   */
  private String description;
  
  //
  // Constructors
  //
  public Chef () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of chefHome
   * indica si el chef es un chef a domicilio
   * true : si lo es
   * false: si no lo es
   * @param newVar the new value of chefHome
   */
  public void setChefHome (boolean newVar) {
    chefHome = newVar;
  }

  /**
   * Get the value of chefHome
   * indica si el chef es un chef a domicilio
   * true : si lo es
   * false: si no lo es
   * @return the value of chefHome
   */
  public boolean getChefHome () {
    return chefHome;
  }

  /**
   * Set the value of chefKitchen
   * indica si el chef ofrece el servicio de clases de cocina
   * @param newVar the new value of chefKitchen
   */
  public void setChefKitchen (boolean newVar) {
    chefKitchen = newVar;
  }

  /**
   * Get the value of chefKitchen
   * indica si el chef ofrece el servicio de clases de cocina
   * @return the value of chefKitchen
   */
  public boolean getChefKitchen () {
    return chefKitchen;
  }

  /**
   * Set the value of description
   * Descripción o información del chef
   * @param newVar the new value of description
   */
  public void setDescription (String newVar) {
    description = newVar;
  }

  /**
   * Get the value of description
   * Descripción o información del chef
   * @return the value of description
   */
  public String getDescription () {
    return description;
  }

  //
  // Other methods
  //

}
