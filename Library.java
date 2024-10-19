// Import statement 
import java.util.Scanner; // For Scanner
import java.util.ArrayList; // For ArrayList
/**
 * Title: Quill Quest
 * 
 * 
 * Program Summary: This program is a book system that allows user can see what type of books that the library has by type of category. They can see the information of the library. They can also rent a book and return it by login to their account that they signed up. Furthermore, they can manage their book status where they read that book.  
 * 
 * 
 * Program Element List: ArrayList, replaceAll(), try-catch, scanner, InputMismatchException, for loop, if-else, switch, ArrayList.get(), Arraylist.add(), .nextInt().toString().
 *
 * @author Will
 * @version 1/15/2024
 */

class Library {

  // Instance Variables - Gloabal Variables
  public ArrayList<User> user_list = new ArrayList<User>();
  public ArrayList<ArrayList<Book>> book_table = new ArrayList<ArrayList<Book>>();
  public User nowUser = new User();

  
  
  /*
   * Summary: This Constructor intialized the user list, and book table that get information throught the csv file
   * Parameter(s): fileController of type FileController
   * Return(s): none.
   */
  public Library(FileController fileController) {
    user_list = fileController.loadUserData();
    book_table = fileController.loadBookData();
  } // End of Library Constructor

  
  
  
  
