package shapeStuff;

import java.awt.Color;
import java.util.LinkedList;

import mouseCommands.DrawnShapesList;
import selectionAndMoveStuff.canvasRefresh;
import selectionAndMoveStuff.moveSubject;
import view.interfaces.PaintCanvasBase;

public class GroupCommand implements IShapeAll {
	
	private LinkedList<IShapeAll> groupedShapes;
	private LinkedList<IShapeAll> backUpShapes;
	int startX = -1;
	int startY = -1;
	int height = -1;
	int width = -1;

	public GroupCommand()
	{
		groupedShapes = new LinkedList<IShapeAll>();
		backUpShapes = new LinkedList<IShapeAll>();
	}
	
	public void add (IShapeAll shape)
	{
		groupedShapes.add(shape);
	}
	
	public void run()
	{
		for (IShapeAll shape : groupedShapes)
			shape.run();
	}

	@Override
	public void undo()
	{
		for (IShapeAll shape : groupedShapes)
		{
			backUpShapes.add(shape);
			DrawnShapesList.DrawnShapes.add(shape);
			moveSubject.selectedShapes.add(shape);
		}
		DrawnShapesList.DrawnShapes.removeFirstOccurrence(this);
		moveSubject.selectedShapes.removeFirstOccurrence(this);
		groupedShapes.clear();
		startX = -1;
		startY = -1;
		height = -1;
		width = -1;
		PaintCanvasBase tempBase = backUpShapes.getFirst().getBase();
		if (tempBase != null) canvasRefresh.refresh(tempBase);
	}

	@Override
	public void redo()
	{
		for (IShapeAll shape : backUpShapes)
		{
			if (DrawnShapesList.DrawnShapes.contains(shape))
			{
				DrawnShapesList.DrawnShapes.removeFirstOccurrence(shape);
				groupedShapes.add(shape);
			}
			if (moveSubject.selectedShapes.contains(shape))
				moveSubject.selectedShapes.removeFirstOccurrence(shape);
		}
		backUpShapes.clear();
		DrawnShapesList.DrawnShapes.add(this);
		moveSubject.selectedShapes.add(this);
		PaintCanvasBase tempBase = groupedShapes.getFirst().getBase();
		if (tempBase != null) canvasRefresh.refresh(tempBase);
	}

	@Override
	public void update(int xMoved, int yMoved)
	{
		for (IShapeAll shape : groupedShapes)
			shape.update(xMoved, yMoved);
	}

	@Override
	public IShapeAll getParent()
	{
		if (! groupedShapes.isEmpty()) 
			return groupedShapes.getFirst().getParent();
		return null;
	}

	@Override
	public int getStartX()
	{
		if (startX < 0 && ! groupedShapes.isEmpty())
		{
			startX = groupedShapes.getFirst().getStartX();
			int tempX;
			for (IShapeAll shape : groupedShapes)
			{
				tempX = shape.getStartX();
				if (tempX < startX) startX = tempX;
			}
		}
		return startX;
	}

	@Override
	public int getStartY()
	{
		if (startY < 0 && ! groupedShapes.isEmpty())
		{
			startY = groupedShapes.getFirst().getStartY();
			int tempY;
			for (IShapeAll shape : groupedShapes)
			{
				tempY = shape.getStartY();
				if (tempY < startY) startY = tempY;
			}
		}
		return startY;
	}

	@Override
	public int getWidth()
	{
		if (width < 0 && ! groupedShapes.isEmpty())
		{
			width = groupedShapes.getFirst().getWidth();
			int tempX = startX;
			int furthestX = startX;
			IShapeAll furthestShape = groupedShapes.getFirst();
			for (IShapeAll shape : groupedShapes)
			{
				tempX = shape.getStartX();
				if (tempX > furthestX)
				{
					furthestX = tempX;
					furthestShape = shape;
				}
			}
			width = (furthestX - startX) + furthestShape.getWidth();
		}
		return width;
	}

	@Override
	public int getHeight()
	{
		if (height < 0 && ! groupedShapes.isEmpty())
		{
			height = groupedShapes.getFirst().getHeight();
			int tempY = startY;
			int furthestY = startY;
			IShapeAll furthestShape = groupedShapes.getFirst();
			for (IShapeAll shape : groupedShapes)
			{
				tempY = shape.getStartY();
				if (tempY > furthestY)
				{
					furthestY = tempY;
					furthestShape = shape;
				}
			}
			height = (furthestY - startY) + furthestShape.getHeight();
		}
		return height;
	}

	@Override
	public Color getPrimColor()
	{
		if (! groupedShapes.isEmpty()) 
			return groupedShapes.getFirst().getPrimColor();
		return null;
	}

	@Override
	public Color getSecColor()
	{
		if (! groupedShapes.isEmpty()) 
			return groupedShapes.getFirst().getSecColor();
		return null;
	}

	@Override
	public String getShadeType()
	{
		if (! groupedShapes.isEmpty()) 
			return groupedShapes.getFirst().getShadeType();
		return null;
	}

	@Override
	public boolean equals(IShapeAll shape) {
		if (!(shape instanceof GroupCommand)) return false;
		GroupCommand tempShape = (GroupCommand) shape;
		return groupedShapes.equals(tempShape.getGroup());
	}

	@Override
	public PaintCanvasBase getBase()
	{
		if (! groupedShapes.isEmpty()) 
			return groupedShapes.getFirst().getBase();
		return null;
	}
	
	public LinkedList<IShapeAll> getGroup()
	{
		return groupedShapes;
	}
	
	public void outline()
	{
		for (IShapeAll shape : groupedShapes)
			shape.outline();
	}

}
