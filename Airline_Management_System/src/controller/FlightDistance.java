package controller;

public abstract class FlightDistance {
	
	public abstract String toString(int i);

    public abstract String[] calculateDistance(double lat1, double lon1, double lat2, double lon2);

}

