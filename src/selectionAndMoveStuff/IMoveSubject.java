package selectionAndMoveStuff;

import java.util.LinkedList;
import shapeStuff.IShapeAll;

public interface IMoveSubject {
	public LinkedList<IShapeAll> selectedShapes = new LinkedList<IShapeAll>();
	
	public void notifyObservers(int xMoved, int yMoved);
}
