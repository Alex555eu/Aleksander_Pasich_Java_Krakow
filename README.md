## Basket Splitter

### Introduction

This project contains a Java class named `BasketSplitter`, which provides functionality to split a list of items into separate baskets based on their delivery methods. The class is designed to be used in applications where there is a need to categorize items for different delivery methods.

### Constructor

The `BasketSplitter` class has a constructor that accepts the absolute path to a configuration file as a parameter. Upon instantiation, the constructor initializes the `basketData` and `basketDataParser` attributes, and parses the data from the provided configuration file.

### Public Methods

#### `split(List<String> items)`

This method takes a list of items as input and returns a map containing the split baskets based on delivery methods. It filters out invalid products, processes the remaining products, and categorizes them into separate baskets according to their most common delivery method.

### Example Usage

```java
BasketSplitter splitter = new BasketSplitter("/path/to/config/file");
List<String> items = Arrays.asList("Product1", "Product2", "Product3");
Map<String, List<String>> splitBaskets = splitter.split(items);
```

Config File example:

```
{
"Product1": ["Pick-up point", "Parcel locker"],
"Product2": ["Parcel locker", "Same day delivery"],
"Product3" : ["Courier"]
}
```

Result example:

```
{
"Parcel locker" : ["Product1", "Product2"],
"Courier" : ["Product3"]
}
```

### Build Project

The steps below are required to build project and create `.jar` file`:

1. Clone this repository.
2. Open terminal and move to projects root directory.
3. Build project by executing: `./mvnw clean package`.

These steps will build the project and create `*fat.jar` file, which will be located
in the `target/` directory.
