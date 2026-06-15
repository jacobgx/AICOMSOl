/*
 * cascade_impactor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:53 by COMSOL 6.3.0.290. */
public class cascade_impactor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Rcol35", "8.225[cm]/2", "\u5e95\u5c42\u4e09\u9636\u6bb5\u7684\u6536\u96c6\u677f\u534a\u5f84");
    model.param().set("L0", "4.1[mm]", "0 \u7ea7\u55b7\u5634\u957f\u5ea6");
    model.param().set("L1", "4.1[mm]", "1 \u7ea7\u55b7\u5634\u957f\u5ea6");
    model.param().set("L24", "1.5[mm]", "2-4 \u7ea7\u55b7\u5634\u957f\u5ea6");
    model.param().set("R0", "1.275[mm]", "0 \u7ea7\u55b7\u5634\u534a\u5f84");
    model.param().set("R1", "0.94[mm]", "1 \u7ea7\u55b7\u5634\u534a\u5f84");
    model.param().set("R2", "0.457[mm]", "2 \u7ea7\u55b7\u5634\u534a\u5f84");
    model.param().set("R3", "0.3555[mm]", "3 \u7ea7\u55b7\u5634\u534a\u5f84");
    model.param().set("R4", "0.2665[mm]", "4 \u7ea7\u55b7\u5634\u534a\u5f84");
    model.param().set("Gap0", "2.02[mm]");
    model.param().set("Gap1", "2.15[mm]");
    model.param().set("Ri", "10.2[mm]", "\u5165\u53e3\u534a\u5f84");
    model.param().set("Ti", "22[mm]", "\u5165\u53e3\u6df1\u5ea6");
    model.param().set("Rpsb", "40[mm]", "\u9884\u5206\u79bb\u5668\u57fa\u5ea7\u534a\u5f84");
    model.param().set("Tpsb", "6.5[mm]", "\u9884\u5206\u79bb\u5668\u57fa\u5ea7\u539a\u5ea6");
    model.param().set("Rch", "44[mm]", "\u6536\u96c6\u677f\u652f\u67b6\u534a\u5f84");
    model.param().set("Tch", "5.2[mm]", "\u6536\u96c6\u677f\u652f\u67b6\u539a\u5ea6");
    model.param().set("Rc", "39[mm]", "\u8154\u534a\u5f84");
    model.param().set("Tc", "15.8[mm]", "\u8154\u539a\u5ea6");
    model.param()
         .set("Rcol12", "Rcol35-10[mm]", "\u9876\u5c42\u4e24\u9636\u6bb5\u7684\u6536\u96c6\u677f\u534a\u5f84");
    model.param().set("Tcol", "1[mm]", "\u6536\u96c6\u677f\u539a\u5ea6");
    model.param().set("Re", "10[mm]", "\u51fa\u53e3\u534a\u5f84");
    model.param().set("Te", "22[mm]", "\u51fa\u53e3\u539a\u5ea6");
    model.param().set("Tps", "26.8[mm]");
    model.param().set("D12", "6.5[mm]", "\u524d\u4e24\u9636\u6bb5\u7684\u55b7\u5634\u95f4\u8ddd");
    model.param().set("D35", "4.15[mm]", "\u5e95\u5c42\u4e09\u9636\u6bb5\u7684\u55b7\u5634\u95f4\u8ddd");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"Ri", "Ti"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"Rpsb", "Tpsb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new String[]{"0", "-Tps"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "Ri", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "0.925*Rpsb", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "Tpsb-Tps", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "Tpsb-Tps", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"Rch", "Tch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"0", "-Tps-L0-Tch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new String[]{"Rc", "Tc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"0", "-Tps-L0-Tch-Tc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"Rcol12", "Tcol"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"Rcol35-Rcol12", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .setIndex("pos", "-Tps-L0-Gap0-Tcol", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input")
         .set("dif1", "r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{1, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "-L1-Tch-Tc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("size", new String[]{"Rch", "Tch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new String[]{"0", "-Tps-L0-L1-L24-3*Tch-2*Tc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("size", new String[]{"Rc", "Tc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("pos", new String[]{"0", "-Tps-L0-L1-L24-3*Tch-3*Tc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("size", new String[]{"Rcol35", "Tcol"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("pos", new String[]{"0", "-Tps-L0-L1-L24-2*Tch-2*Tc-Gap1-Tcol"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input").set("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input2").set("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").selection("input")
         .set("dif2", "r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("displ", new String[]{"0", "-L24-Tch-Tc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("size", new String[]{"Re", "Te"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9")
         .set("pos", new String[]{"0", "-Tps-L0-L1-3*L24-5*Tch-5*Tc-Te"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r9");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle1", -15);
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 15);
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u6240\u6709\u55b7\u5634");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u7ec6\u55b7\u5634");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R0");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L0");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"14[mm]", "0", "-Tps-L0"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{4, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"D12", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "R1");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L1");
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"14[mm]", "0", "-Tps-L0-Tch-Tc-L1"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{4, 1, 1});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"D12", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "R2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "L24");
    model.component("comp1").geom("geom1").feature("cyl3")
         .set("pos", new String[]{"5[mm]", "0", "-Tps-L0-L1-2*Tch-2*Tc-L24"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("cyl3");
    model.component("comp1").geom("geom1").feature("arr3").set("fullsize", new int[]{8, 1, 1});
    model.component("comp1").geom("geom1").feature("arr3").set("displ", new String[]{"D35", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").create("arr4", "Array");
    model.component("comp1").geom("geom1").feature("arr4").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("arr4").set("fullsize", new int[]{1, 1, 3});
    model.component("comp1").geom("geom1").feature("arr4").set("displ", new String[]{"0", "0", "-L24-Tch-Tc"});
    model.component("comp1").geom("geom1").feature("arr4").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("arr4");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "-7.5 7.5");
    model.component("comp1").geom("geom1").feature("rot1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("arr1", "arr2", "rev1", "rot1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("cascade_impactor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
