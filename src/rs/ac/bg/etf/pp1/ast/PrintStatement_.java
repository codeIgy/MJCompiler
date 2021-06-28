// generated with ast extension for cup
// version 0.8
// 28/5/2021 18:1:4


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement_ extends Statement {

    private ToPrint ToPrint;

    public PrintStatement_ (ToPrint ToPrint) {
        this.ToPrint=ToPrint;
        if(ToPrint!=null) ToPrint.setParent(this);
    }

    public ToPrint getToPrint() {
        return ToPrint;
    }

    public void setToPrint(ToPrint ToPrint) {
        this.ToPrint=ToPrint;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ToPrint!=null) ToPrint.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ToPrint!=null) ToPrint.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ToPrint!=null) ToPrint.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement_(\n");

        if(ToPrint!=null)
            buffer.append(ToPrint.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement_]");
        return buffer.toString();
    }
}
