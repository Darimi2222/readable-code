package cleancode.minesweeper.tobe.cell;

public class NumberCell extends Cell {
    private int nearByLandMineCount;

    public NumberCell(int nearByLandMineCount) {
        this.nearByLandMineCount = nearByLandMineCount;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public String getSign() {
        if(isOpened){
            return String.valueOf(nearByLandMineCount);
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
