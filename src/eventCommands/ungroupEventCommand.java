package eventCommands;

import java.util.LinkedList;

import mouseCommands.IUndoable;
import selectionAndMoveStuff.moveSubject;
import shapeStuff.IShapeAll;
import shapeStuff.GroupCommand;

public class ungroupEventCommand implements IEventCommand, IUndoable {
	
	private LinkedList<IShapeAll> removedGroups;

	public ungroupEventCommand()
	{
		removedGroups = new LinkedList<IShapeAll>();
		
		for (IShapeAll shape : moveSubject.selectedShapes)
		{
			if (shape instanceof GroupCommand)
				removedGroups.add(shape);
		}
		for (IShapeAll shape2 : removedGroups)
			shape2.undo();
	}
	public void undo()
	{
		for (IShapeAll group : removedGroups)
			group.redo();
	}

	public void redo()
	{
		for (IShapeAll group : removedGroups)
			group.undo();
	}

}
