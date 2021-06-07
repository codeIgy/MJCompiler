// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class SwitchBody_ extends SwitchBody {

    private SwitchBodyWithoutDefault SwitchBodyWithoutDefault;
    private StatementListWithYield StatementListWithYield;

    public SwitchBody_ (SwitchBodyWithoutDefault SwitchBodyWithoutDefault, StatementListWithYield StatementListWithYield) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.setParent(this);
        this.StatementListWithYield=StatementListWithYield;
        if(StatementListWithYield!=null) StatementListWithYield.setParent(this);
    }

    public SwitchBodyWithoutDefault getSwitchBodyWithoutDefault() {
        return SwitchBodyWithoutDefault;
    }

    public void setSwitchBodyWithoutDefault(SwitchBodyWithoutDefault SwitchBodyWithoutDefault) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
    }

    public StatementListWithYield getStatementListWithYield() {
        return StatementListWithYield;
    }

    public void setStatementListWithYield(StatementListWithYield StatementListWithYield) {
        this.StatementListWithYield=StatementListWithYield;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.accept(visitor);
        if(StatementListWithYield!=null) StatementListWithYield.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseTopDown(visitor);
        if(StatementListWithYield!=null) StatementListWithYield.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseBottomUp(visitor);
        if(StatementListWithYield!=null) StatementListWithYield.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchBody_(\n");

        if(SwitchBodyWithoutDefault!=null)
            buffer.append(SwitchBodyWithoutDefault.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementListWithYield!=null)
            buffer.append(StatementListWithYield.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchBody_]");
        return buffer.toString();
    }
}
