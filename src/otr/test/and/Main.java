package otr.test.and;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

/***
 * Прочитать текстовый файл определённой структуры с инфо о пользователях,
 * получить коллекцию пользователей и вывести на экран (столбиком), формат вывода не важен.
 * Пример файла:
 * Иванов
 * Иван
 * 44
 * 11122 123455
 * Петров
 * Петр
 * 11
 * 3453 1122334
 * Климова
 * Ольга
 * 17
 * 1122 345667
 *
 * файл может состояить из любого количества пользователей; возраст опционален, то есть, может отсутствовать
 * пример
 * Иванов
 * Иван
 *
 * 11122 123455
 *
 * итоговый список перед выводом отсортировать по возрасту, по убыванию, пустой-возраст в начале
 *
 * *************************
 * !!!! данные выводятся в кодировке utf-8! для корректного отображения в окне cmd windows
 * !!!! необходимо перед исполнением jar выполнить команду chcp 65001
 *
 */
public class Main {

    public static void main(String[] args) {

        personsProvider supplier = null;
        try {
            supplier = new personsFileReader("usrdata.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("No source data! Things are bad!");
            return;
        }
        List<personData> data = null;
        try {
            data = supplier.supplyData();
        } catch (Exception e) {
            System.err.println("Invalid file format! Things are bad!");
            return;
        }
        if (data == null) {
            System.err.println("Error. Something goes wrong.");
            return;
        }
        data.stream().sorted().forEach(System.out::println);
    }
}
