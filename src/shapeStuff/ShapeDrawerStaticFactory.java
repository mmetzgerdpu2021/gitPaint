package shapeStuff;

import view.interfaces.PaintCanvasBase;

import java.awt.Color;
import java.awt.Point;
import model.ShapeColor;
import model.interfaces.IApplicationState;
import mouseCommands.CommandHistory;
import mouseCommands.DrawnShapesList;

import java.util.*;

public class ShapeDrawerStaticFactory { //factory pattern
	
	public static void drawShape	(Point downPoint,
									 Point upPoint,
									 PaintCanvasBase base,
									 IApplicationState appState
									)
	{
		Map<ShapeColor,Color> shapeColors = new HashMap<ShapeColor,Color>();
		shapeColors.put(model.ShapeColor.BLACK, Color.BLACK);
		shapeColors.put(model.ShapeColor.BLUE, Color.BLUE);
		shapeColors.put(model.ShapeColor.CYAN, Color.CYAN);
		shapeColors.put(model.ShapeColor.DARK_GRAY, Color.DARK_GRAY);
		shapeColors.put(model.ShapeColor.GRAY, Color.GRAY);
		shapeColors.put(model.ShapeColor.GREEN, Color.GREEN);
		shapeColors.put(model.ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
		shapeColors.put(model.ShapeColor.MAGENTA, Color.MAGENTA);
		shapeColors.put(model.ShapeColor.ORANGE, Color.ORANGE);
		shapeColors.put(model.ShapeColor.PINK, Color.PINK);
		shapeColors.put(model.ShapeColor.RED, Color.RED);
		shapeColors.put(model.ShapeColor.WHITE, Color.WHITE);
		shapeColors.put(model.ShapeColor.YELLOW, Color.YELLOW);
		
		
		String shadeType = "";
		if (appState.getActiveShapeShadingType() == model.ShapeShadingType.FILLED_IN)
			shadeType = "filled";
		else if (appState.getActiveShapeShadingType() == model.ShapeShadingType.OUTLINE)
			shadeType = "outline";
		else if (appState.getActiveShapeShadingType() == model.ShapeShadingType.OUTLINE_AND_FILLED_IN)
			shadeType = "filledAndOutline";
		
		IShapeAll command = null;  //command pattern

		if (appState.getActiveShapeType() == model.ShapeType.RECTANGLE)
		command = new	DrawRectangleCommand	(base,
												 downPoint,
												 upPoint,
												 shadeType,
												 shapeColors.get(appState.getActivePrimaryColor()),
												 shapeColors.get(appState.getActiveSecondaryColor())
												);
		
		else if (appState.getActiveShapeType() == model.ShapeType.TRIANGLE)
			command = new DrawTriangleCommand	(base,
												 downPoint,
												 upPoint,
												 shadeType,
												 shapeColors.get(appState.getActivePrimaryColor()),
												 shapeColors.get(appState.getActiveSecondaryColor())
												);
		
		else if (appState.getActiveShapeType() == model.ShapeType.ELLIPSE)
			command = new DrawEllipseCommand	(base,
												 downPoint,
												 upPoint,
												 shadeType,
												 shapeColors.get(appState.getActivePrimaryColor()),
												 shapeColors.get(appState.getActiveSecondaryColor())
												);
		else 
			command = new NullShapeCommand();
		
		DrawnShapesList.DrawnShapes.add(command);
		CommandHistory.add(command);
	}

}
