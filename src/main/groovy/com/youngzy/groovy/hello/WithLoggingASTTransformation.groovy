package com.youngzy.groovy.hello

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

//在 Groovy 中编写 AST 转换时，即使并不强制，也强烈建议使用 CompileStatic，因为这能提高编译器的性能。
@CompileStatic
//利用 org.codehaus.groovy.transform.GroovyASTTransformation 来注释，从而得知转换运行的具体编译阶段。这里正处于语义解析阶段。
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class WithLoggingASTTransformation implements ASTTransformation { // 实现 ASTTransformation 接口。

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        //nodes 参数是一个包含 2 个 AST 节点的数组。第一个是注释节点（@WithLogging），第二个是已被注释节点（方法节点）
        MethodNode method = (MethodNode) nodes[1]

        // 创建一个语句，当进入方法时，打印消息。
        def startMessage = createPrintlnAst("Starting $method.name")
        // 创建一个语句，当退出方法时，打印消息。
        def endMessage = createPrintlnAst("Ending $method.name")

        // 方法体，在该例中是 BlockStatement
        def existingStatements = ((BlockStatement) method.code).statements
        // 在已有代码的第一个语句之前添加进入方法消息
        existingStatements.add(0, startMessage)
        // 在已有代码的最后一个语句之后添加结束方法消息
        existingStatements.add(endMessage)
    }

    // 创建一个 ExpressionStatement ，用来封装一个对应于 this.println("message") 的 MethodCallExpression。
    private static Statement createPrintlnAst(String message) {
        new ExpressionStatement(
                new MethodCallExpression(
                        new VariableExpression("this"),
                        new ConstantExpression("println"),
                        new ArgumentListExpression(
                                new ConstantExpression(message)
                        )
                )
        )
    }
}
