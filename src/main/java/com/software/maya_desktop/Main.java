package com.software.maya_desktop;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    double Kreiswinkel = 360;
    double Toene = 13;
    double Nahuales = 20;
    double Tage = 365;

    ImageView ivZahnrad_Toene = new ImageView();
    ImageView ivZahnkreis_Nahuales = new ImageView();

    ImageView ivWellenabschnitt_rot = new ImageView();
    ImageView ivWellenabschnitt_weiß = new ImageView();
    ImageView ivWellenabschnitt_blau = new ImageView();
    ImageView ivWellenabschnitt_gelb = new ImageView();

    ImageView ivKreis_Nahuales = new ImageView();

    ImageView ivRadjahr_Haab = new ImageView();

    Pane paneWinals = new Pane();

    Image[] SandSteinGlyphe = new Image[20];

    ImageView ivSandSteinGlyphe_Baktun = new ImageView();
    ImageView ivSandSteinGlyphe_Katun = new ImageView();
    ImageView ivSandSteinGlyphe_Tun = new ImageView();
    ImageView ivSandSteinGlyphe_Unial = new ImageView();
    ImageView ivSandSteinGlyphe_Kin = new ImageView();

    float WellenAbschnittWinkel;
    int WertWellenMaske;

    int Ton;
    int Nahual;

    int Tag;

    int kinzahl = 0;
    int welle = 0;
    int WellenFarbe = 0;

    int Baktun;
    int Katun;
    int Tun;
    int Uinal;
    int Kin;

    double FrameRate = 100;

    double s;
    double t;

    double p;
    double q;

    double y;
    double z;

    double v;
    double w;

    String[] ToeneNamen = { "Jun", "Keb", "Oxib", "Kajib", "Job", "Waqib", "Wuqub", "Wajxaqib", "Belejeb", "Lajuj",
            "Julajuj", "Kablajuj", "Oxlajuj" };
    String[] NahualesNamen = { "Imox", "Iq", "Aqabal", "Kat", "Kan", "Kame", "Kej", "Qanil", "Toj", "Tzi", "Batz", "E",
            "Aj", "Ix", "Tzikin", "Ajmaq", "Noj", "Tijax", "Kawoq", "Ajpu" };

    String[] strWinal = {
            "Pop",
            "Uo",
            "Zip",
            "Zotz",
            "Zek",
            "Xul",
            "Yaxkin",
            "Mol",
            "Chen",
            "Yax",
            "Zac",
            "Ceh",
            "Mac",
            "Kankin",
            "Muan",
            "Pax",
            "Kayab",
            "Cumku",
            "Wayeb"
    };

    Text txtTone;
    Text txtNahual;

    double opacity = 0.8;

    @Override
    public void start(Stage tzolkin_stage) {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dtfDatum = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String strStartDate = "22.12.2012 00:00";

        String strCurrentDate = dtfDatum.format(calendar.getTime());

        long VergangeneTage;
        try {
            Date StartDate = dtfDatum.parse(strStartDate);
            long StartlongDate = StartDate.getTime();

            Date CurrentDate = dtfDatum.parse(strCurrentDate);
            long CurrentlongDate = CurrentDate.getTime();

            VergangeneTage = (CurrentlongDate + 86400000L - StartlongDate) / 86400000L;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Baktun = 13; // ?
        Katun = (int) (VergangeneTage / 7200L);
        Tun = (int) ((VergangeneTage - Katun * 7200L) / 360L);
        Uinal = (int) ((VergangeneTage - Katun * 7200L - Tun * 360L) / 20L);
        Kin = (int) (VergangeneTage - Katun * 7200L - Tun * 360L - Uinal * 20L);

        int StartTon = 3;
        int StartNahual = 19;
        long StartTag = 303;

        Ton = (int) ((StartTon + VergangeneTage) % 13);

        Nahual = (int) ((StartNahual + VergangeneTage) % 20);

        Tag = (int) ((StartTag + VergangeneTage) % 365);

        kinzahl = getKinzahl(Ton, Nahual);
        welle = kinzahl / 13;

        WellenFarbe = welle % 4;

        WertWellenMaske = kinzahl % 52;

        WellenAbschnittWinkel = 18 * WertWellenMaske;

        Image Zahnrad_Toene = new Image("zahnrad_toene.png");
        ivZahnrad_Toene.setImage(Zahnrad_Toene);
        ivZahnrad_Toene.setOpacity(opacity);

        ivZahnrad_Toene.setLayoutX(229);
        ivZahnrad_Toene.setLayoutY(159.5);

        ivZahnrad_Toene.setRotate(Kreiswinkel / Toene * Ton);

        Image Zahnkreis_Nahuales = new Image("zahnkreis_nahuales.png");
        ivZahnkreis_Nahuales.setImage(Zahnkreis_Nahuales);
        ivZahnkreis_Nahuales.setOpacity(opacity);

        ivZahnkreis_Nahuales.setLayoutX(0);
        ivZahnkreis_Nahuales.setLayoutY(0);

        ivZahnkreis_Nahuales.setRotate(Kreiswinkel / Nahuales * Nahual);

        Image Wellenabschnitt_rot = new Image("wellenabschnitt_rot.png");
        ivWellenabschnitt_rot.setImage(Wellenabschnitt_rot);
        ivWellenabschnitt_rot.setOpacity(opacity);

        ivWellenabschnitt_rot.setLayoutX(0);
        ivWellenabschnitt_rot.setLayoutY(0);

        Image Wellenabschnitt_weiß = new Image("wellenabschnitt_weiß.png");
        ivWellenabschnitt_weiß.setImage(Wellenabschnitt_weiß);
        ivWellenabschnitt_weiß.setOpacity(opacity);

        ivWellenabschnitt_weiß.setLayoutX(0);
        ivWellenabschnitt_weiß.setLayoutY(0);

        Image Wellenabschnitt_blau = new Image("wellenabschnitt_blau.png");
        ivWellenabschnitt_blau.setImage(Wellenabschnitt_blau);
        ivWellenabschnitt_blau.setOpacity(opacity);

        ivWellenabschnitt_blau.setLayoutX(0);
        ivWellenabschnitt_blau.setLayoutY(0);

        Image Wellenabschnitt_gelb = new Image("wellenabschnitt_gelb.png");
        ivWellenabschnitt_gelb.setImage(Wellenabschnitt_gelb);
        ivWellenabschnitt_gelb.setOpacity(opacity);

        ivWellenabschnitt_gelb.setLayoutX(0);
        ivWellenabschnitt_gelb.setLayoutY(0);

        switch (WellenFarbe) {
            case 0:
                ivWellenabschnitt_rot.setVisible(true);
                ivWellenabschnitt_weiß.setVisible(false);
                ivWellenabschnitt_blau.setVisible(false);
                ivWellenabschnitt_gelb.setVisible(false);
                break;
            case 1:
                ivWellenabschnitt_rot.setVisible(false);
                ivWellenabschnitt_weiß.setVisible(true);
                ivWellenabschnitt_blau.setVisible(false);
                ivWellenabschnitt_gelb.setVisible(false);
                break;
            case 2:
                ivWellenabschnitt_rot.setVisible(false);
                ivWellenabschnitt_weiß.setVisible(false);
                ivWellenabschnitt_blau.setVisible(true);
                ivWellenabschnitt_gelb.setVisible(false);
                break;
            case 3:
                ivWellenabschnitt_rot.setVisible(false);
                ivWellenabschnitt_weiß.setVisible(false);
                ivWellenabschnitt_blau.setVisible(false);
                ivWellenabschnitt_gelb.setVisible(true);
                break;
            default:
        }

        ivWellenabschnitt_rot.setRotate(WellenAbschnittWinkel);
        ivWellenabschnitt_weiß.setRotate(WellenAbschnittWinkel);
        ivWellenabschnitt_blau.setRotate(WellenAbschnittWinkel);
        ivWellenabschnitt_gelb.setRotate(WellenAbschnittWinkel);

        Image Kreis_Nahuales = new Image("kreis_nahuales.png");
        ivKreis_Nahuales.setImage(Kreis_Nahuales);
        ivKreis_Nahuales.setOpacity(0.8);

        ivKreis_Nahuales.setLayoutX(0);
        ivKreis_Nahuales.setLayoutY(0);

        ivKreis_Nahuales.setRotate(Kreiswinkel / Nahuales * Nahual);

        SandSteinGlyphe[0] = new Image("SandsteinGlyphe_0.png");      
        SandSteinGlyphe[1] = new Image("SandsteinGlyphe_1.png");
        SandSteinGlyphe[2] = new Image("SandsteinGlyphe_2.png");
        SandSteinGlyphe[3] = new Image("SandsteinGlyphe_3.png");
        SandSteinGlyphe[4] = new Image("SandsteinGlyphe_4.png");
        SandSteinGlyphe[5] = new Image("SandsteinGlyphe_5.png");
        SandSteinGlyphe[6] = new Image("SandsteinGlyphe_6.png");
        SandSteinGlyphe[7] = new Image("SandsteinGlyphe_7.png");
        SandSteinGlyphe[8] = new Image("SandsteinGlyphe_8.png");
        SandSteinGlyphe[9] = new Image("SandsteinGlyphe_9.png");
        SandSteinGlyphe[10] = new Image("SandsteinGlyphe_10.png");
        SandSteinGlyphe[11] = new Image("SandsteinGlyphe_11.png");
        SandSteinGlyphe[12] = new Image("SandsteinGlyphe_12.png");
        SandSteinGlyphe[13] = new Image("SandsteinGlyphe_13.png");
        SandSteinGlyphe[14] = new Image("SandsteinGlyphe_14.png");
        SandSteinGlyphe[15] = new Image("SandsteinGlyphe_15.png");
        SandSteinGlyphe[16] = new Image("SandsteinGlyphe_16.png");
        SandSteinGlyphe[17] = new Image("SandsteinGlyphe_17.png");
        SandSteinGlyphe[18] = new Image("SandsteinGlyphe_18.png");
        SandSteinGlyphe[19] = new Image("SandsteinGlyphe_19.png");

        ivSandSteinGlyphe_Baktun.setImage(SandSteinGlyphe[Baktun]);
        ivSandSteinGlyphe_Baktun.setOpacity(opacity);
        ivSandSteinGlyphe_Baktun.setFitHeight(50);
        ivSandSteinGlyphe_Baktun.setFitWidth(50);
        ivSandSteinGlyphe_Baktun.setLayoutX(165);
        ivSandSteinGlyphe_Baktun.setLayoutY(171);
        ivSandSteinGlyphe_Katun.setImage(SandSteinGlyphe[Katun]);
        ivSandSteinGlyphe_Katun.setOpacity(opacity);
        ivSandSteinGlyphe_Katun.setFitHeight(50);
        ivSandSteinGlyphe_Katun.setFitWidth(50);
        ivSandSteinGlyphe_Katun.setLayoutX(165);
        ivSandSteinGlyphe_Katun.setLayoutY(223);
        ivSandSteinGlyphe_Tun.setImage(SandSteinGlyphe[Tun]);
        ivSandSteinGlyphe_Tun.setOpacity(opacity);
        ivSandSteinGlyphe_Tun.setFitHeight(50);
        ivSandSteinGlyphe_Tun.setFitWidth(50);
        ivSandSteinGlyphe_Tun.setLayoutX(165);
        ivSandSteinGlyphe_Tun.setLayoutY(275);
        ivSandSteinGlyphe_Unial.setImage(SandSteinGlyphe[Uinal]);
        ivSandSteinGlyphe_Unial.setOpacity(opacity);
        ivSandSteinGlyphe_Unial.setFitHeight(50);
        ivSandSteinGlyphe_Unial.setFitWidth(50);
        ivSandSteinGlyphe_Unial.setLayoutX(165);
        ivSandSteinGlyphe_Unial.setLayoutY(327);
        ivSandSteinGlyphe_Kin.setImage(SandSteinGlyphe[Kin]);
        ivSandSteinGlyphe_Kin.setOpacity(opacity);
        ivSandSteinGlyphe_Kin.setFitHeight(50);
        ivSandSteinGlyphe_Kin.setFitWidth(50);
        ivSandSteinGlyphe_Kin.setLayoutX(165);
        ivSandSteinGlyphe_Kin.setLayoutY(379);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setLayoutX(269.5);
        vbox.setLayoutY(260);
        vbox.setMinWidth(200);
        vbox.setMinHeight(80);

        Font fontTextKin = Font.font("Chococooky", 28);

        txtTone = new Text(ToeneNamen[Ton]);
        txtTone.setFont(fontTextKin);
        txtTone.setFill(Color.WHITE);
        txtTone.setOpacity(opacity);

        txtNahual = new Text(NahualesNamen[Nahual]);
        txtNahual.setFont(fontTextKin);
        txtNahual.setFill(Color.WHITE);
        txtNahual.setOpacity(opacity);

        vbox.getChildren().addAll(txtTone, txtNahual);

        Group tzolkinroot = new Group();

        tzolkinroot.getChildren().add(ivZahnrad_Toene);
        tzolkinroot.getChildren().add(ivZahnkreis_Nahuales);

        tzolkinroot.getChildren().add(ivWellenabschnitt_rot);
        tzolkinroot.getChildren().add(ivWellenabschnitt_weiß);
        tzolkinroot.getChildren().add(ivWellenabschnitt_blau);
        tzolkinroot.getChildren().add(ivWellenabschnitt_gelb);

        tzolkinroot.getChildren().add(ivKreis_Nahuales);

        tzolkinroot.getChildren().add(ivSandSteinGlyphe_Baktun);
        tzolkinroot.getChildren().add(ivSandSteinGlyphe_Katun);
        tzolkinroot.getChildren().add(ivSandSteinGlyphe_Tun);
        tzolkinroot.getChildren().add(ivSandSteinGlyphe_Unial);
        tzolkinroot.getChildren().add(ivSandSteinGlyphe_Kin);
        tzolkinroot.getChildren().add(vbox);

        tzolkin_stage.initStyle(StageStyle.TRANSPARENT);
        Scene haabscene = new Scene(tzolkinroot, 600, 600);
        haabscene.setFill(Color.TRANSPARENT);

        /*
         * haabscene.setOnMousePressed(pressEvent -> {
         * haabscene.setOnMouseDragged(dragEvent -> {
         * tzolkin_stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
         * tzolkin_stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
         * });
         * });
         */

        tzolkin_stage.setScene(haabscene);

        tzolkin_stage.getIcons().add(new Image("Icon.png"));
        tzolkin_stage.setTitle("Cholqij");

        tzolkin_stage.setX(3084 + 21);
        tzolkin_stage.setY(240);

        Stage radjahr_stage = new Stage();

        Image Radjahr_Haab = new Image("radjahr_haab.png");
        ivRadjahr_Haab.setImage(Radjahr_Haab);
        ivRadjahr_Haab.setOpacity(opacity);

        ivRadjahr_Haab.setLayoutX(0);
        ivRadjahr_Haab.setLayoutY(-4541);

        ivRadjahr_Haab.setRotate(-Kreiswinkel / Tage * Tag);

        Group radjahrroot = new Group();
        
        radjahrroot.getChildren().add(ivRadjahr_Haab);

        Font fontTextWinal = Font.font("Chococooky", 22);

        Label[] label = new Label[365];
        
        for (int i = 0; i < 365; i++) {

            label[i] = new Label(strWinal[i / 20]);
            label[i].setPrefSize(74, 26);
            label[i].setAlignment(Pos.CENTER);

            label[i].setFont(fontTextWinal);
            label[i].setTextFill(Color.WHITE);
            label[i].setOpacity(opacity);

            label[i].setLayoutX(40);
            label[i].setLayoutY(1080/2 - 13);

            //label[i].setRotate(-90);
            label[i].getTransforms().add(new Rotate(-90, 37, 13));

            label[i].getTransforms().add(new Rotate((double) i * 360 / 365, 37, 5081 - 40));

            paneWinals.getChildren().add(label[i]);
        }

        // TODO: Überprüfen
        paneWinals.getTransforms().add(new Rotate(-Kreiswinkel / Tage * Tag, 5081 + 24, 540));

        radjahrroot.getChildren().add(paneWinals);

        radjahr_stage.initStyle(StageStyle.TRANSPARENT);
        Scene radjahrscene = new Scene(radjahrroot, 156, 1080);
        radjahrscene.setFill(Color.TRANSPARENT);

        radjahr_stage.setScene(radjahrscene);

        radjahr_stage.getIcons().add(new Image("Icon.png"));
        radjahr_stage.setTitle("Haab");
        
        radjahr_stage.setX(3684);
        radjahr_stage.setY(1080);

        tzolkin_stage.show();
        radjahr_stage.show();

        Timer DayTimer = new Timer();

        long cTime = System.currentTimeMillis() + 7200000; // plus 2 Stunden Sommerzeit (7200000), plus 1 Stunde
                                                           // Winterzeit (3600000)!

        long dSekunden = 86400000 - cTime % 86400000;

        DayTimer.schedule(new DayTimerTask(), dSekunden, 86400000);       

    }

    public class DayTimerTask extends TimerTask {

        @Override
        public void run() {

            s = Kreiswinkel / Toene * Ton;
            t = Kreiswinkel / Toene / FrameRate;

            p = Kreiswinkel / Nahuales * Nahual;
            q = Kreiswinkel / Nahuales / FrameRate;

            v = Kreiswinkel / Tage * Tag;
            w = Kreiswinkel / Tage / FrameRate;

            y = 18 * WertWellenMaske;
            z = 18 / FrameRate;

            Ton++;
            if (Ton > 12) {
                Ton = 0;
            }
            Nahual++;
            if (Nahual > 19) {
                Nahual = 0;
            }
            Tag++;
            if (Tag > 364) {
                Tag = 0;
            }
            WertWellenMaske++;
            if (WertWellenMaske > 51) {
                WertWellenMaske = 0;
            }

            Kin++;
            if (Kin > 19) {
                Kin = 0;
                Uinal++;
                if (Uinal > 17) {
                    Uinal = 0;
                    Tun++;
                    if (Tun > 19) {
                        Tun = 0;
                        Katun++;
                        if (Katun > 19) {
                            Katun = 0;
                        }
                    }
                }
            }
            animationTurn.start();
        }
    }

    AnimationTimer animationTurn = new AnimationTimer() {

        int frameCount = 0;
        long lastUpdate = 0;

        @Override
        public void handle(long now) {

            if (now - lastUpdate >= 50_000_000) {

                double r = s + (t * frameCount);
                ivZahnrad_Toene.setRotate(r);

                double o = p + (q * frameCount);
                ivZahnkreis_Nahuales.setRotate(o);
                ivKreis_Nahuales.setRotate(o);

                double u = v + (w * frameCount);
                ivRadjahr_Haab.setRotate(-u);
                // TODO: Überprüfen
                paneWinals.getTransforms().add(new Rotate(-w, 5081 + 24, 540));

                double x = y + (z * frameCount);
                ivWellenabschnitt_rot.setRotate(x);
                ivWellenabschnitt_weiß.setRotate(x);
                ivWellenabschnitt_blau.setRotate(x);
                ivWellenabschnitt_gelb.setRotate(x);
               
                if (frameCount == 50) {
                    txtTone.setText(ToeneNamen[Ton]);
                    txtNahual.setText(NahualesNamen[Nahual]);

                    ivSandSteinGlyphe_Katun.setImage(SandSteinGlyphe[Katun]);
                    ivSandSteinGlyphe_Tun.setImage(SandSteinGlyphe[Tun]);
                    ivSandSteinGlyphe_Unial.setImage(SandSteinGlyphe[Uinal]);
                    ivSandSteinGlyphe_Kin.setImage(SandSteinGlyphe[Kin]);

                    switch (WertWellenMaske) {
                        case 0:
                            if(Ton == 0){
                                y += 144;
                                ivWellenabschnitt_rot.setRotate(y + (z * frameCount));
                            }
                            ivWellenabschnitt_gelb.setVisible(false);
                            ivWellenabschnitt_rot.setVisible(true);
                            break;
                        case 13:
                            ivWellenabschnitt_rot.setVisible(false);
                            ivWellenabschnitt_weiß.setVisible(true);
                            ;
                            break;
                        case 26:
                            ivWellenabschnitt_weiß.setVisible(false);
                            ivWellenabschnitt_blau.setVisible(true);
                            break;
                        case 39:
                            ivWellenabschnitt_blau.setVisible(false);
                            ivWellenabschnitt_gelb.setVisible(true);
                            break;
                        default:
                    }
                }
                
                if (frameCount > 98) {
                    animationTurn.stop();
                    frameCount = 0;
                }

                frameCount++;
                lastUpdate = now;
            }
        }
    };

    private int getKinzahl(int ton, int nahuales) {

        int array[][] = { { 0, 0 },
                { 1, 1 },
                { 2, 2 },
                { 3, 3 },
                { 4, 4 },
                { 5, 5 },
                { 6, 6 },
                { 7, 7 },
                { 8, 8 },
                { 9, 9 },
                { 10, 10 },
                { 11, 11 },
                { 12, 12 },
                { 0, 13 },
                { 1, 14 },
                { 2, 15 },
                { 3, 16 },
                { 4, 17 },
                { 5, 18 },
                { 6, 19 },
                { 7, 0 },
                { 8, 1 },
                { 9, 2 },
                { 10, 3 },
                { 11, 4 },
                { 12, 5 },
                { 0, 6 },
                { 1, 7 },
                { 2, 8 },
                { 3, 9 },
                { 4, 10 },
                { 5, 11 },
                { 6, 12 },
                { 7, 13 },
                { 8, 14 },
                { 9, 15 },
                { 10, 16 },
                { 11, 17 },
                { 12, 18 },
                { 0, 19 },
                { 1, 0 },
                { 2, 1 },
                { 3, 2 },
                { 4, 3 },
                { 5, 4 },
                { 6, 5 },
                { 7, 6 },
                { 8, 7 },
                { 9, 8 },
                { 10, 9 },
                { 11, 10 },
                { 12, 11 },
                { 0, 12 },
                { 1, 13 },
                { 2, 14 },
                { 3, 15 },
                { 4, 16 },
                { 5, 17 },
                { 6, 18 },
                { 7, 19 },
                { 8, 0 },
                { 9, 1 },
                { 10, 2 },
                { 11, 3 },
                { 12, 4 },
                { 0, 5 },
                { 1, 6 },
                { 2, 7 },
                { 3, 8 },
                { 4, 9 },
                { 5, 10 },
                { 6, 11 },
                { 7, 12 },
                { 8, 13 },
                { 9, 14 },
                { 10, 15 },
                { 11, 16 },
                { 12, 17 },
                { 0, 18 },
                { 1, 19 },
                { 2, 0 },
                { 3, 1 },
                { 4, 2 },
                { 5, 3 },
                { 6, 4 },
                { 7, 5 },
                { 8, 6 },
                { 9, 7 },
                { 10, 8 },
                { 11, 9 },
                { 12, 10 },
                { 0, 11 },
                { 1, 12 },
                { 2, 13 },
                { 3, 14 },
                { 4, 15 },
                { 5, 16 },
                { 6, 17 },
                { 7, 18 },
                { 8, 19 },
                { 9, 0 },
                { 10, 1 },
                { 11, 2 },
                { 12, 3 },
                { 0, 4 },
                { 1, 5 },
                { 2, 6 },
                { 3, 7 },
                { 4, 8 },
                { 5, 9 },
                { 6, 10 },
                { 7, 11 },
                { 8, 12 },
                { 9, 13 },
                { 10, 14 },
                { 11, 15 },
                { 12, 16 },
                { 0, 17 },
                { 1, 18 },
                { 2, 19 },
                { 3, 0 },
                { 4, 1 },
                { 5, 2 },
                { 6, 3 },
                { 7, 4 },
                { 8, 5 },
                { 9, 6 },
                { 10, 7 },
                { 11, 8 },
                { 12, 9 },
                { 0, 10 },
                { 1, 11 },
                { 2, 12 },
                { 3, 13 },
                { 4, 14 },
                { 5, 15 },
                { 6, 16 },
                { 7, 17 },
                { 8, 18 },
                { 9, 19 },
                { 10, 0 },
                { 11, 1 },
                { 12, 2 },
                { 0, 3 },
                { 1, 4 },
                { 2, 5 },
                { 3, 6 },
                { 4, 7 },
                { 5, 8 },
                { 6, 9 },
                { 7, 10 },
                { 8, 11 },
                { 9, 12 },
                { 10, 13 },
                { 11, 14 },
                { 12, 15 },
                { 0, 16 },
                { 1, 17 },
                { 2, 18 },
                { 3, 19 },
                { 4, 0 },
                { 5, 1 },
                { 6, 2 },
                { 7, 3 },
                { 8, 4 },
                { 9, 5 },
                { 10, 6 },
                { 11, 7 },
                { 12, 8 },
                { 0, 9 },
                { 1, 10 },
                { 2, 11 },
                { 3, 12 },
                { 4, 13 },
                { 5, 14 },
                { 6, 15 },
                { 7, 16 },
                { 8, 17 },
                { 9, 18 },
                { 10, 19 },
                { 11, 0 },
                { 12, 1 },
                { 0, 2 },
                { 1, 3 },
                { 2, 4 },
                { 3, 5 },
                { 4, 6 },
                { 5, 7 },
                { 6, 8 },
                { 7, 9 },
                { 8, 10 },
                { 9, 11 },
                { 10, 12 },
                { 11, 13 },
                { 12, 14 },
                { 0, 15 },
                { 1, 16 },
                { 2, 17 },
                { 3, 18 },
                { 4, 19 },
                { 5, 0 },
                { 6, 1 },
                { 7, 2 },
                { 8, 3 },
                { 9, 4 },
                { 10, 5 },
                { 11, 6 },
                { 12, 7 },
                { 0, 8 },
                { 1, 9 },
                { 2, 10 },
                { 3, 11 },
                { 4, 12 },
                { 5, 13 },
                { 6, 14 },
                { 7, 15 },
                { 8, 16 },
                { 9, 17 },
                { 10, 18 },
                { 11, 19 },
                { 12, 0 },
                { 0, 1 },
                { 1, 2 },
                { 2, 3 },
                { 3, 4 },
                { 4, 5 },
                { 5, 6 },
                { 6, 7 },
                { 7, 8 },
                { 8, 9 },
                { 9, 10 },
                { 10, 11 },
                { 11, 12 },
                { 12, 13 },
                { 0, 14 },
                { 1, 15 },
                { 2, 16 },
                { 3, 17 },
                { 4, 18 },
                { 5, 19 },
                { 6, 0 },
                { 7, 1 },
                { 8, 2 },
                { 9, 3 },
                { 10, 4 },
                { 11, 5 },
                { 12, 6 },
                { 0, 7 },
                { 1, 8 },
                { 2, 9 },
                { 3, 10 },
                { 4, 11 },
                { 5, 12 },
                { 6, 13 },
                { 7, 14 },
                { 8, 15 },
                { 9, 16 },
                { 10, 17 },
                { 11, 18 },
                { 12, 19 } };

        int k;
        for (k = 0; k < array.length; k++) {
            if (array[k][0] == ton && array[k][1] == nahuales) {
                break;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        /// com.sun.javafx.application.LauncherImpl.launchApplication(Main.class,
        /// TestPre.class, args);
        launch();
    }
}

