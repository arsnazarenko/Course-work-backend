package generator;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

    }

    class Human {
        private String name;
        private String job;
        private Date dateOfBirth;

        @Override
        public String toString() {
            return "values (" +
                    "'" + name + "'," +
                    ", job='" + job + '\'' +
                    ", dateOfBirth=" + dateOfBirth +
                    '}';
        }
    }
}
