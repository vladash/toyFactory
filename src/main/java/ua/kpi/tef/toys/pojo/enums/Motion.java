package ua.kpi.tef.toys.pojo.enums;

public enum Motion {
    PERCUSSION(MotionAmount.ABSENT),
    WIND(MotionAmount.SMALL),
    STRINGED_SMALL(MotionAmount.MEDIUM, MotionLength.SHORT),
    STRINGED_MEDIUM(MotionAmount.MEDIUM, MotionLength.MEDIUM),
    STRINGED_LONG(MotionAmount.BIG, MotionLength.LONG),
    REED(MotionAmount.MEDIUM);

    private MotionAmount motionAmount;
    private MotionLength motionLength;

    Motion(MotionAmount motionAmount) {
        this.motionAmount = motionAmount;
    }

    Motion(MotionAmount motionAmount, MotionLength motionLength) {
        this.motionAmount = motionAmount;
        this.motionLength = motionLength;
    }

    public MotionAmount getMotionAmount() {
        return motionAmount;
    }

    public MotionLength getResonatorsLength() {
        return motionLength;
    }

}
