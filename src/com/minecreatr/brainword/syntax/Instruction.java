package com.minecreatr.brainword.syntax;

public enum Instruction {
    INCREMENT_POINTER('>'){

        @Override
        public void doOperation(BrainContext ctx, int index){
            ctx.incrementPointer();
            //System.out.println("Incrementing pointer at index "+ctx.getLocation()+" and value "+ctx.getValue());
        }
    },
    DECREMENT_POINTER('<'){

        @Override
        public void doOperation(BrainContext ctx, int index){
            ctx.decrementPointer();
            //System.out.println("Decrementing pointer at index "+ctx.getLocation()+" and value "+ctx.getValue());
        }
    },
    INCREMENT_VALUE('+'){

        @Override
        public void doOperation(BrainContext ctx, int index){
            ctx.incrementValue();
            //System.out.println("Incrementing value at index "+ctx.getLocation()+" and value "+ctx.getValue());
        }
    },
    DECREMENT_VALUE('-'){

        @Override
        public void doOperation(BrainContext ctx, int index){
            ctx.decrementValue();
            //System.out.println("Decrementing value at index "+ctx.getLocation()+" and value "+ctx.getValue());
        }
    },
    LOOP_START('['){

        @Override
        public void doOperation(BrainContext ctx, int index){
            //System.out.println("Starting loop at index "+ctx.getLocation()+" and value "+ctx.getValue());
            if (ctx.currentValueNonzero()){
                ctx.startLoopAt(index+1);
            }
            else {
                ctx.getExecuteManager().seekLoopEnd();
            }

        }
    },
    LOOP_END(']'){

        @Override
        public void doOperation(BrainContext ctx, int index){
            if (ctx.currentValueNonzero()){
                //System.out.println("Relooping at index "+ctx.getLocation()+" and value "+ctx.getValue());
                ctx.getExecuteManager().setIndex(ctx.getCurrentLoopBeginning());
            }
            else {
                //System.out.println("Ending loop at index "+ctx.getLocation()+" and value "+ctx.getValue());
                ctx.popLoop();
            }

        }
    },
    PRINT_VALUE('.'){

        @Override
        public void doOperation(BrainContext ctx, int index){
            ctx.getIo().print(ctx.getValue());
        }
    },
    READ_VALUE(','){

        @Override
        public void doOperation(BrainContext ctx, int index){
            ctx.setValue(ctx.getIo().readChar());
        }
    }

    ;

    private char c;

    Instruction(char c){
        this.c = c;
    }

    public char getChar(){
        return this.c;
    }

    public abstract void doOperation(BrainContext ctx, int index);

    public static Instruction fromChar(char c){
        for (Instruction inst : values()){
            if (inst.getChar() == c){
                return inst;
            }
        }
        return null;
    }

}
