import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SecureSystem {
    ReferenceMonitor monitor = new ReferenceMonitor();
    ArrayList<SecureSubject> subjects = new ArrayList<>();

    public void createSubject(String name, SecurityLevel level) {
        this.subjects.add(new SecureSubject(name, 0));
        monitor.createSubject(name, level);
    }

    public void updateSubject(String name, int value) {
        for (SecureSubject subject : this.subjects) {
            if (name.equalsIgnoreCase(subject.name)) {
                subject.setTemp(value);
            }
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public ReferenceMonitor getReferenceMonitor() {
        return this.monitor;
    }

    public void printState() {
        System.out.println("El estado actual es: ");
        System.out.println("   lObj tiene valor: " + this.monitor.getValue("lobj"));
        System.out.println("   hObj tiene valor: " + this.monitor.getValue("hobj"));
        System.out.println("   lyle fue recientemente leído: " + this.getSubjectTemp("lyle"));
        System.out.println("   hal fue recientemente leído: " + this.getSubjectTemp("hal"));
    }

    public int getSubjectTemp(String name) {
        for (SecureSubject check : this.subjects) {
            if (check.name.equalsIgnoreCase(name)) {
                return check.readTemp;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File instructions = new File("instructionList");

        SecureSystem sys = new SecureSystem();
        
        sys.createSubject("lyle", SecurityLevel.LOW);
        sys.createSubject("hal", SecurityLevel.HIGH);
        sys.getReferenceMonitor().createNewObject("lobj", SecurityLevel.LOW);
        sys.getReferenceMonitor().createNewObject("hobj", SecurityLevel.HIGH);
        
        try (Scanner scanner = new Scanner(instructions)) {
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                String[] line = command.split(" ");
                executeCommand(line, sys);
            }
        }
    }
    
    private static void executeCommand(String[] line, SecureSystem sys) {
        if (line.length == 2) {
            InstructionObject instruct = new InstructionObject(line[0], line[1]);
            if (instruct.isRunInstruction()) {
                sys.updateSubject(instruct.getInstructionSubjName(),
                        sys.monitor.checkRead(
                                instruct.getInstructionSubjName(),
                                instruct.getInstructionObjName()));
                instruct.printInstruction();
            } else {
                InstructionObject.BAD_INSTRUCTION.printInstruction();
                sys.monitor.saveBadInstruction(instruct);
            }
        } else if (line.length == 3) {
            InstructionObject instruct = new InstructionObject(line[0], line[1], line[2]);
            if (instruct.isReadInstruction()) {
                sys.updateSubject(instruct.getInstructionSubjName(),
                        sys.monitor.checkRead(
                                instruct.getInstructionSubjName(),
                                instruct.getInstructionObjName()));
                instruct.printInstruction();
            } if (instruct.isCreateInstruction()) {
                sys.getReferenceMonitor().createNewObject(instruct.getInstructionObjName(), instruct.getInstructionSubjName());
            } if (instruct.isDestroyInstruction()) {
                
            } else {
                InstructionObject.BAD_INSTRUCTION.printInstruction();
                sys.monitor.saveBadInstruction(instruct);
            }
        } else if (line.length == 4 && isNumeric(line[3])) {
            InstructionObject instruct = new InstructionObject(line[0], line[1], line[2], Integer.valueOf(line[3]));
            if (instruct.isWriteInstruction()) {
                sys.monitor.checkWrite(instruct.getInstructionSubjName(), instruct.getInstructionObjName(), instruct.getInstructionValue());
                instruct.printInstruction();
            } else {
                InstructionObject.BAD_INSTRUCTION.printInstruction();
                sys.monitor.saveBadInstruction(instruct);
            }
        } else {
            InstructionObject.BAD_INSTRUCTION.printInstruction();
            sys.monitor.saveBadInstruction(InstructionObject.BAD_INSTRUCTION);
        }
        
        sys.printState();
    }
}
