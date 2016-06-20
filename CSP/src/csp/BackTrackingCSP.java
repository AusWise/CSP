package csp;

import expression.Variable;
import expression.constraint.Constraint;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
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
public class BackTrackingCSP extends ConstraintSatisfactionProblem{

    public int bt;
    
    public BackTrackingCSP(File file) throws FileNotFoundException {
        super(file);
        bt=0;
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
        Iterator<Object> i = variable.getDomain().iterator();
        
        while(i.hasNext()){
            variable.setValue(i.next());
            System.out.println(variables);
            bt++;
            if(isSatisfying(variable) && solve(variableIndex+1))
                return true;
            
            System.out.println("BACK!");
        }
        
        variable.setValue(null);
            
        return false;
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
}