  /*
   * Summary: This method is for select menu where check the user input is vaild
   * Parameter(s): keyboardinput of type Scanner.
   * Return(s): select
   */
  static String selectMenu(Scanner keyboardinput) {
    String select;

    while (true) { // Loop until user input is valid
      try { // Try-catch to prevent invalid input
        select = keyboardinput.nextLine();
        select.replaceAll(" ", "").toLowerCase();
        return select;
      } catch (Exception e) {
        System.out.print("Wrong input. Please try again.\n\n");
        keyboardinput.next();
      } // End of try catch
    } // End of while loop
  } // End of method selectMenu 


  
  
  
  /*
   * Summary: This method is used to print out the library main menu
   * Parameter(s): keyboardinput of type Scanner.
   * Return(s): selectMenu(keyboardinput);
   */
  private String mainMenu(Scanner keyboardinput) {
    System.out.println("<Library Main Menu>"); // For menu
    System.out.println();
    System.out.println("[1] Search Book");
    System.out.println("[2] User Login / Register");
    System.out.println("[3] Request New Book");
    System.out.println("[4] Library Info");
    System.out.println("[5] Exit");
    System.out.println();

    System.out.print("Select the menu : ");
    return selectMenu(keyboardinput);
  } // End of method mainMenu


  
  
  
  /*
   * Summary: This method checks if the user read the book 
   * Parameter(s): none.
   * Return(s): none.
   */
  private void readBook(Scanner keyboardinput, UserBook userBook, JFrameLibrary panel) {
    int search_key, i, j, k;
    Boolean user_check = false, book_check = false;
    String show_book_title = "";
    System.out.print("Input Book ID that you read completed (or Back to the menu = -1) : ");

    search_key = Integer.parseInt(selectMenu(keyboardinput));

    if (search_key == -1) { // If user input -1, return to the main menu
      return;
    } else {
      for (i = 0; i < book_table.size(); i++) { // Loop through the book table
        for (j = 0; j < book_table.get(i).size(); j++) { // Loop through the book table
          if (book_table.get(i).get(j).id == search_key) { // If the book id is the same as the user input
            book_check = true;
            show_book_title = book_table.get(i).get(j).book_title;
            
            for (k = 0; k < user_list.size(); k++) { // Loop through the user list 
              if (user_list.get(k).id == nowUser.id) { // If the user id is the same as the user input
                ArrayList<Boolean> book_list_status = new ArrayList<Boolean>();
                book_list_status.add(user_list.get(k).book_list.get(search_key).get(0));
                book_list_status.add(true);
                user_list.get(k).book_list.put(book_table.get(i).get(j).id, book_list_status);
                nowUser = user_list.get(k);
                user_check = true;
              } // End of if statement
            } // End of for loop
            
            if (!user_check) {
              panel.errMsg("Invalied User");
              System.out.println("Invalied User");
            } // End of if statement
          } // End of if statement
        } // End of for loop
      } // End of for loop
      
      if (!book_check) { // If the book id is not in the book table
        panel.errMsg("Invalied Book ID");
        System.out.println("Invalied Book ID");
      } // End of if statement
      
      if (user_check && book_check) { // If the user and book id is valid
        clearScreen();
        panel.successMsg("The book (" + show_book_title + ") 's read status has updated successfully");
        System.out.println("The book (" + show_book_title + ") 's read status has updated successfully");
      } // End of if statement
    } // End of if/ else statement
    userBook.updateUserBook(user_list, book_table);
  } // End of method readBook


  
  
  
  /*
   * Summary: This method is used to return a book
   * Parameter(s): none.
   * Return(s): none.
   */
  private void returnBook(Scanner keyboardinput, UserBook userBook, JFrameLibrary panel) {
    int search_key, i, j, k;
    Boolean user_check = false, book_check = false;
    String show_book_title = "";
    System.out.print("Input Book ID to return the book (or Back to the menu = -1) : ");

    search_key = Integer.parseInt(selectMenu(keyboardinput));

    if (search_key == -1) { // If user input -1, return to the main menu
      return;
    } else {
      for (i = 0; i < book_table.size(); i++) { // Loop through the book table
        for (j = 0; j < book_table.get(i).size(); j++) { // Loop through the book table
          if (book_table.get(i).get(j).id == search_key) { // If the book id is the same as the user input
            book_check = true;
            
            if (book_table.get(i).get(j).rent_by == -1) { // If the book is not rented
              panel.errMsg("This book (" + book_table.get(i).get(j).book_title + ") is already returned.");
              System.out.println("This book (" + book_table.get(i).get(j).book_title + ") is already returned.");
            } else {
              book_table.get(i).get(j).rent_by = -1;
              show_book_title = book_table.get(i).get(j).book_title;
              
              for (k = 0; k < user_list.size(); k++) { // Loop through the user list
                if (user_list.get(k).id == nowUser.id) { // If the user id is the same as the user input
                  ArrayList<Boolean> book_list_status = new ArrayList<Boolean>();
                  book_list_status.add(false);
                  book_list_status.add(user_list.get(k).book_list.get(search_key).get(1));
                  user_list.get(k).book_list.put(book_table.get(i).get(j).id, book_list_status);
                  nowUser = user_list.get(k);
                  user_check = true;
                } // End of if statement
              } // End of for loop
              
              if (!user_check) { // If the user id is not in the user list
                System.out.println("Invalied User");
              } // End of if statement
            } // End of if/else statement
          } // End of if statement
        } // End of for loop
      } // End of for loop
      
      if (!book_check) { // If the book id is not in the book table
        panel.errMsg("Invalied Book ID");
        System.out.println("Invalied Book ID");
      } // End of if statement
      
      if (user_check && book_check) { // If the user and book id is valid
        clearScreen();
        panel.successMsg("The book (" + show_book_title + ") is returned by " + nowUser.user_name + " successfully");
        System.out.println("The book (" + show_book_title + ") is returned by " + nowUser.user_name + " successfully");
      } // End of if statement
    } // End of if/ else statement
    userBook.updateUserBook(user_list, book_table);
  } // End of method returnBook

  

  
  
