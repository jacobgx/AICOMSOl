/*
 * vector_hysteresis_modeling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class vector_hysteresis_modeling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Verifications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature("ccc").set("CoilName", "1");
    model.study("std1").feature("ccc").set("outputmap", new String[]{});
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").setSolveFor("/physics/mf", true);

    model.param().set("W", "174.5[mm]");
    model.param().descr("W", "\u94c1\u82af\u7684\u5bbd\u5ea6");
    model.param().set("H", "180[mm]");
    model.param().descr("H", "\u94c1\u82af\u7684\u9ad8\u5ea6");
    model.param().set("w", "30[mm]");
    model.param().descr("w", "\u4e2d\u5fc3\u94c1\u82af\u7684\u5bbd\u5ea6");
    model.param().set("h1", "H-2*w");
    model.param().descr("h1", "\u6846\u67b6\u7684\u9ad8\u5ea6");
    model.param().set("w1", "(W-3*w)/2");
    model.param().descr("w1", "\u6846\u67b6\u7684\u5bbd\u5ea6");
    model.param().set("Th", "5*0.48[mm]");
    model.param().descr("Th", "\u94c1\u82af\u7684\u539a\u5ea6");
    model.param().set("f", "10[Hz]");
    model.param().descr("f", "\u9988\u7535\u7535\u538b\u9891\u7387");
    model.param().set("R_coil", "11.42[ohm]");
    model.param().descr("R_coil", "\u7ebf\u5708\u7535\u963b");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.5);
    model.component("comp1").func("step1").set("smooth", 1);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"W", "H/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "3*H/4"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"w1", "h1/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"-(w+w1)/2", "H-h1/4"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"w1", "h1/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"(w+w1)/2", "H-h1/4"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2", "r3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Th/2", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "w*0.7");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "h1/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-w-w1", "H-h1/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "0.1*w", 0);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("cyl1", 3, 4, 5);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("del1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "(w+w1)*2");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*W", "H", "3*w"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-W", "0", "0"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("mf").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("mf").feature("symp1").selection().set(5, 9, 11, 16, 21, 30, 37, 42, 44, 49);
    model.component("comp1").physics("mf").feature("symp1").set("Symmetry_type", "Antisymmetry");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().set(3);
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "JilesAtherton");
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mf").feature("coil1").set("VCoil", "14.5[V]*sin(2*pi*f*t)*step1(f*t)");
    model.component("comp1").physics("mf").feature("coil1").set("N", 90);
    model.component("comp1").physics("mf").feature("coil1").set("wireProperties", "Resistance");
    model.component("comp1").physics("mf").feature("coil1").set("RCoilDC", "R_coil");
    model.component("comp1").physics("mf").feature("coil1").selection().set(5, 6);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").set("fl", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").set("fA", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().set(51);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("cg1").selection().set(35);
    model.component("comp1").physics("mf").create("coil2", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil2").selection().set(2, 4);
    model.component("comp1").physics("mf").feature("coil2").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil2").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mf").feature("coil2").set("VCoil", "14.5[V]*cos(2*pi*f*t)*step1(f*t)");
    model.component("comp1").physics("mf").feature("coil2").set("N", 90);
    model.component("comp1").physics("mf").feature("coil2").set("wireProperties", "Resistance");
    model.component("comp1").physics("mf").feature("coil2").set("RCoilDC", "R_coil");
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").set("fl", 2);
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").set("fA", 2);
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").feature("ct1").selection().set(26);
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").feature("cg1").selection().set(6);
    model.component("comp1").physics("mf").create("gfa1", "GaugeFixingA", 3);
    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2, 4, 5, 6);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").label("\u7ebf\u5708");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("JilesAtherton", "JilesAtherton", "Jiles\u2013Atherton model parameters");
    model.component("comp1").material("mat2").label("Jiles\u2013Atherton Hysteretic Material");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .label("Jiles\u2013Atherton model parameters");
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("MsJA", new String[]{"1.6e6[A/m]", "0", "0", "0", "1.6e6[A/m]", "0", "0", "0", "1.6e6[A/m]"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("aJA", new String[]{"560[A/m]", "0", "0", "0", "560[A/m]", "0", "0", "0", "560[A/m]"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("kJA", new String[]{"1200[A/m]", "0", "0", "0", "1200[A/m]", "0", "0", "0", "1200[A/m]"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("cJA", new String[]{"0.1", "0", "0", "0", "0.1", "0", "0", "0", "0.1"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("alphaJA", new String[]{"0.0007", "0", "0", "0", "0.0007", "0", "0", "0", "0.0007"});
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("MsJA", new String[]{"1.31e6[A/m]", "0", "0", "0", "1.33e6[A/m]", "0", "0", "0", "1.31e6[A/m]"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("aJA", new String[]{"233.78[A/m]", "0", "0", "0", "172.856[A/m]", "0", "0", "0", "233.78[A/m]"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("kJA", new String[]{"374.975[A/m]", "0", "0", "0", "232.652[A/m]", "0", "0", "0", "374.975[A/m]"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("cJA", new String[]{"736e-3", "0", "0", "0", "652e-3", "0", "0", "0", "736e-3"});
    model.component("comp1").material("mat2").propertyGroup("JilesAtherton")
         .set("alphaJA", new String[]{"562e-6", "0", "0", "0", "417e-6", "0", "0", "0", "562e-6"});
    model.component("comp1").material("mat2")
         .label("Jiles-Atherton \u5404\u5411\u5f02\u6027\u6ede\u56de\u6750\u6599");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(14);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "w/10");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.3);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(0,2.5,300)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").feature("comp1_A").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_A").set("scaleval", "5e-3");
    model.sol("sol1").feature("v2").feature("comp1_mf_als1_H_").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_mf_als1_H_").set("scaleval", "1e4");
    model.sol("sol1").feature("v2").feature("comp1_mf_als1_M_").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_mf_als1_M_").set("scaleval", "1e6");
    model.sol("sol1").feature("v2").feature("comp1_mf_psi").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_mf_coil1_ICoil_ode").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_mf_coil2_ICoil_ode").set("scalemethod", "manual");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Prism");
    model.result("pg1").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();

    model.view("view3").set("locked", true);

    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xcoord", "W");
    model.result("pg1").feature("mslc1").set("ycoord", "H");
    model.result("pg1").feature("mslc1").set("zcoord", 0);
    model.result("pg1").run();
    model.result("pg1").feature("strmsl1").set("xcoord", "W");
    model.result("pg1").feature("strmsl1").set("ycoord", "H");
    model.result("pg1").feature("strmsl1").set("zcoord", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 111, 0);
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", "H-61.5[mm]");
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().dataset().create("av1", "Average");
    model.result().dataset("av1").set("intsurface", true);
    model.result().dataset("av1").set("intvolume", true);
    model.result().dataset("av1").set("showlevel", "off");
    model.result().dataset("av1").selection().geom("geom1", 2);
    model.result().dataset("av1").selection().set(30);
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().set(14);
    model.result().dataset("surf1").set("param", "xy");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "cpt1");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("expr", "mf.By");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "mf.Bx");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "interp", 0);
    model.result("pg2").setIndex("interp", "range(200,2.5,300)", 0);
    model.result("pg2").run();
    model.result("pg2").label("\u65cb\u8f6c\u573a");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mf.ICoil_1"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7ebf\u5708\u7535\u6d41"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "range(200,2,300)", 0);
    model.result("pg3").run();
    model.result("pg3").label("\u7ebf\u5708\u7535\u6d41");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u6d41\u8c10\u6ce2\u6c61\u67d3");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "mf.ICoil_1-mf.VCoil_1/mf.RCoil_1", 0);
    model.result("pg4").feature("glob1").set("xdata", "fourier");
    model.result("pg4").feature("glob1").set("fouriershow", "spectrum");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").set("data", "av1");
    model.result("pg5").setIndex("looplevelinput", "interp", 0);
    model.result("pg5").setIndex("interp", "range(200,1,300)", 0);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "mf.By", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u78c1\u901a\u5bc6\u5ea6 y \u5206\u91cf", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "mf.Hy");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("\u6ede\u56de");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 111, 0);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"mf.Mx", "mf.My"});
    model.result("pg6").feature("arws1").set("xnumber", 41);
    model.result("pg6").feature("arws1").set("arrowtype", "cone");
    model.result("pg6").feature().duplicate("arws2", "arws1");
    model.result("pg6").run();
    model.result("pg6").feature("arws2").set("scaleactive", true);
    model.result("pg6").feature("arws2").set("scale", "7e-9");
    model.result("pg6").feature("arws2").set("data", "surf1");
    model.result("pg6").feature("arws2").set("color", "blue");
    model.result("pg6").feature("arws2").set("titletype", "none");
    model.result("pg6").run();
    model.result("pg6").feature("arws1").set("scaleactive", true);
    model.result("pg6").feature("arws1").set("scale", "7e-9");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u78c1\u5316");
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u78c1\u573a");
    model.result("pg7").run();
    model.result("pg7").feature("arws1").set("expr", new String[]{"mf.Hx", "mf.Hy"});
    model.result("pg7").feature("arws1").set("scale", "2e-5");
    model.result("pg7").run();
    model.result("pg7").feature("arws2").set("expr", new String[]{"mf.Hx", "mf.Hy"});
    model.result("pg7").feature("arws2").set("scale", "2e-5");
    model.result("pg7").run();
    model.result("pg5").run();

    model.title("\u77e2\u91cf\u6ede\u56de\u5efa\u6a21");

    model
         .description("\u672c\u6559\u7a0b\u662f\u4e00\u4e2a\u57fa\u51c6\u6a21\u578b\uff0c\u53ef\u4ee5\u91cd\u73b0\u201c\u6d4b\u8bd5\u7535\u78c1\u5206\u6790\u65b9\u6cd5 (TEAM) \u95ee\u9898 32\u201d\uff0c\u8be5\u95ee\u9898\u53ef\u4ee5\u8bc4\u4f30\u5404\u5411\u5f02\u6027\u78c1\u6ede\u4eff\u771f\u7684\u6570\u503c\u65b9\u6cd5\u3002\u78c1\u6ede\u4e09\u67f1\u53e0\u7247\u94c1\u82af\u53d7\u5230\u7531\u4e24\u4e2a\u7ebf\u5708\u4ea7\u751f\u7684\u65f6\u53d8\u78c1\u573a\u4f5c\u7528\u3002\u672c\u4f8b\u4f7f\u7528 Jiles-Atherton \u6750\u6599\u6a21\u578b\uff08\u5728\u201c\u78c1\u573a\u201d\u63a5\u53e3\u4e2d\u63d0\u4f9b\uff09\u6765\u6a21\u62df\u6750\u6599\u7684\u54cd\u5e94\uff0c\u5e76\u91cd\u73b0\u5df2\u53d1\u8868\u7684\u5b9e\u9a8c\u548c\u6570\u503c\u6570\u636e\u3002\n\n\u7ebf\u5708\u7531\u4ea4\u6d41\u7535\u538b\u6e90\u6fc0\u52b1\uff0c\u8fd9\u4e9b\u7535\u538b\u6e90\u5f7c\u6b64\u76f8\u79fb 90 \u5ea6\uff0c\u4ea7\u751f\u7684\u78c1\u573a\u5728\u94c1\u82af\u7684\u67d0\u4e9b\u533a\u57df\u4e2d\u65cb\u8f6c\u3002\u5916\u52a0\u78c1\u573a\u4e3b\u8981\u4f4d\u4e8e xy \u5e73\u9762\uff0c\u800c\u6750\u6599\u5448\u5404\u5411\u5f02\u6027\uff0c\u56e0\u6b64\u5bf9\u6cbf x \u6216 y\u00a0\u65b9\u5411\u65bd\u52a0\u7684\u573a\u4f1a\u4ea7\u751f\u4e0d\u540c\u7684\u53cd\u5e94\u3002\n\n\u4e3a\u4e86\u7cbe\u786e\u6a21\u62df\u77ac\u6001\u573a\uff0c\u9700\u8981\u77e2\u91cf\u6ede\u56de\u6a21\u578b\uff0c\u5e76\u901a\u8fc7\u5c06\u78c1\u901a\u5bc6\u5ea6\u7ed8\u5236\u6210\u4e00\u4e2a\u4ea4\u6d41\u5468\u671f\uff08\u5bf9\u5e94\u4e8e\u4e00\u4e2a\u6ede\u56de\u73af\uff09\u7684\u78c1\u573a\u7684\u51fd\u6570\u6765\u663e\u793a\u78c1\u6ede\u7279\u6027\u3002\u672c\u4f8b\u4f7f\u7528\u76f4\u63a5\u6c42\u89e3\u5668 (PARDISO) \u4ee3\u66ff\u9ed8\u8ba4\u7684\u8fed\u4ee3\u6c42\u89e3\u5668\uff0c\u5176\u4e2d\u5e94\u7528\u4e86\u201cA \u573a\u89c4\u8303\u56fa\u5b9a\u201d\u7279\u5f81\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("vector_hysteresis_modeling.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
