package expressivo;

import java.util.Map;

public class Product implements Expression {
    private final Expression left;
    private final Expression right;
    
    // Abstract function:
    //  represents product of two expressions left * right
    // Representation invariant:
    //  true
    // Safety from rep exposure:
    //  all fields are immutable and final
    
    public Product(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString(){
        String toStringLeft = left.toString();
        String toStringRight = right.toString();
        
        if(left.isSum())
            toStringLeft = "("+toStringLeft+")";
        
        if(right.isSum() || right.isProduct())
            toStringRight = "("+toStringRight+")";
        
        return toStringLeft+"*"+toStringRight;
    }
    
    @Override
    public boolean equals(Object that){
        if(that instanceof Product){
            return this.left.equals(((Product) that).left) &&
                    this.right.equals(((Product) that).right);
        }
        
        return false;
    }
    
    @Override
    public int hashCode(){
        return left.hashCode() * right.hashCode();
    }

    @Override
    public Expression differentiate(Expression x) {
        return new Sum(new Product(left, right.differentiate(x)), new Product(right, left.differentiate(x)));
    }

    @Override
    public Expression simplify(Map<Expression, Double> environment) {
        final Expression simplifiedLeft = left.simplify(environment);
        final Expression simplifiedRight = right.simplify(environment);
        
        if(simplifiedLeft.isNumber() && simplifiedRight.isNumber()){
            return new Number(simplifiedLeft.getValue() * simplifiedRight.getValue());
        }else if(simplifiedLeft.isNumber()){
            if(simplifiedLeft.getValue() == 0){
                return simplifiedLeft; //return 0
            }else if(simplifiedLeft.getValue() == 1){
                return simplifiedRight;
            }
        }else if(simplifiedRight.isNumber()){
            if(simplifiedRight.getValue() == 0){
                return simplifiedRight; //return 0
            }else if(simplifiedRight.getValue() == 1){
                return simplifiedLeft;
            }
        }
        
        return new Product(simplifiedLeft, simplifiedRight);
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public double getValue() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("operation is undedfined on a product expression");
    }

    @Override
    public boolean isVariable() {
        return false;
    }

    @Override
    public String getName() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("operation is undedfined on a product expression");
    }

    @Override
    public boolean isSum() {
        return false;
    }

    @Override
    public boolean isProduct() {
        return true;
    }

    @Override
    public Expression getLeftExpression() throws UnsupportedOperationException {
        return left;
    }

    @Override
    public Expression getRightExpression() throws UnsupportedOperationException {
        return right;
    }
}
