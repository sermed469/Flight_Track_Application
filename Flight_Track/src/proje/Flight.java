package proje;

public class Flight {
	
	private int flightnumber;
	private String departure;
	private String arrival;
	private String departuretime;
	private String arrivaltime;
	private String weekday;
	private String airline;
	private String aircraft;
	private String flightstatus;
	
	public Flight( int flightnumber, String departure, String arrival, String departuretime, String arrivaltime, String weekday, String Airline, String Aircraft) {
		
		this.departure = departure;
		this.arrival = arrival;
		this.weekday = weekday;
		this.departuretime = departuretime;
		this.arrivaltime = arrivaltime;
		this.aircraft = Aircraft;
		this.flightnumber = flightnumber;
		this.airline = Airline;
		this.flightstatus = "did not started";
	}
	
	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public int getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(int flightnumber) {
		this.flightnumber = flightnumber;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	public String getFlightstatus() {
		return flightstatus;
	}

	public void setFlightstatus(String flightstatus) {
		this.flightstatus = flightstatus;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	
} 
