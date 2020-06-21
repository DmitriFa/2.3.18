package service;

import com.sun.deploy.security.SelectableSecurityManager;
import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static java.time.chrono.JapaneseEra.values;

public class UserService {

    private static volatile UserService instance;

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null)
            synchronized (UserService.class) {
                if (instance == null)
                    instance = new UserService();
            }
        return instance;
    }

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap( new HashMap<>() );
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong( 0 );
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap( new HashMap<>() );


    public List<User> getAllUsers() {
    /*    List<User> users = new ArrayList<>();
        //  Collection<User> users = dataBase.values();
        for (Map.Entry<Long, User> entry : dataBase.entrySet()) {
            users.add( entry.getValue());
        }
        // List<User> user = Arrays.asList(users.toArray());
        return users;*/
        return new ArrayList<User>(dataBase.values());
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }

    public boolean addUser(User user) {
        Long id = maxId.getAndIncrement();
        if(user == null){
            return false;
        }
        for (Map.Entry<Long, User> entry : dataBase.entrySet()) {
            if (user.getEmail().equals(entry.getValue().getEmail())) {
                return false;
            }
        }
        dataBase.put( id, user );
        // showdataBase();
        return true;
    }



    public void deleteAllUser() {
        dataBase.clear();
    }

    public boolean isExistsThisUser(User user) {
        // for (Map.Entry<Long, User> entry : dataBase.entrySet()) {
        //   if (user.getEmail().equals( entry.getValue().getEmail())& user.getPassword().equals(entry.getValue().getPassword())) {
        //  if(dataBase.containsKey(user)) {
        if(dataBase.containsValue(user)){
            return true;
        }
        // }
        return false;
    }

    public List<User> getAllAuth() {
     /*   List<User> authUsers = new ArrayList<>();
        for (Map.Entry<Long, User> entry : authMap.entrySet()) {
            authUsers.add( entry.getValue() );
        }
        return authUsers;*/
        return new ArrayList<User>(authMap.values());
        }

      public boolean authUser(User user) {
          if (user == null) {
              return false;
          }
          for (Map.Entry<Long, User> entry : dataBase.entrySet()) {
              if(user.getEmail().equals(entry.getValue().getEmail()) & user.getPassword().equals(entry.getValue().getPassword())){
                  authMap.put(entry.getKey(),user);
                  // showauthMap();
                  return true;
              }
          }
          return false;
      }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {
        //  for (Map.Entry<Long, User> entry : authMap.entrySet()) {
        //    if (id.equals( entry.getKey() )) {
        //     return true;
        if (authMap.containsKey(id)){
            return true;
        }
        return false;
    }

/*    public void showdataBase() {
        for (Map.Entry<Long, User> entry : dataBase.entrySet()) {
            System.out.println( (entry.getKey()) + "MAPA1" );
            System.out.println( (entry.getValue().getEmail()) + "MAPA1" );
            System.out.println( (entry.getValue().getPassword()) + "MAPA1" );
        }
    }

    public void showauthMap() {
        for (Map.Entry<Long, User> entry : authMap.entrySet()) {
            System.out.println( (entry.getKey()) + "MAPA2" );
            System.out.println( (entry.getValue().getEmail()) + "MAPA2" );
            System.out.println( (entry.getValue().getPassword()) + "MAPA2" );
        }
    }*/
}
