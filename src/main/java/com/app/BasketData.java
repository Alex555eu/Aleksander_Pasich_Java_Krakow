package com.app;

import java.util.*;

class BasketData {

    private Map<String, List<String>> basketData;

    public Map<String, List<String>> getBasketData() {
        return basketData;
    }

    public void setBasketData(Map<String, List<String>> basketData) {
        this.basketData = basketData;
    }

    /**
     * This method evaluates the occurrences of delivery methods associated with the products specified in the items argument
     * and returns a map containing the delivery methods along with their respective counts.
     * @param items - list of products
     * @return Map of delivery methods and their occurrences.
     */
    public Map<String, Integer> evaluateDeliveryMethods(List<String> items) {
        Map<String, Integer> result = new HashMap<>();
        for(String product : items) {
            for(String deliveryMethod : this.basketData.get(product)) {
                result.put(deliveryMethod, result.getOrDefault(deliveryMethod, 1) + 1);
            }
        }
        return result;
    }

    /**
     *
     * @param map
     * @return A Key, that is mapped to the biggest Value.
     */
    public String getKeyWithBiggestValue(Map<String, Integer> map) {
        try {
            return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Filters out products not specified in the config file.
     * @param productList
     * @return List of valid products.
     */
    public List<String> filterValidProducts(List<String> productList) {
        List<String> validList = new ArrayList<>(productList);
        validList.removeIf(product -> !basketData.containsKey(product));
        return validList;
    }


}
