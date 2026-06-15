/*
 * ship_hull_geometry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:47 by COMSOL 6.3.0.290. */
public class ship_hull_geometry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Design_Module\\Tutorial_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").label("Side View");
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp1").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp1").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("sizeconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"35[m]", "1.8185[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "-1.8185[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1").set("p", new double[]{6, -1.8185});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi1").selection("entity1")
         .set("r1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi1").selection("entity2")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist1").set("distance", "6[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist1")
         .set("parname", "geom1.wp1.xdist1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist1").selection("entity1")
         .set("r1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist1").selection("entity2")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").set("type", "solid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-0.5375144244678021, -1.731058057229189}, {0, -1.3685}, {0, -1.8185}, {-0.7, -1.9583738023894661}});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.3138372630795028, -2.0626730862512055});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").feature("ca1").set("r", 0.4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").feature("ca1")
         .set("angle1", 164.8855371196874);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").feature("ca1").set("angle2", 124);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi2").selection("entity2")
         .set("r1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi3").selection("entity1")
         .set("r1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi3").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").set("distance", "0.45[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ydist1").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist2").set("distance", "0.7[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist2").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist2").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang1").set("angle", "101.3[deg]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang1").selection("edge1").set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang1").selection("edge2").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ang2", "Angle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang2").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang2").set("angle", "56[deg]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang2").selection("edge1").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang2").selection("edge2").set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rad1").set("radius", "0.4[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rad1").selection("edge").set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1")
         .set("helppoint1", new double[]{-0.537514424467802, -1.7310580572291892});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cc2", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("pol1")
         .set("table", new double[][]{{24, -2.3}, {28.738522111376312, -2.3}});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("ca1")
         .set("center", new double[]{28.738522111376312, 6.7});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("ca1").set("r", 9);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("ca1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("ca1").set("angle2", -58);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("pol2")
         .set("table", new double[][]{{33.50779548947516, -0.9324328654078338}, {35, 0}, {0, 0}, {0, -1.8185}});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("cb1")
         .set("p", new double[][]{{0, 3.6, 12, 24}, {-1.8185, -1.4391159335306896, -2.3, -2.3}});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cc2").feature("cb1")
         .set("w", new double[]{0.5, 1, 2, 0.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi4").selection("entity1")
         .set("r1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi4").selection("entity2")
         .set("cc2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi5").selection("entity1")
         .set("r1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi5").selection("entity2")
         .set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi6").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi6").selection("entity1")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi6").selection("entity2")
         .set("r1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi7")
         .set("helppoint1", new double[]{6, -1.8185});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi7").selection("entity1")
         .set("cc2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("coi7").selection("entity2")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dist1").set("distance", "2.3[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dist1").selection("entity1")
         .set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dist1").selection("entity2")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ang3", "Angle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang3").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang3").set("angle", "32[deg]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang3").selection("edge1").set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ang3").selection("edge2").set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("xdist3", "XDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist3").set("distance", "24[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist3").selection("entity1")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist3").selection("entity2")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rad2", "Radius");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rad2").set("radius", "9[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rad2").selection("edge").set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("xdist4", "XDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist4")
         .set("distance", "geom1.wp1.xdist1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist4").selection("entity1")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist4").selection("entity2")
         .set("cc2(1)", 9);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("xdist5", "XDistance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist5")
         .set("distance", "geom1.wp1.xdist1*0.4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist5").selection("entity1")
         .set("cc2(1)", 8);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("xdist5").selection("entity2")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2")
         .set("helppoint2", new double[]{33.50779548947516, -0.9324328654078338});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").selection("edge1")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").selection("vertex1")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").selection("edge2")
         .set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc2").selection("vertex2")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc3")
         .set("helppoint1", new double[]{28.738522111376312, -2.3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc3").selection("edge1")
         .set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc3").selection("vertex1")
         .set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc3").selection("edge2")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc3").selection("vertex2")
         .set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("tanc4", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc4").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc4").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc4")
         .set("helppoint2", new double[]{24, -2.3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc4").selection("edge1")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc4").selection("vertex1")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc4").selection("edge2")
         .set("cc2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("tanc4").selection("vertex2")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2constr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"0", "-0.7[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex1").set("r1(1)", 3);
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").label("Transom View");
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp2").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp2").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cro1").selection("input")
         .set("wp1.cc2", "wp1.ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("table", new double[][]{{0, 0}, {0, -3}, {-1, -3}, {-2.091910702798606, 0}});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("hor1").selection("edge").set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ver1").selection("edge").set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi1").selection("entity2")
         .set("cro1.wp1.cc2", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi2").selection("entity1")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist1").set("distance", "1[m]");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist1").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ang1").set("angle", "110[deg]");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ang1").selection("edge1")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ang1").selection("edge2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("dist2", "Distance");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist2").set("distance", "3[m]");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist2").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dist2").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").set("type", "solid");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("pol1").label("Polygon 2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-1.8185, 0}, {0, 0}, {0, -3}, {-0.7, -3}});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.7, -2.571555597977365});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca1")
         .set("r", 0.42844440202263545);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca1").set("angle2", 200);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("pol2").label("Polygon 3");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{-1.1026060429977012, -2.7180922137642267}, {-1.5615779801362595, -1.457077180312688}});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{2.44170867117546, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca2")
         .set("r", 4.26020867117546);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca2").set("angle1", -160);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca2").set("angle2", -180);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi3").selection("entity2")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi4").selection("entity2")
         .set("cc1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi5").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi5").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi6").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi7").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi7").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi8", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi8").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi8").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi8").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi8").selection("entity2")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("coi9", "Coincident");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi9").selection("entity1")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi9").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("coi9").selection("entity2")
         .set("cro1.wp1.ls1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1")
         .set("helppoint1", new double[]{-1.5615779801362604, -1.457077180312686});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("tanc2", "TangentConstraint");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2")
         .set("helppoint1", new double[]{-1.1026060429977016, -2.718092213764227});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc3")
         .set("helppoint1", new double[]{-0.7, -3});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc3").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc3").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("tanc3").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").label("Deck View");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp3").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp3").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp3").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-3, 0}, {-3, 24}});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("ca1")
         .set("center", new int[]{17, 24});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("ca1").set("r", 20);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("ca1").set("angle1", 180);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("ca1")
         .set("angle2", 156.07963400869778);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{-1.2821977688708373, 32.10933072083598}, {0, 35}});
    model.component("comp1").geom("geom1").feature("wp3").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi1").selection("entity2")
         .set("cro1.wp1.cc2", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("coi2").selection("entity2")
         .set("cro1.wp2", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("para1", "Parallel");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("para1").selection("edge").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("para1").selection("edge")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rad1").set("radius", "20[m]");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rad1").selection("edge").set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dist1").set("distance", "24[m]");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dist1").selection("entity1")
         .set("cro1.wp2", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("tanc1")
         .set("helppoint2", new double[]{-1.2821977688708373, 32.109330720835985});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").label("Water Line");
    model.component("comp1").geom("geom1").feature("mov1").set("specify", "pos");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("wp1.ls1");
    model.component("comp1").geom("geom1").feature("mov1").selection("oldposvertex").set("wp1.ls1", 2);
    model.component("comp1").geom("geom1").feature("mov1").selection("newposvertices").set("wp1.ls1", 2);
    model.component("comp1").geom("geom1").feature("mov1").selection("newposvertices").set("wp2.cc1", 4);
    model.component("comp1").geom("geom1").feature("mov1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").label("Profile\u00a01 - Hull Loft");
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp4").set("quicky", "1[m]");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp4").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp4").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("proj1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-1.7474471500632847, 0}, {0, 0}, {0, -3}, {-0.68, -3}});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.68, -2.6224884638029757});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca1")
         .set("r", 0.37751153619702404);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca1").set("angle2", -160);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{-1.0347448048258958, -2.7516050135201753}, {-1.476063943599872, -1.53909064496551}});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{2.7525528499367153, 0});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca2").set("r", 4.5);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca2").set("angle1", -160);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca2").set("angle2", 180);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi1").selection("entity2")
         .set("proj1.wp2", 3);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi2").selection("entity2")
         .set("proj1.wp2", 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi3").selection("entity2")
         .set("proj1.wp2", 6);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi4").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi5").selection("entity1")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi5").selection("entity2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("coi6").selection("entity2")
         .set("cro1.mov1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("rad1").set("radius", "4.5[m]");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("rad1").selection("edge").set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("para1", "Parallel");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("para1").selection("edge").set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("para1").selection("edge")
         .set("proj1.wp2", 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc1")
         .set("helppoint1", new double[]{-1.476063943599872, -1.53909064496551});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc2")
         .set("helppoint2", new double[]{-1.0347448048258956, -2.751605013520175});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc3")
         .set("helppoint1", new double[]{-0.68, -3});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc3").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc3").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("tanc3").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").label("Profile\u00a02 - Hull Loft");
    model.component("comp1").geom("geom1").feature("wp5").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp5").set("quicky", "6[m]");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp5").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp5").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp5").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("proj1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-1.8185, 0}, {0, 0}, {0, -3}, {-0.58, -3}});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.58, -2.6686902571683984});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca1")
         .set("r", 0.3313097428316018);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca1").set("angle2", 204.5);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{-0.8814790346408242, -2.80608216874684}, {-1.507489474758722, -1.4324276282896287}});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{1.6356860849105817, 0});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca2")
         .set("r", 3.4541860849105817);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca2").set("angle1", -155.5);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca2").set("angle2", 180);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi1").selection("entity2")
         .set("proj1.wp2", 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi2").selection("entity2")
         .set("proj1.wp2", 5);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi3").selection("entity2")
         .set("proj1.wp2", 6);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi4").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi5").selection("entity1")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi5").selection("entity2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi6").selection("entity2")
         .set("cro1.mov1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc1")
         .set("helppoint1", new double[]{-1.5074894747587224, -1.4324276282896284});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc2")
         .set("helppoint2", new double[]{-0.8814790346408244, -2.80608216874684});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc3")
         .set("helppoint1", new double[]{-0.58, -3});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc3").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc3").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("tanc3").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi7").selection("entity1")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi7").selection("entity2")
         .set("proj1.wp2", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang1").set("angle", "114.5[deg]");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang1").selection("edge1").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang1").selection("edge2").set("cc1(1)", 5);
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").label("Profile\u00a03 - Hull Loft");
    model.component("comp1").geom("geom1").feature("wp6").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp6").set("quicky", "16.6[m]");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp6").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp6").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp6").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("proj1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-2.271965426653453, 0}, {0, 0}, {0, -3}, {-0.368, -3}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.368, -2.5125774499887155});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1")
         .set("r", 0.48742255001128465);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1").set("angle2", 210.7);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{-0.7871113868547432, -2.7614275809273927}, {-2.138893250995909, -0.48476744951317136}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{-1.3224518157387612, 0});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2")
         .set("r", 0.9495136109146914);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2").set("angle1", -149.3);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2").set("angle2", 180);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi1").selection("entity2")
         .set("proj1.wp2", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi2", "Coincident");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi2").selection("entity2")
         .set("proj1.wp2", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi3").selection("entity2")
         .set("proj1.wp2", 6);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi4").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity1")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi6").selection("entity2")
         .set("cro1.mov1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1")
         .set("helppoint1", new double[]{-2.1388932509959084, -0.48476744951317197});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2")
         .set("helppoint2", new double[]{-0.7871113868547431, -2.7614275809273927});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3")
         .set("helppoint1", new double[]{-0.368, -3});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi7").selection("entity1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi7").selection("entity2")
         .set("proj1.wp2", 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang1").set("angle", "120.7[deg]");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang1").selection("edge1").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang1").selection("edge2").set("cc1(1)", 5);
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").label("Profile\u00a04 - Hull Loft");
    model.component("comp1").geom("geom1").feature("wp7").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp7").set("quicky", "24[m]");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp7").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp7").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp7").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("proj1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-2.3, 0}, {0, 0}, {0, -3}, {-0.22, -3}});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.22, -2.473819984128981});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1")
         .set("r", 0.5261800158710188);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1").set("angle2", 212);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{-0.666225960694975, -2.7526529109935676}, {-2.226835158299319, -0.25515612572828833}});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{-1.8185, 0});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca2").set("r", 0.4815);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca2").set("angle1", -148);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca2").set("angle2", 180);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi1").selection("entity2")
         .set("proj1.wp2", 3);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi2").selection("entity2")
         .set("proj1.wp2", 5);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi3").selection("entity2")
         .set("proj1.wp2", 6);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi4").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi5").selection("entity1")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi5").selection("entity2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi6").selection("entity2")
         .set("cro1.mov1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc1")
         .set("helppoint1", new double[]{-2.226835158299319, -0.25515612572828844});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc2")
         .set("helppoint2", new double[]{-0.6662259606949751, -2.7526529109935676});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc3")
         .set("helppoint1", new double[]{-0.22, -3});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc3").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc3").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("tanc3").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi7").selection("entity1")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("coi7").selection("entity2")
         .set("proj1.wp2", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("ang1").set("angle", "122[deg]");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("ang1").selection("edge1").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("ang1").selection("edge2").set("cc1(1)", 5);
    model.component("comp1").geom("geom1").create("wp8", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp8").label("Profile\u00a05 - Hull Loft");
    model.component("comp1").geom("geom1").feature("wp8").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp8").set("quicky", "28.7[m]");
    model.component("comp1").geom("geom1").feature("wp8").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp8").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp8").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp8").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp8").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp8").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-2.3, 0}, {0, 0}, {0, -2.439907407186896}, {-0.126, -2.4013853413231243}});
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{0.10789736377818951, -1.6363415365526763});
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca1")
         .set("r", 0.8000000000000206);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca1").set("angle1", -107);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca1")
         .set("angle2", -141.14360985023896);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{-62.81275038743491, -52.327880145750214});
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca2").set("r", 80);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca2")
         .set("angle1", 38.85639014976072);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("cc1").feature("ca2")
         .set("angle2", 40.851328886308224);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi1").selection("entity2")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi2").selection("entity2")
         .set("cro1.wp1.cc2", 2);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("coi3").selection("entity2")
         .set("cro1.wp3", 1);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("xdist1").selection("entity1")
         .set("cro1.mov1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("ang1").set("angle", "73[deg]");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("ang1").selection("edge1").set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("ang1").selection("edge2").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc1")
         .set("helppoint2", new double[]{-0.126, -2.4013853413231048});
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2")
         .set("helppoint1", new double[]{-0.5150793466298623, -2.1382379584259166});
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2")
         .set("helppoint2", new double[]{-0.5150793466298822, -2.1382379584259326});
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("rad1").set("radius", "0.8[m]");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("rad1").selection("edge").set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("rad2", "Radius");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("rad2").set("radius", "80[m]");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("rad2").selection("edge").set("cc1(1)", 5);
    model.component("comp1").geom("geom1").create("wp9", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp9").label("Profile\u00a06 - Hull Loft");
    model.component("comp1").geom("geom1").feature("wp9").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp9").set("quicky", "32[m]");
    model.component("comp1").geom("geom1").feature("wp9").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp9").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp9").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp9").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp9").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp9").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-1.6882514257751469, 0}, {0, 0}, {0, -1.3303027798233586}, {-0.06, -1.330302779823344}});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.06, -1.0303027798233586});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca1")
         .set("r", 0.2999999999999854);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca1")
         .set("angle2", -131.96105261505176);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{-53.75061091558049, -60.741342793473656});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca2").set("r", 80);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca2")
         .set("angle1", 48.03894738494826);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("ca2")
         .set("angle2", 49.39960183682903);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity2")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity2")
         .set("cro1.wp1.cc2", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi3").selection("entity2")
         .set("cro1.wp3", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("xdist1").selection("entity1")
         .set("cro1.mov1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").set("angle", "90[deg]");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").selection("edge1").set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").selection("edge2").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1")
         .set("helppoint2", new double[]{-0.06, -1.3303027798233584});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2")
         .set("helppoint1", new double[]{-0.2605875874803759, -1.2533826304347542});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2")
         .set("helppoint2", new double[]{-0.2605875874803658, -1.2533826304347429});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("rad1").set("radius", "0.3[m]");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("rad1").selection("edge").set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("rad2", "Radius");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("rad2").set("radius", "80[m]");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("rad2").selection("edge").set("cc1(1)", 5);
    model.component("comp1").geom("geom1").run("wp9");
    model.component("comp1").geom("geom1").create("wp10", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp10").label("Profile\u00a07 - Hull Loft");
    model.component("comp1").geom("geom1").feature("wp10").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp10").set("quicky", "33.5[m]");
    model.component("comp1").geom("geom1").feature("wp10").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp10").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp10").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp10").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp10").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp10").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-0.9372984959439495, 0}, {0, 0}, {0, -0.6653464881539379}, {-0.03, -0.6706362975751952}});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.05083778132003173, -0.5524593672137269});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1").set("r", 0.12);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1").set("angle1", -80);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("angle2", -134.3793337191147);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{-3.631794394622652, -4.2118513568267915});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca2").set("r", 5);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca2")
         .set("angle1", 45.62066628088549);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca2")
         .set("angle2", 57.391267358636334);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity2")
         .set("cro1.wp1.cc2", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity2")
         .set("cro1.wp1.cc2", 2);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity2")
         .set("cro1.wp3", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("xdist1").selection("entity1")
         .set("cro1.mov1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").set("angle", "100[deg]");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1")
         .set("helppoint2", new double[]{-0.03, -0.6706362975751918});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2")
         .set("helppoint1", new double[]{-0.13476645194431205, -0.6382263669702828});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2")
         .set("helppoint2", new double[]{-0.1347664519443148, -0.6382263669702857});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("rad1").set("radius", "0.12[m]");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("rad1").selection("edge").set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("rad2", "Radius");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("rad2").set("radius", "5[m]");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("rad2").selection("edge").set("cc1(1)", 5);
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", "0.9", 0);
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("wp10.cc1", 4);
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("wp8.cc1", 4);
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("wp9.cc1", 4);
    model.component("comp1").geom("geom1").create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("extract1").label("Guide Curve - Hull Loft");
    model.component("comp1").geom("geom1").feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("extract1").selection("input").set("wp1.cc2", 3, 4, 5, 6);
    model.component("comp1").geom("geom1").create("loft1", "Loft");
    model.component("comp1").geom("geom1").feature("loft1").label("Hull\u00a0loft\u00a0");
    model.component("comp1").geom("geom1").feature("loft1").selection("profile")
         .set("pare1", "wp4", "wp5", "wp6", "wp7");
    model.component("comp1").geom("geom1").feature("loft1").selection("startprofile").init();
    model.component("comp1").geom("geom1").feature("loft1").selection("startprofile").set("wp2");
    model.component("comp1").geom("geom1").feature("loft1").selection("endprofile").init(0);
    model.component("comp1").geom("geom1").feature("loft1").selection("endprofile").set("extract1(1)", 5);
    model.component("comp1").geom("geom1").feature("loft1").selection("guide").set("extract1", "wp3");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").set("p", new String[]{"0", "-0.7[m]", "0"});
    model.component("comp1").geom("geom1").feature("pt1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp11", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp11").label("Profile - Stern Loft");
    model.component("comp1").geom("geom1").feature("wp11").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp11").set("quickx", "-2.5[m]");
    model.component("comp1").geom("geom1").feature("wp11").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp11").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp11").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp11").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp11").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp11").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").set("type", "solid");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-0.5467796951699426, -1.1007926783617346}, {0, -0.7319851171331018}, {0, -1.181985117133102}, {-0.684, -1.2599170732114275}});
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.32310253378164366, -1.4324077073837511});
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").feature("ca1").set("r", 0.4);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").feature("ca1")
         .set("angle1", 154.4545724527363);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").feature("ca1").set("angle2", 124);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity2")
         .set("cro1.loft1", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi2").selection("entity1")
         .set("cro1.loft1", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi2").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").set("distance", "0.45[m]");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("xdist1").set("distance", "0.684[m]");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").set("angle", "96.5[deg]");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("ang2", "Angle");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").set("angle", "56[deg]");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("rad1").set("radius", "0.4[m]");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("rad1").selection("edge").set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("tanc1")
         .set("helppoint1", new double[]{-0.5467796951699426, -1.1007926783617346});
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").create("wp12", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp12").label("Guide\u00a0Curve - Stern Loft");
    model.component("comp1").geom("geom1").feature("wp12").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp12").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp12").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp12").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp12").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp12").selection("vertex1").set("wp1.cc1", 1);
    model.component("comp1").geom("geom1").feature("wp12").selection("vertex2").set("wp11.cc1", 1);
    model.component("comp1").geom("geom1").feature("wp12").selection("vertex3").set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp12").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{0.9750514692018102, 3.4654400344555656});
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").feature("ca1").set("r", 3.6);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").feature("ca1")
         .set("angle1", -105.71471201118975);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").feature("ca1")
         .set("angle2", -84.7147120111897);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{1.3066651421476991, -0.11925414663092404}, {2.595784621753909, 0}});
    model.component("comp1").geom("geom1").feature("wp12").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("tanc1")
         .set("helppoint1", new double[]{1.3066651421476987, -0.11925414663092404});
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("rad1").set("radius", "3.6[m]");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("rad1").selection("edge").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity2")
         .set("cro1.wp1.cc1", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity2")
         .set("cro1.wp11", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang1").set("angle", "21[deg]");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").create("pare2", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare2").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare2").selection("edge").set("loft1(1)", 9);
    model.component("comp1").geom("geom1").feature("pare2").selection("vertexproj").set("wp11.cc1", 3);
    model.component("comp1").geom("geom1").create("loft2", "Loft");
    model.component("comp1").geom("geom1").feature("loft2").label("Stern\u00a0loft\u00a0");
    model.component("comp1").geom("geom1").feature("loft2").selection("profile").set("wp1.cc1", "wp11");
    model.component("comp1").geom("geom1").feature("loft2").selection("guide").set("wp12");
    model.component("comp1").geom("geom1").feature("loft2").selection("startguide").set("pare2(1)", 10, 12);
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "0.075[m]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "5[m]");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-1.5[m]", "1.9[m]", "-1.15[m]"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "0.15[m]");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "0.7[m]");
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"-1.5[m]", "1.85[m]", "-1.15[m]"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "11[deg]");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"-1.5[m]", "7[m]", "-1.15[m]"});
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").create("wp13", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp13").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp13").set("quickx", "-1.5[m]");
    model.component("comp1").geom("geom1").feature("wp13").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp13").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp13").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp13").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("proj1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp13").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("pol1")
         .set("table", new double[][]{{1.9937013644169137, -2.1231258764203784}, {6.901837281655236, -1.1690808995376538}});
    model.component("comp1").geom("geom1").feature("wp13").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity2")
         .set("proj1.rot1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity2")
         .set("proj1.rot1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("pol2")
         .set("table", new double[][]{{2.31208689154689, -3.1063441069427737}, {2.5800360839780123, -2.1063441069427737}, {2.0800360839780123, -2.1063441069427737}, {2.0800360839780123, -3.1063441069427737}});
    model.component("comp1").geom("geom1").feature("wp13").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ver1").selection("edge")
         .set("pol2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("hor1").selection("edge")
         .set("pol2(1)", 2, 4);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ang1").set("angle", "75[deg]");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ang1").selection("edge1")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ang1").selection("edge2")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("dist1").set("distance", "1[m]");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("dist1").selection("entity1")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("dist1").selection("entity2")
         .set("pol2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("dist2", "Distance");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("dist2").set("distance", "0.5[m]");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("dist2").selection("entity1")
         .set("pol2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("dist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("dist2").selection("entity2")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ydist1").set("distance", "0.04[m]");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ydist1").selection("entity1")
         .set("proj1.rot1(2)", 6);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("ydist1").selection("entity2")
         .set("pol2(1)", 3);

    return model;
  }

  public static Model run5(Model model) {
    model.component("comp1").geom("geom1").feature("wp13").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi3").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi3").selection("entity2")
         .set("pol2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("fil1").set("radius", "0.1[m]");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("fil1").selection("point")
         .set("pol2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp13").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp14", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp14").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp14").set("circoffset", "-27[deg]");
    model.component("comp1").geom("geom1").feature("wp14").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp14").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp14").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp14").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp14").selection("circedge").set("rot1(2)", 2);
    model.component("comp1").geom("geom1").feature("wp14").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp14").geom().constrDimBuild("uptotarget");
    model.component("comp1").geom("geom1").feature("wp14").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cro1").selection("input")
         .set("rot1", "wp13");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp14").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("pt1")
         .set("pconstr", new String[]{"off", "on"});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("pt1")
         .set("p", new String[]{"0", "5.15[m]-4.49[m]/cos(11[deg])"});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("pt1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp14").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("pol1")
         .set("table", new double[][]{{-0.075, 0.22596203965108871}, {0, 1.2812854460138217}, {0.075, 0.22596203965108863}});
    model.component("comp1").geom("geom1").feature("wp14").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi1").selection("entity1")
         .set("cro1.rot1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi1").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi2").selection("entity1")
         .set("cro1.rot1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3").selection("entity1")
         .set("cro1.wp13.pol1", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("dist1").set("distance", "0.35[m]");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("dist1").selection("entity1")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("dist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("dist1").selection("entity2")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("fil1").set("radius", "0.05[m]");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("fil1").selection("point")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("con1", "Concentric");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("con1").selection("edge")
         .set("fil1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("con1").selection("vertex")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "1.6[m]", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp14");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot2").set("specify", "edge");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "-101[deg]");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("rot2").selection("edge").set("wp13.pol1", 1);
    model.component("comp1").geom("geom1").create("thi1", "Thicken");
    model.component("comp1").geom("geom1").feature("thi1").set("totalthick", "0.14[m]");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").set("wp13.fil1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.07[m]");
    model.component("comp1").geom("geom1").feature("fil1").selection("edge").set("thi1(1)", 2, 12);
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("fil1", "rot1", "rot2", "ext1");
    model.component("comp1").geom("geom1").feature("uni1").setAttribute("construction", "off");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("Rudder");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"0.15[m]", "0.75[m]", "2.3[m]"});
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"-1.575[m]", "0.235[m]", "-2.36[m]"});
    model.component("comp1").geom("geom1").create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("Propeller Mounting");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "0.65[m]", 0);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("scale", "7/6", 0, 0);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("scale", "7/6", 0, 1);
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("uni1(1)", 8);
    model.component("comp1").geom("geom1").create("wp15", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp15").label("Coordinate System for Helix");
    model.component("comp1").geom("geom1").feature("wp15").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp15").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp15").selection("circedge").set("ext2(1)", 7);
    model.component("comp1").geom("geom1").create("hel1", "Helix");
    model.component("comp1").geom("geom1").feature("hel1").label("Spine Curve - Sweep 2");
    model.component("comp1").geom("geom1").feature("hel1").set("turns", 0.22);
    model.component("comp1").geom("geom1").feature("hel1").set("rmaj", "130[mm]");
    model.component("comp1").geom("geom1").feature("hel1").set("rmin", 0);
    model.component("comp1").geom("geom1").feature("hel1").set("axialpitch", "2290[mm]");
    model.component("comp1").geom("geom1").feature("hel1").set("chirality", "left");
    model.component("comp1").geom("geom1").feature("hel1").set("endcaps", "perpspine");
    model.component("comp1").geom("geom1").feature("hel1").set("pos", new String[]{"0", "75[mm]", "0"});
    model.component("comp1").geom("geom1").feature("hel1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("hel1").set("rot", "5[deg]");
    model.component("comp1").geom("geom1").feature("hel1").set("workplane", "wp15");
    model.component("comp1").geom("geom1").feature("hel1").set("rtol", 1.0E-8);
    model.component("comp1").geom("geom1").create("wp16", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp16").label("Faces to Sweep - Sweep 2");
    model.component("comp1").geom("geom1").feature("wp16").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp16").set("workplane", "wp15");
    model.component("comp1").geom("geom1").feature("wp16").set("transaxis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("wp16").set("transrot", "90[deg]");
    model.component("comp1").geom("geom1").feature("wp16").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp16").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp16").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp16").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp16").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("cro1").selection("input").set("hel1");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp16").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pol1")
         .set("table", new double[][]{{0.12999999999261114, 0.10680555555589955}, {0.8399999999926111, 0.10680555555589955}});
    model.component("comp1").geom("geom1").feature("wp16").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").set("distance", 0.71);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1")
         .set("helppoint1", new double[]{0.12999999999261136, 0});
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1")
         .set("helppoint2", new double[]{0.8399999999926113, 0});
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2")
         .set("helppoint1", new double[]{0.12964677810668945, -0.005125641822814941});
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2")
         .set("helppoint2", new double[]{0.12964677810668945, 0.0980970710515976});
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity2")
         .set("cro1.hel1", 1);
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("keep", false);
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("twisting", "curvature");
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").init(1);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").set("wp16.pol1", 1);
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").set("hel1(1)", 1);
    model.component("comp1").geom("geom1").feature("swe1").selection("diredge").set("hel1(1)", 1);
    model.component("comp1").geom("geom1").create("wp17", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp17").label("Blade Projection");
    model.component("comp1").geom("geom1").feature("wp17").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp17").set("rot", "90[deg]");
    model.component("comp1").geom("geom1").feature("wp17").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp17").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp17").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp17").set("showprojection", false);
    model.component("comp1").geom("geom1").feature("wp17").selection("face").set("uni1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp17").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp17").geom().constrDimBuild("uptotarget");
    model.component("comp1").geom("geom1").feature("wp17").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("proj1").selection("input")
         .set("ext2", "swe1");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp17").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cro1").selection("input").set("loft2");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp17").geom().create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("off1").set("distance", "25[mm]");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("off1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("off1").selection("input")
         .set("cro1.loft2", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{0.8049999999926102, 0}, {-0.008190431605409998, 0}, {0.1451215863232133, 0.601460417146731}});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.1753913733845032, -0.4128983721103032});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").feature("ca1")
         .set("r", 1.0637914789485752);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").feature("ca1")
         .set("angle1", 72.46471784342799);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").feature("ca1")
         .set("angle2", 22.838717599057457);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp17").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi1").selection("entity2")
         .set("proj1.swe1", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi2").selection("entity2")
         .set("proj1.swe1", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi3")
         .set("helppoint2", new double[]{0.1451215863232134, 0.6014604171467308});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi3").selection("entity2")
         .set("off1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("ang1").set("angle", "3.5[deg]");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("ang1").selection("edge2")
         .set("proj1.swe1", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("xdist1").set("distance", "35[mm]");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("xdist1").selection("entity2")
         .set("proj1.swe1", 4);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.1451215863232134, 0.6014604171467308});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.14512158632321343, 0.6014604171467309});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc1").selection("edge1")
         .set("off1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("cc2", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").set("type", "solid");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca1")
         .set("center", new double[]{2.112260977585323E-6, 1.7474911011838845E-6});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca1")
         .set("r", 0.1299978877433844);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca1")
         .set("angle1", 79.20077072699729);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca1")
         .set("angle2", 359.99922980391005);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("pol1")
         .set("table", new double[][]{{0.12999999999261003, 0}, {0.5775495957334302, 0.015628776268986094}});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca2")
         .set("center", new double[]{0.5730127348787981, 0.14554747281157626});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca2")
         .set("r", 0.12999788774337806);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca2").set("angle1", -88);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca2")
         .set("angle2", 36.729645885973774);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca3")
         .set("center", new double[]{-0.17539137338450328, -0.4128983721103032});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca3")
         .set("r", 1.0637914789485754);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca3")
         .set("angle1", 36.72964588597377);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca3")
         .set("angle2", 63.95785771619555);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").create("ca4", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca4")
         .set("center", new double[]{0.23457399782039248, 0.42608843671849567});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca4")
         .set("r", 0.1299978877433844);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca4")
         .set("angle1", 63.95785771619556);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("ca4")
         .set("angle2", 165.69984972396793);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("cc2").feature("pol2")
         .set("table", new double[][]{{0.10860408376460218, 0.45819811704260743}, {0.02435956963602559, 0.12769734290442214}});
    model.component("comp1").geom("geom1").feature("wp17").geom().create("eqrad1", "EqualRadius");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("eqrad1").selection("edge1")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("eqrad1").selection("edge2")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi4").selection("entity1")
         .set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi4").selection("entity2")
         .set("proj1.swe1", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi5").selection("entity1")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi5").selection("entity2")
         .set("proj1.swe1", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.2916472448489737, 0.5428878172449111});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.29164724484897375, 0.5428878172449112});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2").selection("edge1")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2").selection("vertex1")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2").selection("edge2")
         .set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc2").selection("vertex2")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3")
         .set("helppoint1", new double[]{0.6772016628913873, 0.22329139924526853});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3")
         .set("helppoint2", new double[]{0.6772016628913873, 0.22329139924526853});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3").selection("edge1")
         .set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3").selection("vertex1")
         .set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3").selection("edge2")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc3").selection("vertex2")
         .set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("tanc4", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc4").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc4").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc4")
         .set("helppoint1", new double[]{0.5775495957334296, 0.0156287762689864});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc4").selection("edge1")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc4").selection("vertex1")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc4").selection("edge2")
         .set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc4").selection("vertex2")
         .set("cc2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("tanc5", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc5").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc5").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc5")
         .set("helppoint1", new double[]{0.10860408376460223, 0.45819811704260743});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc5").selection("edge1")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc5").selection("vertex1")
         .set("cc2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc5").selection("edge2")
         .set("cc2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc5").selection("vertex2")
         .set("cc2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("tanc6", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6")
         .set("helppoint1", new double[]{0.024359569636025584, 0.12769734290442214});

    return model;
  }

  public static Model run6(Model model) {
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6")
         .set("helppoint2", new double[]{0.024359569636025702, 0.12769734290442214});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6").selection("edge1")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6").selection("vertex1")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6").selection("edge2")
         .set("proj1.swe1", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("tanc6").selection("vertex2")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("con1", "Concentric");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("con1").selection("edge").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("con1").selection("edge").set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi6")
         .set("helppoint2", new double[]{0.6772016628913873, 0.22329139924526864});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi6").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi6").selection("entity1")
         .set("cc2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi6").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi7").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("coi7").selection("entity2")
         .set("cc2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("ang2", "Angle");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("ang2").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("ang2").set("angle", "2[deg]");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("ang2").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("ang2").selection("edge2")
         .set("cc2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("eqrad2", "EqualRadius");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("eqrad2").selection("edge1")
         .set("cc2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("eqrad2").selection("edge2")
         .set("cc2(1)", 5);
    model.component("comp1").geom("geom1").create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp17");
    model.component("comp1").geom("geom1").feature("ext3").selection("vertex").set("ext2(1)", 8);
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("int1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("ext3", "swe1");
    model.component("comp1").geom("geom1").create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("rot3").set("specify", "edge");
    model.component("comp1").geom("geom1").feature("rot3").set("rot", "range(72[deg],72[deg],360[deg])");
    model.component("comp1").geom("geom1").feature("rot3").selection("input").named("int1");
    model.component("comp1").geom("geom1").feature("rot3").selection("edge").set("wp13.pol1", 1);
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("ext2", "rot3");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "0.444[m]");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 1.2);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"-1.5[m]", "8[m]", "-1.6[m]"});
    model.component("comp1").geom("geom1").feature("cyl3").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("rot4", "Rotate");
    model.component("comp1").geom("geom1").feature("rot4").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("rot4").set("rot", "25.8[deg]");
    model.component("comp1").geom("geom1").feature("rot4").set("pos", new String[]{"-1.5[m]", "8[m]", "-1.485[m]"});
    model.component("comp1").geom("geom1").feature("rot4").selection("input").set("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "0.229[m]");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 1.2);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"-1.7[m]", "14.2[m]", "-1.6[m]"});
    model.component("comp1").geom("geom1").feature("cyl4").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("rot5", "Rotate");
    model.component("comp1").geom("geom1").feature("rot5").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("rot5").set("rot", "29.3[deg]");
    model.component("comp1").geom("geom1").feature("rot5").set("pos", new String[]{"-1.7[m]", "14.2[m]", "-1.4[m]"});
    model.component("comp1").geom("geom1").feature("rot5").selection("input").set("cyl4");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("loft2");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("rot4", "rot5");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"10[m]", "57[m]", "11[m]"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-10[m]", "-12[m]", "-11[m]"});
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"2.7[m]", "7.2[m]", "3.4[m]"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"-2.7[m]", "-1[m]", "-3.4[m]"});
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("blk2", "blk3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1", "par1", "uni1", "uni2");
    model.component("comp1").geom("geom1").create("ccom1", "ConvertToCOMSOL");
    model.component("comp1").geom("geom1").feature("ccom1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input")
         .set("fin(1)", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159);
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("igv1(1)", 51, 69, 85, 87, 89, 91, 127, 138, 140);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("Hull\u00a0shape");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("mov1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp8");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp9");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp10");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("extract1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("Stern\u00a0shape");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("pt1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp11");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp12");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("pare2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("loft2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").label("Drive shaft");
    model.component("comp1").geom("geom1").nodeGroup("grp3").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("cyl1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("cyl2");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("rot1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp13");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp14");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ext1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("rot2");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("thi1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("fil1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp4");
    model.component("comp1").geom("geom1").nodeGroup("grp4").label("Propeller");
    model.component("comp1").geom("geom1").nodeGroup("grp4").placeAfter("blk1");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("ext2");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("wp15");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("hel1");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("wp16");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("swe1");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("wp17");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("ext3");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("int1");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("rot3");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("uni2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp5");
    model.component("comp1").geom("geom1").nodeGroup("grp5").label("Electrodes");
    model.component("comp1").geom("geom1").nodeGroup("grp5").placeAfter("blk1");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("cyl3");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("rot4");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("cyl4");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("rot5");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("par1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp6");
    model.component("comp1").geom("geom1").nodeGroup("grp6").label("Computational Domains");
    model.component("comp1").geom("geom1").nodeGroup("grp6").placeAfter("blk1");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("blk2");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("blk3");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("uni3");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("dif1");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").export("ship_hull_geometry.mphbin");
    model.component("comp1").geom("geom1").export().selection().init();
    model.component("comp1").geom("geom1").feature().remove("ccom1");
    model.component("comp1").geom("geom1").run("ige1");

    model.title("\u8239\u4f53\u51e0\u4f55");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u653e\u6837\u201d\u64cd\u4f5c\u521b\u5efa\u8239\u4f53\u7684\u51e0\u4f55\u5e8f\u5217\uff0c\u5176\u4e2d\u4f7f\u7528\u5c3a\u5bf8\u548c\u7ea6\u675f\u521b\u5efa\u653e\u6837\u7684\u8f6e\u5ed3\u3002");

    model.label("ship_hull_geometry.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    run6(model);
  }

}
