package com.sviryd.mikhail;

import java.util.Arrays;

/**
 * 1.1 Задача 1
 */
public class JavaSE2 {
    private static class Parent {
        String fio;

        private Parent() {
            this.fio = "анна мария оглы";
        }

        private void print() {
            if (this instanceof Child) {
                formatChildFio();
            } else {
                formatParentFio();
            }
            System.out.println(fio);
        }

        private void formatChildFio() {
            char[] delimiters = new char[]{' ', '-', '\''};
            Arrays.sort(delimiters);
            fio = fio.toLowerCase();
            char[] chars = fio.toCharArray();
            boolean changeChar = true;
            for (int i = 0; i < chars.length; i++) {
                char currentChar = chars[i];
                if (Arrays.binarySearch(delimiters, currentChar) > -1) {
                    changeChar = true;
                } else if (changeChar) {
                    chars[i] = Character.toUpperCase(currentChar);
                    changeChar = false;
                }
            }
            fio = String.copyValueOf(chars);
        }

        private void formatParentFio() {
            fio += "!";
        }
    }

    private static class Child extends Parent {
        private Child() {
            this.fio = "АН'НА-МАРИЯ оглы";
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.print();
        Parent child = new Child();
        child.print();
    }
}
