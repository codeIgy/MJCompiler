// generated with ast extension for cup
// version 0.8
// 28/5/2021 3:3:24


package rs.ac.bg.etf.pp1.ast;

public class NumConst2 extends Factor {

    private Integer cValue;

    public NumConst2 (Integer cValue) {
        this.cValue=cValue;
    }

    public Integer getCValue() {
        return cValue;
    }

    public void setCValue(Integer cValue) {
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
        buffer.append("NumConst2(\n");

        buffer.append(" "+tab+cValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConst2]");
        return buffer.toString();
    }
}
