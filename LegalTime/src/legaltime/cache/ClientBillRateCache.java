/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.cache;


import legaltime.model.ClientBean;
import legaltime.model.ClientBillRateBean;

/**
 *
 * @author bmartin
 */
public class ClientBillRateCache {
    private ClientBillRateBean[] cache;
    static ClientBillRateCache instance = null;
    protected ClientBillRateCache(){
         //cache = new ArrayList<ClientBean>();
    }
    static public ClientBillRateCache getInstance(){
        if(instance == null){
            instance = new ClientBillRateCache();
        }
        return instance;
    }

    public void setList(ClientBillRateBean[] cache_){
        cache= cache_;
    }
    public ClientBillRateBean[] getCache(){
        return cache;
    }

    public int getLength() {
        try{
        return cache.length;
        } catch(Exception e){
            return 0;
        }
    }

       public double getBillRate(int clientId_, String userKey_){
        double billRate =300D;
        for (int ndx = 0;ndx< cache.length;ndx++){
            //TODO: add clientbased bill rates
            if (cache[ndx].getClientId() == clientId_ && cache[ndx].getUserKey().equals(userKey_)){
                billRate = cache[ndx].getBillRate();
                break;
            }
        }

        return billRate;
    }
}
