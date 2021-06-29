// generated with ast extension for cup
// version 0.8
// 29/5/2021 4:34:32


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatement_ extends Expr {

    private SwitchDummy SwitchDummy;
    private Expr Expr;
    private SwitchBody SwitchBody;

    public SwitchStatement_ (SwitchDummy SwitchDummy, Expr Expr, SwitchBody SwitchBody) {
        this.SwitchDummy=SwitchDummy;
        if(SwitchDummy!=null) SwitchDummy.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SwitchBody=SwitchBody;
        if(SwitchBody!=null) SwitchBody.setParent(this);
    }

    public SwitchDummy getSwitchDummy() {
        return SwitchDummy;
    }

    public void setSwitchDummy(SwitchDummy SwitchDummy) {
        this.SwitchDummy=SwitchDummy;
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
        if(SwitchDummy!=null) SwitchDummy.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(SwitchBody!=null) SwitchBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchDummy!=null) SwitchDummy.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SwitchBody!=null) SwitchBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchDummy!=null) SwitchDummy.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SwitchBody!=null) SwitchBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatement_(\n");

        if(SwitchDummy!=null)
            buffer.append(SwitchDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
