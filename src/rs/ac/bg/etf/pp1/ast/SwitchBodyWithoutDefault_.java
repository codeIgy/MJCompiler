// generated with ast extension for cup
// version 0.8
// 29/5/2021 16:13:8


package rs.ac.bg.etf.pp1.ast;

public class SwitchBodyWithoutDefault_ extends SwitchBodyWithoutDefault {

    private SwitchBodyWithoutDefault SwitchBodyWithoutDefault;
    private CaseDummy CaseDummy;
    private Integer N3;
    private StatementList StatementList;

    public SwitchBodyWithoutDefault_ (SwitchBodyWithoutDefault SwitchBodyWithoutDefault, CaseDummy CaseDummy, Integer N3, StatementList StatementList) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.setParent(this);
        this.CaseDummy=CaseDummy;
        if(CaseDummy!=null) CaseDummy.setParent(this);
        this.N3=N3;
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public SwitchBodyWithoutDefault getSwitchBodyWithoutDefault() {
        return SwitchBodyWithoutDefault;
    }

    public void setSwitchBodyWithoutDefault(SwitchBodyWithoutDefault SwitchBodyWithoutDefault) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
    }

    public CaseDummy getCaseDummy() {
        return CaseDummy;
    }

    public void setCaseDummy(CaseDummy CaseDummy) {
        this.CaseDummy=CaseDummy;
    }

    public Integer getN3() {
        return N3;
    }

    public void setN3(Integer N3) {
        this.N3=N3;
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
        if(CaseDummy!=null) CaseDummy.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseTopDown(visitor);
        if(CaseDummy!=null) CaseDummy.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseBottomUp(visitor);
        if(CaseDummy!=null) CaseDummy.traverseBottomUp(visitor);
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

        if(CaseDummy!=null)
            buffer.append(CaseDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+N3);
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
