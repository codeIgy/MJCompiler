// generated with ast extension for cup
// version 0.8
// 26/5/2021 1:10:26


package rs.ac.bg.etf.pp1.ast;

public class BoolConst2 extends Factor {

    private String cValue;

    public BoolConst2 (String cValue) {
        this.cValue=cValue;
    }

    public String getCValue() {
        return cValue;
    }

    public void setCValue(String cValue) {
        this.cValue=cValue;
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
        buffer.append("BoolConst2(\n");

        buffer.append(" "+tab+cValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConst2]");
        return buffer.toString();
    }
}
