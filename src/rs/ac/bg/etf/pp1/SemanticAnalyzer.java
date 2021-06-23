package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	boolean errorDetected = false;
	Obj currentMethod = null;
	boolean returnFound = false;
	Struct lastType = null;
	List<BreakStatement_> breaks = new ArrayList<>();
	List<ContinueStatement_> continues = new ArrayList<>();
	int numOfFormPars = 0;
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
		nVars = TabWithBool.currentScope.getnVars();
		TabWithBool.chainLocalSymbols(program.getProgName().obj);
		TabWithBool.closeScope();

		for(int i = 0; i < breaks.size(); i++) 
			report_error("\"break\" mora da bude unutar do while petlje!", breaks.remove(i));
		
		for(int i = 0; i < continues.size(); i++) 
			report_error("\"continue\" mora da bude unutar do while petlje!", continues.remove(i));
		
	}
	
	@Override
	public void visit(ProgName progname) {
		progname.obj = TabWithBool.insert(Obj.Prog, progname.getProgName(), TabWithBool.noType);
		TabWithBool.openScope();
	}
	
	@Override
	public void visit(Type type) {
		Obj typeNode = TabWithBool.find(type.getVType());
		if(typeNode == TabWithBool.noObj) {
			report_error("Nije pronadjen tip " + type.getVType() + " u tabeli simbola! ", type);
			type.struct = TabWithBool.noType;
		}
		else
		{
			if(Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			}
			else {
				report_error("Ime " + typeNode.getName() + " ne predstavlja tip!", type);
				type.struct = TabWithBool.noType;
			}
		}
		lastType = type.struct;
	}
	
	public void visit(NumConst1 numconst) {
		if(lastType.getKind() != Struct.Int) {
			report_error("Numericka konstanta ne odgovara tipu konstante!", numconst);
		}
		Obj ident = TabWithBool.find(numconst.getVName());
		if(ident == TabWithBool.noObj) {
			TabWithBool.insert(Obj.Con, numconst.getVName(), lastType);
		}
		else {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", numconst);
		}
	}
	
	public void visit(CharConst1 charconst) {
		if(lastType.getKind() != Struct.Char) {
			report_error("Karakterna konstanta ne odgovara tipu konstante!", charconst);
		}
		Obj ident = TabWithBool.find(charconst.getVName());
		if(ident == TabWithBool.noObj) {
			TabWithBool.insert(Obj.Con, charconst.getVName(), lastType);
		}
		else {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", charconst);
		}
	}
	
	public void visit(BoolConst1 boolconst) {
		if(lastType.getKind() != Struct.Bool) {
			report_error("Boolean konstanta ne odgovara tipu konstante!", boolconst);
		}
		Obj ident = TabWithBool.find(boolconst.getVName());
		if(ident == TabWithBool.noObj) {
			TabWithBool.insert(Obj.Con, boolconst.getVName(), lastType);
		}
		else {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", boolconst);
		}
	}
	
	public void visit(VarIdent varIdent) {
		Obj ident = TabWithBool.find(varIdent.getI1());
		if(ident != TabWithBool.noObj) {
			report_error("Simbol \"" + ident.getName() + "\" je vec deklarisan!", varIdent);
		}
		else {
			if(varIdent.getIsArray() instanceof IsArray_) {
				TabWithBool.insert(Obj.Var, varIdent.getI1(), new Struct(Struct.Array, lastType));
			}
			else {
				TabWithBool.insert(Obj.Var, varIdent.getI1(), lastType);
			}
		}
	}
	
	public void visit(DesignatorNotArray_ designator) {
		Obj ident = TabWithBool.find(designator.getI1());
		if(ident == TabWithBool.noObj) {
			report_error("Simbol \"" + designator.getI1() + "\" nije deklarisan!", designator);
			designator.obj = TabWithBool.noObj;
		}
		else {
			//rep_info
			designator.obj = ident;
		}
	}
	
	public void visit(DesignatorArray1 designatorArr) {
		designatorArr.obj = TabWithBool.find(designatorArr.getDesignatorArray().getI1());
		if(designatorArr.getExpr().struct.getKind() != Struct.Int && designatorArr.getExpr().struct.getKind() != Struct.None) {
			report_error("Izraz unutar ivicastih zagrada mora da bude tipa int!", designatorArr.getExpr());
		}
		
		if(designatorArr.obj == TabWithBool.noObj) {
			report_error("Simbol \"" + designatorArr.getDesignatorArray().getI1() + "\" nije deklarisan!", designatorArr);
		}
		else if(designatorArr.obj.getKind() != Obj.Var){
			report_error("Simbol \"" + designatorArr.obj.getName() + "\" nije varijabla!", designatorArr);
			designatorArr.obj = TabWithBool.noObj;
		}
		else if(designatorArr.obj.getType().getKind() != Struct.Array) {
			report_error("Simbol \"" + designatorArr.obj.getName() + "\" nije niz!", designatorArr);
			designatorArr.obj = TabWithBool.noObj;
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
		if(ident.getKind() != Obj.Var && ident != TabWithBool.find("null")) {
			report_error("Simbol \"" + ident.getName() + "\" nije varijabla!", designator1);
			designator1.struct = TabWithBool.noType;
		}
		else if (designator1.getDesignator() instanceof DesignatorArray1) {
			if(designator1.getDesignator().obj != TabWithBool.noObj)
				designator1.struct = ident.getType().getElemType(); //access to an element of an array, so the type is not array but the type of an array element
			else
				designator1.struct = TabWithBool.noType; //in case of an error
		}
		else designator1.struct = ident.getType();
	}
	
	public void visit(NumConst2 numconst2) {
		numconst2.struct = TabWithBool.intType;
	}
	
	public void visit(CharConst2 charconst2) {
		charconst2.struct = TabWithBool.charType;
	}
	
	public void visit(BoolConst2 boolconst2) {
		boolconst2.struct = TabWithBool.boolType;
	}
	
	public void visit(NestedExpr1 nestedExpr1) {
		nestedExpr1.struct = nestedExpr1.getExpr().struct;
	}
	
	public void visit(Factor1 factor1) {
		factor1.struct = factor1.getFactor().struct;
	}
	
	public void visit(Term_ term) {
		
		if(term.getTerm().struct.getKind() == TabWithBool.noType.getKind() || term.getFactor().struct.getKind() == TabWithBool.noType.getKind()) {
			term.struct = term.getTerm().struct;
			return;
		}
		
		if(term.getFactor().struct.getKind() != TabWithBool.intType.getKind() || term.getTerm().struct.getKind() != TabWithBool.intType.getKind()) {
			report_error("Simboli moraju da budu tipa int prilikom operacije mnozenja ili deljenja!", term.getFactor());
			term.struct = TabWithBool.noType;
		}
		else {
			term.struct = TabWithBool.intType;
		}
	}
	
	public void visit(MinusExpr_ minusExpr) {
		if(minusExpr.getTerm().struct.getKind() == TabWithBool.noType.getKind()) {
			minusExpr.struct = TabWithBool.noType;
			return;
		}
		
		if(minusExpr.getTerm().struct.getKind() != TabWithBool.intType.getKind()) {
			report_error("Izraz mora da bude tipa int prilikom negacije!", minusExpr);
			minusExpr.struct = TabWithBool.noType;
		}
		else {
			minusExpr.struct = TabWithBool.intType;
		}
	}
	
	public void visit(PlusExpr_ plusExpr) {
		plusExpr.struct = plusExpr.getTerm().struct;
	}
	
	public void visit(ExprList1 exprList1) {
		if(exprList1.getTerm().struct.getKind() == TabWithBool.noType.getKind() || exprList1.getExpr().struct.getKind() == TabWithBool.noType.getKind()) {
			exprList1.struct = TabWithBool.noType;
			return;
		}
		
		if(exprList1.getTerm().struct.getKind() != TabWithBool.intType.getKind() || exprList1.getExpr().struct.getKind() != TabWithBool.intType.getKind()) {
			report_error("Izraz mora da bude tipa int prilikom operacija oduzimanja i sabiranja!", exprList1);
			exprList1.struct = TabWithBool.noType;
		}
		else {
			exprList1.struct = TabWithBool.intType;
		}
	}
	
	public void visit(ActParsListExpression_ actParsListExpression) {
		
	}
	
	public void visit(DesignatorStatement designatorStatement) {
		Designator designator = designatorStatement.getDesignator();
		OperationsWithDesignator operationsWithDesig = designatorStatement.getOperationsWithDesignator();
		
		if(designator.obj == TabWithBool.noObj) return; //exit if there was a previous error in designator side
		
		if(designator.obj.getType() == TabWithBool.nullType) {
			report_error("null ne moze da bude designator!", designatorStatement);
			return;
		}
		
		
		if(operationsWithDesig instanceof AssignExpression_) {
			
			AssignExpression_ assignExpr = (AssignExpression_) operationsWithDesig;
			
			if(assignExpr.getExpr().struct == TabWithBool.noType) return; //exit in case there was a previous error in expression side
			
			if(designator.obj.getKind() != Obj.Var) {
				report_error("Sa lijeve strane znaka jedankosti mora biti promjenljiva!", designatorStatement);
				return;
			}
			
			if(designator instanceof DesignatorNotArray_) {
				/*if(assignExpr.getExpr().struct == TabWithBool.nullType && !designator.obj.getType().isRefType()) {
					report_error("Tipovi su nekompatiblni!", designatorStatement);
				}*/
				boolean isDesignatorRef = designator.obj.getType().isRefType();
				boolean isAssignExprRef = assignExpr.getExpr().struct.isRefType();
				
				if(assignExpr.getExpr().struct == TabWithBool.nullType) { //nulltype is also a ref type but without elemtype so we will need to check this case here before enter next if clause
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
		else if(designatorStatement.getOperationsWithDesignator() instanceof ActParsListExpression_) {
			
		}
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
	
	public void visit(Designator2 designatorMeth) {
		Designator desig = designatorMeth.getDesignator();
		
		if(desig.obj == TabWithBool.noObj) { //in case of previous error
			designatorMeth.struct = TabWithBool.noType; 
			return;
		}
		
		if(desig.obj.getKind() != Obj.Meth) {
			report_error("Pri pozivu funkcije designator mora da bude metod!", designatorMeth);
			designatorMeth.struct = TabWithBool.noType;
		}
		else designatorMeth.struct = desig.obj.getType();
		//rep_info
		
	}
	
	public void visit(CondFactExpr_ condFact) {
		Struct type = condFact.getExpr().struct;
		if(type.getKind() != Struct.Bool) {
			report_error("Uslov mora da bude tipa bool!", condFact);
		}
	}
	
	public void visit(CondFactExprs_ condFacts) {
		Relop rel = condFacts.getRelop();
		Expr expr1 = condFacts.getExpr();
		Expr expr2 = condFacts.getExpr1();
		
		if(expr1.struct.getKind() == Struct.None || expr2.struct.getKind() == Struct.None) //there were previous errors
			return;
		
		if(expr1.struct.isRefType() != expr2.struct.isRefType()) {
			report_error("Tipovi su nekompatiblni!", condFacts);
		}
		else if(expr1.struct.isRefType()){
			if(!(expr1.struct == TabWithBool.nullType) && !(expr2.struct == TabWithBool.nullType)
					&& expr1.struct.getElemType().getKind() != expr2.struct.getElemType().getKind()) //both are arrays, but element types are not the same;
				report_error("Tipovi su nekompatiblni!", condFacts);
			else if(!(rel instanceof Eqop1 || rel instanceof Difop1)){
				report_error("Uz promjenljive tipa niz mogu se samo koristiti samo != i == od relacionih operatora!", condFacts);
			}
		}
		else {
			if(expr1.struct.getKind() != expr2.struct.getKind()) { //they are not arrays so we check if they are the same ordinary types
				report_error("Tipovi su nekompatiblni!", condFacts);
			}
			else if(expr1.struct.getKind() == Struct.Bool && !(rel instanceof Eqop1 || rel instanceof Difop1)) {
				report_error("Uz promjenljive tipa bool mogu se samo koristiti samo != i == od relacionih operatora!", condFacts);
			}
		}
	}
	
	@Override
	public void visit(BreakStatement_ breakStatement) {
		breaks.add(breakStatement);
	}
	
	@Override
	public void visit(ContinueStatement_ continueStatement) {
		continues.add(continueStatement);
	}
	
	public void visit(DoWhileStatement_ doWhileStatement) {
		continues.clear();
		breaks.clear();
	}
	
	public void visit(SwitchStatement_ switchStatement) {
		
	}
	
	public void visit(MethodDecl method) {
		//check if main
		TabWithBool.chainLocalSymbols(currentMethod);
		TabWithBool.closeScope();
	}
	
	public void visit(TypeReturn methNameAndReturnType) {
		currentMethod = TabWithBool.insert(Obj.Meth, methNameAndReturnType.getMName(), methNameAndReturnType.getType().struct);
		TabWithBool.openScope();
	}
	
	public void visit(Void1 voidMethName) {
		currentMethod = TabWithBool.insert(Obj.Meth, voidMethName.getMName(), TabWithBool.noType);
		TabWithBool.openScope();
	}
}
