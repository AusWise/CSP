
import csp.BackTrackingCSP;
import csp.ConstraintSatisfactionProblem;
import csp.ForwardCheckingCSP;
import expression.Absolute;
import expression.Constant;
import expression.Expression;
import expression.Substract;
import expression.constraint.Constraint;
import expression.constraint.IsDiffrent;
import expression.constraint.IsNotEqual;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.currentTimeMillis;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class Test {
    public static void main(String [] args) throws FileNotFoundException{
        
        int start1, start2, start3, start4, end1, end2, end3, end4;
        
        start1=(int) currentTimeMillis();
        ConstraintSatisfactionProblem csp1 = new BackTrackingCSP(new File("/home/auswise/Documents/8hetmanów"));
        csp1.solve();
        end1=(int) currentTimeMillis();
        
        start2=(int) currentTimeMillis();
        ConstraintSatisfactionProblem csp2 = new ForwardCheckingCSP(new File("/home/auswise/Documents/8hetmanów"));
        csp2.solve();
        end2=(int) currentTimeMillis();
        
        start3=(int) currentTimeMillis();
        ConstraintSatisfactionProblem csp3 = new BackTrackingCSP(new File("/home/auswise/Documents/sudoku"));
        csp3.solve();
        end3=(int) currentTimeMillis();
        
        start4=(int) currentTimeMillis();
        ConstraintSatisfactionProblem csp4 = new ForwardCheckingCSP(new File("/home/auswise/Documents/sudoku"));
        csp4.solve();
        end4=(int) currentTimeMillis();
        
        System.out.println("8Hetmanów BackTracking bt=" + ((BackTrackingCSP)csp1).bt);
        System.out.println("8Hetmanów BackTracking time=" + (end1-start1));
        System.out.println("8Hetmanów ForwardChecking bt=" +((ForwardCheckingCSP)csp2).bt);
        System.out.println("8Hetmanów ForwardChecking fc=" +((ForwardCheckingCSP)csp2).fc);
        System.out.println("8Hetmanów ForwardChecking time=" + (end2-start2));
        
        System.out.println("Sudoku BackTracking bt=" + ((BackTrackingCSP)csp3).bt);
        System.out.println("Sudoku BackTracking time=" + (end3-start3));
        System.out.println("Sudoku ForwardChecking bt=" +((ForwardCheckingCSP)csp4).bt);
        System.out.println("Sudoku ForwardChecking fc=" +((ForwardCheckingCSP)csp4).fc);
        System.out.println("Sudoku ForwardChecking time=" + (end4-start4));
        
        
//        
//        Constant a = new Constant(1);
//        Constant b = new Constant(2);
//        
//        Expression s =  new Substract(a,b);
//        Expression abs = new Absolute(s);
//        Constraint c = new IsNotEqual(abs,new Constant(1));
//        System.out.println(c.evaluate());
    }
}
