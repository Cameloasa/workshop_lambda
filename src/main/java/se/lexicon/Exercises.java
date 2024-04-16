package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().equals("Erik");
        storage.findMany(filter).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getGender() == Gender.FEMALE;
        storage.findMany(filter).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getBirthDate().isAfter(LocalDate.of(2000, 1, 1)) ||
                person.getBirthDate().isEqual(LocalDate.of(2000, 1, 1));
        storage.findMany(filter).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 123;
        System.out.println(storage.findOne(filter));
        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 456;
        Function<Person, String> personToString = person ->
                "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate();
        System.out.println(storage.findOneAndMapToString(filter, personToString));
        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getGender() == Gender.MALE && person.getFirstName().startsWith("E");
        Function<Person, String> personToString = person -> person.getFirstName() + " " + person.getLastName();
        storage.findManyAndMapEachToString(filter, personToString).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> Period.between(person.getBirthDate(), LocalDate.now()).getYears() < 10;
        Function<Person, String> personToString = person ->
                person.getFirstName() + " " + person.getLastName() + " " +
                        Period.between(person.getBirthDate(), LocalDate.now()).getYears() + " years";
        storage.findManyAndMapEachToString(filter, personToString).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().equals("Ulf");
        storage.findAndDo(filter, System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getLastName().contains(person.getFirstName());
        storage.findAndDo(filter, System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> {
            String firstName = person.getFirstName().toLowerCase();
            return firstName.equals(new StringBuilder(firstName).reverse().toString());
        };
        Consumer<Person> printName = person -> System.out.println(person.getFirstName() + " " + person.getLastName());
        storage.findAndDo(filter, printName);
        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().startsWith("A");
        Comparator<Person> comparator = Comparator.comparing(Person::getBirthDate);
        storage.findAndSort(filter, comparator).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getBirthDate().isBefore(LocalDate.of(1950, 1, 1));
        Comparator<Person> comparator = Comparator.comparing(Person::getBirthDate).reversed();
        storage.findAndSort(filter, comparator).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        Comparator<Person> comparator = Comparator.comparing(Person::getLastName)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getBirthDate);
        storage.findAndSort(comparator).forEach(System.out::println);
        System.out.println("----------------------");
    }
}
