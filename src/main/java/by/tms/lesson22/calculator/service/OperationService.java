package by.tms.lesson22.calculator.service;

import by.tms.lesson22.calculator.model.Operation;

public class OperationService {

	public Operation calculate(Operation operation) {
		double result;
		switch (operation.getOperation()) {
			case "+":
				result = operation.getNum1() + operation.getNum2();
				operation.setResult(result);
				return operation;
			case "-":
				result = operation.getNum1() - operation.getNum2();
				operation.setResult(result);
				return operation;
			case "*":
				result = operation.getNum1() * operation.getNum2();
				operation.setResult(result);
				return operation;
			case "/":
				if (operation.getNum2() == 0) {
					throw new IllegalArgumentException("Can't divide by zero");
				} else {
					result = operation.getNum1() / operation.getNum2();
					operation.setResult(result);
					return operation;
				}
		}
		throw new IllegalArgumentException("Unsupported operation");
	}
}
