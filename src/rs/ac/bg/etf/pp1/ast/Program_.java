// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class Program_ extends Program {

    private String I1;
    private NonMethodDeclList NonMethodDeclList;
    private MethodDeclList MethodDeclList;

    public Program_ (String I1, NonMethodDeclList NonMethodDeclList, MethodDeclList MethodDeclList) {
        this.I1=I1;
        this.NonMethodDeclList=NonMethodDeclList;
        if(NonMethodDeclList!=null) NonMethodDeclList.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public NonMethodDeclList getNonMethodDeclList() {
        return NonMethodDeclList;
    }

    public void setNonMethodDeclList(NonMethodDeclList NonMethodDeclList) {
        this.NonMethodDeclList=NonMethodDeclList;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonMethodDeclList!=null) NonMethodDeclList.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonMethodDeclList!=null) NonMethodDeclList.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonMethodDeclList!=null) NonMethodDeclList.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(NonMethodDeclList!=null)
            buffer.append(NonMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_]");
        return buffer.toString();
    }
}
