import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluate {

    public Map<String, Double> vars; // symbol table for storing values of variables

    public Evaluate() {
        vars = new HashMap<>();
    }

    public double evaluate(AstNodes astNodes) {
        // astNodes instance : Variable Number InfixNode VariableDeclaration

        // 변수 선언은 0을 return 해야하기에 초기값을 0으로 setting한다.
        double res = 0;

        if (astNodes instanceof VariableDeclaration) {

            VariableDeclaration decl = (VariableDeclaration) astNodes;
            vars.put(decl.variable, decl.value);

        } else if (astNodes instanceof Number) {

            Number num = (Number) astNodes;

            res = num.num;

        } else if (astNodes instanceof InfixNode) {

            InfixNode node = (InfixNode) astNodes;

            AstNodes left = node.left;
            AstNodes right = node.right;

            double leftVal = evaluate(left);
            double rightVal = evaluate(right);

            String operator = node.operator;

            switch (operator) {
                case "ADD":
                    res = leftVal + rightVal;
                    break;
                case "SUB":
                    res = leftVal - rightVal;
                    break;
                case "MUL":
                    res = leftVal * rightVal;
                    break;
                case "DIV":
                    res = leftVal / rightVal;
                    break;
            }

        } else if (astNodes instanceof Variable) {
            Variable variable = (Variable) astNodes;

            res = vars.get(variable.variable);

        }

        return res;
    }
}