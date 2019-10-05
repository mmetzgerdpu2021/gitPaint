package mouseCommands;

import java.awt.Point;

import model.interfaces.IApplicationState;
import shapeStuff.ShapeDrawerStaticFactory;
import view.interfaces.PaintCanvasBase;
import selectionAndMoveStuff.canvasRefresh;
import selectionAndMoveStuff.moveShapes;
import selectionAndMoveStuff.selectShapes;


public class SelectionTypeFactory {
	
	public static void doSomething (Point downPoint, Point upPoint, PaintCanvasBase base,
									IApplicationState appState)
	{
		
		if (appState.getActiveStartAndEndPointMode() == model.StartAndEndPointMode.DRAW)
			ShapeDrawerStaticFactory.drawShape	(downPoint,
											   	 upPoint,
												 base,
												 appState
												);
		
		else if (appState.getActiveStartAndEndPointMode() == model.StartAndEndPointMode.SELECT)
			selectShapes.select	(downPoint,
					 			 upPoint
								);
		
		else if (appState.getActiveStartAndEndPointMode() == model.StartAndEndPointMode.MOVE)
		{
			moveShapes MoveShape = new moveShapes	(downPoint,
													 upPoint
					   								);
			MoveShape.moveSelected();
			CommandHistory.add(MoveShape);
		}
		
		//base.repaint();
		//draw a white cover instead of repaint()
		canvasRefresh.refresh(base);
	}

}