  /*
   * Summary: This method is used to rent a book
   * Parameter(s): keyboardinput of type Scanner, userBook of type UserBook, panel of type JFrameLibrary.
   * Return(s): none.
   */
  private void rentBook(Scanner keyboardinput, UserBook userBook, JFrameLibrary panel) {
    int search_key, i, j, k;
    Boolean user_check = false, book_check = false;
    String show_book_title = "";
    System.out.print("Input Book ID to rent the book (or Back to the menu = -1) : ");

    search_key = Integer.parseInt(selectMenu(keyboardinput));

    if (search_key == -1) { // If user input -1, return to the main menu
      return;
    } else {
      for (i = 0; i < book_table.size(); i++) { // Loop through the book table
        for (j = 0; j < book_table.get(i).size(); j++) { // Loop through the book table
          if (book_table.get(i).get(j).id == search_key) { // If the book id is the same as the user input
            book_check = true;
            
            if (book_table.get(i).get(j).rent_by != -1) { // If the book is already rented
              panel.errMsg("This book (" + book_table.get(i).get(j).book_title + ") is already on rent.");
              System.out.println("This book (" + book_table.get(i).get(j).book_title + ") is already on rent.");
            } else {
              book_table.get(i).get(j).rent_by = nowUser.id;
              show_book_title = book_table.get(i).get(j).book_title;
              
              for (k = 0; k < user_list.size(); k++) { // Loop through the user list
                if (user_list.get(k).id == nowUser.id) { // If the user id is the same as the user input
                  ArrayList<Boolean> book_list_status = new ArrayList<Boolean>();
                  book_list_status.add(true);
                  book_list_status.add(false);
                  user_list.get(k).book_list.put(book_table.get(i).get(j).id, book_list_status);
                  nowUser = user_list.get(k);
                  user_check = true;
                } // End of if statement
              } // End of for loop
              
              if (!user_check) { // If the user id is not in the user list
                panel.errMsg("Invalied User");
                System.out.println("Invalied User");
              } // End of if statement
            } // End of if/else statement
          } // End of if statement
        } // End of for loop
      } // End of for loop
      
      if (!book_check) { // If the book id is not in the book table
        panel.errMsg("Invalied Book ID");
        System.out.println("Invalied Book ID");
      } // End of if statement
      
      if (user_check && book_check) { // If the user and book id is valid
        clearScreen();
        panel.successMsg("The book (" + show_book_title + ") is rent by " + nowUser.user_name + " successfully");
        System.out.println("The book (" + show_book_title + ") is rent by " + nowUser.user_name + " successfully");
      } // End of if statement
    } // End of if/ else statement
    userBook.updateUserBook(user_list, book_table);
  } // End of method rentBook


  
  
  
  /*
   * Summary: This menu is for Searching books in the library
   * Parameter(s): keyboardinput of type Scanner, Boolean user, userBook of type UserBook, panel of type JFrameLibrary.
   * Return(s): type int
   */
  public int searchBook(Scanner keyboardinput, Boolean user, UserBook userBook, JFrameLibrary panel) {
    Searching searching = new Searching();

    clearScreen();
    panel.searchBookMenu();

    while (true) { // Loop for search menu
      System.out.println("<Search Books Menu>");
      System.out.println();
      System.out.println("[1] Category Searching");
      System.out.println("[2] Title Searching");
      System.out.println("[3] Back to Menu");
      System.out.println();

      System.out.print("Select the menu : ");
      switch (selectMenu(keyboardinput)) { // Switch statement for search menu
        case "1":
        case "category":
          clearScreen();
          searching.categorySearch(book_table, keyboardinput, user, panel);
          break;

        case "2":
        case "title":
        case "name":
          clearScreen();
          searching.titleSearch(book_table, keyboardinput, user, panel);
          break;

        case "3":
        case "exit":
        case "q":
          clearScreen();
          return 0;
      } // End of switch statement

      if (user) {
        rentBook(keyboardinput, userBook, panel);
      } // End of if statement
    } // End of while loop
  } // End of method searchBook

 
  
  
  
