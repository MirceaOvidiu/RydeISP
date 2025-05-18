public enum Statii {
    Politehnica("Politehnica"),
    Eroilor("Eroilor"),
    Grozavesti("Grozavesti"),
    Izvor("Izvor");

    private final String stationName;

    Statii(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }
}
