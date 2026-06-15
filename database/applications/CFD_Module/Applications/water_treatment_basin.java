/*
 * water_treatment_basin.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class water_treatment_basin {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c_0", "1.0[mole/m^3]", "Inlet concentration");
    model.param().set("D_diff", "1.0e-9[m^2/s]", "Molecular diffusivity");
    model.param().set("de_b", "3.0[m]", "Basin water depth");
    model.param().set("di_w", "l_b/(n_lw+n_rw+1)", "Distance between walls");
    model.param().set("fr_bw", "50[cm]", "Fillet radius between the basin walls and the mixing walls");
    model.param().set("h_il", "0.75[m]", "Height of inlet channel for rectangular inlet profile");
    model.param().set("h_ol", "0.75[m]", "Height of outlet channel for rectangular outlet profile");
    model.param()
         .set("hp_il", "0.0[m]", "Inlet offset position from the center of the inlet wall along the height of the wall");
    model.param()
         .set("hp_ol", "0.0[m]", "Outlet offset position from the center of the outlet wall along the height of the wall");
    model.param().set("k_f", "0.5e-2[1/s]", "Reaction rate constant");
    model.param().set("l_b", "20[m]", "Basin length");
    model.param().set("l_il", "2.0[m]", "Length, inlet channel");
    model.param().set("l_ol", "1.5[m]", "Length, outlet channel");
    model.param().set("l_w", "2.0[m]", "Mixing wall length");
    model.param().set("n_lw", "3", "Number of walls, left from inlet");
    model.param().set("n_rw", "3", "Number of walls, right from inlet");
    model.param()
         .set("p_bo", "1", "Parameter for basin orientation. If 0, then the tortuous path runs in the x-y plane. If 1, then this path runs in the x-z plane.");
    model.param()
         .set("p_il", "0", "Parameter that is 1 for a circular inlet channel profile and 0 for a rectangular one");
    model.param()
         .set("p_ol", "0", "Parameter that is 1 for a circular outlet channel profile and 0 for a rectangular one");
    model.param()
         .set("p_rw", "-1", "Parameter that is -1 for starting with a wall to the right from the inlet and 1 for starting on the left hand side");
    model.param().set("t_half", "-log(0.5)/k_f", "Half life for c");
    model.param().set("th_w", "10[cm]", "Mixing wall thickness");
    model.param().set("u_0", "1.0[m/s]", "Inlet velocity");
    model.param().set("w_b", "10.0[m]", "Basin width");
    model.param().set("w_il", "2.5[m]", "Width or diameter of inlet channel");
    model.param().set("w_ol", "2.5[m]", "Width or diameter of outlet channel");
    model.param()
         .set("wp_il", "0[m]", "Inlet offset position from the center of the inlet wall along the width of the wall");
    model.param()
         .set("wp_ol", "0[m]", "Outlet offset position from the center of the outlet wall along the width of the wall");
    model.param().set("yc_p", "1*(p_bo==0)", "Plane coordinate");
    model.param().set("zc_p", "1*(p_bo==1)", "Plane coordinate");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "coordinates");
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "yc_p", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "zc_p", 2, 2);
    model.component("comp1").geom("geom1").feature("wp1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"l_b", "(w_b*(p_bo==0)+de_b*(p_bo==1))"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"th_w", "l_w"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"-l_b/2+di_w", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .setIndex("pos", "p_rw*((w_b*(p_bo==0)+de_b*(p_bo==1))/2-l_w/2)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"n_rw", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"2*di_w", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"th_w", "l_w"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"-l_b/2+2*di_w", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .setIndex("pos", "-p_rw*((w_b*(p_bo==0)+de_b*(p_bo==1))/2-l_w/2)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("fullsize", new String[]{"n_lw", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("displ", new String[]{"2*di_w", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").label("Walls");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("xmin", "-l_b/2+di_w/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("xmax", "l_b/2-di_w/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection("csel1").label("walls");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("selkeep", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("boxsel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").label("Fillet Vertex 1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("xmin", "-l_b/2-l_b/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("xmax", "l_b/2+l_b/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2")
         .set("ymin", "-(w_b*(p_bo==0)+de_b*(p_bo==1))/2-(w_b*(p_bo==0)+de_b*(p_bo==1))/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2")
         .set("ymax", "-(w_b*(p_bo==0)+de_b*(p_bo==1))/2+(w_b*(p_bo==0)+de_b*(p_bo==1))/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection("csel2").label("fillet_vertex");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("selkeep", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("boxsel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("if1").set("condition", "fr_bw > 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").named("csel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "fr_bw");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().createAfter("endif2", "EndIf", "boxsel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().createAfter("if2", "If", "boxsel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("if2").set("condition", "p_bo == 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("if2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").label("Fillet Vertex 2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("xmin", "-l_b/2-l_b/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("xmax", "l_b/2+l_b/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("ymin", "w_b/2-w_b/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("ymax", "w_b/2+w_b/100");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel3").set("selkeep", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("endif1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "de_b*(p_bo==0)+w_b*(p_bo==1)", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("basin_and_pipes");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "p_bo == 1");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "ext1");
    model.component("comp1").geom("geom1").feature().createAfter("if2", "If", "ext1");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "p_bo==1");
    model.component("comp1").geom("geom1").run("if2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "w_b/2");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "de_b/2");
    model.component("comp1").geom("geom1").run("endif2");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "l_b/2");
    model.component("comp1").geom("geom1").feature("wp2").set("displ", new String[]{"0", "de_b/2"});
    model.component("comp1").geom("geom1").feature("wp2").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("if1").set("condition", "p_ol==1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("if1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "w_ol/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"wp_ol", "hp_ol"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("elseif1").set("condition", "p_ol==0");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("elseif1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"w_ol", "h_ol"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"wp_ol", "hp_ol"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "l_ol", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", "-l_b/2");
    model.component("comp1").geom("geom1").feature("wp3").set("displ", new String[]{"0", "de_b/2"});
    model.component("comp1").geom("geom1").feature("wp3").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("if1").set("condition", "p_il==1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("if1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "w_il/2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1")
         .set("pos", new String[]{"wp_il", "hp_il"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("elseif1").set("condition", "p_il==0");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("elseif1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"w_il", "h_il"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"wp_il", "hp_il"});
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "-l_il", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("Inlet");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "-l_b/2-l_il-l_b/100");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "-l_b/2-l_il+l_b/100");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("inlet");
    model.component("comp1").geom("geom1").feature("boxsel1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selkeep", false);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("Outlet");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "l_b/2+l_ol-l_b/100");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "l_b/2+l_ol+l_b/100");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("outlet");
    model.component("comp1").geom("geom1").feature("boxsel2").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").feature("boxsel2").set("selkeep", false);
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("Water surface");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "de_b-de_b/100");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "de_b+de_b/100");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("free surface");
    model.component("comp1").geom("geom1").feature("boxsel3").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").feature("boxsel3").set("selkeep", false);
    model.component("comp1").geom("geom1").run("if1");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("Transparent Wall");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "-w_b/2-w_b/100");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", "-w_b/2+w_b/100");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("transparent_wall");
    model.component("comp1").geom("geom1").feature("boxsel4").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").feature("boxsel4").set("selkeep", false);
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 3);
    model.component("comp1").multiphysics("rfd1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("rfd1").set("Species_physics", "tds");

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_csel4_bnd");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("tds").feature("cdm1").set("DiffusionMaterialList", "mat1");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D_diff", "0", "0", "0", "D_diff", "0", "0", "0", "D_diff"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_0", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c_0", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("tds").create("reac1", "Reactions", 3);
    model.component("comp1").physics("tds").feature("reac1").selection().all();
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_c", "-k_f*c", 0);

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").label("Hide Geometry Objects 1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").named("csel4");
    model.component("comp1").view("view1").hideObjects().create("hide2");
    model.component("comp1").view("view1").hideObjects("hide2").label("Hide Geometry Objects 2");
    model.component("comp1").view("view1").hideObjects("hide2").init(2);
    model.component("comp1").view("view1").hideObjects("hide2").named("csel5");
    model.component("comp1").view("view1").hideObjects().create("hide3");
    model.component("comp1").view("view1").hideObjects("hide3").label("Hide Geometry Objects 3");
    model.component("comp1").view("view1").hideObjects("hide3").init(2);
    model.component("comp1").view("view1").hideObjects("hide3").named("csel2");
    model.component("comp1").view("view1").hideObjects().create("hide4");
    model.component("comp1").view("view1").hideObjects("hide4").label("Hide Geometry Objects 4");
    model.component("comp1").view("view1").hideObjects("hide4").init(2);
    model.component("comp1").view("view1").hideObjects("hide4").named("csel3");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat2").set("usesol", true);
    model.study("std1").feature("stat2").set("notsolmethod", "sol");
    model.study("std1").feature("stat2").set("notstudy", "std1");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Velocity (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("Slice");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("Exterior Walls");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43, 44, 45, 46, 47);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Pressure (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Wall Resolution (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Wall Resolution");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("Concentration, Streamline (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy", "tds.tflux_cz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").create("col", "Color");
    model.result("pg4").feature("str1").feature("col").set("expr", "c");
    model.result("pg4").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg4").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg4").feature("str1").set("linetype", "ribbon");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("Concentration, Surface (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "-l_b/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "de_b/2", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", "l_b/2", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "de_b/2", 1, 2);
    model.result().dataset().create("cln2", "CutLine3D");
    model.result().dataset("cln2").setIndex("genpoints", "-l_b/2+di_w/2", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", "-w_b/2", 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", "de_b/2", 0, 2);
    model.result().dataset("cln2").setIndex("genpoints", "-l_b/2+di_w/2", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "w_b/2", 1, 1);
    model.result().dataset("cln2").setIndex("genpoints", "de_b/2", 1, 2);
    model.result().dataset("cln2").set("genparaactive", true);
    model.result().dataset("cln2").set("genparadist", "range(di_w,di_w,l_b-di_w)");
    model.result().dataset("cln2").set("orthvec", new int[]{1, 0, 0});
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", "-l_b/2");
    model.result().dataset("cpt1").set("pointy", "wp_il");
    model.result().dataset("cpt1").set("pointz", "(hp_il+de_b/2)");
    model.result().dataset().create("cpt2", "CutPoint3D");
    model.result().dataset("cpt2").set("pointx", "l_b/2");
    model.result().dataset("cpt2").set("pointy", "wp_ol");
    model.result().dataset("cpt2").set("pointz", "(hp_ol+de_b/2)");
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("Pressure, Inlet");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().numerical("pev1").setIndex("expr", "p", 0);
    model.result().numerical("pev1").setIndex("unit", "Pa", 0);
    model.result().numerical("pev1").setIndex("descr", "Pressure", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Pressure, Inlet");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("pev2", "EvalPoint");
    model.result().numerical("pev2").label("Pressure, Outlet");
    model.result().numerical("pev2").set("data", "cpt2");
    model.result().numerical("pev2").setIndex("expr", "p", 0);
    model.result().numerical("pev2").setIndex("unit", "Pa", 0);
    model.result().numerical("pev2").setIndex("descr", "Pressure", 0);
    model.result().numerical("pev2").set("table", "tbl1");
    model.result().numerical("pev2").appendResult();
    model.result().numerical().create("meas1", "MeasureVolume");
    model.result().numerical("meas1").label("Basin Volume");
    model.result().numerical("meas1").selection().all();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Basin Volume");
    model.result().numerical("meas1").set("table", "tbl2");
    model.result().numerical("meas1").setResult();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("Flow Rate");
    model.result().numerical("int1").selection().named("geom1_csel2_bnd");
    model.result().numerical("int1").setIndex("expr", "u_0", 0);
    model.result().numerical("int1").setIndex("unit", "m^3/s", 0);
    model.result().numerical("int1").setIndex("descr", "Inlet velocity", 0);
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").appendResult();
    model.result().table("tbl1").label("Pressure");
    model.result().table("tbl2").label("Space Time");
    model.result().table("tbl2").setIndex("headers", "Basin volume (m^3)", 0, 1);
    model.result().table("tbl2").setIndex("headers", "Flow rate (m^3/s)", 1, 1);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Slice: Velocity magnitude (m/s)  Streamline: Velocity field");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickznumber", 1);
    model.result("pg1").run();
    model.result("pg1").create("slc2", "Slice");
    model.result("pg1").feature("slc2").set("quickplane", "zx");
    model.result("pg1").feature("slc2").set("quickynumber", 1);
    model.result("pg1").feature("slc2").set("inheritplot", "slc1");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("startmethod", "coord");
    model.result("pg1").feature("str1").set("xcoord", "range(-l_b/2+di_w/2,l_b/40,l_b/2-di_w/2)");
    model.result("pg1").feature("str1").set("ycoord", 0);
    model.result("pg1").feature("str1").set("zcoord", "de_b/2");
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("str1").set("tuberadiusscale", 0.025);
    model.result("pg1").feature("str1").set("inttol", 0.001);
    model.result("pg1").feature("str1").set("stattol", 0.1);
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().dataset().remove("surf1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Pressure Profile");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("expr", "p");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Concentration Profile");
    model.result("pg3").set("data", "cln2");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "Cut line plots: Concentration profiles");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "Basin width (m)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "c");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("recover", "pprint");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("Concentration Field");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Slice: Concentration (mol/m<sup>3</sup>)  Streamline: Velocity field");
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "c");
    model.result("pg4").feature("slc1").set("quickplane", "xy");
    model.result("pg4").feature("slc1").set("quickznumber", 1);
    model.result("pg4").feature("slc1").set("rangecoloractive", true);
    model.result("pg4").feature("slc1").set("rangecolormax", 1);
    model.result("pg4").run();
    model.result("pg4").create("slc2", "Slice");
    model.result("pg4").feature("slc2").set("expr", "c");
    model.result("pg4").feature("slc2").set("quickplane", "zx");
    model.result("pg4").feature("slc2").set("quickynumber", 1);
    model.result("pg4").feature("slc2").set("inheritplot", "slc1");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").selection().named("geom1_csel2_bnd");
    model.result("pg4").feature("str1").set("linetype", "tube");
    model.result("pg4").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("str1").set("tuberadiusscale", 0.025);
    model.result("pg4").feature("str1").set("inttol", 0.001);
    model.result("pg4").feature("str1").set("stattol", 0.1);
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("col1").set("expr", "c");
    model.result("pg4").feature("str1").feature("col1").set("rangecoloractive", true);
    model.result("pg4").feature("str1").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("str1").feature("col1").set("rangecolormax", 1);
    model.result("pg4").feature("str1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").run();

    model.title(null);

    model.description("");

    model.label("water_treatment_basin_embedded.mph");

    model.result("pg4").run();

    model.setExpectedComputationTime("20 \u5206\u949f");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///water_treatment.docx");
    model.result().report("rpt1").set("imagesize", "xlarge");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").label("\u6c34\u5904\u7406\u6c60");
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u8fd9\u4e2a App \u5b9a\u4e49\u4e86\u4e00\u4e2a\u5bf9\u6c34\u5904\u7406\u6c60\u8fdb\u884c\u6a21\u62df\u8fd0\u884c\uff0c\u4ee5\u4fbf\u5bf9\u6dfb\u52a0\u7684\u5316\u5b66\u7269\u8d28\uff08\u5982\u6c2f\uff09\u8fdb\u884c\u6df7\u5408\u548c\u53cd\u5e94\u7684\u4eff\u771f\u6a21\u578b\u3002\u53ef\u80fd\u7684\u51e0\u4f55\u5f62\u72b6\u662f\u77e9\u5f62\u6c34\u6c60\uff0c\u4e24\u4fa7\u88c5\u6709\u6321\u677f\uff0c\u5165\u53e3\u548c\u51fa\u53e3\u901a\u9053\u7684\u6a2a\u622a\u9762\u53ef\u4ee5\u662f\u5706\u5f62\u6216\u77e9\u5f62\u3002\u7ed3\u679c\u663e\u793a\u4e86\u6d41\u573a\uff0c\u4ee5\u53ca\u4e00\u7ea7\u5316\u5b66\u53cd\u5e94\u4f53\u7cfb\u4e2d\u5316\u5b66\u7269\u8d28\u7684\u6d53\u5ea6\u573a\u3002");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u4eff\u771f\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeauthor", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includedatecreated", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeversion", false);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u6a21\u578b\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature().create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature("mat1").set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("mat1").set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature().create("phys1", "Physics");
    model.result().report("rpt1").feature("sec2").feature("phys1").set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("phys1").set("includefeaturetable", false);
    model.result().report("rpt1").feature("sec2").feature("phys1")
         .set("children", new String[][]{{"fp1", "off", "off", "off"}, 
         {"init1", "off", "off", "off"}, 
         {"wallbc1", "off", "off", "off"}, 
         {"grav1", "off", "off", "off"}, 
         {"dcont1", "off", "off", "off"}, 
         {"inl1", "off", "off", "off"}, 
         {"out1", "off", "off", "off"}, 
         {"sym1", "off", "off", "off"}});
    model.result().report("rpt1").feature("sec2").feature().create("phys2", "Physics");
    model.result().report("rpt1").feature("sec2").feature("phys2").set("noderef", "tds");
    model.result().report("rpt1").feature("sec2").feature("phys2").set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("phys2").set("includefeaturetable", false);
    model.result().report("rpt1").feature("sec2").feature("phys2")
         .set("children", new String[][]{{"sp1", "off", "off", "off"}, 
         {"cdm1", "off", "off", "off"}, 
         {"nflx1", "off", "off", "off"}, 
         {"init1", "off", "off", "off"}, 
         {"dcont1", "off", "off", "off"}, 
         {"in1", "off", "off", "off"}, 
         {"out1", "off", "off", "off"}, 
         {"reac1", "off", "off", "off"}});
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7f51\u683c 1");
    model.result().report("rpt1").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature().create("field1", "StringDataField");
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7814\u7a76 1");
    model.result().report("rpt1").feature("sec4").feature().create("std1", "Study");
    model.result().report("rpt1").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec5").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec5").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec5").feature("sec1").label("\u8868\u683c");
    model.result().report("rpt1").feature("sec5").feature("sec1").feature().create("field1", "DoubleDataField");
    model.result().report("rpt1").feature("sec5").feature("sec1").feature("field1").label("\u6d3e\u751f\u503c");
    model.result().report("rpt1").feature("sec5").feature("sec1").feature("field1").set("numberformat", "custom");
    model.result().report("rpt1").feature("sec5").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec5").feature("sec2").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature("pg1").label("\u901f\u5ea6");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature("pg2").label("\u6d53\u5ea6\u5206\u5e03");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature("pg2").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature("pg3").label("\u6d53\u5ea6\u573a");
    model.result().report("rpt1").feature("sec5").feature("sec2").feature("pg3").set("noderef", "pg4");

    model.title("\u6c34\u5904\u7406\u6c60");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u53c2\u6570\u5316\u51e0\u4f55\uff0c\u5305\u542b\u5e26\u6709 if \u8bed\u53e5\u7684\u51e0\u4f55\u5e8f\u5217\uff0c\u4ee5\u4ea7\u751f\u4e0d\u540c\u7c7b\u578b\u7684\u8bbe\u8ba1\n\u2022 \u7528\u4e8e\u8bbe\u7f6e\u7f51\u683c\u5927\u5c0f\u7684\u9009\u9879\n\u2022 \u56fe\u5f62\u7528\u6237\u754c\u9762\uff0c\u5305\u542b\u53ef\u4ee5\u663e\u793a\u6216\u9690\u85cf\u7684\u5404\u79cd\u7a97\u53e3\n\u2022 \u6d45\u8272\u4e3b\u9898\n\n\u6c34\u5904\u7406\u6c60\u5728\u8bb8\u591a\u5de5\u4e1a\u89c4\u6a21\u7684\u8fc7\u7a0b\u4e2d\u7528\u4e8e\u53bb\u9664\u7ec6\u83cc\u6216\u5176\u4ed6\u6c61\u67d3\u7269\u3002\n\n\u8be5 App \u4e3e\u4f8b\u8bf4\u660e\u5982\u4f55\u6a21\u62df\u53d7\u5316\u5b66\u53cd\u5e94\u5f71\u54cd\u7684\u6e4d\u6d41\u548c\u7269\u6599\u5e73\u8861\u3002\u60a8\u53ef\u4ee5\u6307\u5b9a\u5904\u7406\u6c60\u7684\u5c3a\u5bf8\u548c\u65b9\u5411\u3001\u6df7\u5408\u6321\u677f\u4ee5\u53ca\u5165\u53e3\u548c\u51fa\u53e3\u901a\u9053\u3002\u6b64\u5916\uff0c\u8fd8\u53ef\u4ee5\u5728\u4e00\u7ea7\u53cd\u5e94\u4e2d\u8bbe\u7f6e\u5165\u53e3\u901f\u5ea6\u3001\u7269\u8d28\u6d53\u5ea6\u548c\u53cd\u5e94\u901f\u7387\u5e38\u6570\u3002\n\n\u8be5 App \u6c42\u89e3\u6d41\u7ecf\u5904\u7406\u6c60\u7684\u6e4d\u6d41\u5e76\u663e\u793a\u4ea7\u751f\u7684\u6d41\u573a\u548c\u6d53\u5ea6\u573a\u4ee5\u53ca\u65f6\u7a7a\u3001\u534a\u8870\u671f\u548c\u538b\u964d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("water_treatment_basin.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
