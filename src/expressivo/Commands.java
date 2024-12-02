/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.HashMap;
import java.util.Map;

/**
 * String-based commands provided by the expression system.
 * 
 * <p>PS3 instructions: this is a required class.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You MUST NOT add fields, constructors, or instance methods.
 * You may, however, add additional static methods, or strengthen the specs of existing methods.
 */
public class Commands {
    
    /**
     * Differentiate an expression with respect to a variable.
     * @param expression the expression to differentiate
     * @param variable the variable to differentiate by, a case-sensitive nonempty string of letters.
     * @return expression's derivative with respect to variable.  Must be a valid expression equal
     *         to the derivative with structure specified by Expression.differentiate(); i.e,
     *         <pre>
     *         let x = Expression.differentiate(Expression.parse(expression), Expression.parse(variable))) and
     *         let y = Expression.parse(Commands.differentiate(expression, variable)),
     *         then x.equals(y)
     *         </pre>
     * @throws IllegalArgumentException if the expression or variable is invalid
     */
    public static String differentiate(String expression, String variable) {
        if(!variable.matches("[a-zA-z]+")){
            throw new IllegalArgumentException("ParseError: invalid variable in derivative command ");
        }
        
        Expression var = Expression.parse(variable);
        Expression expr = Expression.parse(expression);
        Expression derivative = expr.differentiate(var);
        
        return derivative.toString();
    }
    
    /**
     * Simplify an expression.
     * @param expression the expression to simplify
     * @param environment maps variables to values.  Variables are required to be case-sensitive nonempty 
     *         strings of letters.  The set of variables in environment is allowed to be different than the 
     *         set of variables actually found in expression.  Values must be nonnegative numbers.
     * @return an string, <i>strExp</i>, representing an expression, <i>e</i>, <i>functionally-equal</i> to an expression, <i>e'</i>, which is the input <b>expression</b> but 
     *         with all of its variables that appear in the <b>environment</b> substituted for their corresponding value. If there are no variables left in 
     *         <i>e'</i>, <i>strExp</i> must be the abstract representation of <i>e'</i> evaluated to a single number, that is, a representation with no white spaces or parentheses. 
     *         In this context,  <i>functionally-equal</i> means that under any environment, <i>env</i>, containing assignments to all variables in <i>e'</i>, <i>e</i> and 
     *         <i>e'</i> evaluates to a equal numbers.
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static String simplify(String expression, Map<String,Double> environment) {
        final Expression expr = Expression.parse(expression);
        final Map<Expression, Double> env = new HashMap<>();
        
        // construct environment with String variable names in environment replaced with variable Expression with same name.
        for(Map.Entry<String, Double> entry : environment.entrySet()){
            Expression variable = Expression.parse(entry.getKey());
            Double value = entry.getValue();
            env.put(variable, value);
        }
        
        Expression simplifiedExpr = expr.simplify(env);
        
        return simplifiedExpr.toString();
    }
    
}
