package logic;

import java.util.ArrayList;

public class PhysicsDomain {
    public ArrayList<PhysicsEntity> physObjArray;

    public PhysicsDomain(){
        this.physObjArray = new ArrayList<PhysicsEntity>();
    }

    public void add(PhysicsEntity obj){
        physObjArray.add(obj);
    }

    public void simulate(double time){
        for(PhysicsEntity obj : physObjArray){
                obj.simulate(time);
        }
    }
    //TODO: Rewrite rendering of physics domains
    /*
    public Bitmap render(){
        Bitmap rBMP = new Bitmap(width, height);
        for(PhysicsEntity obj : physObjArray){
            rBMP.add(Sprites.rect(20, 20, new Bit(255, 0, 255)), (int)obj.x, (int)obj.y);
        }
        return rBMP;
    }
    */
}
