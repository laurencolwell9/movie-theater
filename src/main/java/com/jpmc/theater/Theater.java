package com.jpmc.theater;

import java.io.StringWriter;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jakarta.json.stream.JsonGenerator;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.Json;
import jakarta.json.JsonWriterFactory;
import jakarta.json.JsonWriter;

public class Theater {
    private List<Showing> schedule;

    public Theater(List<Showing> schedule) {
        this.schedule = schedule;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            String errorMessage = "Not able to find any showing for given sequence " + sequence;
            System.out.println(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        Reservation reservation = new Reservation(customer, showing, howManyTickets);
        System.out.println("You have to pay " + reservation.totalFee());
        return reservation;
    }

    public void printSchedule() {
        System.out.println(LocalDateProvider.singleton().currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");

        printJson();
    }

    private void printJson() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        schedule.forEach(s ->
                obj.add(Integer.toString(s.getSequenceOfTheDay()), Json.createObjectBuilder()
                        .add("startTime", s.getStartTime().toString())
                        .add("title", s.getMovie().getTitle())
                        .add("runTime", humanReadableFormat(s.getMovie().getRunningTime()))
                        .add("fee", "$" + s.getMovieFee()).build())
        );

        Map<String, String> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, "");
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);

        StringWriter strWriter = new StringWriter();
        try (JsonWriter jsonWriter = writerFactory.createWriter(strWriter)) {
            jsonWriter.writeObject(obj.build());
        }

        System.out.println(strWriter);
    }

    private String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {}
}
