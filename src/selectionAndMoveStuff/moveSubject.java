package selectionAndMoveStuff;

import shapeStuff.IShapeMoveObserver;

public class moveSubject implements IMoveSubject {

	@Override
	public void notifyObservers(int xMoved, int yMoved) {
		for (IShapeMoveObserver temp : selectedShapes)
		{
			temp.update (xMoved, yMoved);
		}

	}

}
