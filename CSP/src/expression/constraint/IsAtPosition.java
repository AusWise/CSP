package expression.constraint;


import expression.Expression;
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
public class IsAtPosition implements Expression{
    
    Expression expression;
    Expression i;

    public IsAtPosition(Expression expression, Expression i) {
        this.expression = expression;
        this.i = i;
    }
    
    @Override
    public Object evaluate() {
        return ((String)expression.evaluate()).charAt((int)i.evaluate());
    }

    @Override
    public boolean isComputable() {
        return expression.isComputable() && expression.isComputable();
    }
}
