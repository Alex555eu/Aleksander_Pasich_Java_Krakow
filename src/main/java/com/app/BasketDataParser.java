package com.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;


class BasketDataParser {

    private static BasketDataParser singletonObj = null;

    private BasketDataParser(){}

    public static BasketDataParser getInstance() {
        if (singletonObj == null) {
            singletonObj = new BasketDataParser();
        }
        return singletonObj;
    }

    /**
     * This method reads data from a file specified by the pathToFile parameter and passes it to a BasketData object.
     * @param pathToFile
     * @param basketData
     */
    public void parseData(String pathToFile, BasketData basketData){
        String data = readFromFile(pathToFile);
        try {
            basketData.setBasketData(new ObjectMapper().readValue(data, HashMap.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads data from a file specified by the absolutePathToFile and returns it as String.
     * @param pathToFile Path to file
     * @return String containing data from file
     */
    public String getRawData(String pathToFile) {
        return readFromFile(pathToFile);
    }

    private String readFromFile(String pathToFile) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(pathToFile))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;
    }


}
