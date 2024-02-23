package kr.bgmsound.mockito.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTests {

    @Test
    void stubMethodCall() {
        Map<String, String> map = mock();
        when(map.get("key"))
                .thenReturn("value")
                .thenReturn("value2");

        when(map.containsKey("key")).thenReturn(false);

        assertEquals("value", map.get("key"));
        assertEquals("value2", map.get("key"));
        assertFalse(map.containsKey("key"));
    }

    @Test
    void testArgumentMatchers() {
        List<String> list = mock();
        /*
        * anyInt(), anyBoolean(), anyFloat(), anyString() ...
        * anySet(), anyMap(), anyIterable() ...
        * isNull(), isNotNull() ...
        * contains(), startWith(), artThat() .
        */
        when(list.get(anyInt())).thenReturn("Hello Mockito");

        assertEquals("Hello Mockito", list.get(0));
        assertEquals("Hello Mockito", list.get(1));
    }

    @Test
    void testException() {
        Map<String, String> map1 = mock();
        Map<String, String> map2 = mock();

        when(map1.get("key")).thenThrow(new RuntimeException("Key not found"));
        doThrow(new RuntimeException("Key not found")).when(map2).get("key");

        assertThrows(RuntimeException.class, () -> map1.get("key"));
        assertThrows(RuntimeException.class, () -> map2.get("key"));
    }

    @Test
    void verifyInteraction() {
        List<String> mockedList = mock();
        /*
        * atMostOnce()
        * atLeastOnce()
        * atLeast(N)
        * atMost(N)
         */
        mockedList.add("Hello Mockito");
        mockedList.add("Hello Mockito");

        verify(mockedList, times(2)).add("Hello Mockito");
        verify(mockedList, never()).clear();
    }

}
