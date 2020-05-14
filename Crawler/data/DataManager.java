38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Singleton%20Pattern/src/DataManager.java
public class DataManager {
    private static DataManager dataManager;

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    private DataManager() { }
}
