package expression.constraint;


import expression.Expression;
import expression.Expression;
import expression.constraint.Constraint;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class IsDiffrent implements Constraint {

    List<Expression> expressions;

    public IsDiffrent(List<Expression> expressions) {
        this.expressions = expressions;
    }
    
    @Override
    public Boolean evaluate() {
        List<Object> values = getValues();
        
        for(int i=0;i<values.size();i++)
            for(int j=0;j<values.size();j++)
                if(i!=j && values.get(i).equals(values.get(j)))
                    return false;
        
        return true;
            
    }
    
    private List<Object> getValues(){
        List<Object> values = new LinkedList<Object>();
        
        Iterator<Expression> i = expressions.iterator();
        
        while(i.hasNext())
            values.add(i.next().evaluate());
        
        return values;
    }

    @Override
    public boolean isComputable() {
        Iterator<Expression> i = expressions.iterator();
        
        while(i.hasNext())
            if(!i.next().isComputable())
                return false;
        
        return true;
    }
    
}
