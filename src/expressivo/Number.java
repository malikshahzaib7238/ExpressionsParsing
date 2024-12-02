package expressivo;

import java.util.Map;

public class Number implements Expression {
    private final double number;
    
    // Abstract function:
    //  this.number represents real valued number
    // Representation Invariant:
    //  this.number >= 0
    // Safety from rep exposure:
    //  all fields are immutable and final
    
    private void checkRep(){
        assert this.number >= 0;
    }
    
    public Number(double number){
        this.number = number;
        
        checkRep();
    }
    
    @Override
    public String toString(){
        // This implementation used the string decimal representation returned by String.valueOf()
        // and re-formats it into decimal form if the string returned is in computerized scientific format.
        // The reason for doing it this, rather than other ways like String.format(), is to ensure
        // the decimal number represented by toString() is accurate to enough significant digits to ensure
        // this.number is the closest double precision floating-point number to this.toString().
        // i.e to ensure this.number == Double.valueOf(this.toString()).
        
        String value = String.valueOf(number);
        
        if(value.contains("E")){
            // value is in computerized scientific form and must be
            // re-formatted into decimal form.
            
            String[] split = value.split("[\\.E]");
            
            String decimal = split[0];
            String fraction = split[1];
            String exponent = split[2];       
            int expt = Integer.valueOf(exponent);
            
            if(expt < 0){
                final int numberOfShifts = -expt;
                final int lengthOfDecimal = decimal.length();
                
                if(numberOfShifts > lengthOfDecimal){
                    return "0." + stringOfZeros(numberOfShifts-lengthOfDecimal) + decimal + fraction;
                }
                
                return decimal.substring(0, lengthOfDecimal - numberOfShifts) + "." 
                        + decimal.substring(lengthOfDecimal - numberOfShifts)+ fraction;
            }else{
                final int numberOfShifts = expt;
                final int lengthOfFraction = fraction.length();
                
                if(numberOfShifts > lengthOfFraction){
                    return decimal + fraction + stringOfZeros(numberOfShifts - lengthOfFraction) + ".0";
                }
                
                return decimal + fraction.substring(0, numberOfShifts) 
                         + "." + fraction.substring(numberOfShifts);
            } 
        }
        
        return value; // no need to re-format the value; it's already in decimal form.
    }
    
    private String stringOfZeros(int length){
        // requires: length >= 0
        // effects: returns a string of zeros of length specified
        //          with length zero corresponding to empty string.
        
        StringBuilder sb = new StringBuilder();;
        
        for(int i = 0; i < length; i++){
            sb.append("0");
        }
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object that){
        if(that instanceof Number){
            return this.number == ((Number)that).number;
        }
        
        return false;
    }
    
    @Override
    public int hashCode(){
        return Double.hashCode(number);
    }
    
    @Override
    public Expression differentiate(Expression x){
        return new Number(0);
    }
    
    @Override
    public Expression simplify(Map<Expression, Double> environment) {
        return this;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public double getValue() throws UnsupportedOperationException {
        return number;
    }

    @Override
    public boolean isVariable() {
        return false;
    }

    @Override
    public String getName() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("operation is undedfined on numbers");
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
        throw new UnsupportedOperationException("operation is undedfined on numbers");
    }

    @Override
    public Expression getRightExpression() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("operation is undedfined on numbers");
    }
    
}
