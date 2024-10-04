package cleancode.minesweeper.tobe.cell;

public class LandMineCell extends Cell {

    private static final String LAND_MINE_SIGN = "☼";

    @Override
    public boolean isLandMine() {
        return true;
    }

    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public String getSign() {
        if(isOpened){
            return LAND_MINE_SIGN;
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