package expressivo;

import java.util.Map;

public class Variable implements Expression {
    private final String name;
    
    // Abstract function:
    //  represents variable with name: this.name
    // Representation invariant:
    //  name is non-empty case-insensitive sequence of letters
    // Safety from rep exposure:
    //  all fields are immutable and final
    
    private void checkRep(){
        assert name.matches("[a-zA-z]+");
    }
    
    public Variable(String name){
        this.name = name;
        
        checkRep();
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    @Override
    public boolean equals(Object that){
        if(that instanceof Variable){
            return this.name.equals(((Variable)that).name);
        }
        
        return false;
    }
    
    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public Expression differentiate(Expression x) {
        if(this.equals(x)){
            return new Number(1);
        }else{
            return new Number(0);
        }
    }
    
    @Override
    public Expression simplify(Map<Expression, Double> environment) {
        if(environment.containsKey(this)){
            return new Number(environment.get(this));
        }
        
        return this;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public double getValue() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("operation is undedfined on variables");
    }

    @Override
    public boolean isVariable() {
        return true;
    }

    @Override
    public String getName() throws UnsupportedOperationException {
        return name;
    }

    @Override
    public boolean isSum() {
        return false;
    }

    @Override
    public boolean isProduct() {
        return false;
    }

    @Override
    public Expression getLeftExpression() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("operation is undedfined on variables");
    }

    @Override
    public Expression getRightExpression() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("operation is undedfined on variables");
    }
}
