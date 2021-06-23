// generated with ast extension for cup
// version 0.8
// 23/5/2021 1:37:7


package rs.ac.bg.etf.pp1.ast;

public class BoolConst1 extends ConstIdent {

    private String vName;
    private String vValue;

    public BoolConst1 (String vName, String vValue) {
        this.vName=vName;
        this.vValue=vValue;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName=vName;
    }

    public String getVValue() {
        return vValue;
    }

    public void setVValue(String vValue) {
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
        buffer.append("BoolConst1(\n");

        buffer.append(" "+tab+vName);
        buffer.append("\n");

        buffer.append(" "+tab+vValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConst1]");
        return buffer.toString();
    }
}
