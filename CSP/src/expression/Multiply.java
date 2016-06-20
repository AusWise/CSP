package expression;


import expression.Expression;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class Multiply implements Expression {

    Expression first;
    Expression second;

    public Multiply(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Object evaluate() {
        return (double)first.evaluate()*(double)second.evaluate();
    }

    @Override
    public boolean isComputable() {
        return first.isComputable() && second.isComputable();
    }
    
    
    
    
}
