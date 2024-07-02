import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class program {

    public static void main(String[] args) throws IOException {

        List<Double> result = new ArrayList<>();

        // Get Lexer
        ExprLexer lexer = new ExprLexer(CharStreams.fromStream(System.in));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Pass tokens to parser
        ExprParser parser = new ExprParser(tokens);

        // Make AST from prog and print the tree
        ParseTree antlrAst = parser.prog();

        AntlrToProgram progVisitor = new AntlrToProgram();

        ExprNode prog = progVisitor.visit(antlrAst);

        Evaluate Evaluator = new Evaluate();

        AstCall astCall = new AstCall();

        for (AstNodes node : prog.expressions) {

            // 마지막 node는 출력되면 안되므로
            if (node != null) {

                astCall.Call(node, 0);
                // Evaluate AST result
                result.add(Evaluator.evaluate(node));
                // Sysout으로 출력하려고 하였지만 여러 input이 들어올 시
                // AST를 다 그리고 마지막에 출력을 해야하므로 아래로 뺴야함.
                // System.out.println(Evaluator.evaluate(node));
            }

        }

        for (double res : result) {
            System.out.println(res);
        }

    }
}