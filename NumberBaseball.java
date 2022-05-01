import java.util.*;
import java.util.stream.Collectors;

public class NumberBaseball {
    private int s = 0;
    private int b = 0;
    private int o = 0;
    private int round = 0;

    public static void main(String[] args) {
        //System.out.println("number baseball project");
        NumberBaseball game = new NumberBaseball();
        game.creatAnswer();
    }

    private void creatAnswer() {
        //중복되지 않는 3자리 숫자 랜덤 answer 생성 함수

        List<Integer> numlist = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numlist.add(i);
        }
        Collections.shuffle(numlist);
        numlist = numlist.subList(0,3);

        //출력
        String answer = numlist.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println(answer);
    }
}
