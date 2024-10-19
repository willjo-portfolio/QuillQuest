import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Searching {

  public HashMap<Integer, String> category = new HashMap<>();

  public Searching() {
    category.put(0, "Computer science, information and general works");
    category.put(1, "Philosophy and psychology");
    category.put(2, "Religion");
    category.put(3, "Social sciences");
    category.put(4, "Language");
    category.put(5, "Pure science");
    category.put(6, "Technology");
    category.put(7, "Arts and recreation");
    category.put(8, "Literature");
    category.put(9, "History and geography");
  }

  public int categoryToSearch(String category) {

    switch (category) {
      case "0":
      case "computer":
      case "computerscience":
      case "information":
      case "general":
      case "generalworks":
      case "computerscienceinformationandgeneralworks":
        return 0;

      case "1":
      case "philosophypsychology":
      case "psychology":
      case "philosophy":
        return 1;

      case "2":
      case "religion":
        return 2;

      case "3":
      case "social":
      case "socialsciences":
        return 3;

      case "4":
      case "language":
        return 4;

      case "5":
      case "purescience":
      case "pure":
        return 5;

      case "6":
      case "technology":
        return 6;

      case "7":
      case "arts":
      case "artsandrecreation":
      case "artsrecreation":
      case "recreation":
        return 7;

      case "8":
      case "literature":
        return 8;

      case "9":
      case "history":
      case "historyandgeography":
      case "historygeography":
      case "geography":
        return 9;

      case "10":
      case "back":
      case "exit":
      case "q":
        return -1;

      default:
        System.out.println("Wrong Input.");
        return -2;
    }

  }

  public void categorySearch(ArrayList<ArrayList<Book>> book_table, Scanner keyboardinput, boolean user,
      JFrameLibrary panel) {
    String select;
    int i, search_key = -1;

    panel.categorySearch(category);
    while (true) {
      System.out.println("<Category Searching>");
      System.out.println();
      for (int category_number : category.keySet()) {
        System.out.println("[" + category_number + "] " + category.get(category_number));
      }
      System.out.println("[" + category.keySet().size() + "] Back to the Menu");
      System.out.println();
      System.out.print("Select category : ");

      select = Library.selectMenu(keyboardinput);

      search_key = categoryToSearch(select);

      if (search_key == -1) {
        panel.searchBookMenu();
        return;
      }
      if (search_key == -2) {
        continue;
      }

      panel.categoryBookList(search_key, book_table, category);

      for (i = 0; i < book_table.get(search_key).size(); i++) {
        System.out.println();
        System.out.println("Book title : " + book_table.get(search_key).get(i).book_title);
        System.out.println("\tBook ID : " + book_table.get(search_key).get(i).id);
        System.out.println(
            "\ton Rent : " + (book_table.get(search_key).get(i).rent_by < 0 ? "Can be Rent" : "Already Rented"));
        System.out.println();
      }

      if (user) {
        panel.searchBookMenu();
        return;
      }

      System.out.println("Press any key to search continue");
      select = keyboardinput.nextLine();
    }
  }

  public void titleSearch(ArrayList<ArrayList<Book>> book_table, Scanner keyboardinput, boolean user,
      JFrameLibrary panel) {

    int i, j;
    String search_key;
    panel.titleSearch();

    while (true) {

      System.out.println("<Title Searching>");
      System.out.println();
      System.out.print("Enter keyword (Back to the menu = -1) : ");

      search_key = Library.selectMenu(keyboardinput);

      if (search_key.equals("-1")) {
        panel.searchBookMenu();
        return;
      }

      panel.titleBookList(search_key, book_table);

      for (i = 0; i < book_table.size(); i++) {
        for (j = 0; j < book_table.get(i).size(); j++) {
          if (book_table.get(i).get(j).book_title.contains(search_key)) {
            System.out.println();
            System.out.println("Book title : " + book_table.get(i).get(j).book_title);
            System.out.println("\tBook ID : " + book_table.get(i).get(j).id);
            System.out.println(
                "\ton Rent : " + (book_table.get(i).get(j).rent_by < 0 ? "Can be Rent" : "Already Rented"));
            System.out.println();
          }
        }
      }

      if (user) {
        panel.searchBookMenu();
        return;
      }

      System.out.println("Press any key to search continue");
      search_key = keyboardinput.nextLine();
    }
  }
}