  /*
   * Summary: This method if for menu for Searching books in the library
   * Parameter(s): keyboardinput of type Scanner, panel of type JFrameLibrary.
   * Return(s): type int
   */
  public int requestNewBook(Scanner keyboardinput, JFrameLibrary panel) {
    String title, category_select;
    int j, category, book_id = 0;
    Book newBook = new Book();

    clearScreen();
    panel.requestNewBook();
    System.out.println("<Reqeust New Book>");
    System.out.println();
    System.out.print("Book Title : ");
    try { // Try statement for input validation
      title = keyboardinput.nextLine();
    } catch (Exception e) {
      panel.errMsg("Wrong input. Please try again.");
      System.out.print("Wrong input. Please try again.\n\n");
      keyboardinput.next();
      return -1;
    } // End of try/catch statement

    Searching searching = new Searching();
    panel.categoryList(searching.category);
    for (int i : searching.category.keySet()) { // Loop through the category list
      System.out.println();
      System.out.println("[" + i + "] " + searching.category.get(i));
    } // End of for loop

    System.out.print("Book Category : ");

    try { // Try statement for input validation
      category_select = keyboardinput.nextLine();
    } catch (Exception e) {
      panel.errMsg("Wrong input. Please try again.");
      System.out.print("Wrong input. Please try again.\n\n");
      keyboardinput.next();
      return -1;
    } // End of try/catch statement

    category = searching.categoryToSearch(category_select);
    
    for (j = 0; j < book_table.size(); j++) { // Loop through the book table
      book_id += book_table.get(j).size();
    } // End of for loop
    
    newBook.id = book_id;
    newBook.book_title = title;
    newBook.category = category;
    newBook.rent_by = -1;

    book_table.get(category).add(newBook);
    panel.successMsg("Request book successfully.");
    System.out.println("Request book successfully.");

    return 0;
  } // End of method requestNewBook


  
  
  
  /*
   * Summary: This method is for information of the library
   * Parameter(s): keyboardinput of type Scanner, panel of type JFrameLibrary.
   * Return(s): none.
   */
  public void showInfo(Scanner keyboardinput, JFrameLibrary panel) {
    clearScreen();
    String back;

    panel.showInfo();
    System.out.println("<Library Info>");
    System.out.println();
    System.out.println("Open Time : 24/7");
    System.out.println("Address : 120 14th St W, Vancouver, BC");
    System.out.println("Parking : NO");
    System.out.println("Phone Number : 778-689-9879");
    System.out.println();
    System.out.println("Press \"q\" key to back to menu");

    back = selectMenu(keyboardinput);
    if (back.equals("q")) { // If user input q, return to the main menu
      return;
    } // End of if statement
  } // End of method showInfo




  
  /*
   * Summary: This method is for menu for user when they log-int
   * Parameter(s): keyboardinput of type Scanner, userBook of type UserBook, panel of type JFrameLibrary.
   * Return(s): type int
   */
  public int userPanel(Scanner keyboardinput, UserBook userBook, JFrameLibrary panel) {
    String pw;
    clearScreen();
    panel.userPanel(nowUser);

    while (true) { // Loop for user menu
      System.out.println("<Welcome " + nowUser.user_name + " >");
      System.out.println();
      System.out.println("[1] Search and Rent");
      System.out.println("[2] My Book List");
      System.out.println("[3] Change password");
      System.out.println("[4] Logout");
      System.out.println();

      System.out.print("Select the menu : ");

      switch (selectMenu(keyboardinput)) { // Switch statement for user menu
        case "1":
        case "search":
        case "rent":
        case "searchandrent":
        case "searchrent":
          searchBook(keyboardinput, true, userBook, panel);
          break;

        case "2":
        case "book":
        case "list":
        case "mybooklist":
          clearScreen();
          userBook.printUserBookState(nowUser, panel);
          System.out.println("<My Books Menu>");
          System.out.println();
          System.out.println("[1] Return Book");
          System.out.println("[2] Read Complete");
          System.out.println("[3] Back to Menu");
          System.out.println();
          System.out.print("Select Menu : ");

          switch (selectMenu(keyboardinput)) { // Switch statement for my book menu
            case "1":
            case "return":
            case "returnbook":
              returnBook(keyboardinput, userBook, panel);
              break;
            case "2":
            case "read":
            case "complete":
            case "readcomplete":
              readBook(keyboardinput, userBook, panel);
              break;
            case "3":
            case "exit":
            case "q":
              break;
            default:
              panel.errMsg("Invalied Input.");
              System.out.println("Invalied Input.");
              break;
          } // End of switch statement
          break;

        case "3":
        case "changepassword":
          clearScreen();
          panel.changePassword();
          System.out.println();
          System.out.println("<Change Password>");
          System.out.println();
          System.out.print("Current Password : ");
          pw = selectMenu(keyboardinput);
          if (nowUser.user_pw.equals(pw)) { // If user input the current password
            System.out.print("New Password : ");
            pw = selectMenu(keyboardinput);
            System.out.print("New Password Verification : ");
            if (pw.equals(selectMenu(keyboardinput))) { // If user input the new password
              user_list.get(nowUser.id).user_pw = pw;
            } else {
              panel.errMsg("Wrong Password Verification.");
              System.out.println("Wrong Password Verification.");
              break;
            } // End of if /else statement
          } else {
            panel.errMsg("Wrong Password.");
            System.out.println("Wrong Password.");
            break;
          } // End of if/else statement
          break;

        case "4":
        case "logout":
        case "exit":
        case "q":
          clearScreen();
          return 0;

      } // End of switch statement
    } // End of while loop
  } // End of method userPanel




  
  /*
   * Summary: This method is for menu for user when they trying to log-in or register
   * Parameter(s): keyboardinput of type Scanner, userBook of type UserBook, panel of type JFrameLibrary.
   * Return(s): type int
   */
  public int userMenu(Scanner keyboardinput, UserBook userBook, JFrameLibrary panel) {
    int i;
    String user_id, pw;
    boolean user_check = false;
    clearScreen();
    panel.userMenu();

    while (true) { // Loop for user log-in menu
      System.out.println("<User Menu>");
      System.out.println();
      System.out.println("[1] Login");
      System.out.println("[2] Register");
      System.out.println("[3] Exit");
      System.out.println();

      System.out.print("Select the menu : ");
      switch (selectMenu(keyboardinput)) { // Switch statement for user log-in menu
        case "1":
        case "login":
          clearScreen();
          panel.userLogin();
          System.out.println();
          System.out.println("<Login Panel>");
          System.out.println();
          System.out.print("User ID : ");
          user_id = selectMenu(keyboardinput);

          for (i = 0; i < user_list.size(); i++) { // Loop through the user list
            if (user_list.get(i).user_id.equals(user_id)) {
              nowUser = user_list.get(i);
              user_check = true;
            } // End of if statement
          } // End of for loop

          if (user_check == false) { // If user input the wrong user id
            panel.errMsg("Invalied ID");
            System.out.println("Invalied ID");
            break;
          } // End of if statement

          System.out.print("User PW : ");
          pw = selectMenu(keyboardinput);

          if (nowUser.user_pw.equals(pw)) { // If user input the correct password
            userPanel(keyboardinput, userBook, panel);
          } else {
            panel.errMsg("Wrong Password. Please try again.");
            System.out.println("Wrong Password. Please try again.");
            break;
          } // End of if/else statement
          panel.userMenu();
          break;

        case "2":
        case "register":
          clearScreen();
          panel.userRegister();
          userRegister(keyboardinput, panel);
          break;

        case "3":
        case "exit":
        case "q":
          clearScreen();
          return 0;

      } // End of switch statement
    } // End of while loop
  } // End of method userMenu


  


