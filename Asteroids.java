
/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class Asteroids extends Game {
	private static int counter = 0;
  private static Point[] shipPoints = {new Point(0,0), new Point(50,25), new Point(25, 0), new Point(50, -25)};
  private static Point shipPosition = new Point(400, 300);
  private static double shipRotation = 0;
  private static Ship s = new Ship(shipPoints, shipPosition, shipRotation, 800.0, 600.0);
  private static Point[] asteroidPoints = {new Point(0,0), new Point(50,0), new Point(50,50), new Point(0,50)};
  private static Point asteroidPosition1 = new Point(800,600);
  private static Point asteroidPosition2 = new Point(800,0);
  private static Point asteroidPosition3 = new Point(0,600);
  private static Point asteroidPosition4 = new Point(0,0);
  private static Point asteroidPosition5 = new Point(0,300);
  private static Point asteroidPosition6 = new Point(800,300);
  private static Point asteroidPosition7 = new Point(-20,0);
  private static double asteroidRotation = 0;
  private static Point[] shooterPoints = {new Point(0,0), new Point(10,0), new Point(10,5), new Point(0,5)};
  private static ArrayList<Shooter> weapons = new ArrayList<Shooter>();
  private Random r = new Random();
  public int mainTheme = 0;

  KeyListener listener = new KeyListener(){
    
    public void keyPressed(KeyEvent e) {
      s.keyPressed(e);
      int key = e.getKeyCode();
      if (key == 32) {
        Point pos = s.hereIsPoint().clone();
        double rot = s.getRotation();
        weapons.add(new Shooter(shooterPoints, pos, rot));
      }
    }

    public void keyReleased(KeyEvent e) {
      s.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {}
  };

  private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

  public Asteroids() {
    super("Asteroids!",800,600);
    this.addKeyListener(listener);
    this.setFocusable(true);
	  this.requestFocus();
    for (int x=0; x<18; x++) {
      if (x<3) {
          asteroids.add(new Asteroid(asteroidPoints, asteroidPosition1, asteroidRotation));
      } else if (x>2&&x<6) {
        asteroids.add(new Asteroid(asteroidPoints, asteroidPosition2, asteroidRotation));
      } else if (x>5&&x<9) {
        asteroids.add(new Asteroid(asteroidPoints, asteroidPosition3, asteroidRotation));
      } else if (x>8&&x<12) {
          asteroids.add(new Asteroid(asteroidPoints, asteroidPosition4, asteroidRotation));
      } else if (x>11&&x<15) {
          asteroids.add(new Asteroid(asteroidPoints, asteroidPosition5, asteroidRotation));
      } else {
          asteroids.add(new Asteroid(asteroidPoints, asteroidPosition6, asteroidRotation));
      }
    }
  }

  private int lives = 120;

	public void paint(Graphics brush) {
    if (counter % 1000 == 0 && counter > 0) {
      for(int i = 0; i < 3; i++) {
        asteroids.add(new Asteroid(asteroidPoints, asteroidPosition7, asteroidRotation));
      }
    }
    if (counter > 6000) {
        System.exit(0);
    }
    if(lives <= 0) {
      brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
      brush.setColor(Color.red);
      brush.setFont(new Font("Serif", Font.PLAIN, 24));
      brush.drawString("You died :)", 350, 300);
    } else if(counter >= 5000){
      brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
      brush.setColor(Color.green);
      brush.setFont(new Font("Serif", Font.PLAIN, 24));  
      brush.drawString("You won :(", 350, 300);
    }
    else {
      s.move();
      if (s.checkEdge()) {
        mainTheme = r.nextInt(5);
        s.setTheme(mainTheme);
      }
      for (Shooter weapon: weapons) {
          weapon.move();
          weapon.setTheme(mainTheme);
      }
      for(Asteroid a : asteroids) {
        a.move();
        a.checkEdge();
        a.setTheme(mainTheme);
        if(a.collides(s)) {
          lives--;
        }
      }
      for(Shooter weapon : weapons) {
        for(int i = 0; i < asteroids.size(); i += 3) {
          if(asteroids.get(i).collides(weapon)) {
            int x = r.nextInt(801);
            int y = r.nextInt(601);
            Point asteroidPosition = new Point(x,y);
            asteroids.set(i, new Asteroid(asteroidPoints, asteroidPosition, asteroidRotation));
            asteroids.set(i + 1, new Asteroid(asteroidPoints, asteroidPosition, asteroidRotation));
            asteroids.set(i + 2, new Asteroid(asteroidPoints, asteroidPosition, asteroidRotation));
          }
        }
      }
      if(mainTheme == 0) {
        brush.setColor(Color.black);
      }
      else if(mainTheme == 1) {
        brush.setColor(Color.white);
      }
      else if(mainTheme == 2) {
        brush.setColor(Color.green);
      }
      else if(mainTheme == 3) {
        brush.setColor(Color.black);
      }
      else if(mainTheme == 4) {
        brush.setColor(Color.blue);
      }
      brush.fillRect(0,0,width,height);
      s.paint(brush);
      for (Shooter weapon : weapons) {
          weapon.paint(brush);
      }
      for (Asteroid a : asteroids) {
        a.paint(brush);
      }  	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
      if(mainTheme == 0) {
        brush.setColor(Color.white);
      }
      else if(mainTheme == 1) {
        brush.setColor(Color.black);
      }
      else if(mainTheme == 2) {
        brush.setColor(Color.red);
      }
      else if(mainTheme == 3) {
        brush.setColor(Color.yellow);
      }
      else if(mainTheme == 4) {
        brush.setColor(Color.black);
      }
      brush.drawString("Counter is " + counter,10,10);
      brush.drawString("Lives: " + lives,200,10);
    }
  }
}