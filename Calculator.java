import java.io.*;

/** Methods for performing arithmetic calculations. */
class Arithmetic {
  public static float add(float num1, float num2) {
    return num1 + num2;
  }

  public static float sous(float num1, float num2) {
    return num1 - num2;
  }

  public static float mult(float num1, float num2) {
    return num1 * num2;
  }

  public static float div(float num1, float num2) {
    return num1 / num2;
  }

  public static float mod(float num1, float num2) {
    return num1 % num2;
  }
  
  public static float square(float num1) {
    return num1 * num1;
  }
  
  public static float cube(float num1) {
    return num1 * num1 * num1;
  }
  
  
}

/** The calculator program. */
public class Calculator {
  public static void main(String[] args) {
    //Main loop for the calculator
    while (true) {
      //Prompt user to enter equation
      String input = getUserInput("Enter your equation:");
      if (input == null) {
        System.out.println("Please enter an equation.");
        continue;
      }

      //Split equation input into tokens
      String[] tokens = input.split(" ");

      //Check if the first element from tokens is operator or "q", if is q exit
      String operator = tokens[0];
      if (operator.toLowerCase().equals("q")) {
        System.out.println("Quitting the program. Goodbye!");
        break;
      }

      //Attemp to parse the numbers
      Float num1, num2;
      try {
        num1 = Float.parseFloat(tokens[1]);

        if (tokens.length >= 3) {
          num2 = Float.parseFloat(tokens[2]);
        } else {
          num2 = 0f;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Error: enter at least 1 number.");
        continue;
      } catch (NumberFormatException e) {
        System.out.println("Error: not able to parse the numbers you entered.");
        continue;
      }

      // This helps us delegate calls to methods in `Arithmetic` based
      // on the operator that the user entered.
      Float result;
      switch (operator) {
        case "+":
          result = Arithmetic.add(num1, num2);
          break;

        case "-":
          result = Arithmetic.sous(num1, num2);
          break;
        
        case "*":
          result = Arithmetic.mult(num1, num2);
          break;

        case "/":
          result = Arithmetic.div(num1, num2);
          break;

        case "%":
          result = Arithmetic.mod(num1, num2);
          break;

        case "square":
          result = Arithmetic.square(num1);
          break;

        case "cube":
          result = Arithmetic.cube(num1);
          break;

        case "q":
          System.out.println("Quitting the program. Goodbye!");
          return;

        default:
          result = null;
          break;
      }

      // Attempt to output `result`. If `result` doesn't exist, it's
      // because the user entered an operator we did not recognize.
      if (result == null) {
        System.out.println("Invalid operator.");
      } else {
        System.out.println("=> " + result);
      }
    }
  }

  /** Works exactly like Python's input() function. */
  static String getUserInput(String prompt) {
    String inputLine = null;
    System.out.print(prompt + " ");
    try {
      BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
      inputLine = is.readLine();
      if (inputLine.length() == 0) {
        return null;
      }
    } catch (IOException e) {
      System.out.println("IOException: " + e);
    }
    return inputLine;
  }
}
