import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FileController {

  public ArrayList<User> loadUserData() {

    ArrayList<User> newUserList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("./save/user_list.csv"))) {
      String line;

      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        User newUser = new User();
        int i = 0;

        newUser.id = Integer.parseInt(values[0]);
        newUser.user_name = values[1];
        newUser.user_id = values[2];
        newUser.user_pw = values[3];
        if (values.length > 4) {
          newUser.book_list_status.add(Boolean.parseBoolean(values[5]));
          newUser.book_list_status.add(Boolean.parseBoolean(values[6]));
          newUser.book_list.put(Integer.parseInt(values[4]), newUser.book_list_status);

          if (values.length > 7) {
            for (i = 7; i < values.length; i += 3) {
              ArrayList<Boolean> book_list_status = new ArrayList<Boolean>();
              book_list_status.add(Boolean.parseBoolean(values[i + 1]));
              book_list_status.add(Boolean.parseBoolean(values[i + 2]));
              newUser.book_list.put(Integer.parseInt(values[i]), book_list_status);
            }
          }
        }
        newUserList.add(newUser);
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (NumberFormatException | IOException ex) {
      ex.printStackTrace();
    }
    return newUserList;
  }

  public ArrayList<ArrayList<Book>> loadBookData() {

    ArrayList<Book> newBookList0 = new ArrayList<>();
    ArrayList<Book> newBookList1 = new ArrayList<>();
    ArrayList<Book> newBookList2 = new ArrayList<>();
    ArrayList<Book> newBookList3 = new ArrayList<>();
    ArrayList<Book> newBookList4 = new ArrayList<>();
    ArrayList<Book> newBookList5 = new ArrayList<>();
    ArrayList<Book> newBookList6 = new ArrayList<>();
    ArrayList<Book> newBookList7 = new ArrayList<>();
    ArrayList<Book> newBookList8 = new ArrayList<>();
    ArrayList<Book> newBookList9 = new ArrayList<>();
    ArrayList<ArrayList<Book>> newBookTable = new ArrayList<ArrayList<Book>>();

    try (BufferedReader br = new BufferedReader(new FileReader("./save/book_list.csv"))) {
      String line;

      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        Book newBook = new Book();
        newBook.id = Integer.parseInt(values[0]);
        newBook.book_title = values[1].replaceAll("_", " ");
        newBook.category = Integer.parseInt(values[2]);
        newBook.rent_by = Integer.parseInt(values[3]);

        switch (newBook.category) {
          case 0:
            newBookList0.add(newBook);
            break;
          case 1:
            newBookList1.add(newBook);
            break;
          case 2:
            newBookList2.add(newBook);
            break;
          case 3:
            newBookList3.add(newBook);
            break;
          case 4:
            newBookList4.add(newBook);
            break;
          case 5:
            newBookList5.add(newBook);
            break;
          case 6:
            newBookList6.add(newBook);
            break;
          case 7:
            newBookList7.add(newBook);
            break;
          case 8:
            newBookList8.add(newBook);
            break;
          case 9:
            newBookList9.add(newBook);
            break;
          default:
            break;
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (NumberFormatException | IOException ex) {
      ex.printStackTrace();
    }

    newBookTable.add(0, newBookList0);
    newBookTable.add(1, newBookList1);
    newBookTable.add(2, newBookList2);
    newBookTable.add(3, newBookList3);
    newBookTable.add(4, newBookList4);
    newBookTable.add(5, newBookList5);
    newBookTable.add(6, newBookList6);
    newBookTable.add(7, newBookList7);
    newBookTable.add(8, newBookList8);
    newBookTable.add(9, newBookList9);

    return newBookTable;
  }

  public int saveUserData(ArrayList<User> UserList) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./save/user_list.csv"))) {
      int i, j;

      for (i = 0; i < UserList.size(); i++) {
        String aData = "";
        User saveUser = new User();
        saveUser = UserList.get(i);

        aData += saveUser.id + ",";
        aData += saveUser.user_name + ",";
        aData += saveUser.user_id + ",";
        aData += saveUser.user_pw + ",";
        for (int book_id : saveUser.book_list.keySet()) {
          aData += book_id + ",";
          for (j = 0; j < saveUser.book_list.get(book_id).size(); j++) {
            aData += saveUser.book_list.get(book_id).get(j) + ",";
          }
        }

        aData = aData.substring(0, aData.length() - 1);

        bw.write(aData);
        bw.newLine();
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return -1;
    } catch (NumberFormatException | IOException ex) {
      ex.printStackTrace();
      return -1;
    }
    return 0;
  }

  public int saveBookData(ArrayList<ArrayList<Book>> BookList) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./save/book_list.csv"))) {
      int i, j;

      for (i = 0; i < BookList.size(); i++) {
        for (j = 0; j < BookList.get(i).size(); j++) {
          String aData = "";
          Book saveBook = new Book();
          saveBook = BookList.get(i).get(j);

          aData += saveBook.id + ",";
          aData += saveBook.book_title + ",";
          aData += saveBook.category + ",";
          aData += saveBook.rent_by;

          bw.write(aData);
          bw.newLine();
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return -1;
    } catch (NumberFormatException | IOException ex) {
      ex.printStackTrace();
      return -1;
    }
    return 0;
  }
}