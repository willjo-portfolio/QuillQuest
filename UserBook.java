import java.util.ArrayList;
import java.util.HashMap;

public class UserBook {

  public HashMap<String, ArrayList<String>> userBookTable = new HashMap<String, ArrayList<String>>();

  public UserBook(ArrayList<User> user_list, ArrayList<ArrayList<Book>> book_table) {
    updateUserBook(user_list, book_table);
  }

  public void updateUserBook(ArrayList<User> user_list, ArrayList<ArrayList<Book>> book_table) {
    HashMap<String, ArrayList<String>> newUserBookTable = new HashMap<String, ArrayList<String>>();

    int i , j, k;

    for(k=0; k<user_list.size(); k++){

      ArrayList<String> info = new ArrayList<>();

      for (int book_id : user_list.get(k).book_list.keySet()) {
        for(i=0; i<book_table.size(); i++){
          for(j=0; j<book_table.get(i).size(); j++){
            if(book_table.get(i).get(j).id == book_id) {

              info.add(book_table.get(i).get(j).book_title);
              info.add(book_table.get(i).get(j).id + "");
              info.add(user_list.get(k).book_list.get(book_id).get(0) ? "True" : "False");
              info.add(user_list.get(k).book_list.get(book_id).get(1) ? "True" : "False");

            }
          }
        }
      }
      newUserBookTable.put(user_list.get(k).user_name, info);
      userBookTable = newUserBookTable;
    }
  }

  public void printUserBookState(User user, JFrameLibrary panel) {
    int i, j;

    panel.printUserBookState(user, userBookTable);

    for(i = 0; i < userBookTable.size(); i++) {
      for(String name : userBookTable.keySet()) {
        if(user.user_name == name) {
          System.out.println("User Name : " + name);
          for(j=0; j<userBookTable.get(name).size(); j+=4) {
            System.out.println("\n\tBook name : " + userBookTable.get(name).get(j));
            System.out.println("\tBook ID : " + userBookTable.get(name).get(j+1));
            System.out.println("\tOn rent : " + userBookTable.get(name).get(j+2));
            System.out.println("\tRead : " + userBookTable.get(name).get(j+3));
            System.out.println();
          }
          return;
        }
      }
    }

    panel.errMsg("There are no book history to show");
    System.out.println("There are no book history to show");
  }
}
