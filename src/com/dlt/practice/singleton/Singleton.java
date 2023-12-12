package com.dlt.practice.singleton;

final class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}

final class LazySingleton {

    private static volatile LazySingleton instance;

    private LazySingleton() {
        if (instance != null) {
            throw new IllegalStateException("Instance already created");
        }
    }

    public static LazySingleton getInstance() {
        if (instance == null) { // Check instance is null
            synchronized (LazySingleton.class) { // Synchronized lock against class
                if (instance == null) { // Check instance is still null before creating 
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

class DatabaseConnection {
    private String databaseURL;

    private static volatile DatabaseConnection instance;

    private DatabaseConnection(String databaseURL) {
        if (instance != null) {
            throw new IllegalStateException("DatabaseConnection instance already created");
        }
        this.databaseURL = databaseURL;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection("jdbc:mysql://localhost:3306/mydatabase");
                }
            }
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query on " + databaseURL + ": " + query);
    }

    public void close() {
        System.out.println("Closing connection with: " + databaseURL);
    }
}
