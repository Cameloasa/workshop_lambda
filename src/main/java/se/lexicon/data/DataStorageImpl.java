package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl() {
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
       //implement the method
        //Predicate<Person> filter
        //Iterate and add a person
        for(Person person : personList){
            if (filter.test(person)){
                return person;
            }
        }
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {
        // implement the method
        //Predicate<Person> filter, Function<Person, String
        //initialize a person
        Person person = findOne(filter);
        return (person != null) ? personToString.apply(person) : null;

    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString) {
        //implement the method
        //Predicate<Person> filter, Function<Person, String> personToString
        //Initialize a array of strings
        List<String> result = new ArrayList<>();
        //Iterate throw person list
        for (Person person : personList){
            //use filter to add
            if(filter.test(person)){
                //use apply to make a string
                result.add(personToString.apply(person));
            }
        }
        return result;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer) {
        // implement the method
        //Predicate<Person> filter, Consumer<Person> consumer
        for (Person person : personList){
            if(filter.test(person));{
                consumer.accept(person);
            }

        }

    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {
        // implement the method
        //Comparator<Person> comparator
        //Initialize a new sorted list
        List<Person> sortedList = new ArrayList<>(personList);
        // sort that list using comparator
        sortedList.sort(comparator);
        //return sorted list
        return sortedList;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {
        // implement the method
        //Predicate<Person> filter, Comparator<Person> comparator
        List<Person> filteredList = findMany(filter);
        filteredList.sort(comparator);
        return filteredList;

    }
}
