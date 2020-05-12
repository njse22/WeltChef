package appmoviles.com.weltchef.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.Client;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.util.Constants;

public class FirebaseDB  {

    private DatabaseReference databaseReference;
    private Query querySearch;
    private UsersManager usersManager;

    public FirebaseDB() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.querySearch = null;
        this.usersManager = new UsersManager();
    }

    public Query getQuerySearch() {
        return querySearch;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }


    public void getMenus(int type){
        querySearch = databaseReference
                .child(Constants.FIREBASE_MENU_BRANCH)
                .orderByChild("type")
                .equalTo(type);
    }

    public void searchOrder(String clientId){
        querySearch = databaseReference
                .child(Constants.FIREBASE_ORDER_BRANCH)
                .orderByChild("clientId")
                .equalTo(clientId);
    }

    public void searchUserByid(String id){
        querySearch = databaseReference
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(id);
    }

    public void searchChatByChef(String idChef){
        querySearch = databaseReference
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .orderByChild("userIDChef")
                .equalTo(idChef);
    }

    public void searchChatByClient(String idClient){
        querySearch = databaseReference
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .orderByChild("userIDClient")
                .equalTo(idClient);
    }

    public void searchUserByEmail(String email, String branch){
         querySearch = databaseReference
                .child(branch)
                .orderByChild("email")
                .equalTo(email);
    }

    public String createId(String branch){
        return databaseReference.child(branch).push().getKey();
    }

    public void sendInfo(Object object, String id, String branch){
        databaseReference
                .child(branch)
                .child(id)
                .setValue(object);
    }

    public void readInfo(String branch, String id){
        databaseReference.child(branch).child(id);
    }

    public void initDatabase(){

        Chef chef1 = new Chef(
                true,
                false,
                "Chef especializado en comida Colombiana",
                "juan11@gmail.com",
                "juan garcia",
                "Juan1234",
                "3135447788",
                createId(Constants.FIREBASE_USER_BRANCH));

        Chef chef2 = new Chef(
                true,
                false,
                "Chef con 5 años de experiencia en comida gourmet",
                "diego33@gmail.com",
                "diego guzman",
                "Juan1234",
                "3165437798",
                createId(Constants.FIREBASE_USER_BRANCH));

        Client client1 = new Client(
                "Felipe peña",
                "felipe44@gmail.com",
                "312666788892",
                "loco23",
                createId(Constants.FIREBASE_USER_BRANCH));

        Client client2 = new Client(
                "johan",
                "johan44@gmail.com",
                "317644783891",
                "loco2333",
                createId(Constants.FIREBASE_USER_BRANCH));

        Menu menu1 = new Menu(Menu.COLOMBIANA, 30000, "Sancocho Colombiano", "sancocho",createId(Constants.FIREBASE_MENU_BRANCH) );
        menu1.setChefId(chef1.getId());
        Menu menu2 = new Menu(Menu.COLOMBIANA, 30000, "Un tradicional plato colombiano", "vandeja paisa",createId(Constants.FIREBASE_MENU_BRANCH));
        menu2.setChefId(chef1.getId());
        Menu menu3 = new Menu(Menu.MEXICANA, 30000, "Un tradicional plato Mexicano", "Tacos", createId(Constants.FIREBASE_MENU_BRANCH));
        menu3.setChefId(chef2.getId());
        Menu menu4 = new Menu(Menu.MEXICANA, 30000, "Un tradicional plato Mexicano", "Burritos", createId(Constants.FIREBASE_MENU_BRANCH));
        menu4.setChefId(chef2.getId());



        ArrayList<Menu> menus1 = new ArrayList<>();
        menus1.add(menu1);
        menus1.add(menu2);

        ArrayList<Menu> menus2 = new ArrayList<>();
        menus2.add(menu3);
        menus2.add(menu4);

        Order order1 = new Order(60000, client1.getId(), createId(Constants.FIREBASE_ORDER_BRANCH));
        order1.setClientId(client1.getId());
        order1.setPlates(menus1);
        Order order2 = new Order(60000, client2.getId(), createId(Constants.FIREBASE_ORDER_BRANCH));
        order2.setClientId(client2.getId());
        order2.setPlates(menus2);



        Message message1 = new Message(
                0,
                UUID.randomUUID().toString(),
                "Hola tienes disponibilidad para cuando termine la cuarentena?",
                Calendar.getInstance().getTime().getTime(),
                chef1.getId());

        Message message2 = new Message(
                0,
                UUID.randomUUID().toString(),
                "Claro agenda tu pedido",
                Calendar.getInstance().getTime().getTime(),
                client1.getId());

        Message message3 = new Message(
                0,
                UUID.randomUUID().toString(),
                "Oye pues esto no mejora yo creo que no se va a poder hacer el pedido",
                Calendar.getInstance().getTime().getTime(),
                chef2.getId());

        Message message4 = new Message(
                0,
                UUID.randomUUID().toString(),
                "pues si loco ni modo para cuando mejore todo me escribes",
                Calendar.getInstance().getTime().getTime(),
                client2.getId());


        ArrayList<Message> m1 = new ArrayList<>();
        m1.add(message1);
        m1.add(message2);

        ArrayList<Message> m2 = new ArrayList<>();
        m2.add(message3);
        m2.add(message4);

        MessageContainer messageContainer1 = new MessageContainer(
                createId(Constants.FIREBASE_CHATS_BRANCH),
                m1,
                chef1.getId(),
                client1.getId());

        MessageContainer messageContainer2 = new MessageContainer(
                createId(Constants.FIREBASE_CHATS_BRANCH),
                m2,
                chef2.getId(),
                client2.getId());

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(chef1.getId())
                .setValue(chef1);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(chef2.getId())
                .setValue(chef2);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(client1.getId())
                .setValue(client1);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(client2.getId())
                .setValue(client2);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu1.getId())
                .setValue(menu1);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu2.getId())
                .setValue(menu2);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu3.getId())
                .setValue(menu3);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu4.getId())
                .setValue(menu4);

        Menu menu5 = new Menu(Menu.ITALIANA, 25000, "un plato italiano", "risoto", createId(Constants.FIREBASE_MENU_BRANCH));
        menu4.setChefId(chef2.getId());

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu5.getId())
                .setValue(menu5);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_ORDER_BRANCH)
                .child(order1.getId())
                .setValue(order1);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_ORDER_BRANCH)
                .child(order2.getId())
                .setValue(order2);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .child(messageContainer1.getId())
                .setValue(messageContainer1);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .child(messageContainer2.getId())
                .setValue(messageContainer2);

    }



}
