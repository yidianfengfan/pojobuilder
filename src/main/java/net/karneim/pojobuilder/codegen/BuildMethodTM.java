package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @since 3.0
 * @author karneim
 */
public class BuildMethodTM {

    private String returnType;
    private ConstructionTM construction;
    private List<SetterCallTM> setterCalls = new ArrayList<SetterCallTM>();
    private List<BuildAssignmentTM> assignments = new ArrayList<BuildAssignmentTM>();
    private Set<String> thrownExceptions = new HashSet<String>();
    
    private boolean override;

    public BuildMethodTM() {
    }

    public BuildMethodTM(String returnType, ConstructorCallTM construction) {
        this.returnType = returnType;
        this.construction = construction;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public ConstructionTM getConstruction() {
        return construction;
    }

    public void setConstruction(ConstructionTM construction) {
        this.construction = construction;
    }

    public List<SetterCallTM> getSetterCalls() {
        return setterCalls;
    }

    public List<BuildAssignmentTM> getAssignments() {
        return assignments;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isOverride() {
        return override;
    }

    public Set<String> getThrownExceptions() {
        return thrownExceptions;
    }
    
    public boolean isThrowingExceptions() {
        return !thrownExceptions.isEmpty();
    }

}
