package ru.sbt.mipt.oop.main;

public class AlarmState implements SignalingState {
    private final Signaling signaling;

    public AlarmState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activateSignaling(String activatingCode) {
    }

    @Override
    public void deactivateSignaling(String activatingCode) {
        if (activatingCode.equals(signaling.getCode()) || signaling.getCode() == null){
            signaling.changeState(new NonActiveState(signaling));
            signaling.setCode(null);
            System.out.println("Signaling has been deactivated");
        } else {
            System.out.println("ALARM_STATE");
        }
    }

    @Override
    public void turnOnAlarmState() {
        signaling.changeState(new AlarmState(signaling));
        System.out.println("ALARM_STATE");
    }
}
