package com.tw;

import java.util.Scanner;

public class Menu {

    public void mainMenu() {
        System.out.print("```\n1. 添加学生\n2. 生成成绩单\n3. 退出\n输入你的选择（1～3）：\n```\n");
    }

    public void errorInput() {
        System.out.print("```\n输入错误，请重新输入！\n```\n");
    }

    public void addStudentMenu() {
        System.out.print("```\n请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n```\n");
    }

    public void inputErrorStudentInfo() {
        System.out.print("```\n请输入正确的学生信息（格式：姓名, 学号, 学科: 成绩, ...）：\n```\n");
    }

    public void addingStudentSuccess(Student student) {
        System.out.print("```\n学生" + student.getName() + "的成绩被添加\n```\n");
    }

    public void generateReportMenu() {
        System.out.print("```\n请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n```\n");
    }

    public void inputErrorReportInfo() {
        System.out.print("```\n请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n```\n");
    }

    public void exitMenu() {
        System.out.print("```\n您已退出，谢谢使用！\n```\n");
    }



}
