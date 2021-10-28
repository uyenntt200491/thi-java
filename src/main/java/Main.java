import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    String title = "ID,Name,Email,Bonus,Report,App,LT,Total";
    String titleReport = "Thống kê,số lượng,tỉ lệ";
    String st1 = "18424005,Đặn Văn Thường,dvthuong@gmail.com,0,0,0,4.5";
    String st2 = "18424013,Nguyễn Ngọc Châu,nnchau@outlook.com,2,8,8,5.5";

    public List<Student> convertDateToList(String datas){
        String lines[] = datas.split("\n");
        List<Student> students = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String l = lines[i];
            String data[] = l.split(",");
            if(data.length > 0){
                students.add(new Student(
                    data[0],
                    data[1],
                    data[2],
                    Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]),
                    Integer.parseInt(data[5]),
                    Double.parseDouble(data[6])
                ));
            }
        }
        return students;
    }

    public List<Student> sortByScore(List<Student> students){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getLt() > o2.getLt() ? -1 : 1;
            }
        });
        return students;
    }

    public List<Student> getTop10ByScore(List<Student> students){
        List<Student> sortedList = sortByScore(students);
        List<Student> result = new ArrayList<>();
        int end = sortedList.size() < 10 ? sortedList.size() : 10;
        for (int i = 0; i < end; i++) {
            result.add(sortedList.get(i));
        }
        return result;
    }

    public List<Student> sortByTotalScore(List<Student> students){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getTotal() > o2.getTotal() ? 1 : -1;
            }
        });
        return students;
    }

    public List<Student> calculateTotal(List<Student> students){
        students.forEach((s)->{
            Double total = s.getBonus()*0.1 + s.getReport()*0.3 + s.getApp()*0.15 + s.getLt()*0.45;
            s.setTotal(this.roundToHalf(total.doubleValue()));
        });
        return students;
    }

    public double roundToHalf(double d) {
        return Math.round(d * 2) / 2.0;
    }

    public void displayTables(List<Student> students){
        System.out.println(title);
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%s,%s,%s,%d,%d,%d,%f,%f",
                              s.getId(),
                              s.getName(),
                              s.getEmail(),
                              s.getBonus(),
                              s.getReport(),
                              s.getApp(),
                              s.getLt(),
                              s.getTotal()
                              );
            System.out.println();
        }
    }

    public void displayReportTables(List<Student> students){
        int gioi = 0;
        int kha = 0;
        int trungbinh = 0;
        int khongdat = 0;
        int dat=0;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            double total = s.getTotal();
            if(total <= 4.5){
                khongdat++;
            }else if(total <=6.5){
                trungbinh++;
            }else if(total <=8){
                kha++;
            }else {
                gioi++;
            }
        }
        int total = students.size();
        System.out.println(titleReport);
        System.out.printf("Số lượng sinh viên,%d,", students.size());
        System.out.println();
        System.out.printf("Đạt(điểm tổng kết > 4.5),%d,%d%%", (total - khongdat) , (total - khongdat)*(total/100));
        System.out.printf("Không đạt,%d,%d%%", khongdat ,khongdat*(total/100));
        System.out.printf("Giỏi (điểm tổng kết 8-10),%d,%d%%", gioi , gioi*(total/100));
        System.out.printf("Khá(điểm tổng kết 6.5-8),%d,%d%%", kha , kha*(total/100));
        System.out.printf("Trung bình(điểm tổng kết 5-6.5),%d,%d%%", trungbinh , trungbinh*(total/100));
    }
}
