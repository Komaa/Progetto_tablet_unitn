package tablet_unitn.dbmanager;

import java.util.List; 

import tablet_unitn.treasurehunt.User;
 
public interface UserDAO { 
 public void open(); 
 public void close(); 
 
 public User insertUser(User user); 
 public User updateUser(User user);
 public void deleteUser(User user); 
 public List<User> getAllUser();
 public User getInfo(String ID);
 public void logout(String ID);
}