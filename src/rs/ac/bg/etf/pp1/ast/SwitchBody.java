// generated with ast extension for cup
// version 0.8
// 29/5/2021 16:13:8


package rs.ac.bg.etf.pp1.ast;

public class SwitchBody implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private SwitchBodyWithoutDefault SwitchBodyWithoutDefault;
    private DefaultDummy DefaultDummy;
    private DummyColon DummyColon;
    private StatementList StatementList;

    public SwitchBody (SwitchBodyWithoutDefault SwitchBodyWithoutDefault, DefaultDummy DefaultDummy, DummyColon DummyColon, StatementList StatementList) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.setParent(this);
        this.DefaultDummy=DefaultDummy;
        if(DefaultDummy!=null) DefaultDummy.setParent(this);
        this.DummyColon=DummyColon;
        if(DummyColon!=null) DummyColon.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public SwitchBodyWithoutDefault getSwitchBodyWithoutDefault() {
        return SwitchBodyWithoutDefault;
    }

    public void setSwitchBodyWithoutDefault(SwitchBodyWithoutDefault SwitchBodyWithoutDefault) {
        this.SwitchBodyWithoutDefault=SwitchBodyWithoutDefault;
    }

    public DefaultDummy getDefaultDummy() {
        return DefaultDummy;
    }

    public void setDefaultDummy(DefaultDummy DefaultDummy) {
        this.DefaultDummy=DefaultDummy;
    }

    public DummyColon getDummyColon() {
        return DummyColon;
    }

    public void setDummyColon(DummyColon DummyColon) {
        this.DummyColon=DummyColon;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.accept(visitor);
        if(DefaultDummy!=null) DefaultDummy.accept(visitor);
        if(DummyColon!=null) DummyColon.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseTopDown(visitor);
        if(DefaultDummy!=null) DefaultDummy.traverseTopDown(visitor);
        if(DummyColon!=null) DummyColon.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchBodyWithoutDefault!=null) SwitchBodyWithoutDefault.traverseBottomUp(visitor);
        if(DefaultDummy!=null) DefaultDummy.traverseBottomUp(visitor);
        if(DummyColon!=null) DummyColon.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchBody(\n");

        if(SwitchBodyWithoutDefault!=null)
            buffer.append(SwitchBodyWithoutDefault.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DefaultDummy!=null)
            buffer.append(DefaultDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyColon!=null)
            buffer.append(DummyColon.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchBody]");
        return buffer.toString();
    }
}
