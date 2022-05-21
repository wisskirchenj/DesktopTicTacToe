package de.cofinpro.tictactoe.model;

import de.cofinpro.tictactoe.controller.StatusModelListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatusModelTest {

    @Mock
    StatusModelListener listener1;

    @Mock
    StatusModelListener listener2;

    StatusModel statusModel;

    @BeforeEach
    void setUp() {
        statusModel = new StatusModel();
        statusModel.registerListener(listener1);
        statusModel.registerListener(listener2);
    }

    @Test
    void whenStatusSet_notifyListenersNotifies() {
        statusModel.setStatus(Status.DRAW);
        verify(listener1, times(1)).update(statusModel);
        verify(listener2, times(1)).update(statusModel);
    }

    @Test
    void whenPlayerSet_notifyListenersNotifies() {
        statusModel.setPlayer(1, Player.ROBOT);
        verify(listener1, times(1)).update(statusModel);
        verify(listener2, times(1)).update(statusModel);
    }

    @Test
    void whenConstructed_PlayersHumanMove0StateNotStarted() {
        assertArrayEquals(new Player[] {Player.HUMAN, Player.HUMAN}, statusModel.getPlayer());
        assertEquals(0, statusModel.getMove());
        assertEquals(Status.NOT_STARTED, statusModel.getStatus());
    }

    @Test
    void whenMoveIncremented_notifyListenersNotifies() {
        statusModel.incrementAndGetMove();
        verify(listener1, times(1)).update(statusModel);
        verify(listener2, times(1)).update(statusModel);
    }

    @Test
    void whenTogglePlayer_notifyListenersNotifies() {
        statusModel.togglePlayer(0);
        verify(listener1, times(1)).update(statusModel);
        verify(listener2, times(1)).update(statusModel);
    }

    @Test
    void whenModelReset_notifyListenersNotifies() {
        statusModel.reset();
        verify(listener1, times(1)).update(statusModel);
        verify(listener2, times(1)).update(statusModel);
    }

    @Test
    void whenPlayerGet_notifyListenersNotCalled() {
        statusModel.getPlayer(1);
        verify(listener1, times(0)).update(statusModel);
        verify(listener2, times(0)).update(statusModel);
    }

    @Test
    void whenReset_Moves0AndStatusNotStarted() {
        statusModel.setStatus(Status.O_WINS);
        statusModel.incrementAndGetMove();
        statusModel.reset();
        assertEquals(0, statusModel.getMove());
        assertEquals(Status.NOT_STARTED, statusModel.getStatus());
    }


    @Test
    void whenResetPlayersUnchanged() {
        statusModel.setPlayer(0, Player.ROBOT);
        statusModel.reset();
        assertArrayEquals(new Player[] {Player.ROBOT, Player.HUMAN}, statusModel.getPlayer());
    }

    @Test
    void whenPlayerHuman_togglePlayerSetsRobot() {
        statusModel.togglePlayer(0);
        assertEquals(Player.ROBOT, statusModel.getPlayer(0));
    }

    @Test
    void whenPlayerRobot_togglePlayerSetsHuman() {
        statusModel.setPlayer(1, Player.ROBOT);
        statusModel.togglePlayer(1);
        assertEquals(Player.HUMAN, statusModel.getPlayer(1));
    }
}