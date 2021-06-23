// generated with ast extension for cup
// version 0.8
// 23/5/2021 14:7:45


package rs.ac.bg.etf.pp1.ast;

public class Gtop1 extends Relop {

    public Gtop1 () {
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
        buffer.append("Gtop1(\n");

        buffer.append(tab);
        buffer.append(") [Gtop1]");
        return buffer.toString();
    }
}
