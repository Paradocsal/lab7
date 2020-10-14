package Commands;

import Server.CommandExecutor;
import This.OrganizationData;

import java.net.Socket;

public class Help implements Command{
    public Help(){
        CommandExecutor.addCommand("help",this);
    }
    public String execute(String s, OrganizationData data, Socket userSocket, String user){
        return ("help : вывести справку по доступным командам\n"+
        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" +
        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"+
        "add {element} : добавить новый элемент в коллекцию\n"+
        "update_id {element} : обновить значение элемента коллекции, id которого равен заданному\n"+
        "remove_id id : удалить элемент из коллекции по его id\n"+
        "clear : очистить коллекцию\n"+
        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n" +
                "exit : завершить программу (без сохранения в файл)\n"+
        "remove_first_element : удалить первый элемент из коллекции\n"+
        "head : вывести первый элемент коллекции\n"+
        "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n"+
        "average_of_employees_count : вывести среднее значение поля employeesCount для всех элементов коллекции\n"+
        "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n"+
        "print_field_descending_postal_address : вывести значения поля postalAddress всех элементов в порядке убывания");
    }
}
