package tablet_unitn.dbmanager;

import java.util.List; 

import tablet_unitn.treasurehunt.User;
 
public interface UserDAO { 
 public void open(); 
 public void close(); 
 
 public User insertUser(User User) ; 
 public User updateUser(User User) ;
 public void deleteUser(User User) ; 
 public List<User> getAllUser() ;
}