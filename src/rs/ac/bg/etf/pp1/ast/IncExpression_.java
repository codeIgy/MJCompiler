// generated with ast extension for cup
// version 0.8
// 26/5/2021 1:10:26


package rs.ac.bg.etf.pp1.ast;

public class IncExpression_ extends OperationsWithDesignator {

    public IncExpression_ () {
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
        buffer.append("IncExpression_(\n");

        buffer.append(tab);
        buffer.append(") [IncExpression_]");
        return buffer.toString();
    }
}
