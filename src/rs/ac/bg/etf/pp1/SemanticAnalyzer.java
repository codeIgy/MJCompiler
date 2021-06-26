package rs.ac.bg.etf.pp1;

import java.util.*;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;

//TO DO: napraviti Struct voidType kako bi prijavilo gresku u slucaju meth(main());

public class SemanticAnalyzer extends VisitorAdaptor {
	boolean errorDetected = false;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean mainDeclared = false;
	Struct lastType = null;
	List<List<BreakStatement_>> breaks = new ArrayList<>();
	List<List<ContinueStatement_>> continues = new ArrayList<>();
	List<List<Struct>> actParsList = new ArrayList<>();
	List<Set<Integer>> caseConsts = new ArrayList<>();
	List<List<YieldStatement_>> yieldReturnTypes = new ArrayList<>();
	List<Boolean> inDefault = new ArrayList<>();
	List<Boolean> yieldFoundInDefault = new ArrayList<>();
	int numOfFormPars = 0;
	int nVars;
	
	Logger log = Logger.getLogger(getClass());
	
	public SemanticAnalyzer() {
		breaks.add(new ArrayList<BreakStatement_>());
		continues.add(new ArrayList<ContinueStatement_>());
	}
	
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
		StringBuilder msg = new StringBuilder("Info"); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		msg.append(": ").append(message);
		log.info(msg.toString());
	}
	
	@Override
	public void visit(Program program) {
		nVars = TabWithBool.currentScope.getnVars();
		TabWithBool.chainLocalSymbols(program.getProgName().obj);
		TabWithBool.closeScope();
		
		for(List<BreakStatement_> b : breaks)
			for(int i = 0; i < b.size(); i++) 
				report_error("\"break\" mora da bude unutar do while petlje!", b.get(i));
		
		breaks.clear();
		
		for(List<ContinueStatement_> c : continues)
			for(int i = 0; i < c.size(); i++) 
				report_error("\"continue\" mora da bude unutar do while petlje!", c.get(i));
		
		continues.clear();
		
		if(!mainDeclared) {
			report_error("Ne postoji void main() metod!", null);
		}
		
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
			report_info("Deklaracija " + numconst.getVName(), numconst);
			Obj obj = TabWithBool.insert(Obj.Con, numconst.getVName(), lastType);
			obj.setAdr(numconst.getVValue());
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
			report_info("Deklaracija " + charconst.getVName(), charconst);
			Obj obj = TabWithBool.insert(Obj.Con, charconst.getVName(), lastType);
			obj.setAdr(charconst.getVValue());
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
			report_info("Deklaracija " +  boolconst.getVName(), boolconst);
			Obj obj = TabWithBool.insert(Obj.Con, boolconst.getVName(), lastType);
			obj.setAdr(boolconst.getVValue().equals("true") ? 1 : 0);
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
				report_info("Deklaracija " + varIdent.getI1(), varIdent);
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
			report_info("Upotreba " + ident.getName(), designator);
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
			report_info("Upotreba " + designatorArr.obj.getName(), designatorArr);
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
			if(designator1.getDesignator().obj != TabWithBool.noObj) {
				designator1.struct = ident.getType().getElemType(); //access to an element of an array, so the type is not array but the type of an array element
				report_info("Upotreba " + ident.getName(), designator1);
			}
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
	
	public void visit(DummyLparen DummyLparen) {
		actParsList.add(new ArrayList<>());
	}
	
	public void visit(DesignatorStatement designatorStatement) {
		Designator designator = designatorStatement.getDesignator();
		OperationsWithDesignator operationsWithDesig = designatorStatement.getOperationsWithDesignator();
		
		
		
		
		if(operationsWithDesig instanceof AssignExpression_) {
			
			if(designator.obj == TabWithBool.noObj) {
				return; //exit if there was a previous error in designator side
			}
			
			if(designator.obj.getType() == TabWithBool.nullType) {
				report_error("null ne moze da bude designator!", designatorStatement);
				return;
			}
			
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
			
			if(designator.obj == TabWithBool.noObj) {
				actParsList.remove(actParsList.size() - 1);
				return; //exit if there was a previous error in designator side
			}
			
			if(designator.obj.getType() == TabWithBool.nullType) {
				actParsList.remove(actParsList.size() - 1);
				report_error("null ne moze da bude designator!", designatorStatement);
				return;
			}
			
			if(designator.obj.getKind() != Obj.Meth) {
				report_error("Poziv funkcije se moze vrsiti samo nad funkcijom!",designator);
				actParsList.remove(actParsList.size() - 1);
				return;
			}
			
			Obj methodBeingAccesed = designator.obj;
			Collection<Obj> col = null;
			
			if (methodBeingAccesed == currentMethod) {
				col = TabWithBool.currentScope().getLocals().symbols();
			}
			else {
				col = methodBeingAccesed.getLocalSymbols();
			}
			
			Iterator<Obj> formParsIterator= col.iterator();
			
			List<Struct> actPars = actParsList.remove(actParsList.size() - 1);
			
			if(methodBeingAccesed.getLevel() < actPars.size()) {
				report_error("Previse argumenata pri pozivu funkcije!", designatorStatement);
			}
			else if (methodBeingAccesed.getLevel() > actPars.size()){
				report_error("Premalo argumenata pri pozivu funkcije!", designatorStatement);
			}
			else{
				for(int i = methodBeingAccesed.getLevel() - 1; i >= 0; i--) {
					Struct formal = formParsIterator.next().getType();
					Struct actual = actPars.get(i);
					if(formal == TabWithBool.noType || actual == TabWithBool.noType) continue; //there was an error with formal type
					
					if(formal.isRefType() != actual.isRefType()) {
						report_error("Argument funkcije na poziciji " + (methodBeingAccesed.getLevel() - i) + " nije odgovarajuceg tipa!", designatorStatement);
					}
					else if(formal.isRefType()) { //in case they are both arrays or act is null
						if(actual != TabWithBool.nullType && (formal.getElemType().getKind() != actual.getElemType().getKind()) ) {
							report_error("Argument funkcije na poziciji " + (methodBeingAccesed.getLevel() - i) + " nije odgovarajuceg tipa!", designatorStatement);
						}
					}
					else {
						if(formal.getKind() != actual.getKind()) {
							report_error("Argument funkcije na poziciji " + (i + 1) + " nije odgovarajuceg tipa!", designatorStatement);
						}
					}
				}
			}
		}
		else if(designatorStatement.getOperationsWithDesignator() instanceof IncExpression_) {
			
			if(designator.obj == TabWithBool.noObj) {
				return; //exit if there was a previous error in designator side
			}
			
			if(designator.obj.getType() == TabWithBool.nullType) {
				report_error("null ne moze da bude designator!", designatorStatement);
				return;
			}
			
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
			
			if(designator.obj == TabWithBool.noObj) {
				return; //exit if there was a previous error in designator side
			}
			
			if(designator.obj.getType() == TabWithBool.nullType) {
				report_error("null ne moze da bude designator!", designatorStatement);
				return;
			}
			
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
		Designator designator = designatorMeth.getDesignator();
		
		if(designator.obj == TabWithBool.noObj) { //in case of previous error
			designatorMeth.struct = TabWithBool.noType; 
			actParsList.remove(actParsList.size() - 1);
			return;
		}
		
		if(designator.obj.getKind() != Obj.Meth) {
			report_error("Pri pozivu funkcije designator mora da bude funkcija!", designatorMeth);
			designatorMeth.struct = TabWithBool.noType;
			actParsList.remove(actParsList.size() - 1);
			return;
		}
		
		Obj methodBeingAccesed = designator.obj;
		Collection<Obj> col = null;
		boolean errorOccurred = false;
		
		if (methodBeingAccesed == currentMethod) {
			col = TabWithBool.currentScope().getLocals().symbols();
		}
		else {
			col = methodBeingAccesed.getLocalSymbols();
		}
		
		Iterator<Obj> formParsIterator= col.iterator();
		List<Struct> actPars = actParsList.remove(actParsList.size() - 1);
		
		if(methodBeingAccesed.getLevel() < actPars.size()) {
			report_error("Previse argumenata pri pozivu funkcije!", designatorMeth);
			errorOccurred = true;
		}
		else if (methodBeingAccesed.getLevel() > actPars.size()){
			report_error("Premalo argumenata pri pozivu funkcije!", designatorMeth);
			errorOccurred = true;
		}
		else{
			for(int i = methodBeingAccesed.getLevel() - 1; i >= 0; i--) {
				Struct formal = formParsIterator.next().getType();
				Struct actual = actPars.get(i);
				if(formal == TabWithBool.noType || actual == TabWithBool.noType) continue; //there was an error with formal type
				
				if(formal.isRefType() != actual.isRefType()) {
					report_error("Argument funkcije na poziciji " + (methodBeingAccesed.getLevel() - i) + " nije odgovarajuceg tipa!", designatorMeth);
					errorOccurred = true;
				}
				else if(formal.isRefType()) { //in case they are both arrays or act is null
					if(actual != TabWithBool.nullType && (formal.getElemType().getKind() != actual.getElemType().getKind()) ) {
						report_error("Argument funkcije na poziciji " + (methodBeingAccesed.getLevel() - i) + " nije odgovarajuceg tipa!", designatorMeth);
						errorOccurred = true;
					}
				}
				else {
					if(formal.getKind() != actual.getKind()) {
						report_error("Argument funkcije na poziciji " + (i + 1) + " nije odgovarajuceg tipa!", designatorMeth);
						errorOccurred = true;
					}
				}
			}
		}
		
		designatorMeth.struct = errorOccurred ? TabWithBool.noType : designator.obj.getType();
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
		breaks.get(breaks.size() - 1).add(breakStatement);
	}
	
	@Override
	public void visit(ContinueStatement_ continueStatement) {
		continues.get(continues.size() - 1).add(continueStatement);
	}
	public void visit(DoWhileStatement_ doWhileStatement) {
		continues.remove(continues.size() - 1);
		breaks.remove(breaks.size() - 1);
	}
	
	public void visit(DoDummy doDummy) {
		breaks.add(new ArrayList<BreakStatement_>());
		continues.add(new ArrayList<ContinueStatement_>());
	}
	
	public void visit(MethodDecl method) {
		if(currentMethod.getName().equals("main") && currentMethod.getLevel() == 0 && currentMethod.getType() == TabWithBool.noType)
			mainDeclared = true;
		TabWithBool.chainLocalSymbols(currentMethod);
		TabWithBool.closeScope();
	}
	
	public void visit(TypeReturn methNameAndReturnType) {
		methNameAndReturnType.obj = currentMethod = TabWithBool.insert(Obj.Meth, methNameAndReturnType.getMName(), methNameAndReturnType.getType().struct);
		numOfFormPars = 0;
		TabWithBool.openScope();
	}
	
	public void visit(Void1 voidMethName) {
		voidMethName.obj = currentMethod = TabWithBool.insert(Obj.Meth, voidMethName.getMName(), TabWithBool.noType);
		numOfFormPars = 0;
		TabWithBool.openScope();
	}
	
	public void visit(FormParsList_ formParamList) {
		currentMethod.setLevel(numOfFormPars);
	}
	
	public void visit(NoFormParamsList noFormParamsList) {
		currentMethod.setLevel(numOfFormPars);
	}
	
	public void visit(FormPar formPar) {
		numOfFormPars++;
		Struct type = null;
		if(formPar.getIsArray() instanceof IsArray_) {
			type = new Struct(Struct.Array, formPar.getType().struct);
		}
		else {
			type = formPar.getType().struct;
		}
		TabWithBool.insert(Obj.Var, formPar.getI2(), type);
	}
	
	public void visit(ActPar_ actPar) {
		actParsList.get(actParsList.size() - 1).add(actPar.getExpr().struct);
	}
	
	public void visit(ActPars_ actPars) {
		actParsList.get(actParsList.size() - 1).add(actPars.getExpr().struct);
	}
	
	public void visit(NoNumConstPrint_ print) {
		Struct type = print.getExpr().struct;
		if(type.getKind() != TabWithBool.boolType.getKind() && type.getKind() != TabWithBool.intType.getKind() && type.getKind() != TabWithBool.charType.getKind()) {
			report_error("Izraz unutar print mora da bude tipa int, char ili bool", print);
		}
	}
	
	public void visit(NumConstPrint_ print) {
		Struct type = print.getExpr().struct;
		if(type.getKind() != TabWithBool.boolType.getKind() && type.getKind() != TabWithBool.intType.getKind() && type.getKind() != TabWithBool.charType.getKind()) {
			report_error("Izraz unutar print mora da bude tipa int, char ili bool", print);
		}
	}
	
	public void visit(ReturnVoidStatement_ returnVoid) {
		if(currentMethod.getType().getKind() != TabWithBool.noType.getKind()) {
			report_error("Metod mora d avrati izraz!", returnVoid.getParent());
		}
	}
	
	public void visit(ReturnExprStatement_ returnExpr) {
		if(currentMethod.getType().getKind() == TabWithBool.noType.getKind()) {
			report_error("Void metod ne smije da vraca izraz!", returnExpr.getParent());
		}
		else if(currentMethod.getType().getKind() != returnExpr.getExpr().struct.getKind()) {
			report_error("Povratni tip funkcije i tip return izraza se ne poklapaju!", returnExpr);
		}
	}
	
	public void visit(ReadStatement_ readStatement) {
		Designator designator = readStatement.getDesignator();
		
		if(designator.obj == TabWithBool.noObj) //if there were previous errors 
			return;
		
		if(designator instanceof DesignatorNotArray_) {
			if(designator.obj.getKind() == Obj.Meth || designator.obj.getType().getKind() != Struct.Bool && designator.obj.getType().getKind() != Struct.Int && designator.obj.getType().getKind() != Struct.Char) {
				report_error("Designator unutar read iskaza mora da bude tipa int, char ili bool!", readStatement);
			}
		}
		else {
			Struct type = designator.obj.getType();
			if(type.getElemType().getKind() != Struct.Bool && type.getElemType().getKind() != Struct.Int && type.getElemType().getKind() != Struct.Char) {
				report_error("Designator unutar read iskaza mora da bude tipa int, char ili bool!", readStatement);
			}
		}
	}
	
	public void visit(SwitchStatement_ switchStatement) {
		if(switchStatement.getExpr().struct.getKind() != Struct.Int && switchStatement.getExpr().struct.getKind() != Struct.None) {
			report_error("Izraz unutar zagrada mora da bude tipa int!", switchStatement);
			switchStatement.struct = TabWithBool.noType;
		}
		else switchStatement.struct = switchStatement.getSwitchBody().struct;
	}
	
	public void visit(SwitchBodyWithoutDefault_ switchBody) {
		Integer numConst = switchBody.getN2(); //get NUMCONST
		if(caseConsts.get(caseConsts.size() - 1).contains(numConst))
			report_error("Konstanta " + numConst + " je vec stavljena u case i ne moze da se ponovi!", switchBody);
		else
			caseConsts.get(caseConsts.size() - 1).add(numConst);
	}
	
	public void visit(NoSwitchBodyWithoutDefault_ switchBody) {
		caseConsts.add(new HashSet<>());//reset the set
		yieldReturnTypes.add(new ArrayList<YieldStatement_>());
		inDefault.add(false);
		yieldFoundInDefault.add(false);
	}
	
	public void visit(YieldStatement_ yield) {
		if(yieldReturnTypes.size() == 0) {
			report_error("Yield se moze koristiti samo unutar switch-a!", yield);
			return;
		}
		yieldReturnTypes.get(yieldReturnTypes.size() - 1).add(yield);
		if(inDefault.get(inDefault.size() - 1)) yieldFoundInDefault.set(yieldFoundInDefault.size() - 1, true);
	}
	
	public void visit(DummyColon col) {
		inDefault.set(inDefault.size() - 1, true);
	}
	
	public void visit(SwitchBody switchBody) {
		if(!yieldFoundInDefault.remove(yieldFoundInDefault.size() - 1)) {
			report_error("Default ne posjeduje yield naredbu!", switchBody);
			switchBody.struct = TabWithBool.noType;
		}
		
		switchBody.struct = null;
		
		inDefault.remove(inDefault.size() - 1);
		caseConsts.remove(caseConsts.size() - 1);
		
		List<YieldStatement_> list = yieldReturnTypes.remove(yieldReturnTypes.size() - 1);
		Struct previous = list.size() > 0 ? list.get(0).getExpr().struct : null;
		
		for(int i = 1; i < list.size(); i++) {
			YieldStatement_ y = list.get(i);
			Struct s = list.get(i).getExpr().struct;
			
			if(previous.isRefType() != s.isRefType()) {
				report_error("Sve yield naredbe moraju da vracaju isti tip podatka!", y);
				switchBody.struct = TabWithBool.noType;
				break;
			}
			else if(s.isRefType()) { //in case they are both arrays or act is null
				if(s != TabWithBool.nullType && previous != TabWithBool.nullType && previous.getElemType().getKind() != s.getElemType().getKind() ) {
					report_error("Sve yield naredbe moraju da vracaju isti tip podatka!", y);
					switchBody.struct = TabWithBool.noType;
					break;
				}
			}
			else {
				if(s.getKind() != previous.getKind()) {
					report_error("Sve yield naredbe moraju da vracaju isti tip podatka!", y);
					switchBody.struct = TabWithBool.noType;
					break;
				}
			}
		}
		
		if(switchBody.struct == null) switchBody.struct = previous == null ? TabWithBool.noType : previous;
	}
}
