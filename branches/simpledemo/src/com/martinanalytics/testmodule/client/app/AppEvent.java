package com.martinanalytics.testmodule.client.app;




import java.util.*;

/**
 *
 * @author bmartin
 */
public class AppEvent extends EventObject {

        private static final long serialVersionUID = 1L;
        private AppMsg name;
        private Object payLoad;
        private Object payLoad2;
        private Object payLoad3;
        private String note;


    public AppEvent(Object source, AppMsg name_, Object payLoad_, String note_ ){
        super(source);
        this.name = name_;
        this.payLoad = payLoad_;
        this.payLoad2 = null;
        this.payLoad3 = null;
        this.note = note_;
    }
    public AppEvent(Object source, AppMsg name_, Object payLoad_,Object payLoad2_, Object payLoad3_, String note_ ){
        super(source);
        this.name = name_;
        this.payLoad = payLoad_;
        this.payLoad2 = payLoad2_;
        this.payLoad3 = payLoad3_;
        this.note = note_;
    }

    public AppMsg getName() {
        return name;
    }

    public void setName(AppMsg name_) {
        this.name = name_;
    }

    public Object getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(Object payLoad_) {
        this.payLoad = payLoad_;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

	/**
	 * @param payLoad2 the payLoad2 to set
	 */
	public void setPayLoad2(Object payLoad2) {
		this.payLoad2 = payLoad2;
	}

	/**
	 * @return the payLoad2
	 */
	public Object getPayLoad2() {
		return payLoad2;
	}

	/**
	 * @param payLoad3 the payLoad3 to set
	 */
	public void setPayLoad3(Object payLoad3) {
		this.payLoad3 = payLoad3;
	}

	/**
	 * @return the payLoad3
	 */
	public Object getPayLoad3() {
		return payLoad3;
	}

}

