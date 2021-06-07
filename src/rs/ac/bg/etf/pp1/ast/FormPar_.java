// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class FormPar_ extends FormPar {

    private Type Type;
    private String I2;
    private IsArray IsArray;

    public FormPar_ (Type Type, String I2, IsArray IsArray) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.IsArray=IsArray;
        if(IsArray!=null) IsArray.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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
        if(Type!=null) Type.accept(visitor);
        if(IsArray!=null) IsArray.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(IsArray!=null) IsArray.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(IsArray!=null) IsArray.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPar_(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(IsArray!=null)
            buffer.append(IsArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPar_]");
        return buffer.toString();
    }
}
