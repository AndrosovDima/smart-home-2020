package ru.sbt.mipt.oop.main;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.ActiveState;
import ru.sbt.mipt.oop.main.Signaling;
import ru.sbt.mipt.oop.main.SignalingState;

import static org.junit.jupiter.api.Assertions.*;

class SignalingTest {

    @Test
    void changeStateTest_returnsTrue_whenStateHaveChangedSuccessfully() {
        // given
        Signaling signaling = new Signaling();
        SignalingState activeState = new ActiveState(signaling);
        // when
        signaling.changeState(activeState);
        boolean isSuccess = signaling.getState() instanceof ActiveState;
        // then
        assertTrue(isSuccess);
    }


    @Test
    void activateSignalingTest_returnsTrue_whenSignalisingActivatedSuccessfully() {
        // given
        Signaling signaling = new Signaling();
        // when
        signaling.activateSignaling("1234");
        boolean isSuccessActivate = signaling.getState() instanceof ActiveState;
        // then
        assertTrue(isSuccessActivate);
    }

    @Test
    void activateSignalingTest_returnsTrue_whenSignalisingActivatingCodeIsCorrect() {
        // given
        Signaling signaling = new Signaling();
        // when
        signaling.activateSignaling("1234");
        String code = signaling.getCode();
        boolean isCodeCorrect = code.equals("1234");
        // then
        assertTrue(isCodeCorrect);
    }

    @Test
    void deactivateSignalingTest_returnsTrue_whenSignalisingDeactivatesFromAlarmStateWithCorrectAlarmCode() {
        // given
        Signaling signaling = new Signaling();
        // when
        signaling.turnOnAlarmState();
        System.out.println(signaling.getState());
        signaling.deactivateSignaling("0000");
        System.out.println(signaling.getState());
        boolean isSuccess = signaling.getState() instanceof NonActiveState;
        // then
        assertTrue(isSuccess);
    }

    @Test
    void deactivateSignalingTest_returnsFalse_whenSignalisingDeactivatesFromAlarmStateWithIncorrectAlarmCode() {
        // given
        Signaling signaling = new Signaling();
        // when
        signaling.turnOnAlarmState();
        System.out.println(signaling.getState());
        signaling.deactivateSignaling("1234");
        System.out.println(signaling.getState());
        boolean isSuccess = signaling.getState() instanceof NonActiveState;
        // then
        assertFalse(isSuccess);
    }

    @Test
    void deactivateSignalingTest_returnsTrue_whenSignalisingDeactivatingCodeIsCorrect() {
        // given
        Signaling signaling = new Signaling();
        // when
        signaling.activateSignaling("1234");
        signaling.deactivateSignaling("1234");
        boolean isSuccess = signaling.getState() instanceof NonActiveState;
        // then
        assertTrue(isSuccess);
    }

    @Test
    void deactivateSignalingTest_returnsFalse_whenSignalisingDeactivatingCodeIsIncorrect() {
        // given
        Signaling signaling = new Signaling();
        // when
        signaling.activateSignaling("1234");
        signaling.deactivateSignaling("4321");
        boolean isSuccess = signaling.getState() instanceof NonActiveState;
        // then
        assertFalse(isSuccess);
    }

    @Test
    void turnOnAlarmStateTest_returnsTrue_whenAlarmStateTurnsOnCorrect() {
        // given
        Signaling signaling = new Signaling();
        // when
        signaling.turnOnAlarmState();
        boolean isSuccess = signaling.getState() instanceof AlarmState;
        // then
        assertTrue(isSuccess);
    }
}