package net.mouseroom.client;

import net.mouseroom.datamodel.Cage;
import net.mouseroom.datamodel.Line;
import net.mouseroom.datamodel.Mouse;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mouse")
public interface MouseService extends RemoteService {
	
  public void saveMouse(Mouse mouse) throws NotLoggedInException;
  public void deleteMouse(Long id) throws NotLoggedInException;
  public Mouse getMouse(Long id) throws NotLoggedInException;
  public Mouse[] getMice() throws NotLoggedInException;
  
  public void saveCage(Cage cage) throws NotLoggedInException;
  public void deleteCage(Long id) throws NotLoggedInException;
  public Cage getCage(Long id) throws NotLoggedInException;
  public Cage[] getCages() throws NotLoggedInException;
  
  public void saveLine(Line line) throws NotLoggedInException;
  public void deleteLine(Long id) throws NotLoggedInException;
  public Line getLine(Long id) throws NotLoggedInException;
  public Line[] getLines() throws NotLoggedInException;
  
}