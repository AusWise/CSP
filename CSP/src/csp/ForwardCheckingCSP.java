package csp;


import expression.Variable;
import expression.constraint.Constraint;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class ForwardCheckingCSP extends ConstraintSatisfactionProblem {

    public int bt,fc;
    
    public ForwardCheckingCSP(File file) throws FileNotFoundException {
        super(file);
        bt=fc=0;
    }

    @Override
    public List<Variable> solve() {
        
    if(solve(0))
            return variables;
        
        return null;
    }
    
    private boolean solve(int variableIndex){
        
        if(variableIndex==variables.size())
            return true;
        
        Variable variable = variables.get(variableIndex);
        
        for(int i=variableIndex+1;i<variables.size();i++)
            removeNotSatisfying(variables.get(i), variables.get(i).getDomain());
        
        List<Domain> domains = new LinkedList<Domain>();
        for(int i=variableIndex+1;i<variables.size();i++)
            domains.add((Domain) variable.getDomain().clone());
        
        Iterator<Object> it = variable.getDomain().iterator();
        
        while(it.hasNext()){
            variable.setValue(it.next());
            System.out.println(variables);
            bt++;
            if(isSatisfying(variable) && solve(variableIndex+1))
                return true;
            
            System.out.println("BACK!");
            for(int i=0;i<domains.size();i++)
                variables.get(variableIndex+i+1).setDomain(domains.get(i));
        }
        
        variable.setValue(null);
            
        return false;
    }
    
    private Domain removeNotSatisfying(Variable variable, Domain domain){
        
        Iterator<Object> i = domain.iterator();
        fc+=domain.size();
        while(i.hasNext()){
            variable.setValue(i.next());
            if(!isSatisfying(variable))
                i.remove();
        }
        
        variable.setValue(null);
        
        return domain;
    }

    private boolean isSatisfying(Variable variable){
        List<Constraint> constraints = variable.getConstraints();
        Iterator<Constraint> i = constraints.iterator();
        Constraint constraint;
        while(i.hasNext()){
            constraint = i.next();
            if(constraint.isComputable() && !(boolean)constraint.evaluate())
                return false;    
        }
        
        return true;
    }
    
    private void printDomains(){
        for(Variable variable : variables)
            System.out.println("D[" + variable.getName() + "]" + variable.getDomain());
    }
}
