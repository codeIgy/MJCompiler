// generated with ast extension for cup
// version 0.8
// 27/5/2021 18:28:49


package rs.ac.bg.etf.pp1.ast;

public class SwitchBodyWithoutDefault_ extends SwitchBodyWithoutDefault {

    private SwitchBodyWithoutDefault SwitchBodyWithoutDefault;
    private Integer N2;
    private StatementList StatementList;

    public SwitchBodyWithoutDefault_ (SwitchBodyWithoutDefault SwitchBodyWithoutDefault, Integer N2, StatementList StatementList) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.setParent(this);
        this.N2=N2;
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public SwitchBodyWithoutDefault getSwitchBodyWithoutDefault() {
        return SwitchBodyWithoutDefault;
    }

    public void setSwitchBodyWithoutDefault(SwitchBodyWithoutDefault SwitchBodyWithoutDefault) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
    }

    public Integer getN2() {
        return N2;
    }

    public void setN2(Integer N2) {
        this.N2=N2;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchBodyWithoutDefault_(\n");

        if(SwitchBodyWithoutDefault!=null)
            buffer.append(SwitchBodyWithoutDefault.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+N2);
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchBodyWithoutDefault_]");
        return buffer.toString();
    }
}
