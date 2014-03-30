package net.mouseroom.datamodel;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Pregnancy implements Serializable {
	private @Parent Key<Scientist> owner;
	private @Id Long id;
	private Date plugDate;
	private Date bithDate;
	private Key<Mouse> mother;
	private Key<Mouse> father;
}
