// generated with ast extension for cup
// version 0.8
// 23/5/2021 1:37:7


package rs.ac.bg.etf.pp1.ast;

public class NumConst1 extends ConstIdent {

    private String vName;
    private Integer vValue;

    public NumConst1 (String vName, Integer vValue) {
        this.vName=vName;
        this.vValue=vValue;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName=vName;
    }

    public Integer getVValue() {
        return vValue;
    }

    public void setVValue(Integer vValue) {
        this.vValue=vValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NumConst1(\n");

        buffer.append(" "+tab+vName);
        buffer.append("\n");

        buffer.append(" "+tab+vValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConst1]");
        return buffer.toString();
    }
}
