// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class VarDecl_ extends VarDecl {

    private Type Type;
    private VarIdents VarIdents;

    public VarDecl_ (Type Type, VarIdents VarIdents) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarIdents=VarIdents;
        if(VarIdents!=null) VarIdents.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarIdents getVarIdents() {
        return VarIdents;
    }

    public void setVarIdents(VarIdents VarIdents) {
        this.VarIdents=VarIdents;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarIdents!=null) VarIdents.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarIdents!=null) VarIdents.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarIdents!=null) VarIdents.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl_(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarIdents!=null)
            buffer.append(VarIdents.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl_]");
        return buffer.toString();
    }
}
