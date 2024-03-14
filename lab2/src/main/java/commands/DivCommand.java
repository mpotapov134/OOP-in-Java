package commands;

import context.Context;
import exceptions.EmptyNumStackException;
import exceptions.MathematicalException;

import java.util.EmptyStackException;

public class DivCommand implements Command {
    public void execute(Context context, String[] args) throws EmptyNumStackException,
            MathematicalException {
        double a, b;
        try {
            b = context.pop();
            a = context.pop();
        } catch (EmptyStackException e) {
            throw new EmptyNumStackException("No numbers in the stack.", e);
        }

        if (b == 0)
            throw new MathematicalException("Division by zero.");
        context.push(a / b);
    }
}
