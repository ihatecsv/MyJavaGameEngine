package logic;

import render.Bit;
import render.Bitmap;
import render.Sprites;

import java.util.ArrayList;

public class PhysicsDomain {
    public ArrayList<PhysicsObject> physObjArray;

    public PhysicsDomain(){
        this.physObjArray = new ArrayList<PhysicsObject>();
    }

    public void add(PhysicsObject obj){
        physObjArray.add(obj);
    }

    public void simulate(double time){
        for(PhysicsObject obj : physObjArray){
                obj.simulate(time);
        }
    }
    //TODO: Rewrite rendering of physics domains
    /*
    public Bitmap render(){
        Bitmap rBMP = new Bitmap(width, height);
        for(PhysicsObject obj : physObjArray){
            rBMP.add(Sprites.rect(20, 20, new Bit(255, 0, 255)), (int)obj.x, (int)obj.y);
        }
        return rBMP;
    }
    */
}
