// generated with ast extension for cup
// version 0.8
// 26/5/2021 1:10:26


package rs.ac.bg.etf.pp1.ast;

public class NonMethodDeclList_ extends NonMethodDeclList {

    private NonMethodDeclList NonMethodDeclList;
    private NonMethodDecl NonMethodDecl;

    public NonMethodDeclList_ (NonMethodDeclList NonMethodDeclList, NonMethodDecl NonMethodDecl) {
        this.NonMethodDeclList=NonMethodDeclList;
        if(NonMethodDeclList!=null) NonMethodDeclList.setParent(this);
        this.NonMethodDecl=NonMethodDecl;
        if(NonMethodDecl!=null) NonMethodDecl.setParent(this);
    }

    public NonMethodDeclList getNonMethodDeclList() {
        return NonMethodDeclList;
    }

    public void setNonMethodDeclList(NonMethodDeclList NonMethodDeclList) {
        this.NonMethodDeclList=NonMethodDeclList;
    }

    public NonMethodDecl getNonMethodDecl() {
        return NonMethodDecl;
    }

    public void setNonMethodDecl(NonMethodDecl NonMethodDecl) {
        this.NonMethodDecl=NonMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonMethodDeclList!=null) NonMethodDeclList.accept(visitor);
        if(NonMethodDecl!=null) NonMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonMethodDeclList!=null) NonMethodDeclList.traverseTopDown(visitor);
        if(NonMethodDecl!=null) NonMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonMethodDeclList!=null) NonMethodDeclList.traverseBottomUp(visitor);
        if(NonMethodDecl!=null) NonMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonMethodDeclList_(\n");

        if(NonMethodDeclList!=null)
            buffer.append(NonMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NonMethodDecl!=null)
            buffer.append(NonMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonMethodDeclList_]");
        return buffer.toString();
    }
}
