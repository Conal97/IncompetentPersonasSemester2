package Tests;

import Model.Model;
import Controller.Controller;

public class Test {
	public static void main(String [] args)	{

        // First init model
    	Model model = new Model();

		// Init controller by passing model
		Controller controller = new Controller(model); 
	}
}
