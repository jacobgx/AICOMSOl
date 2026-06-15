/*
 * continuous_mixer_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:14 by COMSOL 6.3.0.290. */
public class continuous_mixer_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("impellerTilt", "30");
    model.param().descr("impellerTilt", "\u53f6\u8f6e\u8f74\u503e\u89d2");
    model.param().set("r_in", "0.08[m]");
    model.param().descr("r_in", "\u5165\u53e3\u534a\u5f84");
    model.param().set("Vol", "3.38[m^3]");
    model.param().descr("Vol", "\u91dc\u4f53\u79ef");

    model.geom().load(new String[]{"part1"}, "Mixer_Module\\Tanks\\flat_bottom_tank.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_ba", "0[1]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_ba", "0.15[m]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_ta", "1.5[m]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "h_ta", "2.0[m]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rf_ta", "0.05[m]");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.geom().load(new String[]{"part2"}, "Mixer_Module\\Shafts\\impeller_shaft.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hp_im", "0.5[m]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_is", "2[m]");
    model.geom()
         .load(new String[]{"part3"}, "Mixer_Module\\Impellers,_Axial\\pitched_blade_impeller_constant_pitch.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "n_ib", "5[1]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_im", "0.8[m]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "hp_im", "0.5[m]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "pa_cs_im", 1);
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("pi2", "pi3");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "impellerTilt");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new double[]{0, 0, 0.5});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", -0.175);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("mov1(3)", "uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("mov1(1)", "mov1(2)");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_in");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "r_in*8");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-0.9, 0.3, 1.5});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", -0.35);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{0.2, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{0.25, -0.25});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{0.25, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new double[]{0.25, -0.25});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 0.03);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("fil1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("pos", new double[]{0.5, -0.25});
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 13, 27, 28, 29, 60, 85, 88, 90, 96, 97, 99, 101, 105, 109, 115, 116, 120, 121, 147, 148, 171, 180, 188, 189, 197, 198);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("igf1", "IgnoreFaces");
    model.component("comp1").geom("geom1").feature("igf1").selection("input").set("ige1", 51);
    model.component("comp1").geom("geom1").run("igf1");

    model.title(null);

    model.description("");

    model.label("continuous_mixer_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
