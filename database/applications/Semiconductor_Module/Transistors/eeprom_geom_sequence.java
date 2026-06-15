/*
 * eeprom_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:54 by COMSOL 6.3.0.290. */
public class eeprom_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.8, 0.1});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "50[nm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.15", "42[nm]"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0.62", "8[nm]"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"0.15", "8[nm]"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0.62, 0});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new double[]{0.8, 0.1});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "165[nm]"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new double[]{1.8, 0.6});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{-0.5, -0.6});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new double[]{1.8, 0.3});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new double[]{-0.5, 0});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new double[]{0.35, 0.15});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new double[]{-0.5, 0.15});
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new double[]{0.35, 0.15});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new double[]{0.95, 0.15});
    model.component("comp1").geom("geom1").run("r8");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r6");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r7", "r8");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("dif1", 3, 4, 5, 6);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 0.075);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("r9").set("size", new double[]{0.55, 0.2});
    model.component("comp1").geom("geom1").feature("r9").set("pos", new double[]{-0.5, -0.2});
    model.component("comp1").geom("geom1").run("r9");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("r9", 2);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", 0.08);
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("r10").set("size", new double[]{0.45, 0.2});
    model.component("comp1").geom("geom1").feature("r10").set("pos", new double[]{0.85, -0.2});
    model.component("comp1").geom("geom1").run("r10");
    model.component("comp1").geom("geom1").create("r11", "Rectangle");
    model.component("comp1").geom("geom1").feature("r11").set("size", new double[]{0.7, 0.1});
    model.component("comp1").geom("geom1").feature("r11").set("pos", new double[]{0.6, -0.1});
    model.component("comp1").geom("geom1").run("r11");
    model.component("comp1").geom("geom1").create("fil3", "Fillet");
    model.component("comp1").geom("geom1").feature("fil3").selection("point").set("r10", 1);
    model.component("comp1").geom("geom1").feature("fil3").selection("point").set("r11", 1);
    model.component("comp1").geom("geom1").feature("fil3").set("radius", 0.08);
    model.component("comp1").geom("geom1").run("fil3");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", -0.5, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", -0.1, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("eeprom_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
