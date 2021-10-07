package otr.test.and;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * конкретизация интерфейса personsProvider - чтение записей из файла.
 */
public class personsFileReader implements personsProvider {
    /***
     * Метод получения списка записей из файла. Строки файла обрабатываются группами по 4 (размер записи).
     * Если первая строка в группе является пробельной (пустой), ситуация рассматривается, как конец данных.
     * Eсли количество обрабатываемых строк не кратно 4, выбрасывается исключение.
     * Если строка возраста не пуста и не парсится в целое число, выбрасывается исключение.
     * @return список записей
     * @throws IllegalArgumentException ошибка парсинга возраста
     * @throws IndexOutOfBoundsException ошибка некорректного формата файла
     */
    @Override
    public List<personData> supplyData() throws IllegalArgumentException, IndexOutOfBoundsException {
        List<personData> retList = new ArrayList<>();
        personData data;
        List<String> lines = this.reader.lines().collect(Collectors.toList());
        String strlast, str1st, strAge, strPass;
        Integer age;
        for (int i = 0; i < lines.size(); i +=4 ) {
            try {
                strlast = lines.get(i);
                if (strlast.isBlank())
                    break;
                str1st = lines.get(i + 1);
                strAge = lines.get(i + 2);
                strPass = lines.get(i + 3);
                if (strAge.isBlank()) {
                    age = null;
                } else {
                    try {
                        age = Integer.valueOf(strAge);
                    } catch (IllegalArgumentException ex) {
                        throw ex;
                    }
                }
            }  catch (IndexOutOfBoundsException ex) {
                throw ex;
            }
            data = new personData(strlast, str1st, age, strPass);
            retList.add(data);
        }
        return retList;
    }

    private BufferedReader reader;

    public personsFileReader(FileInputStream fileStream) {
        this.reader = new BufferedReader(new InputStreamReader(fileStream));
    }
    public  personsFileReader(String filePath) throws FileNotFoundException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    }
}