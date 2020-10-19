package ru.sbt.mipt.oop.main;

public class NonActiveState implements SignalingState {
    private final Signaling signaling;

    public NonActiveState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activateSignaling(String activatingCode) {
        signaling.changeState(new ActiveState(signaling));
        signaling.setCode(activatingCode);
        System.out.println("Signaling has been activated");
    }

    @Override
    public void deactivateSignaling(String activatingCode) {
        System.out.println("Signaling is already deactivated");
    }

    @Override
    public void turnOnAlarmState() {
        signaling.changeState(new AlarmState(signaling));
        System.out.println("ALARM_STATE");
    }
}
