package eventCommands;

import java.util.LinkedList;

import mouseCommands.DrawnShapesList;
import selectionAndMoveStuff.canvasRefresh;
import selectionAndMoveStuff.moveSubject;
import shapeStuff.IShapeAll;
import view.interfaces.PaintCanvasBase;
import mouseCommands.IUndoable;

public class deleteEventCommand implements IEventCommand, IUndoable{
	
	private LinkedList<IShapeAll> tempList;
	private PaintCanvasBase base = null;
	
	public deleteEventCommand()
	{
		tempList = new LinkedList<IShapeAll>();	
		
		for (IShapeAll temp1 : moveSubject.selectedShapes)
		{
			for (IShapeAll temp2 : DrawnShapesList.DrawnShapes)
			{
				if (base == null) base = temp2.getBase();
				
				if (temp1.equals(temp2))
					tempList.add(temp1);
			}
		}
	}
	
	public void run()
	{
		for (IShapeAll temp : tempList)
		{
			moveSubject.selectedShapes.removeFirstOccurrence(temp);
			DrawnShapesList.DrawnShapes.removeFirstOccurrence(temp);
		}
		if (base != null) canvasRefresh.refresh(base);
	}

	@Override
	public void undo() {
		for (IShapeAll temp : tempList)
		{
			moveSubject.selectedShapes.add(temp);
			DrawnShapesList.DrawnShapes.add(temp);
		}
		if (base != null) canvasRefresh.refresh(base);
	}

	@Override
	public void redo() {
		this.run();
	}

}
