package se.oskare.system;

import java.util.List;

public class System {
    public String id;
    public String name;
    public int X;
    public int Y;
    public Asteroid asteroid;
    public Station station;
    public List<NeighboringSystem> neighboringSystems;

    public static class Station {
        public boolean directSell;
        public boolean buyShips;
    }

    public static class Asteroid {
        public String miningResourceId;
    }

    public static class NeighboringSystem {
        public String systemId;
    }
}



