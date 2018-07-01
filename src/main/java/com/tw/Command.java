package com.tw;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Command {

    public static final int inputCommandCheck = 1;
    public static final int inputStudentCheck = 2;
    public static final int inputIdCheck = 3;

    public Scanner scanner = new Scanner(System.in);
    public String inputInfo() {
        return scanner.next();
    }

    public boolean inputCheck(String input,int flag) {
        String[] in =input.split(",");
        List<String> list=Stream.of(in).map(String::trim).collect(Collectors.toList());

        String regexDigit = "^[+]?\\d+(\\.\\d+)?$";
        boolean validFlag = false;

        switch (flag) {
            case inputCommandCheck:
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < 4) {
                    validFlag = true;
                }
                break;

            case inputStudentCheck:
                if (in.length >= 3 && Pattern.compile(regexDigit).matcher(list.get(1)).find()) {
                    for (int i = 2; i < list.size(); ++i) {
                        if (list.get(i).contains(":")
                                && list.get(i).split(":").length == 2
                                && Pattern.compile(regexDigit).matcher(list.get(i).split(":")[1].trim()).find()) {
                            validFlag = true;
                        } else {
                            validFlag = false;
                            break;
                        }
                    }
                }
                break;

            case inputIdCheck:
                validFlag = list.stream().allMatch(item -> Pattern.compile(regexDigit).matcher(item).find());
                break;
        }
        return validFlag;

    }
}
