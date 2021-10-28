import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testmain {
    private Main main;

    @BeforeEach
    void setup(){
        main = new Main();
    }

    @Test()
    void addSingleElement(){
        String data = "ID,Name,Email,Bonus,Report,App,LT,Total\n"
            + "18424005,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,4.5\n";
        List<Student> result = main.convertDateToList(data);
        assertEquals(result.get(0).getId(), "18424005");
        assertEquals(result.size(), 1);
    }

    @Test()
    void getTop10ByLTScore(){
        String data = "ID,Name,Email,Bonus,Report,App,LT,Total\n"
            + "18424001,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,4\n"
            + "18424002,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,6.5\n"
            + "18424003,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,6.5\n"
            + "18424004,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,7\n"
            + "18424005,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,10\n"
            + "18424006,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,10\n"
            + "18424007,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,4.5\n"
            + "18424008,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,4.5\n"
            + "18424009,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,4.5\n"
            + "18424010,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,4.5\n"
            + "18424011,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,2.5\n";
        List<Student> list = main.convertDateToList(data);
        List<Student> result = main.getTop10ByScore(list);
        Optional<Student> s = result.stream().filter(student -> student.getId().equals("18424011") ).findFirst();
        assertEquals(s.isEmpty(), true);

    }
}
