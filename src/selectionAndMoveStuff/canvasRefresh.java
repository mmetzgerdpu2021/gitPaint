package selectionAndMoveStuff;

import java.awt.Color;
import java.awt.Graphics2D;

import mouseCommands.DrawnShapesList;
import shapeStuff.IShapeAll;
import view.interfaces.PaintCanvasBase;
import shapeStuff.NullShapeCommand;

public class canvasRefresh {
	
	public static void refresh(PaintCanvasBase base)
	{
		Graphics2D graphics2d = base.getGraphics2D();
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0, 0, 1920, 1080);
		for (IShapeAll temp : DrawnShapesList.DrawnShapes)
		{
			if (temp == null) temp = new NullShapeCommand();
			temp.run();
		}
		for (IShapeAll temp2 : moveSubject.selectedShapes)
		{
			if (temp2 == null) temp2 = new NullShapeCommand();
			temp2.outline();
		}
	}

}
