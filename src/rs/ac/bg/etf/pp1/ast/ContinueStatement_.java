// generated with ast extension for cup
// version 0.8
// 29/5/2021 23:28:1


package rs.ac.bg.etf.pp1.ast;

public class ContinueStatement_ extends Statement {

    private Continue Continue;

    public ContinueStatement_ (Continue Continue) {
        this.Continue=Continue;
        if(Continue!=null) Continue.setParent(this);
    }

    public Continue getContinue() {
        return Continue;
    }

    public void setContinue(Continue Continue) {
        this.Continue=Continue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Continue!=null) Continue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Continue!=null) Continue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Continue!=null) Continue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ContinueStatement_(\n");

        if(Continue!=null)
            buffer.append(Continue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ContinueStatement_]");
        return buffer.toString();
    }
}
