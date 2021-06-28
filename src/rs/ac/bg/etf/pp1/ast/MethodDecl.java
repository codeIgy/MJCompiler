// generated with ast extension for cup
// version 0.8
// 28/5/2021 18:1:4


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodNameAndReturnType MethodNameAndReturnType;
    private FormParsList FormParsList;
    private MethodVarDecls MethodVarDecls;
    private StatementList StatementList;

    public MethodDecl (MethodNameAndReturnType MethodNameAndReturnType, FormParsList FormParsList, MethodVarDecls MethodVarDecls, StatementList StatementList) {
        this.MethodNameAndReturnType=MethodNameAndReturnType;
        if(MethodNameAndReturnType!=null) MethodNameAndReturnType.setParent(this);
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.MethodVarDecls=MethodVarDecls;
        if(MethodVarDecls!=null) MethodVarDecls.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodNameAndReturnType getMethodNameAndReturnType() {
        return MethodNameAndReturnType;
    }

    public void setMethodNameAndReturnType(MethodNameAndReturnType MethodNameAndReturnType) {
        this.MethodNameAndReturnType=MethodNameAndReturnType;
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
    }

    public MethodVarDecls getMethodVarDecls() {
        return MethodVarDecls;
    }

    public void setMethodVarDecls(MethodVarDecls MethodVarDecls) {
        this.MethodVarDecls=MethodVarDecls;
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
        if(MethodNameAndReturnType!=null) MethodNameAndReturnType.accept(visitor);
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(MethodVarDecls!=null) MethodVarDecls.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodNameAndReturnType!=null) MethodNameAndReturnType.traverseTopDown(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(MethodVarDecls!=null) MethodVarDecls.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodNameAndReturnType!=null) MethodNameAndReturnType.traverseBottomUp(visitor);
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(MethodVarDecls!=null) MethodVarDecls.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodNameAndReturnType!=null)
            buffer.append(MethodNameAndReturnType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodVarDecls!=null)
            buffer.append(MethodVarDecls.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
