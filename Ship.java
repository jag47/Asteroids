import java.awt.*;
import java.awt.event.KeyEvent;

public class Ship extends Polygon {

  private double width;
  private double height;
  private boolean forward = false;
  private boolean left = false;
  private boolean right = false;
  private boolean backwards = false;
  private final int amountToMove = 2;
  private final int amountToRotate = 2;
  

  public Ship (Point[] Shape, Point inPosition, double inRotation) {
    super(Shape, inPosition, inRotation);
  }

  public Ship(Point[] Shape, Point inPosition, double inRotation, double screenWidth, double screenHeight) {
    super(Shape, inPosition, inRotation);
    this.width = screenWidth;
    this.height = screenHeight;
  }
  
  public void paint(Graphics brush) {
    if(theme == 0) {
        brush.setColor(Color.red);
    } else if(theme == 1) {
        brush.setColor(Color.green);
    } else if(theme == 2) {
        brush.setColor(Color.blue);
    } else if(theme == 3) {
        brush.setColor(Color.pink);
    } else if(theme == 4) {
        brush.setColor(Color.cyan);
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
    if (forward) {
      this.position.setX(this.position.getX() - amountToMove * Math.cos(Math.toRadians(rotation)));
      this.position.setY(this.position.getY() - amountToMove * Math.sin(Math.toRadians(rotation)));
    }
    if (right) {
      rotate(amountToRotate);
    }
    if (left) {
      rotate(-amountToRotate);
    } if (backwards) {
      this.position.setX(this.position.getX() + amountToMove/2 * Math.cos(Math.toRadians(rotation)));
      this.position.setY(this.position.getY() + amountToMove/2 * Math.sin(Math.toRadians(rotation)));
    }
  }

  public boolean checkEdge() {
    if (position.getX() < 0) {
        position.setX(800.0);
        return true;
      } else if (position.getY() > 600) {
        position.setY(0);
        return true;
      } else if(position.getX() > 800) {
        position.setX(0);
        return true;
      } else if(position.getY() < 0) {
        position.setY(600);
        return true;
      }
      return false;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == 38 || key == 87) {
      forward = true;
    } else if (key == 39 || key == 68) {
      right = true;
    } else if (key == 37 || key == 65) {
      left = true;
    } else if (key == 40 || key == 83) {
      backwards = true;
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == 38 || key == 87) {
      forward = false;
    } else if (key == 39 || key == 68) {
      right = false;
    } else if (key == 37 || key == 65) {
      left = false;
    } else if (key == 40 || key == 83) {
      backwards = false;
    }
  }

  public double getRotation() {
    return rotation;
  }

  public Point hereIsPoint() {
    return position;
  }
}