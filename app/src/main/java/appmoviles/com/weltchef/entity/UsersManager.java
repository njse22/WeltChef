package appmoviles.com.weltchef.entity;


import java.util.ArrayList;

/**
 * Class AcountManager
 */
public class UsersManager {

  private ArrayList<User> users;
  
  public UsersManager() {
    users = new ArrayList<>();
    Chef andres = new Chef("andres", "azo_67@hotmail.es","315 4163438",
            0.0 , "****", true, false , "ffff");

    users.add(andres);
  }

  /**
   * Set the value of users
   * @param newVar the new value of users
   */
  public void setUsers (ArrayList<User> newVar) {
    users = newVar;
  }

  /**
   * Get the value of users
   * @return the value of users
   */
  public ArrayList<User> getUsers () {
    return users;
  }

  /**
   * register a new user and add to list of users
   * @param        user the new user of the application
   */
  public void register(User user)
  {
    users.add(user); 
  }


  /**
   * identify Chef or Client
   * true : if the user is a chef
   * false : if the user is a client
   * @return       boolean
   * @param        user The user that want to know if is chef or not
   * 
   */
  public boolean identifyUser(User user)
  {
    if(user instanceof Chef )
    	return true; 
    else 
    	return false; 
  }

  public User getUserByEmail(String email){
    for (int i= 0; i < users.size(); i++){
      if(users.get(i).getEmail().equals(email))
        return users.get(i);
    }
    return null;
  }


  /**
   * calculate and update the ranking to the user
   * @return       double
   * @param        ranking new calification  to the user
   * 
   */
  public double updateRanking(int ranking) {
    return 0.0;
  }


}
