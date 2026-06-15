/*
 * acoustic_microfluidic_pump_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class acoustic_microfluidic_pump_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("channel_w", "0.6[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("L", "10[mm]", "\u7cfb\u7edf\u957f\u5ea6");
    model.param().set("W", "5[mm]", "\u7cfb\u7edf\u5bbd\u5ea6");
    model.param().set("corner", "0.5[mm]", "\u8154\u5ba4\u7684\u5706\u89d2\u534a\u5f84");
    model.param().set("flap_w", "0.2[\u00b5m]", "\u74e3\u819c\u5bbd\u5ea6");
    model.param().set("flap_L", "250[\u00b5m]", "\u74e3\u819c\u957f\u5ea6");
    model.param().set("flap_angle", "30[deg]", "\u74e3\u819c\u89d2\u5ea6");
    model.param().set("flap_dist", "1[mm]", "\u74e3\u819c\u95f4\u8ddd");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "W"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"L-channel_w*2", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "W-channel_w*2", 1);
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r2", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "corner");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-10*flap_w", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "cos(flap_angle)*flap_L", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2+sin(flap_angle)*flap_L", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "+10*flap_w", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2", 2, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "flap_L/4");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "-W/2"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"2*flap_L", "0.7*flap_L"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-0.75*flap_L", "0"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("pos", "-W/2", 1);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("c1", "pol1", "r3");
    model.component("comp1").geom("geom1").feature("copy1")
         .set("displx", "range(-3*flap_dist,(3*flap_dist-(-3*flap_dist))/6,3*flap_dist)");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-10*flap_w-0.5*flap_dist", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-W/2+channel_w", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2")
         .setIndex("table", "cos(flap_angle)*flap_L-0.5*flap_dist", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2")
         .setIndex("table", "-W/2+channel_w-sin(flap_angle)*flap_L", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "+10*flap_w-0.5*flap_dist", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-W/2+channel_w", 2, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "flap_L/4");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"-0.5*flap_dist", "0"});
    model.component("comp1").geom("geom1").feature("c2").setIndex("pos", "-W/2+channel_w", 1);
    model.component("comp1").geom("geom1").feature("c2").set("rot", 180);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"2*flap_L", "0.7*flap_L"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-0.75*flap_L-flap_dist/2", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "-W/2+channel_w-flap_L*0.7", 1);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("c2", "pol2", "r4");
    model.component("comp1").geom("geom1").feature("copy2")
         .set("displx", "range(-2*flap_dist,(3*flap_dist-(-2*flap_dist))/5,3*flap_dist)");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("c1", "c2", "copy1", "copy2", "fil1", "pol1", "pol2", "r3", "r4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point")
         .set("uni1", 19, 32, 45, 58, 71, 84, 97, 110, 123, 136, 149, 162, 175);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "flap_w");
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("fil2", 2, 4, 5, 6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30, 31, 32, 34, 35, 36, 37, 39, 40, 41, 42, 44, 45, 46, 47, 49, 50, 51, 52, 54, 55, 56, 57, 59, 60, 61, 62, 64, 65, 66, 67);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("acoustic_microfluidic_pump_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
