// generated with ast extension for cup
// version 0.8
// 21/5/2021 21:52:24


package rs.ac.bg.etf.pp1.ast;

public class NoStatementList_ extends StatementList {

    public NoStatementList_ () {
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
        buffer.append("NoStatementList_(\n");

        buffer.append(tab);
        buffer.append(") [NoStatementList_]");
        return buffer.toString();
    }
}
