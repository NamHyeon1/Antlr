public class AstCall {

    public void Call(AstNodes astNodes, int tabs) {

        // astNodes instance : Variable Number InfixNode VariableDeclaration

        String indent = "";

        //
        for (int i = 0; i < tabs; i++) {
            indent += "\t";
        }

        if (astNodes instanceof Variable) {

            Variable var = (Variable) astNodes;

            String varName = var.variable;

            System.out.println(indent + varName);

        } else if (astNodes instanceof Number) {

            Number number = (Number) astNodes;

            System.out.println(indent + number.num);

        } else if (astNodes instanceof InfixNode) {
            InfixNode infixNode = (InfixNode) astNodes;

            AstNodes left = infixNode.left;
            AstNodes right = infixNode.right;

            String operator = infixNode.operator; // ADD SUB MUL DIV

            System.out.println(indent + operator);
            this.Call(left, tabs + 1);
            this.Call(right, tabs + 1);

        } else if (astNodes instanceof VariableDeclaration) {

            VariableDeclaration decl = (VariableDeclaration) astNodes;

            String varName = decl.variable;
            double value = decl.value;

            System.out.println("ASSIGN");
            System.out.println("\t" + varName);
            System.out.println("\t" + value);

        }

    }
}