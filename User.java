import java.util.ArrayList;
import java.util.HashMap;

public class User {
  // Member
  public int id;
  public String user_name;
  public String user_id;
  public String user_pw;
  public HashMap<Integer, ArrayList<Boolean>> book_list = new HashMap<Integer, ArrayList<Boolean>>();
  public ArrayList<Boolean> book_list_status = new ArrayList<Boolean>();

  public void printUserInfo(User user) {
    System.out.print(user.id);
    System.out.print("\t" + user.user_name);
    System.out.print("\t" + user.user_id);

    System.out.println();
  }
}
