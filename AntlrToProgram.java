public class AntlrToProgram extends ExprBaseVisitor<ExprNode> {

    @Override
    public ExprNode visitProg(ExprParser.ProgContext ctx) {

        ExprNode program = new ExprNode();

        BuildAstVisitor visitor = new BuildAstVisitor();

        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i == ctx.getChildCount() - 1) {
                // last child 이므로 visit하면 안됨

            } else {

                // ExprNode 안에 <AstNode>를 담는 List가 있는데 그 List에 <AstNode>들을 추가해주는 것
                // prog => 아래에 있는 child들을 돌면서
                program.addExpressions(visitor.visit(ctx.getChild(i)));
            }
        }

        return program;
    }
}
