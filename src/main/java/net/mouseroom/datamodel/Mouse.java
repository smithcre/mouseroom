package net.mouseroom.datamodel;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Mouse implements Serializable {
	private @Parent Key<Scientist> owner;
	private @Id Long id;
	private String name;
	private Gender gender;
	private String color;
	private String earPunch;
	private Key<Pregnancy> parentInfo;
	private Key<Pregnancy> childrenInfo;
	private Date sacrificeDate;
	private Key<Cage> location;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Key<Scientist> getOwner() {
		return owner;
	}
	public void setOwner(Key<Scientist> owner) {
		this.owner = owner;
	}
	
	
}
