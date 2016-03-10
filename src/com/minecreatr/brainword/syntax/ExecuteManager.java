package com.minecreatr.brainword.syntax;

public class ExecuteManager {

    private int index;

    private int amountSeeked;

    private BrainContext context;

    private String code;

    public ExecuteManager(String code){
        this.code = code;
        this.index = 0;
        this.amountSeeked = 0;
        this.context = new BrainContext(this);
    }

    public boolean tick(){
        if (index >= code.length()){
            return false;
        }
        if (this.amountSeeked != 0){
            if (code.charAt(this.index) == ']'){
                System.out.println("Skipping");
                this.amountSeeked--;
            }
        }
        else {
            char c = code.charAt(this.index);
            Instruction inst = Instruction.fromChar(c);
            if (inst != null){
                inst.doOperation(context, this.index);
            }
        }
        this.index++;
        return true;
    }

    public int getIndex(){
        return this.index;
    }

    public void skipAmount(int amount){
        this.index += amount;
    }

    public void setIndex(int index){
        this.index = index;
    }


    public void seekLoopEnd(){
        this.amountSeeked++;
    }

}
