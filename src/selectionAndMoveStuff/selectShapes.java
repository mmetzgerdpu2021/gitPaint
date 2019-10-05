package selectionAndMoveStuff;

import java.awt.Point;
import java.util.LinkedList;

import mouseCommands.DrawnShapesList;
import shapeStuff.IShapeAll;

public class selectShapes {

	public static void select	(Point downPoint,
			 					 Point upPoint
								)
	{
		if (downPoint.equals(upPoint))
		{
			moveSubject.selectedShapes.clear();
			
			LinkedList<IShapeAll> tempOutlines = new LinkedList<IShapeAll>();
			for (IShapeAll temp2 : tempOutlines)
			{
				DrawnShapesList.DrawnShapes.removeFirstOccurrence(temp2);
			}
		}
		
		else
		{
			int selectBoxX;
			if (downPoint.getX() < upPoint.getX())
				selectBoxX = (int) downPoint.getX();
			else selectBoxX = (int) upPoint.getX();
			
			int selectBoxY;
			if (downPoint.getY() < upPoint.getY())
				selectBoxY = (int) downPoint.getY();
			else selectBoxY = (int) upPoint.getY();
			
			int selectBoxWidth = java.lang.Math.abs( (int) downPoint.getX() - (int) upPoint.getX());
			
			int selectBoxHeight = java.lang.Math.abs( (int) downPoint.getY() - (int) upPoint.getY());
			
			//LinkedList<IShapeAll> tempSelected = new LinkedList<IShapeAll>();

			for (IShapeAll temp : DrawnShapesList.DrawnShapes)
			{
				if (temp.getStartX() < selectBoxX + selectBoxWidth &&
					temp.getStartX() + temp.getWidth() > selectBoxX &&
					temp.getStartY() < selectBoxY + selectBoxHeight &&
					temp.getStartY() + temp.getHeight() > selectBoxY &&
					!moveSubject.selectedShapes.contains(temp))
				{
					moveSubject.selectedShapes.add(temp);
				}
			}

		}
	}
}
