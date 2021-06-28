// generated with ast extension for cup
// version 0.8
// 28/5/2021 3:3:24


package rs.ac.bg.etf.pp1.ast;

public class NoMethodVarDecls_ extends MethodVarDecls {

    public NoMethodVarDecls_ () {
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
        buffer.append("NoMethodVarDecls_(\n");

        buffer.append(tab);
        buffer.append(") [NoMethodVarDecls_]");
        return buffer.toString();
    }
}
