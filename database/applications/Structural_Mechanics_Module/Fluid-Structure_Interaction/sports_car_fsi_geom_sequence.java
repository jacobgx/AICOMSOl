/*
 * sports_car_fsi_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:09 by COMSOL 6.3.0.290. */
public class sports_car_fsi_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);

    model.param().loadFile("sports_car_geom_parameters.txt");

    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Partition Edges");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").label("Work Plane - Hood");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "z_cfc*1.042");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("pos", new double[]{1.34292, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxesconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxes", new String[]{"R_fc*2.5", "R_fc*1.02"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pos1")
         .set("pos", new String[]{"x_cfc*1.178", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pos1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pos1")
         .set("parname", new String[]{"geom1.wp1.pos1.xw", "geom1.wp1.pos1.yw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pos1").selection("vertex").set("e1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"-L", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("sizeconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"L", "W/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("int1").selection("input").set("e1", "r1");
    model.component("comp1").geom("geom1").create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").label("Revolve - Hood");
    model.component("comp1").geom("geom1").feature("rev1").set("angle1", 50);
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 100);
    model.component("comp1").geom("geom1").feature("rev1").set("pos", new String[]{"geom1.wp1.pos1.xw", "0"});
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").label("Work Plane - Windscreen");
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "z_wsc*1.0109");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("pos", new double[]{2.778528, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "R_wsc");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pos1")
         .set("pos", new String[]{"x_wsc*0.9888", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pos1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pos1")
         .set("parname", new String[]{"geom1.wp2.pos1.xw", "geom1.wp2.pos1.yw"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pos1").selection("vertex").set("c1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new String[]{"-L/2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("sizeconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new String[]{"L", "W/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("int1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").label("Revolve - Windscreen");
    model.component("comp1").geom("geom1").feature("rev2").set("angle1", 30);
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", 85);
    model.component("comp1").geom("geom1").feature("rev2").set("pos", new String[]{"geom1.wp2.pos1.xw", "0"});
    model.component("comp1").geom("geom1").feature("rev2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").label("Work Plane - Roof");
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "z_rc");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1").set("pos", new double[]{2.34, 0});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1")
         .set("semiaxesconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1")
         .set("semiaxes", new String[]{"R_wsr*1.3", "R_wsr"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pos1")
         .set("pos", new String[]{"x_rc", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pos1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pos1")
         .set("parname", new String[]{"geom1.wp3.pos1.xw", "geom1.wp3.pos1.yw"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pos1").selection("vertex").set("e1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("pos", new String[]{"-L/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("sizeconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("size", new String[]{"L", "W/2"});
    model.component("comp1").geom("geom1").feature("wp3").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("int1").selection("input").set("e1", "r1");
    model.component("comp1").geom("geom1").create("rev3", "Revolve");
    model.component("comp1").geom("geom1").feature("rev3").label("Revolve - Roof");
    model.component("comp1").geom("geom1").feature("rev3").set("angle1", 75);
    model.component("comp1").geom("geom1").feature("rev3").set("angle2", 140);
    model.component("comp1").geom("geom1").feature("rev3").set("pos", new String[]{"geom1.wp3.pos1.xw", "0"});
    model.component("comp1").geom("geom1").feature("rev3").selection("input").set("wp3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").label("Work Plane - Rear Window");
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp4").set("quickx", "x_rbc");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("e1").set("pos", new String[]{"0", "z_rc"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("e1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("e1")
         .set("semiaxes", new String[]{"R_wsr*1.00442", "R_wsr*0.98"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("e1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").set("size", new String[]{"W/2", "H"});
    model.component("comp1").geom("geom1").feature("wp4").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("int1").selection("input").set("e1", "r1");
    model.component("comp1").geom("geom1").create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("Extrude - Rear Window");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L-x_rbc+0.5", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("scale", "1", 0, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("scale", "0.69", 0, 1);
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp4");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("Union - Roof Surfaces");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "rev1", "rev2", "rev3");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").label("Delete - Roof Surfaces");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1(1)", 1, 3, 5, 6, 8, 9);
    model.component("comp1").geom("geom1").create("fil1", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil1").label("Fillet - Roof to Back");
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "z_gcl*100");
    model.component("comp1").geom("geom1").feature("fil1").selection("edge").set("del1(1)", 10);
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").label("Work Plane - Underside");
    model.component("comp1").geom("geom1").feature("wp5").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp5").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .set("table", new double[][]{{3.6449, 0.135}, {-0.4, 0.135}});
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol2")
         .set("table", new double[][]{{4.46024, 0.5151992860000973}, {3.6449, 0.135}});
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol3")
         .set("table", new double[][]{{3.6449, 0.135}, {4.46024, 0.27876644045084004}});
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol4")
         .set("table", new double[][]{{4.32524, 0.874395}, {3.8042796463713047, 0.6639133545136493}});
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pos1")
         .set("pos", new String[]{"-0.4", "z_gcl"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pos1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pos1")
         .set("parname", new String[]{"geom1.wp5.pos1.xw", "geom1.wp5.pos1.yw"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pos1").selection("vertex")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pos2", "Position");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pos2")
         .set("posconstr", new String[]{"on", "off"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pos2")
         .set("pos", new String[]{"x_w+L_wb", "0.135"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pos2").selection("vertex")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("hor1").selection("edge").set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi1").selection("entity2")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi2").selection("entity1")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi2").selection("entity2")
         .set("pol3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang1").set("angle", 25);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang1").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang1").selection("edge2")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist1")
         .set("distance", "L-geom1.wp5.pos1.xw");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist1").selection("entity1")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist1").selection("entity2")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist2").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist2").selection("entity2")
         .set("pol3(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("xdist3", "XDistance");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist3").set("distance", "z_gcl");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist3").selection("entity1")
         .set("pol4(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("xdist3").selection("entity2")
         .set("pol3(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ydist1").set("distance", "0.75*H-z_gcl");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ydist1").selection("entity1")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ydist1").selection("entity2")
         .set("pol4(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("ang2", "Angle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang2").set("angle", 10);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang2").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang2").selection("edge2")
         .set("pol3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("ang3", "Angle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang3").set("angle", 22);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang3").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("ang3").selection("edge2")
         .set("pol4(1)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("c1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("c1")
         .set("pos", new String[]{"x_w+L_wb", "R_w"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("c1").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("c1").set("r", "R_wh");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("c1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi3")
         .set("helppoint1", new double[]{3.8368543129689257, 0.6463124116006638});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi3")
         .set("helppoint2", new double[]{3.804279646371304, 0.6639133545136495});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi3").selection("entity1")
         .set("pol4(1)", 2);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("coi3").selection("entity2")
         .set("c1(1)", 3);
    model.component("comp1").geom("geom1").create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("Extrude - Underside");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "W/2", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp5");
    model.component("comp1").geom("geom1").create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("extract1").label("Extract - Construction Geometry");
    model.component("comp1").geom("geom1").feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract1").selection("input").set("ext2(2)", 1);
    model.component("comp1").geom("geom1").feature("extract1").selection("input").set("ext2(4)", 1);
    model.component("comp1").geom("geom1").feature("extract1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").label("Work Plane - Top View");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("pos", new String[]{"x_w-R_wh", "0"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("sizeconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("size", new String[]{"R_wh*2", "W/2"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("mov1").set("displx", "L_wb");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("mov1").selection("input").named("r1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1")
         .set("table", new double[][]{{4.0149, 0.96139}, {4.46024, 0.7210425}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi1").selection("entity2")
         .set("mov1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol1").label("Polygon 2");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{4.46024, 0}, {4.46024, 0.384556}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{4.46024, 4.46024, 4.024077147209925, 4.0149}, {0.384556, 0.7210425, 0.9564371451272347, 0.96139}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol2").label("Polygon 3");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{4.0149, 0.96139}, {3.2749, 0.96139}, {3.1799, 0.96139}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{3.1799, 0.82639});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1").set("r", 0.135);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1").set("angle1", 90);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca1")
         .set("angle2", 106.80914248121289);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{2.8545536, 1.9033681583198037});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2")
         .set("r", 0.9900476583198037);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2")
         .set("angle1", -73.19085751878686);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2").set("angle2", -90);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol3").label("Polygon 4");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol3")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol3")
         .set("table", new String[][]{{"2.8545536", "0.9133205"}, {"2.05859689916758941", "0.9133205"}, {"1.50916", "0.96139"}, {"1.39616", "0.96139"}, {"0.65616", "0.96139"}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca3")
         .set("center", new double[]{0.9818307820291452, 0.07428034402024175});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca3").set("r", 0.945);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca3")
         .set("angle1", 110.15891620189699);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca3")
         .set("angle2", 128.7384890408643);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol4").label("Polygon 5");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol4")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("pol4")
         .set("table", new double[][]{{0.39048117725934206, 0.81139}, {0.07909117725934209, 0.5}});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").create("ca4", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca4")
         .set("center", new double[]{1.62, 0});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca4").set("r", 1.62);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca4")
         .set("angle1", 162.02259133250638);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("cc1").feature("ca4").set("angle2", -180);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pos1").selection("vertex")
         .set("cc1(1)", 14);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("wp6").geom().create("pos2", "Position");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pos2").set("pos", new String[]{"L", "0"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pos2").selection("vertex")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ver1").selection("edge").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("hor1").selection("edge")
         .set("cc1(1)", 4, 7, 9, 21);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 11);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi2").selection("entity2")
         .set("r1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi3").selection("entity2")
         .set("r1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi4").selection("entity2")
         .set("mov1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("coi5").selection("entity2")
         .set("mov1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("dist1")
         .set("distance", "R_wh*2*1.2-0.035");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("dist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("dist1").selection("entity1")
         .set("cc1(1)", 11);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("dist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("dist1").selection("entity2")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang1").set("angle", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang1").selection("edge1").set("cc1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang1").selection("edge2").set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist1").set("distance", "W/2*0.95");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist1").selection("entity1")
         .set("r1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist1").selection("entity2")
         .set("cc1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ydist2", "YDistance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist2").set("distance", "W/2*0.4");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist2").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist2").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist1").set("distance", "air_inlet");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 14);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ydist3", "YDistance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist3").set("distance", 0.5);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist3").selection("entity1")
         .set("cc1(1)", 14);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist3").selection("entity2")
         .set("cc1(1)", 13);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ydist4", "YDistance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist4").set("distance", 0.15);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist4").selection("entity1")
         .set("cc1(1)", 12);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ydist4").selection("entity2")
         .set("cc1(1)", 11);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("ang2", "Angle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang2").set("angle", 45);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang2").selection("edge1")
         .set("cc1(1)", 12);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("ang2").selection("edge2").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("fil1").set("radius", "z_gcl*5");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("fil1").selection("point").set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("fil2").set("radius", "z_gcl");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("fil2").selection("point")
         .set("fil1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rad1").set("radius", "z_gcl*12");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rad1").selection("edge")
         .set("fil2(1)", 12);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("rad2", "Radius");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rad2").set("radius", "z_gcl*7");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rad2").selection("edge")
         .set("fil2(1)", 13);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("rad3", "Radius");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rad3").set("radius", "z_gcl");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rad3").selection("edge")
         .set("fil2(1)", 15);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist2").set("distance", 0.095);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist2").selection("entity1")
         .set("fil2(1)", 12);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("xdist2").selection("entity2")
         .set("fil2(1)", 13);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1")
         .set("helppoint2", new double[]{2.8545536, 0.9133205});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("edge1")
         .set("fil2(1)", 5);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("vertex1")
         .set("fil2(1)", 10);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("edge2")
         .set("fil2(1)", 14);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc1").selection("vertex2")
         .set("fil2(1)", 10);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2")
         .set("helppoint1", new double[]{3.1408600858459685, 0.9556219043535529});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2")
         .set("helppoint2", new double[]{3.1408600858459685, 0.9556219043535529});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("edge1")
         .set("fil2(1)", 14);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("vertex1")
         .set("fil2(1)", 11);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("edge2")
         .set("fil2(1)", 15);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc2").selection("vertex2")
         .set("fil2(1)", 11);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3")
         .set("helppoint1", new double[]{3.1799, 0.96139});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("edge1")
         .set("fil2(1)", 15);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("vertex1")
         .set("fil2(1)", 12);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("edge2")
         .set("fil2(1)", 6);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc3").selection("vertex2")
         .set("fil2(1)", 12);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("tanc4", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc4")
         .set("helppoint2", new double[]{4.0149, 0.96139});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc4").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("tanc4").selection("edge2")
         .set("fil2(1)", 11);
    model.component("comp1").geom("geom1").feature("wp6").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("Extrude - Top View");
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "H", 0);
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp6");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input")
         .set("ext2(1)", "ext2(3)", "ext3", "fil1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("extract1(1)");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").label("Box Selection - Basic Shape");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "x_w");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "L-x_w/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "W/4");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "H");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").label("Box Selection - Basic Shape (straight part)");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "L-x_w*0.4");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "L");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "W");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "z_gcl*2.1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "z_gcl*2.1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("selshow", false);
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").label("Complement Selection - Basic Shape");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"boxsel1", "boxsel2"});
    model.component("comp1").geom("geom1").feature("comsel1").set("selshow", false);
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").label("Delete Entities - Basic Shape");
    model.component("comp1").geom("geom1").feature("del2").selection("input").named("comsel1");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").label("Work Plane - Wheel Arches");
    model.component("comp1").geom("geom1").feature("wp7").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp7").set("quicky", "W/2-W_w*1.1");
    model.component("comp1").geom("geom1").feature("wp7").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cro1").selection("input")
         .set("del2", "extract1(1)");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").set("type", "solid");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1")
         .set("centerconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1")
         .set("center", new String[]{"x_w", "R_w"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1").set("r", "R_wh");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("ca1").set("angle2", 180);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{0.65616, 0.33}, {0.65616, 0}, {1.39616, 0}, {1.39616, 0.33}});
    model.component("comp1").geom("geom1").feature("wp7").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("hor1").selection("edge")
         .set("cc1(1)", 3, 5, 6);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("ver1").selection("edge")
         .set("cc1(1)", 2, 4);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("mov1").set("displx", "L_wb");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("mov1").selection("input").set("cc1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("par1").selection("input")
         .set("cc1", "mov1");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("par1").selection("tool").set("cro1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("del1").selection("input")
         .set("par1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("del1").selection("input")
         .set("par1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni1").selection("input").set("del1(2)");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("extract1").selection("input")
         .set("del1(1)", 1, 2, 4);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("extract2").selection("input")
         .set("uni1(1)", 1, 2, 3, 5);
    model.component("comp1").geom("geom1").create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").label("Extrude - Wheel Arches");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "W_w*1.1", 0);
    model.component("comp1").geom("geom1").feature("ext4").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp7.extract1", "wp7.extract2");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").label("Partition Edges - Position for Back Loft");
    model.component("comp1").geom("geom1").feature("pare1").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("ext4(2)", 6);
    model.component("comp1").geom("geom1").feature("pare1").selection("vertexproj").set("extract1(2)", 2);
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").label("Box Selection - Back Loft");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "x_w+L_wb");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "x_w+L_wb+R_wh*1.05");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", "W/2*0.95");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "W/2*1.05");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "z_gcl+R_wh/4");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "z_gcl+R_wh*2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel3").set("selshow", false);
    model.component("comp1").geom("geom1").create("wp8", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp8").label("Work Plane - Roof Partitioning");
    model.component("comp1").geom("geom1").feature("wp8").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp8").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp8").selection("vertex1").set("del2(1)", 44);
    model.component("comp1").geom("geom1").feature("wp8").selection("vertex2").set("del2(1)", 43);
    model.component("comp1").geom("geom1").feature("wp8").selection("vertex3").set("wp6.fil2", 3);
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("Explicit Selection - Rear Loft (Wheel Arch)");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("pare1(1)", 5, 6);
    model.component("comp1").geom("geom1").feature("sel1").set("selshow", false);
    model.component("comp1").geom("geom1").create("parf1", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf1").label("Partition Faces - Roof Partitioning");
    model.component("comp1").geom("geom1").feature("parf1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf1").selection("face").set("del2(1)", 4, 5, 6);
    model.component("comp1").geom("geom1").create("wp9", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp9").label("Work Plane - Profile Back Loft 1");
    model.component("comp1").geom("geom1").feature("wp9").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp9").set("offsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp9").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp9").selection("offsetvertex").set("parf1(1)", 33);
    model.component("comp1").geom("geom1").feature("wp9").selection("face").set("extract1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cro1").selection("input")
         .set("pare1", "parf1");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp9").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("proj1").selection("input").set("parf1");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp9").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-0.44981407622343905, -0.480695}, {-0.44981407622343905, -0.096139}});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{-0.44981407622343905, -0.44981407622343905, -0.13981392217874572, -0.002035815457595902}, {-0.096139, 0.192278, 0.385648250642829, 0.480695}});
    model.component("comp1").geom("geom1").feature("wp9").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ydist1").set("distance", "0.4*W/2");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ydist1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ydist1").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ver1").selection("edge").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi1").selection("entity2")
         .set("proj1.parf1", 3);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("coi2").selection("entity2")
         .set("cro1.pare1", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1")
         .set("helppoint2", new double[]{-0.44981407622343905, -0.096139});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("eqdist1", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist1").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist1").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist1").selection("entity3")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist1").selection("entity4")
         .set("proj1.parf1", 12);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("eqdist2", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist2").selection("entity1")
         .set("cro1.pare1", 1);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist2").selection("entity2").init(0);

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist2").selection("entity2")
         .set("proj1.parf1", 6);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist2").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist2").selection("entity3")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist2").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("eqdist2").selection("entity4")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp9").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").set("angle", 34.6);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1")
         .set("helppoint2", new double[]{-0.002035815457595902, 0.480695});
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").selection("edge1")
         .set("proj1.parf1", 12);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("ang1").selection("edge2").set("cc1(1)", 2);
    model.component("comp1").geom("geom1").create("wp10", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp10").label("Work Plane - Profile Back Loft 2");
    model.component("comp1").geom("geom1").feature("wp10").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp10").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp10").selection("face").set("extract1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cro1").selection("input")
         .set("pare1", "parf1");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp10").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("proj1").selection("input")
         .set("parf1", "wp9");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp10").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{1.9508429908857718, -0.4798855269471634});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("r", 2.2203372541238764);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("angle1", -179.97911155591416);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("angle2", 169.55320223827837);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("cc1").feature("cb1")
         .set("p", new String[][]{{"-0.23268928508000997", "-0.20422489970552637", "-0.050024483261841626", "0.28093692047307384"}, {"-0.07728855494408388", "0.07709140442698341", "0.28961434446226675", "0.4806950"}});
    model.component("comp1").geom("geom1").feature("wp10").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi1").selection("entity2")
         .set("cro1.parf1", 2);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi2").selection("entity2")
         .set("cro1.parf1", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("coi3").selection("entity2")
         .set("cro1.pare1", 2);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1")
         .set("helppoint1", new double[]{-0.2326892850800002, -0.07728855494403108});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1")
         .set("helppoint2", new double[]{-0.2326892850799993, -0.07728855494403125});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("tanc1").selection("edge2")
         .set("cro1.parf1", 1);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("eqdist1", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity2")
         .set("cro1.parf1", 4);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity3")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("eqdist1").selection("entity4")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp10").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").set("angle", 30);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1")
         .set("helppoint1", new double[]{0.28093692047307384, 0.480695});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1")
         .set("helppoint2", new double[]{0.28093692047307384, 0.480695});
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").selection("edge1")
         .set("proj1.parf1", 12);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").create("wp11", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp11").label("Work Plane - Guide Curve Back Loft 1");
    model.component("comp1").geom("geom1").feature("wp11").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp11").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp11").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cro1").selection("input")
         .set("parf1", "wp10", "wp9");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp11").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("pol1")
         .set("table", new double[][]{{4.46024, 0.5151992860000977}, {4.3735384760287435, 0.5227846864580037}, {4.396587928497475, 0.6535046271960628}, {4.29532635625049, 0.7955943943803614}, {4.314630416147171, 0.8701084498782595}});
    model.component("comp1").geom("geom1").feature("wp11").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("fil1").set("radius", 0.01);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("fil1").selection("point")
         .set("pol1(1)", 1, 3, 4);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").set("angle", 130);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").selection("edge1")
         .set("fil1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang1").selection("edge2")
         .set("fil1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("ang2", "Angle");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").set("angle", 5);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").selection("edge1")
         .set("fil1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang2").selection("edge2")
         .set("cro1.parf1", 5);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("ang3", "Angle");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang3").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang3").set("angle", 100);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang3").selection("edge1")
         .set("fil1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ang3").selection("edge2")
         .set("cro1.parf1", 5);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity1")
         .set("cro1.parf1", 9);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi1").selection("entity2")
         .set("fil1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi2").selection("entity1")
         .set("cro1.wp9", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi2").selection("entity2")
         .set("fil1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi3").selection("entity1")
         .set("cro1.wp10", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("coi3").selection("entity2")
         .set("fil1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp11").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").set("distance", 0.07);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity1")
         .set("fil1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp11").geom().feature("ydist1").selection("entity2")
         .set("fil1(1)", 3);
    model.component("comp1").geom("geom1").create("wp12", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp12").label("Work Plane - Guide Curve Back Loft 2");
    model.component("comp1").geom("geom1").feature("wp12").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp12").set("quicky", "0.4*W/2");
    model.component("comp1").geom("geom1").feature("wp12").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp12").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cro1").selection("input")
         .set("parf1", "wp10", "wp9");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp12").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("pol1")
         .set("table", new double[][]{{4.46024, 0.5151992860000977}, {4.374587006104348, 0.5226929519630223}, {4.397610098483596, 0.6532633972048287}, {4.275002649429074, 0.7829595550375469}, {4.28365022639974, 0.8575916407389474}});
    model.component("comp1").geom("geom1").feature("wp12").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("fil1").set("radius", 0.01);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("fil1").selection("point")
         .set("pol1(1)", 1, 3, 4);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang1").set("angle", 130);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang1").selection("edge1")
         .set("fil1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang1").selection("edge2")
         .set("fil1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("ang2", "Angle");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang2").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang2").set("angle", 5);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang2").selection("edge1")
         .set("fil1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang2").selection("edge2")
         .set("cro1.parf1", 4);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("ang3", "Angle");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang3").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang3").set("angle", 100);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang3").selection("edge1")
         .set("fil1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ang3").selection("edge2")
         .set("cro1.parf1", 4);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity1")
         .set("cro1.parf1", 9);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi1").selection("entity2")
         .set("fil1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity1")
         .set("cro1.wp9", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi2").selection("entity2")
         .set("fil1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi3").selection("entity1")
         .set("cro1.wp10", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("coi3").selection("entity2")
         .set("fil1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp12").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ydist1").set("distance", 0.07);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ydist1").selection("entity1")
         .set("fil1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp12").geom().feature("ydist1").selection("entity2")
         .set("fil1(1)", 3);
    model.component("comp1").geom("geom1").create("pare2", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare2").label("Partition Edges - Back Loft");
    model.component("comp1").geom("geom1").feature("pare2").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare2").selection("edge").set("parf1(1)", 51);
    model.component("comp1").geom("geom1").feature("pare2").selection("edge").set("wp9.cc1", 2);
    model.component("comp1").geom("geom1").feature("pare2").selection("vertexproj").set("wp10.cc1", 2);
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("Explicit Selection - Back Loft");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("pare2(1)", 51, 54, 57);
    model.component("comp1").geom("geom1").feature("sel2").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel2").set("selshow", false);
    model.component("comp1").geom("geom1").create("loft1", "Loft");
    model.component("comp1").geom("geom1").feature("loft1").label("Loft - Back Loft");
    model.component("comp1").geom("geom1").feature("loft1").set("facepartitioning", "columns");
    model.component("comp1").geom("geom1").feature("loft1").selection("profile").set("pare2(2)", "wp10");
    model.component("comp1").geom("geom1").feature("loft1").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft1").selection("startprofile").named("sel2");
    model.component("comp1").geom("geom1").feature("loft1").selection("guide").set("wp11", "wp12");
    model.component("comp1").geom("geom1").feature("loft1").selection("startguide").named("boxsel3");
    model.component("comp1").geom("geom1").create("del3", "Delete");
    model.component("comp1").geom("geom1").feature("del3").label("Delete Entities - Roof Partitioning");
    model.component("comp1").geom("geom1").feature("del3").selection("input").set("loft1(1)", 7, 13, 19);
    model.component("comp1").geom("geom1").create("wp13", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp13").label("Work Plane - Guide Curve Rear Loft");
    model.component("comp1").geom("geom1").feature("wp13").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp13").set("quickx", "x_w-R_wh+L_wb");
    model.component("comp1").geom("geom1").feature("wp13").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp13").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("cro1").selection("input").set("del3");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp13").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("cb1")
         .set("p", new double[][]{{0.96139, 0.96139, 0.7848176543655423, 0.508873787760035}, {0.33, 0.7001395171342196, 0.9897619058386034, 1.019050554266928}});
    model.component("comp1").geom("geom1").feature("wp13").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi1").selection("entity2")
         .set("cro1.del3", 3);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("coi2").selection("entity2")
         .set("cro1.del3", 7);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.5088737877600334, 1.0190505542669244});
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.508873787760035, 1.019050554266928});
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("tanc1").selection("edge1")
         .set("cro1.del3", 2);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("tanc1").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.96139, 0.33});
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("tanc2").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("tanc2").selection("edge2")
         .set("cro1.del3", 6);
    model.component("comp1").geom("geom1").feature("wp13").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp13").geom().feature("pare1").selection("edge")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp13").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp14", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp14").label("Work Plane - Window Frame");
    model.component("comp1").geom("geom1").feature("wp14").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp14").set("workplane", "wp8");
    model.component("comp1").geom("geom1").feature("wp14").set("transdispl", new double[]{0, 0, 0.18});
    model.component("comp1").geom("geom1").feature("wp14").set("transrot", 90);
    model.component("comp1").geom("geom1").feature("wp14").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp14").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cro1").selection("input")
         .set("del3", "wp13");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp14").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("c1")
         .set("pos", new double[]{2.1018930576209267, -2.5890109708579767});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("c1").set("r", 3.1868151968095484);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("c1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp14").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("c2")
         .set("pos", new double[]{3.1376851839914877, -1.7733528263570508});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("c2").set("r", 2.0772818966007023);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("c2").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi1")
         .set("helppoint2", new double[]{2.457958358733595, 0.5778500301901949});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi1").selection("entity1")
         .set("cro1.del3", 9);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi1").selection("entity2")
         .set("c1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi2")
         .set("helppoint2", new double[]{1.4372711999071872, 0.5277291345831903});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi2").selection("entity1")
         .set("cro1.del3", 8);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi2").selection("entity2")
         .set("c1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc1")
         .set("helppoint1", new double[]{1.437271199907187, 0.5277291345831904});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc1")
         .set("helppoint2", new double[]{1.4372711999071868, 0.5277291345831907});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc1").selection("edge1")
         .set("cro1.del3", 9);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc1").selection("edge2")
         .set("c1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3")
         .set("helppoint1", new double[]{3.8138823713770345, 0.1907901016695439});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3")
         .set("helppoint2", new double[]{3.8138823713770345, 0.1907901016695439});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3").selection("entity1")
         .set("c2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi3").selection("entity2")
         .set("cro1.del3", 12);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi4")
         .set("helppoint1", new double[]{3.2246266828066616, 0.30210887016466303});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi4").selection("entity1")
         .set("c2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi4").selection("entity2")
         .set("cro1.del3", 10);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc2")
         .set("helppoint1", new double[]{3.2246266828066616, 0.3021088701646615});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc2")
         .set("helppoint2", new double[]{3.2246266828066616, 0.3021088701646608});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc2").selection("edge1")
         .set("cro1.del3", 10);

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc2").selection("edge2")
         .set("c2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{2.101893057620927, -2.589010970857977});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca1")
         .set("r", 3.157303080031952);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca1")
         .set("angle1", 106.39500317834921);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca1")
         .set("angle2", 83.66634570402454);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{0.8911319545987185, -5.244881370587317});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca2").set("r", 6);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca2")
         .set("angle1", 74.93914614583524);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca2")
         .set("angle2", 67.63023409510018);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca3")
         .set("center", new double[]{3.1376851839914877, -1.7733528263570502});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca3")
         .set("r", 2.0772818966007023);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca3")
         .set("angle1", 88.98102248215183);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("ca3")
         .set("angle2", 94.98102248215112);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{2.9573235667880615, 2.717323566788031, 1.7507196582678084, 1.2107196582678057}, {0.29608423839867126, 0.27516705813450953, 0.09991172730793169, 0.43991172730793604}});
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi5").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi5").selection("entity2")
         .set("cro1.wp13", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("con1", "Concentric");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("con1").selection("edge").set("c1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("con1").selection("edge").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi6").selection("entity2")
         .set("cro1.del3", 9);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi7")
         .set("helppoint1", new double[]{3.174626682806662, 0.30360056830790927});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi7").selection("entity1")
         .set("c2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi7").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("coi8", "Coincident");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi8")
         .set("helppoint2", new double[]{2.9573235667879203, 0.29608423839866727});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi8").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi8").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("coi8").selection("entity2")
         .set("c2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ang1").set("angle", 6);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("con2", "Concentric");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("con2").selection("edge").set("c2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("con2").selection("edge").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc3")
         .set("helppoint1", new double[]{2.9573235667879203, 0.2960842383986678});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc3")
         .set("helppoint2", new double[]{2.9573235667879203, 0.29608423839866704});
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("tanc3").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist1").set("distance", 0.05);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist1").selection("entity2")
         .set("cro1.del3", 10);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("rad1").set("radius", 6);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("rad1").selection("edge").set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist2").set("distance", 0.24);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist2").selection("entity1")
         .set("cc1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist2").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("xdist3", "XDistance");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist3").set("distance", 0.54);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist3").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("xdist3").selection("entity2")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ydist1").set("distance", 0.34);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ydist1").selection("entity1")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("ydist1").selection("entity2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp14").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("fil1").set("radius", "z_gcl*5");
    model.component("comp1").geom("geom1").feature("wp14").geom().feature("fil1").selection("point")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").create("fil2", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "z_gcl*3");
    model.component("comp1").geom("geom1").feature("fil2").selection("edge").set("del3(1)", 20);
    model.component("comp1").geom("geom1").create("wp15", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp15").label("Work Plane - Hood Partitioning");
    model.component("comp1").geom("geom1").feature("wp15").set("quickz", "0.67*H+z_gcl");
    model.component("comp1").geom("geom1").feature("wp15").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp15").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp15").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("proj1").set("project", "bnd");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("proj1").set("projectiontype", "edgvtx");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("proj1").selection("input")
         .set("fil2(1)", 2, 5, 6, 7);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp15").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pol1")
         .set("tableconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pol1")
         .set("table", new String[][]{{"0", "0.85*W/2"}, {"L", "0.85*W/2"}});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp15").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ca1")
         .set("center", new double[]{1.62, 0});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ca1").set("r", 1.62);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ca1").set("angle1", 166.2680281683006);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ca1").set("angle2", -180);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ca1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp15").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos1").selection("vertex")
         .set("ca1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("hor1").selection("edge").set("ca1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("rad1").set("radius", "z_gcl*12");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("rad1").selection("edge").set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("pos2", "Position");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos2")
         .set("posconstr", new String[]{"off", "on"});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos2")
         .set("pos", new String[]{"0.04630476811296158", "0.4*W/2"});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos2").selection("vertex")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("uni1").selection("input")
         .set("ca1", "cro1.fil2", "pol1");
    model.component("comp1").geom("geom1").feature("wp15").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{-2.33172, -0.6740046997551888, -0.2644706299221195, 2.8545536}, {-4.46024, 0.12008536248295787, 1.3463441926048767, 0.6249035}});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cc1").feature("pol1").label("Polygon 2");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{2.8545536, 0.6249035}, {3.038465855736513, 0.5823639827098798}});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("cc1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp15").geom().create("pos3", "Position");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos3")
         .set("pos", new String[]{"air_inlet", "0.65*W/2"});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos3").selection("vertex")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi1")
         .set("helppoint1", new double[]{1.4500035287889828, 0.8171815});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi1").selection("entity2")
         .set("uni1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi2")
         .set("helppoint1", new double[]{0.04630476811296175, 0.384556});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi2").selection("entity2")
         .set("uni1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc1")
         .set("helppoint1", new double[]{1.4500035287889832, 0.8171815});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.046304768112960915, 0.384556});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc1").selection("edge2")
         .set("uni1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi3")
         .set("helppoint1", new double[]{3.0384658557365127, 0.5823639827098797});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi3").selection("entity1")
         .set("proj1.fil2", 17);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("coi3").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc2")
         .set("helppoint2", new double[]{2.8545536, 0.6249035});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("pos4", "Position");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos4")
         .set("pos", new String[]{"-2*H", "-L"});
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("pos4").selection("vertex")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ydist1")
         .set("distance", 0.26447063751704214);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ydist1").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("ydist1").selection("entity2")
         .set("uni1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp15").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("par1").selection("input").set("cc1");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("par1").selection("tool")
         .set("proj1", "uni1");
    model.component("comp1").geom("geom1").feature("wp15").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("extract1").selection("input")
         .set("par1(1)", 3, 4, 5, 6);
    model.component("comp1").geom("geom1").feature("wp15").geom().feature("extract1")
         .setAttribute("construction", "off");
    model.component("comp1").geom("geom1").create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").label("Extrude - Hood Partitioning");
    model.component("comp1").geom("geom1").feature("ext5").set("distance", new double[]{0.7, -0.7});
    model.component("comp1").geom("geom1").feature("ext5").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext5").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext5").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext5").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext5").selection("input").set("wp15");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").label("Partition Objects - Hood Partitioning");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("fil2");
    model.component("comp1").geom("geom1").feature("par2").selection("tool").set("ext5");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel4").label("Box Selection - Position Point");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", "x_w");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", "x_w+R_wh");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "W/2*0.8");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", "0.9*W/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmin", "R_wh+R_w");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmax", "0.67*H+z_gcl");
    model.component("comp1").geom("geom1").feature("boxsel4").set("selshow", false);
    model.component("comp1").geom("geom1").create("wp16", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp16").label("Work Plane - Hood Starting");
    model.component("comp1").geom("geom1").feature("wp16").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp16").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp16").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp16").selection("offsetvertex").named("boxsel4");
    model.component("comp1").geom("geom1").feature("wp16").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("cro1").selection("input").set("par2");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp16").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pol1")
         .set("table", new double[][]{{0.9335306129708023, 0.7907108121846929}, {0.8100802223158955, 0.8216946958526732}, {0.8998511028555319, 0.8152768296077039}});
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp16").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").set("distance", "z_gcl*2/3");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity1")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("dist1").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi1").selection("entity2")
         .set("cro1.par2", 3);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.8100802223158888, 0.8216946958526461});
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("tanc1").selection("edge1")
         .set("cro1.par2", 1);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("tanc1").selection("edge2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("ang1").set("angle", 10);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("ang1").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("ang1").selection("edge2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp16").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("pt1")
         .set("p", new double[]{0.8998511028555319, 0.8152768296077039});
    model.component("comp1").geom("geom1").feature("wp16").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity1")
         .set("pt1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp16").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp16").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp17", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp17").label("Work Plane - Outer Partitioning");
    model.component("comp1").geom("geom1").feature("wp17").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp17").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp17").selection("vertex1").set("wp16.pt1", 1);
    model.component("comp1").geom("geom1").feature("wp17").selection("vertex2").set("wp14.fil1", 1);
    model.component("comp1").geom("geom1").feature("wp17").selection("vertex3").set("par2(1)", 40);
    model.component("comp1").geom("geom1").feature("wp17").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("r1")
         .set("pos", new String[]{"0", "-H/2"});
    model.component("comp1").geom("geom1").feature("wp17").geom().feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").feature("wp17").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").label("Extrude - Window Frame");
    model.component("comp1").geom("geom1").feature("ext6").set("workplane", "wp14");
    model.component("comp1").geom("geom1").feature("ext6").set("distance", new String[]{"-W/8", "W/8"});
    model.component("comp1").geom("geom1").feature("ext6").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext6").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext6").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext6").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext6").selection("input").set("wp14");
    model.component("comp1").geom("geom1").feature("ext6").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").set("keep", true);
    model.component("comp1").geom("geom1").feature("int1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("int1").set("keeplowerdim", true);
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("ext6", "wp17");
    model.component("comp1").geom("geom1").feature("int1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("extract2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract2").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("extract2").selection("input").set("int1(1)", 1, 3);
    model.component("comp1").geom("geom1").feature("extract2").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp18", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp18").label("Work Plane - Rear Door Section");
    model.component("comp1").geom("geom1").feature("wp18").set("quickplane", "zy");
    model.component("comp1").geom("geom1").feature("wp18").set("quickx", "air_inlet*0.9");
    model.component("comp1").geom("geom1").feature("wp18").set("rot", -90);
    model.component("comp1").geom("geom1").feature("wp18").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp18").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp18").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cro1").selection("input")
         .set("ext6", "extract2", "par2");

    return model;
  }

  public static Model run5(Model model) {
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp18").geom().create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("boxsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("boxsel1").set("xmin", "-W/2");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("boxsel1").set("xmax", "-W/3");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("boxsel1").set("ymin", "H/2");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("boxsel1").set("ymax", "H*2/3");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature("wp18").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-0.5222193822003568, 0.8066936668195646}, {-0.8124698416365236, 0.7555146796579406}});
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{-0.8124698416365236, -0.94, -0.86, -0.7922193822003569}, {0.7555146796579406, 0.66, 0.28, 0.135}});
    model.component("comp1").geom("geom1").feature("wp18").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("coi1").selection("entity2")
         .named("boxsel1");
    model.component("comp1").geom("geom1").feature("wp18").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("coi2").selection("entity1")
         .set("cro1.par2", 1);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("coi2").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp18").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist1").set("distance", "z_gcl*2");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist1").selection("entity2")
         .set("cro1.ext6", 4);
    model.component("comp1").geom("geom1").feature("wp18").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("ang1").set("angle", 10);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("ang1").selection("edge1")
         .set("cro1.par2", 1);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp18").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist2").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("xdist2").selection("entity2")
         .set("cro1.ext6", 4);
    model.component("comp1").geom("geom1").feature("wp18").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("pare1")
         .set("param", new double[]{0.3, 0.6});
    model.component("comp1").geom("geom1").feature("wp18").geom().feature("pare1").selection("edge")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").create("wp19", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp19").label("Work Plane - Door Cut Start");
    model.component("comp1").geom("geom1").feature("wp19").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp19").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp19").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp19").selection("offsetvertex").set("wp6.fil2", 6);
    model.component("comp1").geom("geom1").feature("wp19").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cro1").selection("input")
         .set("ext6", "extract2", "par2");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp19").geom().create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("boxsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("boxsel1").set("xmin", "W/3");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("boxsel1").set("xmax", "W/2");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("boxsel1").set("ymin", "H/2");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("boxsel1").set("ymax", "H*3/4");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{0.6340032876255018, 0.8483937296210906}, {0.8784514563883171, 0.8140387799416935}});
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{0.8784514563883171, 0.9256019021844095, 0.96139, 0.96139}, {0.8140387799416935, 0.8074122169329208, 0.46, 0.405}});
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{0.96139, 0.405}, {0.96139, 0.135}});
    model.component("comp1").geom("geom1").feature("wp19").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi1").selection("entity2")
         .named("boxsel1");
    model.component("comp1").geom("geom1").feature("wp19").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("coi2").selection("entity2")
         .set("cro1.par2", 4);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("ver1").selection("edge").set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("dist1").set("distance", "z_gcl*2");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("dist1").selection("entity1")
         .set("cro1.par2", 3);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("dist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("dist1").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("ang1").set("angle", 8);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("ang1").selection("edge2")
         .set("cro1.par2", 3);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("xdist1").selection("entity2")
         .set("cro1.ext6", 1);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.8784514563883171, 0.8140387799416935});
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.96139, 0.405});
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp19").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("pare1").setIndex("param", "0.3", 0);
    model.component("comp1").geom("geom1").feature("wp19").geom().feature("pare1").selection("edge")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").create("wp20", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp20").label("Work Plane - Floor Guide Curve");
    model.component("comp1").geom("geom1").feature("wp20").set("quickz", "z_gcl");
    model.component("comp1").geom("geom1").feature("wp20").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cro1").selection("input")
         .set("par2", "wp18", "wp19");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp20").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("pol1")
         .set("table", new double[][]{{2.8545536, 0.7922193822003569}, {3.2749, 0.7922193822003569}});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp20").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi1").selection("entity1")
         .set("cro1.par2", 14);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi1").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi2").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi2").selection("entity2")
         .set("cro1.wp18", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("pos1")
         .set("posconstr", new String[]{"on", "off"});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("pos1")
         .set("pos", new String[]{"air_inlet", "0.7922193822003569"});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("pos1").selection("vertex")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{1.5032657727088077, 0.8263900000009563});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").feature("ca1")
         .set("r", 0.13499999999898182);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").feature("ca1")
         .set("angle1", 90.00000000061802);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").feature("ca1")
         .set("angle2", 75.00000000061802);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{1.5382063437950808, 2.203770281303075, 2.3032657727060895, 2.8545536}, {0.9567899865490483, 0.7784526669825034, 0.7922193822003569, 0.7922193822003569}});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").create("cb2", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("cc1").feature("cb2")
         .set("p", new double[][]{{2.8545536, 3.2399594289116047, 3.257429714455802, 3.2749}, {0.7922193822003569, 0.7922193822003569, 0.9264494289113738, 0.96139}});
    model.component("comp1").geom("geom1").feature("wp20").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi3").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi4").selection("entity2")
         .set("cro1.wp19", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi5").selection("entity1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi5").selection("entity2")
         .set("cro1.par2", 14);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi6")
         .set("helppoint1", new double[]{2.56909824, 0.7922193822003567});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("coi6").selection("entity2")
         .set("cro1.wp18", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist1").set("distance", 0.8);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("con1", "Concentric");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("con1").selection("edge").set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("con1").selection("edge")
         .set("cro1.par2", 3);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ang1").set("angle", 15);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1")
         .set("helppoint1", new double[]{1.5382063437962188, 0.9567899865490249});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1")
         .set("helppoint2", new double[]{1.5382063437962188, 0.9567899865490249});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc2")
         .set("helppoint1", new double[]{2.8545536, 0.7922193822003569});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc2").selection("vertex1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc2").selection("edge2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc2").selection("vertex2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc3")
         .set("helppoint1", new double[]{2.8545536, 0.7922193822003569});
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc3").selection("vertex1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc3").selection("edge2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("tanc3").selection("vertex2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ydist1")
         .set("distance", 0.03494057108862614);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ydist1").selection("entity1")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("ydist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2").set("distanceconstr", false);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2")
         .set("distance", 0.034940571088866834);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2")
         .set("parname", "geom1.wp20.xdist2");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist2").selection("entity2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("xdist3", "XDistance");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist3")
         .set("distance", "geom1.wp20.xdist2/2");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist3").selection("entity1")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist3").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("xdist4", "XDistance");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist4")
         .set("distance", "geom1.wp20.xdist2/2");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist4").selection("entity1")
         .set("cc1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("xdist4").selection("entity2")
         .set("cc1(1)", 9);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("extract1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("extract1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("extract1").selection("input")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp20").geom().create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("extract2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("extract2").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("extract2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp20").geom().feature("extract2").selection("input")
         .set("cc1(1)", 1, 3);
    model.component("comp1").geom("geom1").feature("wp20").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp21", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp21").label("Work Plane - Door Middle");
    model.component("comp1").geom("geom1").feature("wp21").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp21").set("quickx", "L/2");
    model.component("comp1").geom("geom1").feature("wp21").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp21").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp21").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cro1").selection("input")
         .set("ext6", "extract2", "par2", "wp20.extract2");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp21").geom().create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("boxsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("boxsel1").set("xmin", "W/3");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("boxsel1").set("xmax", "W/2");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("boxsel1").set("ymin", "H/2");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("boxsel1").set("ymax", "H*3/4");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature("wp21").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{0.5577712279961402, 0.7942361803213959}, {0.8416852447231902, 0.7492686176817911}});
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{0.8416852447231902, 0.96139, 0.9133205, 0.8086172529579161}, {0.7492686176817911, 0.6270120629966371, 0.26, 0.135}});
    model.component("comp1").geom("geom1").feature("wp21").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi1").selection("entity2")
         .named("boxsel1");
    model.component("comp1").geom("geom1").feature("wp21").geom().create("coi2", "Coincident");

    return model;
  }

  public static Model run6(Model model) {
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("coi2").selection("entity2")
         .set("cro1.wp20.extract2", 1);
    model.component("comp1").geom("geom1").feature("wp21").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("xdist1").selection("entity2")
         .set("cro1.ext6", 1);
    model.component("comp1").geom("geom1").feature("wp21").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("ang1").set("angle", 9);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("ang1").selection("edge2")
         .set("cro1.par2", 1);
    model.component("comp1").geom("geom1").feature("wp21").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("pare1")
         .set("param", new double[]{0.3, 0.7});
    model.component("comp1").geom("geom1").feature("wp21").geom().feature("pare1").selection("edge")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel5").label("Box Selection - Door Inclination");
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmin", "air_inlet*0.95");
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmax", "air_inlet*1.05");
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymin", "W/2*0.9");
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymax", "W/2");
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmin", "z_gcl");
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmax", "z_gcl*2");
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel5").set("selshow", false);
    model.component("comp1").geom("geom1").create("wp22", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp22").label("Work Plane - Door Inclination");
    model.component("comp1").geom("geom1").feature("wp22").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp22").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp22").selection("vertex1").named("boxsel5");
    model.component("comp1").geom("geom1").feature("wp22").selection("vertex2").set("wp20.extract2", 2);
    model.component("comp1").geom("geom1").feature("wp22").selection("vertex3").set("extract2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp22").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("cro1").selection("input")
         .set("ext6", "extract2", "par2", "wp20.extract1");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp22").geom().create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel1").set("xmin", "W/10");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel1").set("xmax", "W/6");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel1").set("ymin", "H/2");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("cb1")
         .set("p", new double[][]{{0.4075770568217464, -0.06334719769209765, 0.12110111779964308, 0.12110111779964305}, {0.9869963994751223, 0.9357764450479364, 0.36, 0}});
    model.component("comp1").geom("geom1").feature("wp22").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi1").selection("entity2")
         .set("cro1.par2", 2);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi2").selection("entity2")
         .set("cro1.wp20.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("xdist1").selection("entity1")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("xdist1").selection("entity2")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("ydist1").set("distance", 0.36);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("ydist1").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("ydist1").selection("entity2")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.4075770568217464, 0.9869963994751223});
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.4075770568217465, 0.9869963994751211});
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1").selection("vertex1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1").selection("edge2")
         .set("cro1.par2", 2);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("tanc1").selection("vertex2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi3")
         .set("helppoint2", new double[]{0.22345944711663454, 0.9215601825936781});
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi3").selection("entity1")
         .named("boxsel1");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("coi3").selection("entity2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("par1").selection("input").set("cb1");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("par1").selection("tool")
         .set("cro1.ext6", "cro1.par2");
    model.component("comp1").geom("geom1").feature("wp22").geom().create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("boxsel2").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("wp22").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("pare1")
         .set("param", new double[]{0.3, 0.6});
    model.component("comp1").geom("geom1").feature("wp22").geom().feature("pare1").selection("edge")
         .named("boxsel2");
    model.component("comp1").geom("geom1").create("par3", "Partition");
    model.component("comp1").geom("geom1").feature("par3").label("Partition Objects - Door Guide Curve");
    model.component("comp1").geom("geom1").feature("par3").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("par3").selection("input").set("extract2");
    model.component("comp1").geom("geom1").feature("par3").selection("tool").set("wp19");
    model.component("comp1").geom("geom1").feature("par3").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("loft2", "Loft");
    model.component("comp1").geom("geom1").feature("loft2").label("Loft - Door");
    model.component("comp1").geom("geom1").feature("loft2").set("unite", false);
    model.component("comp1").geom("geom1").feature("loft2").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft2").selection("profile").set("wp18", "wp21");
    model.component("comp1").geom("geom1").feature("loft2").selection("startprofile").init();
    model.component("comp1").geom("geom1").feature("loft2").selection("startprofile").set("wp19");
    model.component("comp1").geom("geom1").feature("loft2").selection("endprofile").init();
    model.component("comp1").geom("geom1").feature("loft2").selection("endprofile").set("wp22");
    model.component("comp1").geom("geom1").feature("loft2").selection("startguide").named("wp20_extract2");
    model.component("comp1").geom("geom1").feature("loft2").selection("endguide").set("par3(1)", 2, 3);
    model.component("comp1").geom("geom1").create("extract3", "Extract");
    model.component("comp1").geom("geom1").feature("extract3").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("extract3").selection("input").set("loft2(1)", 17);
    model.component("comp1").geom("geom1").create("wp23", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp23").label("Work Plane - Side Window");
    model.component("comp1").geom("geom1").feature("wp23").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp23").set("offsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp23").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp23").selection("offsetvertex").named("boxsel4");
    model.component("comp1").geom("geom1").feature("wp23").selection("face").set("wp17.r1", 1);
    model.component("comp1").geom("geom1").feature("wp23").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp23").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp23").geom().selection("csel1").label("Cumulative Selection 1");
    model.component("comp1").geom("geom1").feature("wp23").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("cro1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("cro1").selection("input")
         .set("ext6", "loft2", "par2");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp23").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("ca1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("ca1")
         .set("center", new double[]{-2.156928132029787, 1.39389233824524});
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("ca1").set("r", 1.428848175444899);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("ca1").set("angle1", -92.55127070650806);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("ca1").set("angle2", -81.57157948768493);
    model.component("comp1").geom("geom1").feature("wp23").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi1").selection("entity1")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi1").selection("entity2")
         .set("cro1.par2", 2);
    model.component("comp1").geom("geom1").feature("wp23").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi2").selection("entity1")
         .set("cro1.loft2", 2);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("coi2").selection("entity2")
         .set("ca1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp23").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1")
         .set("helppoint1", new double[]{-1.947496538461253, -0.01952391894425537});
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1")
         .set("helppoint2", new double[]{-1.9474965384612541, -0.019523918944255092});
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1").selection("edge1")
         .set("cro1.loft2", 2);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1").selection("vertex1")
         .set("ca1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1").selection("edge2")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("tanc1").selection("vertex2")
         .set("ca1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp23").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("csol1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp23").geom().create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("boxsel1").set("xmin", "-L/2");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("boxsel1").set("xmax", "L/2");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("boxsel1").set("ymin", -0.1);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("boxsel1").set("ymax", "H");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature("wp23").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp23").geom().feature("del1").selection("input")
         .named("boxsel1");
    model.component("comp1").geom("geom1").create("wp24", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp24").label("Work Plane - Roof Rails");
    model.component("comp1").geom("geom1").feature("wp24").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp24").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp24").selection("vertex1").named("boxsel4");
    model.component("comp1").geom("geom1").feature("wp24").selection("vertex2").set("wp23.del1", 3);
    model.component("comp1").geom("geom1").feature("wp24").selection("vertex3").set("extract3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp24").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("cro1").selection("input")
         .set("ext6", "loft2", "par2", "par3", "wp13");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp24").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("ca1")
         .set("center", new double[]{0.30487761792906787, 2.571700146383154});
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("ca1").set("r", 2.684311681257539);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("ca1").set("angle1", -79.4962261650949);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("ca1").set("angle2", -56.089789129412665);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi1").selection("entity1")
         .set("cro1.par2", 7);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi1").selection("entity2")
         .set("ca1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi2").selection("entity1")
         .set("cro1.par2", 4);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi2").selection("entity2")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi3")
         .set("helppoint1", new double[]{0.8714397988333175, -0.0521398825257533});
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi3").selection("entity1")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi3").selection("entity2")
         .set("cro1.par2", 5);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("pol1")
         .set("table", new double[][]{{1.8024363666881322, 0.34395532343981206}, {0.3048776179290671, 2.5717001463831566}});
    model.component("comp1").geom("geom1").feature("wp24").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi4").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi4").selection("entity2")
         .set("ca1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi5").selection("entity1")
         .set("ca1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("coi5").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("ls1").selection("vertex1")
         .set("cro1.par2", 3);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("ls1").selection("vertex2")
         .set("cro1.ext6", 3);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("csol1").selection("input")
         .set("ca1", "cro1", "ls1", "pol1");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("csol1")
         .setAttribute("construction", "off");
    model.component("comp1").geom("geom1").feature("wp24").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("extract1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("extract1").selection("input")
         .set("csol1(1)", 1, 6);
    model.component("comp1").geom("geom1").feature("wp24").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp24").geom().feature("uni1").selection("input").set("extract1");
    model.component("comp1").geom("geom1").create("parf2", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf2").label("Partition Faces - Partition Roof with Window");
    model.component("comp1").geom("geom1").feature("parf2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf2").set("workplane", "wp23");
    model.component("comp1").geom("geom1").feature("parf2").selection("face").set("ext6(1)", 1, 2, 3, 4, 5);
    model.component("comp1").geom("geom1").feature("parf2").selection("face").set("loft2(1)", 1);
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel6").label("Box Selection - Roof Rails");
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmin", "x_w");
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmax", "L-x_w");
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymax", "W");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmin", "H*2/3");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmax", "H*1.1");
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel6").set("selshow", false);
    model.component("comp1").geom("geom1").create("parf3", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf3").label("Partition Faces - Roof Rails");
    model.component("comp1").geom("geom1").feature("parf3").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf3").selection("face").named("boxsel6");
    model.component("comp1").geom("geom1").create("extract4", "Extract");
    model.component("comp1").geom("geom1").feature("extract4").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract4").selection("input").set("parf3(3)", 12, 14);
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("extract4", "parf2(2)");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel7").label("Box Selection - Delete Boundaries 1");
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmax", "L");
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmax", "H");
    model.component("comp1").geom("geom1").feature("boxsel7").set("selshow", false);
    model.component("comp1").geom("geom1").create("boxsel8", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel8").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel8").label("Box Selection - Delete Boundaries 2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("xmin", "x_w/2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("xmax", "L");
    model.component("comp1").geom("geom1").feature("boxsel8").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel8").set("ymax", "W/2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmax", "H*0.45");
    model.component("comp1").geom("geom1").feature("boxsel8").set("selshow", false);
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("Explicit Selection - Delete Boundaries");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("parf3(1)", 13);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("parf3(2)", 1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("uni3(1)", 2, 4, 5);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("wp23.del1", 1);
    model.component("comp1").geom("geom1").feature("sel3").set("selshow", false);
    model.component("comp1").geom("geom1").create("comsel2", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel2").label("Complement Selection - Delete Boundaries");
    model.component("comp1").geom("geom1").feature("comsel2")
         .set("input", new String[]{"boxsel7", "boxsel8", "sel3"});
    model.component("comp1").geom("geom1").feature("comsel2").set("selshow", false);
    model.component("comp1").geom("geom1").create("del4", "Delete");
    model.component("comp1").geom("geom1").feature("del4").label("Delete Entities - Roof and Hood");
    model.component("comp1").geom("geom1").feature("del4").selection("input").named("comsel2");
    model.component("comp1").geom("geom1").create("uni4", "Union");
    model.component("comp1").geom("geom1").feature("uni4").label("Union - Roof and Door");
    model.component("comp1").geom("geom1").feature("uni4").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni4").selection("input")
         .set("del4", "ext4(1)", "parf3(2)", "wp23", "wp7.del1(1)", "wp7.uni1");
    model.component("comp1").geom("geom1").create("pare3", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare3").label("Partition Edges - Roof Loft");
    model.component("comp1").geom("geom1").feature("pare3").setIndex("param", "0.3", 0);
    model.component("comp1").geom("geom1").feature("pare3").selection("edge").set("extract3(1)", 1);
    model.component("comp1").geom("geom1").create("pare4", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare4").label("Partition Edges - Roof Loft (roof edge)");
    model.component("comp1").geom("geom1").feature("pare4").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare4").selection("edge").set("uni4(1)", 66);
    model.component("comp1").geom("geom1").feature("pare4").selection("vertexproj").set("pare3(1)", 3);
    model.component("comp1").geom("geom1").create("boxsel9", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel9").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel9").label("Box Selection - Roof (end guide)");
    model.component("comp1").geom("geom1").feature("boxsel9").set("xmin", "x_rbc*1.07");
    model.component("comp1").geom("geom1").feature("boxsel9").set("xmax", "x_rbc*1.1");
    model.component("comp1").geom("geom1").feature("boxsel9").set("ymin", "W*0.25");
    model.component("comp1").geom("geom1").feature("boxsel9").set("ymax", "W*0.28");

    return model;
  }

  public static Model run7(Model model) {
    model.component("comp1").geom("geom1").feature("boxsel9").set("zmin", "H/2");
    model.component("comp1").geom("geom1").feature("boxsel9").set("zmax", "H");
    model.component("comp1").geom("geom1").feature("boxsel9").set("selshow", false);
    model.component("comp1").geom("geom1").create("loft3", "Loft");
    model.component("comp1").geom("geom1").feature("loft3").label("Loft - Roof");
    model.component("comp1").geom("geom1").feature("loft3").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft3").selection("profile").set();
    model.component("comp1").geom("geom1").feature("loft3").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft3").selection("startprofile").set("pare4(1)", 63, 65);
    model.component("comp1").geom("geom1").feature("loft3").selection("endprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft3").selection("endprofile").set("pare3(1)", 1, 2);
    model.component("comp1").geom("geom1").feature("loft3").selection("guide").set();
    model.component("comp1").geom("geom1").feature("loft3").selection("startguide").set("pare4(1)", 64);
    model.component("comp1").geom("geom1").feature("loft3").selection("endguide").named("boxsel9");
    model.component("comp1").geom("geom1").create("wp25", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp25").label("Work Plane - Rear Top Loft Position");
    model.component("comp1").geom("geom1").feature("wp25").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp25").set("offsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp25").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp25").selection("offsetvertex").set("loft3(1)", 53);
    model.component("comp1").geom("geom1").feature("wp25").selection("face").set("loft3(1)", 8);
    model.component("comp1").geom("geom1").feature("wp25").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp25").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp25").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp25").geom().feature("cro1").selection("input").set("loft3");
    model.component("comp1").geom("geom1").feature("wp25").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp25").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp25").geom().feature("ls1").selection("vertex1")
         .set("cro1.loft3", 11);
    model.component("comp1").geom("geom1").feature("wp25").geom().feature("ls1").selection("vertex2")
         .set("cro1.loft3", 17);
    model.component("comp1").geom("geom1").create("pare5", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare5").label("Partition Edges - Rear Edge 1");
    model.component("comp1").geom("geom1").feature("pare5").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare5").selection("edge").set("loft3(1)", 91);
    model.component("comp1").geom("geom1").feature("pare5").selection("vertexproj").set("wp25.ls1", 2);
    model.component("comp1").geom("geom1").create("pare6", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare6").label("Partition Edges - Rear Edge 2");
    model.component("comp1").geom("geom1").feature("pare6").setIndex("param", "0.3", 0);
    model.component("comp1").geom("geom1").feature("pare6").selection("edge").set("pare5(1)", 103);
    model.component("comp1").geom("geom1").create("loft4", "Loft");
    model.component("comp1").geom("geom1").feature("loft4").label("Loft - Rear to Rail");
    model.component("comp1").geom("geom1").feature("loft4").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft4").selection("profile").set();
    model.component("comp1").geom("geom1").feature("loft4").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft4").selection("startprofile").set("pare6(1)", 77, 81);
    model.component("comp1").geom("geom1").feature("loft4").selection("endprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft4").selection("endprofile").set("pare6(1)", 103, 104);
    model.component("comp1").geom("geom1").feature("loft4").selection("guide").set("wp25");
    model.component("comp1").geom("geom1").feature("loft4").selection("startguide").set("pare6(1)", 82, 92);
    model.component("comp1").geom("geom1").create("boxsel10", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel10").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel10").label("Box Selection - Circular Edge");
    model.component("comp1").geom("geom1").feature("boxsel10").set("xmin", "x_w+L_wb-R_wh");
    model.component("comp1").geom("geom1").feature("boxsel10").set("xmax", "x_w+L_wb");
    model.component("comp1").geom("geom1").feature("boxsel10").set("ymin", "W/2*0.95");
    model.component("comp1").geom("geom1").feature("boxsel10").set("ymax", "W/2*1.05");
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmin", "z_gcl+R_wh/4");
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmax", "z_gcl+R_wh*2");
    model.component("comp1").geom("geom1").feature("boxsel10").set("selshow", false);
    model.component("comp1").geom("geom1").create("wp26", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp26").label("Work Plane - Rear Loft");
    model.component("comp1").geom("geom1").feature("wp26").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp26").set("workplane", "wp22");
    model.component("comp1").geom("geom1").feature("wp26").set("transdispl", new String[]{"0", "H*0.7", "0"});
    model.component("comp1").geom("geom1").feature("wp26").set("transaxis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("wp26").set("transrot", 105);
    model.component("comp1").geom("geom1").feature("wp26").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp26").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp26").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("cro1").selection("input").set("loft4");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp26").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("cb1")
         .set("p", new double[][]{{0.13642769963714485, 0.0800802705141966, 1.5045515839527118E-4, 0.03232244031984077}, {-6.607603353359082E-12, -0.17567848319451815, -0.5944529367863314, -0.7115510840552179}});
    model.component("comp1").geom("geom1").feature("wp26").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi1").selection("entity2")
         .set("cro1.loft4", 2);
    model.component("comp1").geom("geom1").feature("wp26").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("coi2").selection("entity2")
         .set("cro1.loft4", 1);
    model.component("comp1").geom("geom1").feature("wp26").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.03232244031984077, -0.7115510840552179});
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.03232244031984077, -0.7115510840552179});
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc1").selection("edge1")
         .set("cro1.loft4", 2);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc1").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp26").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.13642769963714485, -6.607603353359082E-12});
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.13642769963714485, -6.607603353359082E-12});
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2").selection("edge1")
         .set("cro1.loft4", 3);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2").selection("vertex1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp26").geom().feature("tanc2").selection("vertex2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp26").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("wp27", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp27").label("Work Plane - Guide Curve Rear Loft 1");
    model.component("comp1").geom("geom1").feature("wp27").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp27").set("circpoint", "othervertex");
    model.component("comp1").geom("geom1").feature("wp27").selection("circedge").named("boxsel10");
    model.component("comp1").geom("geom1").feature("wp27").selection("circvertex").set("loft4(1)", 53);
    model.component("comp1").geom("geom1").feature("wp27").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("cro1").selection("input")
         .set("loft4", "wp26");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp27").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("cb1")
         .set("p", new double[][]{{0.37, 0.4133676585527851, 0.6310341483334669, 0.7264046936515429}, {0, -0.021442312978156408, -0.17207488064036747, -0.27152894711663045}});
    model.component("comp1").geom("geom1").feature("wp27").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi1").selection("entity2")
         .set("cro1.loft4", 5);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi2").selection("entity2")
         .set("cro1.loft4", 6);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi3")
         .set("helppoint1", new double[]{0.5876664897806815, -0.15105829739712434});
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi3").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi3").selection("entity2")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.7264046936515429, -0.27152894711663045});
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.7264046936515429, -0.27152894711663045});
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1").selection("edge1")
         .set("cro1.loft4", 5);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1").selection("vertex1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("tanc1").selection("vertex2")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi4")
         .set("helppoint1", new double[]{0.5876664897806803, -0.15105829739712337});
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi4").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("coi4").selection("entity2")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1").set("distanceconstr", false);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1")
         .set("distance", 0.04336765855278546);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1")
         .set("parname", "geom1.wp27.xdist1");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist1").selection("entity2")
         .set("cro1.loft4", 7);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist2")
         .set("distance", "geom1.wp27.xdist1");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist2").selection("entity1")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist2").selection("entity2")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp27").geom().create("xdist3", "XDistance");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist3")
         .set("distance", "geom1.wp27.xdist1");
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist3").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp27").geom().feature("xdist3").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").create("wp28", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp28").label("Work Plane - Guide Curve Rear Loft 2");
    model.component("comp1").geom("geom1").feature("wp28").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp28").set("quickx", "x_w+L_wb");
    model.component("comp1").geom("geom1").feature("wp28").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp28").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("cro1").selection("input")
         .set("loft4", "wp26");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp28").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("cb1")
         .set("p", new double[][]{{0.6817816175733239, 0.8332600736985697, 0.8907559495117151, 0.96139}, {0.8900153979823212, 0.8002431636853063, 0.7598977037125823, 0.7}});
    model.component("comp1").geom("geom1").feature("wp28").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi1").selection("entity2")
         .set("cro1.loft4", 7);
    model.component("comp1").geom("geom1").feature("wp28").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi2")
         .set("helppoint1", new double[]{0.8698401369358664, 0.771343852971084});
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi2").selection("entity2")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp28").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi3").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("coi3").selection("entity2")
         .set("cro1.loft4", 9);
    model.component("comp1").geom("geom1").feature("wp28").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.6817816175733239, 0.8900153979823212});
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.6817816175733239, 0.8900153979823212});
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1").selection("edge1")
         .set("cro1.loft4", 4);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1").selection("vertex1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp28").geom().feature("tanc1").selection("vertex2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").create("wp29", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp29").label("Work Plane - Guide Curve Rear Loft 3");
    model.component("comp1").geom("geom1").feature("wp29").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp29").set("circoffset", 30);
    model.component("comp1").geom("geom1").feature("wp29").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp29").selection("circedge").named("boxsel10");
    model.component("comp1").geom("geom1").feature("wp29").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("cro1").selection("input")
         .set("loft4", "wp26");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp29").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("proj1").selection("input").set("wp27");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp29").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("pol1")
         .set("table", new double[][]{{0.3383011543552619, 0}, {0.632984906054257, -0.2556436819852664}, {0.632984906054257, -0.2281443388128584}, {0.37, 0}, {0.3383011543552619, 0}});
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp29").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("ver1").selection("edge")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("para1", "Parallel");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("para1").selection("edge")
         .set("pol1(1)", 1, 3);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.38948965072631836, -0.03951200842857361});
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.48745375066200514, -0.1293926749529975});
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc1").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc1").selection("edge2")
         .set("proj1.wp27", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi1").selection("entity2")
         .set("proj1.wp27", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi2").selection("entity1")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi2").selection("entity2")
         .set("cro1.loft4", 5);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi3").selection("entity1")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi3").selection("entity2")
         .set("proj1.wp27", 2);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("eqdist1", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity1")
         .set("proj1.wp27", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity3")
         .set("proj1.wp27", 2);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("eqdist1").selection("entity4")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("cb1")
         .set("p", new double[][]{{0.6151651258668873, 0.5414449584786849, 0.40528083621323513, 0.37}, {-0.1338844173903566, -0.148731717275241, -0.09768839225225885, 0}});
    model.component("comp1").geom("geom1").feature("wp29").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi4").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi4").selection("entity2")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi5").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi5").selection("entity2")
         .set("cro1.loft4", 6);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.6151651258668873, -0.1338844173903566});
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.6151651258668872, -0.1338844173903566});
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2").selection("vertex1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2").selection("edge2")
         .set("cro1.loft4", 6);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc2").selection("vertex2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc3")
         .set("helppoint1", new double[]{0.4216351599448357, -0.07229381294581912});
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc3")
         .set("helppoint2", new double[]{0.48222631216049194, -0.10165923833847046});
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc3").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("tanc3").selection("edge2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp29").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi6").selection("entity1")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp29").geom().feature("coi6").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").create("wp30", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp30").label("Work Plane - Guide Curve Rear Loft 4");
    model.component("comp1").geom("geom1").feature("wp30").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp30").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp30").selection("circedge").named("boxsel10");
    model.component("comp1").geom("geom1").feature("wp30").geom().useConstrDim(true);

    return model;
  }

  public static Model run8(Model model) {
    model.component("comp1").geom("geom1").feature("wp30").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("cro1").selection("input").set("loft4");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp30").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("proj1").selection("input")
         .set("wp20.extract1", "wp29");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp30").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("pol1")
         .set("table", new double[][]{{0.31716325534104634, 0}, {0.42971398143128037, -0.2105185834114059}, {0.47928458203103674, -0.21051858341140586}, {0.3667338559408027, 0}, {0.31716325534104634, 0}});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp30").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 2, 4);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("para1", "Parallel");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("para1").selection("edge")
         .set("pol1(1)", 1, 3);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.3324467853373866, -0.028586817661004682});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc1").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc1").selection("edge2")
         .set("proj1.wp29", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.38612427866121524, -0.03626848501694127});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc2").selection("edge1")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc2").selection("edge2")
         .set("proj1.wp20.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi1").selection("entity2")
         .set("proj1.wp29", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("eqdist1", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("eqdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("eqdist1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("eqdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("eqdist1").selection("entity2")
         .set("proj1.wp29", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("eqdist1").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("eqdist1").selection("entity3")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("eqdist1").selection("entity4")
         .set("cro1.loft4", 6);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("cb1")
         .set("p", new double[][]{{0.6903995066205115, 0.644606103966594, 0.4126356841966421, 0.37}, {-0.15538253840176508, -0.1613952192341369, -0.14071021816040677, 0}});
    model.component("comp1").geom("geom1").feature("wp30").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi2").selection("entity2")
         .set("cro1.loft4", 8);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi3").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi3").selection("entity2")
         .set("cro1.loft4", 7);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3")
         .set("helppoint1", new double[]{0.6903995066205115, -0.15538253840176505});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3")
         .set("helppoint2", new double[]{0.6903995066205115, -0.15538253840176508});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3").selection("edge1")
         .set("cro1.loft4", 7);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3").selection("vertex1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc3").selection("vertex2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("tanc4", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc4")
         .set("helppoint1", new double[]{0.38124324465188153, -0.02713883831520338});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc4")
         .set("helppoint2", new double[]{0.38409975744431196, -0.03285820035460322});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc4").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("tanc4").selection("edge2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi4")
         .set("helppoint1", new double[]{0.5327486265229767, -0.13388441739035717});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi4").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi4").selection("entity2")
         .set("proj1.wp29", 2);
    model.component("comp1").geom("geom1").feature("wp30").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi5")
         .set("helppoint1", new double[]{0.6446061039665941, -0.16139521923413694});
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi5").selection("entity1")
         .set("proj1.wp20.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp30").geom().feature("coi5").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").create("wp31", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp31").label("Work Plane - Guide Curve Rear Loft 5");
    model.component("comp1").geom("geom1").feature("wp31").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp31").set("circpoint", "endvertex");
    model.component("comp1").geom("geom1").feature("wp31").set("circoffset", -5);
    model.component("comp1").geom("geom1").feature("wp31").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp31").selection("circedge").named("boxsel10");
    model.component("comp1").geom("geom1").feature("wp31").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp31").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("cro1").selection("input")
         .set("loft4", "wp26");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp31").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("proj1").selection("input").set("wp28");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp31").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("cb1")
         .set("p", new double[][]{{0.37, 0.4245660748719093, 0.4553355135663955, 0.5610054045253401}, {0, -0.05422493814734536, -0.08813076199337594, -0.2839016349512892}});
    model.component("comp1").geom("geom1").feature("wp31").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi1").selection("entity2")
         .set("cro1.loft4", 5);
    model.component("comp1").geom("geom1").feature("wp31").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi2").selection("entity2")
         .set("cro1.loft4", 6);
    model.component("comp1").geom("geom1").feature("wp31").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi3")
         .set("helppoint1", new double[]{0.4361327485151202, -0.07464271568626238});
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi3").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("coi3").selection("entity2")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().create("eqdist1", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity1")
         .set("proj1.wp28", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity3")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist1").selection("entity4")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().create("eqdist2", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity1")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity2")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity3")
         .set("cro1.wp26", 1);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("eqdist2").selection("entity4")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp31").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.5610054045216475, -0.28390163495483917});
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.5610054045253401, -0.2839016349512892});
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("tanc1").selection("edge1")
         .set("cro1.loft4", 5);
    model.component("comp1").geom("geom1").feature("wp31").geom().feature("tanc1").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").create("boxsel11", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel11").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel11").set("xmin", "x_w+L_wb-R_wh*1.05");
    model.component("comp1").geom("geom1").feature("boxsel11").set("xmax", "x_w+L_wb+R_wh*0.4");
    model.component("comp1").geom("geom1").feature("boxsel11").set("ymin", "W/2*0.9");
    model.component("comp1").geom("geom1").feature("boxsel11").set("ymax", "W/2*1.05");
    model.component("comp1").geom("geom1").feature("boxsel11").set("zmin", "R_w*1.05");
    model.component("comp1").geom("geom1").feature("boxsel11").set("zmax", "H");
    model.component("comp1").geom("geom1").feature("boxsel11").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel11").set("selshow", false);
    model.component("comp1").geom("geom1").create("pare7", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare7").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare7").selection("edge").named("boxsel10");
    model.component("comp1").geom("geom1").feature("pare7").selection("vertexproj").named("boxsel11");
    model.component("comp1").geom("geom1").create("wp32", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp32").label("Work Plane - Profile Rear Loft");
    model.component("comp1").geom("geom1").feature("wp32").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp32").set("workplane", "wp8");
    model.component("comp1").geom("geom1").feature("wp32").set("transdispl", new double[]{0, 0, 0.33});
    model.component("comp1").geom("geom1").feature("wp32").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp32").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cro1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cro1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cro1").selection("input")
         .set("pare7", "wp20.extract1", "wp27", "wp28", "wp29", "wp30", "wp31");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp32").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("proj1").set("project", "edg");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("proj1").selection("input")
         .set("pare7(1)", 78);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp32").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cc1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cc1").feature("cb1")
         .set("p", new double[][]{{-0.2548372032776136, -0.2669768902656781, -0.3393584790799341, -0.2964411289892819}, {0.4300637454292122, 0.5130637454292122, 1.074231211136889, 1.1410662955462199}});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cc1").create("cb2", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("cc1").feature("cb2")
         .set("p", new double[][]{{-0.2964411289892819, -0.20023742737469746, 0.228, 0.38019928600009306}, {1.1410662955462199, 1.2908840849558583, 1.365, 1.3205473286154294}});
    model.component("comp1").geom("geom1").feature("wp32").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi1").selection("entity2")
         .set("cro1.wp20.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi2").selection("entity2")
         .set("cro1.pare7", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi3").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi3").selection("entity2")
         .set("cro1.wp27", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi4")
         .set("helppoint1", new double[]{0.18519928600009644, 1.3297065705020585});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi4").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi4").selection("entity2")
         .set("cro1.wp30", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi5")
         .set("helppoint1", new double[]{-0.06023638971066199, 1.2818608851691808});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi5").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi5").selection("entity2")
         .set("cro1.wp29", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi6")
         .set("helppoint1", new double[]{-0.30153366017205147, 0.8544221949907013});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi6").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi6").selection("entity2")
         .set("cro1.wp28", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi7")
         .set("helppoint1", new double[]{-0.28503809247010087, 0.6774972035937974});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi7").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("coi7").selection("entity2")
         .set("cro1.wp31", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1")
         .set("helppoint1", new double[]{-0.2964411289892819, 1.1410662955462199});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1")
         .set("helppoint2", new double[]{-0.2964411289892819, 1.1410662955462199});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1").selection("vertex1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("tanc1").selection("vertex2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("pos1")
         .set("pos", new double[]{0.228, 1.365});
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("pos1").selection("vertex")
         .set("cc1(1)", 7);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("ydist1").set("distance", 0.083);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("ydist1").selection("entity1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("ydist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp32").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("par1").selection("input").set("cc1");
    model.component("comp1").geom("geom1").feature("wp32").geom().feature("par1").selection("tool").named("cro1");
    model.component("comp1").geom("geom1").create("par4", "Partition");
    model.component("comp1").geom("geom1").feature("par4").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par4").selection("input")
         .set("wp20.extract1", "wp27", "wp28", "wp29", "wp30", "wp31");
    model.component("comp1").geom("geom1").create("pare8", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare8").label("Partition Edges - Roof with Guide Curves");
    model.component("comp1").geom("geom1").feature("pare8").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare8").selection("edge").set("pare7(1)", 78);
    model.component("comp1").geom("geom1").feature("pare8").selection("vertexproj").set("par4(3)", 1);
    model.component("comp1").geom("geom1").feature("pare8").selection("vertexproj").set("par4(6)", 3);
    model.component("comp1").geom("geom1").create("pare9", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare9")
         .label("Partition Edges - Start and End Guide Curves with Profile");
    model.component("comp1").geom("geom1").feature("pare9").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare9").selection("edge").set("pare8(1)", 97);
    model.component("comp1").geom("geom1").feature("pare9").selection("vertexproj").set("wp32.par1", 4);
    model.component("comp1").geom("geom1").create("pare10", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare10").label("Partition Edges - Door with Guide Curves");
    model.component("comp1").geom("geom1").feature("pare10").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare10").selection("edge").set("pare9(1)", 57, 62);
    model.component("comp1").geom("geom1").feature("pare10").selection("vertexproj").set("par4(4)", 1);
    model.component("comp1").geom("geom1").feature("pare10").selection("vertexproj").set("par4(5)", 1);
    model.component("comp1").geom("geom1").create("boxsel12", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel12").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel12").label("Box Selection - Partition Edges 11");
    model.component("comp1").geom("geom1").feature("boxsel12").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel12").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").feature("boxsel12").set("xmin", "L-x_w-R_wh/2");
    model.component("comp1").geom("geom1").feature("boxsel12").set("xmax", "L-x_w+R_wh/2");
    model.component("comp1").geom("geom1").feature("boxsel12").set("ymin", "W/2-W_w");
    model.component("comp1").geom("geom1").feature("boxsel12").set("ymax", "W/2*1.1");
    model.component("comp1").geom("geom1").feature("boxsel12").set("zmin", "z_gcl*3/2");
    model.component("comp1").geom("geom1").feature("boxsel12").set("zmax", "H*2/3");
    model.component("comp1").geom("geom1").feature("boxsel12").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel12").set("selkeep", false);
    model.component("comp1").geom("geom1").feature("boxsel12").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("boxsel12");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("Explicit Selection - Partition Edges 11");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("wp32.par1", 3, 5);
    model.component("comp1").geom("geom1").feature("sel4").set("selkeep", false);
    model.component("comp1").geom("geom1").feature("sel4").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").create("pare11", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare11").selection("edge").named("csel1");
    model.component("comp1").geom("geom1").create("loft5", "Loft");
    model.component("comp1").geom("geom1").feature("loft5").label("Loft - Rear Section");
    model.component("comp1").geom("geom1").feature("loft5").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft5").selection("profile").set("pare11(1)");
    model.component("comp1").geom("geom1").feature("loft5").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft5").selection("startprofile")
         .set("pare11(2)", 57, 59, 63, 69, 71, 80, 96, 103);
    model.component("comp1").geom("geom1").feature("loft5").selection("endprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft5").selection("endprofile").named("sel1");
    model.component("comp1").geom("geom1").feature("loft5").selection("guide")
         .set("par4(2)", "par4(3)", "par4(4)", "par4(5)", "par4(6)");
    model.component("comp1").geom("geom1").feature("loft5").selection("startguide").named("wp20_extract1");
    model.component("comp1").geom("geom1").feature("loft5").selection("endguide").set("pare11(2)", 101, 114);
    model.component("comp1").geom("geom1").create("boxsel13", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel13").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel13").label("Box Selection - Outer Vertex Hood");
    model.component("comp1").geom("geom1").feature("boxsel13").set("xmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel13").set("xmax", "x_w");
    model.component("comp1").geom("geom1").feature("boxsel13").set("ymin", "W/5");
    model.component("comp1").geom("geom1").feature("boxsel13").set("ymax", "W/4");
    model.component("comp1").geom("geom1").feature("boxsel13").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel13").set("selshow", false);

    return model;
  }

  public static Model run9(Model model) {
    model.component("comp1").geom("geom1").create("boxsel14", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel14").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel14").label("Box Selection - Hood Projection");
    model.component("comp1").geom("geom1").feature("boxsel14").set("xmin", "x_w/4");
    model.component("comp1").geom("geom1").feature("boxsel14").set("xmax", "x_w/2");
    model.component("comp1").geom("geom1").feature("boxsel14").set("ymin", "W/2*0.4");
    model.component("comp1").geom("geom1").feature("boxsel14").set("ymax", "W/2*1.05");
    model.component("comp1").geom("geom1").feature("boxsel14").set("zmin", "z_gcl");
    model.component("comp1").geom("geom1").feature("boxsel14").set("zmax", "H");
    model.component("comp1").geom("geom1").feature("boxsel14").set("selshow", false);
    model.component("comp1").geom("geom1").create("wp33", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp33").label("Work Plane - Inclined Hood");
    model.component("comp1").geom("geom1").feature("wp33").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp33").selection("vertex1").set("loft5(1)", 2);
    model.component("comp1").geom("geom1").feature("wp33").selection("vertex2").named("boxsel13");
    model.component("comp1").geom("geom1").feature("wp33").selection("vertex3").set("loft5(1)", 53);
    model.component("comp1").geom("geom1").feature("wp33").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("cro1").selection("input")
         .set("extract1(1)", "loft5");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp33").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("proj1").set("project", "edg");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("proj1").selection("input")
         .named("boxsel14");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp33").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("c1")
         .set("pos", new double[]{-1.3174852920340858, 1.603993248495252});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("c1").set("r", 2.3416239594674746);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("c1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp33").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ls1").selection("vertex1")
         .set("proj1.loft5", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ls1").selection("vertex2")
         .set("proj1.loft5", 5);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ls1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp33").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.3885066265674546, 0});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.3885066265674546, 0});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc1").selection("edge1")
         .set("proj1.loft5", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc1").selection("edge2")
         .set("c1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi1")
         .set("helppoint1", new double[]{0.38850662656745716, 0});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi1").selection("entity1")
         .set("c1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi1").selection("entity2")
         .set("cro1.loft5", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi2")
         .set("helppoint1", new double[]{0.9841861257940718, 1.173282844737632});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi2").selection("entity1")
         .set("c1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi2").selection("entity2")
         .set("proj1.loft5", 4);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca1")
         .set("center", new double[]{-0.22756287280952436, 1.3676470202275877});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca1").set("r", 1.5);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca1").set("angle1", -65.7503678079892);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca1").set("angle2", -46.20453017714197);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca2")
         .set("center", new double[]{-0.010945959057196086, 1.1417252368307262});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca2").set("r", 1.187008722906209);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca2").set("angle1", -46.20453017714188);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("ca2").set("angle2", -26.39391626262936);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi3").selection("entity1")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi3").selection("entity2")
         .set("cro1.loft5", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi4").selection("entity1")
         .set("ca2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi4").selection("entity2")
         .set("cro1.loft5", 9);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi5").selection("entity1")
         .set("ls1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi5").selection("entity2")
         .set("ca2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi6").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi6").selection("entity1")
         .set("ca1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("coi6").selection("entity2")
         .set("ca2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.810566284160966, 0.2849245934497284});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc2").selection("edge1")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc2").selection("edge2")
         .set("ls1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc3")
         .set("helppoint1", new double[]{0.8105662841609611, 0.28492459344972354});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc3")
         .set("helppoint2", new double[]{0.8105662841609611, 0.28492459344972354});
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc3").selection("edge1")
         .set("ca1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("tanc3").selection("edge2")
         .set("ca2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp33").geom().create("rad1", "Radius");
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("rad1").set("radius", 1.5);
    model.component("comp1").geom("geom1").feature("wp33").geom().feature("rad1").selection("edge").set("ca1(1)", 1);
    model.component("comp1").geom("geom1").create("wp34", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp34").label("Work Plane - Front Wheel Arch");
    model.component("comp1").geom("geom1").feature("wp34").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp34").set("reverse", true);
    model.component("comp1").geom("geom1").feature("wp34").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp34").selection("vertex1").set("wp33.ca2", 1);
    model.component("comp1").geom("geom1").feature("wp34").selection("vertex2").set("loft5(1)", 18);
    model.component("comp1").geom("geom1").feature("wp34").selection("vertex3").set("wp6.fil2", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("cro1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("cro1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("cro1").selection("input")
         .set("loft5", "wp33");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp34").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("proj1").set("project", "edg");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("proj1").selection("input")
         .set("loft5(1)", 5, 19);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp34").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol1")
         .set("table", new double[][]{{-0.32470478510950523, -0.30418029823734677}, {0.13996603809570268, 0.13111882905172564}, {-0.3727474617639729, -0.16429862628473865}});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi1").selection("entity2")
         .set("cro1.wp33.ca2", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi2").selection("entity1")
         .set("proj1.loft5", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi3").selection("entity1")
         .set("cro1.loft5", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi3").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc1")
         .set("helppoint1", new double[]{-0.3727474617639709, -0.16429862628473757});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc1").selection("edge1")
         .set("proj1.loft5", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc1").selection("edge2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol2")
         .set("table", new double[][]{{-0.15730109326642094, -0.09063441611568301}, {0.5134455684169276, 0.29583926172614267}, {1.1840473631534327, 0}});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol2").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp34").geom().create("para1", "Parallel");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("para1").selection("edge")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("para1").selection("edge")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi4").selection("entity1")
         .set("cro1.loft5", 4);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi4").selection("entity2")
         .set("pol2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi5").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi5").selection("entity2")
         .set("cro1.wp33.ca2", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc2")
         .set("helppoint1", new double[]{1.1840473631534327, 0});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc2").selection("edge1")
         .set("cro1.loft5", 4);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc2").selection("vertex1")
         .set("pol2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc2").selection("edge2")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc2").selection("vertex2")
         .set("pol2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol3")
         .set("table", new double[][]{{-0.008363428963961481, -0.4517469982429917}, {0.594842455319321, 0.11333043268532722}});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("pol3").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp34").geom().create("para2", "Parallel");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("para2").selection("edge")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("para2").selection("edge")
         .set("pol3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi6").selection("entity1")
         .set("pol3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi6").selection("entity2")
         .set("cro1.loft5", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi7", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi7")
         .set("helppoint1", new double[]{0.594842455319321, 0.11333043268532725});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi7").selection("entity1")
         .set("proj1.loft5", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi7").selection("entity2")
         .set("pol3(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("mov1").set("specify", "pos");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("mov1").selection("input").set("pol3");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("mov1").selection("oldposvertex")
         .set("pol3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("mov1").selection("newposvertices")
         .set("proj1.loft5", 2, 3);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("cb1")
         .set("p", new double[][]{{1.1840473631534327, 0.8084019466497162, 0.23411269718467795, 0}, {0, 0.16571781280861883, 0.13489205430163692, 0}});
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi8", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi8").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi8").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi8").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi8").selection("entity2")
         .set("cro1.loft5", 4);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3")
         .set("helppoint1", new double[]{1.1840473631534327, 0});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3")
         .set("helppoint2", new double[]{1.1840473631534327, 0});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3").selection("edge1")
         .set("cro1.loft5", 4);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3").selection("vertex1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc3").selection("vertex2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("tanc4", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc4")
         .set("helppoint1", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc4").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("tanc4").selection("edge2")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("coi9", "Coincident");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi9").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi9").selection("entity1")
         .set("cro1.wp33.ca2", 1);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi9").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("coi9").selection("entity2")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("eqdist1", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity1")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity3")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("eqdist1").selection("entity4")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("dist1").set("distance", 0.26);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("dist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("dist1").selection("entity1")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("dist1").selection("entity2")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp34").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("par1").selection("input").set("cb1");
    model.component("comp1").geom("geom1").feature("wp34").geom().feature("par1").selection("tool").set("mov1");
    model.component("comp1").geom("geom1").create("wp35", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp35").label("Work Plane - Shoulder Loft");
    model.component("comp1").geom("geom1").feature("wp35").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp35").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp35").selection("vertex1").named("boxsel4");
    model.component("comp1").geom("geom1").feature("wp35").selection("vertex2").set("loft5(1)", 12);
    model.component("comp1").geom("geom1").feature("wp35").selection("vertex3").set("loft5(1)", 17);
    model.component("comp1").geom("geom1").feature("wp35").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp35").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("cro1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("cro1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("cro1").selection("input")
         .set("extract1(2)", "loft5");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp35").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("proj1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("proj1").set("project", "edg");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("proj1").selection("input")
         .set("loft5(1)", 5);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp35").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("cb1")
         .set("p", new double[][]{{-5.8283644355228036E-8, 0.019764390529417905, 0.06003118389983063, 0.10849540556009374}, {3.0323935029664306E-9, 0.001517302622419615, -0.001839190047382995, 2.4613276213281132E-6}});
    model.component("comp1").geom("geom1").feature("wp35").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi1").selection("entity2")
         .set("proj1.loft5", 2);
    model.component("comp1").geom("geom1").feature("wp35").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("coi2").selection("entity2")
         .set("cro1.loft5", 3);
    model.component("comp1").geom("geom1").feature("wp35").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("tanc1")
         .set("helppoint1", new double[]{-5.8283644355228036E-8, 3.0323935029664306E-9});
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("tanc1")
         .set("helppoint2", new double[]{-5.8283644355228155E-8, 3.032393558477508E-9});
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("tanc1").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("tanc1").selection("edge2")
         .set("proj1.loft5", 1);
    model.component("comp1").geom("geom1").feature("wp35").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist1")
         .set("distance", 0.01976444881306226);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist1").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp35").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist2")
         .set("distance", 0.04026679337041272);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist2").selection("entity1")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("xdist2").selection("entity2")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp35").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("ydist1")
         .set("distance", 0.00335649266980261);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("ydist1").selection("entity1")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp35").geom().feature("ydist1").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").create("uni5", "Union");
    model.component("comp1").geom("geom1").feature("uni5").label("Union - Shoulder Loft");
    model.component("comp1").geom("geom1").feature("uni5").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni5").selection("input").set("loft5", "wp35");
    model.component("comp1").geom("geom1").create("loft6", "Loft");

    return model;
  }

  public static Model run10(Model model) {
    model.component("comp1").geom("geom1").feature("loft6").label("Loft - Shoulder");
    model.component("comp1").geom("geom1").feature("loft6").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft6").selection("profile").set();
    model.component("comp1").geom("geom1").feature("loft6").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft6").selection("startprofile").set("uni5(1)", 5, 18, 20);
    model.component("comp1").geom("geom1").feature("loft6").selection("endprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft6").selection("endprofile").set("wp34.par1", 1, 2, 3);
    model.component("comp1").geom("geom1").feature("loft6").selection("guide").set();
    model.component("comp1").geom("geom1").feature("loft6").selection("startguide").set("uni5(1)", 27);
    model.component("comp1").geom("geom1").feature("loft6").selection("endguide").set("wp33.ca1", 1);
    model.component("comp1").geom("geom1").create("cap1", "CapFaces");
    model.component("comp1").geom("geom1").feature("cap1").label("Cap Faces - A-pillar");
    model.component("comp1").geom("geom1").feature("cap1").set("groupadjedg", true);
    model.component("comp1").geom("geom1").feature("cap1").selection("input").set("loft6(1)", 21, 22, 24, 27, 50);
    model.component("comp1").geom("geom1").create("pare12", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare12").label("Partition Edges - Wheel Arch Loft");
    model.component("comp1").geom("geom1").feature("pare12").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare12").selection("edge").set("cap1(1)", 17);
    model.component("comp1").geom("geom1").feature("pare12").selection("vertexproj").set("wp33.ca2", 2);
    model.component("comp1").geom("geom1").create("pare13", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare13").label("Partition Edges - Corner Loft 1");
    model.component("comp1").geom("geom1").feature("pare13").setIndex("param", "0.3", 0);
    model.component("comp1").geom("geom1").feature("pare13").selection("edge").set("pare12(1)", 9);
    model.component("comp1").geom("geom1").create("pare14", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare14").label("Partition Edges - Corner Loft 2");
    model.component("comp1").geom("geom1").feature("pare14").selection("edge").set("pare13(1)", 35);
    model.component("comp1").geom("geom1").create("wp36", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp36").label("Work Plane - Corner Loft");
    model.component("comp1").geom("geom1").feature("wp36").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp36").selection("vertex1").set("pare14(1)", 7);
    model.component("comp1").geom("geom1").feature("wp36").selection("vertex2").set("pare14(1)", 8);
    model.component("comp1").geom("geom1").feature("wp36").selection("vertex3").set("wp33.ca2", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp36").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("cro1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("cro1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("cro1").selection("input")
         .set("pare14", "wp33.ca2");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp36").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("proj1").set("project", "edg");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("proj1").selection("input")
         .set("pare14(1)", 7, 9);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp36").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("cb1")
         .set("p", new double[][]{{0, -0.050458235921669155, 0, 0.010743991619000026}, {0, 0.10121150591343152, 0.3258688430923075, 0.36343499462751905}});
    model.component("comp1").geom("geom1").feature("wp36").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi1").selection("entity2")
         .set("cro1.pare14", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi2").selection("entity2")
         .set("cro1.wp33.ca2", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi3")
         .set("helppoint1", new double[]{-0.012212739473425587, 0.03360971923706876});
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi3").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("coi3").selection("entity2")
         .set("proj1.pare14", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc1")
         .set("helppoint1", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc1")
         .set("helppoint2", new double[]{-3.0572387504706853E-16, 4.4098352885577153E-16});
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc1").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc1").selection("edge2")
         .set("proj1.pare14", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.010743991619000026, 0.36343499462751905});
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.010743991618999638, 0.36343499462751894});
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc2").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("tanc2").selection("edge2")
         .set("cro1.pare14", 2);
    model.component("comp1").geom("geom1").feature("wp36").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("pos1")
         .set("posconstr", new String[]{"on", "off"});
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("pos1")
         .set("pos", new double[]{0, 0.3258688430923075});
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("pos1").selection("vertex")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp36").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp36").geom().feature("pare1").selection("edge")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").create("wp37", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp37").label("Work Plane - Wheel Arch");
    model.component("comp1").geom("geom1").feature("wp37").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp37").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp37").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp37").selection("offsetvertex").set("pare14(1)", 15);
    model.component("comp1").geom("geom1").feature("wp37").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("cro1").selection("input")
         .set("pare14", "wp33.ca2");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp37").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("proj1").set("project", "edg");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("proj1").selection("input")
         .set("pare14(1)", 39, 41, 42);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp37").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("pol1").set("type", "closed");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("pol1")
         .set("table", new double[][]{{0.9385886097537716, 0.6213116459345486}, {0.9107491126813211, 0.8021999189442163}, {0.9613899999999791, 0.7910890109208426}, {0.96139, 0.6213116459345484}});
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp37").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi1")
         .set("helppoint2", new double[]{0.9385886097537715, 0.6213116459345485});
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi1").selection("entity2")
         .set("proj1.pare14", 2);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi2").selection("entity1")
         .set("cro1.pare14", 5);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi3").selection("entity1")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi3").selection("entity2")
         .set("cro1.pare14", 9);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("ver1").selection("edge")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.9385886097537715, 0.6213116459345485});
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc1").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc1").selection("edge2")
         .set("proj1.pare14", 2);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.8945921491466114, 0.8057448516117964});
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc2").selection("edge1")
         .set("cro1.pare14", 7);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc2").selection("edge2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("cb1")
         .set("p", new double[][]{{0.96139, 0.9613899999999789, 0.9343794647870063, 0.8945921491466114}, {0.6213116459345484, 0.7419128972347988, 0.797015280943028, 0.8057448516117964}});
    model.component("comp1").geom("geom1").feature("wp37").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi4").selection("entity1")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi4").selection("entity2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi5").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("coi5").selection("entity2")
         .set("cro1.pare14", 7);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc3")
         .set("helppoint1", new double[]{0.8945921491466114, 0.8057448516117964});
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc3")
         .set("helppoint2", new double[]{0.8945921491466114, 0.8057448516117964});
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc3").selection("edge1")
         .set("cro1.pare14", 7);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc3").selection("edge2")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp37").geom().create("tanc4", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc4").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc4").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc4")
         .set("helppoint1", new double[]{0.96139, 0.6213116459345484});
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc4").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc4").selection("vertex1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc4").selection("edge2")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp37").geom().feature("tanc4").selection("vertex2")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").create("uni6", "Union");
    model.component("comp1").geom("geom1").feature("uni6").label("Union - Wheel Arch");
    model.component("comp1").geom("geom1").feature("uni6").selection("input").set("pare14", "wp36", "wp37");
    model.component("comp1").geom("geom1").create("loft7", "Loft");
    model.component("comp1").geom("geom1").feature("loft7").label("Loft - Wheel Arch 1");
    model.component("comp1").geom("geom1").feature("loft7").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft7").set("startprofiledir", "perpendicular");
    model.component("comp1").geom("geom1").feature("loft7").set("startprofilerel", "profileedges");
    model.component("comp1").geom("geom1").feature("loft7").selection("profile").set();
    model.component("comp1").geom("geom1").feature("loft7").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft7").selection("startprofile").set("uni6(1)", 43, 45, 46);
    model.component("comp1").geom("geom1").feature("loft7").selection("endprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft7").selection("endprofile").set("uni6(1)", 27, 29, 38);
    model.component("comp1").geom("geom1").feature("loft7").selection("guide").set();
    model.component("comp1").geom("geom1").feature("loft7").selection("startguide").set("uni6(1)", 39, 40);
    model.component("comp1").geom("geom1").feature("loft7").selection("endguide").set("uni6(1)", 28, 31, 34);
    model.component("comp1").geom("geom1").create("loft8", "Loft");
    model.component("comp1").geom("geom1").feature("loft8").label("Loft - Wheel Arch 2");
    model.component("comp1").geom("geom1").feature("loft8").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft8").set("startprofiledir", "perpendicular");
    model.component("comp1").geom("geom1").feature("loft8").set("startprofilerel", "profileedges");
    model.component("comp1").geom("geom1").feature("loft8").selection("profile").set();
    model.component("comp1").geom("geom1").feature("loft8").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft8").selection("startprofile").set("loft7(1)", 8, 9, 11);
    model.component("comp1").geom("geom1").feature("loft8").selection("endprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft8").selection("endprofile").set("loft7(1)", 18, 20, 21);
    model.component("comp1").geom("geom1").feature("loft8").selection("guide").set("wp33.ca2");
    model.component("comp1").geom("geom1").feature("loft8").selection("startguide").set("loft7(1)", 27);
    model.component("comp1").geom("geom1").feature("loft8").selection("endguide").set("loft7(1)", 12);
    model.component("comp1").geom("geom1").create("ext7", "Extrude");
    model.component("comp1").geom("geom1").feature("ext7").set("workplane", "wp6");
    model.component("comp1").geom("geom1").feature("ext7").set("distance", new String[]{"z_gcl*3", "z_gcl"});
    model.component("comp1").geom("geom1").feature("ext7").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext7").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext7").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext7").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext7").selection("input").set("wp6");
    model.component("comp1").geom("geom1").create("wp38", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp38").label("Work Plane - Lower Air Inlet");
    model.component("comp1").geom("geom1").feature("wp38").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp38").set("workplane", "wp22");
    model.component("comp1").geom("geom1").feature("wp38").set("transaxis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("wp38").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp38").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp38").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("cro1").selection("input")
         .set("ext7", "loft8");
    model.component("comp1").geom("geom1").feature("wp38").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("pol1")
         .set("table", new double[][]{{0.10731303840308626, 0.21912184166823745}, {-0.2018691798290939, -0.04031284361695825}});
    model.component("comp1").geom("geom1").feature("wp38").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("coi1").selection("entity1")
         .set("cro1.loft8", 5);
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("coi1").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp38").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("ang1").set("angle", 40);
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("ang1").selection("edge1")
         .set("cro1.loft8", 1);
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("ang1").selection("edge2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp38").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp38").geom().feature("csol1").selection("input")
         .set("cro1", "pol1");
    model.component("comp1").geom("geom1").create("parf4", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf4").label("Partition Faces - Lower Air Inlet 1");
    model.component("comp1").geom("geom1").feature("parf4").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf4").selection("face").set("ext7(1)", 10);
    model.component("comp1").geom("geom1").create("wp39", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp39").label("Work Plane - Lower Air Inlet 2");
    model.component("comp1").geom("geom1").feature("wp39").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp39").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp39").selection("vertex1").set("wp38.csol1", 3);
    model.component("comp1").geom("geom1").feature("wp39").selection("vertex2").set("wp38.csol1", 1);
    model.component("comp1").geom("geom1").feature("wp39").selection("vertex3").set("loft8(1)", 70);
    model.component("comp1").geom("geom1").feature("wp39").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp39").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp39").geom().feature("cro1").selection("input")
         .set("loft8", "parf4", "wp38");
    model.component("comp1").geom("geom1").feature("wp39").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp39").geom().feature("csol1").selection("input").set("cro1");
    model.component("comp1").geom("geom1").feature("wp39").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp39").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp39").geom().feature("del1").selection("input")
         .set("csol1(1)", 2);
    model.component("comp1").geom("geom1").create("uni7", "Union");
    model.component("comp1").geom("geom1").feature("uni7").label("Union - Lower Air Inlet");
    model.component("comp1").geom("geom1").feature("uni7").selection("input").set("parf4", "wp38", "wp39");
    model.component("comp1").geom("geom1").create("wp40", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp40").label("Work Plane - Floor Treatment 1");
    model.component("comp1").geom("geom1").feature("wp40").set("quickz", "b_t+z_gcl");
    model.component("comp1").geom("geom1").feature("wp40").set("quickorigin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp40").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp40").selection("originvertex").set("uni7(1)", 19);
    model.component("comp1").geom("geom1").create("wp41", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp41").label("Work Plane - Floor Treatment 2");
    model.component("comp1").geom("geom1").feature("wp41").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp41").set("workplane", "wp40");
    model.component("comp1").geom("geom1").feature("wp41").set("transaxis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("wp41").set("transrot", -7);
    model.component("comp1").geom("geom1").feature("wp41").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp41").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp41").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("cro1").selection("input")
         .set("loft8", "uni7");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp41").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("uni1").selection("input").set("cro1");
    model.component("comp1").geom("geom1").feature("wp41").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("r1")
         .set("pos", new double[]{-1.334041041436182, 5.110047481230451E-4});
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("r1")
         .set("size", new double[]{1.3493855661365952, 0.16993005486395948});
    model.component("comp1").geom("geom1").feature("wp41").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi1").selection("entity1")
         .set("r1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi1").selection("entity2")
         .set("uni1(1)", 11);
    model.component("comp1").geom("geom1").feature("wp41").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi2").selection("entity1")
         .set("r1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi2").selection("entity2")
         .set("uni1(1)", 12);
    model.component("comp1").geom("geom1").feature("wp41").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi3").selection("entity1")
         .set("r1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("coi3").selection("entity2")
         .set("uni1(1)", 16);
    model.component("comp1").geom("geom1").feature("wp41").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("uni2").selection("input")
         .set("r1", "uni1");
    model.component("comp1").geom("geom1").feature("wp41").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("extract1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp41").geom().feature("extract1").selection("input")
         .set("uni2(1)", 4);
    model.component("comp1").geom("geom1").create("parf5", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf5").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf5").selection("face").set("uni7(1)", 5, 6, 7, 8, 9, 11, 12);
    model.component("comp1").geom("geom1").create("wp42", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp42").label("Work Plane - Front Air Inlet 1");
    model.component("comp1").geom("geom1").feature("wp42").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp42").set("reverse", true);
    model.component("comp1").geom("geom1").feature("wp42").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp42").selection("vertex1").set("parf5(1)", 5);
    model.component("comp1").geom("geom1").feature("wp42").selection("vertex2").named("boxsel13");
    model.component("comp1").geom("geom1").feature("wp42").selection("vertex3").set("loft8(1)", 2);
    model.component("comp1").geom("geom1").feature("wp42").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("cro1").selection("input")
         .set("loft8", "parf5");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp42").geom().create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("off1").set("distance", "b_t*1.5");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("off1").set("convexcorner", "extend");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("off1").set("trim", false);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("off1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("off1").selection("input")
         .set("cro1.loft8", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("ls1").selection("vertex1")
         .set("cro1.loft8", 3);

    return model;
  }

  public static Model run11(Model model) {
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("ls1").selection("vertex2")
         .set("cro1.parf5", 3);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("cb1")
         .set("p", new double[][]{{0.06481460856995651, 0.14457120797592227, 0.4380304443836212, 0.6059551562900363}, {0.007816120312332878, 0.017434124500180802, 0.06134696483612061, 0}});
    model.component("comp1").geom("geom1").feature("wp42").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi1").selection("entity2")
         .set("cro1.loft8", 2);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi2").selection("entity2")
         .set("off1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi3").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi3").selection("entity2")
         .set("cro1.parf5", 2);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.06481460856995651, 0.007816120312332878});
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("tanc1").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("tanc1").selection("edge2")
         .set("cro1.parf5", 2);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("pos1")
         .set("pos", new double[]{0.14457120797592227, 0.017434124500180802});
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("pos1").selection("vertex")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("pos2", "Position");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("pos2")
         .set("pos", new double[]{0.4380304443836212, 0.06134696483612061});
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("pos2").selection("vertex")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("pol1")
         .set("table", new double[][]{{0.06481460856996, 0.007816120312330128}, {0.6952900619038265, -0.4620923136073196}});
    model.component("comp1").geom("geom1").feature("wp42").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi4").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi4").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("coi5", "Coincident");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi5").selection("entity1")
         .set("ls1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi5").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("coi6", "Coincident");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi6").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("coi6").selection("entity2")
         .set("off1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp42").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("csol1").selection("input")
         .set("cb1", "cro1.parf5", "ls1", "pol1");
    model.component("comp1").geom("geom1").feature("wp42").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("extract1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp42").geom().feature("extract1").selection("input")
         .set("csol1(1)", 1);
    model.component("comp1").geom("geom1").create("wp43", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp43").label("Work Plane - Front Air Inlet 2");
    model.component("comp1").geom("geom1").feature("wp43").set("planetype", "edgeangle");
    model.component("comp1").geom("geom1").feature("wp43").set("angle", 50);
    model.component("comp1").geom("geom1").feature("wp43").selection("edge").set("wp42.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp43").selection("adjface").set("wp42.extract1", 1);
    model.component("comp1").geom("geom1").feature("wp43").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp43").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("cro1").selection("input")
         .set("loft8", "parf5", "wp42");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp43").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("cb1")
         .set("p", new double[][]{{-0.11047765684511113, -0.09654500994626478, 0.03502787757448807, 0.3189303453779815}, {-0.07410909488369632, -0.06095543114306137, 0.0196735783484773, 0.213648105704214}});
    model.component("comp1").geom("geom1").feature("wp43").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi1").selection("entity2")
         .set("cro1.loft8", 11);
    model.component("comp1").geom("geom1").feature("wp43").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi2").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi2").selection("entity2")
         .set("cro1.parf5", 14);
    model.component("comp1").geom("geom1").feature("wp43").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi3")
         .set("helppoint1", new double[]{1.1171619185290638E-15, 7.875644580934704E-16});
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi3").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("coi3").selection("entity2")
         .set("cro1.wp42", 1);
    model.component("comp1").geom("geom1").feature("wp43").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("uni1").selection("input")
         .set("cb1", "cro1");
    model.component("comp1").geom("geom1").feature("wp43").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("ls1").selection("vertex1")
         .set("uni1(1)", 28);
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("ls1").selection("vertex2")
         .set("uni1(1)", 29);
    model.component("comp1").geom("geom1").feature("wp43").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("extract1").selection("input")
         .set("uni1(1)", 10);
    model.component("comp1").geom("geom1").feature("wp43").geom().create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("extract2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("extract2").selection("input")
         .set("uni1(1)", 9, 33, 34);
    model.component("comp1").geom("geom1").feature("wp43").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("csol1").selection("input")
         .set("extract2", "ls1");
    model.component("comp1").geom("geom1").feature("wp43").geom().feature("csol1")
         .setAttribute("construction", "off");
    model.component("comp1").geom("geom1").create("parf6", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf6").label("Partition Faces - Floor Treatment 2");
    model.component("comp1").geom("geom1").feature("parf6").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf6").selection("face").set("parf5(1)", 1, 2, 3);
    model.component("comp1").geom("geom1").create("pare15", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare15").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare15").selection("edge").set("loft8(1)", 14);
    model.component("comp1").geom("geom1").feature("pare15").selection("vertexproj").set("wp43.extract1", 1);
    model.component("comp1").geom("geom1").create("loft9", "Loft");
    model.component("comp1").geom("geom1").feature("loft9").label("Loft - Front Corner 1");
    model.component("comp1").geom("geom1").feature("loft9").set("type", "surface");
    model.component("comp1").geom("geom1").feature("loft9").selection("profile").set();
    model.component("comp1").geom("geom1").feature("loft9").selection("startprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft9").selection("startprofile").set("pare15(1)", 5);
    model.component("comp1").geom("geom1").feature("loft9").selection("endprofile").init(1);
    model.component("comp1").geom("geom1").feature("loft9").selection("endprofile").set("wp43.extract1", 1);
    model.component("comp1").geom("geom1").feature("loft9").selection("guide").set();
    model.component("comp1").geom("geom1").feature("loft9").selection("startguide").set("pare15(1)", 8, 11, 13, 14);
    model.component("comp1").geom("geom1").feature("loft9").selection("endguide").set("wp42.extract1", 4, 5);
    model.component("comp1").geom("geom1").create("parf7", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf7").label("Partition Faces - Hood");
    model.component("comp1").geom("geom1").feature("parf7").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf7").set("workplane", "wp42");
    model.component("comp1").geom("geom1").feature("parf7").selection("face").set("parf6(1)", 3);
    model.component("comp1").geom("geom1").create("par5", "Partition");
    model.component("comp1").geom("geom1").feature("par5").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("par5").selection("input").set("parf7");
    model.component("comp1").geom("geom1").feature("par5").selection("tool").set("loft9");
    model.component("comp1").geom("geom1").create("extract5", "Extract");
    model.component("comp1").geom("geom1").feature("extract5").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract5").selection("input")
         .set("par5(1)", 1, 4, 21, 24, 27, 30, 31, 32, 34, 35, 37, 39);
    model.component("comp1").geom("geom1").feature("extract5").selection("input").set("wp41.extract1", 1);
    model.component("comp1").geom("geom1").create("uni8", "Union");
    model.component("comp1").geom("geom1").feature("uni8").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni8").selection("input").set("extract5", "loft9", "wp43.csol1");
    model.component("comp1").geom("geom1").create("del5", "Delete");
    model.component("comp1").geom("geom1").feature("del5").label("Delete Entities - Interior Boundaries");
    model.component("comp1").geom("geom1").feature("del5").selection("input")
         .set("uni8(1)", 4, 5, 15, 26, 38, 40, 54, 60);
    model.component("comp1").geom("geom1").create("cap2", "CapFaces");
    model.component("comp1").geom("geom1").feature("cap2").set("groupadjedg", true);
    model.component("comp1").geom("geom1").feature("cap2").selection("input").set("del5(1)", 17, 18, 23, 26);
    model.component("comp1").geom("geom1").create("fil3", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil3").label("Fillet - Lower Air Intake");
    model.component("comp1").geom("geom1").feature("fil3").set("radius", 0.05);
    model.component("comp1").geom("geom1").feature("fil3").selection("edge").set("cap2(1)", 106);
    model.component("comp1").geom("geom1").create("fil4", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil4").label("Fillet - Front Air Intake");
    model.component("comp1").geom("geom1").feature("fil4").set("radius", 0.02);
    model.component("comp1").geom("geom1").feature("fil4").selection("edge").set("fil3(1)", 15);
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("Explicit Selection - A-pillar and Roof Rail");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("fil4(1)", 44, 79, 81, 87, 115, 123);
    model.component("comp1").geom("geom1").feature("sel5").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel5").set("angletol", 25);
    model.component("comp1").geom("geom1").feature("sel5").set("selshow", false);
    model.component("comp1").geom("geom1").create("fil5", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil5").label("Fillet - A-pillar and Roof Rail");
    model.component("comp1").geom("geom1").feature("fil5").set("radius", "z_gcl/3");
    model.component("comp1").geom("geom1").feature("fil5").set("propagate", false);
    model.component("comp1").geom("geom1").feature("fil5").selection("edge").named("sel5");
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("ballsel1").label("Ball Selection - Hood and Shoulder Fillet");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posx", "x_w+R_wh*0.55");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posy", "W/2*0.85");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posz", "0.69*H");
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", "z_gcl/3");
    model.component("comp1").geom("geom1").feature("ballsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("ballsel1").set("selshow", false);
    model.component("comp1").geom("geom1").create("fil6", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil6").label("Fillet - Hood and Shoulder Fillet 1");
    model.component("comp1").geom("geom1").feature("fil6").set("radius", "z_gcl");
    model.component("comp1").geom("geom1").feature("fil6").set("propagate", false);
    model.component("comp1").geom("geom1").feature("fil6").selection("edge").named("ballsel1");
    model.component("comp1").geom("geom1").create("fil7", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil7").label("Fillet - Hood and Shoulder Fillet 2");
    model.component("comp1").geom("geom1").feature("fil7").set("radius", "z_gcl*4.7");
    model.component("comp1").geom("geom1").feature("fil7").set("preserveoverlapped", true);
    model.component("comp1").geom("geom1").feature("fil7").selection("edge").set("fil6(1)", 44);
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("Explicit Selection - Window Fillet");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection")
         .set("fil7(1)", 40, 44, 46, 48, 51, 52, 56, 59, 69);
    model.component("comp1").geom("geom1").feature("sel6").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel6").set("angletol", 10);
    model.component("comp1").geom("geom1").feature("sel6").set("selshow", false);
    model.component("comp1").geom("geom1").create("fil8", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil8").label("Fillet - Side Window and Hood");
    model.component("comp1").geom("geom1").feature("fil8").set("radius", "z_gcl/3");
    model.component("comp1").geom("geom1").feature("fil8").selection("edge").named("sel6");
    model.component("comp1").geom("geom1").create("boxsel15", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel15").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel15").label("Box Selection - Chamfer");
    model.component("comp1").geom("geom1").feature("boxsel15").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("boxsel15").set("xmin", "L*0.99");
    model.component("comp1").geom("geom1").feature("boxsel15").set("ymin", "W/4");
    model.component("comp1").geom("geom1").feature("boxsel15").set("zmin", "z_gcl*2");
    model.component("comp1").geom("geom1").feature("boxsel15").set("zmax", "z_gcl*3");
    model.component("comp1").geom("geom1").feature("boxsel15").set("selshow", false);
    model.component("comp1").geom("geom1").create("cha1", "Chamfer3D");
    model.component("comp1").geom("geom1").feature("cha1").set("radius", "z_gcl*0.48");
    model.component("comp1").geom("geom1").feature("cha1").set("propagate", false);
    model.component("comp1").geom("geom1").feature("cha1").selection("edge").named("boxsel15");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("Wheel bays");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection")
         .set("cha1(1)", 13, 14, 15, 29, 67, 68, 69, 75, 76);
    model.component("comp1").geom("geom1").feature("sel7").set("groupcontang", true);
    model.component("comp1").geom("geom1").create("wp44", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp44").label("Work Plane - Door Line 1");
    model.component("comp1").geom("geom1").feature("wp44").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp44").set("workplane", "wp22");
    model.component("comp1").geom("geom1").feature("wp44").set("transdispl", new double[]{0, 0, -0.3});
    model.component("comp1").geom("geom1").feature("wp44").set("transaxis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("wp44").set("transrot", 40);
    model.component("comp1").geom("geom1").feature("wp44").set("unite", true);
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "z_gcl/4");
    model.component("comp1").geom("geom1").feature("cylsel1").set("top", "W/2*0.8");
    model.component("comp1").geom("geom1").feature("cylsel1").set("bottom", 0);
    model.component("comp1").geom("geom1").feature("cylsel1").set("pos", new String[]{"L/2", "0", "H*2/3+z_gcl/4"});
    model.component("comp1").geom("geom1").feature("cylsel1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("cylsel1").set("selshow", false);
    model.component("comp1").geom("geom1").create("parf8", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf8").label("Partition Faces - Door Line 1");
    model.component("comp1").geom("geom1").feature("parf8").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf8").selection("face").named("cylsel1");
    model.component("comp1").geom("geom1").create("wp45", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp45").label("Work Plane - Door Line 2");
    model.component("comp1").geom("geom1").feature("wp45").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp45").set("workplane", "wp22");
    model.component("comp1").geom("geom1").feature("wp45").set("transdispl", new double[]{0, 0, 0.2});
    model.component("comp1").geom("geom1").feature("wp45").set("unite", true);
    model.component("comp1").geom("geom1").create("parf9", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf9").label("Partition Faces - Door Line 2");
    model.component("comp1").geom("geom1").feature("parf9").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf9").selection("face").set("parf8(1)", 34);
    model.component("comp1").geom("geom1").create("ballsel2", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel2").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("ballsel2").set("posx", "air_inlet*1.01");
    model.component("comp1").geom("geom1").feature("ballsel2").set("posy", "W/2*0.82");
    model.component("comp1").geom("geom1").feature("ballsel2").set("posz", "H*0.69");
    model.component("comp1").geom("geom1").feature("ballsel2").set("r", "z_gcl");
    model.component("comp1").geom("geom1").feature("ballsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("ballsel2").set("selshow", false);
    model.component("comp1").geom("geom1").create("parf10", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf10").selection("face").set("parf9(1)", 33);
    model.component("comp1").geom("geom1").feature("parf10").selection("vertexsegment").named("ballsel2");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("Air vents");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("parf10(1)", 4, 54);
    model.component("comp1").geom("geom1").create("wp46", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp46").label("Work Plane - Mirror TOP View");
    model.component("comp1").geom("geom1").feature("wp46").set("quickz", "z_mirror");
    model.component("comp1").geom("geom1").feature("wp46").set("displ", new String[]{"x_mirror", "w_mirror/2"});
    model.component("comp1").geom("geom1").feature("wp46").set("rot", 70);
    model.component("comp1").geom("geom1").feature("wp46").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp46").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cro1").selection("input").set("parf10");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp46").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("ls1").selection("vertex1")
         .set("cro1.parf10", 1);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("ls1").selection("vertex2")
         .set("cro1.parf10", 17);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("ls1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp46").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").set("type", "solid");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").feature("pol1")
         .set("tableconstr", new String[]{"off", "off", "off", "on"});
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{-0.09669696183482733, 0.1152389516596016}, {-0.2553238698428319, 0.14320915540777243}, {-0.2032, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{-0.11701669824097362, 0});
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").feature("ca1")
         .set("r", 0.11701669824097437);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("cc1").feature("ca1").set("angle2", 80);
    model.component("comp1").geom("geom1").feature("wp46").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("hor1").selection("edge")
         .set("cc1(1)", 3, 5);
    model.component("comp1").geom("geom1").feature("wp46").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("tanc1")
         .set("helppoint1", new double[]{-0.09669696183482732, 0.11523895165960157});
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp46").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("xdist1").set("distance", "8[in]");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("xdist1").selection("entity1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp46").geom().create("dist1", "Distance");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("dist1").set("distance", "6[in]");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("dist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("dist1").selection("entity1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("dist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("dist1").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp46").geom().create("para1", "Parallel");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("para1").selection("edge")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("para1").selection("edge")
         .set("ls1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp46").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("ang1").set("angle", 10);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("ang1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp46").geom().feature("ang1").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").create("ext8", "Extrude");
    model.component("comp1").geom("geom1").feature("ext8").setIndex("distance", "4[in]", 0);
    model.component("comp1").geom("geom1").feature("ext8").selection("input").set("wp46");
    model.component("comp1").geom("geom1").create("wp47", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp47").label("Work Plane - Mirror Partition 1");
    model.component("comp1").geom("geom1").feature("wp47").set("planetype", "edgeangle");
    model.component("comp1").geom("geom1").feature("wp47").set("angle", -7);
    model.component("comp1").geom("geom1").feature("wp47").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp47").selection("edge").set("ext8(1)", 5);
    model.component("comp1").geom("geom1").feature("wp47").selection("adjface").set("ext8(1)", 4);
    model.component("comp1").geom("geom1").create("par6", "Partition");
    model.component("comp1").geom("geom1").feature("par6").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par6").selection("input").set("ext8");
    model.component("comp1").geom("geom1").create("wp48", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp48").label("Work Plane - Mirror Partition 2");
    model.component("comp1").geom("geom1").feature("wp48").set("planetype", "edgeangle");
    model.component("comp1").geom("geom1").feature("wp48").set("angle", 5);
    model.component("comp1").geom("geom1").feature("wp48").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp48").selection("edge").set("par6(1)", 3);

    return model;
  }

  public static Model run12(Model model) {
    model.component("comp1").geom("geom1").feature("wp48").selection("adjface").set("par6(1)", 3);
    model.component("comp1").geom("geom1").create("par7", "Partition");
    model.component("comp1").geom("geom1").feature("par7").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par7").selection("input").set("par6");
    model.component("comp1").geom("geom1").create("extract6", "Extract");
    model.component("comp1").geom("geom1").feature("extract6").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("extract6").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("extract6").selection("input").set("par7(1)", 1);
    model.component("comp1").geom("geom1").run("extract6");
    model.component("comp1").geom("geom1").create("wp49", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp49").label("Work Plane - Mirror Sweep Face");
    model.component("comp1").geom("geom1").feature("wp49").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp49").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp49").selection("face").set("extract6(1)", 6);
    model.component("comp1").geom("geom1").feature("wp49").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp49").geom().constrDimBuild("uptotarget");
    model.component("comp1").geom("geom1").feature("wp49").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("cro1").selection("input").set("extract6");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp49").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("pol1")
         .set("table", new double[][]{{0, 0.042447214925798366}, {0.1016, 0.04067378032909151}, {0.1016, -0.0390774178239872}, {0, -0.03907741782398722}});
    model.component("comp1").geom("geom1").feature("wp49").geom().create("ver1", "Vertical");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("ver1").selection("edge")
         .set("pol1(1)", 2, 4);
    model.component("comp1").geom("geom1").feature("wp49").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp49").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("pos1")
         .set("posconstr", new String[]{"on", "off"});
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("pos1")
         .set("pos", new double[]{0, -0.03907741782398722});
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("pos1").selection("vertex")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp49").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi1").selection("entity1")
         .set("cro1.extract6", 2);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi1").selection("entity2")
         .set("pol1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp49").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi2").selection("entity1")
         .set("cro1.extract6", 3);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp49").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi3").selection("entity1")
         .set("cro1.extract6", 4);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("coi3").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp49").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("ang1").set("reverse1", true);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("ang1").set("angle", 91);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("ang1").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("ang1").selection("edge2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp49").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("uni1").selection("input")
         .set("cro1", "pol1");
    model.component("comp1").geom("geom1").feature("wp49").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp49").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("extract1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("extract1").selection("input")
         .set("uni1(1)", 3, 4);
    model.component("comp1").geom("geom1").feature("wp49").geom().feature("extract1")
         .setAttribute("construction", "off");
    model.component("comp1").geom("geom1").feature("wp49").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", true);
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").set("wp49.extract1", 1, 2);
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").set("extract6(1)", 5);
    model.component("comp1").geom("geom1").feature("swe1").selection("diredge").set("extract6(1)", 5);
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("extract6");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("swe1");
    model.component("comp1").geom("geom1").create("fil9", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil9").set("radius", "0.75[in]");
    model.component("comp1").geom("geom1").feature("fil9").selection("edge").set("dif1(1)", 2, 4, 6, 8, 11, 12);
    model.component("comp1").geom("geom1").create("fil10", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil10").set("radius", "0.5[in]");
    model.component("comp1").geom("geom1").feature("fil10").selection("edge")
         .set("fil9(1)", 7, 9, 27, 28, 29, 30, 31, 32, 33, 34);
    model.component("comp1").geom("geom1").create("wp50", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp50").label("Work Plane - Mirror Loft guide curve 1");
    model.component("comp1").geom("geom1").feature("wp50").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp50").set("quickx", "x_mirror-3[in]");
    model.component("comp1").geom("geom1").feature("wp50").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp50").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("cro1").selection("input").set("fil10");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp50").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("pare1").selection("edge")
         .set("cro1.fil10", 5, 12);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ls1").selection("vertex1")
         .set("pare1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ls1").selection("vertex2")
         .set("pare1(1)", 13);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ls1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp50").geom().create("pare2", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("pare2").selection("edge")
         .set("ls1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("cb1")
         .set("p", new double[][]{{0.9212844453083227, 0.9169772297281519, 0.886977229728152, 0.788}, {0.886988186763071, 0.8006197850742014, 0.7549997850742014, 0.75438}});
    model.component("comp1").geom("geom1").feature("wp50").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("coi1").selection("entity2")
         .set("pare2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("pos1")
         .set("pos", new String[]{"y_mirror*0.8", "z_mirror*0.9"});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("pos1").selection("vertex")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist1").set("distance", 0.03355);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist1")
         .set("helppoint1", new double[]{0.8820675611495972, 0.7569308876991272});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist1")
         .set("helppoint2", new double[]{0.9208762645721436, 0.8423099517822266});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist1").selection("entity1")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist1").selection("entity2")
         .set("pare1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist2").set("distance", 0.00355);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist2")
         .set("helppoint1", new double[]{0.9113897085189819, 0.8017764687538147});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist2")
         .set("helppoint2", new double[]{0.9200137853622437, 0.8457596302032471});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist2").selection("entity1")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("xdist2").selection("entity2")
         .set("pare1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist1").set("distance", 0.045933);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist1")
         .set("helppoint1", new double[]{0.9113897085189819, 0.803501307964325});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist1")
         .set("helppoint2", new double[]{0.9200137853622437, 0.8448972105979919});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist1").selection("entity1")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist1").selection("entity2")
         .set("pare1(1)", 8);
    model.component("comp1").geom("geom1").feature("wp50").geom().create("ydist2", "YDistance");
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist2").set("distance", 0.04562);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist2")
         .set("helppoint1", new double[]{0.8794803619384766, 0.7586557269096375});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist2")
         .set("helppoint2", new double[]{0.9062151908874512, 0.7991892099380493});
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist2").selection("entity1")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp50").geom().feature("ydist2").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp50").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("ext9", "Extrude");
    model.component("comp1").geom("geom1").feature("ext9").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext9").set("distance", new double[]{-0.2, 0.2});
    model.component("comp1").geom("geom1").feature("ext9").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext9").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext9").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext9").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext9").selection("input").set("wp50");
    model.component("comp1").geom("geom1").create("wp51", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp51").label("Work Plane Mirror Loft guide curve 2");
    model.component("comp1").geom("geom1").feature("wp51").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp51").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp51").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp51").selection("offsetvertex").set("wp50.cb1", 1);
    model.component("comp1").geom("geom1").feature("wp51").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp51").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("proj1").selection("input").set("wp50");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp51").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("cb1")
         .set("p", new double[][]{{0.886988186763071, 0.795744, 0.77, 0.75438}, {1.7018, 1.7018, 1.7018, 1.72466}});
    model.component("comp1").geom("geom1").feature("wp51").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi1").selection("entity1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi1").selection("entity2")
         .set("proj1.wp50", 2);
    model.component("comp1").geom("geom1").feature("wp51").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi2")
         .set("helppoint1", new double[]{0.77, 1.7018});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi2")
         .set("helppoint2", new double[]{0.7700842022895813, 1.7002238035202026});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi2").selection("entity1")
         .set("proj1.wp50", 1);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("coi2").selection("entity2")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp51").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist1").set("distance", 0.01562);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist1")
         .set("helppoint1", new double[]{0.7529186010360718, 1.7239915132522583});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist1")
         .set("helppoint2", new double[]{0.774705708026886, 1.7005538940429688});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist1").selection("entity1")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist1").selection("entity2")
         .set("cb1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp51").geom().create("xdist2", "XDistance");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist2").set("distance", 0.041364);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist2")
         .set("helppoint1", new double[]{0.7539089322090149, 1.7005538940429688});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist2")
         .set("helppoint2", new double[]{0.7958325743675232, 1.7008839845657349});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist2").selection("entity1")
         .set("proj1.wp50", 1);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("xdist2").selection("entity2")
         .set("cb1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp51").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("pos1")
         .set("pos", new String[]{"z_mirror*0.9", "x_mirror*0.97"});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("pos1").selection("vertex")
         .set("cb1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp51").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.886988186763071, 1.7018});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.886988186763071, 1.7018});
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("tanc1").selection("edge1")
         .set("cb1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp51").geom().feature("tanc1").selection("edge2")
         .set("proj1.wp50", 1);
    model.component("comp1").geom("geom1").feature("wp51").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").create("ext10", "Extrude");
    model.component("comp1").geom("geom1").feature("ext10").set("distance", new double[]{-0.2, 0.1});
    model.component("comp1").geom("geom1").feature("ext10").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext10").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext10").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext10").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext10").selection("input").set("wp51");
    model.component("comp1").geom("geom1").create("int2", "Intersection");
    model.component("comp1").geom("geom1").feature("int2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("int2").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("int2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("int2").set("keeplowerdim", true);
    model.component("comp1").geom("geom1").feature("int2").selection("input").set("ext10", "ext9");
    model.component("comp1").geom("geom1").create("wp52", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp52").label("Work Plane - Mirror Loft Profile");
    model.component("comp1").geom("geom1").feature("wp52").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp52").set("quickorigin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp52").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp52").selection("offsetvertex").set("int2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp52").selection("originvertex").set("int2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp52").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp52").geom().constrDimBuild("uptotarget");
    model.component("comp1").geom("geom1").feature("wp52").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro1").selection("input").set("fil10");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp52").geom().create("cro2", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro2").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro2").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro2").selection("input").set("int2");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("cro2").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp52").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("e1")
         .set("pos", new double[]{-0.027115, 0});
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("e1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("e1")
         .set("semiaxes", new double[]{0.027115, 0.021692});
    model.component("comp1").geom("geom1").feature("wp52").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("coi1").selection("entity1")
         .set("e1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("coi1").selection("entity2").named("cro2");
    model.component("comp1").geom("geom1").feature("wp52").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("xdist1").set("distance", 0.05423);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("xdist1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("xdist1")
         .set("parname", "geom1.wp52.xdist1");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("xdist1").selection("entity1")
         .set("e1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("xdist1").selection("entity2")
         .set("e1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp52").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("ydist1")
         .set("distance", "geom1.wp52.xdist1*0.8");
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("ydist1").selection("entity1")
         .set("e1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp52").geom().feature("ydist1").selection("entity2")
         .set("e1(1)", 4);
    model.component("comp1").geom("geom1").create("ballsel3", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel3").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("ballsel3").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("ballsel3").set("input", new String[]{"int2"});
    model.component("comp1").geom("geom1").feature("ballsel3").set("posx", "x_mirror");
    model.component("comp1").geom("geom1").feature("ballsel3").set("posy", "y_mirror*0.7");
    model.component("comp1").geom("geom1").feature("ballsel3").set("posz", "z_mirror*0.9");
    model.component("comp1").geom("geom1").feature("ballsel3").set("r", "z_gcl");
    model.component("comp1").geom("geom1").feature("ballsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("ballsel3").set("selshow", false);
    model.component("comp1").geom("geom1").create("wp53", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp53").label("Work Plane - Mirror Loft Profile 2");
    model.component("comp1").geom("geom1").feature("wp53").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp53").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp53").set("quickorigin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp53").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp53").selection("offsetvertex").named("ballsel3");
    model.component("comp1").geom("geom1").feature("wp53").selection("originvertex").named("ballsel3");
    model.component("comp1").geom("geom1").feature("wp53").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp53").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("cro1").selection("input").set("int2");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp53").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("proj1").set("project", "obj");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("proj1").set("projectiontype", "edgvtx");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("proj1").selection("input").set("wp52");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp53").geom().create("ydist1", "YDistance");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1").set("distanceconstr", false);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1")
         .set("distance", 0.05423000000003125);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1").set("createpar", true);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1")
         .set("parname", "geom1.wp53.ydist1");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1").selection("entity1")
         .set("proj1.wp52", 1);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("ydist1").selection("entity2")
         .set("proj1.wp52", 3);
    model.component("comp1").geom("geom1").feature("wp53").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("e1")
         .set("pos", new double[]{0, -0.02982650000001719});
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("e1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("e1")
         .set("semiaxesconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("e1")
         .set("semiaxes", new String[]{"geom1.wp53.ydist1/2*1.4", "geom1.wp53.ydist1/2*1.1"});
    model.component("comp1").geom("geom1").feature("wp53").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("coi1").selection("entity1")
         .set("e1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp53").geom().feature("coi1").selection("entity2")
         .set("cro1.int2", 1);

    return model;
  }

  public static Model run13(Model model) {
    model.component("comp1").geom("geom1").create("loft10", "Loft");
    model.component("comp1").geom("geom1").feature("loft10").label("Loft - Mirror");
    model.component("comp1").geom("geom1").feature("loft10").selection("profile").set("wp52", "wp53");
    model.component("comp1").geom("geom1").feature("loft10").selection("guide").named("int2");
    model.component("comp1").geom("geom1").create("uni9", "Union");
    model.component("comp1").geom("geom1").feature("uni9").label("Union - Mirror");
    model.component("comp1").geom("geom1").feature("uni9").set("selresult", true);
    model.component("comp1").geom("geom1").feature("uni9").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("uni9").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni9").selection("input").set("fil10", "loft10");
    model.component("comp1").geom("geom1").create("ballsel4", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel4").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("ballsel4").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("ballsel4").set("angletol", 10);
    model.component("comp1").geom("geom1").feature("ballsel4").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("ballsel4").set("input", new String[]{"uni9"});
    model.component("comp1").geom("geom1").feature("ballsel4").set("posx", "x_mirror*0.95");
    model.component("comp1").geom("geom1").feature("ballsel4").set("posy", "y_mirror*0.9");
    model.component("comp1").geom("geom1").feature("ballsel4").set("posz", "z_mirror");
    model.component("comp1").geom("geom1").feature("ballsel4").set("r", "z_gcl*0.1");
    model.component("comp1").geom("geom1").feature("ballsel4").set("selshow", false);
    model.component("comp1").geom("geom1").create("fil11", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil11").set("radius", 0.01);
    model.component("comp1").geom("geom1").feature("fil11").selection("edge").named("ballsel4");
    model.component("comp1").geom("geom1").create("uni10", "Union");
    model.component("comp1").geom("geom1").feature("uni10").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni10").selection("input").set("fil11", "parf10");
    model.component("comp1").geom("geom1").create("del6", "Delete");
    model.component("comp1").geom("geom1").feature("del6").selection("input").set("uni10(1)", 58, 59, 65);
    model.component("comp1").geom("geom1").create("ballsel5", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel5").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("ballsel5").set("posx", "x_mirror*0.94");
    model.component("comp1").geom("geom1").feature("ballsel5").set("posy", "y_mirror*0.9");
    model.component("comp1").geom("geom1").feature("ballsel5").set("posz", "z_mirror*0.92");
    model.component("comp1").geom("geom1").feature("ballsel5").set("r", "z_gcl*0.4");
    model.component("comp1").geom("geom1").feature("ballsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("ballsel5").set("selshow", false);
    model.component("comp1").geom("geom1").run("ballsel5");
    model.component("comp1").geom("geom1").create("fil12", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil12").label("Fillet - Mirror to car body");
    model.component("comp1").geom("geom1").feature("fil12").set("radius", 0.01);
    model.component("comp1").geom("geom1").feature("fil12").selection("edge").named("ballsel5");
    model.component("comp1").geom("geom1").create("wp54", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp54").label("Work Plane - Front Light");
    model.component("comp1").geom("geom1").feature("wp54").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp54").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp54").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp54").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("proj1").set("project", "bnd");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("proj1").set("projectiontype", "edgvtx");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("proj1").selection("input")
         .set("fil12(1)", 5, 7, 12);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp54").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ls1")
         .set("coord2", new double[]{0.6871756520224562, 0.3653777753884658});
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ls1").selection("vertex1")
         .set("proj1.fil12", 2);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ls1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp54").geom().create("ang1", "Angle");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ang1").set("reverse2", true);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ang1").set("angle", 56);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ang1").selection("edge1")
         .set("proj1.fil12", 1);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("ang1").selection("edge2")
         .set("ls1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp54").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("extract1").selection("input")
         .set("proj1.fil12", 2, 4, 7);
    model.component("comp1").geom("geom1").feature("wp54").geom().create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("extract2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("extract2").selection("input")
         .set("proj1.fil12", 4, 5, 6, 10);
    model.component("comp1").geom("geom1").feature("wp54").geom().create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off1").set("distance", "20[mm]");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off1")
         .set("convexcorner", "noconnection");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off1").selection("input").set("extract2");
    model.component("comp1").geom("geom1").feature("wp54").geom().create("off2", "Offset");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off2").set("distance", "150[mm]");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off2").set("convexcorner", "extend");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off2").selection("input").set("extract1");
    model.component("comp1").geom("geom1").feature("wp54").geom().create("off3", "Offset");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off3").set("keep", false);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off3").set("distance", "50[mm]");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("off3").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp54").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("csol1").selection("input")
         .set("off1", "off2", "off3");
    model.component("comp1").geom("geom1").feature("wp54").geom().create("extract3", "Extract");
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("extract3").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("extract3").selection("input")
         .set("csol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp54").geom().feature("extract3")
         .setAttribute("construction", "off");
    model.component("comp1").geom("geom1").create("ext11", "Extrude");
    model.component("comp1").geom("geom1").feature("ext11").setIndex("distance", "x_w-R_wh", 0);
    model.component("comp1").geom("geom1").feature("ext11").selection("input").set("wp54");
    model.component("comp1").geom("geom1").create("par8", "Partition");
    model.component("comp1").geom("geom1").feature("par8").selection("input").set("fil12");
    model.component("comp1").geom("geom1").feature("par8").selection("tool").set("ext11");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("Headlights");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("par8(1)", 10);
    model.component("comp1").geom("geom1").create("wp55", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp55").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp55").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp55").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("proj1").set("project", "bnd");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("proj1").selection("input")
         .set("par8(1)", 120, 125, 126);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("sel1").selection("selection")
         .set("proj1.par8", 9, 11, 12, 14, 15, 16);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("sel1").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("sel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("extract1").selection("input")
         .named("sel1");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("proj2", "Projection");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("proj2").set("project", "bnd");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("proj2").selection("input")
         .set("par8(1)", 116);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("proj2")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("mov1").set("specify", "pos");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("mov1").selection("input").set("extract1");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("mov1").selection("oldposvertex")
         .set("extract1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("mov1").selection("newposvertices")
         .set("extract1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("mov1").selection("newposvertices")
         .set("proj1.par8", 7);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("pol1")
         .set("table", new double[][]{{0.6708440547112398, 0.8018919887842814}, {0.6624906804527901, 0.7345892606842767}});
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi1").selection("entity2")
         .set("mov1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi2")
         .set("helppoint1", new double[]{0.6624906804527904, 0.7345892606842769});
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi2").selection("entity1")
         .set("mov1(2)", 4);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("para1", "Parallel");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("para1").selection("edge")
         .set("pol1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("para1").selection("edge")
         .set("proj2.par8", 8);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("off1").set("distance", "20[mm]");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("off1").selection("input").set("mov1(1)");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("pol2")
         .set("table", new double[][]{{0.4034064450559158, 0.856321117665031}, {0.3902333095779734, 0.8371703605693206}});
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("pol2").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi3").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi3").selection("entity2")
         .set("mov1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi4")
         .set("helppoint1", new double[]{0.3902333095779734, 0.8371703605693206});
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi4").selection("entity1")
         .set("off1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("coi4").selection("entity2")
         .set("pol2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.40340644505591533, 0.8563211176650304});
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("tanc1").selection("edge1")
         .set("proj2.par8", 1);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("tanc1").selection("edge2")
         .set("pol2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp55").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("csol1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("csol1").set("repairtol", 1.0E-5);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("csol1").selection("input")
         .set("mov1", "off1", "pol1", "pol2", "proj1", "proj2");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("extract2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("extract2").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("extract2").selection("input")
         .set("csol1(1)", 1, 4);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("extract2")
         .setAttribute("construction", "off");
    model.component("comp1").geom("geom1").feature("wp55").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp55").geom().feature("uni1").selection("input").set("extract2");
    model.component("comp1").geom("geom1").create("ext12", "Extrude");
    model.component("comp1").geom("geom1").feature("ext12").set("distance", new String[]{"L-x_w", "L"});
    model.component("comp1").geom("geom1").feature("ext12").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext12").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext12").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext12").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext12").selection("input").set("wp55");
    model.component("comp1").geom("geom1").create("par9", "Partition");
    model.component("comp1").geom("geom1").feature("par9").selection("input").set("par8");
    model.component("comp1").geom("geom1").feature("par9").selection("tool").set("ext12");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("Rear lights");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("par9(1)", 125, 126, 129);
    model.component("comp1").geom("geom1").create("wp56", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp56").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("proj1", "Projection");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("proj1").set("project", "bnd");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("proj1").set("projectiontype", "all");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("proj1").selection("input")
         .set("par9(1)", 105);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp56").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("ls1").selection("vertex1")
         .set("proj1.par9", 2);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("ls1").selection("vertex2")
         .set("proj1.par9", 5);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("ls1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp56").geom().create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("off1").set("distance", 0.1);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("off1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("off1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp56").geom().create("cc1", "CompositeCurve");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca1").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca1")
         .set("center", new double[]{6.317096158364501, 0});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca1").set("r", "R_wsr");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca1").set("angle1", -180);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca1")
         .set("angle2", 174.8685134588226);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca1")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca2").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca2")
         .set("center", new double[]{3.234500721210159, 0.27682191176861});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca2").set("r", "z_gcl");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca2")
         .set("angle1", 174.8685134588226);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca2")
         .set("angle2", 84.0127397557233);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca2")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("pol1")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("pol1")
         .set("table", new double[][]{{3.248582210498607, 0.4110855019910501}, {4.133848629866213, 0.3182392613895933}});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca3").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca3")
         .set("center", new double[]{4.119767140586652, 0.18397567116667018});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca3").set("r", "z_gcl");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca3")
         .set("angle1", 84.01273975949586);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca3")
         .set("angle2", 3.407834277899699);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca3")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").create("ca4", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca4").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca4")
         .set("center", new double[]{1.03024, 0});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca4").set("r", "R_wsr");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca4")
         .set("angle1", 3.407834277898368);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca4").set("angle2", 0);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("ca4")
         .set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("pol2")
         .set("source", "table");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("cc1").feature("pol2")
         .set("table", new double[][]{{4.26024, 0}, {3.0870961583645014, 0}});
    model.component("comp1").geom("geom1").feature("wp56").geom().create("hor1", "Horizontal");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("hor1").selection("edge")
         .set("cc1(1)", 6, 7, 14);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("coi1").selection("entity1")
         .set("off1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("coi1").selection("entity2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("coi2").selection("entity1")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("coi2").selection("entity2")
         .set("proj1.par9", 2);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("pos1", "Position");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("pos1")
         .set("pos", new String[]{"L-0.2", "0"});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("pos1").selection("vertex")
         .set("cc1(1)", 6);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("xdist1").set("distance", 0.08);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("xdist1").selection("entity1")
         .set("proj1.par9", 1);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("xdist1").selection("entity2")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("tanc1", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc1")
         .set("helppoint1", new double[]{3.100041792610557, 0.2888965347375023});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc1")
         .set("helppoint2", new double[]{3.100041792610557, 0.2888965347375023});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc1").selection("edge1")
         .set("cc1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc1").selection("edge2")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("tanc2", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc2").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc2").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc2")
         .set("helppoint1", new double[]{3.248582210498607, 0.4110855019910501});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc2").selection("edge1")
         .set("cc1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc2").selection("vertex1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc2").selection("edge2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc2").selection("vertex2")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("tanc3", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc3").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc3").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc3")
         .set("helppoint2", new double[]{4.13384862987465, 0.31823926138915304});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc3").selection("edge1")
         .set("cc1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc3").selection("vertex1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc3").selection("edge2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc3").selection("vertex2")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp56").geom().create("tanc4", "TangentConstraint");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4").set("point1", "vertex");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4").set("point2", "vertex");
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4")
         .set("helppoint1", new double[]{4.254528421354906, 0.1920004581158263});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4")
         .set("helppoint2", new double[]{4.254528421354905, 0.19200045811582625});
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4").selection("edge1")
         .set("cc1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4").selection("vertex1")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4").selection("edge2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").feature("wp56").geom().feature("tanc4").selection("vertex2")
         .set("cc1(1)", 5);
    model.component("comp1").geom("geom1").create("ext13", "Extrude");
    model.component("comp1").geom("geom1").feature("ext13").set("distance", new String[]{"H", "H/4"});
    model.component("comp1").geom("geom1").feature("ext13").set("includeinput", false);
    model.component("comp1").geom("geom1").feature("ext13").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp1").geom("geom1").feature("ext13").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp1").geom("geom1").feature("ext13").set("twist", new int[]{0, 0});
    model.component("comp1").geom("geom1").feature("ext13").selection("input").set("wp56");
    model.component("comp1").geom("geom1").create("par10", "Partition");

    return model;
  }

  public static Model run14(Model model) {
    model.component("comp1").geom("geom1").feature("par10").set("selresult", true);
    model.component("comp1").geom("geom1").feature("par10").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("par10").selection("input").set("par9");
    model.component("comp1").geom("geom1").feature("par10").selection("tool").set("ext13");
    model.component("comp1").geom("geom1").create("sel11", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel11").label("Windows");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel11").selection("selection")
         .set("par10(1)", 18, 32, 74, 98, 109, 119);
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel1").label("Adjacent Selection - Ignore Edges");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"par10"});
    model.component("comp1").geom("geom1").create("sel12", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel12").label("Form Composite Face 1");
    model.component("comp1").geom("geom1").feature("sel12").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel12").selection("selection")
         .set("par10(1)", 2, 7, 19, 21, 24, 29, 52, 53, 54, 55, 56, 58, 61, 62, 72, 73, 82, 83, 84, 85, 90, 91, 92, 103, 104, 106, 107, 108, 109, 117, 118, 119, 125, 133, 134);
    model.component("comp1").geom("geom1").create("sel13", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel13").label("Form Composite Face 2");
    model.component("comp1").geom("geom1").feature("sel13").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel13").selection("selection")
         .set("par10(1)", 12, 13, 20, 22, 23, 25, 26, 27, 28, 31, 33, 36, 87, 88, 94, 99, 105, 111, 112, 121, 126, 127, 128, 131, 135);
    model.component("comp1").geom("geom1").create("sel14", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel14").label("Form Composite Face 3");
    model.component("comp1").geom("geom1").feature("sel14").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel14").selection("selection")
         .set("par10(1)", 5, 6, 8, 11, 17, 95, 97, 110, 113, 122, 129, 132, 133);
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"x_w", "W/2-W_w", "R_w"});
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("cyl1").set("layername", new String[]{"Layer 1"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "R_w*0.25", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R_w");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "W_w");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"x_w-R_w", "W/2-W_w", "0"});
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"R_w*2", "R_w", "0.01[m]"});
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk1");
    model.component("comp1").geom("geom1").create("fil13", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil13").set("radius", "z_gcl/5");
    model.component("comp1").geom("geom1").feature("fil13").selection("edge").set("dif2(1)", 3, 6);
    model.component("comp1").geom("geom1").create("sel15", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel15").label("Tires");
    model.component("comp1").geom("geom1").feature("sel15").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel15").selection("selection")
         .set("fil13(1)", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 13, 14, 16, 18, 19, 20, 21, 22, 23, 24);
    model.component("comp1").geom("geom1").feature("sel15").set("groupcontang", true);
    model.component("comp1").geom("geom1").create("fil14", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil14").set("radius", "z_gcl/10");
    model.component("comp1").geom("geom1").feature("fil14").selection("edge").set("fil13(1)", 15, 17);
    model.component("comp1").geom("geom1").create("sel16", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel16").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel16").selection("selection")
         .set("fil14(1)", 11, 12, 13, 14, 19, 21, 28, 29);
    model.component("comp1").geom("geom1").feature("sel16").set("selshow", false);
    model.component("comp1").geom("geom1").create("sel17", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel17").label("Explicit Selection - Ignore Edges 1");
    model.component("comp1").geom("geom1").feature("sel17").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel17").selection("selection")
         .set("fil14(1)", 1, 2, 5, 8, 11, 12, 13, 14, 17, 20, 28, 30, 32, 33, 35, 37, 39, 41, 43, 45, 47, 49, 52, 53, 55, 57, 61, 62, 63, 64, 65, 66, 67, 68);
    model.component("comp1").geom("geom1").feature("sel17").set("groupcontang", true);
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("cyl2").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"x_w", "W/2+z_gcl/10-W_w", "R_w"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "R_w*0.75");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "W_w-2*z_gcl/10");
    model.component("comp1").geom("geom1").create("wp57", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp57").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp57").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp57").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp57").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp57").selection("face").set("cyl2(1)", 3);
    model.component("comp1").geom("geom1").feature("wp57").geom().useConstrDim(true);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("cro1").selection("input").set("fil14");
    model.component("comp1").geom("geom1").feature("wp57").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r1")
         .set("pos", new double[]{-0.06, -0.15});
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r1").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r1")
         .set("sizeconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r1")
         .set("size", new double[]{0.02, 0.09});
    model.component("comp1").geom("geom1").feature("wp57").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r2")
         .set("pos", new double[]{-0.05, -0.07});
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r2").set("rotconstr", true);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r2").set("rot", 45);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r2")
         .set("size", new double[]{0.07071067811865477, 0.014142135623730973});
    model.component("comp1").geom("geom1").feature("wp57").geom().create("coi1", "Coincident");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi1").selection("entity1")
         .set("r2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi1").selection("entity2")
         .set("r1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("coi2", "Coincident");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi2").selection("entity1")
         .set("r1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi2").selection("entity2")
         .set("r2(1)", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("xdist1", "XDistance");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("xdist1").selection("entity1")
         .set("r2(1)", 2);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("xdist1").selection("entity2")
         .set("cro1.fil14", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r3")
         .set("pos", new double[]{0.010226329718507603, -0.28694134295108975});
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r3").set("rot", 27.149681697783116);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("r3")
         .set("size", new double[]{0.017796349256254708, 0.15389824168904764});
    model.component("comp1").geom("geom1").feature("wp57").geom().create("coi3", "Coincident");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi3").selection("entity1")
         .set("r3(1)", 2);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi3").selection("entity2")
         .set("r1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("coi4", "Coincident");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi4").selection("entity1")
         .set("r1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("coi4").selection("entity2")
         .set("r3(1)", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("eqdist1", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity1").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity1")
         .set("cro1.fil14", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity2")
         .set("r3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity3").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity3")
         .set("r3(1)", 1);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist1").selection("entity4")
         .set("cro1.fil14", 3);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("eqdist2", "EqualDistance");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist2").selection("entity1")
         .set("r3(1)", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist2").selection("entity2").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist2").selection("entity2")
         .set("cro1.fil14", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist2").selection("entity3")
         .set("r3(1)", 2);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist2").selection("entity4").init(0);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("eqdist2").selection("entity4")
         .set("cro1.fil14", 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("uni1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("uni1").selection("input")
         .set("r1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("wp57").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("fil1").set("radius", "z_gcl/3");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("fil1").selection("point")
         .set("uni1(1)", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp57").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("mir1").selection("input").named("uni1");
    model.component("comp1").geom("geom1").feature("wp57").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("rot1").set("rot", "range(72,72,360)");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("rot1").selection("input").named("uni1");
    model.component("comp1").geom("geom1").feature("wp57").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("dif1").selection("input").named("uni1");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("dif1").selection("input2").set("cro1");
    model.component("comp1").geom("geom1").feature("wp57").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("c1")
         .set("posconstr", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("c1").set("rconstr", true);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("c1").set("r", "R_w*0.15");
    model.component("comp1").geom("geom1").feature("wp57").geom().create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("dif2").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("wp57").geom().feature("dif2").selection("input2").set("c1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "0 W_w-2*z_gcl/10");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("wp57");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").label("Rims");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel16", "wp57"});
    model.component("comp1").geom("geom1").create("uni11", "Union");
    model.component("comp1").geom("geom1").feature("uni11").set("selresult", true);
    model.component("comp1").geom("geom1").feature("uni11").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("uni11").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni11").selection("input").set("cyl2", "fil14", "mov1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").label("Wheel air");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"cyl2"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").label("Rear wheels");
    model.component("comp1").geom("geom1").feature("mov2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("mov2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov2").set("displx", "L_wb");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("uni11");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("Front wheels");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"uni11"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"mov2"});
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("Front wheel boundaries");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"difsel2"});
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("Rear wheel boundaries");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"mov2"});
    model.component("comp1").geom("geom1").create("dsl1", "DeleteSliverFaces");
    model.component("comp1").geom("geom1").feature("dsl1").set("delete", "all");
    model.component("comp1").geom("geom1").feature("dsl1").set("entsize", 0.003);
    model.component("comp1").geom("geom1").feature("dsl1").selection("input").set("par10");
    model.component("comp1").geom("geom1").feature("dsl1").find();
    model.component("comp1").geom("geom1").feature("dsl1").detail().setGroup(1);
    model.component("comp1").geom("geom1").run("dsl1");
    model.component("comp1").geom("geom1").create("dse1", "DeleteShortEdges");
    model.component("comp1").geom("geom1").feature("dse1").set("delete", "all");
    model.component("comp1").geom("geom1").feature("dse1").selection("input").set("dsl1");
    model.component("comp1").geom("geom1").feature("dse1").find();
    model.component("comp1").geom("geom1").feature("dse1").detail().setGroup(4);
    model.component("comp1").geom("geom1").run("dse1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("dse1", "mov2", "uni11");
    model.component("comp1").geom("geom1").create("uni12", "Union");
    model.component("comp1").geom("geom1").feature("uni12").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni12").selection("input").set("dse1", "mir1(1)");
    model.component("comp1").geom("geom1").create("fil15", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil15").label("Fillet 15 - Front Air Inlet");
    model.component("comp1").geom("geom1").feature("fil15").set("radius", 1);
    model.component("comp1").geom("geom1").feature("fil15").selection("edge").set("uni12(1)", 10);
    model.component("comp1").geom("geom1").run("fil15");
    model.component("comp1").geom("geom1").create("sel18", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel18").label("Explicit Selection - Ignore Edges 2");
    model.component("comp1").geom("geom1").feature("sel18").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel18").selection("selection")
         .set("fil15(1)", 8, 10, 15, 17, 31, 33, 159, 163, 652, 654, 661, 663);
    model.component("comp1").geom("geom1").feature("sel18").set("selshow", false);
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"adjsel1", "sel17", "sel18"});
    model.component("comp1").geom("geom1").feature("unisel2").set("selshow", false);
    model.component("comp1").geom("geom1").create("knit1", "Knit");
    model.component("comp1").geom("geom1").feature("knit1").selection("input").set("fil15");
    model.component("comp1").geom("geom1").run("knit1");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init();
    model.component("comp1").view("view1").hideObjects("hide1").add("wp17");
    model.component("comp1").view("view1").hideObjects("hide1").add("extract1(2)");

    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-2*L", "-Depth/2", "0.01[m]"});
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"Width", "Depth", "Height"});
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("blk2");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2")
         .set("knit1", "mir1(2)", "mir1(3)", "mov2", "uni11");
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel16", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel16").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel16").label("Car surfaces");
    model.component("comp1").geom("geom1").feature("boxsel16").set("xmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel16").set("xmax", "L");
    model.component("comp1").geom("geom1").feature("boxsel16").set("ymin", "-inf");
    model.component("comp1").geom("geom1").feature("boxsel16").set("ymax", "inf");
    model.component("comp1").geom("geom1").feature("boxsel16").set("zmin", "-inf");
    model.component("comp1").geom("geom1").feature("boxsel16").set("zmax", "inf");
    model.component("comp1").geom("geom1").feature("boxsel16").set("condition", "inside");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel3").label("Body");
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"boxsel16"});
    model.component("comp1").geom("geom1").feature("difsel3")
         .set("subtract", new String[]{"sel7", "sel8", "sel9", "sel10", "sel11", "sel15", "unisel1", "difsel1", "adjsel2", "adjsel3"});
    model.component("comp1").geom("geom1").create("adjsel4", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel4").set("input", new String[]{"uni9"});
    model.component("comp1").geom("geom1").feature("adjsel4").set("selshow", false);
    model.component("comp1").geom("geom1").create("boxsel17", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel17").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel17").set("xmin", 2);
    model.component("comp1").geom("geom1").feature("boxsel17").set("xmax", 2);
    model.component("comp1").geom("geom1").feature("boxsel17").set("ymin", -3);
    model.component("comp1").geom("geom1").feature("boxsel17").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel17").set("zmin", 0.5);
    model.component("comp1").geom("geom1").feature("boxsel17").set("zmax", 0.9);
    model.component("comp1").geom("geom1").feature("boxsel17").set("selshow", false);
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel3")
         .set("input", new String[]{"uni9", "adjsel4", "boxsel17"});
    model.component("comp1").geom("geom1").feature("unisel3").set("selshow", false);
    model.component("comp1").geom("geom1").create("boxsel18", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel18").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel18").label("General Extrusion");
    model.component("comp1").geom("geom1").feature("boxsel18").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel18").set("input", new String[]{"unisel3"});
    model.component("comp1").geom("geom1").feature("boxsel18").set("xmin", "-inf");
    model.component("comp1").geom("geom1").feature("boxsel18").set("xmax", "inf");
    model.component("comp1").geom("geom1").feature("boxsel18").set("ymin", "-inf");
    model.component("comp1").geom("geom1").feature("boxsel18").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel18").set("zmin", "-inf");
    model.component("comp1").geom("geom1").feature("boxsel18").set("zmax", "inf");
    model.component("comp1").geom("geom1").feature("boxsel18").set("condition", "inside");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").named("sel12");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input").named("sel13");
    model.component("comp1").geom("geom1").create("cmf3", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf3").selection("input").named("sel14");
    model.component("comp1").geom("geom1").create("cle1", "CollapseEdges");
    model.component("comp1").geom("geom1").feature("cle1").selection("input").set("cmf3(1)", 694, 696, 697, 699);
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input")
         .set("cle1(1)", 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 697, 698, 699, 700, 701, 702, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1119, 1120, 1121, 1122, 1123, 1124, 1125, 1126, 1127, 1128, 1129, 1130, 1131, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1150, 1151, 1152, 1153, 1154, 1155, 1156, 1157, 1158, 1159, 1160, 1161, 1162, 1163, 1164, 1165, 1166, 1167, 1168, 1169, 1170, 1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178, 1179, 1180, 1181, 1182, 1183, 1184, 1185, 1186, 1187, 1188, 1189, 1190, 1191, 1192, 1193, 1194, 1195, 1196, 1197, 1198, 1199, 1200, 1201, 1202, 1203, 1204, 1205, 1206, 1207, 1208, 1209, 1210, 1211, 1212, 1213, 1214, 1215, 1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1235, 1236, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262);

    return model;
  }

  public static Model run15(Model model) {
    model.component("comp1").geom("geom1").run("igv1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("Chassis");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("rev1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("rev2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("rev3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("extract1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("par1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("comsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp8");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("parf1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp9");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp10");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp11");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp12");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp13");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp14");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp15");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("par2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp16");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp17");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("int1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("extract2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp18");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp19");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp20");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp21");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp22");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("par3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("extract3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp23");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp24");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("parf2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("parf3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("extract4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel8");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("comsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel9");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp25");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel10");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp26");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp27");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp28");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp29");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp30");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp31");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel11");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp32");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("par4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare8");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare9");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare10");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel12");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare11");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel13");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel14");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp33");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp34");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp35");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cap1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare12");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare13");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare14");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp36");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp37");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft8");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp38");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("parf4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp39");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp40");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp41");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("parf5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp42");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp43");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("parf6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pare15");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("loft9");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("parf7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("par5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("extract5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni8");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cap2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ballsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil7");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel6");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil8");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel15");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cha1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("Door Line Partitioning");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter("sel7");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp44");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("cylsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("parf8");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp45");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("parf9");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("ballsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("parf10");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").label("Mirror");
    model.component("comp1").geom("geom1").nodeGroup("grp3").placeAfter("sel8");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp46");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ext8");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp47");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("par6");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp48");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("par7");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("extract6");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp49");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("swe1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("dif1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("fil9");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("fil10");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp50");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ext9");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp51");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ext10");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("int2");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp52");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ballsel3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp53");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("loft10");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("uni9");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ballsel4");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("fil11");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("uni10");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("del6");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("ballsel5");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("fil12");
    model.component("comp1").geom("geom1").nodeGroup().create("grp4");
    model.component("comp1").geom("geom1").nodeGroup("grp4").label("Front Light Partitioning");
    model.component("comp1").geom("geom1").nodeGroup("grp4").placeAfter("sel8");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("wp54");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("ext11");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("par8");
    model.component("comp1").geom("geom1").nodeGroup().create("grp5");
    model.component("comp1").geom("geom1").nodeGroup("grp5").label("Rear Light Partitioning");
    model.component("comp1").geom("geom1").nodeGroup("grp5").placeAfter("sel9");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("wp55");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("ext12");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("par9");
    model.component("comp1").geom("geom1").nodeGroup().create("grp6");
    model.component("comp1").geom("geom1").nodeGroup("grp6").label("Top Window Partitioning");
    model.component("comp1").geom("geom1").nodeGroup("grp6").placeAfter("sel10");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("wp56");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("ext13");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("par10");
    model.component("comp1").geom("geom1").nodeGroup().create("grp7");
    model.component("comp1").geom("geom1").nodeGroup("grp7").label("Wheels");
    model.component("comp1").geom("geom1").nodeGroup("grp7").placeAfter("sel14");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("cyl1");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("blk1");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("dif2");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("fil13");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("sel15");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("fil14");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("sel16");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("sel17");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("cyl2");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("wp57");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("mov1");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("unisel1");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("uni11");
    model.component("comp1").geom("geom1").run();

    model.title(null);

    model.description("");

    model.label("sports_car_geom_sequence.mph");

    model.component("comp1").geom("geom1").run("fil11");

    model.param().set("cut_1", "5[cm]", "Cut parameter for door partitioning");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("cadps");
    model.component("comp2").geom("geom2").designBooleans(true);
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "sequence");
    model.component("comp2").geom("geom2").feature("imp1").set("sequence", "geom1");
    model.component("comp2").geom("geom2").feature("imp1").set("selresult", true);
    model.component("comp2").geom("geom2").feature("imp1").set("selresultshow", false);
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp1").geom("geom1").run();
    model.component("comp2").geom("geom2").create("del1", "Delete");
    model.component("comp2").geom("geom2").feature("del1").selection("input").init();
    model.component("comp2").geom("geom2").feature("del1").selection("input")
         .set("imp1(1)", "imp1(10)", "imp1(2)", "imp1(3)", "imp1(4)", "imp1(5)", "imp1(6)", "imp1(7)", "imp1(9)");
    model.component("comp2").geom("geom2").create("mir1", "Mirror");
    model.component("comp2").geom("geom2").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp2").geom("geom2").feature("mir1").selection("input").set("imp1(11)", "imp1(8)");
    model.component("comp2").geom("geom2").create("par1", "Partition");
    model.component("comp2").geom("geom2").feature("par1").set("keeptool", true);
    model.component("comp2").geom("geom2").feature("par1").selection("input").set("mir1(1)");
    model.component("comp2").geom("geom2").feature("par1").selection("tool").set("mir1(2)");
    model.component("comp2").geom("geom2").create("extract1", "Extract");
    model.component("comp2").geom("geom2").feature("extract1").label("Mirror Boundaries");
    model.component("comp2").geom("geom2").feature("extract1").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("extract1").set("selresult", true);
    model.component("comp2").geom("geom2").feature("extract1").set("selresultshow", "bnd");
    model.component("comp2").geom("geom2").feature("extract1").selection("input").init(3);
    model.component("comp2").geom("geom2").feature("extract1").selection("input").set("par1(1)", 1);
    model.component("comp2").geom("geom2").create("extract2", "Extract");
    model.component("comp2").geom("geom2").feature("extract2").label("Extract - Shoulder");
    model.component("comp2").geom("geom2").feature("extract2").selection("input")
         .set("mir1(2)", 23, 30, 32, 33, 59, 60);
    model.component("comp2").geom("geom2").create("extract3", "Extract");
    model.component("comp2").geom("geom2").feature("extract3").label("Extract - Door Boundaries");
    model.component("comp2").geom("geom2").feature("extract3").set("selresult", true);
    model.component("comp2").geom("geom2").feature("extract3").set("selresultshow", "bnd");
    model.component("comp2").geom("geom2").feature("extract3").selection("input").set("mir1(2)", 32, 33, 34);
    model.component("comp2").geom("geom2").create("extract4", "Extract");
    model.component("comp2").geom("geom2").feature("extract4").label("Window Boundary");
    model.component("comp2").geom("geom2").feature("extract4").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("extract4").set("selresult", true);
    model.component("comp2").geom("geom2").feature("extract4").set("selresultshow", "bnd");
    model.component("comp2").geom("geom2").feature("extract4").selection("input").set("mir1(2)", 31);
    model.component("comp2").geom("geom2").create("extract5", "Extract");
    model.component("comp2").geom("geom2").feature("extract5").label("Extract - Door Surface");
    model.component("comp2").geom("geom2").feature("extract5").selection("input").set("extract3(1)", 3);
    model.component("comp2").geom("geom2").create("mov1", "Move");
    model.component("comp2").geom("geom2").feature("mov1").set("keep", true);
    model.component("comp2").geom("geom2").feature("mov1").set("disply", "1.5[cm] 4[cm]");
    model.component("comp2").geom("geom2").feature("mov1").set("propagatesel", false);
    model.component("comp2").geom("geom2").feature("mov1").selection("input").set("extract5");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").label("Work Plane - Position y Direction");
    model.component("comp2").geom("geom2").feature("wp1").set("quickplane", "yz");
    model.component("comp2").geom("geom2").feature("wp1").set("quickoffsettype", "vertex");
    model.component("comp2").geom("geom2").feature("wp1").selection("offsetvertex").set("extract4(1)", 6);
    model.component("comp2").geom("geom2").feature("wp1").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp1").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("cro1").set("intersect", "selected");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("cro1").selection("input").set("extract3");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r1")
         .set("size", new double[]{0.028625332639842127, 0.8679496984892564});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r1")
         .set("posconstr", new String[]{"off", "on"});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r1")
         .set("pos", new double[]{-0.6878873864378295, 0});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r1").set("rotconstr", true);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("coi1", "Coincident");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("coi1").selection("entity1")
         .set("r1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("coi1").selection("entity2")
         .set("cro1.extract3", 3);
    model.component("comp2").geom("geom2").feature("wp1").geom().create("coi2", "Coincident");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("coi2").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("coi2").selection("entity1")
         .set("cro1.extract3", 4);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("coi2").selection("entity2")
         .set("r1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp1").geom().create("extract1", "Extract");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("extract1").selection("input")
         .set("r1(1)", 2, 4);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("extract1")
         .setAttribute("construction", "off");
    model.component("comp2").geom("geom2").feature("wp1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").create("wp2", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp2").label("Work Plane - Projection and Cut");
    model.component("comp2").geom("geom2").feature("wp2").set("quickplane", "xz");
    model.component("comp2").geom("geom2").feature("wp2").geom().create("proj1", "Projection");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("proj1").set("project", "obj");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("proj1").selection("input").set("extract3");
    model.component("comp2").geom("geom2").feature("wp2").geom().create("ls1", "LineSegment");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("ls1").selection("vertex1")
         .set("proj1.extract3", 6);
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("ls1").selection("vertex2")
         .set("proj1.extract3", 3);
    model.component("comp2").geom("geom2").feature("wp2").geom().create("del1", "Delete");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("del1").selection("input")
         .set("proj1.extract3", 4, 5, 6, 7, 12, 13);
    model.component("comp2").geom("geom2").feature("wp2").geom().create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("uni1").selection("input")
         .set("del1", "ls1");
    model.component("comp2").geom("geom2").feature("wp2").geom().create("off1", "Offset");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off1").label("Offset - cut_1*0.5");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off1").set("distance", "cut_1*0.5");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off1").selection("input").set("uni1");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off1").setAttribute("construction", "off");
    model.component("comp2").geom("geom2").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("fil1").set("radius", "4[cm]");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("fil1").selection("point")
         .set("off1(1)", 3, 5);
    model.component("comp2").geom("geom2").feature("wp2").geom().create("off2", "Offset");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off2").label("Offset - cut_1");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off2").set("distance", "cut_1");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off2").selection("input").set("uni1");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off2").setAttribute("construction", "off");
    model.component("comp2").geom("geom2").feature("wp2").geom().create("fil2", "Fillet");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("fil2").set("radius", "10[cm]");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("fil2").selection("point")
         .set("off2(1)", 3, 5);
    model.component("comp2").geom("geom2").feature("wp2").geom().create("off3", "Offset");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off3").label("Offset - cut_1*1.5");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off3").set("distance", "cut_1*1.5");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off3").selection("input").set("uni1");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("off3").setAttribute("construction", "off");
    model.component("comp2").geom("geom2").feature("wp2").geom().create("fil3", "Fillet");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("fil3").set("radius", "4[cm]");
    model.component("comp2").geom("geom2").feature("wp2").geom().feature("fil3").selection("point")
         .set("off3(1)", 3, 5);
    model.component("comp2").geom("geom2").create("ext1", "Extrude");
    model.component("comp2").geom("geom2").feature("ext1").label("Extrude - Cut Offset");
    model.component("comp2").geom("geom2").feature("ext1").set("specify", "vertices");
    model.component("comp2").geom("geom2").feature("ext1").set("includeinput", false);
    model.component("comp2").geom("geom2").feature("ext1").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp2").geom("geom2").feature("ext1").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp2").geom("geom2").feature("ext1").set("twist", new int[]{0, 0});
    model.component("comp2").geom("geom2").feature("ext1").selection("input").set("wp2");
    model.component("comp2").geom("geom2").feature("ext1").selection("vertex").set("extract1(1)", 49);
    model.component("comp2").geom("geom2").feature("ext1").selection("vertex").set("wp1.extract1", 1);
    model.component("comp2").geom("geom2").feature("ext1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").create("wp3", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp3").label("Work Plane - Inner Door Panel");
    model.component("comp2").geom("geom2").feature("wp3").set("planetype", "vertices");
    model.component("comp2").geom("geom2").feature("wp3").set("rot", 270);
    model.component("comp2").geom("geom2").feature("wp3").selection("vertex1").set("wp1.extract1", 1);
    model.component("comp2").geom("geom2").feature("wp3").selection("vertex2").set("wp1.extract1", 2);
    model.component("comp2").geom("geom2").feature("wp3").selection("vertex3").set("extract4(1)", 2);
    model.component("comp2").geom("geom2").feature("wp3").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp3").geom().create("proj1", "Projection");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("proj1").set("project", "edg");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("proj1").selection("input")
         .set("extract3(1)", 6, 13, 14, 15, 16);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("proj1").selection("input")
         .set("extract1(1)", 50);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("proj1").selection("input")
         .set("ext1(4)", 1, 3, 4, 7, 14, 17, 20);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp3").geom().create("off1", "Offset");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("off1").label("Offset - cut_1*1.2");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("off1").set("distance", "cut_1*1.2");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("off1").selection("input").set("proj1");
    model.component("comp2").geom("geom2").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("pol1")
         .set("table", new double[][]{{-1.2766591370631306, 0.7376583320617838}, {-1.2766591370631226, 0.28394016310963466}});

    return model;
  }

  public static Model run16(Model model) {
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp3").geom().create("ver1", "Vertical");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("ver1").selection("edge").set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp3").geom().create("coi1", "Coincident");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi1").selection("entity2")
         .set("off1(2)", 1);
    model.component("comp2").geom("geom2").feature("wp3").geom().create("coi2", "Coincident");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi2")
         .set("helppoint1", new double[]{-1.2766591370631226, 0.28394016310963466});
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi2").selection("entity1")
         .set("off1(3)", 2);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp3").geom().create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("uni1").selection("input")
         .set("off1(1)", "off1(3)", "pol1");
    model.component("comp2").geom("geom2").feature("wp3").geom().create("extract1", "Extract");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("extract1").selection("input")
         .set("uni1(1)", 1, 12, 13, 14, 15, 16);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("extract1")
         .setAttribute("construction", "off");
    model.component("comp2").geom("geom2").feature("wp3").geom().create("fil1", "Fillet");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("fil1").set("radius", "4[cm]");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("fil1").selection("point")
         .set("extract1(1)", 2, 6);
    model.component("comp2").geom("geom2").feature("wp3").geom().create("fil2", "Fillet");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("fil2").set("radius", "10[cm]");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("fil2").selection("point")
         .set("fil1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp3").geom().create("fil3", "Fillet");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("fil3").set("radius", "40[cm]");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("fil3").selection("point")
         .set("fil2(1)", 6);
    model.component("comp2").geom("geom2").feature("wp3").geom().create("csol1", "ConvertToSolid");
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("csol1").set("keep", true);
    model.component("comp2").geom("geom2").feature("wp3").geom().feature("csol1").selection("input").set("fil3");
    model.component("comp2").geom("geom2").create("ext2", "Extrude");
    model.component("comp2").geom("geom2").feature("ext2").setIndex("distance", "2[cm]", 0);
    model.component("comp2").geom("geom2").feature("ext2").selection("input").set("wp3.fil3");
    model.component("comp2").geom("geom2").create("wp4", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp4").label("Work Plane - Window Slot Position");
    model.component("comp2").geom("geom2").feature("wp4").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp4").set("offsettype", "vertex");
    model.component("comp2").geom("geom2").feature("wp4").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp4").selection("offsetvertex").set("ext2(1)", 20);
    model.component("comp2").geom("geom2").feature("wp4").selection("face").set("wp3.csol1", 1);
    model.component("comp2").geom("geom2").feature("wp4").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("proj1", "Projection");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("proj1").set("project", "bnd");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("proj1").selection("input")
         .set("mov1(2)", 1);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("proj1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("proj2", "Projection");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("proj2").set("project", "obj");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("proj2").selection("input")
         .set("wp3.csol1");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("proj2").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("off1", "Offset");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off1").set("distance", "cut_1*0.8");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off1").set("convexcorner", "extend");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off1").selection("input").set("proj2");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("off2", "Offset");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off2").set("distance", "20[mm]");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off2").set("convexcorner", "extend");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off2").selection("input").set("proj1");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("off2").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pol1").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pol1")
         .set("table", new double[][]{{-0.5895771252979667, 0.18146630494196242}, {-0.5895771252979729, 0.29227199201659465}});
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("coi1", "Coincident");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi1").selection("entity2")
         .set("off1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("coi2", "Coincident");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi2")
         .set("helppoint1", new double[]{-0.5895771252979728, 0.29227199201659465});
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi2").selection("entity1")
         .set("proj1.mov1(2)", 9);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("tanc1", "TangentConstraint");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("tanc1").selection("edge1")
         .set("off1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("tanc1").selection("edge2")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("pol2", "Polygon");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pol2").set("type", "open");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pol2").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pol2")
         .set("table", new double[][]{{0.6331474640424725, 0.2795161054435784}, {0.3620995729838463, -0.25114752196131973}, {0.09580959710062806, -0.25382351272076403}});
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pol2").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("coi3", "Coincident");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi3").selection("entity1")
         .set("pol2(1)", 3);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi3").selection("entity2")
         .set("off1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("coi4", "Coincident");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi4").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi4").selection("entity2")
         .set("off1(1)", 10);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("coi5", "Coincident");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi5")
         .set("helppoint1", new double[]{0.6331474640424725, 0.2795161054435784});
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi5").selection("entity1")
         .set("proj1.mov1(2)", 11);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("coi5").selection("entity2")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("tanc2", "TangentConstraint");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.0958095971005081, -0.25382351272076537});
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("tanc2").selection("edge1")
         .set("off1(1)", 7);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("tanc2").selection("edge2")
         .set("pol2(1)", 2);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("fil1", "Fillet");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("fil1").set("radius", "30[cm]");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("fil1").selection("point")
         .set("pol2(1)", 2);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("pare1", "PartitionEdges");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pare1")
         .set("param", new double[]{0.3, 0.6});
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("pare1").selection("edge")
         .set("off1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("par1", "Partition");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("par1").selection("input")
         .set("fil1", "pol1");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("par1").selection("tool").set("off2");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("csol1", "ConvertToSolid");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("csol1").selection("input")
         .set("par1", "pare1", "proj1", "proj2");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("boxsel1", "BoxSelection");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel1").set("xmin", 0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel1").set("xmax", 0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel1").set("ymin", 0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel1").set("ymax", 0);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel1").set("selshow", false);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("boxsel2", "BoxSelection");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel2").set("xmin", -0.6);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel2").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("boxsel2").set("selshow", false);
    model.component("comp2").geom("geom2").feature("wp4").geom().create("difsel1", "DifferenceSelection");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("difsel1")
         .set("add", new String[]{"boxsel2"});
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("difsel1")
         .set("subtract", new String[]{"boxsel1"});
    model.component("comp2").geom("geom2").feature("wp4").geom().create("extract1", "Extract");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("extract1").selection("input").init(2);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("extract1").selection("input")
         .named("difsel1");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("extract1")
         .setAttribute("construction", "off");
    model.component("comp2").geom("geom2").feature("wp4").geom().create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("uni1").set("intbnd", false);
    model.component("comp2").geom("geom2").feature("wp4").geom().feature("uni1").selection("input").set("extract1");
    model.component("comp2").geom("geom2").create("sel1", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel1").selection("selection").init(1);
    model.component("comp2").geom("geom2").feature("sel1").selection("selection")
         .set("wp4.uni1", 1, 2, 3, 4, 5, 6, 7, 8, 11, 13, 15, 20, 26);
    model.component("comp2").geom("geom2").feature("sel1").set("groupcontang", true);
    model.component("comp2").geom("geom2").feature("sel1").set("selshow", false);
    model.component("comp2").geom("geom2").create("par2", "Partition");
    model.component("comp2").geom("geom2").feature("par2").set("keeptool", true);
    model.component("comp2").geom("geom2").feature("par2").selection("input").set("ext1(2)", "ext1(3)");
    model.component("comp2").geom("geom2").feature("par2").selection("tool").set("extract5", "mov1");
    model.component("comp2").geom("geom2").create("parf1", "PartitionFaces");
    model.component("comp2").geom("geom2").feature("parf1").label("Partition Faces - for Lock (Lock Side) 1");
    model.component("comp2").geom("geom2").feature("parf1").selection("face").set("par2(2)", 22);
    model.component("comp2").geom("geom2").feature("parf1").selection("vertexsegment").set("par2(2)", 36, 38);
    model.component("comp2").geom("geom2").create("parf2", "PartitionFaces");
    model.component("comp2").geom("geom2").feature("parf2").label("Partition Faces - for Lock (Lock Side) 2");
    model.component("comp2").geom("geom2").feature("parf2").selection("face").set("par2(1)", 22);
    model.component("comp2").geom("geom2").feature("parf2").selection("vertexsegment").set("par2(1)", 36, 37);
    model.component("comp2").geom("geom2").create("parf3", "PartitionFaces");
    model.component("comp2").geom("geom2").feature("parf3").label("Partition Faces - for Lock (Hinge Side) 1");
    model.component("comp2").geom("geom2").feature("parf3").selection("face").set("parf1(1)", 4);
    model.component("comp2").geom("geom2").feature("parf3").selection("vertexsegment").set("parf1(1)", 16, 18);
    model.component("comp2").geom("geom2").create("parf4", "PartitionFaces");
    model.component("comp2").geom("geom2").feature("parf4").label("Partition Faces - for Lock (Hinge Side) 2");
    model.component("comp2").geom("geom2").feature("parf4").selection("face").set("parf2(1)", 4);
    model.component("comp2").geom("geom2").feature("parf4").selection("vertexsegment").set("parf2(1)", 16, 17);
    model.component("comp2").geom("geom2").create("extract6", "Extract");
    model.component("comp2").geom("geom2").feature("extract6").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("extract6").selection("input")
         .set("parf3(1)", 8, 9, 10, 15, 18, 22, 26);
    model.component("comp2").geom("geom2").feature("extract6").selection("input")
         .set("parf4(1)", 5, 6, 7, 11, 16, 20, 24);
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").set("extract5", "extract6", "mov1");
    model.component("comp2").geom("geom2").create("extract7", "Extract");
    model.component("comp2").geom("geom2").feature("extract7").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("extract7").selection("input")
         .set("uni1(1)", 1, 4, 6, 7, 8, 9, 10, 12, 13, 15, 16, 17, 18, 19, 20, 21);
    model.component("comp2").geom("geom2").create("wp5", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp5").label("Work Plane - Guide Curve Loft 1");
    model.component("comp2").geom("geom2").feature("wp5").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp5").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp5").selection("face").set("extract7(1)", 16);
    model.component("comp2").geom("geom2").feature("wp5").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp5").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp5").geom().create("ls1", "LineSegment");
    model.component("comp2").geom("geom2").feature("wp5").geom().feature("ls1").selection("vertex1")
         .set("cro1.extract3", 1);
    model.component("comp2").geom("geom2").feature("wp5").geom().feature("ls1").selection("vertex2")
         .set("cro1.extract7", 1);
    model.component("comp2").geom("geom2").create("pare1", "PartitionEdges");
    model.component("comp2").geom("geom2").feature("pare1").set("position", "projection");
    model.component("comp2").geom("geom2").feature("pare1").selection("edge").set("extract3(1)", 19);
    model.component("comp2").geom("geom2").feature("pare1").selection("vertexproj").set("wp5.ls1", 1);
    model.component("comp2").geom("geom2").create("loft1", "Loft");
    model.component("comp2").geom("geom2").feature("loft1").label("Loft 1 - Extending Profile");
    model.component("comp2").geom("geom2").feature("loft1").set("type", "surface");
    model.component("comp2").geom("geom2").feature("loft1").selection("profile").set();
    model.component("comp2").geom("geom2").feature("loft1").selection("startprofile").init(1);
    model.component("comp2").geom("geom2").feature("loft1").selection("startprofile")
         .set("extract7(1)", 54, 55, 56, 57);
    model.component("comp2").geom("geom2").feature("loft1").selection("guide").set("wp5");
    model.component("comp2").geom("geom2").create("wp6", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp6").label("Work Plane - Guide Curve Loft 2");
    model.component("comp2").geom("geom2").feature("wp6").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp6").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp6").selection("face").set("loft1(1)", 10);
    model.component("comp2").geom("geom2").feature("wp6").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp6").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp6").geom().create("ls1", "LineSegment");
    model.component("comp2").geom("geom2").feature("wp6").geom().feature("ls1").selection("vertex1")
         .set("cro1.pare1", 2);
    model.component("comp2").geom("geom2").feature("wp6").geom().feature("ls1").selection("vertex2")
         .set("cro1.loft1", 2);
    model.component("comp2").geom("geom2").create("pare2", "PartitionEdges");
    model.component("comp2").geom("geom2").feature("pare2").set("position", "projection");
    model.component("comp2").geom("geom2").feature("pare2").selection("edge").set("pare1(1)", 8);
    model.component("comp2").geom("geom2").feature("pare2").selection("vertexproj").set("wp6.ls1", 1);
    model.component("comp2").geom("geom2").create("loft2", "Loft");
    model.component("comp2").geom("geom2").feature("loft2").label("Loft 2 - Extending Profile");
    model.component("comp2").geom("geom2").feature("loft2").selection("profile").set();
    model.component("comp2").geom("geom2").feature("loft2").selection("startprofile").init(1);
    model.component("comp2").geom("geom2").feature("loft2").selection("startprofile")
         .set("loft1(1)", 5, 20, 21, 22, 34);
    model.component("comp2").geom("geom2").feature("loft2").selection("guide").set("wp6");
    model.component("comp2").geom("geom2").create("pare3", "PartitionEdges");
    model.component("comp2").geom("geom2").feature("pare3").label("Partition Edges 3 - Main Door Profile");
    model.component("comp2").geom("geom2").feature("pare3").set("position", "projection");
    model.component("comp2").geom("geom2").feature("pare3").selection("edge").set("loft2(1)", 62);
    model.component("comp2").geom("geom2").feature("pare3").selection("vertexproj").set("wp4.uni1", 22, 24);
    model.component("comp2").geom("geom2").create("pare4", "PartitionEdges");
    model.component("comp2").geom("geom2").feature("pare4").label("Partition Edges 4 - Main Door Profile");
    model.component("comp2").geom("geom2").feature("pare4").set("position", "projection");
    model.component("comp2").geom("geom2").feature("pare4").selection("edge").set("pare3(1)", 50);
    model.component("comp2").geom("geom2").feature("pare4").selection("vertexproj").set("wp4.uni1", 11, 15);
    model.component("comp2").geom("geom2").create("sel2", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel2").label("Explicit Selection - Start Profile Loft 3");
    model.component("comp2").geom("geom2").feature("sel2").selection("selection").init(1);
    model.component("comp2").geom("geom2").feature("sel2").selection("selection")
         .set("pare4(1)", 35, 36, 37, 40, 43, 50, 51, 52, 55, 64, 66, 67, 71);
    model.component("comp2").geom("geom2").feature("sel2").set("groupcontang", true);
    model.component("comp2").geom("geom2").feature("sel2").set("angletol", 10);
    model.component("comp2").geom("geom2").feature("sel2").set("selshow", false);
    model.component("comp2").geom("geom2").create("loft3", "Loft");
    model.component("comp2").geom("geom2").feature("loft3").label("Loft 3 - Main Door Profile");
    model.component("comp2").geom("geom2").feature("loft3").set("unite", false);
    model.component("comp2").geom("geom2").feature("loft3").set("type", "surface");
    model.component("comp2").geom("geom2").feature("loft3").set("facepartitioning", "columns");
    model.component("comp2").geom("geom2").feature("loft3").selection("profile").set();
    model.component("comp2").geom("geom2").feature("loft3").selection("startprofile").init(1);
    model.component("comp2").geom("geom2").feature("loft3").selection("startprofile").named("sel2");
    model.component("comp2").geom("geom2").feature("loft3").selection("endprofile").init(1);
    model.component("comp2").geom("geom2").feature("loft3").selection("endprofile").named("sel1");
    model.component("comp2").geom("geom2").create("uni2", "Union");
    model.component("comp2").geom("geom2").feature("uni2").label("Union 11 - Main Door Profile");
    model.component("comp2").geom("geom2").feature("uni2").set("intbnd", false);
    model.component("comp2").geom("geom2").feature("uni2").set("selresult", true);
    model.component("comp2").geom("geom2").feature("uni2").set("selresultshow", false);
    model.component("comp2").geom("geom2").feature("uni2").selection("input")
         .set("ext2", "loft3", "pare4", "wp3.csol1", "wp4");
    model.component("comp2").geom("geom2").create("sel3", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel3").label("Explicit Selection 3 - for Fillet");
    model.component("comp2").geom("geom2").feature("sel3").selection("selection").init(1);
    model.component("comp2").geom("geom2").feature("sel3").selection("selection")
         .set("uni2(1)", 11, 13, 15, 20, 23, 30, 32, 34, 41, 45, 51, 64, 65, 66, 68, 74, 77, 83, 87, 94, 99, 101, 104, 107, 114, 117, 129, 135);
    model.component("comp2").geom("geom2").feature("sel3").set("groupcontang", true);
    model.component("comp2").geom("geom2").feature("sel3").set("angletol", 10);
    model.component("comp2").geom("geom2").feature("sel3").set("selshow", false);
    model.component("comp2").geom("geom2").create("sel4", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel4").label("Explicit Selection 4 - for Fillet");
    model.component("comp2").geom("geom2").feature("sel4").selection("selection").init(1);
    model.component("comp2").geom("geom2").feature("sel4").selection("selection")
         .set("uni2(1)", 16, 17, 18, 21, 24, 52, 59, 61, 63, 73, 76, 82, 86, 97, 102, 105, 112, 116, 137);
    model.component("comp2").geom("geom2").feature("sel4").set("groupcontang", true);
    model.component("comp2").geom("geom2").feature("sel4").set("angletol", 10);
    model.component("comp2").geom("geom2").feature("sel4").set("selshow", false);
    model.component("comp2").geom("geom2").create("sel5", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel5").label("Explicit Selection 5 - for Fillet");
    model.component("comp2").geom("geom2").feature("sel5").selection("selection").init(1);
    model.component("comp2").geom("geom2").feature("sel5").selection("selection")
         .set("uni2(1)", 35, 36, 39, 42, 46, 70, 80, 90, 95, 109, 121, 124, 131);
    model.component("comp2").geom("geom2").feature("sel5").set("groupcontang", true);
    model.component("comp2").geom("geom2").feature("sel5").set("angletol", 15);
    model.component("comp2").geom("geom2").feature("sel5").set("selshow", false);
    model.component("comp2").geom("geom2").create("fil1", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil1").label("Fillet 1 - 0.5[cm]");
    model.component("comp2").geom("geom2").feature("fil1").set("radius", "0.5[cm]");
    model.component("comp2").geom("geom2").feature("fil1").selection("edge").named("sel3");
    model.component("comp2").geom("geom2").create("fil2", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil2").label("Fillet 2 - 0.5[cm]");
    model.component("comp2").geom("geom2").feature("fil2").set("radius", "0.5[cm]");
    model.component("comp2").geom("geom2").feature("fil2").selection("edge").named("sel4");
    model.component("comp2").geom("geom2").create("fil3", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil3").label("Fillet 3 - 1.8[cm]");
    model.component("comp2").geom("geom2").feature("fil3").set("radius", "1.8[cm]");
    model.component("comp2").geom("geom2").feature("fil3").selection("edge").named("sel1");
    model.component("comp2").geom("geom2").create("fil4", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil4").label("Fillet 4 - 2.15[cm]");
    model.component("comp2").geom("geom2").feature("fil4").set("radius", "2.15[cm]");
    model.component("comp2").geom("geom2").feature("fil4").set("propagate", false);
    model.component("comp2").geom("geom2").feature("fil4").set("selresult", true);
    model.component("comp2").geom("geom2").feature("fil4").set("selresultshow", false);
    model.component("comp2").geom("geom2").feature("fil4").selection("edge").named("sel5");
    model.component("comp2").geom("geom2").create("thi2", "Thicken");
    model.component("comp2").geom("geom2").feature("thi2").set("offset", "asymmetric");
    model.component("comp2").geom("geom2").feature("thi2").set("upthick", "15[mm]");
    model.component("comp2").geom("geom2").feature("thi2").selection("input").set("extract2");
    model.component("comp2").geom("geom2").create("dif1", "Difference");
    model.component("comp2").geom("geom2").feature("dif1").selection("input").set("fil4");
    model.component("comp2").geom("geom2").feature("dif1").selection("input2").set("thi2");
    model.component("comp2").geom("geom2").create("wp7", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp7").label("Work Plane - Position of Window (Slid In)");
    model.component("comp2").geom("geom2").feature("wp7").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp7").selection("face").set("extract4(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp7").geom().constrDimBuild("uptotarget");
    model.component("comp2").geom("geom2").feature("wp7").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("cro1").set("intersect", "selected");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("cro1").selection("input")
         .set("extract4", "dif1", "pare2");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp7").geom().create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol1").set("type", "open");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol1").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol1")
         .set("table", new double[][]{{-0.05423016005111414, 0.16300400400839543}, {-0.07687967482017218, -0.06969631717901989}, {-0.528264641939093, -0.046124905277080165}});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi1", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi1").selection("entity2")
         .set("cro1.extract4", 4);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi2", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi2")
         .set("helppoint1", new double[]{-0.5282646419390928, -0.046124905277080165});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi2").selection("entity1")
         .set("cro1.extract4", 4);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi2").selection("entity2").init(0);

    return model;
  }

  public static Model run17(Model model) {
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("telen1", "TotalEdgeLength");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("telen1").set("length", 0.2338);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("telen1").selection("edge")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("telen2", "TotalEdgeLength");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("telen2").set("length", 0.452);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("telen2").selection("edge")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("ang1", "Angle");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang1").set("reverse2", true);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang1").set("angle", 92.57);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang1").selection("edge1")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang1").selection("edge2")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("mov1", "Move");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("mov1").set("specify", "pos");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("mov1").selection("input")
         .set("cro1.extract4");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("mov1").selection("oldposvertex")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("mov1").selection("newposvertices")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("pol2", "Polygon");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol2").set("type", "open");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol2").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol2")
         .set("table", new double[][]{{-0.5909547594591928, -0.19539906520445138}, {0.6165248800822019, -0.3027236599021692}});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("pol2").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi3", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi3").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi3").selection("entity2")
         .set("cro1.dif1", 12);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("ang2", "Angle");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang2").set("reverse2", true);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang2").set("angle", 2.09);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang2").selection("edge1")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("ang2").selection("edge2")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi4", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi4")
         .set("helppoint2", new double[]{0.6165248800822019, -0.3027236599021692});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi4").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi4").selection("entity1")
         .set("pol2(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi4").selection("entity2")
         .set("cro1.dif1", 26);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r1")
         .set("sizeconstr", new String[]{"on", "off"});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r1")
         .set("size", new double[]{2, 0.3545141119970934});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r1").set("base", "center");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r1")
         .set("posconstr", new String[]{"on", "off"});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r1")
         .set("pos", new double[]{0, -0.019772006388545538});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r1").set("rot", -5.079281659866161);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi5", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi5").selection("entity1")
         .set("r1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi5").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("para1", "Parallel");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("para1").selection("edge")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("para1").selection("edge").set("r1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("dist1", "Distance");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist1").set("distance", "cut_1");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist1").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist1").selection("entity2")
         .set("r1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r2")
         .set("sizeconstr", new String[]{"off", "on"});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r2")
         .set("size", new String[]{"2", "cut_1/2"});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r2")
         .set("pos", new double[]{-1.0117664421130563, -0.1077988802121639});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r2").set("rot", -5.079281659866161);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi6", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi6").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi6").selection("entity1")
         .set("r2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi6").selection("entity2")
         .set("r1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi7", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi7").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi7").selection("entity1")
         .set("r2(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi7").selection("entity2")
         .set("r1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("r3", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r3")
         .set("size", new double[]{1.114562218473681, 0.056375021856084916});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r3")
         .set("pos", new double[]{-0.5467849436326979, -0.12402934238137045});
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("r3").set("rot", -5.079281659866156);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi8", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi8").selection("entity1")
         .set("r2(1)", 3);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi8").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi8").selection("entity2")
         .set("r3(1)", 1);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi9", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi9").selection("entity1")
         .set("r2(1)", 3);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi9").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi9").selection("entity2")
         .set("r3(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("coi10", "Coincident");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi10").selection("entity1")
         .set("r3(1)", 3);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi10").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("coi10").selection("entity2")
         .set("cro1.dif1", 29);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("dist2", "Distance");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist2").set("distance", "cut_1/2*0.8");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist2").selection("entity1")
         .set("r3(1)", 4);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist2").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist2").selection("entity2")
         .set("cro1.dif1", 13);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("dist3", "Distance");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist3").set("distance", "cut_1*1.2");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist3").selection("entity1")
         .set("r3(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist3").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dist3").selection("entity2")
         .set("pol2(1)", 2);
    model.component("comp2").geom("geom2").feature("wp7").geom().create("dif1", "Difference");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dif1").set("keepadd", true);
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dif1").selection("input").set("r1");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("dif1").selection("input2").set("r2", "r3");
    model.component("comp2").geom("geom2").feature("wp7").geom().create("fil1", "Fillet");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("fil1").set("radius", "3[cm]");
    model.component("comp2").geom("geom2").feature("wp7").geom().feature("fil1").selection("point")
         .set("dif1(1)", 4, 6);
    model.component("comp2").geom("geom2").create("ext3", "Extrude");
    model.component("comp2").geom("geom2").feature("ext3").label("Extrude 3 - Cut for Window");
    model.component("comp2").geom("geom2").feature("ext3").setIndex("distance", "3[cm]", 0);
    model.component("comp2").geom("geom2").feature("ext3").selection("input").set("wp7.r1");
    model.component("comp2").geom("geom2").create("ext4", "Extrude");
    model.component("comp2").geom("geom2").feature("ext4").label("Extrude 4 - Cut for Window");
    model.component("comp2").geom("geom2").feature("ext4").setIndex("distance", "cut_1", 0);
    model.component("comp2").geom("geom2").feature("ext4").set("reverse", true);
    model.component("comp2").geom("geom2").feature("ext4").selection("input").set("wp7.fil1");
    model.component("comp2").geom("geom2").create("wp8", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp8").label("Work Plane - Cut for Window Chamfer");
    model.component("comp2").geom("geom2").feature("wp8").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp8").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp8").selection("face").set("ext3(1)", 6);
    model.component("comp2").geom("geom2").feature("wp8").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("cro1").set("intersect", "selected");
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("cro1").selection("input")
         .set("ext3", "ext4");
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp8").geom().create("ls1", "LineSegment");
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("ls1").selection("vertex1")
         .set("cro1.ext4", 4);
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("ls1").selection("vertex2")
         .set("cro1.ext3", 4);
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("ls1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp8").geom().create("csol1", "ConvertToSolid");
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("csol1").selection("input")
         .set("cro1", "ls1");
    model.component("comp2").geom("geom2").feature("wp8").geom().create("extract1", "Extract");
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("extract1").selection("input").init(2);
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("extract1").selection("input")
         .set("csol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp8").geom().feature("extract1")
         .setAttribute("construction", "off");
    model.component("comp2").geom("geom2").run("wp8");
    model.component("comp2").geom("geom2").create("ext5", "Extrude");
    model.component("comp2").geom("geom2").feature("ext5").set("specify", "vertices");
    model.component("comp2").geom("geom2").feature("ext5").set("includeinput", false);
    model.component("comp2").geom("geom2").feature("ext5")
         .set("scale", new double[][]{{1, 1}, {1, 1}, {1, 1}, {1, 1}});
    model.component("comp2").geom("geom2").feature("ext5")
         .set("displ", new double[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}});
    model.component("comp2").geom("geom2").feature("ext5").set("twist", new int[]{0, 0, 0, 0});
    model.component("comp2").geom("geom2").feature("ext5").selection("input").set("wp8");
    model.component("comp2").geom("geom2").feature("ext5").selection("vertex").set("ext4(1)", 2, 6, 14);
    model.component("comp2").geom("geom2").feature("ext5").selection("vertex").set("wp8.extract1", 2);
    model.component("comp2").geom("geom2").create("extract8", "Extract");
    model.component("comp2").geom("geom2").feature("extract8").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("extract8").selection("input").init(3);
    model.component("comp2").geom("geom2").feature("extract8").selection("input").set("ext3(1)", 1);
    model.component("comp2").geom("geom2").feature("extract8").selection("input").set("ext4(1)", 1);
    model.component("comp2").geom("geom2").feature("extract8").selection("input").set("ext5(1)", 1, 3);
    model.component("comp2").geom("geom2").create("uni3", "Union");
    model.component("comp2").geom("geom2").feature("uni3").set("intbnd", false);
    model.component("comp2").geom("geom2").feature("uni3").selection("input").set("extract8");
    model.component("comp2").geom("geom2").create("fil5", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil5").label("Fillet 5 - Cut for Window");
    model.component("comp2").geom("geom2").feature("fil5").set("radius", "1.5[cm]");
    model.component("comp2").geom("geom2").feature("fil5").set("yshaped", true);
    model.component("comp2").geom("geom2").feature("fil5").selection("edge")
         .set("uni3(1)", 21, 22, 23, 27, 30, 36, 37);
    model.component("comp2").geom("geom2").create("fil6", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil6").label("Fillet 6 - Cut for Window");
    model.component("comp2").geom("geom2").feature("fil6").set("radius", "1.5[cm]");
    model.component("comp2").geom("geom2").feature("fil6").selection("edge").set("fil5(1)", 15, 20, 50);
    model.component("comp2").geom("geom2").create("wp9", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp9").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp9").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp9").selection("face").set("fil6(1)", 8);
    model.component("comp2").geom("geom2").feature("wp9").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp9").geom().feature("cro1").set("intersect", "selected");
    model.component("comp2").geom("geom2").feature("wp9").geom().feature("cro1").selection("input")
         .set("dif1", "pare2");
    model.component("comp2").geom("geom2").feature("wp9").geom().create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("wp9").geom().feature("uni1").selection("input").set("cro1");
    model.component("comp2").geom("geom2").feature("wp9").geom().create("extract1", "Extract");
    model.component("comp2").geom("geom2").feature("wp9").geom().feature("extract1").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("wp9").geom().feature("extract1").selection("input")
         .set("uni1(1)", 9, 31);
    model.component("comp2").geom("geom2").feature("wp9").geom().create("thi1", "Thicken2D");
    model.component("comp2").geom("geom2").feature("wp9").geom().feature("thi1").set("totalthick", "1[mm]");
    model.component("comp2").geom("geom2").feature("wp9").geom().feature("thi1").selection("input").set("extract1");
    model.component("comp2").geom("geom2").create("par3", "Partition");
    model.component("comp2").geom("geom2").feature("par3").selection("input").set("pare2");
    model.component("comp2").geom("geom2").feature("par3").selection("tool").set("wp9");
    model.component("comp2").geom("geom2").create("wp10", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp10").label("Work Plane - Cutouts");
    model.component("comp2").geom("geom2").feature("wp10").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp10").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp10").selection("face").set("dif1(1)", 63);
    model.component("comp2").geom("geom2").feature("wp10").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp10").geom().constrDimBuild("uptotarget");
    model.component("comp2").geom("geom2").feature("wp10").geom().create("proj1", "Projection");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("proj1").set("project", "bnd");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("proj1").selection("input")
         .set("dif1(1)", 63);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp10").geom().create("off1", "Offset");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("off1").set("distance", "25[mm]");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("off1").selection("input").set("proj1");
    model.component("comp2").geom("geom2").feature("wp10").geom().create("off2", "Offset");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("off2").set("distance", "35[mm]");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("off2").selection("input").set("proj1");
    model.component("comp2").geom("geom2").feature("wp10").geom().create("off3", "Offset");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("off3").set("distance", "50[mm]");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("off3").selection("input").set("proj1");
    model.component("comp2").geom("geom2").feature("wp10").geom().create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c1").set("r", 0.1);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c1")
         .set("pos", new double[]{-0.3979695402661615, -0.06654038155981698});
    model.component("comp2").geom("geom2").feature("wp10").geom().create("rad1", "Radius");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("rad1").set("radius", 0.1);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("rad1").set("createpar", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("rad1").set("parname", "coutout.rad1");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("rad1").selection("edge").set("c1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi1", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi1").selection("entity1")
         .set("off2(1)", 3);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi1").selection("entity2")
         .set("c1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi2", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi2")
         .set("helppoint1", new double[]{-0.3979695402661615, -0.16654038155981699});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi2").selection("entity1")
         .set("off3(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi2").selection("entity2")
         .set("c1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("c2", "Circle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c2").set("rconstr", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c2").set("r", "coutout.rad1/5");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c2")
         .set("pos", new double[]{-0.47796954026614014, 0.1451201015597137});
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi3", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi3")
         .set("helppoint1", new double[]{-0.47796954026616134, 0.16512010155412832});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi3").selection("entity1")
         .set("off3(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi3").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi3").selection("entity2")
         .set("c2(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi4", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi4").selection("entity1")
         .set("off2(1)", 3);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi4").selection("entity2")
         .set("c2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("c3", "Circle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c3").set("rconstr", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c3").set("r", "coutout.rad1/5");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c3")
         .set("pos", new double[]{-0.15796954026614005, 0.13931371377675433});
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi5", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi5")
         .set("helppoint1", new double[]{-0.1579695402661615, 0.15931371377117065});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi5").selection("entity1")
         .set("off1(1)", 6);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi5").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi5").selection("entity2")
         .set("c3(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("xdist1", "XDistance");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist1").set("distance", 0.24);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist1").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist1").selection("entity1")
         .set("c1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist1").selection("entity2")
         .set("c3(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("c4", "Circle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c4").set("rconstr", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c4").set("r", "coutout.rad1/5");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c4")
         .set("pos", new double[]{-0.13796954026614006, -0.16068628622324566});
    model.component("comp2").geom("geom2").feature("wp10").geom().create("ydist1", "YDistance");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ydist1").set("distance", 0.3);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ydist1").selection("entity1")
         .set("c4(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ydist1").selection("entity2")
         .set("c3(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("xdist2", "XDistance");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist2").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist2").selection("entity1")
         .set("c3(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist2").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist2").selection("entity2")
         .set("c4(1)", 1);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("c5", "Circle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c5").set("rconstr", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c5").set("r", "coutout.rad1/5");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c5")
         .set("pos", new double[]{0.08203045973385992, 0.12867863551041053});
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi6", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi6")
         .set("helppoint1", new double[]{0.08203045973383848, 0.1486786355048237});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi6").selection("entity1")
         .set("off1(1)", 8);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi6").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi6").selection("entity2")
         .set("c5(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("xdist3", "XDistance");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist3").set("distance", 0.24);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist3").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist3").selection("entity1")
         .set("c3(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist3").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist3").selection("entity2")
         .set("c5(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("c6", "Circle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c6").set("rconstr", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c6").set("r", "coutout.rad1/5");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c6")
         .set("pos", new double[]{0.4569747656037082, 0.13284734127801054});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c6").set("rotconstr", false);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("c6").set("rot", 5.732164264638082);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("tanc1", "TangentConstraint");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc1").set("point1", "vertex");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc1")
         .set("helppoint1", new double[]{0.45497719898937283, 0.15274733442874008});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc1")
         .set("helppoint2", new double[]{0.45497719898937283, 0.15274733442874008});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc1").selection("edge1")
         .set("c6(1)", 3);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc1").selection("vertex1")
         .set("c6(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc1").selection("edge2")
         .set("off2(1)", 7);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("tanc2", "TangentConstraint");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc2")
         .set("helppoint1", new double[]{0.4793726600387949, 0.12332064775272894});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc2")
         .set("helppoint2", new double[]{0.4747859095135943, 0.12374991566833335});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc2").selection("edge1")
         .set("off2(1)", 8);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("tanc2").selection("edge2")
         .set("c6(1)", 2);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("pol1").set("source", "table");

    return model;
  }

  public static Model run18(Model model) {
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("pol1")
         .set("table", new double[][]{{-0.06739849973549017, 0.038144117922283206}, {0.42538305285093464, 0.07845170257705762}, {0.2894877979130371, -0.15553716806835505}, {-0.058153723025258695, -0.1596399413534278}});
    model.component("comp2").geom("geom2").feature("wp10").geom().create("ang1", "Angle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang1").set("reverse2", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang1").set("angle", "92[deg]");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang1").selection("edge1")
         .set("pol1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang1").selection("edge2")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("ang2", "Angle");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang2").set("reverse2", true);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang2").set("angle", "92[deg]");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang2").selection("edge1")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("ang2").selection("edge2")
         .set("pol1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("telen1", "TotalEdgeLength");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("telen1").set("length", 0.198);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("telen1").selection("edge")
         .set("pol1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi7", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi7").selection("entity1")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi7").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi7").selection("entity2")
         .set("off3(1)", 3);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("fil1", "Fillet");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("fil1").set("radius", "4[cm]");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("fil1").selection("point")
         .set("pol1(1)", 1, 2, 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi8", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi8").selection("entity1")
         .set("fil1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi8").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi8").selection("entity2")
         .set("c6(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("coi9", "Coincident");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi9")
         .set("helppoint1", new double[]{-0.01952886179322079, -0.15918410095676075});
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi9").selection("entity1")
         .set("off3(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi9").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("coi9").selection("entity2")
         .set("fil1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("xdist4", "XDistance");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist4").set("distance", 0.126);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist4").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist4").selection("entity1")
         .set("fil1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist4").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("xdist4").selection("entity2")
         .set("off3(1)", 3);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("fil2", "Fillet");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("fil2").set("radiusconstr", false);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("fil2").set("radius", 0.19203232153146318);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("fil2").selection("point")
         .set("fil1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("telen2", "TotalEdgeLength");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("telen2").set("length", 0.085);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("telen2").selection("edge")
         .set("fil2(1)", 4);
    model.component("comp2").geom("geom2").feature("wp10").geom().create("telen3", "TotalEdgeLength");
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("telen3").set("length", 0.2);
    model.component("comp2").geom("geom2").feature("wp10").geom().feature("telen3").selection("edge")
         .set("fil2(1)", 3);
    model.component("comp2").geom("geom2").create("wp11", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp11").label("Work Plane - Water Outlet");
    model.component("comp2").geom("geom2").feature("wp11").set("quickplane", "xz");
    model.component("comp2").geom("geom2").feature("wp11").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp11").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("proj1", "Projection");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("proj1").set("project", "bnd");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("proj1").selection("input")
         .set("dif1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp11").geom().create("off1", "Offset");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("off1").set("distance", "10[mm]");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("off1").set("reverse", true);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("off1").selection("input").init(1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("off1").selection("input")
         .set("proj1.dif1", 11, 12, 13, 14, 15, 17);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("dist1", "Distance");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1").set("distanceconstr", false);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1").set("distance", 0.01);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1").set("createpar", true);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1")
         .set("parname", "geom2.wp11.dist1");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1")
         .set("helppoint1", new double[]{1.5710320951201866, 0.16554614562528078});
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1")
         .set("helppoint2", new double[]{1.5707110191306288, 0.17554098980660396});
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1").selection("entity1")
         .set("proj1.dif1", 14);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist1").selection("entity2")
         .set("off1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("ls1", "LineSegment");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ls1").selection("vertex1")
         .set("off1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ls1").selection("vertex2")
         .set("off1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ls1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp11").geom().create("ls2", "LineSegment");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ls2").selection("vertex1")
         .set("off1(1)", 7);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ls2").selection("vertex2")
         .set("off1(1)", 6);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ls2").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp11").geom().create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("pol1").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("pol1")
         .set("table", new double[][]{{1.4347444234367028, 0.22452898434060664}, {1.533770046520199, 0.22452898434060656}, {1.5847444234367027, 0.17598592149404502}, {1.5847444234367027, 0.0745289843406067}, {1.4347444234367028, 0.07452898434060669}});
    model.component("comp2").geom("geom2").feature("wp11").geom().create("para1", "Parallel");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("para1").selection("edge")
         .set("ls1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("para1").selection("edge")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("coi1", "Coincident");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi1")
         .set("helppoint2", new double[]{1.533770046520199, 0.22452898434060656});
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi1").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi1").selection("entity1")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi1").selection("entity2")
         .set("off1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("coi2", "Coincident");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi2")
         .set("helppoint1", new double[]{1.584744423436703, 0.17598592149404504});
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi2").selection("entity1")
         .set("off1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi2").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi2").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("dist2", "Distance");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist2")
         .set("distance", "geom2.wp11.dist1");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist2").selection("entity1")
         .set("ls1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist2").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("pol2", "Polygon");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("pol2").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("pol2")
         .set("table", new double[][]{{2.5859527691869157, 0.07452898434060683}, {2.585952769186916, 0.191912516833531}, {2.6451802905163717, 0.22855573247039834}, {2.739979517316707, 0.22855573247039834}, {2.739979517316707, 0.07452898434060684}});
    model.component("comp2").geom("geom2").feature("wp11").geom().create("eqdist1", "EqualDistance");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist1").selection("entity1")
         .set("ls1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist1").selection("entity2")
         .set("pol1(1)", 2);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist1").selection("entity3")
         .set("ls2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist1").selection("entity4")
         .set("pol2(1)", 2);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("coi3", "Coincident");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi3")
         .set("helppoint2", new double[]{2.645180290516372, 0.2285557324703981});
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi3").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi3").selection("entity1")
         .set("pol2(1)", 3);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi3").selection("entity2")
         .set("off1(1)", 7);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("coi4", "Coincident");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi4")
         .set("helppoint1", new double[]{2.5859527691869166, 0.19191251683353094});
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi4").selection("entity1")
         .set("off1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi4").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi4").selection("entity2")
         .set("pol2(1)", 2);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("hor1", "Horizontal");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 1, 4);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("hor1").selection("edge")
         .set("pol2(1)", 3, 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("ver1", "Vertical");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ver1").selection("edge")
         .set("pol1(1)", 3, 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("ver1").selection("edge")
         .set("pol2(1)", 1, 4);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("eqdist2", "EqualDistance");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist2").selection("entity1")
         .set("pol1(1)", 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist2").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist2").selection("entity3")
         .set("pol1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist2").selection("entity4")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("eqdist3", "EqualDistance");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist3").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist3").selection("entity2")
         .set("pol2(1)", 4);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist3").selection("entity3")
         .set("pol2(1)", 5);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("eqdist3").selection("entity4")
         .set("pol2(1)", 3);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("coi5", "Coincident");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi5").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi5").selection("entity1")
         .set("pol2(1)", 1);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("coi5").selection("entity2")
         .set("pol1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp11").geom().create("dist3", "Distance");
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist3").set("distance", 0.15);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist3").selection("entity1")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp11").geom().feature("dist3").selection("entity2")
         .set("pol1(1)", 5);
    model.component("comp2").geom("geom2").create("ext6", "Extrude");
    model.component("comp2").geom("geom2").feature("ext6").setIndex("distance", "1.5", 0);
    model.component("comp2").geom("geom2").feature("ext6").selection("input").set("wp11");
    model.component("comp2").geom("geom2").create("dif2", "Difference");
    model.component("comp2").geom("geom2").feature("dif2").label("Difference - Cutout");
    model.component("comp2").geom("geom2").feature("dif2").selection("input").set("dif1");
    model.component("comp2").geom("geom2").feature("dif2").selection("input2").set("ext6", "fil6", "wp10");
    model.component("comp2").geom("geom2").create("wp12", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp12").label("Work Plane - Beam Position");
    model.component("comp2").geom("geom2").feature("wp12").set("quickz", 0.45);
    model.component("comp2").geom("geom2").feature("wp12").set("quickorigin", "vertexproj");
    model.component("comp2").geom("geom2").feature("wp12").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp12").selection("originvertex").set("dif2(1)", 94);
    model.component("comp2").geom("geom2").create("wp13", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp13").label("Work Plane - Beam 1");
    model.component("comp2").geom("geom2").feature("wp13").set("planetype", "transformed");
    model.component("comp2").geom("geom2").feature("wp13").set("workplane", "wp12");
    model.component("comp2").geom("geom2").feature("wp13").set("transdispl", new String[]{"0", "0", "2[cm]"});
    model.component("comp2").geom("geom2").feature("wp13").set("transaxis", new int[]{0, 1, 0});
    model.component("comp2").geom("geom2").feature("wp13").set("transrot", 10);
    model.component("comp2").geom("geom2").feature("wp13").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp13").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp13").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp13").geom().feature("cro1").set("intersect", "selected");
    model.component("comp2").geom("geom2").feature("wp13").geom().feature("cro1").selection("input").set("par3");
    model.component("comp2").geom("geom2").feature("wp13").geom().create("thi1", "Thicken2D");
    model.component("comp2").geom("geom2").feature("wp13").geom().feature("thi1").set("offset", "asymmetric");
    model.component("comp2").geom("geom2").feature("wp13").geom().feature("thi1").set("downthick", "3[cm]");
    model.component("comp2").geom("geom2").feature("wp13").geom().feature("thi1").selection("input").set("cro1");
    model.component("comp2").geom("geom2").create("wp14", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp14").label("Work Plane - Beam 2");
    model.component("comp2").geom("geom2").feature("wp14").set("planetype", "transformed");
    model.component("comp2").geom("geom2").feature("wp14").set("workplane", "wp12");
    model.component("comp2").geom("geom2").feature("wp14").set("transdispl", new String[]{"0", "0", "-2[cm]"});
    model.component("comp2").geom("geom2").feature("wp14").set("transaxis", new int[]{0, 1, 0});
    model.component("comp2").geom("geom2").feature("wp14").set("transrot", 10);
    model.component("comp2").geom("geom2").feature("wp14").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp14").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp14").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp14").geom().feature("cro1").set("intersect", "selected");
    model.component("comp2").geom("geom2").feature("wp14").geom().feature("cro1").selection("input").set("par3");
    model.component("comp2").geom("geom2").feature("wp14").geom().create("thi1", "Thicken2D");
    model.component("comp2").geom("geom2").feature("wp14").geom().feature("thi1").set("offset", "asymmetric");
    model.component("comp2").geom("geom2").feature("wp14").geom().feature("thi1").set("downthick", "3[cm]");
    model.component("comp2").geom("geom2").feature("wp14").geom().feature("thi1").selection("input").set("cro1");
    model.component("comp2").geom("geom2").create("loft4", "Loft");
    model.component("comp2").geom("geom2").feature("loft4").label("Loft - Beam");
    model.component("comp2").geom("geom2").feature("loft4").set("type", "surface");
    model.component("comp2").geom("geom2").feature("loft4").selection("profile").set("wp13", "wp14");
    model.component("comp2").geom("geom2").feature("loft4").selection("guide").set();
    model.component("comp2").geom("geom2").create("off1", "OffsetFaces");
    model.component("comp2").geom("geom2").feature("off1").set("keep", true);
    model.component("comp2").geom("geom2").feature("off1").set("distance", "1[cm]");
    model.component("comp2").geom("geom2").feature("off1").selection("face").set("loft4(1)", 1, 2, 3, 4, 5, 6);
    model.component("comp2").geom("geom2").create("extract9", "Extract");
    model.component("comp2").geom("geom2").feature("extract9").label("Extract 9 - Beam");
    model.component("comp2").geom("geom2").feature("extract9").selection("input").set("par3(1)", 1);
    model.component("comp2").geom("geom2").create("par4", "Partition");
    model.component("comp2").geom("geom2").feature("par4").label("Partition Objects - Beam 1");
    model.component("comp2").geom("geom2").feature("par4").selection("input").set("extract9");
    model.component("comp2").geom("geom2").feature("par4").selection("tool").set("off1");
    model.component("comp2").geom("geom2").create("uni4", "Union");
    model.component("comp2").geom("geom2").feature("uni4").label("Union - Beam");
    model.component("comp2").geom("geom2").feature("uni4").set("intbnd", false);
    model.component("comp2").geom("geom2").feature("uni4").selection("input").set("loft4", "par4");
    model.component("comp2").geom("geom2").feature("uni4").set("repairtoltype", "relative");
    model.component("comp2").geom("geom2").feature("uni4").set("repairtol", 1.0E-5);
    model.component("comp2").geom("geom2").create("extract10", "Extract");
    model.component("comp2").geom("geom2").feature("extract10").label("Extract 10 - Beam");
    model.component("comp2").geom("geom2").feature("extract10").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("extract10").set("selresult", true);
    model.component("comp2").geom("geom2").feature("extract10").set("selresultshow", false);
    model.component("comp2").geom("geom2").feature("extract10").selection("input").set("uni4(1)", 2, 6, 7, 8, 10);
    model.component("comp2").geom("geom2").create("adjsel1", "AdjacentSelection");
    model.component("comp2").geom("geom2").feature("adjsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("adjsel1").set("outputdim", 1);
    model.component("comp2").geom("geom2").feature("adjsel1").set("input", new String[]{"extract10"});
    model.component("comp2").geom("geom2").feature("adjsel1").set("exterior", false);
    model.component("comp2").geom("geom2").feature("adjsel1").set("interior", true);
    model.component("comp2").geom("geom2").create("fil7", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil7").label("Fillet - Beam 1");
    model.component("comp2").geom("geom2").feature("fil7").set("radius", "2[mm]");
    model.component("comp2").geom("geom2").feature("fil7").set("selresult", true);
    model.component("comp2").geom("geom2").feature("fil7").set("selresultshow", false);
    model.component("comp2").geom("geom2").feature("fil7").selection("edge").named("adjsel1");
    model.component("comp2").geom("geom2").create("par5", "Partition");
    model.component("comp2").geom("geom2").feature("par5").label("Partition Objects - Beam 2");
    model.component("comp2").geom("geom2").feature("par5").set("keeptool", true);
    model.component("comp2").geom("geom2").feature("par5").selection("input").set("fil7");
    model.component("comp2").geom("geom2").feature("par5").selection("tool").set("dif2");
    model.component("comp2").geom("geom2").create("ballsel1", "BallSelection");
    model.component("comp2").geom("geom2").feature("ballsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("ballsel1").set("inputent", "selections");
    model.component("comp2").geom("geom2").feature("ballsel1").set("input", new String[]{"fil7"});
    model.component("comp2").geom("geom2").feature("ballsel1").set("posx", 2.7);
    model.component("comp2").geom("geom2").feature("ballsel1").set("posy", -0.8);
    model.component("comp2").geom("geom2").feature("ballsel1").set("posz", 0.3);
    model.component("comp2").geom("geom2").feature("ballsel1").set("r", 0.1);
    model.component("comp2").geom("geom2").feature("ballsel1").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("ballsel1").set("selshow", false);
    model.component("comp2").geom("geom2").create("ballsel2", "BallSelection");
    model.component("comp2").geom("geom2").feature("ballsel2").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("ballsel2").set("inputent", "selections");
    model.component("comp2").geom("geom2").feature("ballsel2").set("input", new String[]{"fil7"});
    model.component("comp2").geom("geom2").feature("ballsel2").set("posx", 1.5);
    model.component("comp2").geom("geom2").feature("ballsel2").set("posy", -0.9);
    model.component("comp2").geom("geom2").feature("ballsel2").set("posz", 0.5);
    model.component("comp2").geom("geom2").feature("ballsel2").set("r", 0.1);
    model.component("comp2").geom("geom2").feature("ballsel2").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("ballsel2").set("selshow", false);
    model.component("comp2").geom("geom2").create("difsel1", "DifferenceSelection");
    model.component("comp2").geom("geom2").feature("difsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("difsel1").set("add", new String[]{"fil7"});
    model.component("comp2").geom("geom2").feature("difsel1").set("subtract", new String[]{"ballsel1", "ballsel2"});
    model.component("comp2").geom("geom2").feature("difsel1").set("selshow", false);
    model.component("comp2").geom("geom2").create("extract11", "Extract");
    model.component("comp2").geom("geom2").feature("extract11").label("Beam");
    model.component("comp2").geom("geom2").feature("extract11").set("inputhandling", "remove");
    model.component("comp2").geom("geom2").feature("extract11").set("selresult", true);
    model.component("comp2").geom("geom2").feature("extract11").set("selresultshow", "bnd");
    model.component("comp2").geom("geom2").feature("extract11").selection("input").named("difsel1");
    model.component("comp2").geom("geom2").create("wp15", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp15").label("Work Plane - Door Lock");
    model.component("comp2").geom("geom2").feature("wp15").set("planetype", "faceparallel");
    model.component("comp2").geom("geom2").feature("wp15").selection("face").set("dif2(1)", 48);
    model.component("comp2").geom("geom2").feature("wp15").geom().useConstrDim(true);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("cro1").set("intersect", "selected");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("cro1").selection("input").set("dif2");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("cro1").setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp15").geom().create("proj1", "Projection");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("proj1").set("project", "bnd");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("proj1").set("projectiontype", "all");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("proj1").selection("input")
         .set("dif2(1)", 113);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("proj1")
         .setAttribute("construction", "on");
    model.component("comp2").geom("geom2").feature("wp15").geom().create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("pol1").set("source", "table");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("pol1")
         .set("table", new double[][]{{0.46837598881597364, 0}, {0.44283741125426784, -0.05}, {0.5202033001494646, -0.05}, {0.5457418777111704, 0}});
    model.component("comp2").geom("geom2").feature("wp15").geom().create("hor1", "Horizontal");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("hor1").selection("edge")
         .set("pol1(1)", 2, 4);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("para1", "Parallel");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("para1").selection("edge")
         .set("pol1(1)", 1, 3);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("para1").selection("edge")
         .set("cro1.dif2", 29);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("ydist1", "YDistance");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("ydist1").set("distance", 0.05);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("ydist1").selection("entity1").init(0);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("ydist1").selection("entity1")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("ydist1").selection("entity2").init(0);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("ydist1").selection("entity2")
         .set("pol1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("pos1", "Position");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("pos1")
         .set("posconstr", new String[]{"off", "on"});
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("pos1")
         .set("pos", new double[]{0.5457418777111704, 0});
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("pos1").selection("vertex")
         .set("pol1(1)", 4);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("eqdist1", "EqualDistance");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist1")
         .set("helppoint4", new double[]{0.5230864603301119, 0.08148472094447055});
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist1").selection("entity1")
         .set("cro1.dif2", 29);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist1").selection("entity2")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist1").selection("entity3")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist1").selection("entity4")
         .set("cro1.dif2", 30);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("eqdist2", "EqualDistance");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2")
         .set("helppoint1", new double[]{0.5578681215758541, -0.001887635144202488});

    return model;
  }

  public static Model run19(Model model) {
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2")
         .set("helppoint2", new double[]{0.5667228079039284, -1.9308513833594975E-5});
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2")
         .set("helppoint3", new double[]{0.5260211012206735, 0.07553515907688628});
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2")
         .set("helppoint4", new double[]{0.5265586081656979, 0.07553426558088021});
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2").selection("entity1")
         .set("proj1.dif2", 3);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2").selection("entity2")
         .set("pol1(1)", 3);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2").selection("entity3")
         .set("cro1.dif2", 29);
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("eqdist2").selection("entity4")
         .set("pol1(1)", 1);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("fil1", "Fillet");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("fil1").set("radius", "1[cm]");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("fil1").selection("point")
         .set("pol1(1)", 1, 2, 3, 4);
    model.component("comp2").geom("geom2").feature("wp15").geom().create("off1", "Offset");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("off1").set("distance", "1.3[cm]");
    model.component("comp2").geom("geom2").feature("wp15").geom().feature("off1").selection("input").set("fil1");
    model.component("comp2").geom("geom2").create("ext7", "Extrude");
    model.component("comp2").geom("geom2").feature("ext7").label("Extrude 7 - Door Lock");
    model.component("comp2").geom("geom2").feature("ext7").set("distance", new String[]{"-5[cm]", "5[cm]"});
    model.component("comp2").geom("geom2").feature("ext7").set("includeinput", false);
    model.component("comp2").geom("geom2").feature("ext7").set("scale", new double[][]{{1, 1}, {1, 1}});
    model.component("comp2").geom("geom2").feature("ext7").set("displ", new double[][]{{0, 0}, {0, 0}});
    model.component("comp2").geom("geom2").feature("ext7").set("twist", new int[]{0, 0});
    model.component("comp2").geom("geom2").feature("ext7").selection("input").set("wp15.fil1");
    model.component("comp2").geom("geom2").create("ext8", "Extrude");
    model.component("comp2").geom("geom2").feature("ext8").label("Extrude 8 - Door Lock");
    model.component("comp2").geom("geom2").feature("ext8").setIndex("distance", "6[cm]", 0);
    model.component("comp2").geom("geom2").feature("ext8").selection("input").set("wp15.off1");
    model.component("comp2").geom("geom2").create("fil8", "Fillet3D");
    model.component("comp2").geom("geom2").feature("fil8").label("Fillet - Door Lock");
    model.component("comp2").geom("geom2").feature("fil8").set("radius", "0.5[cm]");
    model.component("comp2").geom("geom2").feature("fil8").selection("edge").set("ext7(1)", 10);
    model.component("comp2").geom("geom2").feature("fil8").selection("edge").set("ext8(1)", 23);
    model.component("comp2").geom("geom2").create("dif3", "Difference");
    model.component("comp2").geom("geom2").feature("dif3").label("Difference - Door Lock");
    model.component("comp2").geom("geom2").feature("dif3").selection("input").set("dif2");
    model.component("comp2").geom("geom2").feature("dif3").selection("input2").set("fil8(1)");
    model.component("comp2").geom("geom2").create("par6", "Partition");
    model.component("comp2").geom("geom2").feature("par6").label("Partition Objects - Door Lock");
    model.component("comp2").geom("geom2").feature("par6").selection("input").set("dif3");
    model.component("comp2").geom("geom2").feature("par6").selection("tool").set("fil8(2)");
    model.component("comp2").geom("geom2").create("wp16", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp16").label("Work Plane - Cut for Cables");
    model.component("comp2").geom("geom2").feature("wp16").set("quickplane", "yz");
    model.component("comp2").geom("geom2").feature("wp16").set("quickx", 1.65);
    model.component("comp2").geom("geom2").feature("wp16").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp16").geom().create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("r1")
         .set("size", new double[]{0.02, 0.06});
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("r1").set("base", "center");
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("r1")
         .set("pos", new double[]{-0.84, 0.52});
    model.component("comp2").geom("geom2").feature("wp16").geom().create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("c1").set("r", 0.02);
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("c1")
         .set("pos", new double[]{-0.84, 0.52});
    model.component("comp2").geom("geom2").feature("wp16").geom().create("sca1", "Scale");
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("sca1").set("keep", true);
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("sca1").setIndex("factor", "1.5", 0);
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("sca1")
         .set("pos", new double[]{-0.84, 0.52});
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("sca1").selection("input").set("c1", "r1");
    model.component("comp2").geom("geom2").feature("wp16").geom().create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("uni1").set("intbnd", false);
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("uni1").selection("input").set("sca1");
    model.component("comp2").geom("geom2").feature("wp16").geom().create("uni2", "Union");
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("uni2").set("intbnd", false);
    model.component("comp2").geom("geom2").feature("wp16").geom().feature("uni2").selection("input").set("c1", "r1");
    model.component("comp2").geom("geom2").create("ext9", "Extrude");
    model.component("comp2").geom("geom2").feature("ext9").label("Extrude - Cut for Cables");
    model.component("comp2").geom("geom2").feature("ext9").setIndex("distance", "10[cm]", 0);
    model.component("comp2").geom("geom2").feature("ext9").set("reverse", true);
    model.component("comp2").geom("geom2").feature("ext9").selection("input").set("wp16");
    model.component("comp2").geom("geom2").create("par7", "Partition");
    model.component("comp2").geom("geom2").feature("par7").label("Partition Objects - Cut for Cables");
    model.component("comp2").geom("geom2").feature("par7").selection("input").set("par6");
    model.component("comp2").geom("geom2").feature("par7").selection("tool").set("ext9");
    model.component("comp2").geom("geom2").create("del2", "Delete");
    model.component("comp2").geom("geom2").feature("del2").label("Delete Entities - Cut for Cables");
    model.component("comp2").geom("geom2").feature("del2").selection("input").set("extract1(1)", 21, 27);
    model.component("comp2").geom("geom2").feature("del2").selection("input").set("par7(1)", 42);
    model.component("comp2").geom("geom2").run("del2");
    model.component("comp2").geom("geom2").nodeGroup().create("grp1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").label("Construction Geometry and Cleanup");
    model.component("comp2").geom("geom2").nodeGroup("grp1").placeAfter("imp1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("del1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("mir1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("par1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("extract1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("extract2");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("extract3");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("extract4");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("extract5");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("mov1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("wp1");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("wp2");
    model.component("comp2").geom("geom2").nodeGroup("grp1").add("ext1");
    model.component("comp2").geom("geom2").nodeGroup().create("grp2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").label("Inner Door Panel");
    model.component("comp2").geom("geom2").nodeGroup("grp2").placeAfter("imp1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("wp3");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("ext2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("wp4");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("sel1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("par2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("parf1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("parf2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("parf3");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("parf4");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("extract6");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("uni1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("extract7");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("wp5");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("pare1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("loft1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("wp6");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("pare2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("loft2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("pare3");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("pare4");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("sel2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("loft3");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("uni2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("sel3");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("sel4");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("sel5");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("fil1");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("fil2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("fil3");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("fil4");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("thi2");
    model.component("comp2").geom("geom2").nodeGroup("grp2").add("dif1");
    model.component("comp2").geom("geom2").nodeGroup().create("grp3");
    model.component("comp2").geom("geom2").nodeGroup("grp3").label("Cutout for Window");
    model.component("comp2").geom("geom2").nodeGroup("grp3").placeAfter("imp1");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("wp7");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("ext3");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("ext4");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("wp8");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("ext5");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("extract8");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("uni3");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("fil5");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("fil6");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("wp9");
    model.component("comp2").geom("geom2").nodeGroup("grp3").add("par3");
    model.component("comp2").geom("geom2").nodeGroup().create("grp4");
    model.component("comp2").geom("geom2").nodeGroup("grp4").label("Cutout for Water Outlet and Door Structure");
    model.component("comp2").geom("geom2").nodeGroup("grp4").placeAfter("imp1");
    model.component("comp2").geom("geom2").nodeGroup("grp4").add("wp10");
    model.component("comp2").geom("geom2").nodeGroup("grp4").add("wp11");
    model.component("comp2").geom("geom2").nodeGroup("grp4").add("ext6");
    model.component("comp2").geom("geom2").nodeGroup("grp4").add("dif2");
    model.component("comp2").geom("geom2").nodeGroup().create("grp5");
    model.component("comp2").geom("geom2").nodeGroup("grp5").label("Interior Door Beam");
    model.component("comp2").geom("geom2").nodeGroup("grp5").placeAfter("imp1");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("wp12");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("wp13");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("wp14");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("loft4");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("off1");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("extract9");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("par4");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("uni4");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("extract10");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("adjsel1");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("fil7");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("par5");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("ballsel1");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("ballsel2");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("difsel1");
    model.component("comp2").geom("geom2").nodeGroup("grp5").add("extract11");
    model.component("comp2").geom("geom2").nodeGroup().create("grp6");
    model.component("comp2").geom("geom2").nodeGroup("grp6").label("Cutout for Door Lock and Cables");
    model.component("comp2").geom("geom2").nodeGroup("grp6").placeAfter("imp1");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("wp15");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("ext7");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("ext8");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("fil8");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("dif3");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("par6");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("wp16");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("ext9");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("par7");
    model.component("comp2").geom("geom2").nodeGroup("grp6").add("del2");
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").selection().create("csel1", "CumulativeSelection");
    model.component("comp2").geom("geom2").selection("csel1").label("Fixed Constraint Selection");
    model.component("comp2").geom("geom2").create("ballsel3", "BallSelection");
    model.component("comp2").geom("geom2").feature("ballsel3").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("ballsel3").set("posx", 2.7);
    model.component("comp2").geom("geom2").feature("ballsel3").set("posy", -0.75);
    model.component("comp2").geom("geom2").feature("ballsel3").set("posz", 0.5);
    model.component("comp2").geom("geom2").feature("ballsel3").set("r", 0.08);
    model.component("comp2").geom("geom2").feature("ballsel3").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("ballsel3").set("selshow", false);
    model.component("comp2").geom("geom2").create("ballsel4", "BallSelection");
    model.component("comp2").geom("geom2").feature("ballsel4").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("ballsel4").set("posx", 1.6);
    model.component("comp2").geom("geom2").feature("ballsel4").set("posy", -0.82);
    model.component("comp2").geom("geom2").feature("ballsel4").set("posz", 0.5);
    model.component("comp2").geom("geom2").feature("ballsel4").set("r", 0.08);
    model.component("comp2").geom("geom2").feature("ballsel4").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("ballsel4").set("selshow", false);
    model.component("comp2").geom("geom2").create("difsel2", "DifferenceSelection");
    model.component("comp2").geom("geom2").feature("difsel2").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("difsel2").set("add", new String[]{"uni2"});
    model.component("comp2").geom("geom2").feature("difsel2").set("subtract", new String[]{"ballsel3", "ballsel4"});
    model.component("comp2").geom("geom2").feature("difsel2").set("selshow", false);
    model.component("comp2").geom("geom2").create("intsel1", "IntersectionSelection");
    model.component("comp2").geom("geom2").feature("intsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("intsel1").set("input", new String[]{"uni2", "ballsel3"});
    model.component("comp2").geom("geom2").feature("intsel1").set("selshow", false);
    model.component("comp2").geom("geom2").create("difsel3", "DifferenceSelection");
    model.component("comp2").geom("geom2").feature("difsel3").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("difsel3").set("add", new String[]{"fil4"});
    model.component("comp2").geom("geom2").feature("difsel3").set("subtract", new String[]{"uni2", "ballsel3"});
    model.component("comp2").geom("geom2").feature("difsel3").set("selshow", false);
    model.component("comp2").geom("geom2").create("ballsel5", "BallSelection");
    model.component("comp2").geom("geom2").feature("ballsel5").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("ballsel5").set("posx", 1.68);
    model.component("comp2").geom("geom2").feature("ballsel5").set("posy", -0.87);
    model.component("comp2").geom("geom2").feature("ballsel5").set("posz", 0.8);
    model.component("comp2").geom("geom2").feature("ballsel5").set("r", 0.05);
    model.component("comp2").geom("geom2").feature("ballsel5").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("ballsel5").set("selshow", false);
    model.component("comp2").geom("geom2").create("difsel4", "DifferenceSelection");
    model.component("comp2").geom("geom2").feature("difsel4").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("difsel4").label("External surfaces");
    model.component("comp2").geom("geom2").feature("difsel4")
         .set("add", new String[]{"extract1", "extract3", "extract4"});
    model.component("comp2").geom("geom2").feature("difsel4").set("subtract", new String[]{"ballsel5"});
    model.component("comp2").geom("geom2").create("ballsel6", "BallSelection");
    model.component("comp2").geom("geom2").feature("ballsel6").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("ballsel6").set("posx", 1.54);
    model.component("comp2").geom("geom2").feature("ballsel6").set("posy", -0.94);
    model.component("comp2").geom("geom2").feature("ballsel6").set("posz", 0.472);
    model.component("comp2").geom("geom2").feature("ballsel6").set("r", 0.033);
    model.component("comp2").geom("geom2").feature("ballsel6").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("ballsel6").set("selshow", false);
    model.component("comp2").geom("geom2").create("adjsel2", "AdjacentSelection");
    model.component("comp2").geom("geom2").feature("adjsel2").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("adjsel2").set("outputdim", 1);
    model.component("comp2").geom("geom2").feature("adjsel2").set("input", new String[]{"ballsel6"});
    model.component("comp2").geom("geom2").feature("adjsel2").set("exterior", false);
    model.component("comp2").geom("geom2").feature("adjsel2").set("interior", true);
    model.component("comp2").geom("geom2").feature("adjsel2").set("selshow", false);
    model.component("comp2").geom("geom2").feature("adjsel2").set("contributeto", "csel1");
    model.component("comp2").geom("geom2").create("ballsel7", "BallSelection");
    model.component("comp2").geom("geom2").feature("ballsel7").set("entitydim", 1);
    model.component("comp2").geom("geom2").feature("ballsel7").set("groupcontang", true);
    model.component("comp2").geom("geom2").feature("ballsel7").set("inputent", "selections");
    model.component("comp2").geom("geom2").feature("ballsel7").set("input", new String[]{"extract11"});
    model.component("comp2").geom("geom2").feature("ballsel7").set("posx", 1.54);
    model.component("comp2").geom("geom2").feature("ballsel7").set("posy", -0.94);
    model.component("comp2").geom("geom2").feature("ballsel7").set("posz", 0.473);
    model.component("comp2").geom("geom2").feature("ballsel7").set("r", 0.033);
    model.component("comp2").geom("geom2").feature("ballsel7").set("condition", "inside");
    model.component("comp2").geom("geom2").feature("ballsel7").set("selshow", false);
    model.component("comp2").geom("geom2").feature("ballsel7").set("contributeto", "csel1");
    model.component("comp2").geom("geom2").create("cylsel1", "CylinderSelection");
    model.component("comp2").geom("geom2").feature("cylsel1").set("entitydim", 1);
    model.component("comp2").geom("geom2").feature("cylsel1").set("r", 0.008);
    model.component("comp2").geom("geom2").feature("cylsel1").set("top", 0.07);
    model.component("comp2").geom("geom2").feature("cylsel1").set("bottom", 0);
    model.component("comp2").geom("geom2").feature("cylsel1").set("pos", new double[]{2.665, -0.7, 0.47});
    model.component("comp2").geom("geom2").feature("cylsel1").set("axis", new double[]{0.025, 0, 0.05});
    model.component("comp2").geom("geom2").feature("cylsel1").set("selshow", false);
    model.component("comp2").geom("geom2").feature("cylsel1").set("contributeto", "csel1");
    model.component("comp2").geom("geom2").create("intsel2", "IntersectionSelection");
    model.component("comp2").geom("geom2").feature("intsel2").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("intsel2").label("Spring Foundation Selection");
    model.component("comp2").geom("geom2").feature("intsel2").set("input", new String[]{"extract3", "fil4"});
    model.component("comp2").geom("geom2").create("sel6", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel6").selection("selection").init(2);
    model.component("comp2").geom("geom2").feature("sel6").selection("selection").set("fin(1)", 4, 6);
    model.component("comp2").geom("geom2").feature("sel6").set("selshow", false);
    model.component("comp2").geom("geom2").create("unisel1", "UnionSelection");
    model.component("comp2").geom("geom2").feature("unisel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("unisel1").label("Thickness and Offset Selection");
    model.component("comp2").geom("geom2").feature("unisel1").set("input", new String[]{"intsel2", "sel6"});
    model.component("comp2").geom("geom2").create("igv1", "IgnoreVertices");
    model.component("comp2").geom("geom2").feature("igv1").selection("input").named("imp1");
    model.component("comp2").geom("geom2").create("cmf1", "CompositeFaces");
    model.component("comp2").geom("geom2").feature("cmf1").selection("input").named("difsel2");
    model.component("comp2").geom("geom2").create("cmf2", "CompositeFaces");
    model.component("comp2").geom("geom2").feature("cmf2").selection("input").named("difsel3");
    model.component("comp2").geom("geom2").create("cmf3", "CompositeFaces");
    model.component("comp2").geom("geom2").feature("cmf3").selection("input").named("intsel1");
    model.component("comp2").geom("geom2").create("cle1", "CollapseEdges");
    model.component("comp2").geom("geom2").feature("cle1").selection("input").set("cmf3(1)", 145, 206);
    model.component("comp2").geom("geom2").create("clf1", "CollapseFaces");
    model.component("comp2").geom("geom2").feature("clf1").selection("input").set("cle1(1)", 54, 77);
    model.component("comp2").geom("geom2").create("ige1", "IgnoreEdges");
    model.component("comp2").geom("geom2").feature("ige1").selection("input").set("clf1(1)", 145);
    model.component("comp2").geom("geom2").create("rmd1", "RemoveDetails");
    model.component("comp2").geom("geom2").run("rmd1");
    model.component("comp2").geom("geom2").run();
    model.component("comp2").geom("geom2").nodeGroup().create("grp7");
    model.component("comp2").geom("geom2").nodeGroup("grp7").label("Selections");
    model.component("comp2").geom("geom2").nodeGroup("grp7").placeAfter("fin");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("ballsel3");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("ballsel4");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("difsel2");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("intsel1");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("difsel3");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("ballsel5");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("difsel4");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("ballsel6");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("adjsel2");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("ballsel7");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("cylsel1");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("intsel2");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("sel6");
    model.component("comp2").geom("geom2").nodeGroup("grp7").add("unisel1");
    model.component("comp2").geom("geom2").nodeGroup().create("grp8");
    model.component("comp2").geom("geom2").nodeGroup("grp8").label("Virtual Operations");
    model.component("comp2").geom("geom2").nodeGroup("grp8").placeAfter("fin");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("igv1");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("cmf1");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("cmf2");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("cmf3");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("cle1");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("clf1");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("ige1");
    model.component("comp2").geom("geom2").nodeGroup("grp8").add("rmd1");

    model.title(null);

    model.description("");

    model.label("sports_car_fsi_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    model = run6(model);
    model = run7(model);
    model = run8(model);
    model = run9(model);
    model = run10(model);
    model = run11(model);
    model = run12(model);
    model = run13(model);
    model = run14(model);
    model = run15(model);
    model = run16(model);
    model = run17(model);
    model = run18(model);
    run19(model);
  }

}
