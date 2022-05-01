
import java.util.*;
import java.util.stream.Collectors;

public class NumberBaseball {
    private int s = 0;
    private int b = 0;
    private int o = 0;
    private int round = 0;

    public static void main(String[] args) {
        NumberBaseball game = new NumberBaseball();
        //게임 시작 물어보기 (y로 응답시에만 시작)
        Boolean isStart = game.askToStart();
        if (!isStart) {
            System.out.println("게임이 종료되었습니다.");
            System.exit(0);
        }

        // 추후 for문으로 9라운드 게임을 돌리는 함수 안에 들어갈 예정. log용 print
        System.out.println("1회가 시작되었습니다");

        // user 추측 받기 (중복되지 않는 3자리 숫자)
        List<Integer> answer =  game.creatAnswer();
        List<Integer> userGuess = game.userGuess();

        //게임결과 출력(1회당)
        game.judge(answer, userGuess);
    }

    private boolean askToStart() {
        Boolean repeatAsking = true;
        Boolean isStart = false;
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
        numlist = numlist.subList(0,3);

        //출력(String)
        String answer = numlist.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(", "));
        System.out.println(answer);

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
                //System.out.println(userInput.size());

                //유효성 검증
                if (userInput.size() != 3) {
                    System.out.println("3자리 숫자가 아닙니다.");
                    continue;
                }

                else if (userInput.stream().distinct().count() != 3) {
                    System.out.println("중복된 숫자가 있습니다.");
                    continue;
                }

                System.out.println("input 받음");
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

    private List<Integer> judge(List<Integer> userguess, List<Integer> answer){

        for (int i = 0; i < answer.size(); i++) {
            if(answer.contains(userguess.get(i))){
                if(answer.get(i).equals(userguess.get(i))){
                        s++;
                }else {
                        b++;
                }
            }else{
                    o++;
            }
        }


        //결과값 출력
        System.out.printf("%dS %dB %dO\n",s,b,o);
        return Arrays.asList(s,b,o);
    }
}
