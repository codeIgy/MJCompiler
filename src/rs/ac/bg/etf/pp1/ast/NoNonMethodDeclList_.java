// generated with ast extension for cup
// version 0.8
// 27/5/2021 18:28:49


package rs.ac.bg.etf.pp1.ast;

public class NoNonMethodDeclList_ extends NonMethodDeclList {

    public NoNonMethodDeclList_ () {
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
        buffer.append("NoNonMethodDeclList_(\n");

        buffer.append(tab);
        buffer.append(") [NoNonMethodDeclList_]");
        return buffer.toString();
    }
}
