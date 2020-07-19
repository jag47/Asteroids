import java.awt.*;

class Shooter extends Polygon {

  private int amountToMove = 2;

  public Shooter(Point[] Shape, Point inPosition, double inRotation) {
    super(Shape, inPosition, inRotation);
  }

  public void paint(Graphics brush) {
    if(theme == 0) {
        brush.setColor(Color.green);
    } else if(theme == 1) {
        brush.setColor(Color.red);
    } else if(theme == 2) {
        brush.setColor(Color.yellow);
    } else if(theme == 3) {
        brush.setColor(Color.orange);
    } else if(theme == 4) {
        brush.setColor(Color.magenta);
    }
    Point[] points = this.getPoints();
    int[] x = new int[points.length];
    int[] y = new int[points.length];
    for(int i = 0; i < points.length; i++) {
      x[i] = (int) points[i].getX();
      y[i] = (int) points[i].getY();
    }
    brush.fillPolygon(x, y, x.length);
  }

  public void move() {
    this.position.setX(this.position.getX() - amountToMove * Math.cos(Math.toRadians(rotation)));
    this.position.setY(this.position.getY() - amountToMove * Math.sin(Math.toRadians(rotation)));
  }

  public Point getPosition(){
    return position;
  }
}