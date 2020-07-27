package com.snackpub.design.factory.sample2;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public  abstract class  Computer {
    public abstract String getRAM();
    public abstract String getHDD();
    public abstract String getCPU();

    @Override
    public String toString(){
        return "RAM= "+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
    }

}
