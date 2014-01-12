package net.karneim.pojobuilder.codegen;

public class BuildAssignmentTM {
    private String fieldname;
    private String pojoFieldname;

    public BuildAssignmentTM(String fieldname, String pojoFieldname) {
        this.fieldname = fieldname;
        this.pojoFieldname = pojoFieldname;
    }

    public BuildAssignmentTM() {
        super();
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getPojoFieldname() {
        return pojoFieldname;
    }

    public void setPojoFieldname(String pojoFieldname) {
        this.pojoFieldname = pojoFieldname;
    }

}
