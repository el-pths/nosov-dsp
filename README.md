# Использование Нейронных Сетей для Цифровой Обработки Сигнала

_This project about digital signal processing and neural networks is a school research and currently it does not suit for reference purpose and so English translation is not provided yet._

### Цель работы

Изучить возможность создания программных преобразователей звукового сигнала основанных на нейронных сетях.

### Задачи работы

1. Реализовать приложение - звуковой процессор - на Java.
2. Создать программные компоненты для фильтрации низких и высоких частот, эффектов fuzz/distortion "классическими" способами.
3. По необходимости и возможности создать аппаратные дополнения - гитарный предусилитель, аналоговый fuzz-box.
4. Исследовать возможность реализации компонент фильтров и fuzz/distortion с использованием нейронных сетей.
5. Возможно (но сомнительно) - портировать решение на C и попробовать реализовать какой-либо эффект или фильтр на микроконтроллере.

### Архитектура приложения

Приложение должно быть разбито на классы, каждый из которых отвечает за часть функционала в соответствии с "цепочкой" обработки сигнала. Например:

- класс приема звукового сигнала с линейного входа компьютера / входа микрофона;
- класс воспроизведения звукового сигнала на линейный выход;
- класс генерации звукового сигнала заданной частоты (для тестирования);
- класс визуализации звукового сигнала для удобного анализа работы остальных компонент;
- классы фильтра НЧ, фильтра ВЧ, fuzz-эффекта;

Кроме того следует предусмотреть удобную возможность составления "цепочки" обработки сигнала, позволяющую легко подставлять нужные классы в нее и по возможности не требующую для этого перекомпиляции программы. Т.е. например чтобы в "базовой" конфигурации использовалась цепочка:

    приемник-с-микрофона    ==>    фильтр ВЧ    ==>    fuzz-эффект    ==>    выход на наушники

но по желанию можно было бы отключить фильтр, заменить приемник на генератор частоты, а выход на визуализатор.

### Ссылки

- текущие задачи https://github.com/el-pths/nosov-dsp/issues
