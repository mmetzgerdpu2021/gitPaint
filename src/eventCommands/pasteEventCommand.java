package eventCommands;

import shapeStuff.IShapeAll;
import view.interfaces.PaintCanvasBase;

import java.awt.Point;
import java.util.LinkedList;

import mouseCommands.DrawnShapesList;
import selectionAndMoveStuff.canvasRefresh;
import shapeStuff.DrawTriangleCommand;
import shapeStuff.DrawRectangleCommand;
import shapeStuff.DrawEllipseCommand;
import mouseCommands.IUndoable;

public class pasteEventCommand implements IEventCommand, IUndoable{
	
	private LinkedList<IShapeAll> tempList;
	private PaintCanvasBase base = null;
	
	public pasteEventCommand()
	{		
		tempList = new LinkedList<IShapeAll>();
		
		for (IShapeAll temp : clipboardShapes)
		{
			Point tempDownPoint = new Point(temp.getStartX() + 15, temp.getStartY() + 15);
			Point tempUpPoint = new Point(temp.getStartX() + 15 + temp.getWidth(),
											temp.getStartY() + 15 + temp.getHeight());
			IShapeAll newTemp = null;
			
			if (base == null) base = temp.getBase();
			
			if (temp instanceof DrawTriangleCommand)
			{
				int[] xArray = ((DrawTriangleCommand) temp).getXarray();
				int[] yArray = ((DrawTriangleCommand) temp).getYarray();
				tempDownPoint = new Point(xArray[0], yArray[0]);
				tempUpPoint = new Point(xArray[2], yArray[2]);
				newTemp = new DrawTriangleCommand	(temp.getBase(),
													 tempDownPoint,
													 tempUpPoint,
													 temp.getShadeType(),
													 temp.getPrimColor(),
													 temp.getSecColor()
													);
			}
			else if (temp instanceof DrawRectangleCommand)
			{
				newTemp = new DrawRectangleCommand	(temp.getBase(),
													 tempDownPoint,
													 tempUpPoint,
													 temp.getShadeType(),
													 temp.getPrimColor(),
													 temp.getSecColor()
													);
			}
			else if (temp instanceof DrawEllipseCommand)
			{
				newTemp = new DrawEllipseCommand	(temp.getBase(),
													 tempDownPoint,
													 tempUpPoint,
													 temp.getShadeType(),
													 temp.getPrimColor(),
													 temp.getSecColor()
													);
			}
			if (newTemp != null) tempList.add(newTemp);	
		}
	}

	public void run()
	{	
		for (IShapeAll temp : tempList)
		{
			if (temp != null) DrawnShapesList.DrawnShapes.add(temp);	
		}
		if (base != null) canvasRefresh.refresh(base);
	}

	@Override
	public void undo() {
		for (IShapeAll temp : tempList)
		{
			if (temp != null) DrawnShapesList.DrawnShapes.removeFirstOccurrence(temp);	
		}
		if (base != null) canvasRefresh.refresh(base);
	}

	@Override
	public void redo() {
		this.run();
	}
}
