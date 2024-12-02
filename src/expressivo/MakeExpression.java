package expressivo;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser.PrimitiveContext;
import expressivo.parser.ExpressionParser.ProductContext;
import expressivo.parser.ExpressionParser.RootContext;
import expressivo.parser.ExpressionParser.SumContext;

public class MakeExpression implements ExpressionListener {
    private Stack<Expression> stack = new Stack<>();
    
    /**
     * Returns the expression constructed by this listener object.
     * Requires that this listener has completely walked over an Expression
     * parse tree using ParseTreeWalker.
     * @return IntegerExpression for the parse tree that was walked
     */
    public Expression getExpression() {
        return stack.get(0);
    }
    
    @Override
    public void exitRoot(RootContext ctx) {
    }
    
    @Override
    public void exitSum(SumContext ctx) {
        // matched the product ('+' product)* rule
        List<ProductContext> addends = ctx.product();
        
        //reverse stack
        Stack<Expression> revStack = new Stack<>();
        for(int i = 0; i < addends.size(); i++){
           revStack.push(stack.pop()); 
        }
        
        Expression sum = revStack.pop();
        
        for(int i = 1; i < addends.size(); i++){
            sum = new Sum(sum, revStack.pop());
        }
        
        stack.push(sum);
    }
    
    @Override
    public void exitProduct(ProductContext ctx) {
        // matched the primitive ('*' primitive)* rule
        List<PrimitiveContext> factors = ctx.primitive();
        
        //reverse stack
        Stack<Expression> revStack = new Stack<>();
        for(int i = 0; i < factors.size(); i++){
           revStack.push(stack.pop()); 
        }
        
        Expression product = revStack.pop();
        
        for(int i = 1; i < factors.size(); i++){
            product = new Product(product, revStack.pop());
        }
        
        stack.push(product);
    }

    @Override
    public void exitPrimitive(PrimitiveContext ctx) {
        if(ctx.NUMBER() != null){
            // matched the NUMBER alternative
            double d = Double.valueOf(ctx.NUMBER().getText());
            Expression number = new Number(d);
            stack.push(number);
        }else if(ctx.VARIABLE() != null){
            // matched the VARIABLE alternative 
            String name = ctx.VARIABLE().getText();
            Expression variable = new Variable(name);
            stack.push(variable);
        }else{
            // matched the '(' sum ')' alternative
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitEveryRule(ParserRuleContext arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visitErrorNode(ErrorNode arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visitTerminal(TerminalNode arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterRoot(RootContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterSum(SumContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterProduct(ProductContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterPrimitive(PrimitiveContext ctx) {
        // TODO Auto-generated method stub
        
    }
}
