package appmoviles.com.weltchef.entity;


import java.util.ArrayList;

/**
 * Class User
 */
public class User {


  /**
   * Nombre de la persona
   * */
  private String name;

  /**
   * correo electronico de la persona 
   * */
  private String email;

  /**
   * telefono de la persona
   * */
  private String phone;

  /**
   * Ranking of the user 
   */
  private double ranking;

  /**
   * the password of the user
   */
  private String password;

  /**
   * Array of the califications that the user has been received 
   * */
  private ArrayList<Integer> califications;

  private String id;

  public User() {
  }

  public User(String name, String email, String phone, String password) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.ranking = 0.0;
    this.password = password;
    this.id = "";
    califications = new ArrayList<>();
  }



  /**
   * Set the value of name
   * Nombre de la persona
   * 
   * @param newVar the new value of name
   */
  public void setName (String newVar) {
    name = newVar;
  }

  /**
   * Get the value of name
   * Nombre de la persona
   * 
   * @return the value of name
   */
  public String getName () {
    return name;
  }

  /**
   * Set the value of email
   * correo electronico de la persona
   * 
   * @param newVar the new value of email
   */
  public void setEmail (String newVar) {
    email = newVar;
  }

  /**
   * Get the value of email
   * correo electronico de la persona
   * 
   * @return the value of email
   */
  public String getEmail () {
    return email;
  }

  /**
   * Set the value of phone
   * telefono de la persona
   * 
   * @param newVar the new value of phone
   */
  public void setPhone (String newVar) {
    phone = newVar;
  }

  /**
   * Get the value of phone
   * telefono de la persona
   * 
   * @return the value of phone
   */
  public String getPhone () {
    return phone;
  }

  /**
   * Set the value of ranking
   * Ranking of the user
   * @param newVar the new value of ranking
   */
  public void setRanking (double newVar) {
    ranking = newVar;
  }

  /**
   * Get the value of ranking
   * Ranking of the user
   * @return the value of ranking
   */
  public double getRanking () {
    return ranking;
  }

  /**
   * Set the value of password
   * the password of the user
   * @param newVar the new value of password
   */
  public void setPassword (String newVar) {
    password = newVar;
  }

  /**
   * Get the value of password
   * the password of the user
   * @return the value of password
   */
  public String getPassword () {
    return password;
  }

  /**
   * Set the value of califications
   * Array of the califications that the user has been received
   * 
   * @param newVar the new value of califications
   */
  public void setCalifications (ArrayList newVar) {
    califications = newVar;
  }

  /**
   * Get the value of califications
   * Array of the califications that the user has been received
   * 
   * @return the value of califications
   */
  public ArrayList<Integer> getCalifications () {
    return califications;
  }

  //
  // Other methods
  //

  /**
   * update the ranking of the user
   * @param        calification the new calification of the user
   * 
   */
  public void updateRanking(int calification)
  {
    int count = 0; 
    for(int i = 0; i < califications.size(); i++){
      count += califications.get(i).intValue();
    }
    this.ranking = count/califications.size();
  }


}
