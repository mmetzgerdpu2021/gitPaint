package shapeStuff;

public interface IShapeMoveObserver {
	
	public void update(int xMoved, int yMoved);
	
	public IShapeAll getParent();
}
