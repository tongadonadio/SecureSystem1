import java.util.LinkedList;

public class ObjectManager {
    LinkedList<SecureObject> objects = new LinkedList<>();
    
    public void createObject(String add, int value) {
        this.objects.add(new SecureObject(add, value));
    }

    public int executeRead(String objectName) {
        for (SecureObject object : this.objects) {
            if (object.name.equalsIgnoreCase(objectName)) {
                return object.value;
            }
        }

        return 0;
    }

    public void executeWrite(String objectName, int value) {
        for (SecureObject object : this.objects) {
            if (object.name.equalsIgnoreCase(objectName)) {
                object.updateObject(value);
            }
        }
    }
    
    public void executeCreate(String objectName, int value) {
        for (SecureObject object : this.objects) {
            if (object.name.equalsIgnoreCase(objectName)) {
                object.updateObject(value);
            }
        }
    }
    
    public void executeRun(String objectName, int value) {
        for (SecureObject object : this.objects) {
            if (object.name.equalsIgnoreCase(objectName)) {
                object.updateObject(value);
            }
        }
    }
    
    public void executeDestroy(String objectName, int value) {
        for (SecureObject object : this.objects) {
            if (object.name.equalsIgnoreCase(objectName)) {
                object.updateObject(value);
            }
        }
    }
}