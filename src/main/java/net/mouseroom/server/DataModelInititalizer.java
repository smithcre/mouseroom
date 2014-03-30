package net.mouseroom.server;

import net.mouseroom.datamodel.*;

import com.googlecode.objectify.ObjectifyService;

public class DataModelInititalizer {
	
	public static void init(){
		ObjectifyService.register(Cage.class);
		ObjectifyService.register(Line.class);
		ObjectifyService.register(Mouse.class);
		ObjectifyService.register(Pregnancy.class);
		ObjectifyService.register(Scientist.class);
	}
}
