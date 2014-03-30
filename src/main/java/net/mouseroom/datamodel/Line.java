package net.mouseroom.datamodel;

import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Line implements Serializable {
	private @Parent Key<Scientist> owner;
	private @Id Long id;
	private String name;
	private String description;
}
