// generated with ast extension for cup
// version 0.8
// 29/5/2021 23:28:1


package rs.ac.bg.etf.pp1.ast;

public class CharConst1 extends ConstIdent {

    private String vName;
    private Character vValue;

    public CharConst1 (String vName, Character vValue) {
        this.vName=vName;
        this.vValue=vValue;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName=vName;
    }

    public Character getVValue() {
        return vValue;
    }

    public void setVValue(Character vValue) {
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
        buffer.append("CharConst1(\n");

        buffer.append(" "+tab+vName);
        buffer.append("\n");

        buffer.append(" "+tab+vValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConst1]");
        return buffer.toString();
    }
}
