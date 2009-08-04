/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.cache;


import legaltime.model.ClientBean;

/**
 *
 * @author bmartin
 */
public class ClientCache {
    private ClientBean[] cache;
    static ClientCache instance = null;
    protected ClientCache(){
         //cache = new ArrayList<ClientBean>();
    }
    static public ClientCache getInstance(){
        if(instance == null){
            instance = new ClientCache();
        }
        return instance;
    }

    public void setList(ClientBean[] cache_){
        cache= cache_;
    }
    public ClientBean[] getCache(){
        return cache;
    }

    public int getLength() {
        try{
        return cache.length;
        } catch(Exception e){
            return 0;
        }
    }
}
