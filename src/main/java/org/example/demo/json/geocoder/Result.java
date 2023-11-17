package org.example.demo.json.geocoder;

public class Result {
    private String formattedAddress;
    private Geometry geometry;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "Response{" +
                "formattedAddress='" + formattedAddress + '\'' +
                ", geometry=" + geometry +
                '}';
    }
}
