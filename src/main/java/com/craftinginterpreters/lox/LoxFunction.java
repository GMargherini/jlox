package com.craftinginterpreters.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
	private final Stmt.Function declaration;
	private final Environment closure;

	LoxFunction(Stmt.Function declaration, Environment closure) {
		this.declaration = declaration;
		this.closure = closure;
	}

	LoxFunction(Expr.Lambda declaration, Environment closure) {
		this.declaration = new Stmt.Function(null, declaration.params, declaration.body);
		this.closure = closure;
	}

	@Override
	public String toString() {
		if(declaration.name == null) return "<lambda>";
		return "<fn " + declaration.name.lexeme + ">";
  }

	@Override
  	public int arity() {
    	return declaration.params.size();
  }

	@Override
	public Object call(Interpreter interpreter,
						List<Object> arguments) {
		Environment environment = new Environment(closure);
		for (int i = 0; i < declaration.params.size(); i++) {
		environment.define(declaration.params.get(i).lexeme,
			arguments.get(i));
		}

		try {
			interpreter.executeBlock(declaration.body, environment);
		} catch (Return returnValue) {
			return returnValue.value;
		}
		return null;
	}
}