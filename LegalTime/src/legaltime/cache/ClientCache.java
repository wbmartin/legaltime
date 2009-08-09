/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.cache;


import legaltime.model.ClientBean;
import legaltime.model.ClientManager;

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

    public String getName(Integer clientId) {
        String result="Unknown Client";
        for (int ndx=0; ndx< cache.length; ndx++){
            if(cache[ndx].getClientId() == clientId){
                result = cache[ndx].getFirstName() + " "+ cache[ndx].getLastName();
                break;
            }
        }
        return result;
    }

    public ClientBean getBeanById(Integer clientId) {
         ClientBean result = ClientManager.getInstance().createClientBean();
        for (int ndx=0; ndx< cache.length; ndx++){
            if(cache[ndx].getClientId().equals(clientId)){
                result = cache[ndx];
                break;
            }
        }
        return result;
    }
}
