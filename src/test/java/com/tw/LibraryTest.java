package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LibraryTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Library library;
    private Command command;
    private Student student;
    private Menu menu;

    @Before
    public void setUp() {
        command = mock(Command.class);
        library = new Library(new HashMap<String, Student>(), command);
        System.setOut(new PrintStream(outContent));
    }

    public String systemOut() {
        return outContent.toString();
    }


    @Test
    public void should_return_mainMenu() {
        library.showMainMenu();
        assertThat(systemOut()).isEqualTo("```\n1. 添加学生\n2. 生成成绩单\n3. 退出\n输入你的选择（1～3）：\n```\n");
    }

    @Test
    public void should_test_return_errormessage_when_input_less_than_command() {
        when(command.inputInfo()).thenReturn("0");
        library.mainMenuService();
        assertThat(systemOut()).isEqualTo("```\n输入错误，请重新输入！\n```\n");
    }

    @Test
    public void should_test_return_errormessage_when_error_more_than_command() {
        when(command.inputInfo()).thenReturn("4");
        library.mainMenuService();
        assertThat(systemOut()).isEqualTo("```\n输入错误，请重新输入！\n```\n");
    }

    @Test
    public void should_test_return_errormessage_when_input_1() {
        when(command.inputInfo()).thenReturn("1");
        library.mainMenuService();
        assertThat(systemOut()).isEqualTo("```\n请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n```\n");
    }

    @Test
    public void should_test_return_errormessage_when_input_2() {
        when(command.inputInfo()).thenReturn("2");
        library.mainMenuService();
        assertThat(systemOut()).isEqualTo("```\n请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n```\n");
    }

    @Test
    public void should_return_addmessage_when_input_right1_studentmessage() {
        when(command.inputInfo()).thenReturn("张三,01,数学:85,语文:78,英语:79,编程:98");
        library.addStudentService();
        assertThat(systemOut()).isEqualTo("```\n学生张三的成绩被添加\n```\n");
    }

    @Test
    public void should_return_addmessage_when_input_right2_studentmessage() {
        when(command.inputInfo()).thenReturn("张三,01,数学:85");
        library.addStudentService();
        assertThat(systemOut()).isEqualTo("```\n学生张三的成绩被添加\n```\n");
    }

    @Test
    public void should_return_addmessage_when_input_error1_studentmessage() {
        when(command.inputInfo()).thenReturn("张三,01,数学:");
        library.addStudentService();
        assertThat(systemOut()).isEqualTo("```\n请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n```\n");
    }

    @Test
    public void should_return_addmessage_when_input_error2_studentmessage() {
        when(command.inputInfo()).thenReturn("张三,01,");
        library.addStudentService();
        assertThat(systemOut()).isEqualTo("```\n请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n```\n");
    }

    @Test
    public void should_return_report_when_input_error1_idmessage() {
        when(command.inputInfo()).thenReturn("张三,01,数学:85,语文:78,英语:79,编程:98");
        library.addStudentService();
        when(command.inputInfo()).thenReturn("李四,02,数学:88,语文:74,英语:79,编程:68");
        library.addStudentService();

        when(command.inputInfo()).thenReturn("01,02");
        library.generateReportService();
        assertThat(systemOut()).contains("```\n成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "==============================================================================\n" +
                "张三|85|78|79|98|85.0|340\n" +
                "李四|88|74|79|68|77.25|309\n" +
                "============================================================================= \n```\n" +
                "全班总分平均数：324.5\n" +
                "全班总分中位数：324.5"
        );
    }

    @Test
    public void should_return_report_when_input_right_idmessage() {
        when(command.inputInfo()).thenReturn("张三,01,数学:85,语文:78,英语:79,编程:98");
        library.addStudentService();
        when(command.inputInfo()).thenReturn("李四,02,数学:88,语文:74,英语:79,编程:68");
        library.addStudentService();

        when(command.inputInfo()).thenReturn("aa,02");
        library.generateReportService();
        assertThat(systemOut()).contains("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
    }

}
