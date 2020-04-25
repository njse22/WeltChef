package appmoviles.com.weltchef.entity;


import java.util.ArrayList;

/**
 * Class Chef
 */
public class Chef extends User {

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

  private ArrayList<Menu> menus;

  public Chef() {
  }

  public Chef(boolean chefHome, boolean chefKitchen, String description, String email
          , String name, String password, String phone){
    super(name, email, phone, password, "");
    this.chefHome = chefHome;
    this.chefKitchen = chefKitchen;
    this.description = description;
    this.menus = new ArrayList<>();

  }

  public Chef(String name, String email, String phone, String password, String id,
              boolean chefHome, boolean chefKitchen, String description) {
    super(name, email, phone, password, id);
    this.chefHome = chefHome;
    this.chefKitchen = chefKitchen;
    this.description = description;
    this.menus = new ArrayList<>();

  }

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

  public boolean addMenu(int type, int price, String description){
    boolean typeB = false, priceB = false, descriptrionB = false;
    if(type >= 1 && type <= 7)
      typeB = true;
    if(price >= 0)
      priceB = true;
    if(description != null && description != " ")
      descriptrionB = true;

    Menu menu = new Menu(type, price, description);
    menus.add(menu);

    return (typeB & priceB) & descriptrionB;
  }

  public int identifyType(String type){
    int typeP = 0;
    if(type.equals("Colombiana"))
      typeP = Menu.COLOMBIANA;
    else if (type.equals("Mexicana"))
      typeP = Menu.MEXICANA;
    else if (type.equals("Mediterranea"))
      typeP = Menu.MEDITERRANEA;
    else if (type.equals("China"))
      typeP = Menu.CHINA;
    else if (type.equals("Japonesa"))
      typeP = Menu.JAPONESA;
    else if (type.equals("Francesa"))
      typeP = Menu.FRANCESA;
    else if (type.equals("Italiana"))
      typeP = Menu.ITALIANA;

    return typeP;
  }


}
