// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl_ extends ConstDecl {

    private Type Type;
    private ConstIdents ConstIdents;

    public ConstDecl_ (Type Type, ConstIdents ConstIdents) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstIdents=ConstIdents;
        if(ConstIdents!=null) ConstIdents.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstIdents getConstIdents() {
        return ConstIdents;
    }

    public void setConstIdents(ConstIdents ConstIdents) {
        this.ConstIdents=ConstIdents;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstIdents!=null) ConstIdents.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstIdents!=null) ConstIdents.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstIdents!=null) ConstIdents.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl_(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstIdents!=null)
            buffer.append(ConstIdents.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl_]");
        return buffer.toString();
    }
}
