package com.snackpub.core.typeinfo;

import com.snackpub.core.typeinfo.factory.Factory;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author snackpub
 * @date 2021/5/5
 */

class Part {
    public String toString() {
        return getClass().getSimpleName();
    }

    static ArrayList<Factory<? extends Part>> partFactories = new ArrayList<>();
    static ArrayList<Class<? extends Part>> partFactories2 = new ArrayList<>();


    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CabinAirFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());

        partFactories2.add(FuelFilter.class);
        partFactories2.add(AirFilter.class);
        partFactories2.add(CabinAirFilter.class);
        partFactories2.add(OilFilter.class);
        partFactories2.add(FanBelt.class);
        partFactories2.add(PowerSteeringBelt.class);
        partFactories2.add(GeneratorBelt.class);


    }

    private static Random rand = new Random(47);

    public static Part createRandom() throws IllegalAccessException, InstantiationException {
        int n = rand.nextInt(partFactories.size());
//        return partFactories.get(n).create();
        return partFactories2.get(n).newInstance();
    }
}

class Filter extends Part {
}

class FuelFilter extends Filter {
    public static class Factory
            implements com.snackpub.core.typeinfo.factory.Factory<FuelFilter> {
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}


class AirFilter extends Filter {
    public static class Factory
            implements com.snackpub.core.typeinfo.factory.Factory<AirFilter> {
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class CabinAirFilter extends Filter {
    public static class Factory
            implements com.snackpub.core.typeinfo.factory.Factory<CabinAirFilter> {
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}

class OilFilter extends Filter {
    public static class Factory
            implements com.snackpub.core.typeinfo.factory.Factory<OilFilter> {
        public OilFilter create() {
            return new OilFilter();
        }
    }
}

class Belt extends Part {
}

class FanBelt extends Belt {
    public static class Factory
            implements com.snackpub.core.typeinfo.factory.Factory<FanBelt> {
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

class GeneratorBelt extends Belt {
    public static class Factory
            implements com.snackpub.core.typeinfo.factory.Factory<GeneratorBelt> {
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}

class PowerSteeringBelt extends Belt {
    public static class Factory
            implements com.snackpub.core.typeinfo.factory.Factory<PowerSteeringBelt> {
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}

public class RegisteredFactories {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        for (int i = 0; i < 10; i++)
            System.out.println(Part.createRandom());
    }
}
