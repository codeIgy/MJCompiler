// generated with ast extension for cup
// version 0.8
// 27/5/2021 18:28:49


package rs.ac.bg.etf.pp1.ast;

public class TypeReturn extends MethodNameAndReturnType {

    private Type Type;
    private String mName;

    public TypeReturn (Type Type, String mName) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.mName=mName;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName=mName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TypeReturn(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+mName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeReturn]");
        return buffer.toString();
    }
}
