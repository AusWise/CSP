package expression;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class Absolute implements Expression {

    Expression expression;

    public Absolute(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate() {
        return Math.abs(((Number)expression.evaluate()).doubleValue());    
    }

    @Override
    public boolean isComputable() {
        return expression.isComputable();
    }
    
}
