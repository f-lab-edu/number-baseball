
import java.util.*;
import java.util.stream.Collectors;

public class NumberBaseball {


    public static void main(String[] args) {
        while (true) {
            NumberBaseball game = new NumberBaseball();
            //게임 시작 물어보기 (y로 응답시에만 시작)
            boolean isStart = game.askToStart();
            if (!isStart) {
                System.out.println("게임이 종료되었습니다.");
                break;
            }

            game.playGame();


        }
    }

    private void playGame() {
        boolean isWin = false;
        // 정답 생성
        List<Integer> answer = this.creatAnswer();

        for (int round = 1; round < 10; round++) {
            System.out.printf("%d회가 시작되었습니다\n", round);

            // user 추측 받기 (중복되지 않는 3자리 숫자)
            List<Integer> userGuess = this.userGuess();

            // 심판
            List<Integer> result = this.judge(userGuess, answer);

            //승리시 라운드 종료(s의값이 3이면)
            if (result.get(0) == 3) {
                isWin = true;
                break;
            }
        }

        if (isWin) {
            System.out.println("승리했습니다. 축하드립니다 >_</");
        } else {
            System.out.println("패배했습니다. 아쉽습니다 ^^*");
        }

    }

    private boolean askToStart() {
        boolean repeatAsking = true;
        boolean isStart = false;
        while (repeatAsking) {
            System.out.println("새 게임을 시작하시겠습니까? y/n");
            Scanner scan = new Scanner(System.in);
            String input = scan.next().toLowerCase();
            switch (input) {
                case "y":
                    isStart = true;
                    repeatAsking = false;
                    break;
                case "n":
                    repeatAsking = false;
                    break;
                default:
                    System.out.println("y, n중에 입력해주세요");
            }
        }
        return isStart;
    }

    private List<Integer> creatAnswer() {
        //중복되지 않는 3자리 숫자 랜덤 answer 생성 함수
        List<Integer> numlist = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numlist.add(i);
        }
        Collections.shuffle(numlist);
        numlist = numlist.subList(0, 3);

//        //출력(String)
//        String answer = numlist.stream()
//                .map(Object::toString)
//                .collect(Collectors.joining(", "));
//        System.out.println(answer);

        //List 형식 반환
        return numlist;
    }

    private List<Integer> userGuess() {
        List<Integer> userInput = List.of();
        while (true) {
            System.out.println("중복되지 않는 숫자 3개를 입력해 주세요");
            Scanner scan = new Scanner(System.in);
            try {
                userInput = Arrays.stream(scan.nextLine()
                                .trim()
                                .split(""))
                        .filter(x -> !x.equals(" "))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                //유효성 검증
                if (userInput.size() != 3) {
                    System.out.println("3자리 숫자가 아닙니다.");
                    continue;
                } else if (userInput.stream().distinct().count() != 3) {
                    System.out.println("중복된 숫자가 있습니다.");
                    continue;
                }
                // 제대로 값 받으면 input 받기 종료
                break;
            } catch (NumberFormatException e) {
                System.out.println(e);
                System.out.println("숫자 형식이 아닙니다.");
            } catch (Exception e) {
                System.out.println(e);
                break;
            }
        }


        return userInput;
    }

    private List<Integer> judge(List<Integer> userguess, List<Integer> answer) {
        //sbo초기화
        int s = 0;
        int b = 0;
        int o = 0;

        for (int i = 0; i < answer.size(); i++) {
            if (answer.contains(userguess.get(i))) {
                if (answer.get(i).equals(userguess.get(i))) {
                    s++;
                } else {
                    b++;
                }
            } else {
                o++;
            }
        }
        //결과값 출력
        System.out.printf("%dS %dB %dO\n", s, b, o);

        return Arrays.asList(s, b, o);
    }
}
