// generated with ast extension for cup
// version 0.8
// 29/5/2021 4:34:32


package rs.ac.bg.etf.pp1.ast;

public class Eqop1 extends Relop {

    public Eqop1 () {
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
        buffer.append("Eqop1(\n");

        buffer.append(tab);
        buffer.append(") [Eqop1]");
        return buffer.toString();
    }
}
