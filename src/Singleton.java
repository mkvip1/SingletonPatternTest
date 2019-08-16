import java.util.Objects;

public class Singleton {
    // Поле обязательно должно быть объявлено volatile, чтобы двойная проверка
    // блокировки сработала как надо.
    private static volatile Singleton instance;
    private String value;

    private Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        // Поле одиночки объявлено как volatile, что заставляет программу
        // обновлять её значение из памяти каждый раз при доступе к переменной,
        // тогда как значение обычной переменной может быть записано в регистр
        // процессора для более быстрого чтения. Используя дополнительную
        // локальную перменную, мы можем ускорить работу с переменной, обновляя
        // значение поля только тогда, когда действительно нужно.
        Singleton res = instance;
        if (Objects.isNull(res)) {
            synchronized (Singleton.class) {
                res = instance;
                if (Objects.isNull(res)) {
                    instance = res = new Singleton(value);
                }
            }
        }
        return instance;
    }

    public String getValue() {
        return value;
    }
}