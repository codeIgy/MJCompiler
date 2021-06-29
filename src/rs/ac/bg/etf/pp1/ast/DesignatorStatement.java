// generated with ast extension for cup
// version 0.8
// 29/5/2021 16:13:8


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatement implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Designator Designator;
    private OperationsWithDesignator OperationsWithDesignator;

    public DesignatorStatement (Designator Designator, OperationsWithDesignator OperationsWithDesignator) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.OperationsWithDesignator=OperationsWithDesignator;
        if(OperationsWithDesignator!=null) OperationsWithDesignator.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public OperationsWithDesignator getOperationsWithDesignator() {
        return OperationsWithDesignator;
    }

    public void setOperationsWithDesignator(OperationsWithDesignator OperationsWithDesignator) {
        this.OperationsWithDesignator=OperationsWithDesignator;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(OperationsWithDesignator!=null) OperationsWithDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(OperationsWithDesignator!=null) OperationsWithDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(OperationsWithDesignator!=null) OperationsWithDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatement(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OperationsWithDesignator!=null)
            buffer.append(OperationsWithDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatement]");
        return buffer.toString();
    }
}
