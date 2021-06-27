// generated with ast extension for cup
// version 0.8
// 27/5/2021 18:28:49


package rs.ac.bg.etf.pp1.ast;

public class Void1 extends MethodNameAndReturnType {

    private String mName;

    public Void1 (String mName) {
        this.mName=mName;
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
        buffer.append("Void1(\n");

        buffer.append(" "+tab+mName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Void1]");
        return buffer.toString();
    }
}
