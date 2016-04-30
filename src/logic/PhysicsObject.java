package logic;

public class PhysicsObject {
    public double xVelocity;
    public double yVelocity;
    public double x;
    public double y;
    public double rot;

    public PhysicsObject(double x, double y, double xVelocity, double yVelocity, double rot){
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.x = x;
        this.y = y;
        this.rot = rot;
    }

    public void simulate(double time){
        y = (yVelocity * time) + (0.5*Physics.GRAVITY*time*time);
        yVelocity = yVelocity + Physics.GRAVITY * time;
        System.out.println(y + "/" + yVelocity);
    }
}
