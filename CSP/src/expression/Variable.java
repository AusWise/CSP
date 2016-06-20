package expression;

import csp.Domain;
import expression.Expression;
import expression.constraint.Constraint;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class Variable implements Expression{

    private Domain domain;
    private String name;
    private Object value;
    private List<Constraint> constraints;

    public Variable(String name) {
        this.name = name;
        constraints = new LinkedList<Constraint>();
    }

    public String getName() {
        return name;
    }
    
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Domain getDomain() {
        return domain;
    }

    @Override
    public Object evaluate() {
        return getValue();    
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    @Override
    public boolean isComputable() {
        return getValue()!=null;
    }

    @Override
    public String toString() {
        return name + "=" + getValue(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
