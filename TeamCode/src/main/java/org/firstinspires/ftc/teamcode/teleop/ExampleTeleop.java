package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.controller.ControllerHandler;

@TeleOp(name = "Example Teleop", group = "Example")
public class ExampleTeleop extends OpMode {

    private DriveTrain dt;
    private ControllerHandler ch1, ch2;
    private String status;

    @Override
    public void init() {
        DcMotor fR = hardwareMap.get(DcMotor.class, "FrontRight");
        DcMotor fL = hardwareMap.get(DcMotor.class, "FrontLeft");
        DcMotor bR = hardwareMap.get(DcMotor.class, "BackRight");
        DcMotor bL = hardwareMap.get(DcMotor.class, "BackLeft");
        dt = new DriveTrain(fR, fL, bR, bL, new DcMotor[] {fR, fL});

        ch1 = new ControllerHandler(gamepad1);
        ch2 = new ControllerHandler(gamepad2);

        status = "Awaiting start";
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", status);

        switch(status) {
            case "Awaiting start":
                status = "Awaiting start.";
                break;
            case "Awaiting start.":
                status = "Awaiting start..";
                break;
            case "Awaiting start..":
                status = "Awaiting start...";
                break;
            case "Awaiting start...":
                status = "Awaiting start";
                break;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void start() {
        status = "Running...";
        telemetry.addData("Status", status);
        telemetry.update();
    }

    @Override
    public void loop() {
        dt.update(ch1, null, false);
    }
}