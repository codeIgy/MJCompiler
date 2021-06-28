// generated with ast extension for cup
// version 0.8
// 28/5/2021 18:1:4


package rs.ac.bg.etf.pp1.ast;

public class IfStatement_ extends IfOrElseStatement {

    private DummyLparen DummyLparen;
    private Condition Condition;
    private DummyRparen DummyRparen;
    private Statement Statement;

    public IfStatement_ (DummyLparen DummyLparen, Condition Condition, DummyRparen DummyRparen, Statement Statement) {
        this.DummyLparen=DummyLparen;
        if(DummyLparen!=null) DummyLparen.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.DummyRparen=DummyRparen;
        if(DummyRparen!=null) DummyRparen.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DummyLparen!=null) DummyLparen.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(DummyRparen!=null) DummyRparen.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DummyLparen!=null) DummyLparen.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(DummyRparen!=null) DummyRparen.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DummyLparen!=null) DummyLparen.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(DummyRparen!=null) DummyRparen.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement_(\n");

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

        buffer.append(tab);
        buffer.append(") [IfStatement_]");
        return buffer.toString();
    }
}
