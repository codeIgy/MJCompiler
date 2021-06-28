package rs.ac.bg.etf.pp1;

import java.util.*;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPC;
	List<List<Integer>> fixCondFactAdress = new ArrayList<>();
	List<Integer> lastCondTermAdress = new ArrayList<>();
	List<Integer> ElseAddr = new ArrayList<>();
	
	List<Integer> whileTop = new ArrayList<>(); //adress of the first instruction in do while
	List<Integer> exitWhileAddr = new ArrayList<>(); //addresses that need to be fixed if while condition is not true
	//List<Integer> fixCondFactAddressWhile = new ArrayList<>();
	
	public CodeGenerator() {
		//fixCondFactAdress.add(new ArrayList<>());
	}
	
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
		
		if(type != TabWithBool.intType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
		else {
			Code.loadConst(5);
			Code.put(Code.print);
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
	
	public void visit(CondFactExprs_ condExprs) {//on the last CondFact we need to change the jump condition since we jump if the condition is true
		SyntaxNode grandParent = condExprs.getParent().getParent();
		
		Relop relop = condExprs.getRelop();
		int op;
		if(relop instanceof Eqop1) {
			op = Code.eq;
		}
		else if(relop instanceof Difop1) {
			op = Code.ne;
		}
		else if(relop instanceof Ltop1) {
			op = Code.lt;
		}
		else if(relop instanceof Leop1) {
			op = Code.le;
		}
		else if(relop instanceof Gtop1) {
			op = Code.gt;
		}
		else {
			op = Code.ge;
		}
		
		if((grandParent instanceof Conditions_ || grandParent instanceof CondTerm1) && (grandParent.getParent() instanceof Conditions_)) {
			Code.putFalseJump(Code.inverse[op], 0);
		}
		else {
			if(grandParent.getParent() instanceof DoWhileStatement_) {
				int topAddr = whileTop.get(whileTop.size() - 1) - Code.pc;
				Code.put(Code.jcc + op); Code.put2(whileTop.get(whileTop.size() - 1) - Code.pc + 1);
			}
			else Code.putFalseJump(op, 0);
			
		}
		
		int adr = Code.pc - 2;
		fixCondFactAdress.get(fixCondFactAdress.size() - 1).add(adr); //add the adress that needs to be fixed to the list
	}
	
	public void visit(CondFactExpr_ condExpr) {
		SyntaxNode grandParent = condExpr.getParent().getParent();
		
		Code.loadConst(0);
		if((grandParent instanceof Conditions_ || grandParent instanceof CondTerm1) && (grandParent.getParent() instanceof Conditions_)) Code.putFalseJump(Code.inverse[Code.ne], 0); //if this is the last expression for this term the we switch the condition
		else Code.putFalseJump(Code.ne, 0);
		
		int adr = Code.pc - 2;
		fixCondFactAdress.get(fixCondFactAdress.size() - 1).add(adr); //add the adress that needs to be fixed to the list
	}
	
	public void visit(OrDummy or) {//let all point to the next CondTerm only if this is not the last CondTerm
		List<Integer> fixCondList = fixCondFactAdress.get(fixCondFactAdress.size() - 1);
		for(int i =0; i < fixCondList.size() - 1; i++) {
			Code.fixup(fixCondList.get(i));
		}
		lastCondTermAdress.add(fixCondList.get(fixCondList.size() - 1));
		fixCondFactAdress.get(fixCondFactAdress.size() - 1).clear();
	}
	
	public void visit(CondFact1 condTermFactOnly) {
		/*if(condTermFactOnly.getParent() instanceof CondTerm_) {
			fixCondFactAdress.add(new ArrayList<>());
			startCondTermAdress.add(Code.pc); //save next term adress
		}*/
	}
	
	public void visit(CondTerm_ condTerm) {
		/*if(condTerm.getParent() instanceof CondTerm_) {
			fixCondFactAdress.add(new ArrayList<>());
			startCondTermAdress.add(Code.pc); //save next term adress
		}*/
	}
	
	public void visit(Conditions_ cond) {
		
	}
	
	public void visit(CondTerm1 condTermOnly) {
		
		
	}
	
	public void visit(DummyRparen DummyR) {
		if(DummyR.getParent() instanceof IfStatement_ || DummyR.getParent() instanceof ElseStatement_) {
			for(int i = 0; i < lastCondTermAdress.size(); i++) {
				Code.fixup(lastCondTermAdress.get(i));//put adress of statement within if since all of the CondFacts within CondTerm were true
			}
			lastCondTermAdress.clear();
		}
		else {
			List<Integer> list = fixCondFactAdress.get(fixCondFactAdress.size() - 1);
			for(int i = 0; i < list.size() - 1; i++) {
				Code.fixup(list.get(i));
			}
			for(int i = 0; i < lastCondTermAdress.size(); i++) {
				int storeAddr = lastCondTermAdress.get(i);
				Code.put2(storeAddr, (whileTop.get(whileTop.size() - 1) - storeAddr + 1));//put adress of statement within if since all of the CondFacts within CondTerm were true
			}
		}
	}
	
	public void visit(DummyLparen DummyL) {
		SyntaxNode parent = DummyL.getParent(); 
		if(parent instanceof IfStatement_) {
			fixCondFactAdress.add(new ArrayList<>());
		}
		else if(parent instanceof ElseStatement_) {
			fixCondFactAdress.add(new ArrayList<>());
		}
	}
	
	public void visit(DummyElse DummyElse) {
		Code.putJump(0); //After if branch has been executed, don't execute else branch too
		int addr = Code.pc - 2;
		ElseAddr.add(addr); //save address of jump instruction
		
		for(int i = 0; i < fixCondFactAdress.get(fixCondFactAdress.size() - 1).size(); i++) { // if any CondFact within last CondTerm is false, we jump directly to else
			Code.fixup(fixCondFactAdress.get(fixCondFactAdress.size() - 1).get(i));
		}
		
		fixCondFactAdress.remove(fixCondFactAdress.size() - 1);
		
	}
	
	public void visit(IfStatement_ ifStat) {
		for(int i = 0; i < fixCondFactAdress.get(fixCondFactAdress.size() - 1).size(); i++) { // if any CondFact within last CondTerm is false, we jump directly to else
			Code.fixup(fixCondFactAdress.get(fixCondFactAdress.size() - 1).get(i));
		}
		
		fixCondFactAdress.remove(fixCondFactAdress.size() - 1);
	}
	
	public void visit(ElseStatement_ elseStat) {
		Code.fixup(ElseAddr.remove(ElseAddr.size() - 1));
	}
	
	public void visit(ReadStatement_ read) {
		Obj designator = read.getDesignator().obj;
		if(designator.getType() == TabWithBool.charType) {
			Code.put(Code.bread);
		}
		else {
			Code.put(Code.read);
		}
			
		Code.store(designator);
	}
	
	public void visit(DoWhileStatement_ doWhile) {
		whileTop.remove(whileTop.size() - 1);
		fixCondFactAdress.remove(fixCondFactAdress.size() - 1);
		lastCondTermAdress.clear();
	}
	
	public void visit(DoDummy doDummy) {
		whileTop.add(Code.pc);
	}
	
	public void visit(WhileDummy whileDummy) {
		fixCondFactAdress.add(new ArrayList<>());
	}
}
