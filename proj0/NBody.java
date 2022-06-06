public class NBody {
	/* return corresponding to the radius of the universe in that file */
	public static double readRadius(String file) {
		In in = new In(file);
		in.readInt(); // 读取总数
		double radius = in.readDouble();
		return radius;
	}


	/* return an array of Planet s corresponding to the bodies in the file */
	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int N = in.readInt();
		Planet[] planets = new Planet[N];
		in.readDouble();			//读取universe radius
		for (int i = 0; i < N; i ++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return planets;
	}


	/* draw the background */
	private static void drawBackground(double radius) {
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");		
	}


	/* draw all of the planents */
	private static void drawPlanets(Planet[] planets) {
		for (Planet planet : planets) {
			planet.draw();
		}
	}
	

	/* print out the final state of the universe */
	private static void printUniverse(Planet[] planets, double radius) {
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}		
	}


	/* Create an Animation for the universe */
	private static void createAnimatation(Planet[] planets, double radius, double dt, double T) {
		StdDraw.enableDoubleBuffering();
		double time = 0.0;
		int N = planets.length;
		while (time != T) {
			double[] xForces = new double[N];
			double[] yForces = new double[N];

			/* calculate x,y Forces to each planet */
			for (int i = 0; i < N; i ++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);			
			}	

			/* update each planet pos, v, a */
			for (int i = 0; i < N; i ++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			/* draw the background */
			drawBackground(radius);

			/* Draw All of the Planets */
			drawPlanets(planets);
			
			//Show the offscreen buffer (see the show method of StdDraw).
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}
	}


	public static void main(String[] args) {
		/* read from files */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/* Create an Animation */
		createAnimatation(planets, radius, dt, T);

		/* Print the Universe */
		printUniverse(planets, radius);

	} 
}