/*
 * forced_air_cooling_with_heat_sink.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:17 by COMSOL 6.3.0.290. */
public class forced_air_cooling_with_heat_sink {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "TurbulentFlowAlgebraicYplus", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("width", "3[cm]");
    model.param().descr("width", "Heat sink width");
    model.param().set("depth", "3[cm]");
    model.param().descr("depth", "Heat sink depth");
    model.param().set("th", "5[mm]");
    model.param().descr("th", "Heat sink base thickness");
    model.param().set("chamf_r", "2[mm]");
    model.param().descr("chamf_r", "Chamfer radius");
    model.param().set("fillet_r_i", "2[mm]");
    model.param().descr("fillet_r_i", "Fillet radius");
    model.param().set("surf_width_i", "width-chamf_r*2");
    model.param().descr("surf_width_i", "Upper surface width, heat sink");
    model.param().set("surf_depth_i", "depth-chamf_r*2");
    model.param().descr("surf_depth_i", "Upper surface depth, heat sink");
    model.param().set("fin_h_i", "1.0[cm]");
    model.param().descr("fin_h_i", "Height of fins");
    model.param().set("fin_th_i", "1.5[mm]");
    model.param().descr("fin_th_i", "Fin thickness");
    model.param().set("fin_n_i", "4");
    model.param().descr("fin_n_i", "Number of fins");
    model.param().set("pin_n_width_i", "5");
    model.param().descr("pin_n_width_i", "Number of pins in width");
    model.param().set("pin_n_depth_i", "5");
    model.param().descr("pin_n_depth_i", "Number of pins in depth");
    model.param().set("pin_rot_i", "1");
    model.param().descr("pin_rot_i", "Parameter for rotating the hexagonal pin by 30 degrees");
    model.param().set("pin_th_i", "2.0[mm]");
    model.param().descr("pin_th_i", "Pin thickness");
    model.param().set("box_spacing_depth", "0.5[cm]");
    model.param().descr("box_spacing_depth", "Spacing between heat sink and lateral sides of the box");
    model.param().set("box_spacing_inlet", "2.0[cm]");
    model.param().descr("box_spacing_inlet", "Spacing between heat sink and inlet");
    model.param().set("box_spacing_outlet", "6.0[cm]");
    model.param().descr("box_spacing_outlet", "Spacing between heat sink and outlet");
    model.param().set("box_spacing_height", "0.5[cm]");
    model.param().descr("box_spacing_height", "Spacing between heat sink and top side of the box");
    model.param().set("box_length", "width+box_spacing_inlet+box_spacing_outlet");
    model.param().descr("box_length", "Box length");
    model.param().set("box_width", "depth+2*box_spacing_depth");
    model.param().descr("box_width", "Box width");
    model.param().set("box_height", "th+fin_h_i+box_spacing_height");
    model.param().descr("box_height", "Box height");
    model.param().set("T_amb", "22[degC]");
    model.param().descr("T_amb", "Ambient temperature");
    model.param().set("T_source", "100[degC]");
    model.param().descr("T_source", "Heat source temperature");
    model.param().set("h_c", "300[W/(m^2*K)]");
    model.param().descr("h_c", "Heat transfer coefficient between heat sink and heat source");
    model.param().set("u0", "1.0[m/s]");
    model.param().descr("u0", "Inlet velocity");
    model.param().set("sol_tol", "1e-3");
    model.param().descr("sol_tol", "Nonlinear solver tolerance");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").inputParam().set("surf_width", "2.25[cm]");
    model.geom("part1").inputParam().descr("surf_width", "Upper surface width, heat sink");
    model.geom("part1").inputParam().set("surf_depth", "2.25[cm]");
    model.geom("part1").inputParam().descr("surf_depth", "Upper surface depth, heat sink");
    model.geom("part1").inputParam().set("fillet_r", "2.0[mm]");
    model.geom("part1").inputParam().descr("fillet_r", "Fillet radius");
    model.geom("part1").inputParam().set("fin_h", "1.0[cm]");
    model.geom("part1").inputParam().descr("fin_h", "Fin height");
    model.geom("part1").inputParam().set("fin_th", "1.0[mm]");
    model.geom("part1").inputParam().descr("fin_th", "Fin thickness");
    model.geom("part1").inputParam().set("fin_n", "7");
    model.geom("part1").inputParam().descr("fin_n", "Number of fins");
    model.geom("part1").localParam().set("spacing_width", "(surf_width-fillet_r)/fin_n");
    model.geom("part1").localParam().descr("spacing_width", "Spacing in width");
    model.geom("part1").localParam().set("spacing_depth", "(surf_depth-fillet_r)/fin_n");
    model.geom("part1").localParam().descr("spacing_depth", "Spacing in depth");
    model.geom("part1").localParam().set("fin_width", "surf_width-spacing_width");
    model.geom("part1").localParam().descr("fin_width", "Fin width");
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").feature("wp1").geom().create("e1", "Ellipse");
    model.geom("part1").feature("wp1").geom().feature("e1").set("semiaxes", new String[]{"fin_width/2", "1"});
    model.geom("part1").feature("wp1").geom().feature("e1").setIndex("semiaxes", "fin_th/2", 1);
    model.geom("part1").feature("wp1").geom().feature("e1")
         .set("pos", new String[]{"0", "-surf_depth/2+spacing_depth/2+fillet_r/2"});
    model.geom("part1").feature("wp1").geom().run("e1");
    model.geom("part1").feature("wp1").geom().create("arr1", "Array");
    model.geom("part1").feature("wp1").geom().feature("arr1").selection("input").set("e1");
    model.geom("part1").feature("wp1").geom().feature("arr1").set("type", "linear");
    model.geom("part1").feature("wp1").geom().feature("arr1").set("linearsize", "fin_n");
    model.geom("part1").feature("wp1").geom().feature("arr1").set("displ", new String[]{"0", "spacing_depth"});
    model.geom("part1").feature("wp1").geom().run("arr1");
    model.geom("part1").run("wp1");
    model.geom("part1").feature().create("ext1", "Extrude");
    model.geom("part1").feature("ext1").setIndex("distance", "fin_h", 0);
    model.geom("part1").feature("ext1").set("crossfaces", false);
    model.geom("part1").run("ext1");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").inputParam().set("surf_width", "2.25[cm]");
    model.geom("part2").inputParam().descr("surf_width", "Upper surface width, heat sink");
    model.geom("part2").inputParam().set("surf_depth", "2.25[cm]");
    model.geom("part2").inputParam().descr("surf_depth", "Upper surface depth, heat sink");
    model.geom("part2").inputParam().set("fillet_r", "2.0[mm]");
    model.geom("part2").inputParam().descr("fillet_r", "Fillet radius");
    model.geom("part2").inputParam().set("fin_h", "1.0[cm]");
    model.geom("part2").inputParam().descr("fin_h", "Fin height");
    model.geom("part2").inputParam().set("fin_th", "1.0[mm]");
    model.geom("part2").inputParam().descr("fin_th", "Fin thickness");
    model.geom("part2").inputParam().set("fin_n", "7");
    model.geom("part2").inputParam().descr("fin_n", "Number of fins");
    model.geom("part2").localParam().set("spacing_width", "(surf_width-fillet_r)/fin_n");
    model.geom("part2").localParam().descr("spacing_width", "Spacing in width");
    model.geom("part2").localParam().set("spacing_depth", "(surf_depth-fillet_r)/fin_n");
    model.geom("part2").localParam().descr("spacing_depth", "Spacing in depth");
    model.geom("part2").localParam().set("fin_width", "surf_width-spacing_width");
    model.geom("part2").localParam().descr("fin_width", "Fin width");
    model.geom("part2").create("blk1", "Block");
    model.geom("part2").feature("blk1").set("size", new String[]{"fin_width", "fin_th", "1"});
    model.geom("part2").feature("blk1").setIndex("size", "fin_h", 2);
    model.geom("part2").feature("blk1").set("base", "center");
    model.geom("part2").feature("blk1")
         .set("pos", new String[]{"0", "-surf_depth/2+spacing_depth/2+fillet_r/2", "0"});
    model.geom("part2").feature("blk1").setIndex("pos", "fin_h/2", 2);
    model.geom("part2").run("blk1");
    model.geom("part2").create("arr1", "Array");
    model.geom("part2").feature("arr1").selection("input").set("blk1");
    model.geom("part2").feature("arr1").set("type", "linear");
    model.geom("part2").feature("arr1").set("linearsize", "fin_n");
    model.geom("part2").feature("arr1").set("displ", new String[]{"0", "spacing_depth", "0"});
    model.geom("part2").run("arr1");
    model.geom("part2").create("uni1", "Union");
    model.geom("part2").feature("uni1").selection("input").set("arr1");
    model.geom("part2").run("uni1");
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").inputParam().set("surf_width", "2.25[cm]");
    model.geom("part3").inputParam().descr("surf_width", "Upper surface width, heat sink");
    model.geom("part3").inputParam().set("surf_depth", "2.25[cm]");
    model.geom("part3").inputParam().descr("surf_depth", "Upper surface depth, heat sink");
    model.geom("part3").inputParam().set("fillet_r", "2.0[mm]");
    model.geom("part3").inputParam().descr("fillet_r", "Fillet radius");
    model.geom("part3").inputParam().set("fin_h", "1.0[cm]");
    model.geom("part3").inputParam().descr("fin_h", "Fin height");
    model.geom("part3").inputParam().set("pin_th", "2.0[mm]");
    model.geom("part3").inputParam().descr("pin_th", "Pin thickness");
    model.geom("part3").inputParam().set("pin_n_width", "5");
    model.geom("part3").inputParam().descr("pin_n_width", "Number of pins in width");
    model.geom("part3").inputParam().set("pin_n_depth", "5");
    model.geom("part3").inputParam().descr("pin_n_depth", "Number of pins in depth");
    model.geom("part3").localParam().set("spacing_width", "(surf_width-fillet_r)/pin_n_width");
    model.geom("part3").localParam().descr("spacing_width", "Spacing in width");
    model.geom("part3").localParam().set("spacing_depth", "(surf_depth-fillet_r)/pin_n_depth");
    model.geom("part3").localParam().descr("spacing_depth", "Spacing in depth");
    model.geom("part3").create("blk1", "Block");
    model.geom("part3").feature("blk1").set("size", new String[]{"pin_th", "pin_th", "1"});
    model.geom("part3").feature("blk1").setIndex("size", "fin_h", 2);
    model.geom("part3").feature("blk1").set("base", "center");
    model.geom("part3").feature("blk1")
         .set("pos", new String[]{"-surf_width/2+spacing_width/2+fillet_r/2", "0", "0"});
    model.geom("part3").feature("blk1").setIndex("pos", "-surf_depth/2+spacing_depth/2+fillet_r/2", 1);
    model.geom("part3").feature("blk1").setIndex("pos", "fin_h/2", 2);
    model.geom("part3").run("blk1");
    model.geom("part3").create("arr1", "Array");
    model.geom("part3").feature("arr1").selection("input").set("blk1");
    model.geom("part3").feature("arr1").set("fullsize", new String[]{"pin_n_width", "1", "1"});
    model.geom("part3").feature("arr1").setIndex("fullsize", "pin_n_depth", 1);
    model.geom("part3").feature("arr1").set("displ", new String[]{"spacing_width", "0", "0"});
    model.geom("part3").feature("arr1").setIndex("displ", "spacing_depth", 1);
    model.geom("part3").run("arr1");
    model.geom("part3").create("uni1", "Union");
    model.geom("part3").feature("uni1").selection("input").set("arr1");
    model.geom("part3").run("uni1");
    model.geom().create("part4", "Part", 3);
    model.geom("part4").geomRep("comsol");
    model.geom("part4").inputParam().set("surf_width", "2.25[cm]");
    model.geom("part4").inputParam().descr("surf_width", "Upper surface width, heat sink");
    model.geom("part4").inputParam().set("surf_depth", "2.25[cm]");
    model.geom("part4").inputParam().descr("surf_depth", "Upper surface depth, heat sink");
    model.geom("part4").inputParam().set("fillet_r", "2.0[mm]");
    model.geom("part4").inputParam().descr("fillet_r", "Fillet radius");
    model.geom("part4").inputParam().set("fin_h", "1.0[cm]");
    model.geom("part4").inputParam().descr("fin_h", "Fin height");
    model.geom("part4").inputParam().set("pin_th", "2.0[mm]");
    model.geom("part4").inputParam().descr("pin_th", "Pin thickness");
    model.geom("part4").inputParam().set("pin_n_width", "5");
    model.geom("part4").inputParam().descr("pin_n_width", "Number of pins in width");
    model.geom("part4").inputParam().set("pin_n_depth", "5");
    model.geom("part4").inputParam().descr("pin_n_depth", "Number of pins in depth");
    model.geom("part4").localParam().set("spacing_width", "(surf_width-fillet_r)/pin_n_width");
    model.geom("part4").localParam().descr("spacing_width", "Spacing in width");
    model.geom("part4").localParam().set("spacing_depth", "(surf_depth-fillet_r)/pin_n_depth");
    model.geom("part4").localParam().descr("spacing_depth", "Spacing in depth");
    model.geom("part4").create("cyl1", "Cylinder");
    model.geom("part4").feature("cyl1").set("r", "pin_th/2");
    model.geom("part4").feature("cyl1").set("h", "fin_h");
    model.geom("part4").feature("cyl1")
         .set("pos", new String[]{"-surf_width/2+spacing_width/2+fillet_r/2", "0", "0"});
    model.geom("part4").feature("cyl1").setIndex("pos", "-surf_depth/2+spacing_depth/2+fillet_r/2", 1);
    model.geom("part4").run("cyl1");
    model.geom("part4").create("arr1", "Array");
    model.geom("part4").feature("arr1").selection("input").set("cyl1");
    model.geom("part4").feature("arr1").set("fullsize", new String[]{"pin_n_width", "1", "1"});
    model.geom("part4").feature("arr1").setIndex("fullsize", "pin_n_depth", 1);
    model.geom("part4").feature("arr1").set("displ", new String[]{"spacing_width", "0", "0"});
    model.geom("part4").feature("arr1").setIndex("displ", "spacing_depth", 1);
    model.geom("part4").run("arr1");
    model.geom("part4").create("uni1", "Union");
    model.geom("part4").feature("uni1").selection("input").set("arr1");
    model.geom("part4").run("uni1");
    model.geom().create("part5", "Part", 3);
    model.geom("part5").geomRep("comsol");
    model.geom("part5").inputParam().set("surf_width", "2.25[cm]");
    model.geom("part5").inputParam().descr("surf_width", "Upper surface width, heat sink");
    model.geom("part5").inputParam().set("surf_depth", "2.25[cm]");
    model.geom("part5").inputParam().descr("surf_depth", "Upper surface depth, heat sink");
    model.geom("part5").inputParam().set("fillet_r", "2.0[mm]");
    model.geom("part5").inputParam().descr("fillet_r", "Fillet radius");
    model.geom("part5").inputParam().set("fin_h", "1.0[cm]");
    model.geom("part5").inputParam().descr("fin_h", "Fin height");
    model.geom("part5").inputParam().set("pin_th", "2.0[mm]");
    model.geom("part5").inputParam().descr("pin_th", "Pin thickness");
    model.geom("part5").inputParam().set("pin_n_width", "5");
    model.geom("part5").inputParam().descr("pin_n_width", "Number of pins in width");
    model.geom("part5").inputParam().set("pin_n_depth", "5");
    model.geom("part5").inputParam().descr("pin_n_depth", "Number of pins in depth");
    model.geom("part5").inputParam().set("pin_rot", "1");
    model.geom("part5").inputParam().descr("pin_rot", "Parameter for rotating the hexagonal pin by 30 degrees");
    model.geom("part5").localParam().set("spacing_width", "(surf_width-fillet_r)/pin_n_width");
    model.geom("part5").localParam().descr("spacing_width", "Spacing in width");
    model.geom("part5").localParam().set("spacing_depth", "(surf_depth-fillet_r)/pin_n_depth");
    model.geom("part5").localParam().descr("spacing_depth", "Spacing in depth");
    model.geom("part5").create("wp1", "WorkPlane");
    model.geom("part5").feature("wp1").set("unite", true);
    model.geom("part5").feature("wp1").geom().create("pol1", "Polygon");
    model.geom("part5").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.geom("part5").feature("wp1").geom().feature("pol1").set("x", "pin_th/2*cos({range(0,pi/3,2*pi)})");
    model.geom("part5").feature("wp1").geom().feature("pol1").set("y", "pin_th/2*sin({range(0,pi/3,2*pi)})");
    model.geom("part5").feature("wp1").geom().run("pol1");
    model.geom("part5").feature("wp1").geom().create("rot1", "Rotate");
    model.geom("part5").feature("wp1").geom().feature("rot1").selection("input").set("pol1");
    model.geom("part5").feature("wp1").geom().feature("rot1").set("rot", "30*pin_rot");
    model.geom("part5").feature("wp1").geom().run("rot1");
    model.geom("part5").feature("wp1").geom().create("mov1", "Move");
    model.geom("part5").feature("wp1").geom().feature("mov1").selection("input").set("rot1");
    model.geom("part5").feature("wp1").geom().feature("mov1")
         .set("displx", "-surf_width/2+spacing_width/2+fillet_r/2");
    model.geom("part5").feature("wp1").geom().feature("mov1")
         .set("disply", "-surf_depth/2+spacing_depth/2+fillet_r/2");
    model.geom("part5").feature("wp1").geom().run("mov1");
    model.geom("part5").feature("wp1").geom().create("arr1", "Array");
    model.geom("part5").feature("wp1").geom().feature("arr1").selection("input").set("mov1");
    model.geom("part5").feature("wp1").geom().feature("arr1").set("fullsize", new String[]{"pin_n_width", "1"});
    model.geom("part5").feature("wp1").geom().feature("arr1").setIndex("fullsize", "pin_n_depth", 1);
    model.geom("part5").feature("wp1").geom().feature("arr1").set("displ", new String[]{"spacing_width", "0"});
    model.geom("part5").feature("wp1").geom().feature("arr1").setIndex("displ", "spacing_depth", 1);
    model.geom("part5").feature("wp1").geom().run("arr1");
    model.geom("part5").run("wp1");
    model.geom("part5").feature().create("ext1", "Extrude");
    model.geom("part5").feature("ext1").setIndex("distance", "fin_h", 0);
    model.geom("part5").feature("ext1").set("crossfaces", false);
    model.geom("part5").run("ext1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"surf_width_i", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "surf_depth_i", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("if1").set("condition", "fillet_r_i>0");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "fillet_r_i");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("endif1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "th", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Heat Sink");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "chamf_r>0");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp2").set("quicky", "-depth/2+fillet_r_i+chamf_r");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"chamf_r", "th"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"-width/2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cha1").selection("pointinsketch")
         .set("r1", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cha1").set("dist", "chamf_r");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("cha1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2")
         .setIndex("distance", "-(depth-2*fillet_r_i-2*chamf_r)", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("crossfaces", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Edge");
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", "-width/2+fillet_r_i+chamf_r");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"chamf_r", "th"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"-depth/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cha1").selection("pointinsketch")
         .set("r1", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cha1").set("dist", "chamf_r");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("cha1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "width-2*fillet_r_i-2*chamf_r", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("crossfaces", false);
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "fillet_r_i>0");
    model.component("comp1").geom("geom1").run("if2");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("revolvefrom", "faces");
    model.component("comp1").geom("geom1").feature("rev1").selection("inputface").set("ext2", 5);
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("rev1").set("pos", new String[]{"chamf_r/2+fillet_r_i", "0"});
    model.component("comp1").geom("geom1").feature("rev1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev2").set("revolvefrom", "faces");
    model.component("comp1").geom("geom1").feature("rev2").selection("inputface").set("ext3", 1);
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("rev2").set("pos", new String[]{"chamf_r/2+fillet_r_i", "0"});
    model.component("comp1").geom("geom1").feature("rev2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("rev2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("rev1", "rev2");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "rev1", "rev2", "rot1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("elseif1").set("condition", "fillet_r_i==0");
    model.component("comp1").geom("geom1").run("elseif1");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").set("ext2", 5);
    model.component("comp1").geom("geom1").feature("ext4").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "chamf_r+fillet_r_i", 0);
    model.component("comp1").geom("geom1").feature("ext4").set("crossfaces", false);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Corner 1");
    model.component("comp1").geom("geom1").feature("ext4").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").named("csel3");
    model.component("comp1").geom("geom1").feature("rot2").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot2").set("rot", -90);
    model.component("comp1").geom("geom1").feature("rot2").set("pos", new String[]{"-width/2+chamf_r/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("rot2").setIndex("pos", "depth/2-chamf_r/2", 1);
    model.component("comp1").geom("geom1").feature("rot2").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("ext4", "rot2");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").set("ext3", 1);
    model.component("comp1").geom("geom1").feature("ext5").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "chamf_r+fillet_r_i", 0);
    model.component("comp1").geom("geom1").feature("ext5").set("crossfaces", false);
    model.component("comp1").geom("geom1").feature("ext5").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("rot3").selection("input").set("ext5");
    model.component("comp1").geom("geom1").feature("rot3").set("keep", true);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("rot3").set("rot", -90);
    model.component("comp1").geom("geom1").feature("rot3").set("pos", new String[]{"-width/2+chamf_r/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("rot3").setIndex("pos", "-depth/2+chamf_r/2", 1);
    model.component("comp1").geom("geom1").feature("rot3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("rot3");
    model.component("comp1").geom("geom1").create("int2", "Intersection");
    model.component("comp1").geom("geom1").feature("int2").selection("input").set("ext5", "rot3");
    model.component("comp1").geom("geom1").run("int2");
    model.component("comp1").geom("geom1").create("rot4", "Rotate");
    model.component("comp1").geom("geom1").feature("rot4").selection("input").set("ext2", "ext3", "int1", "int2");
    model.component("comp1").geom("geom1").feature("rot4").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot4").set("rot", 180);
    model.component("comp1").geom("geom1").run("rot4");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input")
         .set("ext1", "ext2", "ext3", "int1", "int2", "rot4");
    model.component("comp1").geom("geom1").feature("uni2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").run("endif1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "surf_width", "surf_width_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "surf_depth", "surf_depth_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "fillet_r", "fillet_r_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "fin_h", "fin_h_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "fin_th", "fin_th_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "fin_n", "fin_n_i");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "0", "th"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("pi1", "uni1");
    model.component("comp1").geom("geom1").feature("uni3").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("uni3");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"box_length", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "box_width", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "box_height", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"box_length/2-width/2-box_spacing_inlet", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "box_height/2", 2);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Cumulative Selection: Box");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("Explicit Selection: Inlet");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("blk1", 2);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("Inlet");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("Explicit Selection: Outlet");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("blk1", 5);
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("Outlet");
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel6");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("Explicit Selection: Surfaces to Hide");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("blk1", 2, 3, 4);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").named("geom1_sel3");
    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").named("sel3");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("All Domains");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("Air");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"geom1_csel1_dom"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("Heat Source Surface");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"geom1_csel1_bnd", "geom1_csel4_bnd"});

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").label("Aluminum");
    model.material("mat1").set("family", "aluminum");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat2").label("Copper");
    model.material("mat2").set("family", "copper");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material("mat3").selection().named("dif1");

    model.component("comp1").physics("ht").prop("ConsistentStabilization").set("heatCrosswindDiffusion", false);
    model.component("comp1").physics("ht").feature("fluid1").selection().named("dif1");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_amb");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("int1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_c");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_source");
    model.component("comp1").physics("spf").selection().named("dif1");
    model.component("comp1").physics("spf").prop("ConsistentStabilization").set("CrosswindDiffusion", false);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_csel6_bnd");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Temperature (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Velocity (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("Slice");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("Exterior Walls");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(2, 3, 4, 5, 6, 7, 10, 11, 12, 14, 16, 17, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 47, 48, 50, 51);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Pressure (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Wall Resolution (spf)");
    model.result("pg4").set("data", "surf1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Wall Resolution");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Temperature and Fluid Flow (nitf1)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("Wall Temperature");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 10, 11, 12, 14, 16, 17, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 47, 48, 50, 51);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("Solid Temperature");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "nitf1.T");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg5").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg5").feature("vol1").feature("sel1").selection().set(2);
    model.result("pg5").feature("vol1").set("inheritplot", "surf1");
    model.result("pg5").feature().create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").label("Fluid Flow");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("solutionparams", "parent");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg5").feature("arwv1").set("xnumber", 30);
    model.result("pg5").feature("arwv1").set("ynumber", 30);
    model.result("pg5").feature("arwv1").set("znumber", 30);
    model.result("pg5").feature("arwv1").set("arrowtype", "cone");
    model.result("pg5").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("data", "parent");
    model.result("pg5").feature("arwv1").feature().create("col1", "Color");
    model.result("pg5").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg5").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("Cooling Power");
    model.result().numerical("int1").selection().named("int1");
    model.result().numerical("int1").setIndex("expr", "h_c*(T_source-T)", 0);
    model.result().numerical("int1").setIndex("descr", "Cooling power", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Cooling Power");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").label("Average Pressure Loss");
    model.result().numerical("av1").selection().named("geom1_csel5_bnd");
    model.result().numerical("av1").set("expr", new String[]{"p"});
    model.result().numerical("av1").set("descr", new String[]{"Pressure"});
    model.result().numerical("av1").set("unit", new String[]{"Pa"});
    model.result().numerical("av1").setIndex("descr", "Average pressure loss", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Average Pressure Loss");
    model.result().numerical("av1").set("table", "tbl2");
    model.result().numerical("av1").setResult();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Velocity magnitude (m/s)");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("quickplane", "xy");
    model.result("pg2").feature("slc1").set("quickzmethod", "coord");
    model.result("pg2").feature("slc1").set("quickz", "fin_h_i/2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("slc2", "Slice");
    model.result("pg2").feature("slc2").set("expr", "spf.U");
    model.result("pg2").feature("slc2").set("descr", "Velocity magnitude");
    model.result("pg2").feature("slc2").set("quickplane", "zx");
    model.result("pg2").feature("slc2").set("quickynumber", 1);
    model.result("pg2").feature("slc2").set("inheritplot", "slc1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"u", "v", "w"});

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg2").feature("str1").set("descr", "Velocity field");
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("startmethod", "coord");
    model.result("pg2").feature("str1").set("xcoord", "width");
    model.result("pg2").feature("str1").set("ycoord", "range(-3/4*width,width/8,3/4*width)");
    model.result("pg2").feature("str1").set("zcoord", "fin_h_i/1.9");
    model.result("pg2").feature("str1").set("linetype", "ribbon");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("str1").feature("col1").set("expr", "spf.U");
    model.result("pg2").feature("str1").feature("col1").set("descr", "Velocity magnitude");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("descr", "Pressure");
    model.result("pg2").feature("surf1").set("colortable", "GrayScale");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u6563\u70ed\u5668\u7684\u5f3a\u5236\u7a7a\u6c14\u51b7\u5374");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u8ba1\u7b97\u5b8c\u6210\u540e\u53d1\u9001\u5e26\u6709\u62a5\u544a\u7684\u7535\u5b50\u90ae\u4ef6\n\u2022 \u7528\u6237\u5b9a\u4e49\u7684\u7535\u5b50\u90ae\u4ef6\u670d\u52a1\u5668\u8bbe\u7f6e\uff0c\u5728\u8fd0\u884c\u5df2\u7f16\u8bd1\u7684\u72ec\u7acb App \u65f6\u975e\u5e38\u6709\u7528\n\u2022 \u7528\u4e8e\u8bbe\u7f6e\u4e0d\u540c\u7f51\u683c\u5927\u5c0f\u7684\u9009\u9879\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u5bf9\u8f93\u5165\u53c2\u6570\u8fdb\u884c\u8bef\u5dee\u63a7\u5236\n\n\u4eba\u4eec\u5e38\u5e38\u5bf9\u6563\u70ed\u5668\u5728\u7ed9\u5b9a\u98ce\u6247\u66f2\u7ebf\u4e0b\u7684\u6563\u70ed\u80fd\u529b\u8fdb\u884c\u57fa\u51c6\u6d4b\u8bd5\uff0c\u8fdb\u884c\u6b64\u7c7b\u5b9e\u9a8c\u7684\u4e00\u79cd\u53ef\u884c\u7684\u65b9\u6cd5\u662f\u5c06\u6563\u70ed\u5668\u653e\u7f6e\u5728\u5e26\u6709\u9694\u70ed\u58c1\u7684\u77e9\u5f62\u901a\u9053\u4e2d\u3002\n\n\u7136\u540e\u6d4b\u91cf\u901a\u9053\u5165\u53e3\u548c\u51fa\u53e3\u7684\u6e29\u5ea6\u548c\u538b\u529b\uff0c\u5e76\u5c06\u6563\u70ed\u5668\u5e95\u5ea7\u4fdd\u6301\u5728\u7ed9\u5b9a\u6e29\u5ea6\u6240\u9700\u7684\u529f\u7387\u3002\u6ee1\u8db3\u8fd9\u4e9b\u6761\u4ef6\u540e\uff0c\u60a8\u53ef\u4ee5\u8ba1\u7b97\u6563\u70ed\u5668\u7684\u6563\u70ed\u91cf\u548c\u901a\u9053\u7684\u538b\u529b\u635f\u5931\u3002\n\n\u8be5 App \u7684\u76ee\u7684\u662f\u5bf9\u6b64\u7c7b\u57fa\u51c6\u5b9e\u9a8c\u8fdb\u884c\u7814\u7a76\u3002\u60a8\u53ef\u4ee5\u66f4\u6539\u6563\u70ed\u5668\u7684\u7c7b\u578b\u4ee5\u53ca\u7fc5\u7247\u6216\u5f15\u811a\u7684\u6570\u91cf\u53ca\u5176\u5c3a\u5bf8\uff0c\u4ece\u800c\u6839\u636e\u901a\u9053\u4e0a\u7ed9\u5b9a\u7684\u538b\u529b\u635f\u5931\u6765\u786e\u5b9a\u6700\u4f73\u8bbe\u8ba1\u3002\n\n\u8be5 App \u652f\u6301\u6539\u53d8\u7a7a\u6c14\u6d41\u901f\u548c\u70ed\u6e90\u7387\uff0c\u5176\u4e2d\u5047\u8bbe\u6d41\u6001\u4e3a\u4ee3\u6570 y+ \u6a21\u578b\u63cf\u8ff0\u7684\u6e4d\u6d41\uff0c\u5e76\u6c42\u89e3\u975e\u7b49\u6e29\u6d41\u52a8\u3002");

    model.label("forced_air_cooling_with_heat_sink.mph");

    model.result("pg2").run();

    model.setExpectedComputationTime("8 \u5206\u949f");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u81ea\u7531\u5ea6\u6570");
    model.result().numerical("gev1").set("expr", new String[]{"numberofdofs"});
    model.result().numerical("gev1").set("descr", new String[]{"\u81ea\u7531\u5ea6\u6570"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u81ea\u7531\u5ea6\u6570");
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").setResult();
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///forced_air_cooling_with_heat_sink.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u8f6f\u4ef6\u5c5e\u6027");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includepath", false);
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u6a21\u578b");
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh1").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").label("\u51b7\u5374\u529f\u7387");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl2", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl2").label("\u5e73\u5747\u538b\u529b\u635f\u5931");
    model.result().report("rpt1").feature("sec4").feature("mtbl2").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("mtbl2").set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl3", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl3").label("\u81ea\u7531\u5ea6\u6570");
    model.result().report("rpt1").feature("sec4").feature("mtbl3").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("mtbl3").set("noderef", "tbl3");
    model.result().report("rpt1").feature("sec4").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg1").label("\u901f\u5ea6\u573a");
    model.result().report("rpt1").feature("sec4").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg2").label("\u538b\u529b\u573a");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec4").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg3").label("\u975e\u7b49\u6e29\u6d41\u52a8");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("noderef", "pg5");
    model.result("pg2").run();

    model.title("\u6563\u70ed\u5668\u7684\u5f3a\u5236\u7a7a\u6c14\u51b7\u5374");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u8ba1\u7b97\u5b8c\u6210\u540e\u53d1\u9001\u5e26\u6709\u62a5\u544a\u7684\u7535\u5b50\u90ae\u4ef6\n\u2022 \u7528\u6237\u5b9a\u4e49\u7684\u7535\u5b50\u90ae\u4ef6\u670d\u52a1\u5668\u8bbe\u7f6e\uff0c\u5728\u8fd0\u884c\u5df2\u7f16\u8bd1\u7684\u72ec\u7acb App \u65f6\u975e\u5e38\u6709\u7528\n\u2022 \u7528\u4e8e\u8bbe\u7f6e\u4e0d\u540c\u7f51\u683c\u5927\u5c0f\u7684\u9009\u9879\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u5bf9\u8f93\u5165\u53c2\u6570\u8fdb\u884c\u8bef\u5dee\u63a7\u5236\n\n\u4eba\u4eec\u5e38\u5e38\u5bf9\u6563\u70ed\u5668\u5728\u7ed9\u5b9a\u98ce\u6247\u66f2\u7ebf\u4e0b\u7684\u6563\u70ed\u80fd\u529b\u8fdb\u884c\u57fa\u51c6\u6d4b\u8bd5\uff0c\u8fdb\u884c\u6b64\u7c7b\u5b9e\u9a8c\u7684\u4e00\u79cd\u53ef\u884c\u7684\u65b9\u6cd5\u662f\u5c06\u6563\u70ed\u5668\u653e\u7f6e\u5728\u5e26\u6709\u9694\u70ed\u58c1\u7684\u77e9\u5f62\u901a\u9053\u4e2d\u3002\n\n\u7136\u540e\u6d4b\u91cf\u901a\u9053\u5165\u53e3\u548c\u51fa\u53e3\u7684\u6e29\u5ea6\u548c\u538b\u529b\uff0c\u5e76\u5c06\u6563\u70ed\u5668\u5e95\u5ea7\u4fdd\u6301\u5728\u7ed9\u5b9a\u6e29\u5ea6\u6240\u9700\u7684\u529f\u7387\u3002\u6ee1\u8db3\u8fd9\u4e9b\u6761\u4ef6\u540e\uff0c\u60a8\u53ef\u4ee5\u8ba1\u7b97\u6563\u70ed\u5668\u7684\u6563\u70ed\u91cf\u548c\u901a\u9053\u7684\u538b\u529b\u635f\u5931\u3002\n\n\u8be5 App \u7684\u76ee\u7684\u662f\u5bf9\u6b64\u7c7b\u57fa\u51c6\u5b9e\u9a8c\u8fdb\u884c\u7814\u7a76\u3002\u60a8\u53ef\u4ee5\u66f4\u6539\u6563\u70ed\u5668\u7684\u7c7b\u578b\u4ee5\u53ca\u7fc5\u7247\u6216\u5f15\u811a\u7684\u6570\u91cf\u53ca\u5176\u5c3a\u5bf8\uff0c\u4ece\u800c\u6839\u636e\u901a\u9053\u4e0a\u7ed9\u5b9a\u7684\u538b\u529b\u635f\u5931\u6765\u786e\u5b9a\u6700\u4f73\u8bbe\u8ba1\u3002\n\n\u8be5 App \u652f\u6301\u6539\u53d8\u7a7a\u6c14\u6d41\u901f\u548c\u70ed\u6e90\u7387\uff0c\u5176\u4e2d\u5047\u8bbe\u6d41\u6001\u4e3a\u4ee3\u6570 y+ \u6a21\u578b\u63cf\u8ff0\u7684\u6e4d\u6d41\uff0c\u5e76\u6c42\u89e3\u975e\u7b49\u6e29\u6d41\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("forced_air_cooling_with_heat_sink.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
