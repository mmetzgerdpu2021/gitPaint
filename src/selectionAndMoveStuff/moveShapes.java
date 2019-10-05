package selectionAndMoveStuff;

import java.awt.Point;
import java.util.LinkedList;

import mouseCommands.DrawnShapesList;
import shapeStuff.IShapeAll;
import view.interfaces.PaintCanvasBase;
import mouseCommands.IUndoable;

public class moveShapes implements IUndoable{
	
	private int xDelta;
	private int yDelta;
	private LinkedList<IShapeAll> tempMoving;
	private LinkedList<IShapeAll> tempRemove;
	private PaintCanvasBase base = null;
	
	public moveShapes	(Point downPoint,
			 			 Point upPoint
						)
	{
		xDelta = (int) (upPoint.getX() - downPoint.getX());
		yDelta = (int) (upPoint.getY() - downPoint.getY());
		tempMoving = new LinkedList<IShapeAll>();
		tempRemove = new LinkedList<IShapeAll>();
		
		for (IShapeAll temp1 : moveSubject.selectedShapes)
		{
			if (base == null) base = temp1.getBase();
			for (IShapeAll temp2 : DrawnShapesList.DrawnShapes)
			{
				if (temp1.equals(temp2))
				{
					tempRemove.add(temp2);
					temp1.update(xDelta, yDelta);
					tempMoving.add(temp1);
				}
			}
		}
	}
	
	public void moveSelected ()
	{
		for (IShapeAll temp3 : tempRemove) // changed this from tempMoving
		{
			moveSubject.selectedShapes.removeFirstOccurrence(temp3);
			DrawnShapesList.DrawnShapes.removeFirstOccurrence(temp3);
		}
		for (IShapeAll temp4 : tempMoving)
		{
			moveSubject.selectedShapes.add(temp4);
			DrawnShapesList.DrawnShapes.add(temp4);
		}
	}

	@Override
	public void undo()
	{	
		for (IShapeAll temp3 : tempMoving)
		{
			moveSubject.selectedShapes.removeFirstOccurrence(temp3);
			DrawnShapesList.DrawnShapes.removeFirstOccurrence(temp3);
		}
		for (IShapeAll temp4 : tempRemove)
		{
			temp4.update(0 - xDelta, 0 - yDelta);
			
			moveSubject.selectedShapes.add(temp4);
			DrawnShapesList.DrawnShapes.add(temp4);
		}
		if (base != null) canvasRefresh.refresh(base);
	}

	@Override
	public void redo()
	{
		for (IShapeAll temp3 : tempRemove)
		{
			moveSubject.selectedShapes.removeFirstOccurrence(temp3);
			DrawnShapesList.DrawnShapes.removeFirstOccurrence(temp3);
		}
		for (IShapeAll temp4 : tempMoving)
		{
			temp4.update(xDelta, yDelta);
			
			moveSubject.selectedShapes.add(temp4);
			DrawnShapesList.DrawnShapes.add(temp4);
		}
		if (base != null) canvasRefresh.refresh(base);
	}

}
