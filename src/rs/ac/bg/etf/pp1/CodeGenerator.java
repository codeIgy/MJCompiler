package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPC;
	
	public int getMainPC() {
		return mainPC;
	}
	
	public void visit(TypeReturn methName) {
		methName.obj.setAdr(Code.pc);
		
		SyntaxNode methodNode = methName.getParent();
		
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(Void1 methName) {
		if("main".equals(methName.getMName())) {
			mainPC = Code.pc; 
		}
		methName.obj.setAdr(Code.pc);
		
		SyntaxNode methodNode = methName.getParent();
		
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodDecl methodDecl) {
		if(methodDecl.obj.getType() == TabWithBool.noType) {
			Code.put(Code.exit);
			Code.put(Code.return_);
		}
		else {
			Code.put(Code.trap);//in case we do not return from method that actually has return type, generate run-time error 
			Code.put(1);
		}
	}
	
	public void visit(DesignatorArray1 designatorArray) {
		Obj designatorObj = designatorArray.obj;
		//Expr expr = designatorArray.obj
		
		SyntaxNode parent = designatorArray.getParent();
		 //no need to check if it is a method since we have passed te semantic analysis
		
		//cases when we need to load from this location
		if(parent instanceof DesignatorStatement) {
			OperationsWithDesignator od = ((DesignatorStatement) parent).getOperationsWithDesignator();
			if(!(od instanceof AssignExpression_)) {
				Code.load(designatorObj);		
			}
		}
		else if(parent instanceof Designator1) {
			Code.load(designatorObj);
		}
		
	}
	
	public void visit(DesignatorArray designatorArray) {
		//Obj designator = TabWithBool.find(designatorArray.getI1());
		Code.load(designatorArray.obj);
	}
	
	public void visit(DesignatorNotArray_ designator) {
		Obj designatorObj = designator.obj;
		//Expr expr = designatorArray.obj
		
		SyntaxNode parent = designator.getParent();
		
		//cases when we need to load from this location
		if(parent instanceof DesignatorStatement) {
			OperationsWithDesignator od = ((DesignatorStatement) parent).getOperationsWithDesignator();
			if(od instanceof DecExpression_ || od instanceof IncExpression_) {
				Code.load(designatorObj);		
			}
		}
		else if(parent instanceof Designator1) {
			Code.load(designatorObj);
		}
	}
	
	public void visit(ActParsListExpression_ procCall) {
		Obj designator = ((DesignatorStatement) procCall.getParent()).getDesignator().obj;
		int offset = designator.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
		if(designator.getType() != TabWithBool.noType) {
			Code.put(Code.pop);
		}
	}
	
	public void visit(Designator2 funcCall) {
		Obj designator = funcCall.getDesignator().obj;
		int offset = designator.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	public void visit(ReturnVoidStatement_ returnStatement) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnExprStatement_ returnStatement) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(NumConst2 numconst) {
		Code.loadConst(numconst.getCValue());
	}
	
	public void visit(CharConst2 charconst) {
		Code.loadConst(charconst.getCValue());
	}
	
	public void visit(BoolConst2 boolconst) {
		Code.loadConst(boolconst.getCValue().equals("true") ? 1 : 0);
	}
	
	public void visit(New2 new2) {
		Code.put(Code.newarray);
		if(new2.getType().struct == TabWithBool.intType) {
			Code.put(1);
		}
		else {
			Code.put(0);
		}
	}
	
	public void visit(Term_ term) {
		Mulop mulop = term.getMulop();
		
		if(mulop instanceof Mulop1) {
			Code.put(Code.mul);
		}
		else if(mulop instanceof Divop1) {
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}
	}
	
	public void visit(MinusExpr_ minusExpr) {
		Code.put(Code.neg);
	}
	
	public void visit(ExprList1 exprList1) {
		Addop addop = exprList1.getAddop();
		
		if(addop instanceof Plusop1) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(DesignatorStatement designatorStatement) {
		OperationsWithDesignator od = designatorStatement.getOperationsWithDesignator();
		Obj designatorObj = designatorStatement.getDesignator().obj;
		
		if(!(od instanceof ActParsListExpression_)) {
			Code.store(designatorObj);	
		}
	}
	
	public void visit(IncExpression_ inc) {
		Code.loadConst(1);
		Code.put(Code.add);
	}
	
	public void visit(DecExpression_ dec) {
		Code.loadConst(1);
		Code.put(Code.sub);
	}
	
	public void visit(NoNumConstPrint_ print) {
		Struct type = print.getExpr().struct;
		
		if(type == TabWithBool.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(NumConstPrint_ print) {
		Struct type = print.getExpr().struct;
		
		Code.loadConst(print.getN2());
		
		if(type != TabWithBool.charType) {
			Code.put(Code.print);
		}
		else {
			Code.put(Code.bprint);
		}
	}
}
