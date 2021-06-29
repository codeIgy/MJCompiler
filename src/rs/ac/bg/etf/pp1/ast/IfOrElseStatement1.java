// generated with ast extension for cup
// version 0.8
// 29/5/2021 16:13:8


package rs.ac.bg.etf.pp1.ast;

public class IfOrElseStatement1 extends Statement {

    private IfOrElseStatement IfOrElseStatement;

    public IfOrElseStatement1 (IfOrElseStatement IfOrElseStatement) {
        this.IfOrElseStatement=IfOrElseStatement;
        if(IfOrElseStatement!=null) IfOrElseStatement.setParent(this);
    }

    public IfOrElseStatement getIfOrElseStatement() {
        return IfOrElseStatement;
    }

    public void setIfOrElseStatement(IfOrElseStatement IfOrElseStatement) {
        this.IfOrElseStatement=IfOrElseStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfOrElseStatement!=null) IfOrElseStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfOrElseStatement!=null) IfOrElseStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfOrElseStatement!=null) IfOrElseStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfOrElseStatement1(\n");

        if(IfOrElseStatement!=null)
            buffer.append(IfOrElseStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfOrElseStatement1]");
        return buffer.toString();
    }
}
