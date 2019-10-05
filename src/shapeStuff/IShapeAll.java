package shapeStuff;

import java.awt.Color;
import view.interfaces.PaintCanvasBase;
import mouseCommands.IUndoable;


public interface IShapeAll 	extends IShapeCommand,
									IUndoable,
									IShapeSelected,
									IShapeMoveObserver
{

	public int getStartX();
	public int getStartY();
	public int getWidth();
	public int getHeight();
	public Color getPrimColor();
	public Color getSecColor();
	public String getShadeType();
	public boolean equals(IShapeAll shape);
	public PaintCanvasBase getBase();
}
