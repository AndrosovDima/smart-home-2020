package ru.sbt.mipt.oop.main;

public class Signaling implements Actionable{
    private SignalingState state;
    private String code = null;

    public Signaling() {
        this.state = new NonActiveState(this);
    }

    public SignalingState getState() {
        return state;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public void changeState(SignalingState state){
        this.state = state;
    }

    public void activateSignaling(String activatingCode){
        state.activateSignaling(activatingCode);
    }

    public void deactivateSignaling(String activatingCode){
        state.deactivateSignaling(activatingCode);
    }

    public void turnOnAlarmState(){
        state.turnOnAlarmState();
    }

    @Override
    public void execute(Action action) {
        action.doSmth(this);
    }
}
