// generated with ast extension for cup
// version 0.8
// 29/5/2021 4:34:32


package rs.ac.bg.etf.pp1.ast;

public class NoActParsList_ extends ActParsList {

    public NoActParsList_ () {
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
        buffer.append("NoActParsList_(\n");

        buffer.append(tab);
        buffer.append(") [NoActParsList_]");
        return buffer.toString();
    }
}
