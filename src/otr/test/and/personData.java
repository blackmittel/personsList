package otr.test.and;

import java.io.UnsupportedEncodingException;

/***
 * класс единичной записи данных
 */
public class personData implements Comparable<personData> {
    private String nameFirst;

    private String nameLast;
    private Integer age;
    private String passport;

    public personData() {
    }

    public personData(String nameLast, String nameFirst, Integer age, String passport) {
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.age = age;
        this.passport = passport;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age != null && age < 0)
            throw new IllegalArgumentException("Invalid age value");
        if (age == 0) {
            this.age = null;
        } else {
            this.age = age;
        }
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        personData that = (personData) object;
        return nameFirst.equals(that.nameFirst) && nameLast.equals(that.nameLast) && java.util.Objects.equals(age, that.age) && passport.equals(that.passport);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), nameFirst, nameLast, age, passport);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return (age == null ? "" : age.toString()) + " " + nameLast + " " + nameFirst + " " + passport;
    }

    @Override
    public int compareTo(personData other) {
        if (this.age == null && other.age != null)
            return -1;
        if (this.age != null && other.age == null)
            return 1;
        if ((this.age == null && other.age == null) || (this.age == other.age))
            return compareTail(other);
        return this.age.compareTo(other.age);
    }

    /***
     * Метод сравнения записей по текстовым полям.
     * !!!!!!!!! По каким-то причинам при исполнении в среде cmd Windows результаты строковой сортировки
     * обратны получаемым при исполнении в Java IDE. Поэтому в метод добавлены отрицания для чисто строковых полей.
     * @param other
     * @return
     */
    private int compareTail(personData other) {
        int tmp;
        tmp = -this.nameLast.compareTo(other.nameLast);
        if (tmp != 0) return tmp;
        tmp = -this.nameFirst.compareTo(other.nameFirst);
        if (tmp != 0) return tmp;
        return this.passport.compareTo(other.passport);
    }

}
