package ru.sbt.mipt.oop.main;

public class Signaling implements Actionable{
    private SignalingState state;
    private String code;

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
        this.changeState(new AlarmState(this));
        System.out.println("ALARM_STATE");
    }

    @Override
    public void execute(Action action) {
        action.doSmth(this);
    }
}
