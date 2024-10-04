package cleancode.minesweeper.tobe;

//섹션1. 이름 짓기 진행

import cleancode.minesweeper.tobe.gameLevel.Advanced;
import cleancode.minesweeper.tobe.gameLevel.Beginner;
import cleancode.minesweeper.tobe.gameLevel.GameLevel;
import cleancode.minesweeper.tobe.gameLevel.Middle;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutPutHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class GameApp {

    public static void main(String[] args) {
        GameLevel gameLevel = new Middle();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutPutHandler();

        Minesweeper minesweeper = new Minesweeper(gameLevel, inputHandler, outputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }
    /**
     * DIP
     *
     * 스프링에서 IOC(Inversion of Control)와 DI(Dependency Injection)...
     *
     * DI
     * 의존성 주입
     * 필요한 의존성을 외부에서 주입해줌
     * 3 이란 숫자를 항상 떠올려야함
     * 제 3자(스프링 컨텍스트)가 두 객체 간 주입을 해줌(런타임 시점에)
     *
     * IOC
     * 제어의 역전
     * 프로그램의 흐름을 프레임워크가 대신해줌
     * 개발자 의도대로 완전히 흘러가는 것을 제어의 순방향
     * 내 코드는 톱니바퀴 일부일 뿐이고 프레임워크가 전체 흐름을 만드는 것이 제어의 역전
     *
     */
}
