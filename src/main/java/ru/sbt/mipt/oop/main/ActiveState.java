package ru.sbt.mipt.oop.main;

public class ActiveState implements SignalingState {
    private transient final Signaling signaling;

    public ActiveState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activateSignaling(String activatingCode) {
        System.out.println("Signaling is already activated");
    }

    @Override
    public void deactivateSignaling(String activatingCode) {
        if (activatingCode.equals(signaling.getCode())){
            signaling.changeState(new NonActiveState(signaling));
            signaling.setCode(null);
            System.out.println("Signaling has been deactivated");
        } else if (signaling.getState() instanceof ActiveState){
            signaling.changeState(new AlarmState(signaling));
            System.out.println("ALARM_STATE");
        } else {
            System.out.println("ALARM_STATE");
        }
    }
}
