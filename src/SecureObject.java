public class SecureObject {
    String name;
    int value;

    public SecureObject(String name){
        this.name = name;
        this.value = 0;
    }
    
    public SecureObject(String name, int value){
        this.name = name;
        this.value = value;
    }

    public boolean equals(SecureObject object){
        return this.name.equalsIgnoreCase(object.name) && this.value == object.value;
    }

    public void updateObject(int value) {
        this.value = value;
    }
}
