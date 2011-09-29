package com.martinanalytics.testmodule.client.app;


import java.util.Vector;

import com.allen_sauer.gwt.log.client.Log;



public  class AppEventProducer {
        @SuppressWarnings("unchecked")
		private Vector AppEventListeners;

        @SuppressWarnings("unchecked")
		public AppEventProducer() {
                super();
                AppEventListeners = new Vector();
               
        }
         
       
        @SuppressWarnings("unchecked")
		public void addAppEventListener(AppEventListener ael){
                 //add main frame to vector of listeners
                 if (AppEventListeners.contains(ael))  return;
                 AppEventListeners.addElement(ael);
                }
                public  void notifyAppEvent(Object source_, AppMsg msgName_, Object payLoad_){
                    notifyAppEvent(source_, msgName_, payLoad_, null);
                }
                public  void notifyAppEvent(Object source_, AppMsg msgName_){
                	AppEvent appEvent = new AppEvent(source_,msgName_,null, null);
                    notifyAppEvent(appEvent);
                }
                public  void notifyAppEvent(Object source_, AppMsg msgName_, Object payLoad_, String note_){
                	AppEvent appEvent = new AppEvent (source_, msgName_, payLoad_, note_);
                          notifyAppEvent(appEvent);
                }
                public  void notifyAppEvent(Object source_, AppMsg msgName_, Object payLoad_, Object payLoad2_,Object payLoad3_,String note_){
                	AppEvent appEvent = new AppEvent (source_, msgName_, payLoad_,payLoad2_,payLoad3_, note_);
                    //Log.debug("Trying to notify:" + payLoad2_ + " | "+ appEvent.getPayLoad2());
                    notifyAppEvent(appEvent);
                }
                @SuppressWarnings("unchecked")
				public void notifyAppEvent(AppEvent event_){
                	if (!event_.getName().equals("")){
                        Vector vtemp = (Vector)AppEventListeners.clone();
                            for (int x = 0; x < vtemp.size(); x++){
                                 AppEventListener target = null;
                                 target = (AppEventListener)vtemp.elementAt(x);
                                 target.onAppEventNotify(event_);
                            }
                       }
                	
                }
}


