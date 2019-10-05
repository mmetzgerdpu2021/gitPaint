package eventCommands;

import java.util.LinkedList;

import shapeStuff.IShapeAll;

public interface IEventCommand {

	public LinkedList<IShapeAll> clipboardShapes = new LinkedList<IShapeAll>();
}
