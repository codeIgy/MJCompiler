// generated with ast extension for cup
// version 0.8
// 24/5/2021 17:17:6


package rs.ac.bg.etf.pp1.ast;

public class Difop1 extends Relop {

    public Difop1 () {
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
        buffer.append("Difop1(\n");

        buffer.append(tab);
        buffer.append(") [Difop1]");
        return buffer.toString();
    }
}
