// generated with ast extension for cup
// version 0.8
// 27/5/2021 18:28:49


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(Relop Relop);
    public void visit(ToPrint ToPrint);
    public void visit(ConstIdent ConstIdent);
    public void visit(SwitchBodyWithoutDefault SwitchBodyWithoutDefault);
    public void visit(StatementList StatementList);
    public void visit(Break Break);
    public void visit(Addop Addop);
    public void visit(MethodVarDecls MethodVarDecls);
    public void visit(OperationsWithDesignator OperationsWithDesignator);
    public void visit(Factor Factor);
    public void visit(NonMethodDeclList NonMethodDeclList);
    public void visit(CondTerm CondTerm);
    public void visit(IsArray IsArray);
    public void visit(Designator Designator);
    public void visit(IsMinus IsMinus);
    public void visit(ConstIdents ConstIdents);
    public void visit(Term Term);
    public void visit(FormParsList FormParsList);
    public void visit(Condition Condition);
    public void visit(VarIdents VarIdents);
    public void visit(ActParsList ActParsList);
    public void visit(NonMethodDecl NonMethodDecl);
    public void visit(ExprList ExprList);
    public void visit(IfOrElseStatement IfOrElseStatement);
    public void visit(StatementListWithYield StatementListWithYield);
    public void visit(ReturnStatement ReturnStatement);
    public void visit(Continue Continue);
    public void visit(Expr Expr);
    public void visit(ActPars ActPars);
    public void visit(MethodNameAndReturnType MethodNameAndReturnType);
    public void visit(Statement Statement);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(FormPars FormPars);
    public void visit(Modop1 Modop1);
    public void visit(Divop1 Divop1);
    public void visit(Mulop1 Mulop1);
    public void visit(Minusop1 Minusop1);
    public void visit(Plusop1 Plusop1);
    public void visit(Geop1 Geop1);
    public void visit(Gtop1 Gtop1);
    public void visit(Leop1 Leop1);
    public void visit(Ltop1 Ltop1);
    public void visit(Difop1 Difop1);
    public void visit(Eqop1 Eqop1);
    public void visit(Assignop Assignop);
    public void visit(NoSwitchBodyWithoutDefault_ NoSwitchBodyWithoutDefault_);
    public void visit(SwitchBodyWithoutDefault_ SwitchBodyWithoutDefault_);
    public void visit(DummyColon DummyColon);
    public void visit(SwitchBody SwitchBody);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(DesignatorArray1 DesignatorArray1);
    public void visit(DesignatorNotArray_ DesignatorNotArray_);
    public void visit(NestedExpr1 NestedExpr1);
    public void visit(New2 New2);
    public void visit(New1 New1);
    public void visit(BoolConst2 BoolConst2);
    public void visit(CharConst2 CharConst2);
    public void visit(NumConst2 NumConst2);
    public void visit(Designator2 Designator2);
    public void visit(Designator1 Designator1);
    public void visit(Factor1 Factor1);
    public void visit(Term_ Term_);
    public void visit(SwitchDummy SwitchDummy);
    public void visit(SwitchStatement_ SwitchStatement_);
    public void visit(ExprList1 ExprList1);
    public void visit(PlusExpr_ PlusExpr_);
    public void visit(MinusExpr_ MinusExpr_);
    public void visit(CondFactExprs_ CondFactExprs_);
    public void visit(CondFactExpr_ CondFactExpr_);
    public void visit(CondFact1 CondFact1);
    public void visit(CondTerm_ CondTerm_);
    public void visit(CondTerm1 CondTerm1);
    public void visit(Conditions_ Conditions_);
    public void visit(ActPar_ ActPar_);
    public void visit(ActPars_ ActPars_);
    public void visit(NoActParsList_ NoActParsList_);
    public void visit(ActParsList_ ActParsList_);
    public void visit(DummyRparen DummyRparen);
    public void visit(DummyLparen DummyLparen);
    public void visit(DecExpression_ DecExpression_);
    public void visit(IncExpression_ IncExpression_);
    public void visit(ActParsListExpression_ ActParsListExpression_);
    public void visit(AssignExpression_ AssignExpression_);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(NoStatementList_ NoStatementList_);
    public void visit(StatementList_ StatementList_);
    public void visit(NumConstPrint_ NumConstPrint_);
    public void visit(NoNumConstPrint_ NoNumConstPrint_);
    public void visit(ReturnExprStatement_ ReturnExprStatement_);
    public void visit(ReturnVoidStatement_ ReturnVoidStatement_);
    public void visit(ElseStatement_ ElseStatement_);
    public void visit(IfStatement_ IfStatement_);
    public void visit(Continue1 Continue1);
    public void visit(Break1 Break1);
    public void visit(DoDummy DoDummy);
    public void visit(StatementList1 StatementList1);
    public void visit(PrintStatement_ PrintStatement_);
    public void visit(ReadStatement_ ReadStatement_);
    public void visit(ReturnStatement1 ReturnStatement1);
    public void visit(ContinueStatement_ ContinueStatement_);
    public void visit(BreakStatement_ BreakStatement_);
    public void visit(YieldStatement_ YieldStatement_);
    public void visit(DoWhileStatement_ DoWhileStatement_);
    public void visit(IfOrElseStatement1 IfOrElseStatement1);
    public void visit(DesignatorStatement1 DesignatorStatement1);
    public void visit(FormPar FormPar);
    public void visit(FormPar1 FormPar1);
    public void visit(FormPars_ FormPars_);
    public void visit(NoFormParamsList NoFormParamsList);
    public void visit(FormParsList_ FormParsList_);
    public void visit(Void1 Void1);
    public void visit(TypeReturn TypeReturn);
    public void visit(NoMethodVarDecls_ NoMethodVarDecls_);
    public void visit(MethodVarDecls_ MethodVarDecls_);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethods_ NoMethods_);
    public void visit(MethodDeclList_ MethodDeclList_);
    public void visit(Type Type);
    public void visit(NoArray_ NoArray_);
    public void visit(IsArray_ IsArray_);
    public void visit(VarIdent VarIdent);
    public void visit(VarIdent1 VarIdent1);
    public void visit(VarIdents_ VarIdents_);
    public void visit(VarDecl VarDecl);
    public void visit(BoolConst1 BoolConst1);
    public void visit(CharConst1 CharConst1);
    public void visit(NumConst1 NumConst1);
    public void visit(ConstIdent1 ConstIdent1);
    public void visit(ConstIdents_ ConstIdents_);
    public void visit(ConstDecl ConstDecl);
    public void visit(VarDecl1 VarDecl1);
    public void visit(ConstDecl1 ConstDecl1);
    public void visit(NoNonMethodDeclList_ NoNonMethodDeclList_);
    public void visit(NonMethodDeclList_ NonMethodDeclList_);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
