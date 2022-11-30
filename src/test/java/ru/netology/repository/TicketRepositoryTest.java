package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class TicketRepositoryTest {
    TicketRepository repo = new TicketRepository();

    Ticket ticket1 = new Ticket(1, 510, "SVO", "KZN", 95);
    Ticket ticket2 = new Ticket(3, 505, "VKO", "KZN", 85);
    Ticket ticket3 = new Ticket(8, 470, "SVO", "KZN", 60);
    Ticket ticket4 = new Ticket(9, 450, "VKO", "KZN", 100);
    Ticket ticket5 = new Ticket(14, 500, "SVO", "KZN", 95);

    @BeforeEach
    public void setup() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
    }

    @Test
    public void shouldRemoveById() {
        repo.removeById(8);

        Ticket[] expected = {ticket1, ticket2, ticket4, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionRemoveById() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(7);
        });
    }

    @Test
    public void shouldSaveTicket() {

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionSaveTicket() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(ticket4);
        });
    }
}
