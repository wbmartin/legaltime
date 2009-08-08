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
    private UserInfoBean[] userInfoBeans;
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
        userInfoBeans= cache_;
    }
    public UserInfoBean[] getCache(){
        return userInfoBeans;
    }

    public String getNameFromUserKey(String userKey_ ){
        String result = "";
        int ndx;
        for(ndx =0;ndx< userInfoBeans.length;ndx++){
            if (userInfoBeans[ndx].getUserKey().equals(userKey_)){
                result = userInfoBeans[ndx].getUserKey();
                break;
            }


        }


        return result;
    }


}
