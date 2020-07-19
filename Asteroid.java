import java.awt.*;
import java.util.Random;

class Asteroid extends Polygon {

  private Random r = new Random();
  private final int amountToMove = 2;
  private final int amountToRotate = 2;

  public Asteroid(Point[] Shape, Point inPosition, double inRotation) {
    super(Shape, inPosition, inRotation);
  }


  public void paint(Graphics brush) {
    if(theme == 0) {
        brush.setColor(Color.blue);
    } else if(theme == 1) {
        brush.setColor(Color.orange);
    } else if(theme == 2) {
        brush.setColor(Color.magenta);
    } else if(theme == 3) {
        brush.setColor(Color.red);
    } else if(theme == 4) {
        brush.setColor(Color.green);
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
    int x = r.nextInt(3);
    if (x == 0) {
      this.position.setX(this.position.getX() - amountToMove * Math.cos(Math.toRadians(rotation)));
      this.position.setY(this.position.getY() - amountToMove * Math.sin(Math.toRadians(rotation)));
    }
    if (x == 1) {
      rotate(amountToRotate);
    }
    if (x == 2) {
      rotate(-amountToRotate);
    }
  }
  
  public void checkEdge() {
    if (position.getX() < 0) {
        position.setX(800.0);
      } else if (position.getY() > 600) {
        position.setY(0);
      } else if(position.getX() > 800) {
        position.setX(0);
      } else if(position.getY() < 0) {
        position.setY(600);
      }
  }
}