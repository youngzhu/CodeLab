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

@CompileStatic
//在 Groovy 中编写 AST 转换时，即使并不强制，也强烈建议使用 CompileStatic，因为这能提高编译器的性能。
//利用 org.codehaus.groovy.transform.GroovyASTTransformation 来注释，从而得知转换运行的具体编译阶段。这里正处于语义解析阶段。
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class WithLoggingASTTransformationGlobal implements ASTTransformation { // 实现 ASTTransformation 接口。

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        //sourceUnit 参数可以提供对正在编译的源的访问，因此能够获取当前源的 AST 并从该方法中获取方法列表。
        def methods = sourceUnit.AST.methods
        // 遍历每一个方法
        methods.each { method ->
            def startMessage = createPrintlnAst("STARTING $method.name ...")
            def endMessage = createPrintlnAst("ENDING $method.name !")

            def existingStatements = ((BlockStatement)method.code).statements
            existingStatements.add(0, startMessage)
            existingStatements.add(endMessage)
        }
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
