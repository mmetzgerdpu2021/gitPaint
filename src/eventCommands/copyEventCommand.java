package eventCommands;

import selectionAndMoveStuff.moveSubject;
import shapeStuff.IShapeAll;

public class copyEventCommand implements IEventCommand{
	
	public static void run()
	{
		clipboardShapes.clear();
		for (IShapeAll temp : moveSubject.selectedShapes)
		{
			clipboardShapes.add(temp);
		}
	}

}
