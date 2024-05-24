import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    public void ConstructAndAdd() {
        ArrayDeque61B<Integer> lld2 =new ArrayDeque61B<>();
        lld2.addFirst(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addFirst(0);
        lld2.addFirst(-1);
        lld2.addFirst(-2);
        lld2.addLast(4);
        lld2.addLast(5);
        lld2.addLast(6);
        assertThat(lld2.toList()).containsExactly(-2, -1, 0, 1, 2, 3, 4, 5, 6).inOrder();
     }
    @Test
    public void getTest() {
        ArrayDeque61B<Integer> lld2 =new ArrayDeque61B<>();
        lld2.addFirst(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addFirst(0);
        lld2.addFirst(-1);
        lld2.addFirst(-2);
        lld2.addLast(4);
        lld2.addLast(5);
        lld2.addLast(6);
        assertThat(lld2.get(3) == 1).isTrue();
        assertThat(lld2.get(1000) == null).isTrue();
    }

    @Test
    public void sizeEmpty() {
        ArrayDeque61B<Integer> lld2 =new ArrayDeque61B<>();
        assertThat(lld2.isEmpty()).isTrue();
        lld2.addFirst(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addFirst(0);
        lld2.addFirst(-1);
        lld2.addFirst(-2);
        lld2.addLast(4);
        lld2.addLast(5);
        lld2.addLast(6);
        assertThat(lld2.size() == 9).isTrue();
    }

    // This section is for "remove" operation specifically
    @Test
    public void remove_first() {
        ArrayDeque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld2.addFirst(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addFirst(0);
        lld2.addFirst(-1);
        assertThat(lld2.toList()).contains(-1);
        lld2.removeFirst();
        assertThat(lld2.toList()).doesNotContain(-1);
    }

    @Test
    public void remove_last() {
        ArrayDeque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld2.addFirst(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addFirst(0);
        lld2.addFirst(-1);
        assertThat(lld2.toList()).contains(3);
        lld2.removeLast();
        assertThat(lld2.toList()).doesNotContain(3);
    }

    @Test
    public void remove_first_to_empty() {
        ArrayDeque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld2.addLast(2);
        lld2.addLast(3);
        assertThat(lld2.toList()).contains(2);
        assertThat(lld2.toList()).contains(3);
        lld2.removeFirst();
        lld2.removeFirst();
        assertThat(lld2.isEmpty()).isTrue();
    }

    @Test
    public void remove_last_to_empty() {
        ArrayDeque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld2.addFirst(2);
        lld2.addFirst(3);
        assertThat(lld2.toList()).contains(2);
        assertThat(lld2.toList()).contains(3);
        lld2.removeLast();
        lld2.removeLast();
        assertThat(lld2.isEmpty()).isTrue();
    }

    @Test
    public void remove_first_to_one() {
        ArrayDeque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld2.addLast(2);
        lld2.addLast(3);
        assertThat(lld2.toList()).contains(2);
        assertThat(lld2.toList()).contains(3);
        lld2.removeFirst();
        assertThat(lld2.toList()).containsExactly(3);
    }

    @Test
    public void remove_last_to_one() {
        ArrayDeque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld2.addFirst(2);
        lld2.addFirst(3);
        assertThat(lld2.toList()).contains(2);
        assertThat(lld2.toList()).contains(3);
        lld2.removeLast();
        assertThat(lld2.toList()).containsExactly(3);
    }

    @Test
    public void remove_first_trigger_resize() {
        ArrayDeque61B<Integer> lld2 =new ArrayDeque61B<>();
        assertThat(lld2.isEmpty()).isTrue();
        lld2.addFirst(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addFirst(0);
        lld2.addFirst(-1);
        lld2.addFirst(-2);
        lld2.addLast(4);
        lld2.addLast(5);
        lld2.addLast(6);
        lld2.addFirst(9);
        lld2.removeFirst();
        assertThat(lld2.realSize == 25).isTrue();
        assertThat(lld2.toList()).containsExactly(-2, -1, 0, 1, 2, 3, 4, 5, 6);
    }

    @Test
    public void remove_last_trigger_resize() {
        ArrayDeque61B<Integer> lld2 =new ArrayDeque61B<>();
        assertThat(lld2.isEmpty()).isTrue();
        lld2.addFirst(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addFirst(0);
        lld2.addFirst(-1);
        lld2.addFirst(-2);
        lld2.addLast(4);
        lld2.addLast(5);
        lld2.addLast(6);
        lld2.addFirst(9);
        lld2.removeLast();
        assertThat(lld2.realSize == 25).isTrue();
        assertThat(lld2.toList()).containsExactly(9, -2, -1, 0, 1, 2, 3, 4, 5);
    }
}


