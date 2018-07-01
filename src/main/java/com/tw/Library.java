package com.tw;

import java.util.*;

public class Library {
    private Map<String, Student> studentMap = new HashMap<>();
    private Command command = new Command();
    private Menu menu = new Menu();

//    public Scanner scanner = new Scanner(System.in);

//   private Set<Student> students = new HashSet<>();
//        students.add(new Student("张三", 1001, 85, 78, 79, 98));
//        students.add(new Student("李四", 1002, 88, 74, 79, 68));
    public Library() {
    }

    public Library(Map<String, Student> studentMap, Command command) {
        this.studentMap = studentMap;
        this.command = command;
    }

    public void showMainMenu() {
       menu.mainMenu();
    }

    public void mainMenuService() {
        String input = command.inputInfo();
        if (command.inputCheck(input, Command.inputCommandCheck)) {
            switch (Integer.parseInt(input)) {
                case 1:
                    showAddStudentMenu();
                    addStudentService();
                    break;
                case 2:
                    showGenerateReportMenu();
                    generateReportService();
                    break;
                case 3:
                    exitMenu();
                    break;
            }
        } else {
            menu.errorInput();
        }
    }

    public void showAddStudentMenu() {
        menu.addStudentMenu();
    }

    public void addStudentService() {
        while (true) {
            String input = command.inputInfo();
            if (command.inputCheck(input, Command.inputStudentCheck)) {
                String[] arr = input.split(",");
                Map<String, Float> scole = new HashMap<>();
                for (int i = 2; i < arr.length; ++i) {
                    String[] cour = arr[i].split(":");
                    scole.put(cour[0], Float.valueOf(cour[1]));
                }
                Student student = new Student();
                student.addStudent(arr[0], Integer.valueOf(arr[1]), scole);
                menu.addingStudentSuccess(student);
                break;
            } else {
                menu.inputErrorStudentInfo();
            }
        }
    }

    public void showGenerateReportMenu() {
        menu.generateReportMenu();

    }

    public void generateReportService() {
        float totalScole = 0;
        while (true) {
            String input = command.inputInfo();
            if (command.inputCheck(input, Command.inputIdCheck)) {
                System.out.print("```\n成绩单\n姓名|数学|语文|英语|编程|平均分|总分\n========================\n");
                String[] idStr = input.split(",");
                for (String id : idStr) {
                    Set<String> idSet = new HashSet<>(studentMap.keySet());
                    if (idSet.contains(id)) {
                        Student student = studentMap.get(id);
                        System.out.println(studentMap.get(id).getRecord());
                        totalScole += student.getTotal();
                    }
                }
                System.out.println("========================\n```");
                System.out.println("全部总分平均数：" + totalScole / studentMap.size());
                System.out.println("全部总分中位数：" + midea());
                break;

            } else {
                menu.inputErrorReportInfo();
            }
        }
    }

    public float midea() {
        if (studentMap.size() == 0) {
            return 0;
        } else {
            List<Float> totalList = new ArrayList<>();
            Set<String> idSet = studentMap.keySet();
            for (String id : idSet) {
                totalList.add(studentMap.get(id).getTotal());
            }
            Collections.sort(totalList);
            int len = totalList.size();
            float mid = 0;
            if (len % 2 == 0) {
                mid = ((totalList.get(len / 2 - 1)) + totalList.get(len / 2)) / 2;
            } else {
                mid = totalList.get(len / 2);
            }
            return mid;
        }
    }

    public void exitMenu() {
        menu.exitMenu();
        System.exit(0);
    }

}

