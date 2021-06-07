// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class VarIdent_ extends VarIdent {

    private String I1;
    private IsArray IsArray;

    public VarIdent_ (String I1, IsArray IsArray) {
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
        buffer.append("VarIdent_(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(IsArray!=null)
            buffer.append(IsArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarIdent_]");
        return buffer.toString();
    }
}
