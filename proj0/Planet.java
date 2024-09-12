public class Planet {
    
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet (double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet (Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;

    }

    public double calcDistance (Planet b) {
        
        double x_square = (this.xxPos - b.xxPos) * (this.xxPos - b.xxPos);
        double y_square = (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos);
        return Math.sqrt(x_square + y_square);
    }

    public double calcForceExertedBy (Planet p) {
        return (G * this.mass * p.mass) / (calcDistance(p) * calcDistance(p)); 

    }

    public double calcForceExertedByX (Planet p) {
        double dx = p.xxPos - this.xxPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dx / r;
    }

    public double calcForceExertedByY (Planet p) {
        double dy = p.yyPos - this.yyPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dy / r;
    }

    public double calcNetForceExertedByX (Planet[] allPlanets) {
        double netforce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            netforce += calcForceExertedByX(planet);
        }
        return netforce;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets) {
        double netforce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            netforce += calcForceExertedByY(planet);
        }
        return netforce;
    }

    public void update (double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;

    }

    

    
}