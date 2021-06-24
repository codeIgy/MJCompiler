// generated with ast extension for cup
// version 0.8
// 24/5/2021 17:17:6


package rs.ac.bg.etf.pp1.ast;

public class NoMethods_ extends MethodDeclList {

    public NoMethods_ () {
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
        buffer.append("NoMethods_(\n");

        buffer.append(tab);
        buffer.append(") [NoMethods_]");
        return buffer.toString();
    }
}
