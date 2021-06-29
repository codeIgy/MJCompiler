// generated with ast extension for cup
// version 0.8
// 29/5/2021 16:13:8


package rs.ac.bg.etf.pp1.ast;

public class YieldStatement_ extends Statement {

    private YieldDummy YieldDummy;
    private Expr Expr;

    public YieldStatement_ (YieldDummy YieldDummy, Expr Expr) {
        this.YieldDummy=YieldDummy;
        if(YieldDummy!=null) YieldDummy.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public YieldDummy getYieldDummy() {
        return YieldDummy;
    }

    public void setYieldDummy(YieldDummy YieldDummy) {
        this.YieldDummy=YieldDummy;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(YieldDummy!=null) YieldDummy.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(YieldDummy!=null) YieldDummy.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(YieldDummy!=null) YieldDummy.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YieldStatement_(\n");

        if(YieldDummy!=null)
            buffer.append(YieldDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YieldStatement_]");
        return buffer.toString();
    }
}
