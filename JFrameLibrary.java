//Import statement
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*; // JFrame
// import javax.swing.event.DocumentListener;
// import javax.swing.text.Document;
// import javax.swing.event.DocumentEvent;

import java.awt.*;
// import java.net.URI;
// import java.net.URISyntaxException;
// import java.io.IOException;

public class JFrameLibrary {

  // Member

  static JFrame frame = new JFrame("Assignment 7 Library");

  static JPanel mainHeader = new JPanel();
  static JPanel mainBody = new JPanel();

  static JLabel headerTitle = new JLabel("Welcome to Library");
  static JLabel userName = new JLabel("Corrent User : ");
  static JLabel classroomName = new JLabel("Classroom");

  static String userBookTableHeader = "<html><br><br><table><tr><th>User Name</th><th>Book Title</th><th>Book ID</th><th>On Rent</th><th>Read Complete</th></tr>";
  static String bookListHeader = "<html><br><br><table><tr><th>Book Title</th><th>Book ID</th><th>Category</th><th>Rent by</th></tr>";

  // Constructor

  public JFrameLibrary() {
    // JFrameContinents jframeContinents = new JFrameContinents();
    
    frame.setSize(300, 300);
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainHeader.add(headerTitle);
    
    
    frame.add(mainHeader, BorderLayout.NORTH);
    frame.add(mainBody, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  public void mainMenu() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Library Main Menu</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>[1] Search Book<br>[2] User Login / Register<br>[3] Request New Book<br>[4] Library Info<br>[5] Exit</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void searchBookMenu() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Search Book Menu</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>[1] Category Seaarching<br>[2] Title Searching<br>[3] Back to Menu</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void categorySearch(HashMap<Integer, String> category) {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Category Searching</h1></html>");
    mainHeader.add(title);
    String menuText = "<html><h3>";
    for (int category_number : category.keySet()) {
      menuText += "[" + category_number + "] " + category.get(category_number) + "<br>";
    }
    menuText += "[" + category.keySet().size() + "] Back to the Menu</h3></html>";
    JLabel menu = new JLabel(menuText);
   
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void categoryBookList(Integer search_key, ArrayList<ArrayList<Book>> book_table, HashMap<Integer, String> category) {
    int i;
    String body, footer;

    frame.setSize(500, 300);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Search Result</h1></html>");
    mainHeader.add(title);
    body = "<html><br><br>";
    body += "<h3>Selected Category : " + category.get(search_key) + "</h3>";
    body += bookListHeader;
    for (i = 0; i < book_table.get(search_key).size(); i++) {
      body += "<tr><td>"+ book_table.get(search_key).get(i).book_title + "</td>";
      body += "<td>" + book_table.get(search_key).get(i).id + "</td>";
      body += "<td>" + book_table.get(search_key).get(i).category + "</td>";
      body += "<td>" + (book_table.get(search_key).get(i).rent_by < 0 ? "Can be Rent" : "Already Rented") + "</td></tr>";
    }
    footer = "</html>";

    JLabel showBody = new JLabel(body + footer);
      mainBody.add(showBody);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void titleSearch() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Title Searching</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>Enter keyword<br><br>(Back to the menu = -1)</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void titleBookList(String search_key, ArrayList<ArrayList<Book>> book_table) {
    int i, j;
    String body, footer;

    frame.setSize(500, 300);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Search Result</h1></html>");
    mainHeader.add(title);
    body = "<html><br><br>";
    body += "<h3>Search Keyword : " + search_key + "</h3>";
    body += bookListHeader;

    for (i = 0; i < book_table.size(); i++) {
      for (j = 0; j < book_table.get(i).size(); j++) {
        if (book_table.get(i).get(j).book_title.contains(search_key)) {
          
          body += "<tr><td>"+ book_table.get(i).get(j).book_title + "</td>";
          body += "<td>" + book_table.get(i).get(j).id + "</td>";
          body += "<td>" + book_table.get(i).get(j).category + "</td>";
          body += "<td>" + (book_table.get(i).get(j).rent_by < 0 ? "Can be Rent" : "Already Rented") + "</td></tr>";
        }
      }
    }

    footer = "</html>";

    JLabel showBody = new JLabel(body + footer);
      mainBody.add(showBody);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void userMenu() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>User Menu</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>[1] Login<br>[2] Register<br>[3] Exit</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void userLogin() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>User Login</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>Login via console :)</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void userRegister() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>User Register</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>Register via console :)</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void userPanel(User nowUser) {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Welcome " + nowUser.user_name + "</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>[1] Search and Rent<br>[2] My Book List<br>[3] Change password<br>[4] Logout</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }


  public void errMsg(String errmsg) {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Error !</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>" + errmsg +" :(</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void successMsg(String successmsg) {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Success !</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>" + successmsg +" :)</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void changePassword() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Change Password</h1></html>");
    mainHeader.add(title);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void requestNewBook() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Request New Book</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>Request via console :)</h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void categoryList(HashMap<Integer, String> category) {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Category List</h1></html>");
    mainHeader.add(title);
    String menuText = "<html><h3>";
    for (int category_number : category.keySet()) {
      menuText += "[" + category_number + "] " + category.get(category_number) + "<br>";
    }
    JLabel menu = new JLabel(menuText);
   
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void showInfo() {
    frame.setSize(500, 500);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>Library Information</h1></html>");
    mainHeader.add(title);
    JLabel menu = new JLabel("<html><h3>Open Time : 24/7<br>Address : Some where Vancouver, BC<br>Parking : NO<br></h3></html>");
    mainBody.add(menu);
    SwingUtilities.updateComponentTreeUI(frame);
  }

  public void printUserBookState(User user, HashMap<String, ArrayList<String>> userBookTable) {
    int j;
    String body, footer;

    frame.setSize(1000, 300);
    mainHeader.removeAll();
    mainBody.removeAll();
    JLabel title = new JLabel("<html><h1>" + user.user_name + "'s Book List</h1></html>");
    mainHeader.add(title);
    body = "<html><br><br>";
    body += userBookTableHeader;


    for(String name : userBookTable.keySet()) {
      if(user.user_name.equals(name)) {
        body += "<tr><td>"+ name + "</td>";
        for(j=0; j < userBookTable.get(name).size(); j+=4) {
          if(j >= 4) {
            body += "<tr><td></td>";
          }
          body += "<td>" + userBookTable.get(name).get(j) + "</td>";
          body += "<td>" + userBookTable.get(name).get(j+1) + "</td>";
          body += "<td>" + userBookTable.get(name).get(j+2) + "</td>";
          body += "<td>" + userBookTable.get(name).get(j+3) + "</td></tr>";
        }
      }
    }
    

    footer = "</html>";

    JLabel showBody = new JLabel(body + footer);
      mainBody.add(showBody);
    SwingUtilities.updateComponentTreeUI(frame);
  }
}
