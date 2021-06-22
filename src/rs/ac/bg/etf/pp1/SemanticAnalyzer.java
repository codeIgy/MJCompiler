package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	boolean errorDetected = false;
	Obj currentMethod = null;
	boolean returnFound = false;
	Struct lastType = null;
	int nVars;
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder("Greska");
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		msg.append(": ").append(message);
		log.error(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	@Override
	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	@Override
	public void visit(ProgName progname) {
		Tab.insert(Obj.Type, "boolean", new Struct(Struct.Bool)); //add boolean type to our symbol table
		progname.obj = Tab.insert(Obj.Prog, progname.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	@Override
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getVType());
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getVType() + " u tabeli simbola! ", type);
			type.struct = Tab.noType;
		}
		else
		{
			if(Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			}
			else {
				report_error("Ime " + typeNode.getName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
		lastType = type.struct;
	}
	
	public void visit(NumConst1 numconst) {
		if(lastType.getKind() != Struct.Int) {
			report_error("Numericka konstanta ne odgovara tipu konstante!", numconst);
		}
		Obj ident = Tab.find(numconst.getVName());
		if(ident == Tab.noObj) {
			Tab.insert(Obj.Con, numconst.getVName(), lastType);
		}
		else {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", numconst);
		}
	}
	
	public void visit(CharConst1 charconst) {
		if(lastType.getKind() != Struct.Char) {
			report_error("Karakterna konstanta ne odgovara tipu konstante!", charconst);
		}
		Obj ident = Tab.find(charconst.getVName());
		if(ident == Tab.noObj) {
			Tab.insert(Obj.Con, charconst.getVName(), lastType);
		}
		else {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", charconst);
		}
	}
	
	public void visit(BoolConst1 boolconst) {
		if(lastType.getKind() != Struct.Bool) {
			report_error("Boolean konstanta ne odgovara tipu konstante!", boolconst);
		}
		Obj ident = Tab.find(boolconst.getVName());
		if(ident == Tab.noObj) {
			Tab.insert(Obj.Con, boolconst.getVName(), lastType);
		}
		else {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", boolconst);
		}
	}
	
	public void visit(VarIdent varIdent) {
		Obj ident = Tab.find(varIdent.getI1());
		if(ident != Tab.noObj) {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", varIdent);
		}
		else {
			if(varIdent.getIsArray() instanceof IsArray_) {
				Tab.insert(Obj.Var, varIdent.getI1(), new Struct(Struct.Array, lastType));
			}
			else {
				Tab.insert(Obj.Var, varIdent.getI1(), lastType);
			}
		}
	}
	
	public void visit(DesignatorNotArray_ designator) {
		Obj ident = Tab.find(designator.getI1());
		if(ident == Tab.noObj) {
			report_error("Simbol \"" + designator.getI1() + "\" nije deklarisan!", designator);
			designator.obj = Tab.noObj;
		}
		else {
			//rep_info
			designator.obj = ident;
		}
	}
	
	public void visit(DesignatorArray1 designatorArr) {
		designatorArr.obj = Tab.find(designatorArr.getDesignatorArray().getI1());
		if(designatorArr.getExpr().struct.getKind() != Struct.Int && designatorArr.getExpr().struct.getKind() != Struct.None) {
			report_error("Izraz unutar ivicastih zagrada mora da bude tipa int!", designatorArr.getExpr());
		}
		
		if(designatorArr.obj == Tab.noObj) {
			report_error("Simbol \"" + designatorArr.getDesignatorArray().getI1() + "\" nije deklarisan!", designatorArr);
		}
		else if(designatorArr.obj.getKind() != Obj.Var){
			report_error("Simbol \"" + designatorArr.obj.getName() + "\" nije varijabla!", designatorArr);
			designatorArr.obj = Tab.noObj;
		}
		else if(designatorArr.obj.getType().getKind() != Struct.Array) {
			report_error("Simbol \"" + designatorArr.obj.getName() + "\" nije niz!", designatorArr);
			designatorArr.obj = Tab.noObj;
		}
		else {
			//rep_info
		}
	}
	
	public void visit(New2 new2) {
		new2.struct = new Struct(Struct.Array, new2.getType().struct);
		
		if(new2.getExpr().struct.getKind() != Struct.Int) {
			report_error("Izraz unutar ivicastih zagrada mora da bude tipa int!", new2);
		}
	}
	
	public void visit(New1 new1) {
		new1.struct = new1.getType().struct;
	}
	
	public void visit(Designator1 designator1) {
		Obj ident = designator1.getDesignator().obj;
		if(ident.getKind() != Obj.Var && ident != Tab.find("null")) {
			report_error("Simbol \"" + ident.getName() + "\" nije varijabla!", designator1);
			designator1.struct = Tab.noType;
		}
		else if (designator1.getDesignator() instanceof DesignatorArray1) {
			if(designator1.getDesignator().obj != Tab.noObj)
				designator1.struct = ident.getType().getElemType(); //access to an element of an array, so the type is not array but the type of an array element
			else
				designator1.struct = Tab.noType; //in case of an error
		}
		else designator1.struct = ident.getType();
	}
	
	public void visit(NumConst2 numconst2) {
		numconst2.struct = Tab.intType;
	}
	
	public void visit(CharConst2 charconst2) {
		charconst2.struct = Tab.charType;
	}
	
	public void visit(BoolConst2 boolconst2) {
		boolconst2.struct = Tab.find("boolean").getType();
	}
	
	public void visit(NestedExpr1 nestedExpr1) {
		nestedExpr1.struct = nestedExpr1.getExpr().struct;
	}
	
	public void visit(Factor1 factor1) {
		factor1.struct = factor1.getFactor().struct;
	}
	
	public void visit(Term_ term) {
		
		if(term.getTerm().struct.getKind() == Tab.noType.getKind() || term.getFactor().struct.getKind() == Tab.noType.getKind()) {
			term.struct = term.getTerm().struct;
			return;
		}
		
		if(term.getFactor().struct.getKind() != Tab.intType.getKind() || term.getTerm().struct.getKind() != Tab.intType.getKind()) {
			report_error("Simboli moraju da budu tipa int prilikom operacije mnozenja ili deljenja!", term.getFactor());
			term.struct = Tab.noType;
		}
		else {
			term.struct = Tab.intType;
		}
	}
	
	public void visit(MinusExpr_ minusExpr) {
		if(minusExpr.getTerm().struct.getKind() == Tab.noType.getKind()) {
			minusExpr.struct = Tab.noType;
			return;
		}
		
		if(minusExpr.getTerm().struct.getKind() != Tab.intType.getKind()) {
			report_error("Izraz mora da bude tipa int prilikom negacije!", minusExpr);
			minusExpr.struct = Tab.noType;
		}
		else {
			minusExpr.struct = Tab.intType;
		}
	}
	
	public void visit(PlusExpr_ plusExpr) {
		plusExpr.struct = plusExpr.getTerm().struct;
	}
	
	public void visit(SwitchStatement_ switchs) {
		
	}
	
	public void visit(ExprList1 exprList1) {
		if(exprList1.getTerm().struct.getKind() == Tab.noType.getKind() || exprList1.getExpr().struct.getKind() == Tab.noType.getKind()) {
			exprList1.struct = Tab.noType;
			return;
		}
		
		if(exprList1.getTerm().struct.getKind() != Tab.intType.getKind() || exprList1.getExpr().struct.getKind() != Tab.intType.getKind()) {
			report_error("Izraz mora da bude tipa int prilikom operacija oduzimanja i sabiranja!", exprList1);
			exprList1.struct = Tab.noType;
		}
		else {
			exprList1.struct = Tab.intType;
		}
	}
	
	public void visit(ActParsListExpression_ actParsListExpression) {
		
	}
	
	public void visit(DesignatorStatement designatorStatement) {
		Designator designator = designatorStatement.getDesignator();
		OperationsWithDesignator operationsWithDesig = designatorStatement.getOperationsWithDesignator();
		
		if(designator.obj == Tab.noObj) return; //exit if there was a previous error in designator side
		
		if(designator.obj.getType() == Tab.nullType) {
			report_error("null ne moze da bude designator!", designatorStatement);
			return;
		}
		
		
		if(operationsWithDesig instanceof AssignExpression_) {
			
			AssignExpression_ assignExpr = (AssignExpression_) operationsWithDesig;
			
			if(assignExpr.getExpr().struct == Tab.noType) return; //exit in case there was a previous error in expression side
			
			if(designator.obj.getKind() != Obj.Var) {
				report_error("Sa lijeve strane znaka jedankosti mora biti promjenljiva!", designatorStatement);
				return;
			}
			
			if(designator instanceof DesignatorNotArray_) {
				/*if(assignExpr.getExpr().struct == Tab.nullType && !designator.obj.getType().isRefType()) {
					report_error("Tipovi su nekompatiblni!", designatorStatement);
				}*/
				boolean isDesignatorRef = designator.obj.getType().isRefType();
				boolean isAssignExprRef = assignExpr.getExpr().struct.isRefType();
				
				if(assignExpr.getExpr().struct == Tab.nullType) { //nulltype is also a ref type but without elemtype so we will need to check this case here before enter next if clause
					if(!isDesignatorRef) 
						report_error("Tipovi su nekompatiblni!", designatorStatement);
					
					return;
				}
				
				
				if((isDesignatorRef != isAssignExprRef) 
						|| (isDesignatorRef && isAssignExprRef && designator.obj.getType().getElemType().getKind() != assignExpr.getExpr().struct.getElemType().getKind())
						|| (!isDesignatorRef && !isAssignExprRef && designator.obj.getType().getKind() != assignExpr.getExpr().struct.getKind())) { //checking whether they are the same type (in case of an arrays, then array element types)					 
						
					report_error("Tipovi su nekompatiblni!", designatorStatement);
				}
			}
			else {
				Struct desigElemType = designator.obj.getType().getElemType();
				if(desigElemType.getKind() != assignExpr.getExpr().struct.getKind()) {
					report_error("Tipovi su nekompatiblni!", designatorStatement);
				}
			}
		}
		/*else if(designatorStatement.getOperationsWithDesignator() instanceof AssignExpression_) {
			
		}*/
		else if(designatorStatement.getOperationsWithDesignator() instanceof IncExpression_) {
			if(designator.obj.getKind() != Obj.Var || (designator.obj.getType().getKind() == Struct.Array && designator instanceof DesignatorNotArray_)) {//in case designator is a method or designator is an array, not an array element
				report_error("Operator ++ mora da bude izvrsen nad promjenljivom ili clanom niza!", designatorStatement);
				return;
			}
			
			Struct designatorStruct = designator.obj.getType().getKind() == Struct.Array ? designator.obj.getType().getElemType() : designator.obj.getType() ; //check if designator of type int
			
			if(designatorStruct.getKind() != Struct.Int) {
				report_error("Operator ++ mora da bude izvrsen nad tipom int!", designatorStatement);
			}
		}
		else if(designatorStatement.getOperationsWithDesignator() instanceof DecExpression_) {
			if(designator.obj.getKind() != Obj.Var || (designator.obj.getType().getKind() == Struct.Array && designator instanceof DesignatorNotArray_)) {//in case designator is a method or designator is an array, not an array element
				report_error("Operator -- mora da bude izvrsen nad promjenljivom ili clanom niza!", designatorStatement);
				return;
			}
			
			Struct designatorStruct = designator.obj.getType().getKind() == Struct.Array ? designator.obj.getType().getElemType() : designator.obj.getType() ; //check if designator of type int
			
			if(designatorStruct.getKind() != Struct.Int) {
				report_error("Operator -- mora da bude izvrsen nad tipom int!", designatorStatement);
			}
		}
	}
	
	/*public void visit(NestedExpr1 nestedExpr1) {
	
	}
	
	public void visit(NestedExpr1 nestedExpr1) {
	
	}*/
}