  /*
   * Summary: This method is for user registring which they trying to make their account
   * Parameter(s): keyboardinput of type Scanner, panel of type JFrameLibrary.
   * Return(s): none.
   */
  private void userRegister(Scanner keyboardinput, JFrameLibrary panel) {
    int i;
    String user_id, pw, name;
    boolean user_check = false;
    User newUser = new User();

    System.out.println();
    System.out.println("<Register Panel>");
    System.out.println();
    System.out.print("User Name : ");
    try { // Try statement for user name
      name = keyboardinput.nextLine();
    } catch (Exception e) {
      panel.errMsg("Wrong input. Please try again.");
      System.out.print("Wrong input. Please try again.\n\n");
      return;
    } // End of try/catch statement
    
    System.out.print("User ID : ");
    user_id = selectMenu(keyboardinput);
    for (i = 0; i < user_list.size(); i++) { // Loop through the user list
      if (user_list.get(i).user_id.equals(user_id)) { // If user input the same user id
        panel.errMsg("The user id " + user_id + " is already taken.");
        System.out.print("The user id " + user_id + " is already taken.\n\n");
        user_check = true;
      } // End of if statement
    } // End of for loop

    if (user_check) { // If user input the same user id
      user_check = false;
      return;
    } // End of if statement

    System.out.print("User PW : ");
    pw = selectMenu(keyboardinput);
    newUser.id = user_list.size();
    newUser.user_id = user_id;
    newUser.user_pw = pw;
    newUser.user_name = name;
    user_list.add(newUser);

    panel.userMenu();

    return;
  } // End of method userRegister
  

  
  
  
  /*
   * Summary: This function prints out the title of program.
   * Parameter(s): none.
   * Return(s): none.
   */
  public static void titleLibrary() {
    System.out.println("░██████╗░██╗░░░██╗██╗██╗░░░░░██╗░░░░░░██████╗░██╗░░░██╗███████╗░██████╗████████╗");
    System.out.println("██╔═══██╗██║░░░██║██║██║░░░░░██║░░░░░██╔═══██╗██║░░░██║██╔════╝██╔════╝╚══██╔══╝");
    System.out.println("██║██╗██║██║░░░██║██║██║░░░░░██║░░░░░██║██╗██║██║░░░██║█████╗░░╚█████╗░░░░██║░░░");
    System.out.println("╚██████╔╝██║░░░██║██║██║░░░░░██║░░░░░╚██████╔╝██║░░░██║██╔══╝░░░╚═══██╗░░░██║░░░");
    System.out.println("░╚═██╔═╝░╚██████╔╝██║███████╗███████╗░╚═██╔═╝░╚██████╔╝███████╗██████╔╝░░░██║░░░");
    System.out.println("░░░╚═╝░░░░╚═════╝░╚═╝╚══════╝╚══════╝░░░╚═╝░░░░╚═════╝░╚══════╝╚═════╝░░░░╚═╝░░░");  
  } // End of titleLibrary function

  
  
  
  
