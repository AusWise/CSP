package expression.constraint;


import expression.Expression;
import expression.Expression;
import expression.constraint.Constraint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class IsEqual implements Constraint {

    Expression first;
    Expression second;

    public IsEqual(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public Boolean evaluate() {
        return first.equals(second);
    }

    @Override
    public boolean isComputable() {
        return first.isComputable() && second.isComputable();
    }

    
}
