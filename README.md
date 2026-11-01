# Objectives
 
The objectives of this lab are: 

   * Practice writing several mocks for a given object.

   * Use mocks to test an object.
 
# 2 Introduction
 
A mock of an object A is another object B that offers the same interface as A, but provides a simpler implementation. A mock is primarily used as a placeholder for a specific task. For example, consider an object C that uses object A. If A and C are being simultaneously developed, the developer for C can use a simpler mock of A (as a stub). A tester of C may use another mock for A to isolate C. A developer of a different group may simultaneously try out a new, improved implementation of A by mocking it (creating stubs for all methods) and then iteratively fleshing out its methods, while plugging it in C at any time to test. 

In order to use mocks effectively, it is imperative that the interface of the class being mocked be designed and agreed upon. 

# 3 Provided code
 
Load the provided starter code in a new project in IntelliJ. Generate a class diagram for it to better understand its design (In IntelliJ Ultimate Edition, right-click on the src/ folder of the project, select Diagrams->Show Diagram->Java Classes. 

The following is a brief summary of the provided code: 

   1. `SpreadSheet`: interface that represents a spreadsheet.

   2. `SparseSpreadSheet`: a particular implementation of `SpreadSheet`.

   3. `SpreadSheetController`: the controller of an application that uses a spreadsheet.

   4. `SpreadSheetProgram`: a class defining the `main` method of this application.
 
Please run the provided code to further understand what the given code does. 

# 4 What to do
 
For the purposes of this lab, we will assume that the `SparseSpreadSheet` has been tested and works correctly. 

## 4.1 Part 1: Testing the controller: 1
 
The controller does three things: read inputs from the provided `Readable`, transmit outputs to the provided `Appendable` and parse inputs and call corresponding operations in the spreadsheet. 

   * Write a test to determine if the controller transmits the expected "welcome message" to the `Appendable` object given to it, when the program starts. The welcome message is supposed to be as follows: 

```java

Welcome to the spreadsheet program!
Supported user instructions are: 
assign-value row-num col-num value (set a cell to a value)
print-value row-num col-num (print the value at a given cell)
menu (Print supported instruction list)
q or quit (quit the program)

```

See a hint below for how to test this.

   * Write a separate test to determine if the controller transmits the expected "farewell message" to the `Appendable` object given to it, when the program is quit. The farewell message is supposed to be `Thank you for using this program!`.
 
Hint: Note that both tests require that the program immediately quit after startup (i.e. the user input is 'q' or 'quit'). Thus, the `Appendable` object will contain the welcome message, the prompt for input and the farewell message. Rather than type in the entire expected output, each test can use the specific expected output of what it is testing. The contents of the `Appendable` object can be broken down into lines (using the `split` method in the `String` class), then the pertinent lines for the test can be combined into another string to assert against the expected output. 

Ask a TA if you need help putting together a test using `Readable` or `Appendable` appropriately. 

## 4.2 Part 2: Testing the controller: 2
 
In this part you must write a test that verifies that the controller sends the correct inputs to the spreadsheet. Note how the client enters the row and column into the controller and also how the model expects it. The controller is translating between these representations, and that is part of what we must test. 

To do this, write a mock spreadsheet. The objective of this class is to help you test the above. Accordingly, the mock must record the inputs to its various methods in a log, that is passed to its constructor. Follow the section titled "Imitating models" in the lecture on controllers and mocks to see how to set up these tests. 

Write tests to verify all interactions between the controller and the model. 

Show a TA your work. 

## 4.3 Questions to ponder/discuss
 
Show your tests to the person next to you, and see what they did (after both of you are done with Part 2). What specific advantages did the mock provide you? (Hint: there is at least one thing that your test does not have if you use mocks, that it would need to include if you did not use mocks). 

## 4.4 Part 3: A better spreadsheet
 
**Complete this part with the student next to you. You can complete this part by yourself, but it works better if you work with somebody. If you end up working by yourself, please complete Student 1's part before Student 2's part below.** 

   1. (Both): Create a new `BetterSpreadSheet` interface that extends `SpreadSheet` interface. Add a new method that takes a rectangular region in the spreadsheet (e.g. from (0,0) to (1,3)) and a value, and sets all cells in that region to the specified value. Design the signature of this method. Both students must agree on the signature before proceeding!

   2. (Student 1): Modify the provided controller so that it works with a `BetterSpreadSheet` object instead. Add a new instruction to the controller that can be used as follows: "bulk-assign A 1 B 4 100" (to set all cells in the range A1:B4 to 100). Write a mock implementation of `BetterSpreadSheet` and use it in your test to verify that your controller accepts this new instruction and calls the appropriate method in the model. **Do not wait for Student 1 to complete their implementation!**

   3. (Student 2): Write a new class that implements `BetterSpreadSheet` while reusing provided code. Implement the new method and write tests for it.

   4. (Both, after they are done with their respective parts): Merge your work, by having Student 2's controller use Student 1's implementation.
 
Student 1 successfully worked with a mock to simultaneously 
develop with Student 2! 

Submit your work after correcting style and writing all required classes. 