// generated with ast extension for cup
// version 0.8
// 24/5/2021 17:17:6


package rs.ac.bg.etf.pp1.ast;

public class DoDummy implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public DoDummy () {
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
        buffer.append("DoDummy(\n");

        buffer.append(tab);
        buffer.append(") [DoDummy]");
        return buffer.toString();
    }
}