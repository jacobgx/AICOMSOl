/*
 * radial_pump_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:16 by COMSOL 6.3.0.290. */
public class radial_pump_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R_in", "51.63[mm]", "\u6d41\u5165\u7ba1\u534a\u5f84");
    model.param().set("R_LE", "65[mm]", "\u53f6\u8f6e\u53f6\u7247\u524d\u7f18\u7684\u5f84\u5411\u4f4d\u7f6e");
    model.param().set("R_TE", "139[mm]", "\u53f6\u8f6e\u53f6\u7247\u540e\u7f18\u7684\u5f84\u5411\u4f4d\u7f6e");
    model.param().set("W_b", "23[mm]", "\u53f6\u8f6e\u53f6\u7247\u5bbd\u5ea6");
    model.param().set("R_op", "230[mm]", "\u6d41\u51fa\u7ba1\u7684\u5f84\u5411\u4f4d\u7f6e");
    model.param().set("d_op", "22[mm]", "\u6d41\u51fa\u7ba1\u76f4\u5f84");
    model.param().set("r_b", "113.52[mm]", "\u53f6\u8f6e\u53f6\u7247\u8f6e\u5ed3\u7684\u5c40\u90e8\u534a\u5f84");
    model.param().set("r_LE", "3[mm]", "\u53f6\u8f6e\u53f6\u7247\u524d\u7f18\u7684\u5c40\u90e8\u534a\u5f84");
    model.param().set("r_p", "5.25[mm]", "\u53f6\u8f6e\u6d41\u5165\u5706\u89d2\u7684\u5c40\u90e8\u534a\u5f84");
    model.param().set("T_b", "6[mm]", "\u53f6\u8f6e\u53f6\u7247\u539a\u5ea6");
    model.param().set("delta_theta", "91[deg]", "\u53f6\u7247\u5305\u89d2");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("specify", "endsr");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point1", new String[]{"R_LE*cos(0[deg])", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .setIndex("point1", "R_LE*sin(0[deg])", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point2", new String[]{"R_TE*cos(delta_theta-0[deg])", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .setIndex("point2", "R_TE*sin(delta_theta-0[deg])", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("r", "r_b");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").selection("edge").set("ca1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").setIndex("param", 0.35, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").setIndex("param", 0.85, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pare1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("pare1", 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").selection("input").set("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("totalthick", "T_b");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("ends", "circular");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("thi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").selection("input").set("thi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("displx", -0.05);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("disply", 0.005);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mov1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"(R_TE+7.5[mm])*cos(60[deg])", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .setIndex("coord2", "(R_TE+7.5[mm])*sin(60[deg])", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", 60);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("specify", "endsr");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2")
         .set("point1", new String[]{"(R_TE+7.5[mm])*cos(120[deg])", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2")
         .setIndex("point1", "(R_TE+7.5[mm])*sin(120[deg])", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2")
         .set("point2", new String[]{"(R_TE+7.5[mm])*cos(60[deg])", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2")
         .setIndex("point2", "(R_TE+7.5[mm])*sin(60[deg])", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("r", "(R_TE+7.5[mm])");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ca2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("ca2", "ls1", "rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("mov1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "W_b", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"R_in+r_p", "r_p"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new String[]{"0", "W_b"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "r_p");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"R_in+r_p", "W_b+r_p"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("rot", -180);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("size", new String[]{"R_in", "W_b"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("pos", new String[]{"0", "W_b+r_p"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle1", 60);
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 120);
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev1").set("axis3", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"R_op+d_op+W_b+5[mm]-(R_TE+7.5[mm])", "1"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .setIndex("size", "W_b+5[mm]+(W_b/3)+W_b", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"R_TE+7.5[mm]", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2")
         .set("size", new String[]{"R_op-d_op-(R_TE+7.5[mm])", "1"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2")
         .setIndex("size", "(W_b/3)+W_b+5[mm]", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2")
         .set("pos", new String[]{"R_TE+7.5[mm]", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2").setIndex("pos", "W_b", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r3")
         .set("size", new String[]{"2*d_op", "W_b/3+5[mm]"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r3")
         .set("pos", new String[]{"R_op-d_op", "W_b"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dif1").selection("input2").set("r2", "r3");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("rev2").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev2").set("angle1", -30);
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", 30);
    model.component("comp1").geom("geom1").feature("rev2").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev2").set("axis3", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").run("rev2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "d_op/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "d_op");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"R_op*cos(75[deg])", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", "R_op*sin(75[deg])", 1);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", "W_b+5[mm]+(W_b/3)+W_b", 2);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "d_op/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "d_op");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"R_op*cos(105[deg])", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", "R_op*sin(105[deg])", 1);
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", "W_b+5[mm]+(W_b/3)+W_b", 2);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("rev1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "spl1(1)");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 9);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 12, 39, 52);
    model.component("comp1").geom("geom1").run("ige1");

    model.title(null);

    model.description("");

    model.label("radial_pump_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
