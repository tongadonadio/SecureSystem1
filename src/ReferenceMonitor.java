import java.util.ArrayList;
import java.util.HashMap;

public class ReferenceMonitor {
    private final ObjectManager manage;
    HashMap<String, SecurityLevel> objects;
    HashMap<String, SecurityLevel> subjects = new HashMap<>();
    public ArrayList<InstructionObject> badInstructions;

    public ReferenceMonitor() {
        this.badInstructions = new ArrayList<>();
        this.objects = new HashMap<>();
        this.manage = new ObjectManager();
    }
    
    public void createNewObject(String objectName, SecurityLevel level) {
        if (!objects.containsKey(objectName)) {
            objects.put(objectName.toLowerCase(), level);
            this.manage.createObject(objectName, 0);
        }
    }
    
    public void createNewObject(String objectName, String createdBy) {
        if (!objects.containsKey(objectName) && subjects.containsKey(createdBy)) {
            SecurityLevel level = new SecurityLevel(subjects.get(createdBy).value);
            objects.put(objectName.toLowerCase(), level);
            this.manage.createObject(objectName, 0);
        }
    }

    public void createSubject(String name, SecurityLevel level) {
        subjects.put(name.toLowerCase(), level);
    }

    public int checkRead(String subject, String object) {
        if (subjects.containsKey(subject) && objects.containsKey(object)) {
            if (subjects.get(subject).compareGreat(objects.get(object))) {
                return this.manage.executeRead(object);
            }
        }
        
        return 0;
    }

    public boolean checkWrite(String subject, String object, int value) {
        if (subjects.containsKey(subject) && objects.containsKey(object)) {
            if (subjects.get(subject).compareLess(objects.get(object))) {
                this.manage.executeWrite(object, value);
                return true;
            }
        }
        
        return false;
    }

    public int getValue(String object) {
        for (SecureObject check : this.manage.objects) {
            if (check.name.equalsIgnoreCase(object)) {
                return check.value;
            }
        }
        
        return 0;
    }
    
    public void saveBadInstruction(InstructionObject badInstruction) {
        this.badInstructions.add(badInstruction);
    }
}
