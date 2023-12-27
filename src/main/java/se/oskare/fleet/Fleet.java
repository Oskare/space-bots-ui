package se.oskare.fleet;

public class Fleet {
    public String id;
    public String locationSystemId;
    public Object currentAction;
    public Cargo cargo;
    public Object ships;

    public static class Cargo {
        public Integer aluminium;
        public Integer zinc;
        public Integer titanium;
        public Integer zirconium;
        public Integer mithril;
    }
}
