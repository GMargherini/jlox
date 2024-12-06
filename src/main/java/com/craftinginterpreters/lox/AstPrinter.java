package com.craftinginterpreters.lox;

import com.craftinginterpreters.lox.Expr.Get;
import com.craftinginterpreters.lox.Expr.Lambda;
import com.craftinginterpreters.lox.Expr.Set;
import com.craftinginterpreters.lox.Expr.Super;
import com.craftinginterpreters.lox.Expr.This;

class AstPrinter implements Expr.Visitor<String> {
  String print(Expr expr) {
    return "";
  }

  @Override
  public String visitCallExpr(Expr.Call expr) {
    return "";
  }

  @Override
  public String visitTernaryExpr(Expr.Ternary expr) {
  	return parenthesize("conditional", expr.condition, expr.thenExpr, expr.elseExpr);
  }

  @Override
  public String visitLogicalExpr(Expr.Logical expr) {
  	return "";
  }
	
  @Override
  public String visitAssignExpr(Expr.Assign expr) {
  	return "";
  }

  @Override
  public String visitVariableExpr(Expr.Variable expr) {
  	return parenthesize(expr.name.lexeme);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    return parenthesize(expr.operator.lexeme,
                        expr.left, expr.right);
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    return parenthesize("group", expr.expression);
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    if (expr.value == null) return "nil";
    return expr.value.toString();
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    return parenthesize(expr.operator.lexeme, expr.right);
  }

  private String parenthesize(String name, Expr... exprs) {
    StringBuilder builder = new StringBuilder();

    builder.append("(").append(name);
    for (Expr expr : exprs) {
      builder.append(" ");
      builder.append(expr.accept(this));
    }
    builder.append(")");

    return builder.toString();
  }

  @Override
  public String visitLambdaExpr(Lambda expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitLambdaExpr'");
  }

  @Override
  public String visitGetExpr(Get expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitGetExpr'");
  }

  @Override
  public String visitSetExpr(Set expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitSetExpr'");
  }

  @Override
  public String visitThisExpr(This expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitThisExpr'");
  }

  @Override
  public String visitSuperExpr(Super expr) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'visitSuperExpr'");
  }
}
