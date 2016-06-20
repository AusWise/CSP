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
public class Constant implements Expression {
    private final Object value;

    public Constant(Object value) {
        this.value = value;
    }
    
    @Override
    public Object evaluate() {
        return value;
    }

    @Override
    public boolean isComputable() {
        return true;    
    }
}
