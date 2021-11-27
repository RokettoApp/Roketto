package it.rokettoapp.roketto.spaceEvents;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpaceEvents {
    private String title;
    private String description;
    private Date departureDate;

    public SpaceEvents(String title, String description, Date departureDate) {
        this.title = title;
        this.description = description;
        this.departureDate = departureDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartureDate() {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);

        String dateString = df.format(departureDate);
        return dateString;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "spaceEvents{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", departureDate=" + departureDate +
                '}';
    }
}
