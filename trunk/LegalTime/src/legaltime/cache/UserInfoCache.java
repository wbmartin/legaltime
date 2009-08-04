/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.cache;


import legaltime.model.UserInfoBean;

/**
 *
 * @author bmartin
 */
public class UserInfoCache {
    private UserInfoBean[] cache;
    static UserInfoCache instance = null;

    protected UserInfoCache(){
         //cache = new ArrayList<ClientBean>();
    }
    static public UserInfoCache getInstance(){
        if(instance == null){
            instance = new UserInfoCache();
        }
        return instance;
    }

    public void setList(UserInfoBean[] cache_){
        cache= cache_;
    }
    public UserInfoBean[] getCache(){
        return cache;
    }


}
