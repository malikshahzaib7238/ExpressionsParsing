package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CommandsTest {

    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    
    @Test
    public void testCommandsDifferentiate_xWithRespectTox(){
        String a1 = Commands.differentiate("x", "x");
        String a2 = Commands.differentiate("(x)", "x");
        String a3 = Commands.differentiate("((x))", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("x").differentiate(x);
        Expression b2 = Expression.parse("(x)").differentiate(x);
        Expression b3 = Expression.parse("((x))").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
    }
    
    @Test
    public void testCommandsDifferentiate_yWithRespectTox(){
        String a1 = Commands.differentiate("y", "x");
        String a2 = Commands.differentiate("(y)", "x");
        String a3 = Commands.differentiate("((y))", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("y").differentiate(x);
        Expression b2 = Expression.parse("(y)").differentiate(x);
        Expression b3 = Expression.parse("((y))").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
    }
    
    @Test
    public void testCommandsDifferentiate_XWithRespectTox(){
        String a1 = Commands.differentiate("X", "x");
        String a2 = Commands.differentiate("(X)", "x");
        String a3 = Commands.differentiate("((X))", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("X").differentiate(x);
        Expression b2 = Expression.parse("(X)").differentiate(x);
        Expression b3 = Expression.parse("((X))").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
    }
    
    @Test
    public void testCommandsDifferentiate_IntegerWithRespectTox(){
        String a1 = Commands.differentiate("2", "x");
        String a2 = Commands.differentiate("(2)", "x");
        String a3 = Commands.differentiate("((2))", "x");
        String a4 = Commands.differentiate("2.00", "x");
        String a5 = Commands.differentiate("002", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("2").differentiate(x);
        Expression b2 = Expression.parse("(2)").differentiate(x);
        Expression b3 = Expression.parse("((2))").differentiate(x);
        Expression b4 = Expression.parse("2.00").differentiate(x);
        Expression b5 = Expression.parse("002").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
    }
    
    @Test
    public void testCommandsDifferentiate_ZeroWithRespectTox(){
        String a1 = Commands.differentiate("0", "x");
        String a2 = Commands.differentiate("(0)", "x");
        String a3 = Commands.differentiate("((0))", "x");
        String a4 = Commands.differentiate("0.00", "x");
        String a5 = Commands.differentiate("00", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("0").differentiate(x);
        Expression b2 = Expression.parse("(0)").differentiate(x);
        Expression b3 = Expression.parse("((0))").differentiate(x);
        Expression b4 = Expression.parse("0.00").differentiate(x);
        Expression b5 = Expression.parse("00").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
    }
    
    @Test
    public void testCommandsDifferentiate_OneWithRespectTox(){
        String a1 = Commands.differentiate("1", "x");
        String a2 = Commands.differentiate("(1)", "x");
        String a3 = Commands.differentiate("((1))", "x");
        String a4 = Commands.differentiate("1.00", "x");
        String a5 = Commands.differentiate("001", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("1").differentiate(x);
        Expression b2 = Expression.parse("(1)").differentiate(x);
        Expression b3 = Expression.parse("((1))").differentiate(x);
        Expression b4 = Expression.parse("1.00").differentiate(x);
        Expression b5 = Expression.parse("001").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
    }
    
    @Test
    public void testCommandsDifferentiate_DecimalWithRespectTox(){
        String a1 = Commands.differentiate("0.5", "x");
        String a2 = Commands.differentiate("(0.5)", "x");
        String a3 = Commands.differentiate("((0.5))", "x");
        String a4 = Commands.differentiate("0.500", "x");
        String a5 = Commands.differentiate("00.5", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("0.5").differentiate(x);
        Expression b2 = Expression.parse("(0.5)").differentiate(x);
        Expression b3 = Expression.parse("((0.5))").differentiate(x);
        Expression b4 = Expression.parse("0.500").differentiate(x);
        Expression b5 = Expression.parse("00.5").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
    }
    
    @Test
    public void testCommandsDifferentiate_xPlusxRespectTox(){
        String a1 = Commands.differentiate("x+x", "x");
        String a2 = Commands.differentiate(" x + x ", "x");
        String a3 = Commands.differentiate("(x+x)", "x");
        String a4 = Commands.differentiate("(x)+x", "x");
        String a5 = Commands.differentiate("x+(x)", "x");
        String a6 = Commands.differentiate("(x)+(x)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("x+x").differentiate(x);
        Expression b2 = Expression.parse(" x + x ").differentiate(x);
        Expression b3 = Expression.parse("(x+x)").differentiate(x);
        Expression b4 = Expression.parse("(x)+x").differentiate(x);
        Expression b5 = Expression.parse("x+(x)").differentiate(x);
        Expression b6 = Expression.parse("(x)+(x)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_xPlusyRespectTox(){
        String a1 = Commands.differentiate("x+y", "x");
        String a2 = Commands.differentiate(" x + y ", "x");
        String a3 = Commands.differentiate("(x+y)", "x");
        String a4 = Commands.differentiate("(x)+y", "x");
        String a5 = Commands.differentiate("x+(y)", "x");
        String a6 = Commands.differentiate("(x)+(y)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("x+y").differentiate(x);
        Expression b2 = Expression.parse(" x + y ").differentiate(x);
        Expression b3 = Expression.parse("(x+y)").differentiate(x);
        Expression b4 = Expression.parse("(x)+y").differentiate(x);
        Expression b5 = Expression.parse("x+(y)").differentiate(x);
        Expression b6 = Expression.parse("(x)+(y)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_yPlusxRespectTox(){
        String a1 = Commands.differentiate("y+x", "x");
        String a2 = Commands.differentiate(" y + x ", "x");
        String a3 = Commands.differentiate("(y+x)", "x");
        String a4 = Commands.differentiate("(y)+x", "x");
        String a5 = Commands.differentiate("y+(x)", "x");
        String a6 = Commands.differentiate("(y)+(x)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("y+x").differentiate(x);
        Expression b2 = Expression.parse(" y + x ").differentiate(x);
        Expression b3 = Expression.parse("(y+x)").differentiate(x);
        Expression b4 = Expression.parse("(y)+x").differentiate(x);
        Expression b5 = Expression.parse("y+(x)").differentiate(x);
        Expression b6 = Expression.parse("(y)+(x)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_yPlusyRespectTox(){
        String a1 = Commands.differentiate("y+y", "x");
        String a2 = Commands.differentiate(" y + y ", "x");
        String a3 = Commands.differentiate("(y+y)", "x");
        String a4 = Commands.differentiate("(y)+y", "x");
        String a5 = Commands.differentiate("y+(y)", "x");
        String a6 = Commands.differentiate("(y)+(y)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("y+y").differentiate(x);
        Expression b2 = Expression.parse(" y + y ").differentiate(x);
        Expression b3 = Expression.parse("(y+y)").differentiate(x);
        Expression b4 = Expression.parse("(y)+y").differentiate(x);
        Expression b5 = Expression.parse("y+(y)").differentiate(x);
        Expression b6 = Expression.parse("(y)+(y)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_xPlus0RespectTox(){
        String a1 = Commands.differentiate("x+0", "x");
        String a2 = Commands.differentiate("x+0.0", "x");
        String a3 = Commands.differentiate("(x+0)", "x");
        String a4 = Commands.differentiate("(x)+0", "x");
        String a5 = Commands.differentiate("x+(0)", "x");
        String a6 = Commands.differentiate("(x)+(0)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("x+0").differentiate(x);
        Expression b2 = Expression.parse("x+0.0").differentiate(x);
        Expression b3 = Expression.parse("(x+0)").differentiate(x);
        Expression b4 = Expression.parse("(x)+0").differentiate(x);
        Expression b5 = Expression.parse("x+(0)").differentiate(x);
        Expression b6 = Expression.parse("(x)+(0)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_0PlusxRespectTox(){
        String a1 = Commands.differentiate("0+x", "x");
        String a2 = Commands.differentiate("0.0+x", "x");
        String a3 = Commands.differentiate("(0+x)", "x");
        String a4 = Commands.differentiate("(0)+x", "x");
        String a5 = Commands.differentiate("0+(x)", "x");
        String a6 = Commands.differentiate("(0)+(x)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("0+x").differentiate(x);
        Expression b2 = Expression.parse("0.0+x").differentiate(x);
        Expression b3 = Expression.parse("(0+x)").differentiate(x);
        Expression b4 = Expression.parse("(0)+x").differentiate(x);
        Expression b5 = Expression.parse("0+(x)").differentiate(x);
        Expression b6 = Expression.parse("(0)+(x)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_xPlus1RespectTox(){
        String a1 = Commands.differentiate("x+1", "x");
        String a2 = Commands.differentiate("x+1.000", "x");
        String a3 = Commands.differentiate("(x+1)", "x");
        String a4 = Commands.differentiate("(x)+1", "x");
        String a5 = Commands.differentiate("x+(1)", "x");
        String a6 = Commands.differentiate("(x)+(1)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("x+1").differentiate(x);
        Expression b2 = Expression.parse("x+1.000").differentiate(x);
        Expression b3 = Expression.parse("(x+1)").differentiate(x);
        Expression b4 = Expression.parse("(x)+1").differentiate(x);
        Expression b5 = Expression.parse("x+(1)").differentiate(x);
        Expression b6 = Expression.parse("(x)+(1)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_1PlusxRespectTox(){
        String a1 = Commands.differentiate("1+x", "x");
        String a2 = Commands.differentiate("1.000+x", "x");
        String a3 = Commands.differentiate("(1+x)", "x");
        String a4 = Commands.differentiate("(1)+x", "x");
        String a5 = Commands.differentiate("1+(x)", "x");
        String a6 = Commands.differentiate("(1)+(x)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("1+x").differentiate(x);
        Expression b2 = Expression.parse("1.000+x").differentiate(x);
        Expression b3 = Expression.parse("(1+x)").differentiate(x);
        Expression b4 = Expression.parse("(1)+x").differentiate(x);
        Expression b5 = Expression.parse("1+(x)").differentiate(x);
        Expression b6 = Expression.parse("(1)+(x)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_xPlusHalfRespectTox(){
        String a1 = Commands.differentiate("x+0.5", "x");
        String a2 = Commands.differentiate("x+00.50", "x");
        String a3 = Commands.differentiate("(x+0.5)", "x");
        String a4 = Commands.differentiate("(x)+0.5", "x");
        String a5 = Commands.differentiate("x+(0.5)", "x");
        String a6 = Commands.differentiate("(x)+(0.5)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("x+0.5").differentiate(x);
        Expression b2 = Expression.parse("x+00.50").differentiate(x);
        Expression b3 = Expression.parse("(x+0.5)").differentiate(x);
        Expression b4 = Expression.parse("(x)+0.5").differentiate(x);
        Expression b5 = Expression.parse("x+(0.5)").differentiate(x);
        Expression b6 = Expression.parse("(x)+(0.5)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }

    @Test
    public void testCommandsDifferentiate_HalfPlusxRespectTox(){
        String a1 = Commands.differentiate("0.5+x", "x");
        String a2 = Commands.differentiate("00.50+x", "x");
        String a3 = Commands.differentiate("(0.5+x)", "x");
        String a4 = Commands.differentiate("(0.5)+x", "x");
        String a5 = Commands.differentiate("0.5+(x)", "x");
        String a6 = Commands.differentiate("(0.5)+(x)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("0.5+x").differentiate(x);
        Expression b2 = Expression.parse("00.50+x").differentiate(x);
        Expression b3 = Expression.parse("(0.5+x)").differentiate(x);
        Expression b4 = Expression.parse("(0.5)+x").differentiate(x);
        Expression b5 = Expression.parse("0.5+(x)").differentiate(x);
        Expression b6 = Expression.parse("(0.5)+(x)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_yPlus0RespectTox(){
        String a1 = Commands.differentiate("y+0", "x");
        String a2 = Commands.differentiate(" y+00.00 ", "x");
        String a3 = Commands.differentiate("(y+0)", "x");
        String a4 = Commands.differentiate("(y)+0", "x");
        String a5 = Commands.differentiate("y+(0)", "x");
        String a6 = Commands.differentiate("(y)+(0)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("y+0").differentiate(x);
        Expression b2 = Expression.parse(" y + 0 ").differentiate(x);
        Expression b3 = Expression.parse("(y+0)").differentiate(x);
        Expression b4 = Expression.parse("(y)+0").differentiate(x);
        Expression b5 = Expression.parse("y+(0)").differentiate(x);
        Expression b6 = Expression.parse("(y)+(0)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_2Plus1RespectTox(){
        String a1 = Commands.differentiate("2+1", "x");
        String a2 = Commands.differentiate(" 2 + 1 ", "x");
        String a3 = Commands.differentiate("(2+1)", "x");
        String a4 = Commands.differentiate("(2)+1", "x");
        String a5 = Commands.differentiate("2+(1)", "x");
        String a6 = Commands.differentiate("(2)+(1)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("2+1").differentiate(x);
        Expression b2 = Expression.parse(" 2 + 1 ").differentiate(x);
        Expression b3 = Expression.parse("(2+1)").differentiate(x);
        Expression b4 = Expression.parse("(2)+1").differentiate(x);
        Expression b5 = Expression.parse("2+(1)").differentiate(x);
        Expression b6 = Expression.parse("(2)+(1)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    @Test
    public void testCommandsDifferentiate_HalfPlusHalfRespectTox(){
        String a1 = Commands.differentiate("0.5+0.5", "x");
        String a2 = Commands.differentiate(" 0.5 + 0.5 ", "x");
        String a3 = Commands.differentiate("(0.5+0.5)", "x");
        String a4 = Commands.differentiate("(0.5)+0.5", "x");
        String a5 = Commands.differentiate("0.5+(0.5)", "x");
        String a6 = Commands.differentiate("(0.5)+(0.5)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("0.5+0.5").differentiate(x);
        Expression b2 = Expression.parse(" 0.5 + 0.5 ").differentiate(x);
        Expression b3 = Expression.parse("(0.5+0.5)").differentiate(x);
        Expression b4 = Expression.parse("(0.5)+0.5").differentiate(x);
        Expression b5 = Expression.parse("0.5+(0.5)").differentiate(x);
        Expression b6 = Expression.parse("(0.5)+(0.5)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
        assertEquals(Expression.parse(a3), b3);
        assertEquals(Expression.parse(a4), b4);
        assertEquals(Expression.parse(a5), b5);
        assertEquals(Expression.parse(a6), b6);
    }
    
    public void testCommandsDifferentiate_APlusB_PlusCWithRespectTox(){
        String a1 = Commands.differentiate("a+b+c", "x");
        String a2 = Commands.differentiate("(a+b)+c", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("a+b+c").differentiate(x);
        Expression b2 = Expression.parse("(a+b)+c").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
    }
    
    public void testCommandsDifferentiate_APlus_BPlusCWithRespectTox(){
        String a1 = Commands.differentiate("a+(b+c)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("a+(b+c)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
    }
    
    public void testCommandsDifferentiate_APlusB_TimesCWithRespectTox(){
        String a1 = Commands.differentiate("(a+b)*c", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("(a+b)*c").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
    }
    
    public void testCommandsDifferentiate_APlus_BTimesCWithRespectTox(){
        String a1 = Commands.differentiate("a+b*c", "x");
        String a2 = Commands.differentiate("a+(b*c)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("a+b*c").differentiate(x);
        Expression b2 = Expression.parse("a+(b*c)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
    }
    
    public void testCommandsDifferentiate_ATimesB_TimesCWithRespectTox(){
        String a1 = Commands.differentiate("a*b*c", "x");
        String a2 = Commands.differentiate("(a*b)*c", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("a*b*c").differentiate(x);
        Expression b2 = Expression.parse("(a*b)*c").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
        assertEquals(Expression.parse(a2), b2);
    }
    
    public void testCommandsDifferentiate_ATimes_BTimesCWithRespectTox(){
        String a1 = Commands.differentiate("a*(b*c)", "x");
        
        Expression x = Expression.parse("x");
        Expression b1 = Expression.parse("a*(b*c)").differentiate(x);
        
        assertEquals(Expression.parse(a1), b1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_InvalidExpressionUnmatchedParentheses(){
        Commands.differentiate("(a+b", "x");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_InvalidExpressionMissingOperation(){
        Commands.differentiate("3a", "x");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_InvalidExpressionNumberFormat(){
        Commands.differentiate("2 . 3", "x");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_EmptyExpression(){
        Commands.differentiate("", "x");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_EmptyParentheses(){
        Commands.differentiate("2*()", "x");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_InvalidVariableName(){
        Commands.differentiate("x", "x1");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_NumberInsteadOfVariable(){
        Commands.differentiate("x", "2");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_SumInsteadOfVariable(){
        Commands.differentiate("x+y", "x+y");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_ProductInsteadOfVariable(){
        Commands.differentiate("x*y", "x*y");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_ParenthesisedVariable(){
        Commands.differentiate("x", "(x)");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsDifferentiate_EmptyVariable(){
        Commands.differentiate("x", "");
    }
    
    
    // precision with which double values are compared.
    final private static double EPSILON = 0.0;
    
    // required format of single numbers outputs resulting from evaluation of a expression.
    final private static String NUMBER_REGEX = "[0-9]+(.[0-9]+)?";
    
    @Test
    public void testCommandsSimplify_SingleLargeInteger(){
        String expr = "(4000000000)";
        Map<String, Double> env = new HashMap<>(); env.put("x", 2.0);
        
        String result = Commands.simplify(expr, env);
        
        assertTrue(result.matches(NUMBER_REGEX));
        assertEquals(4000000000.0, Double.valueOf(result), EPSILON);
    }
    
    @Test
    public void testCommandsSimplify_SingleVariable(){
        String expr = "((x))";
        Map<String, Double> env1 = new HashMap<>(); env1.put("x", 0.0);
        Map<String, Double> env2 = new HashMap<>(); env2.put("x", 1e-30);
        Map<String, Double> env3 = new HashMap<>(); env3.put("x", 0.1);
        Map<String, Double> env4 = new HashMap<>(); env4.put("x", 1.0);
        Map<String, Double> env5 = new HashMap<>(); env5.put("x", 10.0);
        Map<String, Double> env6 = new HashMap<>(); env6.put("x", 1e30);
        
        assertEquals(0.0, Double.valueOf(Commands.simplify(expr, env1)), EPSILON);
        assertEquals(1e-30, Double.valueOf(Commands.simplify(expr, env2)), EPSILON);
        assertEquals(0.1, Double.valueOf(Commands.simplify(expr, env3)), EPSILON);
        assertEquals(1.0, Double.valueOf(Commands.simplify(expr, env4)), EPSILON);
        assertEquals(10.0, Double.valueOf(Commands.simplify(expr, env5)), EPSILON);
        assertEquals(1e30, Double.valueOf(Commands.simplify(expr, env6)), EPSILON);
    }
    
    @Test
    public void testCommandsSimplify_EvaluationNumberFormatSingleNumber(){
        //check to see if evaluated numbers are of correct form
        String expr = "0.00000000000001";
        Map<String, Double> env = new HashMap<>(); env.put("x", 2.0);
        
        String result = Commands.simplify(expr, env);
        
        assertTrue(result.matches(NUMBER_REGEX));
    }
    
    @Test
    public void testCommandsSimplify_EvaluationNumberFormatSingleVariable(){
        //check to see if evaluated numbers are of correct form
        String expr = "(x)";
        Map<String, Double> env = new HashMap<>(); env.put("x", 123e-20);
        
        String result = Commands.simplify(expr, env);
        
        assertTrue(result.matches(NUMBER_REGEX));
    }
    
    @Test
    public void testCommandsSimplify_EvaluationNumberFormatForSummation(){
        //check to see if evaluated numbers are of correct form
        String expr = "((x)+(y))";
        Map<String, Double> env = new HashMap<>(); env.put("x", 0.1); env.put("y", 0.2);
        
        String result = Commands.simplify(expr, env);
        
        assertTrue(result.matches(NUMBER_REGEX));
    }
    
    @Test
    public void testCommandsSimplify_EvaluationNumberFormatForMultiplication(){
        //check to see if evaluated numbers are of correct form
        String expr = "(x)*(y)";
        Map<String, Double> env = new HashMap<>(); env.put("x", 56.000000004852); env.put("y", 48.0008476);
        
        String result = Commands.simplify(expr, env);
        
        assertTrue(result.matches(NUMBER_REGEX));
    }
    
    @Test
    public void testCommandsSimplify_EvaluationNumberFormatForCoumpundEXP_WithNoVariables(){
        //check to see if evaluated numbers are of correct form
        String expr = "2000000000*2000000000.0";
        Map<String, Double> env = new HashMap<>();
        
        String result = Commands.simplify(expr, env);
        
        assertTrue(result.matches(NUMBER_REGEX));
    }
    
    @Test
    public void testCommandsSimplify_SumExpression(){
        String expr = "x+y";
        
        Map<String, Double> env1 = new HashMap<>(); env1.put("x", 0.0); env1.put("y", 1.0);
        Map<String, Double> env2 = new HashMap<>(); env2.put("x", 1e-30); env2.put("y", 1.0);
        Map<String, Double> env3 = new HashMap<>(); env3.put("x", 0.1); env3.put("y", 1.0);
        Map<String, Double> env4 = new HashMap<>(); env4.put("x", 1.0); env4.put("y", 1.0);
        Map<String, Double> env5 = new HashMap<>(); env5.put("x", 10.0); env5.put("y", 1.0);
        Map<String, Double> env6 = new HashMap<>(); env6.put("x", 1e30); env6.put("y", 1.0);
        

        assertEquals(0.0 + 1.0, Double.valueOf(Commands.simplify(expr, env1)), EPSILON);
        assertEquals(1e-30 + 1.0, Double.valueOf(Commands.simplify(expr, env2)), EPSILON);
        assertEquals(0.1 + 1.0, Double.valueOf(Commands.simplify(expr, env3)), EPSILON);
        assertEquals(1.0 + 1.0, Double.valueOf(Commands.simplify(expr, env4)), EPSILON);
        assertEquals(10.0 + 1.0, Double.valueOf(Commands.simplify(expr, env5)), EPSILON);
        assertEquals(1e30 + 1.0, Double.valueOf(Commands.simplify(expr, env6)), EPSILON);
        
    }
    
    @Test
    public void testCommandsSimplify_ProductExpression(){
        String expr = "X*x";
        
        Map<String, Double> env1 = new HashMap<>(); env1.put("x", 0.0); env1.put("X", 2.0);
        Map<String, Double> env2 = new HashMap<>(); env2.put("x", 1e-30); env2.put("X", 2.0);
        Map<String, Double> env3 = new HashMap<>(); env3.put("x", 0.1); env3.put("X", 2.0);
        Map<String, Double> env4 = new HashMap<>(); env4.put("x", 1.0); env4.put("X", 2.0);
        Map<String, Double> env5 = new HashMap<>(); env5.put("x", 10.0); env5.put("X", 2.0);
        Map<String, Double> env6 = new HashMap<>(); env6.put("x", 1e30); env6.put("X", 2.0);
        

        assertEquals(0.0*2.0, Double.valueOf(Commands.simplify(expr, env1)), EPSILON);
        assertEquals(1e-30*2.0, Double.valueOf(Commands.simplify(expr, env2)), EPSILON);
        assertEquals(0.1*2.0, Double.valueOf(Commands.simplify(expr, env3)), EPSILON);
        assertEquals(1.0*2.0, Double.valueOf(Commands.simplify(expr, env4)), EPSILON);
        assertEquals(10.0*2.0, Double.valueOf(Commands.simplify(expr, env5)), EPSILON);
        assertEquals(1e30*2.0, Double.valueOf(Commands.simplify(expr, env6)), EPSILON);
        
    }
    
    @Test
    public void testCommandsSimplify_SumExpressionWithNoVariables(){
        // Summing two large integers. In case numbers representing integers are stored as ints and
        // added as ints, the sum will be greater than MAX int and test can expect integer overflow.
        
        String expr = "2000000000+2000000000";
        Map<String, Double> env = new HashMap<>();
        
        final double expected = 2e9+2e9;
        
        assertEquals(expected, Double.valueOf(Commands.simplify(expr, env)), EPSILON);
        
    }
    
    @Test
    public void testCommandsSimplify_SimplificationOrder1(){
        // the spec indicates that for any expression its simplification is
        // equivalent to substitution of environment into the expression 
        // and then if no variables remain evaluate the result. For any expression,
        // the order in which the variables are substituted doesn't effect the result of
        // overall substitution; Hence, the order in which the simplification is applied
        // doesn't effect the overall simplification. Verification of this property is
        // what this test tries to achieve.
        
        // will distribution x inside during effect the evaluation of the expression?
        
        String expr = "x*(x+y)";
        Map<String, Double> env1 = new HashMap<>();
        Map<String, Double> env2 = new HashMap<>(); env2.put("x", 1e30); env2.put("y", 1.0);
        Map<String, Double> env3 = new HashMap<>(); env3.put("x", 1e30);
        Map<String, Double> env4 = new HashMap<>(); env4.put("y", 1.0);
        
        final double expected = 1e30*(1e30+1.0);
        
        assertEquals(expected, Double.valueOf(Commands.simplify(expr, env2)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env1), env2)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env3), env4)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env4), env3)), EPSILON);
        
    }
    
    @Test
    public void testCommandsSimplify_SimplificationOrder2(){
        // will like terms be collected? for example here, if simplification with empty
        // environment collects like terms, the resulting expression will not evaluate
        // to same value as 0.1*x + 0.2*x for all floating-point numbers. Hence, violating the spec.
        
        String expr = "0.1*x+0.2*x";
        Map<String, Double> env1 = new HashMap<>();
        Map<String, Double> env2 = new HashMap<>(); env2.put("x", 10.0);
        
        final double expected = 0.1*10.0 + 0.2*10.0;
        
        assertEquals(expected, Double.valueOf(Commands.simplify(expr, env2)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env1), env2)), EPSILON);
        
    }
    
    @Test
    public void testCommandsSimplify_SimplificationOrder3(){
        // the spec indicates that for any expression its simplification is
        // equivalent to substitution of environment into the expression 
        // and then if no variables remain evaluate the result. For any expression,
        // the order in which the variables are substituted doesn't effect the result of
        // overall substitution; Hence, the order in which the simplification is applied
        // doesn't effect the overall simplification. Verification of this property is
        // what this test tries to achieve.
        
        // Here non-associativity of floating-point addition effects order of evaluation;
        // hence, depending on how simplification is done it can effect result of simplification.
        
        String expr = "x+0.2+y";
        Map<String, Double> env1 = new HashMap<>();
        Map<String, Double> env2 = new HashMap<>(); env2.put("x", 0.1); env2.put("y", 0.3);
        Map<String, Double> env3 = new HashMap<>(); env3.put("x", 0.1);
        Map<String, Double> env4 = new HashMap<>(); env4.put("y", 0.3);
        
        final double expected = (0.1+0.2)+0.3;
        
        assertEquals(expected, Double.valueOf(Commands.simplify(expr, env2)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env1), env2)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env3), env4)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env4), env3)), EPSILON);;
        
    }
    
    @Test
    public void testCommandsSimplify_SimplificationOrder4(){
        // the spec indicates that for any expression its simplification is
        // equivalent to substitution of environment into the expression 
        // and then if no variables remain evaluate the result. For any expression,
        // the order in which the variables are substituted doesn't effect the result of
        // overall substitution; Hence, the order in which the simplification is applied
        // doesn't effect the overall simplification. Verification of this property is
        // what this test tries to achieve.
        
        // Here non-associativity of floating-point multiplication effects order of evaluation;
        // hence, depending on how simplification is done it can effect result of simplification.
        
        String expr = "x*0.2*y";
        Map<String, Double> env1 = new HashMap<>(); env1.put("z", 0.0);
        Map<String, Double> env2 = new HashMap<>(); env2.put("x", 0.1); env2.put("y", 0.3); env2.put("z", 0.0);
        Map<String, Double> env3 = new HashMap<>(); env3.put("x", 0.1); env3.put("z", 0.0);
        Map<String, Double> env4 = new HashMap<>(); env4.put("y", 0.3); env4.put("z", 0.0);
        
        final double expected = (0.1*0.2)*0.3;
        
        assertEquals(expected, Double.valueOf(Commands.simplify(expr, env2)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env1), env2)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env3), env4)), EPSILON);
        assertEquals(expected, Double.valueOf(Commands.simplify(Commands.simplify(expr, env4), env3)), EPSILON);;
        
    }
    
    @Test
    public void testCommandsSimplify_DifferentGroupingOfSameElements(){
        // Since (0.1*0.2)*0.3 != 0.1*(0.2*0.3), this test helps to verify that implementation is 
        // able to distinguish between different groupings of same elements. It also verifies the redundant 
        // parentheses have no effect on evaluation of an expression resulting from simplification.
        
        //TODO Does the spec allow this test?
        
        String expr_A1 = "0.1*0.2*0.3";
        String expr_A2 = "(0.1*0.2)*0.3";
        Map<String, Double> env_A = new HashMap<>(); env_A.put("x", 0.0);
        
        final double expected_A = (0.1*0.2)*0.3;
        
        assertEquals(expected_A, Double.valueOf(Commands.simplify(expr_A1, env_A)), EPSILON);
        assertEquals(expected_A, Double.valueOf(Commands.simplify(expr_A2, env_A)), EPSILON);
        
        String expr_B1 = "0.1*(0.2*0.3)";
        String expr_B2 = "((0.1)*((0.2*0.3)))";
        Map<String, Double> env_B = new HashMap<>();
        
        final double expected_B = 0.1*(0.2*0.3);
        
        assertEquals(expected_B, Double.valueOf(Commands.simplify(expr_B1, env_B)), EPSILON);
        assertEquals(expected_B, Double.valueOf(Commands.simplify(expr_B2, env_B)), EPSILON);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_InvalidNumberRepresentation(){
        String expr = "12.34e5"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_UnmatchedLeftParanthese(){
        String expr = "(a"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_UnmatchedRightParanthese(){
        String expr = "a)"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_EmptyParantheses(){
        String expr = "2*()"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_UnsupportedOperation_Negation(){
        String expr = "-(a)"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_UnsupportedOperation_Subtraction(){
        String expr = "2.0 - 1.0"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_UnsupportedOperation_Exponentiation(){
        String expr = "x^2"; 
        Map<String, Double> env = new HashMap<>(); env.put("x", 2.0);
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_UnsupportedOperation_Division(){
        String expr = "1/2"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_NegativeNumber(){
        String expr = "-10.0"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_IllFormattedNumber(){
        String expr = "2 . 3"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_MissingOperation1(){
        String expr = "x2"; 
        Map<String, Double> env = new HashMap<>(); env.put("x", 3.0);
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_MissingOperation2(){
        String expr = "2 x"; 
        Map<String, Double> env = new HashMap<>(); env.put("x", 3.0);
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_VariableContainsIllegalChar1(){
        String expr = "v@ri@ble"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCommandsSimplification_VariableContainsIllegalChar2(){
        String expr = "var1able"; 
        Map<String, Double> env = new HashMap<>();
        
        Commands.simplify(expr, env);
    }
    
}
