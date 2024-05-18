package edu.student.expirement9.services;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import edu.student.expirement9.models.User;

@Service
public class UserService {
    private ArrayList<User> userList = new ArrayList<User>(Arrays.asList(
            new User(1, "Ayham Hashesh"),
            new User(2, "Rajaie Imseeh")
    ));

    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public boolean addUserToList(User user) {
        return userList.add(user);
    }

    public boolean deleteUserFromList(int id) {
        User userT = null;
        for(int i = 0; i < userList.size(); i++){
            if( id == userList.get(i).getId())userT = userList.get(i);
        }
        return userList.remove(userT);
    }

    public boolean updateUserInList(User user, int id){
        for(int i = 0; i < userList.size(); i++){
            if( id == userList.get(i).getId())return userList.get(i).equals(user);
        }
        return false;
    }

    public boolean updateUserNameInList(String name, int id){
        for(int i = 0; i < userList.size(); i++){
            if( id == userList.get(i).getId()){
                userList.get(i).setUserName(name);
                return true;
            }
        }
        return false;
    }

    public String sayHello() {
        return "hello";
    }
}