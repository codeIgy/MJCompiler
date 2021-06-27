// generated with ast extension for cup
// version 0.8
// 27/5/2021 18:28:49


package rs.ac.bg.etf.pp1.ast;

public class MethodVarDecls_ extends MethodVarDecls {

    private MethodVarDecls MethodVarDecls;
    private VarDecl VarDecl;

    public MethodVarDecls_ (MethodVarDecls MethodVarDecls, VarDecl VarDecl) {
        this.MethodVarDecls=MethodVarDecls;
        if(MethodVarDecls!=null) MethodVarDecls.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public MethodVarDecls getMethodVarDecls() {
        return MethodVarDecls;
    }

    public void setMethodVarDecls(MethodVarDecls MethodVarDecls) {
        this.MethodVarDecls=MethodVarDecls;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodVarDecls!=null) MethodVarDecls.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodVarDecls!=null) MethodVarDecls.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodVarDecls!=null) MethodVarDecls.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodVarDecls_(\n");

        if(MethodVarDecls!=null)
            buffer.append(MethodVarDecls.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodVarDecls_]");
        return buffer.toString();
    }
}
