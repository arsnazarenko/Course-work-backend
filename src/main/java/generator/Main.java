package generator;

import com.itmo.squid.domain.Human;

import java.util.Date;
import java.util.List;

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