  /*
   * Summary: This function prints out the welcome message of the program
   * Parameter(s): none.
   * Return(s): none.
   */
  public static void welcomeMsg() {
    System.out.println("Welcome to QuillQuest!"); // prints out welcome message of the program
    System.out.println("Do you want to check the availability of books?");
    System.out.println("Do you want to borrow a book using your own account?");
    System.out.println(
        "Then, this is for you!! Come and sign up for QuillQuest, your life of reading will be change!!\nEnjoy!!");
  } // End of welcomeMsg function

  
  
  
  
  /*
   * Summary: This function prints out the introduction of the program
   * Parameter(s): none.
   * Return(s): none.
   */
  public static void introLibrary() {
    System.out.println("\t\n [Introduction] ");
    System.out.println("\t\n QuillQuest is a program that helps to explore entire library without going in-person \n");
    System.out.println("\t\n People can see the availability of books in each categories \n");
    System.out.println("\t\n They can register and log in their account to borrow/rent the book and check their book list that they have been borrowed \n");
    System.out.println("\t\n They also can request the book that they want to read \n");
    System.out.println("\t\n Recommend looking at JFrame together while exploring this program\n");
  } // End of introLibrary function

  
  
  
  
  /*
   * Summary: This function prints out the explaination of the program.
   * Parameter(s): none.
   * Return(s): none.
   */
  public static void explainationLibrary() {
    System.out.println("\t\n [Direction] ");
    System.out.println("\t\n Enter any key to start, will show up the menu \n");
    System.out.println("\t\n In the menu, people can see the search the books, information of the library, login/register their account, and request their book \n");
    System.out.println("\t\n To borrow the book, people need to register their account or if they already have it they can login to their account \n");
    System.out.println("\t\n Remember you cannot have same ID then other people \n");
    System.out.println("\t\n They can see each categories and input the book ID in able to borrow it \n");
    System.out.println("\t\n You can see your own book list and check if you read that completely and return the book \n");
  } // End of explainationLibrary function

  
  
  
  
