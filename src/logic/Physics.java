package logic;

public class Physics {
    /*
    *   So this is gonna break things. To use this class, you need a class that creates several (or one, depending on the game) of these zones.
    *   This class also needs to track ALL objects in the game world. If an object is in a certain "physics" zone, it acts based on that zone's
    *   properties.
    *   TODO: add exception handlers if physics zones overlap
    */
    
    public void Physics(double yPull, double xPull, double xSize, double ySize, double xZoneCenter, double yZoneCenter, double dragCoeff){
        //Define "Gravity Laws"
        this.yPull = yPull;
        this.xPull = xPull;
        //Define bounds of the area governed by the given physics laws
        this.yUpperBound = yZoneCenter+ySize;
        this.yLowerBound = yZoneCenter-ySize;
        this.xRightBound = xZoneCenter+xSize;
        this.xLeftBound = xZoneCenter-xSize;
        //Define drag in area
        this.dragCoeff = dragCoeff
    }
    
    public void Physics(double yPull, double xPull, double dragCoeff){
        //Define "Gravity Laws"
        this.yPull = yPull;
        this.xPull = xPull;
        //Define drag in area
        this.dragCoeff = dragCoeff
    }
    
    
    
    //HERE'S AN IDEA. Make each object have a Physics() object associated with it, which it uses for its behavior.
    //The issue with that is that it needs exception handlers for if it leaves a physics zone. Maybe make it default to a zone with 0 physics.
    //The other issue is if advanced 2D gravity is needed, i.e. gravity towards a point, rather than on a plane. Perhaps an AdvPhysics() class?
}
