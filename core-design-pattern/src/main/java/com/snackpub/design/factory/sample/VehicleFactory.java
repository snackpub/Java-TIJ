package com.snackpub.design.factory.sample;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public class VehicleFactory {


    /**
     * 通过汽车类型获取车对象
     *
     * @param vehicleType 类型
     * @return vehice
     */
    public Vehicle getVehicle(String vehicleType) {

        if (null == vehicleType) {
            return null;
        } else if (vehicleType.equalsIgnoreCase("BUS")) {
            return new Bus();
        } else if (vehicleType.equalsIgnoreCase("CAR")) {
            return new Car();
        } else if (vehicleType.equalsIgnoreCase("BIKE")) {
            return new Bike();
        }
        return null;
    }
}
