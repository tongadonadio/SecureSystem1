public class InstructionObject {
    String command, subjName, objName;
    int value;

    public InstructionObject(String com, String subj, String obj) {
        command = com;
        subjName = subj.toLowerCase();
        objName = obj.toLowerCase();
    }

    public InstructionObject(String com, String subj, String obj, int val) {
        command = com;
        subjName = subj.toLowerCase();
        objName = obj.toLowerCase();
        value = val;
    }
    
    public InstructionObject(String com, String subj) {
        command = com;
        subjName = subj.toLowerCase();
    }

    static final InstructionObject BAD_INSTRUCTION = new InstructionObject("BadInstruction", "NoSubject", "NoObject");

    public boolean isReadInstruction () {
        return (command.equalsIgnoreCase("Read"));
    }

    public boolean isWriteInstruction () {
        return (command.equalsIgnoreCase("Write"));
    }
    
    boolean isRunInstruction() {
        return (command.equalsIgnoreCase("Run"));
    }
    
    boolean isCreateInstruction() {
        return (command.equalsIgnoreCase("Create"));
    }
    
    boolean isDestroyInstruction() {
        return (command.equalsIgnoreCase("Destroy"));
    }
    
    public boolean isValidSubject(ReferenceMonitor monitor){
        return monitor.subjects.containsKey(subjName);
    }
    public boolean isValidObject(ReferenceMonitor monitor){
        return monitor.objects.containsKey(objName);
    }

    public String getInstructionCommand () {
        return command;
    }

    public String getInstructionObjName () {
        return objName.toLowerCase();
    }

    public String getInstructionSubjName () {
        return subjName.toLowerCase();
    }

    public int getInstructionValue () {
        return value;
    }
    
    public static InstructionObject parseInstruction (String line) {
        return null;
    }

    public void printInstruction () {
        if(command.equals("BadInstruction")){
            System.out.println(command);
        }
        else if(command.equalsIgnoreCase("read")){
            System.out.println(subjName + " lee " + objName);
        }
        else
        {
            System.out.println(subjName + " escribe el valor " + value + " a " + objName);
        }
    }
}
