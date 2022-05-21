package de.cofinpro.tictactoe.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardModelTest {

    BoardModel boardModel;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        boardModel = new BoardModel();
    }

    @Test
    void whenConstructed_AllCellsHaveSpace() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                assertEquals(" " , boardModel.getCellText(i, j));
            }
        }
    }

    @Test
    void whenSetCellTextButtonNamedA2_Cell21ContainsValue() {
        JButton button = new JButton();
        button.setName("ButtonA2");
        boardModel.setCellText(button, 'X');
        assertEquals("X", boardModel.getCellText(2, 1));
        assertEquals("X", boardModel.getCellText(button));
    }

    @Test
    void whenSetCellTextButtonNamedA2_OtherCellsEmpty() {
        JButton button = new JButton();
        button.setName("ButtonA2");
        boardModel.setCellText(button, 'X');
        assertEquals(" ", boardModel.getCellText(2, 2));
        assertEquals(" ", boardModel.getCellText(1, 2));
        assertEquals(" ", boardModel.getCellText(1, 1));
        assertEquals(" ", boardModel.getCellText(3, 1));
    }

    @Test
    void wheGetCellName32_ButtonB3Returned() {
        assertEquals("ButtonB3", boardModel.getCellName(3, 2));
    }

    @ValueSource(strings = {
            "[88, 79, 88, 88, 32, 79, 88, 32, 79]",
            "[88, 32, 79, 79, 88, 32, 79, 32, 88]",
            "[32, 79, 79, 88, 88, 88, 79, 32, 88]"
    })
    @ParameterizedTest
    void whenLineOfThreeX_DetermineStatusReturnsX_Wins(String array) throws JsonProcessingException {
        int[] cells = mapper.readValue(array, int[].class);
        for (int i = 0; i < cells.length; i++) {
            JButton button = new JButton();
            button.setName(boardModel.getCellName(i / 3 + 1, i % 3 + 1));
            boardModel.setCellText(button, (char) cells[i]);
        }
        assertEquals(Status.X_WINS, boardModel.determineStatus(7));
    }


    @ValueSource(strings = {
            "[88, 79, 32, 88, 79, 32, 32, 79, 88]",
            "[88, 32, 79, 88, 79, 32, 79, 32, 88]",
            "[32, 88, 88, 88, 32, 32, 79, 79, 79]"
    })
    @ParameterizedTest
    void whenLineOfThreeO_DetermineStatusReturnsO_Wins(String array) throws JsonProcessingException {
        int[] cells = mapper.readValue(array, int[].class);
        for (int i = 0; i < cells.length; i++) {
            JButton button = new JButton();
            button.setName(boardModel.getCellName(i / 3 + 1, i % 3 + 1));
            boardModel.setCellText(button, (char) cells[i]);
        }
        assertEquals(Status.O_WINS, boardModel.determineStatus(6));
    }

    @ValueSource(strings = {
            "[88, 79, 32, 88, 79, 32, 32, 32, 88]",
            "[88, 32, 79, 88, 79, 32, 32, 32, 88]",
            "[32, 88, 88, 88, 32, 32, 79, 79, 32]"
    })
    @ParameterizedTest
    void whenNoLineComplete_DetermineStatusReturnsProgress(String array) throws JsonProcessingException {
        int[] cells = mapper.readValue(array, int[].class);
        for (int i = 0; i < cells.length; i++) {
            JButton button = new JButton();
            button.setName(boardModel.getCellName(i / 3 + 1, i % 3 + 1));
            boardModel.setCellText(button, (char) cells[i]);
        }
        assertEquals(Status.PROGRESS, boardModel.determineStatus(5));
    }

    @ValueSource(strings = {
            "[88, 79, 88, 79, 79, 88, 88, 88, 79]",
            "[88, 79, 88, 79, 79, 88,  88, 88, 79]"
    })
    @ParameterizedTest
    void whenDraw_DetermineStatusRecognizes(String array) throws JsonProcessingException {
        int[] cells = mapper.readValue(array, int[].class);
        for (int i = 0; i < cells.length; i++) {
            JButton button = new JButton();
            button.setName(boardModel.getCellName(i / 3 + 1, i % 3 + 1));
            boardModel.setCellText(button, (char) cells[i]);
        }
        assertEquals(Status.DRAW, boardModel.determineStatus(9));
    }
}