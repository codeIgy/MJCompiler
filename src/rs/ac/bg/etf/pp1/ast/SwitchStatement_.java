// generated with ast extension for cup
// version 0.8
// 21/5/2021 21:52:24


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatement_ extends Expr {

    private Expr Expr;
    private SwitchBody SwitchBody;

    public SwitchStatement_ (Expr Expr, SwitchBody SwitchBody) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SwitchBody=SwitchBody;
        if(SwitchBody!=null) SwitchBody.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SwitchBody getSwitchBody() {
        return SwitchBody;
    }

    public void setSwitchBody(SwitchBody SwitchBody) {
        this.SwitchBody=SwitchBody;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(SwitchBody!=null) SwitchBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SwitchBody!=null) SwitchBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SwitchBody!=null) SwitchBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatement_(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchBody!=null)
            buffer.append(SwitchBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatement_]");
        return buffer.toString();
    }
}
