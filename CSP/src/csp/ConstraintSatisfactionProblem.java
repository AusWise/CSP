package csp;

import expression.Variable;
import expression.Multiply;
import expression.constraint.IsNotEqual;
import expression.constraint.IsAtPosition;
import expression.Substract;
import expression.constraint.IsEqual;
import expression.constraint.IsLessThan;
import expression.constraint.IsDiffrent;
import expression.Expression;
import expression.constraint.Constraint;
import expression.Constant;
import expression.Absolute;
import expression.Add;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public abstract class ConstraintSatisfactionProblem {
    List<Variable> variables;
    List<Domain> domains;
    Set<Constraint> constraints;
    
    List<Variable> inConstraint;
    
    public ConstraintSatisfactionProblem(File file) throws FileNotFoundException{
        variables = new ArrayList<Variable>();
        domains = new ArrayList<Domain>();
        constraints = new HashSet<Constraint>();
        
        inConstraint = new LinkedList<Variable>();
        
        Scanner fileScanner = new Scanner(file);
        
        String line = fileScanner.nextLine();
        
        List<String> variableNames = variableNames(line);
        
        Iterator<String> variableName = variableNames.iterator();
        
        VariableType type;
        Variable variable;
        Domain domain;
        while(variableName.hasNext()){
            line = fileScanner.nextLine();
            type=variableType(line);
            
            variable = new Variable(variableName.next());
            variables.add(variable);
            domain = domain(line,type);
            //System.out.println(domain);
            domains.add(domain);
            variable.setDomain(domain);
        }
        
        Constraint constraint;
        while(fileScanner.hasNextLine()){
            constraint = constraint(fileScanner.nextLine());
            for(Iterator<Variable> i = inConstraint.iterator();
                    i.hasNext();i.next().getConstraints().add(constraint));
            inConstraint.clear();
            constraints.add(constraint);
            
        }
        
    }
    
    private List<String> variableNames(String line){
        List<String> variableNames = new LinkedList<String>();
        
        Scanner lineScanner = new Scanner(line);
        
        while(lineScanner.hasNext())
            variableNames.add(lineScanner.next());
        
        return variableNames;
    }
    
    private VariableType variableType(String line){
        Scanner lineScanner = new Scanner(line);
        
        while(lineScanner.hasNext())
            if(!isNumeric(lineScanner.next()))
                return VariableType.STR;
        
        return VariableType.NUM;
    }
    
    public boolean isNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    }  
    
    private Domain domain(String line, VariableType type){
        Scanner lineScanner = new Scanner(line);
        Domain domain = new Domain();
        
        if(type==VariableType.NUM)
            while(lineScanner.hasNextDouble())
                domain.add(lineScanner.nextDouble());
        else
            while(lineScanner.hasNextDouble())
                domain.add(lineScanner.next());
        
        return domain;
    }
    
    private Constraint constraint(String line){
        Scanner lineScanner = new Scanner(line);
        
        Stack<Expression> stack = new Stack<Expression>(); 
        
        String token;
        Expression first;
        Expression second;
        Variable variable;
        while(lineScanner.hasNext()){
            token = lineScanner.next();
            if(token.equals(ADD)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new Add(first,second));
            }
            else if(token.equals(SUBSTRACT)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new Substract(first,second));
            }
            else if(token.equals(MULTIPLY)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new Multiply(first,second));
            }
            else if(token.equals(IS_EQUAL)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new IsEqual(first,second));
            }
            else if(token.equals(IS_NOT_EQUAL)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new IsNotEqual(first,second));
            }
            else if(token.equals(IS_LESS_THAN)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new IsLessThan(first,second));
            }
            else if(token.equals(IS_GREATER_THAN)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new IsLessThan(second,first));
            }
            else if(token.equals(ABSOLUTE)){
                first=stack.pop();
                stack.add(new Absolute(first));
            }
            else if(token.equals(IS_AT_POSITION)){
                first=stack.pop();
                second=stack.pop();
                stack.add(new IsAtPosition(first, second));
            }
            else if(token.equals(IS_DIFFRENT)){
                List<Expression> expressions = new LinkedList<Expression>();
                while(stack.size()>1)
                    expressions.add(stack.pop());
                
                stack.add(new IsDiffrent(expressions));
            }
            else{
                variable = getVariable(token);
                if(variable!=null){
                    inConstraint.add(variable);
                    stack.add(variable);
                }
                else {
                    try{
                        stack.add(new Constant(Double.parseDouble(token)));
                    }
                    catch(Exception e){
                        stack.add(new Constant(token));
                    }
                }
            } 
            
        }
            
        return (Constraint)stack.pop();
    }
    
    private Variable getVariable(String name){
        Variable variable;
        Iterator<Variable> i = variables.iterator();
        while(i.hasNext()){
            variable=i.next();
            if(variable.getName().equals(name))
                return variable;
        }
        
        return null;
    }
    
    public abstract List<Variable> solve();
    
    private static enum VariableType{NUM,STR};
    
    private static final String ADD = "+";
    private static final String SUBSTRACT = "-";
    private static final String MULTIPLY = "*";
    private static final String IS_EQUAL = "=";
    private static final String IS_NOT_EQUAL = "<>"; 
    private static final String IS_LESS_THAN = "<";
    private static final String IS_GREATER_THAN = ">";
    private static final String ABSOLUTE = "||";
    private static final String IS_AT_POSITION = "[]";
    private static final String IS_DIFFRENT = "rozne";
}
