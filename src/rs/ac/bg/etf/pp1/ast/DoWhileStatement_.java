// generated with ast extension for cup
// version 0.8
// 28/5/2021 23:52:25


package rs.ac.bg.etf.pp1.ast;

public class DoWhileStatement_ extends Statement {

    private DoDummy DoDummy;
    private Statement Statement;
    private WhileDummy WhileDummy;
    private DummyLparen DummyLparen;
    private Condition Condition;
    private DummyRparen DummyRparen;

    public DoWhileStatement_ (DoDummy DoDummy, Statement Statement, WhileDummy WhileDummy, DummyLparen DummyLparen, Condition Condition, DummyRparen DummyRparen) {
        this.DoDummy=DoDummy;
        if(DoDummy!=null) DoDummy.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.WhileDummy=WhileDummy;
        if(WhileDummy!=null) WhileDummy.setParent(this);
        this.DummyLparen=DummyLparen;
        if(DummyLparen!=null) DummyLparen.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.DummyRparen=DummyRparen;
        if(DummyRparen!=null) DummyRparen.setParent(this);
    }

    public DoDummy getDoDummy() {
        return DoDummy;
    }

    public void setDoDummy(DoDummy DoDummy) {
        this.DoDummy=DoDummy;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public WhileDummy getWhileDummy() {
        return WhileDummy;
    }

    public void setWhileDummy(WhileDummy WhileDummy) {
        this.WhileDummy=WhileDummy;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoDummy!=null) DoDummy.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(WhileDummy!=null) WhileDummy.accept(visitor);
        if(DummyLparen!=null) DummyLparen.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(DummyRparen!=null) DummyRparen.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoDummy!=null) DoDummy.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(WhileDummy!=null) WhileDummy.traverseTopDown(visitor);
        if(DummyLparen!=null) DummyLparen.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(DummyRparen!=null) DummyRparen.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoDummy!=null) DoDummy.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(WhileDummy!=null) WhileDummy.traverseBottomUp(visitor);
        if(DummyLparen!=null) DummyLparen.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(DummyRparen!=null) DummyRparen.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoWhileStatement_(\n");

        if(DoDummy!=null)
            buffer.append(DoDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileDummy!=null)
            buffer.append(WhileDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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

        buffer.append(tab);
        buffer.append(") [DoWhileStatement_]");
        return buffer.toString();
    }
}
