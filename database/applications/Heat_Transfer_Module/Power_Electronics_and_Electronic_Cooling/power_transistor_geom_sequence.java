/*
 * power_transistor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:28 by COMSOL 6.3.0.290. */
public class power_transistor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{50, 50, 1.5});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-44, -25, -12});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", -10.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{34, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{-44, 10.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 6.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new int[]{-10, 6});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", -45);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", 1.75);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("pos", new double[]{-2.5, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").setIndex("layer", 0.85, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tan1", "Tangent");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tan1").selection("edge").set("c1", 9);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tan1").selection("edge2").set("c2", 11);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("tan1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tan2", "Tangent");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tan2").selection("edge").set("c1", 8);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tan2").selection("edge2").set("c2", 7);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("tan2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ccur1").selection("input")
         .set("c1", "c2", "r1", "tan1", "tan2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ccur1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("ccur1", 5, 7, 8, 10, 27, 30, 32);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input").set("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{40.5, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{-44, -1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("r", 1.75);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("pos", new double[]{-2.5, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input").set("c3", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{1, 1, 9});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{-3, 4.5, -14.05});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new double[]{1, 1.75, 6});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{-3, 4.125, -4.25});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("hex1", "Hexahedron");
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -3, 0, 0);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.5, 1, 0);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -5.05, 2, 0);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -5.05, 2, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -5.05, 2, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -5.05, 2, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.25, 2, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.25, 2, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.25, 2, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.25, 2, 7);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.5, 1, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 5.5, 1, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 5.5, 1, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.125, 1, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.125, 1, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 5.875, 1, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 5.875, 1, 7);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -2, 0, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -2, 0, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -3, 0, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -3, 0, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -2, 0, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -2, 0, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -3, 0, 7);
    model.component("comp1").geom("geom1").run("hex1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk2", "blk3", "hex1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new double[]{2.5, 14, 12.5});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new int[]{-4, -7, 0});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new double[]{1.5, 14, 14});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new double[]{-1.5, -7, 0});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("blk6", "Block");
    model.component("comp1").geom("geom1").feature("blk6").set("size", new double[]{1.5, 12.5, 2});
    model.component("comp1").geom("geom1").feature("blk6").set("pos", new double[]{-1.5, -6.25, 14.75});
    model.component("comp1").geom("geom1").run("blk6");
    model.component("comp1").geom("geom1").create("blk7", "Block");
    model.component("comp1").geom("geom1").feature("blk7").set("size", new double[]{1.5, 14, 1.5});
    model.component("comp1").geom("geom1").feature("blk7").set("pos", new double[]{-1.5, -7, 17.5});
    model.component("comp1").geom("geom1").run("blk7");
    model.component("comp1").geom("geom1").create("hex2", "Hexahedron");
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -1.5, 0, 0);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -7, 1, 0);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14, 2, 0);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -7, 1, 1);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14, 2, 1);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 7, 1, 2);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14, 2, 2);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -1.5, 0, 3);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 7, 1, 3);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14, 2, 3);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -1.5, 0, 4);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -6.25, 1, 4);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14.75, 2, 4);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -6.25, 1, 5);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14.75, 2, 5);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 0, 0, 6);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 6.25, 1, 6);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14.75, 2, 6);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", -1.5, 0, 7);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 6.25, 1, 7);
    model.component("comp1").geom("geom1").feature("hex2").setIndex("p", 14.75, 2, 7);
    model.component("comp1").geom("geom1").run("hex2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("hex2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new double[]{0, 0, 15.75});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input")
         .set("blk5", "blk6", "blk7", "hex2", "mir1");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 1.65);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 1.5);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-1.5, 0, 15.75});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").label("\u6676\u4f53\u7ba1\u82af\u7247");
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("dif1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq1").set("size", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq1").set("pos", new double[]{-1.5, 0});
    model.component("comp1").geom("geom1").feature("wp2").set("selresult", true);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", -2.5);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("x", "5.5 5.5 5.9 5.9 6.75");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .set("y", "-9.7 -12 -12 -10.5 -10.5");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("qb1").setIndex("p", 6.75, 0, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("qb1").setIndex("p", 5.5, 0, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("qb1").setIndex("p", 5.5, 0, 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("qb1").setIndex("p", -10.5, 1, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("qb1").setIndex("p", -10.3, 1, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("qb1").setIndex("p", -9.7, 1, 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("qb1")
         .set("w", new String[]{"1", "0.5/sqrt(2)", "1"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("qb1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("csol1").selection("input")
         .set("pol1", "qb1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev1").set("pos3", new double[]{-2.5, 5, -5.05});
    model.component("comp1").geom("geom1").feature("rev1").set("axis3", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("rev1", "uni1");
    model.component("comp1").geom("geom1").run("uni3");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("uni3");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 3, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, -5, 0});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("cmd1").selection("input")
         .set("fin", 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65);
    model.component("comp1").geom("geom1").run("cmd1");
    model.component("comp1").geom("geom1").create("cmd2", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd2").selection("input")
         .set("cmd1", 5, 6, 7, 9, 10, 11, 12, 13, 14);

    model.title(null);

    model.description("");

    model.label("power_transistor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
