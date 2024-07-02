import java.util.List;
import java.util.ArrayList;

public class AstNodes {

}

class ExprNode extends AstNodes {

    public List<AstNodes> expressions;

    public ExprNode() {
        this.expressions = new ArrayList<>();
    }

    public void addExpressions(AstNodes astNode) {
        expressions.add(astNode);
    }

}

class Number extends AstNodes {
    public double num;

    public Number(double num) {
        this.num = num;
    }

    public String toString() {
        return new Double(num).toString();
    }
}

class Variable extends AstNodes {
    public String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public String toString() {
        return variable;
    }
}

class VariableDeclaration extends AstNodes {
    public String variable;
    public double value;

    public VariableDeclaration(String variable, double value) {
        this.variable = variable;
        this.value = value;
    }

    public String toString() {
        return variable + " = " + value;
    }

}

class InfixNode extends AstNodes {
    public AstNodes left;
    public AstNodes right;
    public String operator;

    public InfixNode(AstNodes left, AstNodes right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public String toString() {

        // left와 right가 각각 AstNodes 이기에 (Num이 아닐 수도 있기에)

        return left.toString() + operator + right.toString();

    }

}
