// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class IsArray_ extends IsArray {

    public IsArray_ () {
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
        buffer.append("IsArray_(\n");

        buffer.append(tab);
        buffer.append(") [IsArray_]");
        return buffer.toString();
    }
}
