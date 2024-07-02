
public class BuildAstVisitor extends ExprBaseVisitor<AstNodes> {

    @Override
    public AstNodes visitVariableExpr(ExprParser.VariableExprContext ctx) {
        String variable = ctx.getChild(0).getText();

        return new Variable(variable);

    }

    @Override
    public AstNodes visitInfixExpr(ExprParser.InfixExprContext ctx) {

        // expr ('*'|'/') expr or expr ('+'|'-') expr 이므로
        // 0 => left, 2 => right, 1 => operator

        // left, right에는 num이 되는 expr이 아닌 다른 애들이 들어올 수 있기에
        // visit으로 처리해준다.

        AstNodes left = this.visit(ctx.getChild(0));
        AstNodes right = this.visit(ctx.getChild(2));
        String operator = ctx.getChild(1).getText();

        switch (operator) {
            case "+":
                operator = "ADD";
                break;
            case "-":
                operator = "SUB";
                break;
            case "*":
                operator = "MUL";
                break;
            case "/":
                operator = "DIV";
                break;
        }

        return new InfixNode(left, right, operator);

    }

    @Override
    public AstNodes visitNumberExpr(ExprParser.NumberExprContext ctx) {
        // '-' 'num' or 'num'
        //
        double num;
        String minusCheck = ctx.getChild(0).getText();

        // minusCheck가 '-'이면 => '-' num 이므로 음수 처리

        if ("-".equals(minusCheck)) {

            String temp = ctx.getChild(1).getText();
            num = (-1) * Double.parseDouble(temp);

        } else {
            // 아니면 그냥 num이므로 double로 변환
            num = Double.parseDouble(minusCheck);

        }

        return new Number(num);

    }

    @Override
    public AstNodes visitParensExpr(ExprParser.ParensExprContext ctx) {

        // '(' 'expr' ')' => expr이므로 1
        // visitInfixExpr과 동일한 원리로 visit으로 처리

        return this.visit(ctx.getChild(1));
    }

    @Override
    public AstNodes visitAssingExpr(ExprParser.AssingExprContext ctx) {
        // VAR '=' '-'num
        // VAR '=' num

        String variable = ctx.getChild(0).getText();
        double value;

        String minusCheck = ctx.getChild(2).getText();

        if ("-".equals(minusCheck)) {

            String temp = ctx.getChild(3).getText();
            value = (-1) * Double.parseDouble(temp);

        } else {
            // 아니면 그냥 num이므로 double로 변환
            value = Double.parseDouble(minusCheck);

        }

        return new VariableDeclaration(variable, value);

    }

}