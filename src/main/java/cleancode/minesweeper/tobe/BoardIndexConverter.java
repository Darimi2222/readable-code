package cleancode.minesweeper.tobe;

public class BoardIndexConverter {
    private static final char BASE_CHAR_FOR_COL = 'a';

    public int getSelectedRowIndex(String cellInput, int rowSize) {
        String cellInputRow = cellInput.substring(1);
        return convertRowFrom(cellInputRow, rowSize); //코드가 단 한줄이어도 추상화한다! 코드 줄 수는 중요하지 않다.
    }

    public int getSelectedColIndex(String cellInput, int colSize) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol, colSize);//전치사로 메서드명과 패러키터를 연결지어 보기!
    }

    private int convertRowFrom(String cellInputRow, int rowSize) {
        int rowIndex = Integer.parseInt(cellInputRow) - 1;
        if (rowIndex < 0 || rowIndex >= rowSize) {
            throw new GameException("잘못된 좌표입니다.");
        }

        return rowIndex;
    }

    private int convertColFrom(char cellInputCol, int colSize) {
        int colIndex = cellInputCol - BASE_CHAR_FOR_COL; //각 소문자에 대해 0~25 대입
        if (colIndex < 0 || colIndex >= colSize) {
            throw new GameException("잘못된 좌표입니다.");
        }

        return colIndex;
    }
}
