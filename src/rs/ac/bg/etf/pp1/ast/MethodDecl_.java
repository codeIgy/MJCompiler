// generated with ast extension for cup
// version 0.8
// 7/5/2021 2:33:58


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl_ extends MethodDecl {

    private ReturnStmt ReturnStmt;
    private String I2;
    private FormParsList FormParsList;
    private MethodVarDecls MethodVarDecls;
    private StatementList StatementList;

    public MethodDecl_ (ReturnStmt ReturnStmt, String I2, FormParsList FormParsList, MethodVarDecls MethodVarDecls, StatementList StatementList) {
        this.ReturnStmt=ReturnStmt;
        if(ReturnStmt!=null) ReturnStmt.setParent(this);
        this.I2=I2;
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.MethodVarDecls=MethodVarDecls;
        if(MethodVarDecls!=null) MethodVarDecls.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public ReturnStmt getReturnStmt() {
        return ReturnStmt;
    }

    public void setReturnStmt(ReturnStmt ReturnStmt) {
        this.ReturnStmt=ReturnStmt;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReturnStmt!=null) ReturnStmt.accept(visitor);
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(MethodVarDecls!=null) MethodVarDecls.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnStmt!=null) ReturnStmt.traverseTopDown(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(MethodVarDecls!=null) MethodVarDecls.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnStmt!=null) ReturnStmt.traverseBottomUp(visitor);
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(MethodVarDecls!=null) MethodVarDecls.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl_(\n");

        if(ReturnStmt!=null)
            buffer.append(ReturnStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
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
        buffer.append(") [MethodDecl_]");
        return buffer.toString();
    }
}
