package com.martinanalytics.testmodule.client.app.icons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;



public interface AppClientBundle extends ClientBundle{
	 public static final AppClientBundle INSTANCE =  GWT.create(AppClientBundle.class);
	 
	 @Source("arrow-curve.png")
	 public ImageResource imgRightArrow();
	 
	 @Source("arrow-curve-180-left.png")
	 public ImageResource imgLeftArrow();
	
}
