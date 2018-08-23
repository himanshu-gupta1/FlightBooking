package e.wolfsoft1.tickets.ModelClasses;

public class BookingModel {
    private int imageId;
    private String flightName;
    private int flightNo;
    private String dTime;
    private String aTime;
    private String source;
    private int duration;
    private String destination;
    private int price;

    public BookingModel(int imageId, String flightName, int flightNo, String dTime, String aTime, String source, int duration, String destination, int price) {
        this.imageId = imageId;
        this.flightName = flightName;
        this.flightNo = flightNo;
        this.dTime = dTime;
        this.aTime = aTime;
        this.source = source;
        this.duration = duration;
        this.destination = destination;
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
