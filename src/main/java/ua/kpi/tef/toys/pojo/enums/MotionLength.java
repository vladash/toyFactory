package ua.kpi.tef.toys.pojo.enums;

public enum MotionLength {
    SHORT(10.56),
    MEDIUM(25.89),
    LONG(50.55);

    private double length;

    MotionLength(double length) {
        this.length = length;
    }

    public boolean isShortMotion(){
        if(length<15.0)
            return true;
        return false;
    }

    public boolean isMediumMotion(){
        if(length>=15.0 && length<=30.0)
            return true;
        return false;
    }

    public boolean isLongMotion(){
        if(length>=30.0)
            return true;
        return false;
    }

    public double getLength() {
        return length;
    }
}

