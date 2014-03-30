package net.mouseroom.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.mouseroom.client.MouseService;
import net.mouseroom.client.NotLoggedInException;
import net.mouseroom.datamodel.Cage;
import net.mouseroom.datamodel.Line;
import net.mouseroom.datamodel.Mouse;
import net.mouseroom.datamodel.Scientist;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class MouseServiceImpl extends RemoteServiceServlet implements MouseService {

	private static final Logger LOG = Logger.getLogger(MouseServiceImpl.class.getName());
	
	static {
		DataModelInititalizer.init();
	}

	private User getUser() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser();
	}
	
	private Key<Scientist> getScientist() throws NotLoggedInException
	{
		User user = getUser();
		
		if (user == null) {
			throw new NotLoggedInException("Not logged in.");
		}
		
		return Key.create(Scientist.class, user.getEmail());
	}

	@Override
	public void saveMouse(Mouse mouse) throws NotLoggedInException {
		mouse.setOwner(getScientist());
		ofy().save().entity(mouse);
	}

	@Override
	public void deleteMouse(Long id) throws NotLoggedInException {
		ofy().delete().key(Key.create(getScientist(), Mouse.class, id)).now();
	}

	@Override
	public Mouse getMouse(Long id) throws NotLoggedInException {
		return ofy().load().key(Key.create(getScientist(), Mouse.class, id)).now();
	}

	@Override
	public Mouse[] getMice() throws NotLoggedInException {
		return ofy().load().type(Mouse.class).ancestor(getScientist()).list().toArray(new Mouse[0]);
	}

	@Override
	public void saveCage(Cage cage) throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteCage(Long id) throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Cage getCage(Long id) throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Cage[] getCages() throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveLine(Line line) throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteLine(Long id) throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Line getLine(Long id) throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Line[] getLines() throws NotLoggedInException {
		throw new UnsupportedOperationException();
	}
}