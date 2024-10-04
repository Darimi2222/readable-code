package cleancode.minesweeper.tobe.cell;

public class EmptyCell extends Cell {
    private static final String EMPTY_SIGN = "■";

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public String getSign() {
        if(isOpened){
            return EMPTY_SIGN;
        }
        if (isFlagged){
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }

    @Override
    public void flag() {
        super.flag();
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    public boolean isChecked() {
        return super.isChecked();
    }

    @Override
    public boolean isOpened() {
        return super.isOpened();
    }
}
