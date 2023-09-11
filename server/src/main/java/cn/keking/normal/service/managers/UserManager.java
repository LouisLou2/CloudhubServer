package cn.keking.normal.service.managers;

import cn.hutool.core.collection.ConcurrentHashSet;

public class UserManager {
    private static UserManager instance;
    ConcurrentHashSet<String> loggedUsers=new ConcurrentHashSet<>();
    static{
        instance=new UserManager();
    }
    public static UserManager getInstance(){
        if(instance==null){
            instance=new UserManager();
        }
        return instance;
    }
    private UserManager(){
        loggedUsers=new ConcurrentHashSet<String>();
    }
    public void addLoggedUser(String username){
        loggedUsers.add(username);
    }
    public void removeLoggedUser(String username){
        loggedUsers.remove(username);
    }
    public boolean isLogged(String username){
        return loggedUsers.contains(username);
    }

}
