package com.martinanalytics.testmodule.client.model;

import java.io.Serializable;







public class GWTCustomException extends Exception implements Serializable{
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        String message;
        public GWTCustomException(){
                
        }
        
        public GWTCustomException(final String msg_){
                super(msg_);
                this.message = msg_;
        }
        public GWTCustomException(String message, Throwable cause) {
                super(message, cause);
                this.message = message;
                }
        
        public String getMessage(){
                return message;
        }

}

