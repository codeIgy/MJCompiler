// generated with ast extension for cup
// version 0.8
// 28/5/2021 23:52:25


package rs.ac.bg.etf.pp1.ast;

public class ElseStatement_ extends IfOrElseStatement {

    private DummyLparen DummyLparen;
    private Condition Condition;
    private DummyRparen DummyRparen;
    private Statement Statement;
    private DummyElse DummyElse;
    private Statement Statement1;

    public ElseStatement_ (DummyLparen DummyLparen, Condition Condition, DummyRparen DummyRparen, Statement Statement, DummyElse DummyElse, Statement Statement1) {
        this.DummyLparen=DummyLparen;
        if(DummyLparen!=null) DummyLparen.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.DummyRparen=DummyRparen;
        if(DummyRparen!=null) DummyRparen.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.DummyElse=DummyElse;
        if(DummyElse!=null) DummyElse.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
    }

    public DummyLparen getDummyLparen() {
        return DummyLparen;
    }

    public void setDummyLparen(DummyLparen DummyLparen) {
        this.DummyLparen=DummyLparen;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public DummyRparen getDummyRparen() {
        return DummyRparen;
    }

    public void setDummyRparen(DummyRparen DummyRparen) {
        this.DummyRparen=DummyRparen;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public DummyElse getDummyElse() {
        return DummyElse;
    }

    public void setDummyElse(DummyElse DummyElse) {
        this.DummyElse=DummyElse;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DummyLparen!=null) DummyLparen.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(DummyRparen!=null) DummyRparen.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(DummyElse!=null) DummyElse.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DummyLparen!=null) DummyLparen.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(DummyRparen!=null) DummyRparen.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(DummyElse!=null) DummyElse.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DummyLparen!=null) DummyLparen.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(DummyRparen!=null) DummyRparen.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(DummyElse!=null) DummyElse.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElseStatement_(\n");

        if(DummyLparen!=null)
            buffer.append(DummyLparen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyRparen!=null)
            buffer.append(DummyRparen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyElse!=null)
            buffer.append(DummyElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElseStatement_]");
        return buffer.toString();
    }
}
