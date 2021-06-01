package ua.kpi.tef.toys.pojo.enums;

public enum MotionAmount {
    ABSENT(0),
    SMALL(1),
    MEDIUM(5),
    BIG(10);

    private int motionAmount;
    public static final int MAX_AMOUNT = 50;

    MotionAmount(int motionAmount) {
        this.motionAmount = motionAmount;
    }

    public boolean motionAbsence(){
        return getMotionAmount() == 0;
    }


    public int getMotionAmount() {
        return motionAmount;
    }

    public void setMotionAmount(int motionAmount) {
        this.motionAmount = motionAmount;
    }
}
