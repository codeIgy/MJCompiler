// generated with ast extension for cup
// version 0.8
// 23/5/2021 1:37:7


package rs.ac.bg.etf.pp1.ast;

public class CharConst2 extends Factor {

    private Character cValue;

    public CharConst2 (Character cValue) {
        this.cValue=cValue;
    }

    public Character getCValue() {
        return cValue;
    }

    public void setCValue(Character cValue) {
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
        buffer.append("CharConst2(\n");

        buffer.append(" "+tab+cValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConst2]");
        return buffer.toString();
    }
}
