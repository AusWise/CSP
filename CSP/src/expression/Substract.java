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
public class Substract implements Expression {
    Expression first;
    Expression second;

    public Substract(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Double evaluate() {
       return ((Number)first.evaluate()).doubleValue()-((Number)second.evaluate()).doubleValue(); 
    }

    @Override
    public boolean isComputable() {
        return first.isComputable() && second.isComputable();
    }
}
