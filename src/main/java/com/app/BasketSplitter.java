package com.app;

import java.util.*;

public class BasketSplitter {

    private final BasketData basketData;
    private final BasketDataParser basketDataParser;

    public BasketSplitter(String absolutePathToConfigFile) {
        basketData = new BasketData();
        basketDataParser = BasketDataParser.getInstance();

        basketDataParser.parseData(absolutePathToConfigFile, basketData);
    }

    /**
     * This method splits a basket of products into delivery groups based on the delivery methods associated with each product.
     * It aims to minimize the number of delivery groups while maximizing the number of products in each group.
     * @param items List representing basket of products to be split into delivery groups
     * @return A Map representing the split basket, where the keys are delivery methods and the values are lists of products mapped to each delivery method.
     */
    public Map<String, List<String>> split(List<String> items) {
        Map<String, List<String>> splitBasket = new HashMap<>();

        List<String> validProductList = basketData.filterValidProducts(items);

        processProductList(validProductList, splitBasket);

        return splitBasket;
    }

    /**
     * Maps the desired product to a delivery method, if they are compatible with each other.
     * Compatibility specified by config file.
     * @param product
     * @param deliveryMethod
     * @param splitBasket
     * @return `False` if requested delivery method is not compatible with a given product, `True` otherwise
     */
    private boolean mapProductToDeliveryMethod(String product, String deliveryMethod, Map<String, List<String>> splitBasket) {
        List<String> productDeliveryMethods = basketData.getBasketData().get(product);
        if (productDeliveryMethods.contains(deliveryMethod)) {
            if (splitBasket.containsKey(deliveryMethod)) {
                splitBasket.get(deliveryMethod).add(product);
            } else {
                splitBasket.put(deliveryMethod, new ArrayList<>(Arrays.asList(product)));
            }
            return true; //success
        }
        return false; //failure - delivery method not associated with that product
    }

    /**
     *
     * @param productList
     * @return Most common delivery method associated with given products
     */
    private String getMostCommonDeliveryMethod(List<String> productList) {
        Map<String, Integer> evaluatedDeliveryMethods = basketData.evaluateDeliveryMethods(productList);

        return basketData.getKeyWithBiggestValue(evaluatedDeliveryMethods);
    }

    /**
     * Method that iterates over a list of products, assigning them to most popular delivery method, while deleting them from their list.
     * Products not compatible with the most popular delivery method wait until next loop with newly calculated most popular delivery method.
     * The loop continues until all products are assigned to one of the delivery groups.
     * @param items
     * @param splitBasket
     */
    private void processProductList(List<String> items, Map<String, List<String>> splitBasket) {
        List<String> productList = new ArrayList<>(items);
        while (!productList.isEmpty()) {
            String mostCommonDeliveryMethod = getMostCommonDeliveryMethod(productList);
            productList.removeIf(product -> mapProductToDeliveryMethod(product, mostCommonDeliveryMethod, splitBasket));
        }
    }


}



