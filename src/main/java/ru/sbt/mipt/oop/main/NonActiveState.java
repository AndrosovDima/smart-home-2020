package ru.sbt.mipt.oop.main;

public class NonActiveState implements SignalingState {
    private transient final Signaling signaling;

    public NonActiveState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activateSignaling(String activatingCode) {
        if (signaling.getState() instanceof AlarmState){
            return;
        }
        signaling.changeState(new ActiveState(signaling));
        signaling.setCode(activatingCode);
        System.out.println("Signaling has been activated");
    }

    @Override
    public void deactivateSignaling(String activatingCode) {
        System.out.println("Signaling is already deactivated");
    }
}
