package com.minecreatr.brainword.syntax;

public class BrainContext {

    private byte[] data;

    private int location;

    private int depth;

    private int[] loopBeginnings;

    private IO io;

    private ExecuteManager manager;

    public BrainContext(ExecuteManager manager){
        this.manager = manager;
        this.data = new byte[30000];
        this.location = 0;
        this.depth = 0;
        this.loopBeginnings = new int[1000];
        this.io = new IO();
    }

//    public int getLocation(){
//        return this.location;
//    }

    public void incrementPointer(){
        this.location++;
    }

    public void decrementPointer(){
        if (this.location > 0){
            this.location--;
        }
    }

    public byte getValue(){
        return this.data[this.location];
    }

    public int getLocation(){
        return this.location;
    }

    public void incrementValue(){
        (this.data[this.location])++;
    }

    public void setValue(byte value){
        (this.data[this.location]) = value;
    }

    public void decrementValue(){
        (this.data[this.location])--;
    }

    public boolean currentValueNonzero(){
        return getValue() <= 0;
    }

    public IO getIo(){
        return this.io;
    }

    public void startLoopAt(int loopLocation){
        this.depth++;
        loopBeginnings[this.depth] = loopLocation;
    }

    public void popLoop(){
        this.loopBeginnings[this.depth] = 0;
        this.depth--;
    }

    public int getCurrentLoopBeginning(){
        return this.loopBeginnings[this.depth];
    }

    public ExecuteManager getExecuteManager(){
        return this.manager;
    }

}
