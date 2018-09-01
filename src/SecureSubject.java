public class SecureSubject {
    int readTemp;
    String name;
    
    SecureSubject(String name) {
        this.name = name;
        this.readTemp = 0;
    }

    SecureSubject(String name, int value) {
        this.name = name;
        this.readTemp = value;
    }
    
    public boolean equals(SecureSubject check){
        return this.name.equalsIgnoreCase(check.name) && this.readTemp == check.readTemp;
    }
    
    public void setTemp(int value){
        this.readTemp = value;
    }
}
