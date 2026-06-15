/*
 * diffuser_schroeder_2d_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class diffuser_schroeder_2d_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "0.61[m]", "\u6269\u6563\u5668\u5bbd\u5ea6");
    model.param().set("Lw", "0.09[m]", "\u4e00\u4e2a\u4e95\u7684\u5bbd\u5ea6");
    model.param().set("Li", "0.01[m]", "\u4e95\u95f4\u5bbd\u5ea6");
    model.param().set("H", "0.3[m]", "\u6269\u6563\u5668\u9ad8\u5ea6");
    model.param().set("d1", "0.15[m]", "\u4e95 1 \u7684\u6df1\u5ea6");
    model.param().set("d2", "0.10[m]", "\u4e95 2 \u7684\u6df1\u5ea6");
    model.param().set("d3", "0.29[m]", "\u4e95 3 \u7684\u6df1\u5ea6");
    model.param().set("d4", "0.20[m]", "\u4e95 4 \u7684\u6df1\u5ea6");
    model.param().set("d5", "0.24[m]", "\u4e95 5 \u7684\u6df1\u5ea6");
    model.param().set("d6", "0.05[m]", "\u4e95 6 \u7684\u6df1\u5ea6");
    model.param()
         .set("r_air", "1[m]", "\u6c14\u57df\u7684\u534a\u5f84\uff08\u5bf9\u4e8e\u5355\u4e2a\u6269\u6563\u5668\uff09");
    model.param().set("r0", "10[m]", "\u8ba1\u7b97\u8ddd\u79bb");
    model.param().set("Hair", "1[m]", "\u7a7a\u6c14\u57df\u9ad8\u5ea6");
    model.param().set("Hpml", "0.2[m]", "PML \u539a\u5ea6");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").label("\u5355\u4e2a\u6269\u6563\u4f53");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Lw", "d1"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-L/2+Li", "-d1"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Lw", "d2"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-L/2+2*Li+Lw", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "-d2", 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"Lw", "d3"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-L/2+3*Li+2*Lw", "0"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("pos", "-d3", 1);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"Lw", "d4"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-L/2+4*Li+3*Lw", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "-d4", 1);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"Lw", "d5"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"-L/2+5*Li+4*Lw", "0"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("pos", "-d5", 1);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"Lw", "d6"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"-L/2+6*Li+5*Lw", "0"});
    model.component("comp1").geom("geom1").feature("r6").setIndex("pos", "-d6", 1);
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u4e95");
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("c1", false);
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_air");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("c1", "r1", "r2", "r3", "r4", "r5", "r6");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("r", "r0");
    model.component("comp1").geom("geom1").feature("ca1").set("angle2", 180);
    model.component("comp1").geom("geom1").run("fin");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("5 \u5355\u5143\u6392\u5217");

    model.component("comp2").geom("geom2").nodeGroup().copy("grp1", "geom1/grp1");
    model.component("comp2").geom("geom2").nodeGroup().duplicate("grp2", "grp1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").label("\u5de6\u4fa7\u7684\u4e95 1");
    model.component("comp2").geom("geom2").feature("r7").set("pos", new String[]{"-3*L/2+Li", "-d1"});
    model.component("comp2").geom("geom2").feature("r8").set("pos", new String[]{"-3*L/2+2*Li+Lw", "-d2"});
    model.component("comp2").geom("geom2").feature("r9").set("pos", new String[]{"-3*L/2+3*Li+2*Lw", "-d3"});
    model.component("comp2").geom("geom2").feature("r10").set("pos", new String[]{"-3*L/2+4*Li+3*Lw", "-d4"});
    model.component("comp2").geom("geom2").feature("r11").set("pos", new String[]{"-3*L/2+5*Li+4*Lw", "-d5"});
    model.component("comp2").geom("geom2").feature("r12").set("pos", new String[]{"-3*L/2+6*Li+5*Lw", "-d6"});
    model.component("comp2").geom("geom2").nodeGroup().duplicate("grp3", "grp1");
    model.component("comp2").geom("geom2").nodeGroup("grp3").label("\u5de6\u4fa7\u7684\u4e95 2");
    model.component("comp2").geom("geom2").feature("r13").set("pos", new String[]{"-5*L/2+Li", "-d1"});
    model.component("comp2").geom("geom2").feature("r14").set("pos", new String[]{"-5*L/2+2*Li+Lw", "-d2"});
    model.component("comp2").geom("geom2").feature("r15").set("pos", new String[]{"-5*L/2+3*Li+2*Lw", "-d3"});
    model.component("comp2").geom("geom2").feature("r16").set("pos", new String[]{"-5*L/2+4*Li+3*Lw", "-d4"});
    model.component("comp2").geom("geom2").feature("r17").set("pos", new String[]{"-5*L/2+5*Li+4*Lw", "-d5"});
    model.component("comp2").geom("geom2").feature("r18").set("pos", new String[]{"-5*L/2+6*Li+5*Lw", "-d6"});
    model.component("comp2").geom("geom2").nodeGroup().duplicate("grp4", "grp1");
    model.component("comp2").geom("geom2").nodeGroup("grp4").label("\u53f3\u4fa7\u7684\u4e95 1");
    model.component("comp2").geom("geom2").feature("r19").set("pos", new String[]{"L/2+Li", "-d1"});
    model.component("comp2").geom("geom2").feature("r20").set("pos", new String[]{"L/2+2*Li+Lw", "-d2"});
    model.component("comp2").geom("geom2").feature("r21").set("pos", new String[]{"L/2+3*Li+2*Lw", "-d3"});
    model.component("comp2").geom("geom2").feature("r22").set("pos", new String[]{"L/2+4*Li+3*Lw", "-d4"});
    model.component("comp2").geom("geom2").feature("r23").set("pos", new String[]{"L/2+5*Li+4*Lw", "-d5"});
    model.component("comp2").geom("geom2").feature("r24").set("pos", new String[]{"L/2+6*Li+5*Lw", "-d6"});
    model.component("comp2").geom("geom2").nodeGroup().duplicate("grp5", "grp1");
    model.component("comp2").geom("geom2").nodeGroup("grp5").label("\u53f3\u4fa7\u7684\u4e95 2");
    model.component("comp2").geom("geom2").feature("r25").set("pos", new String[]{"3*L/2+Li", "-d1"});
    model.component("comp2").geom("geom2").feature("r26").set("pos", new String[]{"3*L/2+2*Li+Lw", "-d2"});
    model.component("comp2").geom("geom2").feature("r27").set("pos", new String[]{"3*L/2+3*Li+2*Lw", "-d3"});
    model.component("comp2").geom("geom2").feature("r28").set("pos", new String[]{"3*L/2+4*Li+3*Lw", "-d4"});
    model.component("comp2").geom("geom2").feature("r29").set("pos", new String[]{"3*L/2+5*Li+4*Lw", "-d5"});
    model.component("comp2").geom("geom2").feature("r30").set("pos", new String[]{"3*L/2+6*Li+5*Lw", "-d6"});
    model.component("comp2").geom("geom2").run("r30");
    model.component("comp2").geom("geom2").create("c1", "Circle");
    model.component("comp2").geom("geom2").nodeGroup("grp5").remove("c1", false);
    model.component("comp2").geom("geom2").feature("c1").set("r", "3*r_air");
    model.component("comp2").geom("geom2").feature("c1").set("angle", 180);
    model.component("comp2").geom("geom2").run("c1");
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input")
         .set("c1", "r1", "r10", "r11", "r12", "r13", "r14", "r15", "r16", "r17", "r18", "r19", "r2", "r20", "r21", "r22", "r23", "r24", "r25", "r26", "r27", "r28", "r29", "r3", "r30", "r4", "r5", "r6", "r7", "r8", "r9");
    model.component("comp2").geom("geom2").feature("uni1").set("intbnd", false);
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").run("uni1");
    model.component("comp2").geom("geom2").create("ca1", "CircularArc");
    model.component("comp2").geom("geom2").feature("ca1").set("r", "r0");
    model.component("comp2").geom("geom2").feature("ca1").set("angle2", 180);
    model.component("comp2").geom("geom2").run("fin");

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 2);

    model.component("comp3").mesh().create("mesh3");

    model.component("comp3").label("\u65e0\u9650\u6392\u5217");

    model.component("comp3").geom("geom3").nodeGroup().copy("grp1", "geom1/grp1");
    model.component("comp3").geom("geom3").run("r6");
    model.component("comp3").geom("geom3").create("r7", "Rectangle");
    model.component("comp3").geom("geom3").nodeGroup("grp1").remove("r7", false);
    model.component("comp3").geom("geom3").feature("r7").set("size", new String[]{"L", "Hair"});
    model.component("comp3").geom("geom3").feature("r7").set("pos", new String[]{"-L/2", "0"});
    model.component("comp3").geom("geom3").run("r7");
    model.component("comp3").geom("geom3").create("uni1", "Union");
    model.component("comp3").geom("geom3").feature("uni1").selection("input")
         .set("r1", "r2", "r3", "r4", "r5", "r6", "r7");
    model.component("comp3").geom("geom3").feature("uni1").set("intbnd", false);
    model.component("comp3").geom("geom3").run("fin");

    model.title(null);

    model.description("");

    model.label("diffuser_schroeder_2d_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
