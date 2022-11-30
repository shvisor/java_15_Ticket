package ru.netology.repository;

import ru.netology.domain.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class TicketRepository {
    Ticket[] tickets = new Ticket[0]; // в массиве нет билетов

    public void save(Ticket ticket) { // метод для добавления билетов
        if (findById(ticket.getId()) != null) { // проверяем отсутствие билетов с подобным id
            throw new AlreadyExistsException( // при нахождении дублей выводим сообщение об ошибке
                    "Ticket with id: " + ticket.getId() + " already exist"
            );
        }
        Ticket[] tmp = new Ticket[tickets.length + 1]; // создаем верменный массив на 1 ячейку больше
        for (int i = 0; i < tickets.length; i++) { // перебираем массив, если номер ячейки менее длины массива
            tmp[i] = tickets[i]; // присваиваем ячейкам временного массива значение ячеек массива tickets
        }
        tmp[tmp.length - 1] = ticket; // кладем билет в дополнительную ячейку
        tickets = tmp; // присваиваем массиву tickets значение временного массива
    }

    public Ticket[] findAll() { // метод для нахождения всех имеющихся билетов
        return tickets;
    }

    public void removeById(int id) { // метод для удаления билетов
        if (findById(id) == null) { // проверяем наличие билетов с подобным id
            throw new NotFoundException( // при отсуствии билетов с искомым id выводим сообщение об ошибке
                    "Ticket with id: " + id + " not found"
            );
        }
        Ticket[] tmp = new Ticket[tickets.length - 1]; // создаем верменный массив на 1 ячейку меньше
        int index = 0; // задаем номер ячейки массива
        for (Ticket ticket : tickets) { // перебираем массив tickets
            if (ticket.getId() != id) { // проверяем, что id билета не равно искомому (удаляемому) id
                tmp[index] = ticket; // кладем билеты в ячейку временного массива
                index++; // добавляем номер ячейки массива
            }
        }
        tickets = tmp; // // присваиваем массиву tickets значение временного массива, без ячейки с найденным id
    }

    public Ticket findById(int id) { // метод для нахождения билета по id
        for (Ticket ticket : tickets) { // перебираем массив tickets
            if (ticket.getId() == id) { // проверяем, что id билета равно искомому id
                return ticket; // возвращаем найденный билет
            }
        }
        return null; // возвращаем null, если билет не найден
    }
}
