/*
 * misaligned_rotor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:47 by COMSOL 6.3.0.290. */
public class misaligned_rotor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");
    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.component("comp1").multiphysics().create("brbc1", "BeamRotorBearingCoupling", 2);
    model.component("comp1").multiphysics("brbc1").set("BeamRotor_physics", "rotbm");
    model.component("comp1").multiphysics("brbc1").set("Bearing_physics", "hdb");
    model.component("comp1").multiphysics("brbc1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/rotbm", true);
    model.study("std1").feature("time").setSolveFor("/physics/hdb", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/brbc1", true);

    model.param().set("Ow", "210[rad/s]");
    model.param().descr("Ow", "\u8f74\u7684\u89d2\u901f\u5ea6");
    model.param().set("d", "10[mm]");
    model.param().descr("d", "\u8f74\u5f84");
    model.param().set("L", "100[mm]");
    model.param().descr("L", "\u8f74\u957f");
    model.param().set("a", "20[mm]");
    model.param().descr("a", "\u8f74\u60ac\u5782\u957f\u5ea6");
    model.param().set("rho_d", "7780[kg/m^3]");
    model.param().descr("rho_d", "\u5706\u76d8\u5bc6\u5ea6");
    model.param().set("xd", "40[mm]");
    model.param().descr("xd", "\u5706\u76d8\u4e0e\u5de6\u8f74\u627f\u7684\u8ddd\u79bb");
    model.param().set("Dd", "50[mm]");
    model.param().descr("Dd", "\u5706\u76d8\u76f4\u5f84");
    model.param().set("hd", "10[mm]");
    model.param().descr("hd", "\u5706\u76d8\u539a\u5ea6");
    model.param().set("me", "8e-9[kg*m]");
    model.param().descr("me", "\u5706\u76d8\u4e0d\u5e73\u8861\u5e45\u5ea6");
    model.param().set("Lb", "5[mm]");
    model.param().descr("Lb", "\u8f74\u627f\u957f\u5ea6");
    model.param().set("C", "0.05[mm]");
    model.param().descr("C", "\u8f74\u627f\u521d\u59cb\u95f4\u9699");
    model.param().set("mu0", "9.4[mPa*s]");
    model.param().descr("mu0", "\u6cb9\u9ecf\u5ea6");
    model.param().set("para", "0");
    model.param().descr("para", "\u4e0d\u5bf9\u4e2d\u5f00\u5173\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 a a+xd a+L");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol1").set("z", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lb");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"a-Lb/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u8f74\u627f 1");
    model.component("comp1").geom("geom1").feature("cyl1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"a+L-Lb", "0", "0"});
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u8f74\u627f 2");
    model.component("comp1").geom("geom1").feature("cyl2").set("contributeto", "csel3");

    model.component("comp1").selection().create("uni1", "Union");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("uni1").label("\u8f74\u627f");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_csel2_bnd", "geom1_csel3_bnd"});
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(3, 17);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").label("\u8f74\u627f\u5de6\u8fb9\u7f18");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94a2");
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().named("geom1_csel1_edg");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"200[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.29"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7780[kg/m^3]"});

    model.component("comp1").physics("rotbm").selection().named("geom1_csel1_edg");
    model.component("comp1").physics("rotbm").prop("RotorProperties").set("RotorSpeed", "AngularVelocity");
    model.component("comp1").physics("rotbm").prop("RotorProperties").set("omegar", "Ow");
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "d");
    model.component("comp1").physics("rotbm").create("disk1", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk1").selection().set(11);
    model.component("comp1").physics("rotbm").feature("disk1").set("COM", "Relative");
    model.component("comp1").physics("rotbm").feature("disk1").set("zr", "me/rotbm.disk1.mass");
    model.component("comp1").physics("rotbm").feature("disk1").set("SpecifiedBy", "GeomDim");
    model.component("comp1").physics("rotbm").feature("disk1").set("rho", "rho_d");
    model.component("comp1").physics("rotbm").feature("disk1").set("d", "Dd");
    model.component("comp1").physics("rotbm").feature("disk1").set("h", "hd");
    model.component("comp1").physics("rotbm").create("gr1", "Gravity", 1);
    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("hdb").feature("hjb1").set("C_plain", "C");
    model.component("comp1").physics("hdb").feature("hjb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure_mat", "userdef");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure", "mu0");
    model.component("comp1").physics("hdb").feature("hjb1").set("rho_c", "866[kg/m^3]");
    model.component("comp1").physics("hdb").feature().duplicate("hjb2", "hjb1");
    model.component("comp1").physics("hdb").feature("hjb2").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("hdb").feature("hjb2").create("mlgn1", "Misalignment", 2);
    model.component("comp1").physics("hdb").feature("hjb2").feature("mlgn1").set("u0z", "0.6*C*para");
    model.component("comp1").physics("hdb").feature("hjb2").feature("mlgn1").set("th0z", "0.004*para");

    model.component("comp1").multiphysics().duplicate("brbc2", "brbc1");
    model.component("comp1").multiphysics("brbc2").selection().named("geom1_csel3_bnd");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(8, 22);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_csel1_edg");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("edg1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 40);
    model.component("comp1").mesh("mesh1").feature("edg1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").selection().set(15);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").set("numelem", 60);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,1e-3,0.5)");
    model.study("std1").create("batsw", "BatchSweep");
    model.study("std1").feature("batsw").set("clearmesh", false);
    model.study("std1").feature("batsw").set("clearsol", false);
    model.study("std1").feature("batsw").set("maxallow", 2);
    model.study("std1").feature("batsw").setIndex("pname", "Ow", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("batsw").setIndex("pname", "Ow", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("batsw").setIndex("pname", "para", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "0 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("b1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");
    model.batch("b1").feature("daDef").run();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 501, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"rotbm.mises"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u5e94\u529b (rotbm)");
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "rotbm.re");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").create("line2", "Line");
    model.result("pg1").feature("line2").set("expr", new String[]{"1"});
    model.result("pg1").feature("line2").set("linetype", "tube");
    model.result("pg1").feature("line2").set("radiusexpr", new String[]{"rotbm.re "});
    model.result("pg1").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line2").set("tuberadiusscale", 1);
    model.result("pg1").feature("line2").set("tubeendcaps", false);
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "custom");
    model.result("pg1").feature("line2")
         .set("customcolor", new double[]{0.9803921580314636, 0.7843137383460999, 0.7058823704719543});
    model.result("pg1").feature("line2").set("threshold", "manual");
    model.result("pg1").feature("line2").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line2").set("titletype", "none");
    model.result("pg1").feature("line2").label("\u8f6c\u5b50");
    model.result("pg1").feature("line2").create("def", "Deform");
    model.result("pg1").feature("line2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("line2").feature("def").set("scale", "1");
    model.result("pg1").feature("line2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("plotdata", "points");
    model.result("pg1").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj1").selection().set(11);
    model.result("pg1").feature("pttraj1").selection().inherit(false);
    model.result("pg1").feature("pttraj1").selection().embedded(false);
    model.result("pg1").feature("pttraj1").set("linetype", "none");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj1").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj1").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj1")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj1")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e20x ", "0.5*rotbm.disk1.de*rotbm.e20y ", "0.5*rotbm.disk1.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj1")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e30x ", "0.5*rotbm.disk1.de*rotbm.e30y ", "0.5*rotbm.disk1.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj1").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj1").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj1").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj1").set("titletype", "none");
    model.result("pg1").feature("pttraj1").label("\u5706\u76d8 1");
    model.result("pg1").feature("pttraj1").create("def", "Deform");
    model.result("pg1").feature("pttraj1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj1").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 501, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "pfilm");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "pfilm");
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("inherittubescale", false);
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature().copy("line1", "pg1/line1");
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg2").feature("line1").set("tuberadiusscale", 0.2);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "rightdouble");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5706\u76d8\u8f68\u9053");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(11);
    model.result("pg3").feature("ptgr1").set("expr", "w");
    model.result("pg3").feature("ptgr1").set("unit", "\u00b5m");
    model.result("pg3").feature("ptgr1").set("xdata", "expr");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "v");
    model.result("pg3").feature("ptgr1").set("xdataunit", "\u00b5m");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").feature("ptgr1").set("autopoint", false);
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u5bf9\u4e2d", 0);
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u4e0d\u5bf9\u4e2d", 1);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").set("titletype", "label");
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u627f 1 \u8f68\u9053");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").selection().set(6);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8f74\u627f 2 \u8f68\u9053");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").selection().set(18);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u8f74\u9888\u901f\u5ea6\uff08\u8f74\u627f 2\uff09");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(18);
    model.result("pg6").feature("ptgr1").set("expr", "vt");
    model.result("pg6").feature("ptgr1").set("unit", "mm/s");
    model.result("pg6").feature("ptgr1").set("linewidth", 2);
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u5bf9\u4e2d", 0);
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u4e0d\u5bf9\u4e2d", 1);
    model.result("pg6").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("geom1_csel3_bnd");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u538b\u529b\u6bd4\u8f83\uff1a\u53f3\u4fa7\u8f74\u627f");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 1, 1);
    model.result("pg7").set("edges", false);
    model.result("pg7").set("view", "new");
    model.result("pg7").create("con1", "Contour");
    model.result("pg7").feature("con1").set("expr", "hdb.p");
    model.result("pg7").feature("con1").set("contourtype", "filled");
    model.result("pg7").feature("con1").set("colortable", "RainbowLight");
    model.result("pg7").feature().duplicate("con2", "con1");
    model.result("pg7").run();
    model.result("pg7").feature("con2").set("data", "dset3");
    model.result("pg7").feature("con2").set("inheritplot", "con1");
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg7").feature("con2").create("trn1", "Transformation");
    model.result("pg7").feature("con2").feature("trn1").set("move", new String[]{"3*Lb", "0", "0"});
    model.result("pg7").create("tlan1", "TableAnnotation");
    model.result("pg7").feature("tlan1").set("source", "localtable");
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "L+6.1*Lb", 0, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-d/2", 0, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-d/4", 0, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u4e0d\u5bf9\u4e2d", 0, 3);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "L+3.1*Lb", 1, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-d/2", 1, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-d/4", 1, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u6b63\u5e38", 1, 3);
    model.result("pg7").feature("tlan1").set("showpoint", false);
    model.result("pg7").set("legendpos", "rightdouble");
    model.result("pg7").run();

    model.batch("b1").feature("daDef").set("operation", "clearalldata");
    model.batch("b1").feature("daDef").run();

    model.title("\u8f74\u627f\u4e0d\u5bf9\u4e2d\u5bf9\u8f6c\u5b50\u632f\u52a8\u7684\u5f71\u54cd");

    model
         .description("\u672c\u4f8b\u5206\u6790\u7531\u4e24\u4e2a\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u652f\u6491\u7684\u8f6c\u5b50\u3002\u8fd9\u4e24\u4e2a\u8f74\u627f\u4e4b\u95f4\u653e\u7f6e\u7684\u504f\u5fc3\u76d8\u4f7f\u8f6c\u5b50\u6da1\u52a8\uff1b\u5176\u4e2d\u4e00\u4e2a\u8f74\u627f\u4e0e\u8f6c\u5b50\u8f74\u4e0d\u5bf9\u4e2d\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u8f6c\u5b50\u52a8\u529b\u5b66\u6a21\u5757\u201d\u4e2d\u7684\u5185\u7f6e\u591a\u7269\u7406\u573a\u63a5\u53e3\u201c\u6881\u8f6c\u5b50\u4e0e\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u201d\u5bf9\u8f6c\u5b50\u548c\u8f74\u627f\u8fdb\u884c\u8026\u5408\u4eff\u771f\uff0c\u4f9d\u6b21\u5206\u6790\u5bf9\u4e2d\u548c\u4e0d\u5bf9\u4e2d\u4e24\u79cd\u60c5\u51b5\uff0c\u5e76\u5bf9\u8fd9\u4e24\u79cd\u60c5\u51b5\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\uff0c\u4ee5\u4e86\u89e3\u4e0d\u5bf9\u4e2d\u4ea7\u751f\u7684\u5f71\u54cd\u3002\n\n\u4eff\u771f\u7ed3\u679c\u5305\u62ec\u5706\u76d8\u8f68\u9053\u3001\u5706\u76d8\u4e0a\u8f6c\u5b50\u54cd\u5e94\u7684\u9891\u8c31\u4ee5\u53ca\u8f74\u627f\u7684\u538b\u529b\u5206\u5e03\u7b49\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("misaligned_rotor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
