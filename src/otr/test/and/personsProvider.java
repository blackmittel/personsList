package otr.test.and;

import java.io.IOException;
import java.util.List;

/***
 * интерфейс получения списка записей данных из произвольного источника
 */
public interface personsProvider {
    List<personData> supplyData() throws Exception;
}