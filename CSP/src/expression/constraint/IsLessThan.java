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
public class IsLessThan implements Constraint{
    Expression first;
    Expression second;

    public IsLessThan(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Boolean evaluate() {
        return (double)first.evaluate()<(double)second.evaluate();
    }

    @Override
    public boolean isComputable() {
        return first.isComputable() && second.isComputable();
    }
}
