/*
 * magnetic_damping.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_damping {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");

    model.param().set("sigma", "3.774e7[S/m]");
    model.param().descr("sigma", "\u6750\u6599\u7684\u7535\u5bfc\u7387");
    model.param().set("a_c", "5e5[A]");
    model.param().descr("a_c", "\u5bfc\u7ebf\u4e0a\u7684\u5916\u52a0\u7535\u6d41");
    model.param().set("r0", "0.05[m]");
    model.param().descr("r0", "\u7ebf\u5708\u534a\u5f84");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{0.9, 0.025, 0.1});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0, 0.575, 0.45});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r0");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.5, 0.5, 0});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().set(2);
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil1").selection().set(3);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "a_c");
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().set(14);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("cg1").selection().set(13);
    model.component("comp1").physics("mf").create("ecd1", "ExternalCurrentDensity", 3);
    model.component("comp1").physics("mf").feature("ecd1").selection().set(3);
    model.component("comp1").physics("mf").feature("ecd1").set("Je", new String[]{"0", "0", "a_c/(pi*r0^2)"});
    model.component("comp1").physics("mf").feature("fsp1").set("sigma_stab_mat", "userdef");
    model.component("comp1").physics("mf").feature("fsp1")
         .set("sigma_stab", new String[]{"10[S/m]", "0", "0", "0", "10[S/m]", "0", "0", "0", "10[S/m]"});
    model.component("comp1").physics("solid").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s", 0.1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(5);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(17);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "1e4", "0"});
    model.component("comp1").physics("solid").feature("bndl1").set("harmonicPerturbation", true);
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(17);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0", "1e4", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat1").selection().set(2, 3);

    model.component("comp1").multiphysics().create("mmcpl1", "Magnetomechanics", 3);
    model.component("comp1").multiphysics("mmcpl1").set("OnlyLorentz", true);

    model.component("comp1").mesh("mesh1").autoMeshSize(7);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "sigma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "S/m", 0);
    model.study("std1").feature("param").setIndex("pname", "sigma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "S/m", 0);
    model.study("std1").feature("param").setIndex("pname", "a_c", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0[A] 500000[A]", 0);
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"mf/ecd1"});
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("plist", "range(5,0.5,50)");
    model.study("std1").feature("frlin").set("useadvanceddisable", true);
    model.study("std1").feature("frlin").set("disabledphysics", new String[]{"mf/ecd1", "solid/bndl2"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s3").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("s3").feature("dDef").set("linsolver", "pardiso");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 91, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
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
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("evalmethodactive", "off");
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
    model.result("pg1").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("evalmethodactive", "off");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset("dset4").set("frametype", "spatial");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevel", 91, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("differential", true);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("differential", true);
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").label("\u76f4\u6d41\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("evalmethod", "linpoint");
    model.result("pg1").run();
    model.result("pg1").feature("strmsl1").set("evalmethod", "linpoint");
    model.result("pg1").run();
    model.result("pg1").feature("strmsl1").feature("col1").set("evalmethod", "linpoint");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result().move("pg3", 1);
    model.result("pg3").label("\u4ea4\u6d41\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg3").run();
    model.result("pg3").feature("mslc1").set("evalmethod", "harmonic");
    model.result("pg3").feature("mslc1").create("sel1", "Selection");
    model.result("pg3").feature("mslc1").feature("sel1").selection().set(2);
    model.result("pg3").run();
    model.result("pg3").feature("strmsl1").set("evalmethod", "harmonic");
    model.result("pg3").feature("strmsl1").create("sel1", "Selection");
    model.result("pg3").feature("strmsl1").feature("sel1").selection().set(2);
    model.result("pg3").run();
    model.result("pg3").feature("strmsl1").feature("col1").set("evalmethod", "harmonic");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("differential", false);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u4ea4\u6d41\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("expr", "mf.normJ");
    model.result("pg4").feature("mslc1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg4").run();
    model.result("pg4").feature("strmsl1").feature("col1").active(false);
    model.result("pg4").run();
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"mf.Jx", "mf.Jy", "mf.Jz"});
    model.result("pg4").feature("strmsl1")
         .set("descr", "\u7535\u6d41\u5bc6\u5ea6 \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg4").feature("strmsl1").set("color", "black");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb\u5747\u65b9\u6839 vs. \u9891\u7387");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(18);
    model.result("pg5").feature("ptgr1").set("expr", "solid.disp_rms");
    model.result("pg5").feature("ptgr1").set("descr", "\u4f4d\u79fb\u5747\u65b9\u6839");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/mmcpl1", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "sigma", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "S/m", 0);
    model.study("std2").feature("param").setIndex("pname", "sigma", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "S/m", 0);
    model.study("std2").feature("param").setIndex("pname", "a_c", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0[A] 500000[A]", 0);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"mf/coil1"});
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/mmcpl1", false);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("tlist", "range(0,0.001,0.1)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"mf/coil1", "solid/bndl1"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol7").feature("t1").create("fc2", "FullyCoupled");

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol9");
    model.sol("sol9").study("std2");
    model.sol("sol9").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol9");
    model.batch("p2").run("compute");

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg6").set("data", "dset7");
    model.result("pg6").setIndex("looplevel", 101, 0);
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg6").feature("mslc1").set("colortable", "Prism");
    model.result("pg6").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg6").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg6").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg6").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg6").feature("strmsl1").set("titletype", "none");
    model.result("pg6").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg6").feature("strmsl1").set("udist", 0.02);
    model.result("pg6").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg6").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("inheritcolor", false);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("data", "parent");
    model.result("pg6").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg6").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg6").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg6").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset7");
    model.result("pg7").setIndex("looplevel", 101, 0);
    model.result("pg7").setIndex("looplevel", 2, 1);
    model.result("pg7").label("\u5e94\u529b (solid) 1");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg7").feature("vol1").set("threshold", "manual");
    model.result("pg7").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("vol1").set("colortable", "Rainbow");
    model.result("pg7").feature("vol1").set("colortabletrans", "none");
    model.result("pg7").feature("vol1").set("colorscalemode", "linear");
    model.result("pg7").feature("vol1").set("resolution", "custom");
    model.result("pg7").feature("vol1").set("refine", 2);
    model.result("pg7").feature("vol1").set("colortable", "Prism");
    model.result("pg7").feature("vol1").create("def", "Deform");
    model.result("pg7").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u4f4d\u79fb vs. \u65f6\u95f4");
    model.result("pg8").set("data", "dset7");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(18);
    model.result("pg8").feature("ptgr1").set("expr", "solid.disp");
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").run();

    model.title("\u632f\u52a8\u5bfc\u7535\u56fa\u4f53\u7684\u78c1\u963b\u5c3c");

    model
         .description("\u5f53\u5bfc\u7535\u56fa\u4f53\u6750\u6599\u5728\u9759\u78c1\u573a\u4e2d\u79fb\u52a8\u65f6\u4f1a\u4ea7\u751f\u6da1\u6d41\u3002\u6d41\u8fc7\u5bfc\u4f53\u7684\u7535\u6d41\u672c\u8eab\u5728\u78c1\u573a\u4e2d\u8fd0\u52a8\uff0c\u4ece\u800c\u5728\u56fa\u4f53\u4e0a\u4ea7\u751f\u6d1b\u4f26\u5179\u529b\u3002\u56e0\u6b64\uff0c\u5728\u9759\u78c1\u573a\u4e2d\u632f\u52a8\u7684\u5bfc\u7535\u56fa\u4f53\u4f1a\u53d7\u5230\u7ed3\u6784\u963b\u5c3c\u4f5c\u7528\u3002\u672c\u4f8b\u4f7f\u7528\u4e24\u79cd\u4e0d\u540c\u7684\u65b9\u6cd5\u6765\u8ba1\u7b97\u78c1\u963b\u5c3c\u6548\u5e94\u3002\u9996\u5148\uff0c\u6211\u4eec\u5728\u4e00\u5b9a\u9891\u7387\u8303\u56f4\u5185\u5bf9\u60ac\u81c2\u6881\u8fdb\u884c\u8c10\u6ce2\u6fc0\u52b1\uff0c\u5e76\u5c06\u5176\u7f6e\u4e8e\u5f3a\u78c1\u573a\u4e2d\u3002\u7136\u540e\uff0c\u5728\u5b8c\u6574\u7684\u77ac\u6001\u7814\u7a76\u4e2d\u8ba1\u7b97\u540c\u6837\u7684\u6548\u5e94\uff0c\u800c\u4e0d\u662f\u5bf9\u6881\u65bd\u52a0\u7a81\u7136\u7684\u5916\u52a0\u8f7d\u8377\u3002\u672c\u4f8b\u4ecb\u7ecd\u7684\u65b9\u6cd5\u5047\u8bbe\u7ed3\u6784\u4f4d\u79fb\u7684\u76f8\u5bf9\u5927\u5c0f\u8f83\u5c0f\uff0c\u6750\u6599\u5177\u6709\u5404\u5411\u540c\u6027\u548c\u7ebf\u6027\u7279\u6027\uff0c\u5e76\u4e14\u78c1\u573a\u662f\u9759\u6001\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();

    model.label("magnetic_damping.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
