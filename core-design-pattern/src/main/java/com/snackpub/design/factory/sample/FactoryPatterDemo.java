package com.snackpub.design.factory.sample;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public class FactoryPatterDemo {


    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactory();
        Vehicle bus = factory.getVehicle("bus");
        bus.dn();
        bus.up();

        Vehicle bike = factory.getVehicle("bike");
        bike.dn();
        bike.up();

        Vehicle car = factory.getVehicle("car");
        car.dn();
        car.up();



    }
}
