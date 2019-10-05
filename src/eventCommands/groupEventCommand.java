package eventCommands;

import mouseCommands.DrawnShapesList;
import selectionAndMoveStuff.moveSubject;
import shapeStuff.GroupCommand;
import shapeStuff.IShapeAll;

public class groupEventCommand implements IEventCommand {
	
	public static GroupCommand createGroup()
	{
		GroupCommand groupEC = new GroupCommand();
		for (IShapeAll shape : moveSubject.selectedShapes)
		{
			groupEC.add(shape);
			DrawnShapesList.DrawnShapes.removeFirstOccurrence(shape);
		}
		moveSubject.selectedShapes.clear();
		moveSubject.selectedShapes.add(groupEC);
		DrawnShapesList.DrawnShapes.add(groupEC);
		return groupEC;
	}
}
