// generated with ast extension for cup
// version 0.8
// 28/5/2021 23:52:25


package rs.ac.bg.etf.pp1.ast;

public class VarIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private IsArray IsArray;

    public VarIdent (String I1, IsArray IsArray) {
        this.I1=I1;
        this.IsArray=IsArray;
        if(IsArray!=null) IsArray.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public IsArray getIsArray() {
        return IsArray;
    }

    public void setIsArray(IsArray IsArray) {
        this.IsArray=IsArray;
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
        if(IsArray!=null) IsArray.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IsArray!=null) IsArray.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IsArray!=null) IsArray.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarIdent(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(IsArray!=null)
            buffer.append(IsArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarIdent]");
        return buffer.toString();
    }
}
