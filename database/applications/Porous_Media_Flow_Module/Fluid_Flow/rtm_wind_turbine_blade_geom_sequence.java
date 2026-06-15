/*
 * rtm_wind_turbine_blade_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:12 by COMSOL 6.3.0.290. */
public class rtm_wind_turbine_blade_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Fluid_Flow");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("v_w", "0.55[m]", "\u901a\u98ce\u53e3\u5bbd\u5ea6");
    model.param().set("L", "5.65[m]", "\u524d\u7f18\u957f\u5ea6");
    model.param().set("xpos_ig1", "1.65[m]", "\u6ce8\u5c04\u53e3 1 \u5728 x \u65b9\u5411\u7684\u4f4d\u7f6e");
    model.param()
         .set("dist_ig2", "0.9[m]", "\u6ce8\u5c04\u53e3 2 \u5230 Ig_1 \u5728 x \u65b9\u5411\u7684\u8ddd\u79bb");
    model.param()
         .set("dist_ig3", "1.1[m]", "\u6ce8\u5c04\u53e3 3 \u5230 Ig_2 \u5728 x \u65b9\u5411\u7684\u8ddd\u79bb");
    model.param().set("xpos_v1", "2.1[m]", "\u901a\u98ce\u53e3 1 \u5728 x \u65b9\u5411\u7684\u4f4d\u7f6e");
    model.param()
         .set("dist_v2", "0.8[m]", "\u901a\u98ce\u53e3 2 \u5230 v1_x \u5728 x \u65b9\u5411\u7684\u8ddd\u79bb");
    model.param().set("thickness", "1[cm]", "\u539a\u5ea6");
    model.param().set("d_i", "2[cm]", "\u6d47\u53e3\u76f4\u5f84");
    model.param().set("l_vent", "3.5[cm]", "\u901a\u98ce\u53e3\u957f\u5ea6");
    model.param().set("xpos_v3", "1.65[m]");
    model.param().set("dist_v4", "0.9[m]");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"L", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("sizeconstr", true, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"L", "v_w*3/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("sizeconstr", true, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"L/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("posconstr", true, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("posconstr", true, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"dist_v2", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("sizeconstr", true, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"dist_v2", "v_w*3/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("sizeconstr", true, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new double[]{1.7, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("posconstr", true, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dist1").selection("entity1").set("r1", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dist1").selection("entity2").set("r2", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dist1").set("distance", "xpos_v1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dist1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 5.65, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", -0.1375, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 2.1, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", -0.3979813554751255, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("center", new double[]{1.7037300410132594, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .setIndex("center", 5.002629186638191, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("r", 5.415129186638195);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("angle1", -85.80344097129016);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("angle2", -108.33812137362412);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().compositeCurves("pol1", "ca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi1").selection("entity1").set("r1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi1").selection("entity2").set("cc1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("coi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi2").selection("entity1").set("r2", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi2").selection("entity2").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("coi2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi3").selection("entity1").set("r1", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi3").selection("entity2").set("cc1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("coi3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("edge1").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("vertex1").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("edge2").set("cc1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("vertex2").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("tanc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").selection("edge1").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").selection("edge2").set("r1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2")
         .set("helppoint1", new double[]{1.7037300410132594, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").setIndex("helppoint1", -0.4125, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2")
         .set("helppoint2", new double[]{1.6326980590820312, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2")
         .setIndex("helppoint2", -0.4078097939491272, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("tanc2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity1")
         .set("cc1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity2")
         .set("cc1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ydist1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ydist2", "YDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist2").selection("entity1")
         .set("cc1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist2").selection("entity2").set("r1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist2").set("distance", "v_w");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ydist2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("extract1").selection("input").set("r1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("extract1").set("propagatesel", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("extract1")
         .setAttribute("construction", "off");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("cro1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"0.55", "v_w/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").setIndex("sizeconstr", true, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new double[]{0.14, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").setIndex("posconstr", true, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi1").selection("entity1").set("r1", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi1").selection("entity2")
         .set("cro1.wp1.cc1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("coi1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi2").selection("entity1").set("r1", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi2").selection("entity2")
         .set("cro1.wp1.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("coi2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", -0.1374999999999097, 0, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", 0.22916666666665664, 1, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", 0.12963624321753023, 1, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ca1")
         .set("center", new double[]{0.275, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ca1").setIndex("centerconstr", true, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ca1").set("r", 0.1375);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ca1").set("angle1", 109.47122063449513);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ca1").set("angle2", 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ca1").set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature().compositeCurves("pol1", "ca1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi3").selection("entity1").set("cc1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi3").selection("entity2")
         .set("cro1.wp1.cc1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("coi3");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi4").selection("entity1").set("cc1", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi4").selection("entity2")
         .set("cro1.wp1.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("coi4");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("edge1").set("cc1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("vertex1").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("edge2").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("vertex2").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("tanc1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").selection("edge1").set("cc1", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").selection("edge2").set("r1", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.275, 0.1375});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.3486693501472473, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2")
         .setIndex("helppoint2", 0.14011028409004211, 1);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().duplicate("wp3", "wp2");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", "L");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"0.55", "v_w/4"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.2707791328430176, 0.1375});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("tanc2")
         .setIndex("helppoint1", 0.07022742927074432, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("tanc2").setIndex("helppoint2", 0.34375, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.34375, 0.06875});
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("loft1", "Loft");
    model.component("comp1").geom("geom1").feature("loft1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft1").selection("profile").set("wp2", "wp3");
    model.component("comp1").geom("geom1").feature("loft1").selection("guide").set("wp1");
    model.component("comp1").geom("geom1").run("loft1");
    model.component("comp1").geom("geom1").create("thi1", "Thicken");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").set("loft1");
    model.component("comp1").geom("geom1").feature("thi1").set("totalthick", "thickness");
    model.component("comp1").geom("geom1").run("thi1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"L", "v_w/2"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("pos", new String[]{"v_w/2", "0"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r2")
         .set("size", new String[]{"v_w", "0.85"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r2")
         .set("pos", new String[]{"-v_w/2", "-0.4"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r3")
         .set("size", new String[]{"2.8", "v_w/2"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r3")
         .set("pos", new String[]{"v_w/2+0.9", "0"});
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "v_w", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "-v_w", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").set("includeinput", false);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("thi1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("ext1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("thi1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("par1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("cyl1", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "d_i/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "14[cm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"xpos_ig1", "v_w/4", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "13[cm]");
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"xpos_ig1+dist_ig2", "v_w/4", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "12[cm]");
    model.component("comp1").geom("geom1").feature("cyl3")
         .set("pos", new String[]{"xpos_ig1+dist_ig2+dist_ig3", "v_w/4", "0"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("cyl1", "cyl2", "cyl3");
    model.component("comp1").geom("geom1").feature("par2").selection("tool").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("par2").setAttribute("construction", "off");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("extract1").selection("input").set("par2(1)", 9);
    model.component("comp1").geom("geom1").feature("extract1").selection("input").set("par2(2)", 9);
    model.component("comp1").geom("geom1").feature("extract1").selection("input").set("par2(3)", 9);
    model.component("comp1").geom("geom1").feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("extract1").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("extract1");
    model.component("comp1").geom("geom1").create("thi2", "Thicken");
    model.component("comp1").geom("geom1").feature("thi2").selection("input").named("extract1");
    model.component("comp1").geom("geom1").feature("thi2").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("thi2").set("upthick", "3[cm]");
    model.component("comp1").geom("geom1").feature("thi2").set("selresult", true);
    model.component("comp1").geom("geom1").run("thi2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("thi2(1)", 4);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("thi2(2)", 4);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("thi2(3)", 4);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"extract1"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 1);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickorigin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp5").selection("originvertex").set("par1", 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("size", new String[]{"l_vent", "thickness"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("pos", new String[]{"xpos_v1", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r2")
         .set("pos", new String[]{"xpos_v1+dist_v2", "0"});
    model.component("comp1").geom("geom1").feature("wp5").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp5").set("selresultshow", "edg");
    model.component("comp1").geom("geom1").feature("wp5").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp6").set("quicky", "-v_w");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("proj1").set("project", "bnd");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("proj1").selection("input")
         .set("par1", 8, 22);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("proj1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("size", new String[]{"l_vent", "2*thickness"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("pos", new String[]{"xpos_v3-l_vent/2", "0"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").setIndex("pos", "-thickness", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r2")
         .set("pos", new String[]{"xpos_v3-l_vent/2+dist_v4", "-thickness"});
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp6");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp6");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "v_w/2", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("par3", "Partition");
    model.component("comp1").geom("geom1").feature("par3").selection("input").set("ext2", "thi2", "wp5");
    model.component("comp1").geom("geom1").feature("par3").selection("tool").set("par1");
    model.component("comp1").geom("geom1").feature("par3").set("keeptool", true);
    model.component("comp1").geom("geom1").run("par3");
    model.component("comp1").geom("geom1").create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("extract2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("extract2").set("selresultshow", "edg");
    model.component("comp1").geom("geom1").feature("extract2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("extract2").selection("input").set("par3(1)", 6, 19);
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("cyl1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("cyl2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("cyl3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("par2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("extract1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("thi2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("adjsel1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp5");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp6");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ext2");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("par3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("extract2");
    model.component("comp1").geom("geom1").run("extract2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").nodeGroup("grp3").remove("unisel1", false);
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"adjsel1", "wp5", "extract2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").add("par1", 2, 9, 15, 20, 26);
    model.component("comp1").geom("geom1").feature("sel2").set("selshow", false);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"sel2"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"extract1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").add("par1", 1, 4, 28, 29, 30, 31);
    model.component("comp1").geom("geom1").feature("sel3").set("selshow", false);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"wp5", "extract2", "sel3"});
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("rtm_wind_turbine_blade_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
