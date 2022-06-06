import java.lang.Math; 

public class Planet {
	public double xxPos; // Its current x position
	public double yyPos; // Its current y position
	public double xxVel; // Its current velocity in the x direction
	public double yyVel; // Its current velocity in the y direction
	public double mass; // Its mass
	public String imgFileName; // The name of the file that corresponds to the image that depicts the Planet 

	private static final double G = 6.67e-11;	//科学计数法e，常量

	/* constructor 1 with 6 instance */
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	/* constructor 2 with Planet instance */
	public Planet(Planet b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}


	/* calculate the distance between Planet b and me */
	public double calcDistance(Planet b) {
		double dx = b.xxPos - xxPos;
		double dy = b.yyPos - yyPos;
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}


	/* calculate the force exerted on me by the given Planet b */
	public double calcForceExertedBy(Planet b) {
		return (G * mass * b.mass) / Math.pow(calcDistance(b), 2);
	}


	/* calculate the force exerted on me in the X direction by the given Planet b */
	public double calcForceExertedByX(Planet b) {
		double dx = b.xxPos - xxPos;
		return calcForceExertedBy(b) * dx / calcDistance(b);
	}


	/* calculate the force exerted on me in the Y direction by the given Planet b */
	public double calcForceExertedByY(Planet b) {
		double dy = b.yyPos - yyPos;
		return calcForceExertedBy(b) * dy / calcDistance(b);
	}


	/* calculate the force exerted on me in the X direction by the given Planets(excepet myself) */
	public double calcNetForceExertedByX(Planet[] Planets) {
		double Fx = 0.0;
		for (Planet b : Planets) {
			if (!this.equals(b)) {
				Fx += calcForceExertedByX(b);
			}	
		}
		return Fx;
	}


	/* calculate the force exerted on me in the Y direction by the given Planets(excepet myself) */
	public double calcNetForceExertedByY(Planet[] Planets) {
		double Fy = 0.0;
		for (Planet b : Planets) {
			if (!this.equals(b)) {
				Fy += calcForceExertedByY(b);
			}
		}
		return Fy;
	}


	/* due to fX,fY forces in dt period of time, update my position and velocity instance variables */
	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}


	/* draw the Planet’s image at the Planet’s position */
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}