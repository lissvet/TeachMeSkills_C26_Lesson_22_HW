package by.tms.lesson22.calculator.web;
import by.tms.lesson22.calculator.model.Operation;
import by.tms.lesson22.calculator.service.OperationService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class OperationHttpHandler implements HttpHandler {

	private final OperationService operationService = new OperationService();

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		String query = exchange.getRequestURI().getQuery(); //localhost:8080/calc?num1=2&num2=2&type=sum
		String[] parameters = query.split("&");
		List<String> parametersFinal = new ArrayList<>();

		for (String par : parameters) {
			String[] p = par.split("=");
			parametersFinal.add(p[1]);
		}

		String result;

		if (parametersFinal.get(1).matches("0") && parametersFinal.get(2).matches("/")) {
			result = "Cannot be divided by zero";
		} else if (parametersFinal.get(0).matches("\\d+") && parametersFinal.get(0).matches("\\d+")) {
			double num1 = parseDouble(parametersFinal.get(0));
			double num2 = parseDouble(parametersFinal.get(1));
			String type = parametersFinal.get(2);

			Operation operation = new Operation(num1, num2, type);
			Operation calculate = operationService.calculate(operation);

			result = num1 + " " + type + " " + num2 + " " + "=" + " " + calculate.getResult();

		} else {
			result = "Wrong parameters";
		}

		exchange.sendResponseHeaders(200, result.length());

		PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
		printWriter.print(result);
		printWriter.flush();
	}

}
