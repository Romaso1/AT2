package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // If an argument is provided, use it as the number of elements for the stream task.
        int numElements = 10; // default value
        if (args.length > 0) {
            try {
                numElements = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument provided. Using default value of 10.");
            }
        }

        // Task 1: JSON Serialization/Deserialization
        runJsonTask();

        // Task 2: XML Serialization/Deserialization
        runXmlTask();

        // Task 3: Stream tasks
        runStreamTask(numElements);
    }

    private static void runJsonTask() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Read JSON from input.json (adjust the path if needed)
            Hobbyist hobbyist = objectMapper.readValue(new File("src/main/resources/input.json"), Hobbyist.class);
            System.out.println("Original JSON object: " + hobbyist);

            // Modify a few fields
            hobbyist.setName(hobbyist.getName() + " Updated");
            hobbyist.setId(hobbyist.getId() + 1);

            // Write the updated object to output.json
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("output.json"), hobbyist);
            System.out.println("Modified JSON object written to output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runXmlTask() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            // Read XML from input.xml (adjust the path if needed)
            Hobbyist hobbyist = xmlMapper.readValue(new File("src/main/resources/input.xml"), Hobbyist.class);
            System.out.println("Original XML object: " + hobbyist);

            // Modify fields
            hobbyist.setName(hobbyist.getName() + " Updated");
            hobbyist.setId(hobbyist.getId() + 1);

            // Write the updated object to output.xml
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/output.xml"), hobbyist);
            System.out.println("Modified XML object written to output.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runStreamTask(int numElements) {
        // Generate random Hobbyist objects
        List<Hobbyist> hobbyists = new ArrayList<>();
        String[] names = {"Alice", "Bob", "Charlie", "Dave", "Eve"};
        String[] hobbiesArray = {"reading", "hiking", "cooking", "painting", "cycling"};
        Random random = new Random();

        for (int i = 0; i < numElements; i++) {
            int id = random.nextInt(100);
            String name = names[random.nextInt(names.length)];
            // Create a random list of hobbies (for simplicity, select 3 random hobbies)
            List<String> hobbies = new ArrayList<>(Arrays.asList(hobbiesArray));
            Collections.shuffle(hobbies);
            List<String> selectedHobbies = hobbies.subList(0, 3);

            hobbyists.add(new Hobbyist(id, name, selectedHobbies));
        }

        // Sort the list using two fields: first by id, then by name
        List<Hobbyist> sortedList = hobbyists.stream()
                .sorted(Comparator.comparing(Hobbyist::getId).thenComparing(Hobbyist::getName))
                .collect(Collectors.toList());
        System.out.println("\nSorted Hobbyists:");
        sortedList.forEach(System.out::println);

        // Custom filter: filter by (1) id greater than 50 and (2) name starting with A or B
        List<Hobbyist> filteredList = hobbyists.stream()
                .filter(h -> h.getId() > 50)
                .filter(h -> h.getName().startsWith("A") || h.getName().startsWith("B"))
                .collect(Collectors.toList());
        System.out.println("\nFiltered Hobbyists (id > 50 and name starts with A or B):");
        filteredList.forEach(System.out::println);

        // Collect main fields (id and name) into a list of strings
        List<String> mainFields = hobbyists.stream()
                .map(h -> "id: " + h.getId() + ", name: " + h.getName())
                .collect(Collectors.toList());
        System.out.println("\nList of main fields (id and name):");
        mainFields.forEach(System.out::println);
    }
}
