import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class JavaStarter {

  public static void main(String[] args) {
    System.out.println("Hello world");

    int variable1 = 1;
    variable1 = 2;

    final int value1 = 1;
    //value1 = 2; //compilation error reassignment to final


    int primitive = 0;


    List<Integer> ints = new ArrayList<>();
    for (int i = 0; i < 10; i+=3) {
      ints.add(i);
    }
    int sum = ints.stream().mapToInt((a) -> a).sum();
    System.out.println(Integer.valueOf(1) == Integer.valueOf(1));
  }

}
