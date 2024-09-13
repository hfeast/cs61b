public class NBody {
    public static double readRadius(String filepath) {
        In in = new In(filepath);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius; 

    }
    
    public static Planet[] readPlanets(String filepath) {
        In in = new In(filepath);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[5];
        for(int i = 0; i < 5; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, image);
        
        }
        return planets;
    }

    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();

        double time = 0;
        int num = planets.length;
        while (time < T) {
            double xForces[] = new double[num];
            double yForces[] = new double[num];
            for(int i = 0; i < num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            
            for(Planet planet : planets){
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.println(planets.length);
        StdOut.printf("%.2e\n", radius);
        for(int i = 0; i < planets.length; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %11s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }








    }
    
}
