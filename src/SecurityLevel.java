public class SecurityLevel {
    static final SecurityLevel LOW = new SecurityLevel(0);
    static final SecurityLevel HIGH = new SecurityLevel(1);
    int value;

    public SecurityLevel(int value) {
        this.value = value;
    }

    public boolean compareLess(SecurityLevel securityLevel){
        return this.value <= securityLevel.value;
    }
    public boolean compareGreat(SecurityLevel securityLevel){
        return this.value >= securityLevel.value;
    }
}
