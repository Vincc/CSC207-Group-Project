package interface_adapter;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AutoSuggestorTest {

    private AutoSuggestor autoSuggestor;
    private JTextField textField;
    private JFrame frame;

    @Before
    public void setUp() {
        frame = new JFrame();
        textField = new JTextField();
        ArrayList<String> dictionary = new ArrayList<>(Arrays.asList("hello", "world", "test", "java"));
        autoSuggestor = new AutoSuggestor(textField, frame, dictionary, Color.WHITE, Color.BLACK, Color.BLUE, 0.8f);
    }

    @Test
    public void testInitialization() {
        assertNotNull(autoSuggestor);
        assertEquals(textField, autoSuggestor.getTextField());
        assertEquals(frame, autoSuggestor.getContainer());
    }

    @Test
    public void testWordTyped() {
        textField.setText("he");
        assertTrue(autoSuggestor.wordTyped(textField.getText()));
    }

    @Test
    public void testAddToDictionaryAndCheck() {
        String newWord = "newWord";
        autoSuggestor.addToDictionary(newWord);
        textField.setText(newWord);
        assertTrue(autoSuggestor.wordTyped(textField.getText()));
    }

}