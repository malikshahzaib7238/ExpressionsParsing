/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    private static final double EPSILON = 0.0;
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;     }
    
    
    @Test
    public void testEqualsFor_SingleVariable(){
        Expression a1 = Expression.parse("a");
        
        Expression b1 = Expression.parse("A");
        
        assertEquals(a1,a1);
        assertNotEquals(a1,b1);
        
        assertNotEquals(b1, a1);
        assertEquals(b1, b1);
    }
    
    @Test
    public void testEqualsFor_SingleNumber(){
        Expression a1 = Expression.parse("1");
        Expression a2 = Expression.parse("1.00");
        
        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertEquals(a2, a1);
        assertEquals(a2, a2);
    }
    
    @Test
    public void testEqualsFor_A_Plus_B(){
        Expression a1 = Expression.parse("a+b");
        
        Expression b1 = Expression.parse("b+a");
        
        assertNotEquals(a1, b1);
    }
    
    @Test
    public void testEqualsFor_A_Plus_A(){
        Expression a1 = Expression.parse("a+a");
        
        Expression b1 = Expression.parse("2*a");
        
        Expression c1 = Expression.parse("a+a+a");
        
        assertNotEquals(a1, b1);
        assertNotEquals(a1, c1);
        assertNotEquals(b1, a1);
        assertNotEquals(c1, a1);
    }
    
    @Test
    public void testEqualsFor_A_Plus_0(){
        Expression a1 = Expression.parse("a+0");
        
        Expression b1 = Expression.parse("a");
        
        Expression c1 = Expression.parse("0+a");
        
        assertNotEquals(a1, b1);
        assertNotEquals(a1, c1);
        assertNotEquals(b1, a1);
        assertNotEquals(c1, a1);
    }
    
    @Test
    public void testEqualsFor_2_Plus_2(){
        Expression a1 = Expression.parse("2+2.00");
        Expression a2 = Expression.parse("2.00+2");
        
        Expression b1 = Expression.parse("4");
        
        Expression c1 = Expression.parse("2*2");
        
        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertNotEquals(a1, b1);
        assertNotEquals(a1, c1);
        
        assertEquals(a2, a1);
        assertEquals(a2, a2);
        assertNotEquals(a2, b1);
        assertNotEquals(a2, c1);
        
        assertNotEquals(b1, a1);
        assertNotEquals(b1, a2);
        
        assertNotEquals(c1, a1);
        assertNotEquals(c1, a2);
    }
    
    @Test
    public void testEqualsFor_A_Times_B(){
        Expression a1 = Expression.parse("a*b");
        
        Expression b1 = Expression.parse("b*a");
        
        assertNotEquals(a1, b1);
        assertNotEquals(b1, a1);
    }
    
    @Test
    public void testEqualsFor_1_Times_A(){
        Expression a1 = Expression.parse("1*a");
        
        Expression b1 = Expression.parse("a*1");
        
        Expression c1 = Expression.parse("a");
        
        assertNotEquals(a1, b1);
        assertNotEquals(a1, c1);
        assertNotEquals(b1, a1);
        assertNotEquals(c1, a1);
    }
    
    @Test
    public void testEqualsFor_0_Times_A(){
        Expression a1 = Expression.parse("0*a");
        
        Expression b1 = Expression.parse("a*0");
        
        Expression c1 = Expression.parse("0");
        
        assertNotEquals(a1, b1);
        assertNotEquals(a1, c1);
        assertNotEquals(b1, a1);
        assertNotEquals(c1, a1);
    }
    
    @Test
    public void testEqualsFor_A_Plus_B_Times_C(){
        Expression a1 = Expression.parse("a+b*c");
        Expression a2 = Expression.parse("((a)+(b*c))");
        Expression a3 = Expression.parse(" a + b * c ");
        
        Expression b1 = Expression.parse("(a+b)*c");
        Expression b2 = Expression.parse("((a+b)*c)");
        Expression b3 = Expression.parse(" ( a + b ) * c ");
        
        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertEquals(a1, a3);
        assertNotEquals(a1, b1);
        assertNotEquals(a1, b2);
        assertNotEquals(a1, b3);
        
        assertEquals(a2, a1);
        assertEquals(a2, a2);
        assertEquals(a2, a3);
        assertNotEquals(a2, b1);
        assertNotEquals(a2, b2);
        assertNotEquals(a2, b3);
        
        assertEquals(a3, a1);
        assertEquals(a3, a2);
        assertEquals(a3, a3);
        assertNotEquals(a3, b1);
        assertNotEquals(a3, b2);
        assertNotEquals(a3, b3);
        
        assertNotEquals(b1, a1);
        assertNotEquals(b1, a2);
        assertNotEquals(b1, a3);
        assertEquals(b1,b1);
        assertEquals(b1,b2);
        assertEquals(b1,b3);
        
        assertNotEquals(b2, a1);
        assertNotEquals(b2, a2);
        assertNotEquals(b2, a3);
        assertEquals(b2,b1);
        assertEquals(b2,b2);
        assertEquals(b2,b3);
        
        assertNotEquals(b3, a1);
        assertNotEquals(b3, a2);
        assertNotEquals(b3, a3);
        assertEquals(b3,b1);
        assertEquals(b3,b2);
        assertEquals(b3,b3);  
    }
    
    @Test
    public void testEqualsFor_A_Times_B_Plus_C(){
        Expression a1 = Expression.parse("a*c+b");
        Expression a2 = Expression.parse("((a*c)+(b))");
        Expression a3 = Expression.parse(" a * c + b ");
        
        Expression b1 = Expression.parse("a*(b+c)");
        Expression b2 = Expression.parse("((a)*((b+c)))");
        Expression b3 = Expression.parse(" a * ( b + c ) ");
        
        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertEquals(a1, a3);
        assertNotEquals(a1, b1);
        assertNotEquals(a1, b2);
        assertNotEquals(a1, b3);
        
        assertEquals(a2, a1);
        assertEquals(a2, a2);
        assertEquals(a2, a3);
        assertNotEquals(a2, b1);
        assertNotEquals(a2, b2);
        assertNotEquals(a2, b3);
        
        assertEquals(a3, a1);
        assertEquals(a3, a2);
        assertEquals(a3, a3);
        assertNotEquals(a3, b1);
        assertNotEquals(a3, b2);
        assertNotEquals(a3, b3);
        
        assertNotEquals(b1, a1);
        assertNotEquals(b1, a2);
        assertNotEquals(b1, a3);
        assertEquals(b1,b1);
        assertEquals(b1,b2);
        assertEquals(b1,b3);
        
        assertNotEquals(b2, a1);
        assertNotEquals(b2, a2);
        assertNotEquals(b2, a3);
        assertEquals(b2,b1);
        assertEquals(b2,b2);
        assertEquals(b2,b3);
        
        assertNotEquals(b3, a1);
        assertNotEquals(b3, a2);
        assertNotEquals(b3, a3);
        assertEquals(b3,b1);
        assertEquals(b3,b2);
        assertEquals(b3,b3);  
    }
    
    @Test
    public void testEqualsFor_A_Plus_B_Plus_C(){
        Expression a1 = Expression.parse("a+b+c");
        Expression a2 = Expression.parse("(a+b)+c");
        Expression a3 = Expression.parse("  a  +  b  +  c  ");
        
        Expression b1 = Expression.parse("a+(b+c)");
        Expression b2 = Expression.parse("((a)+((b+c)))");
        Expression b3 = Expression.parse("  a  +  (  b  +  c  )  ");
        
        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertEquals(a1, a3);
        assertNotEquals(a1, b1);
        assertNotEquals(a1, b2);
        assertNotEquals(a1, b3);
        
        assertEquals(a2, a1);
        assertEquals(a2, a2);
        assertEquals(a2, a3);
        assertNotEquals(a2, b1);
        assertNotEquals(a2, b2);
        assertNotEquals(a2, b3);
        
        assertEquals(a3, a1);
        assertEquals(a3, a2);
        assertEquals(a3, a3);
        assertNotEquals(a3, b1);
        assertNotEquals(a3, b2);
        assertNotEquals(a3, b3);
        
        assertNotEquals(b1, a1);
        assertNotEquals(b1, a2);
        assertNotEquals(b1, a3);
        assertEquals(b1,b1);
        assertEquals(b1,b2);
        assertEquals(b1,b3);
        
        assertNotEquals(b2, a1);
        assertNotEquals(b2, a2);
        assertNotEquals(b2, a3);
        assertEquals(b2,b1);
        assertEquals(b2,b2);
        assertEquals(b2,b3);
        
        assertNotEquals(b3, a1);
        assertNotEquals(b3, a2);
        assertNotEquals(b3, a3);
        assertEquals(b3,b1);
        assertEquals(b3,b2);
        assertEquals(b3,b3);
    }
    
    @Test
    public void testEqualsFor_A_Times_B_Times_C(){
        Expression a1 = Expression.parse("a*b*c");
        Expression a2 = Expression.parse("(a*b)*c");
        Expression a3 = Expression.parse("  a  *  b  *  c  ");
        
        Expression b1 = Expression.parse("a*(b*c)");
        Expression b2 = Expression.parse("((a)*((b*c)))");
        Expression b3 = Expression.parse("  a  *  (  b  *  c  )  ");
        
        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertEquals(a1, a3);
        assertNotEquals(a1, b1);
        assertNotEquals(a1, b2);
        assertNotEquals(a1, b3);
        
        assertEquals(a2, a1);
        assertEquals(a2, a2);
        assertEquals(a2, a3);
        assertNotEquals(a2, b1);
        assertNotEquals(a2, b2);
        assertNotEquals(a2, b3);
        
        assertEquals(a3, a1);
        assertEquals(a3, a2);
        assertEquals(a3, a3);
        assertNotEquals(a3, b1);
        assertNotEquals(a3, b2);
        assertNotEquals(a3, b3);
        
        assertNotEquals(b1, a1);
        assertNotEquals(b1, a2);
        assertNotEquals(b1, a3);
        assertEquals(b1,b1);
        assertEquals(b1,b2);
        assertEquals(b1,b3);
        
        assertNotEquals(b2, a1);
        assertNotEquals(b2, a2);
        assertNotEquals(b2, a3);
        assertEquals(b2,b1);
        assertEquals(b2,b2);
        assertEquals(b2,b3);
        
        assertNotEquals(b3, a1);
        assertNotEquals(b3, a2);
        assertNotEquals(b3, a3);
        assertEquals(b3,b1);
        assertEquals(b3,b2);
        assertEquals(b3,b3);
    }
    
    
    @Test
    public void tesHashCodeFor_Variable_SingleLetter(){
        Expression a1 = Expression.parse("a");
        Expression a2 = Expression.parse("((a))");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void tesHashCodeFor_Variable_MultipleLetter(){
        Expression a1 = Expression.parse("abc");
        Expression a2 = Expression.parse(" ( abc ) ");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Integer(){
        Expression a1 = Expression.parse("1");
        Expression a2 = Expression.parse("1.00");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Decimal(){
        Expression a1 = Expression.parse("1.5");
        Expression a2 = Expression.parse("1.500");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Variable_Plus_Variable(){
        Expression a1 = Expression.parse("a+b");
        Expression a2 = Expression.parse("((a)+(b))");

        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Variable_Plus_Number(){
        Expression a1 = Expression.parse("a+2");
        Expression a2 = Expression.parse("(a+2)");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Variable_Times_Variable(){
        Expression a1 = Expression.parse("a*b");
        Expression a2 = Expression.parse("((a)*(b))");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Variable_Times_Number(){
        Expression a1 = Expression.parse("a * 2");
        Expression a2 = Expression.parse("((a * 2))");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Decimal_Plus_Decimal(){
        Expression a1 = Expression.parse("0.50+0.5");
        Expression a2 = Expression.parse("0.5+0.50");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Integer_Plus_Integer(){
        Expression a1 = Expression.parse("1+2");
        Expression a2 = Expression.parse("1.0+2.00");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Decimal_Times_Decimal(){
        Expression a1 = Expression.parse("0.50*0.5");
        Expression a2 = Expression.parse("0.5*0.50");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_Integer_Times_Integer(){
        Expression a1 = Expression.parse("1*2");
        Expression a2 = Expression.parse("1.0*2.00");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_3Array_Grouping(){
        Expression a1 = Expression.parse("1 + a * 0.5");
        Expression a2 = Expression.parse("1 + (a * 0.5)");
        
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    public void testHashCodeFor_LargeNumber(){
        Expression a1 = Expression.parse("1000000000");
        Expression a2 = Expression.parse("1000*1000*1000").simplify(new HashMap<>());
        
        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    @Test
    public void testHashCodeFor_SmallNumber(){
        Expression a1 = Expression.parse("0.0000152587890625");
        Expression a2 = Expression.parse("0.0625*0.0625*0.0625*0.0625").simplify(new HashMap<>());
        
        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }
    
    
    @Test
    public void testToStringFor_SingleInteger(){
        Expression a1 = Expression.parse("1");
        Expression a2 = Expression.parse("1.00");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_SingleDecimal(){
        Expression a1 = Expression.parse("0.5");
        Expression a2 = Expression.parse("0.500");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_SingleVariable(){
        Expression a1 = Expression.parse("a");
        
        Expression b1 = Expression.parse("A");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(Expression.parse(b1.toString()), b1);
    }

    @Test
    public void testToStringFor_Sum_Plus_Term(){
        Expression a1 = Expression.parse("a+b+c");
        Expression a2 = Expression.parse("(a+b)+c");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_Term_Plus_Sum(){
        Expression a1 = Expression.parse("a+(b+c)");
        Expression a2 = Expression.parse("a + ( b + c )");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_Product_Plus_Term(){
        Expression a1 = Expression.parse("a*b+c");
        Expression a2 = Expression.parse("(a*b)+c");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_Term_Times_Sum(){
        Expression a1 = Expression.parse("a*(b+c)");
        Expression a2 = Expression.parse("((a)*((b+c)))");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_Product_Times_Term(){
        Expression a1 = Expression.parse("a*b*c");
        Expression a2 = Expression.parse("(a*b)*c");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_Term_Times_Product(){
        Expression a1 = Expression.parse("a*(b*c)");
        Expression a2 = Expression.parse("((a*(b*c)))");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_LargeNumber(){
        Expression a1 = Expression.parse("1000000000000");
        Expression a2 = Expression.parse("10000*10000*10000").simplify(new HashMap<>());;
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_SmallNumber(){
        Expression a1 = Expression.parse("0.000000000001");
        Expression a2 = Expression.parse("0.0001*0.0001*0.0001").simplify(new HashMap<>());;
        
        assertEquals(a1, a2); //If this assertion is false try another two expression that evaluate to == double values
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }

    
    @Test
    public void testParse_CaseSensitivity(){
        Expression a1 = Expression.parse("aB");
        Expression a2 = Expression.parse("ab");
        
        assertNotEquals(a1, a2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_EmptyExpression(){
        Expression.parse("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidCharacter(){
        Expression.parse("$");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidVariableName1(){
        Expression.parse("x1");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidVariableName2(){
        Expression.parse("x_y");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_MissingOperatorNumberTimesVariable(){
        Expression.parse("3x");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_MissingOperatorBetweenVariables(){
        Expression.parse("x y");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_MissingOperatorBetweenGroupings(){
        Expression.parse("(a)(b)");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_SpacedOutNumberRepresentation1(){
        Expression.parse("2 . 0");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_SpacedOutNumberRepresentation2(){
        Expression.parse("2 4");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_UnmatchedBracket1(){
        Expression.parse("(3");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_UnmatchedBracket2(){
        Expression.parse("3)");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_EmptyBracket(){
        Expression.parse("()");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidBracket(){
        Expression.parse("[3]");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_NegativeNumber(){
        Expression.parse("-1.00");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_Negation(){
        Expression.parse("-(a)");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_Subtraction(){
        Expression.parse("2 - 3");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_Exponentiation(){
        Expression.parse("x^2");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_Division(){
        Expression.parse("4 / 2");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_Modulus(){
        Expression.parse("10 % 5");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidNumberRepresentation(){
        Expression.parse("6.02e23");
    }
    
    
    @Test
    public void testDifferentiate_Integer(){
        Expression a1 = Expression.parse("2");
        Expression a2 = Expression.parse("2.00");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("0");
        
        assertEquals(d1, expected);
        assertEquals(d2, d1);
    }
    
    @Test
    public void testDifferentiate_Decimal(){
        Expression a1 = Expression.parse("0.5");
        Expression a2 = Expression.parse("0.500");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("0");
        
        assertEquals(d1, expected);
        assertEquals(d2, d1);
    }
    
    @Test
    public void testDifferentiate_Zero(){
        Expression a1 = Expression.parse("0");
        Expression a2 = Expression.parse("0.0");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("0");
        
        assertEquals(d1, expected);
        assertEquals(d2, d1);
    }
    
    @Test
    public void testDifferentiate_One(){
        Expression a1 = Expression.parse("1");
        Expression a2 = Expression.parse("1.00");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("0");
        
        assertEquals(d1, expected);
        assertEquals(d2, d1);
    }
    
    @Test
    public void testDifferentiate_VariableWithRespectToDifferentVariable(){
        Expression a1 = Expression.parse("y");
        Expression a2 = Expression.parse("X");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("0");
        
        assertEquals(d1, expected);
        assertEquals(d2, expected);
    }
    
    @Test
    public void testDifferentiate_VariableWithRespectToSameVariable(){
        Expression a1 = Expression.parse("x");
        Expression a2 = Expression.parse("(x)");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("1");
        
        assertEquals(d1, expected);
        assertEquals(d1, d2);
    }
    
    @Test
    public void testDifferentiate_xPlusxWithRepectTox(){
        Expression a1 = Expression.parse("x+x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("(1)+(1)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_xPlusyWithRespectTox(){
        Expression a1 = Expression.parse("x+y");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("(1)+(0)");
        
        assertEquals(d1, expected); 
    }
    
    @Test
    public void testDifferentiate_yPlusxWithRespectTox(){
        Expression a1 = Expression.parse("y+x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("(0)+(1)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_0PlusxWithRespectTox(){
        Expression a1 = Expression.parse("0+x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("(0)+(1)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_1PlusxWithRespectTox(){
        Expression a1 = Expression.parse("1+x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("(0)+(1)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_yPluszWithRespectTox(){
        Expression a1 = Expression.parse("y+z");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("(0)+(0)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_NumberPlusNumberWithRespectTox(){
        Expression a1 = Expression.parse("2+0.5");
        Expression a2 = Expression.parse("0.5+2");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("(0)+(0)");
        
        assertEquals(d1, expected);
        assertEquals(d2, expected);
    }
    
    @Test
    public void testDifferentiate_xTimesxWithRespectTox(){
        Expression a1 = Expression.parse("x*x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("x*(1) + x*(1)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_xTimesyWithRespectTox(){
        Expression a1 = Expression.parse("x*y");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("x*(0) + y*(1)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_yTimesxWithRespectTox(){
        Expression a1 = Expression.parse("y*x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("y*(1) + x*(0)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_0TimesxWithRespectTox(){
        Expression a1 = Expression.parse("0*x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("0*(1) + x*(0)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_1TimesxWithRespectTox(){
        Expression a1 = Expression.parse("1*x");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("1*(1) + x*(0)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_yTimeszWithRespectTox(){
        Expression a1 = Expression.parse("y*z");
        Expression x = Expression.parse("x"); 
        
        Expression d1 = a1.differentiate(x);
        Expression expected = Expression.parse("y*(0) + z*(0)");
        
        assertEquals(d1, expected);
    }
    
    @Test
    public void testDifferentiate_NumberTimesNumberWithRespectTox(){
        Expression a1 = Expression.parse("2*0.5");
        Expression a2 = Expression.parse("0.5*2");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected1 = Expression.parse("2*(0) + 0.5*(0)");
        Expression expected2 = Expression.parse("0.5*(0) + 2*(0)");
        
        assertEquals(d1, expected1);
        assertEquals(d2, expected2);
    }
    
    @Test
    public void testDifferentiate_xPlusx_Plusx_WithRespectTox(){
        Expression a1 = Expression.parse("x+x+x");
        Expression a2 = Expression.parse("(x+x)+x");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("((1) + (1)) + (1)");
        
        assertEquals(d1, expected);
        assertEquals(d1, d2);
    }
    
    @Test
    public void testDifferentiate_xPlus_xPlusx_WithRespectTox(){
        Expression a1 = Expression.parse("x+(x+x)");
        Expression a2 = Expression.parse("(x+(x+x))");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("(1) + ((1) + (1))");
        
        assertEquals(d1, expected);
        assertEquals(d1, d2);
    }
    
    @Test
    public void testDifferentiate_xPlus_xTimesx_WithRespectTox(){
        Expression a1 = Expression.parse("x+x*x");
        Expression a2 = Expression.parse("x+(x*x)");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("(1) + (x*(1) + x*(1))");
        
        assertEquals(d1, expected);
        assertEquals(d1, d2);
    }
    
    @Test
    public void testDifferentiate_xPlusx_Timesx_WithRespectTox(){
        Expression a1 = Expression.parse("(x+x)*x");
        Expression a2 = Expression.parse("((x+x)*x)");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("(x + x)*1 + x*((1) + (1))");
        
        assertEquals(d1, expected);
        assertEquals(d1, d2);
    }
    
    @Test
    public void testDifferentiate_xTimesx_Timesx_WithRespectTox(){
        Expression a1 = Expression.parse("x*x*x");
        Expression a2 = Expression.parse("(x*x)*x");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("(x*x)*(1) + x*(x*1 + x*1)");
        
        assertEquals(d1, expected);
        assertEquals(d1, d2);
    }
    
    @Test
    public void testDifferentiate_xTimes_xTimesx_WithRespectTox(){
        Expression a1 = Expression.parse("x*(x*x)");
        Expression a2 = Expression.parse("(x*(x*x))");
        Expression x = Expression.parse("x");
        
        Expression d1 = a1.differentiate(x);
        Expression d2 = a2.differentiate(x);
        Expression expected = Expression.parse("x*(x*1 + x*1) + (x*x)*1");
        
        assertEquals(d1, expected);
        assertEquals(d1, d2);
    }
    
    
    @Test
    public void testSimplify_NumberWithEmptyEnviroment(){
        Expression expr = Expression.parse("1.00");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(1.0, result.getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x2).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x3).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_NumberWithNonEmptyEnviroment(){
        Expression expr = Expression.parse("1.00");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("X"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(1.0, result.getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x2).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x3).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xWithEmptyEnviroment(){
        Expression expr = Expression.parse("x");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(0.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(0.1, result.simplify(x2).getValue(), EPSILON);
        assertEquals(0.5, result.simplify(x3).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(2.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(3.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xWithSingleAssigmentToX(){
        Expression expr = Expression.parse("x");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("X"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(0.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(0.1, result.simplify(x2).getValue(), EPSILON);
        assertEquals(0.5, result.simplify(x3).getValue(), EPSILON);
        assertEquals(1.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(2.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(3.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xWithSingleAssigmentTox(){
        Expression expr = Expression.parse("x");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 10.5);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(10.5, result.getValue(), EPSILON);
        assertEquals(10.5, result.simplify(x1).getValue(), EPSILON);
        assertEquals(10.5, result.simplify(x2).getValue(), EPSILON);
        assertEquals(10.5, result.simplify(x3).getValue(), EPSILON);
        assertEquals(10.5, result.simplify(x4).getValue(), EPSILON);
        assertEquals(10.5, result.simplify(x5).getValue(), EPSILON);
        assertEquals(10.5, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_1Plus2WithEmptyEnviroment(){
        Expression expr = Expression.parse("1+2");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(1.0+2.0, result.getValue(), EPSILON);
        assertEquals(1.0+2.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(1.0+2.0, result.simplify(x2).getValue(), EPSILON);
        assertEquals(1.0+2.0, result.simplify(x3).getValue(), EPSILON);
        assertEquals(1.0+2.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(1.0+2.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(1.0+2.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusxWithSingleAssigmentToy(){
        Expression expr = Expression.parse("x+x");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(0.0+0.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(0.1+0.1, result.simplify(x2).getValue(), EPSILON);
        assertEquals(0.5+0.5, result.simplify(x3).getValue(), EPSILON);
        assertEquals(1.0+1.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(3.0+3.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusxWithTwoAssigmentsTox_y(){
        Expression expr = Expression.parse("x+x");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 2.0); env.put(Expression.parse("y"), 3.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(2.0+2.0, result.getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x2).getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x3).getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlus0WithEmptyEnviroment(){
        Expression expr = Expression.parse("x+0");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(0.0+0.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(0.1+0.0, result.simplify(x2).getValue(), EPSILON);
        assertEquals(0.5+0.0, result.simplify(x3).getValue(), EPSILON);
        assertEquals(1.0+0.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(2.0+0.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(3.0+0.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_2PlusxWithEmptyEnviroment(){
        Expression expr = Expression.parse("2+x");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> x1 = new HashMap<>(); x1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> x2 = new HashMap<>(); x2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> x3 = new HashMap<>(); x3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> x4 = new HashMap<>(); x4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> x5 = new HashMap<>(); x5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> x6 = new HashMap<>(); x6.put(Expression.parse("x"), 3.0);
        
        assertEquals(2.0+0.0, result.simplify(x1).getValue(), EPSILON);
        assertEquals(2.0+0.1, result.simplify(x2).getValue(), EPSILON);
        assertEquals(2.0+0.5, result.simplify(x3).getValue(), EPSILON);
        assertEquals(2.0+1.0, result.simplify(x4).getValue(), EPSILON);
        assertEquals(2.0+2.0, result.simplify(x5).getValue(), EPSILON);
        assertEquals(2.0+3.0, result.simplify(x6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusyWithEmptyEnviroment(){
        Expression expr = Expression.parse("x+y");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0); a1.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 1.0); a2.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.0); a3.put(Expression.parse("y"), 2.0);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 0.1); a4.put(Expression.parse("y"), 0.2);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 0.6); a5.put(Expression.parse("y"), 0.4);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 1.0); a6.put(Expression.parse("y"), 2.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 3.0); a7.put(Expression.parse("y"), 4.1);
        
        assertEquals(0.0+0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(1.0+0.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.0+2.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(0.1+0.2, result.simplify(a4).getValue(), EPSILON);
        assertEquals(0.6+0.4, result.simplify(a5).getValue(), EPSILON);
        assertEquals(1.0+2.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(3.0+4.1, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusyWithSingleAssigmenttToy(){
        Expression expr = Expression.parse("x+y");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);

        assertEquals(0.0+0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1+0.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5+0.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0+0.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0+0.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0+0.0, result.simplify(a6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusyWithTwoeAssigmenttTox_z(){
        Expression expr = Expression.parse("x+y");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 0.5); env.put(Expression.parse("z"), 100.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("y"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("y"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("y"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("y"), 3.0);

        assertEquals(0.5+0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.5+0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5+0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals(0.5+1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(0.5+2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(0.5+3.0, result.simplify(a6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_0Times2WithEmptyEnvironment(){
        Expression expr = Expression.parse("0*2");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);

        assertEquals(0.0*2.0, result.getValue(), EPSILON);
        assertEquals(0.0*2.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*2.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.0*2.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(0.0*2.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(0.0*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(0.0*2.0, result.simplify(a6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_1Times3WithEmptyEnvironment(){
        Expression expr = Expression.parse("1*3");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);

        assertEquals(1.0*3.0, result.getValue(), EPSILON);
        assertEquals(1.0*3.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(1.0*3.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(1.0*3.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*3.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(1.0*3.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(1.0*3.0, result.simplify(a6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_2Times5WithEmptyEnvironment(){
        Expression expr = Expression.parse("2*5");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        
        assertEquals(2.0*5.0, result.getValue(), EPSILON);
        assertEquals(2.0*5.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(2.0*5.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(2.0*5.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(2.0*5.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*5.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(2.0*5.0, result.simplify(a6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesxWithEmptyEnvironment(){
        Expression expr = Expression.parse("x*x");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals(0.0*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*3.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*4.0, result.simplify(a7).getValue(), EPSILON);
        
    }
    
    @Test
    public void testSimplify_xTimes0WithEmptyEnvironment(){
        Expression expr = Expression.parse("x*0");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals(0.0*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*0.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*0.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*0.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*0.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*0.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*0.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_1TimesxWithEmptyEnvironment(){
        Expression expr = Expression.parse("1*x");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals(1.0*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(1.0*0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals(1.0*0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(1.0*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(1.0*3.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(1.0*4.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesyWithEmptyEnvironment(){
        Expression expr = Expression.parse("x*y");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0); a1.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 2.0); a2.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 2.0); a3.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 0.5); a4.put(Expression.parse("y"), 0.1);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0); a5.put(Expression.parse("y"), 0.5);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0); a6.put(Expression.parse("y"), 2.0);

        assertEquals(0.0*1.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(2.0*0.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(2.0*1.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(0.5*0.1, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*0.5, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*2.0, result.simplify(a6).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesyWithSingleAssignmentToy(){
        Expression expr = Expression.parse("x*y");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 1.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals(0.0*1.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*1.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*1.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*1.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*1.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*1.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesyWithTwoAssigmenttTox_z(){
        Expression expr = Expression.parse("x*y");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 2.0); env.put(Expression.parse("z"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("y"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("y"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("y"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("y"), 4.0);

        assertEquals(2.0*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(2.0*0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals(2.0*0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals(2.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(2.0*3.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(2.0*4.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesyWithSingleAssignmentTox(){
        Expression expr = Expression.parse("x*y");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("y"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("y"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("y"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("y"), 4.0);

        assertEquals(0.0*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.0*0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals(0.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(0.0*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(0.0*3.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(0.0*4.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimes_xPlusxWithEmptyEnviroment(){
        Expression expr = Expression.parse("x*(x+x)");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);
        
        assertEquals(0.0*(0.0+0.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*(0.1+0.1), result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*(0.5+0.5), result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*(1.0+1.0), result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*(2.0+2.0), result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*(3.0+3.0), result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*(4.0+4.0), result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimes_xPlusyWithxAssigned0(){
        Expression expr = Expression.parse("x*(x+y)");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("y"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("y"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("y"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("y"), 4.0);

        assertEquals(0.0*(0.0+0.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*(0.0+0.1), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*(0.0+0.5), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*(0.0+1.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*(0.0+2.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*(0.0+3.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.0*(0.0+4.0), result.simplify(a1).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimes_xPlusyWithEmptyEnvironment(){
        Expression expr = Expression.parse("x*(x+y)");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0); a1.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1); a2.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); a3.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0); a4.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0); a5.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0); a6.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0); a7.put(Expression.parse("y"), 0.0);

        assertEquals(0.0*(0.0+0.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*(0.1+0.0), result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*(0.5+0.0), result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*(1.0+0.0), result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*(2.0+0.0), result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*(3.0+0.0), result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*(4.0+0.0), result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimes_xPlusyWithyAssigned0(){
        Expression expr = Expression.parse("x*(x+y)");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 0.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals(0.0*(0.0+0.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*(0.1+0.0), result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*(0.5+0.0), result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*(1.0+0.0), result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*(2.0+0.0), result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*(3.0+0.0), result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*(4.0+0.0), result.simplify(a7).getValue(), EPSILON);

    }
    
    @Test
    public void testSimplify_xTimes_xPlusyWithxAssigned1(){
        Expression expr = Expression.parse("x*(x+y)");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 1.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("y"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("y"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("y"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("y"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("y"), 4.0);

        assertEquals(1.0*(1.0+0.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals(1.0*(1.0+0.1), result.simplify(a2).getValue(), EPSILON);
        assertEquals(1.0*(1.0+0.5), result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*(1.0+1.0), result.simplify(a4).getValue(), EPSILON);
        assertEquals(1.0*(1.0+2.0), result.simplify(a5).getValue(), EPSILON);
        assertEquals(1.0*(1.0+3.0), result.simplify(a6).getValue(), EPSILON);
        assertEquals(1.0*(1.0+4.0), result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimes_yPlusyWithyAssignedHalf(){
        Expression expr = Expression.parse("x*(y+y)");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 0.5);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals(0.0*(0.5+0.5), result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*(0.5+0.5), result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*(0.5+0.5), result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*(0.5+0.5), result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*(0.5+0.5), result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*(0.5+0.5), result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*(0.5+0.5), result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_zPlusy_timesxWithEmptyEnvironment(){
        Expression expr = Expression.parse("(z+y)*x");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0); a1.put(Expression.parse("z"), 2.0); a1.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1); a2.put(Expression.parse("z"), 2.0); a2.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); a3.put(Expression.parse("z"), 2.0); a3.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0); a4.put(Expression.parse("z"), 2.0); a4.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0); a5.put(Expression.parse("z"), 2.0); a5.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0); a6.put(Expression.parse("z"), 2.0); a6.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0); a7.put(Expression.parse("z"), 2.0); a7.put(Expression.parse("y"), 3.0);

        assertEquals((2.0+3.0)*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals((2.0+3.0)*0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals((2.0+3.0)*0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals((2.0+3.0)*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals((2.0+3.0)*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals((2.0+3.0)*3.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals((2.0+3.0)*4.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesy_Plus_zTimesxEmptyEnviroment(){
        Expression expr = Expression.parse("x*y + z*x");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0); a1.put(Expression.parse("z"), 2.0); a1.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1); a2.put(Expression.parse("z"), 2.0); a2.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); a3.put(Expression.parse("z"), 2.0); a3.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0); a4.put(Expression.parse("z"), 2.0); a4.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0); a5.put(Expression.parse("z"), 2.0); a5.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0); a6.put(Expression.parse("z"), 2.0); a6.put(Expression.parse("y"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0); a7.put(Expression.parse("z"), 2.0); a7.put(Expression.parse("y"), 3.0);

        assertEquals(0.0*2.0 + 3.0*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*2.0 + 3.0*0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*2.0 + 3.0*0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*2.0 + 3.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*2.0 + 3.0*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*2.0 + 3.0*3.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*2.0 + 3.0*4.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesy_Plus_zTimesxWithAssigmentsToy_z(){
        Expression expr = Expression.parse("x*y + z*x");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 2.0); env.put(Expression.parse("z"), 3.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5);
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);
        
        assertEquals(0.0*2.0 + 3.0*0.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*2.0 + 3.0*0.1, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*2.0 + 3.0*0.5, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*2.0 + 3.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*2.0 + 3.0*2.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*2.0 + 3.0*3.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*2.0 + 3.0*4.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusy_Times_xPlusyWithEmptyEnvironment(){
        Expression expr = Expression.parse("(x+y)*(x+y)");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0); a1.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1); a2.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); a3.put(Expression.parse("y"), 1.0); 
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0); a4.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0); a5.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0); a6.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0); a7.put(Expression.parse("y"), 1.0);
        
        assertEquals((0.0+1.0)*(0.0+1.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals((0.1+1.0)*(0.1+1.0), result.simplify(a2).getValue(), EPSILON);
        assertEquals((0.5+1.0)*(0.5+1.0), result.simplify(a3).getValue(), EPSILON);
        assertEquals((1.0+1.0)*(1.0+1.0), result.simplify(a4).getValue(), EPSILON);
        assertEquals((2.0+1.0)*(2.0+1.0), result.simplify(a5).getValue(), EPSILON);
        assertEquals((3.0+1.0)*(3.0+1.0), result.simplify(a6).getValue(), EPSILON);
        assertEquals((4.0+1.0)*(4.0+1.0), result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusy_Times_xPlusyWithyAssigned1(){
        Expression expr = Expression.parse("(x+y)*(x+y)");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 1.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); 
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals((0.0+1.0)*(0.0+1.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals((0.1+1.0)*(0.1+1.0), result.simplify(a2).getValue(), EPSILON);
        assertEquals((0.5+1.0)*(0.5+1.0), result.simplify(a3).getValue(), EPSILON);
        assertEquals((1.0+1.0)*(1.0+1.0), result.simplify(a4).getValue(), EPSILON);
        assertEquals((2.0+1.0)*(2.0+1.0), result.simplify(a5).getValue(), EPSILON);
        assertEquals((3.0+1.0)*(3.0+1.0), result.simplify(a6).getValue(), EPSILON);
        assertEquals((4.0+1.0)*(4.0+1.0), result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesx_Plus_2TimesxTimesy_Plus_yTimesyWithEmptyEnvironment(){
        Expression expr = Expression.parse("x*x + 2*x*y + y*y");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0); a1.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1); a2.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); a3.put(Expression.parse("y"), 1.0); 
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0); a4.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0); a5.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0); a6.put(Expression.parse("y"), 1.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0); a7.put(Expression.parse("y"), 1.0);

        assertEquals(0.0*0.0 + 2.0*0.0 + 1.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*0.1 + 2.0*0.1 + 1.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*0.5 + 2.0*0.5 + 1.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*1.0 + 2.0*1.0 + 1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*2.0 + 2.0*2.0 + 1.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*3.0 + 2.0*3.0 + 1.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*4.0 + 2.0*4.0 + 1.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesx_Plus_2TimesxTimesy_Plus_yTimesyAssigned1(){
        Expression expr = Expression.parse("x*x + 2*x*y + y*y");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("y"), 1.0);
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); 
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);
        
        assertEquals(0.0*0.0 + 2.0*0.0 + 1.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*0.1 + 2.0*0.1 + 1.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*0.5 + 2.0*0.5 + 1.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*1.0 + 2.0*1.0 + 1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*2.0 + 2.0*2.0 + 1.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*3.0 + 2.0*3.0 + 1.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*4.0 + 2.0*4.0 + 1.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlus1_Times_xPlus1WithEmptyEnvironment(){
        Expression expr = Expression.parse("(x+1)*(x+1)");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); 
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals((0.0+1.0)*(0.0+1.0), result.simplify(a1).getValue(), EPSILON);
        assertEquals((0.1+1.0)*(0.1+1.0), result.simplify(a2).getValue(), EPSILON);
        assertEquals((0.5+1.0)*(0.5+1.0), result.simplify(a3).getValue(), EPSILON);
        assertEquals((1.0+1.0)*(1.0+1.0), result.simplify(a4).getValue(), EPSILON);
        assertEquals((2.0+1.0)*(2.0+1.0), result.simplify(a5).getValue(), EPSILON);
        assertEquals((3.0+1.0)*(3.0+1.0), result.simplify(a6).getValue(), EPSILON);
        assertEquals((4.0+1.0)*(4.0+1.0), result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesx_Plus_2Timesx_Plus1WithEmptyEnvironment(){
        Expression expr = Expression.parse("x*x + 2*x + 1");
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = expr.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); 
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);

        assertEquals(0.0*0.0 + 2.0*0.0 + 1.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*0.1 + 2.0*0.1 + 1.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*0.5 + 2.0*0.5 + 1.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*1.0 + 2.0*1.0 + 1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*2.0 + 2.0*2.0 + 1.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*3.0 + 2.0*3.0 + 1.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*4.0 + 2.0*4.0 + 1.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimes_yTimeszWithAllVariablesAssigned(){
        Expression expr = Expression.parse("x*(y*z)");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 3.0); env.put(Expression.parse("y"), 4.0); env.put(Expression.parse("z"), 5.0);
        
        Expression result = expr.simplify(env);
        
        assertEquals(3.0*(4.0*5.0), result.getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xTimesy_TimeszWithAllVariablesAssigned(){
        Expression expr = Expression.parse("(x*y)*z");
        Map<Expression, Double> env = new HashMap<>(); env.put(Expression.parse("x"), 3.0); env.put(Expression.parse("y"), 4.0); env.put(Expression.parse("z"), 5.0);
        
        Expression result = expr.simplify(env);
        
        assertEquals((3.0*4.0)*5.0, result.getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlus_yPluszWithAllVariablesAssigned(){
        Expression expr = Expression.parse("x+(y+z)");
        Map<Expression, Double> env = new HashMap<>();
        env.put(Expression.parse("x"), 3.0);
        env.put(Expression.parse("y"), 4.0);
        env.put(Expression.parse("z"), 5.0);
        
        Expression result = expr.simplify(env);
        
        assertEquals(3.0+(4.0+5.0), result.getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_xPlusy_PluszWithAllVariablesAssigned(){
        Expression expr = Expression.parse("(x+y)+z");
        Map<Expression, Double> env = new HashMap<>();
        env.put(Expression.parse("x"), 3.0);
        env.put(Expression.parse("y"), 4.0);
        env.put(Expression.parse("z"), 5.0);
        
        Expression result = expr.simplify(env);
        
        assertEquals((3.0+4.0)+5.0, result.getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_Differentiate_x_Plus_x_ThenSimplify(){
        Expression expr = Expression.parse("x+x");
        Expression x = Expression.parse("x");
        Expression deriv = expr.differentiate(x);
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = deriv.simplify(env);
        
        assertEquals(1.0+1.0, result.getValue(), EPSILON);
    }
    
    @Test
    public void testSimplify_Differentiate_x_Times_x_ThenSimplify(){
        Expression expr = Expression.parse("x*x");
        Expression x = Expression.parse("x");
        Expression deriv = expr.differentiate(x);
        Map<Expression, Double> env = new HashMap<>();
        
        Expression result = deriv.simplify(env);
        
        Map<Expression, Double> a1 = new HashMap<>(); a1.put(Expression.parse("x"), 0.0);
        Map<Expression, Double> a2 = new HashMap<>(); a2.put(Expression.parse("x"), 0.1);
        Map<Expression, Double> a3 = new HashMap<>(); a3.put(Expression.parse("x"), 0.5); 
        Map<Expression, Double> a4 = new HashMap<>(); a4.put(Expression.parse("x"), 1.0);
        Map<Expression, Double> a5 = new HashMap<>(); a5.put(Expression.parse("x"), 2.0);
        Map<Expression, Double> a6 = new HashMap<>(); a6.put(Expression.parse("x"), 3.0);
        Map<Expression, Double> a7 = new HashMap<>(); a7.put(Expression.parse("x"), 4.0);
        
        assertEquals(0.0*1.0 + 0.0*1.0, result.simplify(a1).getValue(), EPSILON);
        assertEquals(0.1*1.0 + 0.1*1.0, result.simplify(a2).getValue(), EPSILON);
        assertEquals(0.5*1.0 + 0.5*1.0, result.simplify(a3).getValue(), EPSILON);
        assertEquals(1.0*1.0 + 1.0*1.0, result.simplify(a4).getValue(), EPSILON);
        assertEquals(2.0*1.0 + 2.0*1.0, result.simplify(a5).getValue(), EPSILON);
        assertEquals(3.0*1.0 + 3.0*1.0, result.simplify(a6).getValue(), EPSILON);
        assertEquals(4.0*1.0 + 4.0*1.0, result.simplify(a7).getValue(), EPSILON);
    }
    
    
    @Test
    public void testNumberObservers_Integer(){
        Expression a1 = Expression.parse("2");
        
        assertTrue(a1.isNumber());
        assertTrue(a1.getValue() == 2.0);
    }
    
    @Test
    public void testNumberObservers_Decimal(){
        Expression a1 = Expression.parse("0.00001");
        
        assertTrue(a1.isNumber());
        assertTrue(a1.getValue() == 0.00001);
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void testNumberObservers_Variable(){
        Expression a1 = Expression.parse("x");
        
        assertFalse(a1.isNumber());
        a1.getValue();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testNumberObservers_NumberPlusNumber(){
        Expression a1 = Expression.parse("2+3");
        
        assertFalse(a1.isNumber());
        a1.getValue();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testNumberObservers_VariablePlusVariable(){
        Expression a1 = Expression.parse("a+b");
        
        assertFalse(a1.isNumber());
        a1.getValue();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testNumberObservers_NumberTimesNumber(){
        Expression a1 = Expression.parse("2*3");
        
        assertFalse(a1.isNumber());
        a1.getValue();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testNumberObservers_VariableTimesVariable(){
        Expression a1 = Expression.parse("a*b");
        
        assertFalse(a1.isNumber());
        a1.getValue();
    }
    
    
    @Test
    public void testVariableObservers_VaraibleName_a(){
        Expression a1 = Expression.parse("a");
        
        assertTrue(a1.isVariable());
        assertEquals("a", a1.getName());
    }
    
    @Test
    public void testVariableObservers_VariableName_A(){
        Expression a1 = Expression.parse("A");
        
        assertTrue(a1.isVariable());
        assertEquals("A", a1.getName());
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testVariableObservers_Number(){
        Expression a1 = Expression.parse("2.2");
        
        assertFalse(a1.isVariable());
        a1.getName();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testVariableObservers_aPlus0(){
        Expression a1 = Expression.parse("a+0");
        
        assertFalse(a1.isVariable());
        a1.getName();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testVariableObservers_a_Plus_a(){
        Expression a1 = Expression.parse("a+a");
        
        assertFalse(a1.isVariable());
        a1.getName();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testVariableObservers_1_Times_a(){
        Expression a1 = Expression.parse("1*a");
        
        assertFalse(a1.isVariable());
        a1.getName();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testVariableObservers_a_Times_a(){
        Expression a1 = Expression.parse("a*a");
        
        assertFalse(a1.isVariable());
        a1.getName();
    }
    
    
    @Test (expected = UnsupportedOperationException.class)
    public void testSumObservers_Number(){
        Expression a1 = Expression.parse("1");
        
        assertFalse(a1.isSum());
        a1.getLeftExpression();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testSumObservers_Variable(){
        Expression a1 = Expression.parse("x");
        
        assertFalse(a1.isSum());
        a1.getRightExpression();
    }
    
    @Test
    public void testSumObservers_2Plus3(){
        Expression a1 = Expression.parse("2+3");
        
        assertTrue(a1.isSum());
        assertEquals(Expression.parse("2"), a1.getLeftExpression());
        assertEquals(Expression.parse("3"), a1.getRightExpression());
    }
    
    @Test
    public void testSumObservers_aPlusb(){
        Expression a1 = Expression.parse("a+b");
        
        assertTrue(a1.isSum());
        assertEquals(Expression.parse("a"), a1.getLeftExpression());
        assertEquals(Expression.parse("b"), a1.getRightExpression());
    }
    
    @Test
    public void testSumObservers_aPlusb_Plus_cPlusd(){
        Expression a1 = Expression.parse("(a+b)+(c+d)");
        
        assertTrue(a1.isSum());
        assertEquals(Expression.parse("a+b"), a1.getLeftExpression());
        assertEquals(Expression.parse("c+d"), a1.getRightExpression());
    }
    
    @Test
    public void testSumObservers_aTimes_Plus_cTimesd(){
        Expression a1 = Expression.parse("(a*b)+(c*d)");
        
        assertTrue(a1.isSum());
        assertEquals(Expression.parse("a*b"), a1.getLeftExpression());
        assertEquals(Expression.parse("c*d"), a1.getRightExpression());
    }
    
    @Test
    public void testSumObservers_a_Times_b(){
        Expression a1 = Expression.parse("a*b");
        
        assertFalse(a1.isSum());
    }
    
    
    @Test (expected = UnsupportedOperationException.class)
    public void testProductObservers_Number(){
        Expression a1 = Expression.parse("1.5");
        
        assertFalse(a1.isProduct());
        a1.getRightExpression();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testProductObservers_Variable(){
        Expression a1 = Expression.parse("a");
        
        assertFalse(a1.isProduct());
        a1.getLeftExpression();
    }
    
    @Test
    public void testProductObservers_aPlusb(){
        Expression a1 = Expression.parse("a+b");
        
        assertFalse(a1.isProduct());
    }
    
    @Test
    public void testProductObservers_2Times3(){
        Expression a1 = Expression.parse("2*3");
        
        assertTrue(a1.isProduct());
        assertEquals(Expression.parse("2"), a1.getLeftExpression());
        assertEquals(Expression.parse("3"), a1.getRightExpression());
    }
    
    @Test
    public void testProductObservers_aTimesb(){
        Expression a1 = Expression.parse("a*b");
        
        assertTrue(a1.isProduct());
        assertEquals(Expression.parse("a"), a1.getLeftExpression());
        assertEquals(Expression.parse("b"), a1.getRightExpression());
    }
    
    @Test
    public void testSumObservers_aPlusb_Times_cPlusd(){
        Expression a1 = Expression.parse("(a+b)*(c+d)");
        
        assertTrue(a1.isProduct());
        assertEquals(Expression.parse("a+b"), a1.getLeftExpression());
        assertEquals(Expression.parse("c+d"), a1.getRightExpression());
    }
    
    @Test
    public void testSumObservers_aTimes_Times_cTimesd(){
        Expression a1 = Expression.parse("(a*b)*(c*d)");
        
        assertTrue(a1.isProduct());
        assertEquals(Expression.parse("a*b"), a1.getLeftExpression());
        assertEquals(Expression.parse("c*d"), a1.getRightExpression());
    }

}
