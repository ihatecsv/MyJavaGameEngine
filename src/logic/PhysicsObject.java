package logic;

// TODO rework drag, let it adjust slower

public class PhysicsObject {
    public double xVelocity;
    public double yVelocity;
    public double x;
    public double y;
    public Physics physics;
    public String objName;

    public PhysicsObject(double x, double y, double xVelocity, double yVelocity, String objName){
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.x = x;
        this.y = y;
        this.objName = objName;
        changePhysics();
    }

    public void changePhysics(Physics newPhysics){
        this.physics = newPhysics;
    }
    public void changePhysics(){
        this.physics = new Physics(0,0,0); // Null Physics
    }
    
    public void simulate(double time){
        y = (yVelocity * time) + (0.5*physics.yPull*time*time);
        yVelocity = yVelocity + physics.yPull * time;
        if (yVelocity>dragCoeff){
            yVelocity = yVelocity-((dragCoeff-yVelocity)*time) // yVelocity returns to terminal velocity in 1s
        }
        System.out.println(objName+ " : " y + " / " + yVelocity);
        
        x = (xVelocity * time) + (0.5*physics.xPull*time*time);
        xVelocity = xVelocity + physics.xPull * time;
        if (xVelocity>dragCoeff){
            xVelocity = xVelocity-((dragCoeff-xVelocity)*time) // xVelocity returns to terminal velocity in 1s
        }
        System.out.println(objName+ " : " x + " / " + xVelocity);
    }
}
