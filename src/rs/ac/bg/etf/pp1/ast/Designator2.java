// generated with ast extension for cup
// version 0.8
// 29/5/2021 23:28:2


package rs.ac.bg.etf.pp1.ast;

public class Designator2 extends Factor {

    private Designator Designator;
    private DummyLparen DummyLparen;
    private ActParsList ActParsList;
    private DummyRparen DummyRparen;

    public Designator2 (Designator Designator, DummyLparen DummyLparen, ActParsList ActParsList, DummyRparen DummyRparen) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DummyLparen=DummyLparen;
        if(DummyLparen!=null) DummyLparen.setParent(this);
        this.ActParsList=ActParsList;
        if(ActParsList!=null) ActParsList.setParent(this);
        this.DummyRparen=DummyRparen;
        if(DummyRparen!=null) DummyRparen.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DummyLparen getDummyLparen() {
        return DummyLparen;
    }

    public void setDummyLparen(DummyLparen DummyLparen) {
        this.DummyLparen=DummyLparen;
    }

    public ActParsList getActParsList() {
        return ActParsList;
    }

    public void setActParsList(ActParsList ActParsList) {
        this.ActParsList=ActParsList;
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
        if(Designator!=null) Designator.accept(visitor);
        if(DummyLparen!=null) DummyLparen.accept(visitor);
        if(ActParsList!=null) ActParsList.accept(visitor);
        if(DummyRparen!=null) DummyRparen.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DummyLparen!=null) DummyLparen.traverseTopDown(visitor);
        if(ActParsList!=null) ActParsList.traverseTopDown(visitor);
        if(DummyRparen!=null) DummyRparen.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DummyLparen!=null) DummyLparen.traverseBottomUp(visitor);
        if(ActParsList!=null) ActParsList.traverseBottomUp(visitor);
        if(DummyRparen!=null) DummyRparen.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator2(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyLparen!=null)
            buffer.append(DummyLparen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsList!=null)
            buffer.append(ActParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyRparen!=null)
            buffer.append(DummyRparen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator2]");
        return buffer.toString();
    }
}
