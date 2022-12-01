package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTimeComparator;
import ru.netology.repository.TicketRepository;

import java.security.PublicKey;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);
    TicketByTimeComparator timeComparator = new TicketByTimeComparator();

    Ticket ticket1 = new Ticket(1, 510, "SVO", "KZN", 95);
    Ticket ticket2 = new Ticket(3, 505, "VKO", "KZN", 85);
    Ticket ticket3 = new Ticket(8, 510, "SVO", "KZN", 60);
    Ticket ticket4 = new Ticket(9, 450, "VKO", "KZN", 100);
    Ticket ticket5 = new Ticket(14, 500, "SVO", "KZN", 100);
    Ticket ticket6 = new Ticket(15, 470, "SVO", "KZN", 95);


    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }

    @Test
    public void shouldSearchTicketSVO() {

        Ticket[] expected = {ticket3, ticket1, ticket6, ticket5};
        Ticket[] actual = manager.searchBy("SVO", "KZN", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketVKO() {

        Ticket[] expected = {ticket2, ticket4};
        Ticket[] actual = manager.searchBy("VKO", "KZN", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("KZN", "VKO", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
