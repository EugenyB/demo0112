package demo.controller;

import demo.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Controller implements Initializable {
    @FXML TextField filterTextField;
    @FXML TableView personTable;
    @FXML TableColumn nameColumn;
    @FXML TableColumn ageColumn;
    @FXML TableColumn salaryColumn;
    @FXML TableColumn cityColumn;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    private List<Person> allPersons = Arrays.asList(
            new Person("Иван", 20, 1.5, "Николаев"),
            new Person("Александр", 21, 1.5, "Киев"),
            new Person("Мария", 17, 1.0, "Харьков"),
            new Person("Наталья", 15, 2.5, "Николаев"),
            new Person("Татьяна", 28, 3.5, "Киев"),
            new Person("Константин", 62, 5.5, "Николаев"),
            new Person("Федор", 20, 2.1, "Харьков"),
            new Person("Анна", 25, 2.0, "Киев")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("salary"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("city"));
        fillPersonData(allPersons);
    }

    private void fillPersonData(List<Person> personList) {
        personData.clear();
        personData.addAll(personList);
        personTable.setItems(personData);
    }

    public void filter(ActionEvent actionEvent) {
        List<Person> list = personsFromCity("Николаев");
        fillPersonData(list);
    }

    private List<Person> personsFromCity(String city) {
//        List<Person> result = new ArrayList<>();
//        for (int i = 0; i < allPersons.size(); i++) {
//            Person p = allPersons.get(i);
//            if (p.getCity().equals(city)) {
//                result.add(p);
//            }
//        }
//        return result;
        return allPersons
                .stream()
                .filter(p -> p.getCity().equals(city))
                .collect(toList());
    }

    private List<Person> getAdults() {
        return allPersons; // TODO Filter
    }

    public void resetFilter() {
        fillPersonData(allPersons);
    }

    public void filterBySalary(ActionEvent actionEvent) {
        List<Person> list = personsBySalary(2.0, "Николаев");
        fillPersonData(list);
    }

    private List<Person> personsBySalary(double minimum, String city) {
        return allPersons.stream().filter(p -> p.getSalary() >= minimum)
                .filter(p->p.getCity().equals(city))
                .collect(toList());
    }

    public void sort(ActionEvent actionEvent) {
        List<Person> list = sortPersons();
        fillPersonData(list);
    }

    private List<Person> sortPersons() {
        return allPersons.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getName))
                .collect(toList());
    }

    public double minSalary() {
        return allPersons.stream().mapToDouble(Person::getSalary).min().getAsDouble();
    }

    public void minimumSalary(ActionEvent actionEvent) {
        System.out.println(minSalary());
    }
}