  /*
   * Summary: This function prints out the thank you message of the program.
   * Parameter(s): none.
   * Return(s): none.
   */
  public static void thanksMsg() {
    System.out.println("\nThank you for using our library website"); // prints out the thank you message
    System.out.println("We hope you enjoy and make sure you come back when you want to borrow in top priority.");
    System.out.println("We look forward to welcoming you back again, We will give the comfortable service again.");
  } // End of thanksMsg function


  
  
  
  /*
   * Summary: This method is for clearing screen
   * Parameter(s): none.
   * Return(s): none.
   */
  public void clearScreen() {
    System.out.print('\u000C');
  } // End of clearScreen method

  
  
  
  
  /*
   * Summary: This is a main of the program
   * Parameter(s): String[] args
   * Return(s): none.
   */
  public static void main(String[] args) {
    Scanner keyboardinput = new Scanner(System.in); // calling keyboardinput
    String selected = "";
    Boolean running = true;
    JFrameLibrary panel = new JFrameLibrary();

    FileController fileController = new FileController();
    Library library = new Library(fileController);
    UserBook userBook = new UserBook(library.user_list, library.book_table);

    System.out.println("\f"); // clears the terminal
    System.out.flush(); // clears the keyboardinput

    titleLibrary();
    welcomeMsg();
    introLibrary();

    System.out.println("Press any key...");
    selected = keyboardinput.nextLine();

    explainationLibrary();
    while (running) { // while loop for the program
      library.clearScreen();
      panel.mainMenu();
      selected = library.mainMenu(keyboardinput);
      switch (selected) { // switch statement for the main menu
        case "1":
        case "search":
        case "searchbook":
          library.searchBook(keyboardinput, false, userBook, panel);
          break;

        case "2":
        case "user":
        case "userlogin":
        case "userregister":
        case "login":
        case "register":
          library.userMenu(keyboardinput, userBook, panel);
          break;

        case "3":
        case "newbook":
        case "request":
        case "requestnewbook":
          library.requestNewBook(keyboardinput, panel);
          break;

        case "4":
        case "library":
        case "info":
        case "information":
        case "libraryinfo":
        case "libraryinformation":
          library.showInfo(keyboardinput, panel);
          break;

        case "5":
        case "exit":
        case "q":
          fileController.saveBookData(library.book_table);
          fileController.saveUserData(library.user_list);
          running = false;
          break;

        default:
          panel.errMsg("Wrong input.");
          System.out.println("Wrong input.");
          break;
      } // End of switch statement

    } // End of while loop

    library.clearScreen();
    thanksMsg();

    keyboardinput.close();
  } // End of main method
} // End of class Library




/*
 * 
 * Notes:
 * Please research the library website and look how it looks
 * lets add user log-in registration 
 * lets add user can see the book in categories
 * Please trying to make a real library
 * 
 *
 * Test Code:
 *
 *   // for(int i=0; i < library.user_list.size() ; i++) {
    //   userBook.printUserBookState(library.user_list.get(i));
    // }
 */