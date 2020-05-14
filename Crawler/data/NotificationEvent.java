10
https://raw.githubusercontent.com/NearbyShops/Nearby-Shops-Android-app/master/app/src/main/java/org/nearbyshops/enduserappnew/Model/ModelUtility/NotificationEvent.java
package org.nearbyshops.enduserappnew.Model.ModelUtility;

public class NotificationEvent {

    private int notificationType;

    public NotificationEvent(int notificationType) {
        this.notificationType = notificationType;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(int notificationType) {
        this.notificationType = notificationType;
    }
}
