package com.cashmanagement.vitalyevich.server.firebase.model;

import com.google.api.client.util.DateTime;
import com.google.cloud.Date;
import com.google.cloud.Timestamp;
import lombok.Data;

@Data
public class WorkTime {

    Timestamp dateTime;

    String firstName;

    String lastName;

    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
