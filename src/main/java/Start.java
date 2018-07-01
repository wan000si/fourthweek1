import com.tw.Library;
import com.tw.Student;

import java.util.HashSet;
import java.util.Set;

public class Start {
    public static void main(String[] args) {
        Library library = new Library();
        while (true) {
            library.showMainMenu();
            library.mainMenuService();
        }

    }
}
